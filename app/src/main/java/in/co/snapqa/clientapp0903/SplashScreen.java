package in.co.snapqa.clientapp0903;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class SplashScreen extends Activity {
    private PreferenceManager prefManager;


    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        prefManager = new PreferenceManager(this);

        new Handler().postDelayed(new Runnable() {

        /*
         * Showing splash screen with a timer. This will be useful when you
         * want to show case your app logo / company
         */

            @Override
            public void run() {

                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i;

                if (!prefManager.isFirstTimeLaunch()) {
                    i = new Intent(SplashScreen.this, WelcomActivity.class);
                    prefManager.setFirstTimeLaunch(false);
                } else {
                    i = new Intent(SplashScreen.this, MainActivity.class);
                }

                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}

