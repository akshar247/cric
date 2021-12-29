package com.livecrickettv.livet20.star.sports.live;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.livecrickettv.livet20.star.sports.live.activity.GetStartActivity;
import com.livecrickettv.livet20.star.sports.live.ads.Ads_Preference;
import com.livecrickettv.livet20.star.sports.live.ads.All_Ads_Config;
import com.livecrickettv.livet20.star.sports.live.ads.All_Ads_Config2;
import com.livecrickettv.livet20.star.sports.live.ads.All_Ads_Config3;

public class SplashActivity extends AppCompatActivity {
    Ads_Preference preference;
    DatabaseReference databaseReference;
    String Tag = "abc";
    TextView textView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if (isConnected()) {
            textView.setText("Please Wait...");
        } else {
            textView.setText("No Intenet Connection!");
            progressBar.setVisibility(View.INVISIBLE);
        }
        preference = new Ads_Preference(this);
        // callhome();
        databaseReference = FirebaseDatabase.getInstance().getReference(getString(R.string.app_name));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Log.e(Tag, "onDataChange: " + snapshot);
                preference.set_ads_on_off(snapshot.child("ADS_ON_OFF").getValue().toString());
                preference.set_priority(snapshot.child("PRIORITY").getValue().toString());

                preference.set_admob_app_id(snapshot.child("ADMOB_APP_ID").getValue().toString());

                preference.set_admob_interstitial_id(snapshot.child("ADMOB_INTERSTITIAL_ID").getValue().toString());
                preference.set_admob_native_advance_id(snapshot.child("ADMOB_NATIVE_ADVANCED_ID").getValue().toString());
                preference.set_admob_native_advance_id_2(snapshot.child("ADMOB_NATIVE_ADVANCED_ID_2").getValue().toString());
                preference.set_admob_native_advance_id_3(snapshot.child("ADMOB_NATIVE_ADVANCED_ID_3").getValue().toString());

                preference.set_admob_banner_id(snapshot.child("ADMOB_BANNER_ID").getValue().toString());
                preference.set_admob_banner_id_2(snapshot.child("ADMOB_BANNER_ID_2").getValue().toString());
                preference.set_admob_banner_id_3(snapshot.child("ADMOB_BANNER_ID_3").getValue().toString());

                preference.set_facebook_app_id(snapshot.child("FACEBOOK_APP_ID").getValue().toString());
                preference.set_facebook_interstitial_id(snapshot.child("FACEBOOK_INTERSTITIAL_ID").getValue().toString());
                preference.set_facebook_native_id(snapshot.child("FACEBOOK_NATIVE_ID").getValue().toString());
                preference.set_facebook_banner_id(snapshot.child("FACEBOOK_BANNER_ID").getValue().toString());

                preference.set_videolink(snapshot.child("videolink").getValue().toString());
                preference.set_videoname(snapshot.child("videoname").getValue().toString());


                preference.set_no_run(snapshot.child("NO_RUN").getValue().toString());
                preference.set_custom_ads_on_off(snapshot.child("CUSTOM_ADS_ON_OFF").getValue().toString());
                preference.set_new_link(snapshot.child("NEW_APP_LINK").getValue().toString());
                preference.set_new_app_icon(snapshot.child("NEW_APP_ICON").getValue().toString());
                preference.set_download_and_update_text(snapshot.child("DOWNLOAD_AND_UPDATE_TEXT").getValue().toString());
                preference.set_back_button_visibility(snapshot.child("BACK_BUTTON_VISIBILITY").getValue().toString());
                preference.set_new_app_name(snapshot.child("NEW_APP_NAME").getValue().toString());
                preference.set_new_app_version(snapshot.child("NEW_APP_VERSION_CHECK").getValue().toString());
                preference.set_second_ads(snapshot.child("SECOND_ADS").getValue().toString());
                preference.set_stay_text1(snapshot.child("STAY_TEXT_1").getValue().toString());
                preference.set_stay_text2(snapshot.child("STAY_TEXT_2").getValue().toString());
                preference.set_back_inter(snapshot.child("BACK_PRESS_INTER_AD").getValue().toString());
                preference.set_inter_interval(snapshot.child("INTER_INTERVAL").getValue().toString());


          //      MobileAds.initialize(getApplicationContext(), preference.get_admob_app_id());
                FacebookSdk.setApplicationId(preference.get_facebook_app_id());
                FacebookSdk.sdkInitialize(getApplicationContext());
                AppEventsLogger.activateApp(getApplication());
              new All_Ads_Config(SplashActivity.this).FB_Load_Interstitial();
                new All_Ads_Config(SplashActivity.this).Load_Interstitial();
  /*
                new All_Ads_Config2(SplashActivity.this).FB_Load_Interstitial();
                new All_Ads_Config2(SplashActivity.this).Load_Interstitial();

                new All_Ads_Config3(SplashActivity.this).FB_Load_Interstitial();
                new All_Ads_Config3(SplashActivity.this).Load_Interstitial();*/
                callhome();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(Tag, "Database Error ==>> : " + error.getMessage());
            //    MobileAds.initialize(getApplicationContext(), preference.get_admob_app_id());
                FacebookSdk.setApplicationId(preference.get_facebook_app_id());
                FacebookSdk.sdkInitialize(getApplicationContext());
                AppEventsLogger.activateApp(getApplication());
               /* new All_Ads_Config(SplashActivity.this).FB_Load_Interstitial();
                new All_Ads_Config(SplashActivity.this).Load_Interstitial();

                new All_Ads_Config2(SplashActivity.this).FB_Load_Interstitial();
                new All_Ads_Config2(SplashActivity.this).Load_Interstitial();

                new All_Ads_Config3(SplashActivity.this).FB_Load_Interstitial();
                new All_Ads_Config3(SplashActivity.this).Load_Interstitial();*/
                callhome();
            }
        });

    }

    private void callhome() {
        if (preference.get_no_run().equalsIgnoreCase("1")) {
            startActivity(new Intent(SplashActivity.this, GetStartActivity.class));
            finish();
        }else{
            startActivity(new Intent(SplashActivity.this, Stay_Activity.class));
            finish();
        }
    }

    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

}