package com.livecrickettv.livet20.star.sports.live;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.livecrickettv.livet20.star.sports.live.activity.GetStartActivity;
import com.livecrickettv.livet20.star.sports.live.activity.StartActivity;
import com.livecrickettv.livet20.star.sports.live.ads.Ads_Preference;

public class Stay_Activity extends AppCompatActivity {
    Ads_Preference preference;
    TextView link;
    ImageView new_icon;
    TextView download,txt_5,txt_1,txt_2;
    RelativeLayout rl_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stay);
        preference = new Ads_Preference(this);
        link = findViewById(R.id.app_link);
        new_icon = (ImageView) findViewById(R.id.new_app_image);
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        download = (TextView) findViewById(R.id.textdownload);
        txt_5 = (TextView) findViewById(R.id.txt_5);
        txt_1 = (TextView) findViewById(R.id.txt_1);
        txt_2 = (TextView) findViewById(R.id.txt_2);


        download.setText(preference.get_download_and_update_text());
        link.setText(preference.get_new_link());
        txt_5.setText(preference.get_new_app_name());
        txt_2.setText(preference.get_stay_text2());
        txt_1.setText(preference.get_stay_text1());
        Glide.with(this)
                .load(preference.get_new_app_icon())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new_icon);

        if (preference.get_new_app_version().equalsIgnoreCase(BuildConfig.VERSION_NAME)){
            startActivity(new Intent(Stay_Activity.this, GetStartActivity.class));
            finish();
        }

        if (preference.get_back_button_visibility().equalsIgnoreCase("0")){
            rl_back.setVisibility(View.GONE);
        }else {
            rl_back.setVisibility(View.VISIBLE);
        }

        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Stay_Activity.this, GetStartActivity.class));
                finish();
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(preference.get_new_link())));
            }
        });


    }
}