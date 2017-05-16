package in.co.snapqa.clientapp0903;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.preference.Preference;
        import android.preference.PreferenceManager;
        import android.content.pm.PackageInfo;
        import android.content.pm.PackageManager;
        import android.content.pm.Signature;
        import android.os.Bundle;
        import android.os.Handler;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.view.ViewPager;
        import android.text.Html;
        import android.util.Base64;
        import android.util.Log;
        import android.view.View;
        import android.support.design.widget.NavigationView;
        import android.support.v4.view.GravityCompat;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBarDrawerToggle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.keiferstone.nonet.NoNet;

        import java.security.MessageDigest;
        import java.security.NoSuchAlgorithmException;

        import me.anwarshahriar.calligrapher.Calligrapher;
        import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
        import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Key = "key";


    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onStart() {
        super.onStart();

        NoNet.monitor(this)
                .poll()
                .snackbar()
                .start();

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String token = sharedpreferences.getString(Key, "notPresent");

        Log.d("token:  ", " " + token);

        if(token.equals("notPresent")){
            Intent signInFirst = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(signInFirst);
        }else {
            NewDealsFragment newDealFragment = new NewDealsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame_main_activity, newDealFragment).commit();
            getSupportActionBar().setTitle("Available Deals");
        }


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/opensanslight.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        NavigationView navigationView1 = (NavigationView) findViewById(R.id.nav_view_bottom);
        navigationView1.setNavigationItemSelectedListener(this);

        NoNet.configure()
                .endpoint("https://google.com")
                .timeout(5)
                .connectedPollFrequency(60)
                .disconnectedPollFrequency(1);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

        }
        if(id == R.id.tutorProfile){

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.ic_menu_new_deals) {

            NewDealsFragment newDealFragment = new NewDealsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame_main_activity, newDealFragment).commit();
            getSupportActionBar().setTitle("Available Deals");

        } else if (id == R.id.ic_menu_live_session) {

            AcceptedLiveSessionFragment acceptedLiveSessionFragment = new AcceptedLiveSessionFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame_main_activity, acceptedLiveSessionFragment).commit();
            getSupportActionBar().setTitle("Your Live Sessions");

        } else if (id == R.id.ic_menu_home_work) {

            AcceptedDeadlineSessionFragment acceptedDeadlineSessionFragment = new AcceptedDeadlineSessionFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame_main_activity, acceptedDeadlineSessionFragment).commit();
            getSupportActionBar().setTitle("Your Deadline Sessions");

        } else if(id == R.id.ic_menu_logout){

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.remove(Key);
            editor.commit();
            Intent signIn = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(signIn);

        } else if (id == R.id.ic_menu_profile){
            Intent tutorProfile = new Intent(MainActivity.this, TutorProfile.class);
            startActivity(tutorProfile);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
