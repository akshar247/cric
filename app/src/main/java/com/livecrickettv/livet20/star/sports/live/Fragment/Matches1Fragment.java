package com.livecrickettv.livet20.star.sports.live.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.livecrickettv.livet20.star.sports.live.Adapter.UpcomingMatchAdapter;
import com.livecrickettv.livet20.star.sports.live.Model.CompletedMatchModel;
import com.livecrickettv.livet20.star.sports.live.Model.LiveMatchModel;
import com.livecrickettv.livet20.star.sports.live.Model.UpcomingMatchModel;
import com.livecrickettv.livet20.star.sports.live.R;
import com.livecrickettv.livet20.star.sports.live.SplashActivity;
import com.livecrickettv.livet20.star.sports.live.ads.Ads_Preference;
import com.livecrickettv.livet20.star.sports.live.ads.All_Ads_Config;
import com.livecrickettv.livet20.star.sports.live.config.CommonFunctions;
import com.livecrickettv.livet20.star.sports.live.config.Constants;
import com.livecrickettv.livet20.star.sports.live.config.CricketLiveConfig;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Matches1Fragment extends AppCompatActivity {
    Activity ctx;
    public static TabLayout tabLayout;
    public ViewPager viewPager;
    public static AppCompatActivity activity;
    String click;

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fragment_matches);
        ImageView back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        preference = new Ads_Preference(this);
        if (preference.get_inter_interval().equalsIgnoreCase("0")){
            new All_Ads_Config(this).Interstitial_Ads();
        }
        if (preference.get_inter_interval().equalsIgnoreCase("3")){
            new All_Ads_Config(this).Interstitial_Ads();
        }
        new All_Ads_Config(this).Custom_Interstitial_Show();
        activity = this;
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        this.viewPager = (ViewPager) findViewById(R.id.viewpager);

        Intent intent = getIntent();
        click = intent.getStringExtra("click");

        view_pager();

    }

/*    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_matches, viewGroup, false);

        return inflate;
    }*/

    private void view_pager() {
        setupViewPager(this.viewPager);
        tabLayout.setupWithViewPager(this.viewPager);
//        tabLayout.setTabTextColors(Color.parseColor("#ce0ee3"), Color.parseColor("#ce0ee3"));

    }
    private void setupViewPager(ViewPager viewPager2) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new UpcomingFragment(), "Upcoming");
        viewPagerAdapter.addFragment(new LiveFragment(), "Live");
        viewPagerAdapter.addFragment(new CompletedFragment(), "Completed");
        viewPager2.setAdapter(viewPagerAdapter);
        viewPager2.addOnAdapterChangeListener(new ViewPager.OnAdapterChangeListener() {
            public void onAdapterChanged(ViewPager viewPager, PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2) {
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        if (click.equalsIgnoreCase("Upcoming")){
            viewPager2.setCurrentItem(0);
        }else if(click.equalsIgnoreCase("live")){
            viewPager2.setCurrentItem(1);
        }else {
            viewPager2.setCurrentItem(2);
        }

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList();
        private final List<String> mFragmentTitleList = new ArrayList();

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            return this.mFragmentList.get(i);
        }

        public int getCount() {
            return this.mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String str) {
            this.mFragmentList.add(fragment);
            this.mFragmentTitleList.add(str);
        }

        public CharSequence getPageTitle(int i) {
            return this.mFragmentTitleList.get(i);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (preference.get_back_inter().equalsIgnoreCase("ON")){
            new All_Ads_Config(this).Interstitial_Ads();
        }
        new All_Ads_Config(this).Custom_Interstitial_Show();
    }

    Ads_Preference preference;
}
