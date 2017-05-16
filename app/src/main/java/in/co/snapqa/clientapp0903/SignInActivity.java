package in.co.snapqa.clientapp0903;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.keiferstone.nonet.NoNet;
import com.subhrajyoti.passwordview.PasswordView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import in.co.snapqa.clientapp0903.interfaces.API;
import in.co.snapqa.clientapp0903.models.TokenRequest;
import in.co.snapqa.clientapp0903.models.TokenResponse;
import in.co.snapqa.clientapp0903.network.APIClient;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignInActivity extends AppCompatActivity {

    Button signInButton, signUpActivity;
    EditText emailId, fullName, phone;

    PasswordView password;

    Drawable drawable;

    TextView signUpText, forgotPassword;

    ProgressDialog progressDialog;

    Boolean doubleBackToExitPressedOnce = false;

    private static final String TAG = "SignIn";

    int statusCode;

    String email, pass, responseServer, phoneNumber, fName;

    SharedPreferences sharedpreferences;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Key = "key";

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onStart() {
        super.onStart();
        NoNet.monitor(this)
                .poll()
                .snackbar()
                .start();

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(startMain);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/opensanslight.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        signInButton = (Button) findViewById(R.id.signinbutton);
        signUpText = (TextView) findViewById(R.id.signUpText);
        password = (PasswordView) findViewById(R.id.signinpassword);
        phone = (EditText) findViewById(R.id.signinphone);
        forgotPassword = (TextView) findViewById(R.id.sign_in_forgot_password);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        drawable = (Drawable) getResources().getDrawable(R.drawable.user);


        getSupportActionBar().setTitle("Sign In");

        NoNet.configure()
                .endpoint("https://google.com")
                .timeout(5)
                .connectedPollFrequency(60)
                .disconnectedPollFrequency(1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final API service = retrofit.create(API.class);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = ProgressDialog.show(SignInActivity.this, "Just a sec!", "Logging you in", true);

                pass = password.getText().toString();
                phoneNumber = phone.getText().toString();


                final TokenRequest tokenRequest = new TokenRequest();

                tokenRequest.setPhone(phoneNumber);
                tokenRequest.setPassword(pass);

                Log.d("phone: ", "" + phoneNumber);
                Log.d("password: ", "" + pass);

                Call<TokenResponse> tokenResponseCall = service.getTokenAccess(tokenRequest);

                tokenResponseCall.enqueue(new Callback<TokenResponse>() {
                    @Override
                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                        TokenResponse tokenResponse = response.body();
                        statusCode = response.code();
                        //String token = tokenResponse.getToken();


                        if(tokenResponse.message.equals("Successful")){
                            Toast.makeText(SignInActivity.this, "Success", Toast.LENGTH_LONG).show();
                            Log.d("status code: ", "" + statusCode);
                            Log.d("if: ", "" + response.body().getMessage());



                            Toast.makeText(SignInActivity.this, "Success", Toast.LENGTH_LONG).show();
                            Log.d("status code: ", "" + statusCode);
                            Log.d("else if: ", "" + response.body().getMessage());

                            String tok = response.body().getToken();
                            //For Saving Token

                            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();

                            editor.putString(Key, tok);
                            editor.putString("Phone", phone.getText().toString());
                            editor.commit();

                            //OTP Verification
                            Intent mainActivity = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(mainActivity);

                        } else if(tokenResponse.message.equals("Unsuccessful")){
                            Toast.makeText(SignInActivity.this, "Incorrect credentials", Toast.LENGTH_LONG).show();
                            Log.d("status code: ", "" + statusCode);
                        }else {
                            Log.d("else: ", "" + response.body().getMessage());
                            Log.d("phone: ", "" + phoneNumber);
                            Log.d("password: ", "" + pass);
                        }

                        //Log.d(TAG, "token response:::" + token);
                        Log.d(TAG, "token response.token:::" + response.body());
                        Log.d(TAG, "token response.gettoken:::" +response.body().getToken());


                        progressDialog.dismiss();

                        //Changing Activity
                        Intent changeActivity = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(changeActivity);

                    }

                    @Override
                    public void onFailure(Call<TokenResponse> call, Throwable t) {
                        Toast.makeText(SignInActivity.this, "Failed", Toast.LENGTH_LONG).show();

                        progressDialog.dismiss();
                    }



                });
            }
        });

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeActivity = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(changeActivity);
            }
        });

    }
}
