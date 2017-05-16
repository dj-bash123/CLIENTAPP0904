package in.co.snapqa.clientapp0903;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.msg91.sendotp.library.SendOtpVerification;
import com.msg91.sendotp.library.Verification;
import com.msg91.sendotp.library.VerificationListener;
import com.tuenti.smsradar.Sms;
import com.tuenti.smsradar.SmsListener;
import com.tuenti.smsradar.SmsRadar;

import java.io.IOException;

import in.co.snapqa.clientapp0903.interfaces.API;
import in.co.snapqa.clientapp0903.models.OTPResponse;
import in.co.snapqa.clientapp0903.models.SendOTP;
import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class VerifyOTPActivity extends AppCompatActivity implements VerificationListener{

    Verification verification;
    EditText enterOTP;
    Button verifyOTP;

    API service;

    TextView didntReceiveOTP;

    SharedPreferences sharedpreferences;
    ProgressDialog progressDialog;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Phone = "phone";
    public String phone;

    String TAG = "OTP Verification";

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
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/opensanslight.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(API.class);

        enterOTP = (EditText) findViewById(R.id.enter_otp);
        verifyOTP = (Button) findViewById(R.id.verify_otp);
        didntReceiveOTP = (TextView) findViewById(R.id.verify_otp_didnt_receive_otp);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        phone = sharedpreferences.getString(Phone, "notPresent");
        Log.d("jkhdds:  ",  "" + phone);
        getSupportActionBar().setTitle("Verify Your OTP");

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sendotp.msg91.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        final API service = retrofit.create(API.class);

        SendOTP sendOTP = new SendOTP();
        sendOTP.setCountryCode("91");
        sendOTP.setMobileNumber("9933938765");

        Call<OTPResponse> call = service.getOTP(sendOTP);
        call.enqueue(new Callback<OTPResponse>() {
            @Override
            public void onResponse(Call<OTPResponse> call, retrofit2.Response<OTPResponse> response) {
                OTPResponse otpResponse = response.body();
                //String status = otpResponse.getStatus();

                Log.d("otp response " ,"" + response.body().getStatus());
            }

            @Override
            public void onFailure(Call<OTPResponse> call, Throwable t) {

            }
        }); */

        SmsRadar.initializeSmsRadarService(getApplicationContext(), new SmsListener() {
            @Override
            public void onSmsSent(Sms sms) {

            }

            @Override
            public void onSmsReceived(Sms sms) {
                String str = sms.toString();
                str = str.replaceAll("\\D+","");
                Log.d("sms : ", " " + str);
                enterOTP.setText(str);
            }
        });

        verification = SendOtpVerification.createSmsVerification(this, phone , this, "91");
        verification.initiate();



        verifyOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(VerifyOTPActivity.this, "Just a sec!", "Logging you in", true);
                verification.verify(enterOTP.getText().toString());
            }
        });

        didntReceiveOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verification.initiate();
            }
        });

    }

    @Override
    public void onInitiated(String response) {
        Log.d(TAG, "Initialized!" + response);
    }

    @Override
    public void onInitiationFailed(Exception paramException) {
        Log.e(TAG, "Verification initialization failed: " + paramException.getMessage());
    }

    @Override
    public void onVerified(String response) {
        Log.d(TAG, "Verified!\n" + response);

        SendOTP sendOTP = new SendOTP("");
        sendOTP.setPhone(phone);

        Call<OTPResponse> call = service.otpVerified(sendOTP);

        call.enqueue(new Callback<OTPResponse>() {
            @Override
            public void onResponse(Call<OTPResponse> call, retrofit2.Response<OTPResponse> response) {
                String message = response.body().getMessage();
                if(message.equals("Successful")){
                    Toast.makeText(getApplicationContext(), "Successfully Verified", Toast.LENGTH_LONG).show();
                    Intent selectSubject = new Intent(VerifyOTPActivity.this, SelectSubjectActivity.class);
                    progressDialog.dismiss();
                    startActivity(selectSubject);

                }
                Log.d("message  csdkhds", "" + message);
            }

            @Override
            public void onFailure(Call<OTPResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });


    }

    @Override
    public void onVerificationFailed(Exception paramException) {
        Log.e(TAG, "Verification failed: " + paramException.getMessage());
        Log.d("sdfjkhsdfh ", "" + phone);
        progressDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        Log.d("verify otp mein: ", " back dabaya");
    }
}
