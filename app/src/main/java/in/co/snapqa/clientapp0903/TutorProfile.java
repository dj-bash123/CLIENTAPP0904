package in.co.snapqa.clientapp0903;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.keiferstone.nonet.NoNet;

import java.util.ArrayList;

import in.co.snapqa.clientapp0903.interfaces.API;
import in.co.snapqa.clientapp0903.models.AcceptedDealResponse;
import in.co.snapqa.clientapp0903.models.AuthRequest;
import in.co.snapqa.clientapp0903.models.UserProfileResponse;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TutorProfile extends AppCompatActivity {

    TextView phoneNumber, whatsappNumber, email, accountNumber, IFSCCode, specialization, currentBalance, totalEarning, panNumber;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Key = "key";
    public static final String Subjects = "Subjects";
    public static final String Bank = "Bank";
    String rating, name, specializationString = "";
    ArrayList<String> specs;
    FloatingActionButton paymentEditFAB, specializationEditFAB;
    Typeface typeface;
    SharedPreferences.Editor editor;

    CollapsingToolbarLayout collapsingToolbarLayout;

    ProgressDialog progressDialog;

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
        Intent intent = new Intent(TutorProfile.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_profile);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/opensanslight.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        typeface = Typeface.createFromAsset(this.getAssets(), "fonts/opensanslight.ttf");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NoNet.configure()
                .endpoint("https://google.com")
                .timeout(5)
                .connectedPollFrequency(60)
                .disconnectedPollFrequency(1);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String token = sharedpreferences.getString(Key, "notPresent");

        progressDialog = ProgressDialog.show(TutorProfile.this, "Just a sec!", "Loading Details", true);

        phoneNumber = (TextView) findViewById(R.id.tutor_profile_phone_number);
        whatsappNumber = (TextView) findViewById(R.id.tutor_profile_whatsapp_number);
        email = (TextView) findViewById(R.id.tutor_profile_email_id);
        accountNumber = (TextView) findViewById(R.id.tutor_profile_account_number);
        IFSCCode = (TextView) findViewById(R.id.tutor_profile_ifsc_code);
        specialization = (TextView) findViewById(R.id.tutor_profile_specialization);
        currentBalance = (TextView) findViewById(R.id.tutor_profile_current_balance);
        totalEarning = (TextView) findViewById(R.id.tutor_profile_total_earning);
        panNumber = (TextView) findViewById(R.id.tutor_profile_pan_number);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);


        paymentEditFAB = (FloatingActionButton) findViewById(R.id.tutor_profile_payment_details_edit);
        specializationEditFAB = (FloatingActionButton) findViewById(R.id.tutor_profile_specialization_edit);

        specializationString = "";

        specializationEditFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedpreferences.edit();
                editor.putString(Subjects, "Subjects");
                editor.commit();
                Intent intent = new Intent(TutorProfile.this, SelectSubjectActivity.class);
                startActivity(intent);
            }
        });

        AuthRequest authRequest = new AuthRequest();
        authRequest.setToken(token);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API service = retrofit.create(API.class);

        Call<UserProfileResponse> call = service.userProfile(authRequest);
        call.enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                progressDialog.dismiss();
                Log.d("name:  ", " " + response.body().getName());
                rating = String.valueOf(response.body().getRating());
                name = response.body().getName() + "(" + rating + ")";
                collapsingToolbarLayout.setTitle(name);
                collapsingToolbarLayout.setExpandedTitleTypeface(typeface);
                collapsingToolbarLayout.setCollapsedTitleTypeface(typeface);
                phoneNumber.setText(response.body().getPhone());
                whatsappNumber.setText(response.body().getPhone());
                email.setText(response.body().getEmail());
                currentBalance.setText(String.valueOf(response.body().getPoints()));
                totalEarning.setText(String.valueOf(response.body().getPoints()));
                specs = (ArrayList<String>) response.body().getSpecialization();
                if(specs.isEmpty()){
                    Log.d("no", " subjects ");
                }else {
                    for(int i = 0; i< specs.size(); i++){

                        if(i == 0){
                            specializationString = specs.get(i).toString();
                        }else {
                            specializationString = specializationString + "\n" + specs.get(i).toString();
                            Log.d("spe str  ", "  " + specializationString);
                            specialization.setText(specializationString);
                        }

                    }
                }
                panNumber.setText(response.body().getBankDetails().getPanNumber());
                IFSCCode.setText(response.body().getBankDetails().getiFSC());
                accountNumber.setText(response.body().getBankDetails().getAccNo());
            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                Log.d("error in response:  ", "  " + t.getMessage());
            }
        });

        paymentEditFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedpreferences.edit();
                editor.putString(Bank, "Bank");
                editor.commit();
                Intent paymentEditIntent = new Intent(TutorProfile.this, PaymentDetailsActivity.class);
                startActivity(paymentEditIntent);
            }
        });

    }
}
