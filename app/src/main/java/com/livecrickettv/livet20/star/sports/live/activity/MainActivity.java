package com.livecrickettv.livet20.star.sports.live.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.livecrickettv.livet20.star.sports.live.Fragment.Matches1Fragment;
import com.livecrickettv.livet20.star.sports.live.R;
import com.livecrickettv.livet20.star.sports.live.ads.Ads_Preference;
import com.livecrickettv.livet20.star.sports.live.ads.All_Ads_Config;
import com.livecrickettv.livet20.star.sports.live.ads.All_Ads_Config2;
import com.livecrickettv.livet20.star.sports.live.ads.All_Ads_Config3;
import com.livecrickettv.livet20.star.sports.live.ads.NativeApp;
import com.livecrickettv.livet20.star.sports.live.ads.NativeAppAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycler_native;
    NativeAppAdapter nativeAppAdapter;
    public static final String NATIVE_CATEGORY = "customNative";
    ArrayList<NativeApp> nativeAppArrayList;
    public RelativeLayout upcoming, live, comp;
    RecyclerView recycler_native2;
    NativeAppAdapter nativeAppAdapter2;
    public static final String NATIVE_CATEGORY2 = "customNative";
    ArrayList<NativeApp> nativeAppArrayList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        if (preference.get_inter_interval().equalsIgnoreCase("2")){
            new All_Ads_Config(this).Interstitial_Ads();
        }
        if (preference.get_inter_interval().equalsIgnoreCase("1")){
            new All_Ads_Config(this).Interstitial_Ads();
        }

        new All_Ads_Config(this).Custom_Interstitial_Show();
        Ads_Preference preference = new Ads_Preference(this);
        if (preference.get_second_ads().equalsIgnoreCase("ON")) {
            recycler_native = (RecyclerView) findViewById(R.id.recycler_native);
            new All_Ads_Config3(this).Native_Ads(findViewById(R.id.native_container));
            ads();
        }
        recycler_native2 = (RecyclerView) findViewById(R.id.recycler_native2);
        new All_Ads_Config3(this).Native_Ads(findViewById(R.id.native_container2));
        ads2();

        upcoming = (RelativeLayout) findViewById(R.id.upcoming);
        live = (RelativeLayout) findViewById(R.id.live);
        comp = (RelativeLayout) findViewById(R.id.comp);

        upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Matches1Fragment.class);
                i.putExtra("click","upcoming");
                startActivity(i);
            }
        });
        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Matches1Fragment.class);
                i.putExtra("click","live");
                startActivity(i);
            }
        });
        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Matches1Fragment.class);
                i.putExtra("click","comp");
                startActivity(i);
            }
        });


    }


    private void ads() {
        nativeAppArrayList = new ArrayList<>();
        nativeAppAdapter = new NativeAppAdapter(this, nativeAppArrayList);
        recycler_native.setNestedScrollingEnabled(false);
        GridLayoutManager gridLayoutManager1;
        gridLayoutManager1 = new GridLayoutManager(this, 1) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler_native.setLayoutManager(gridLayoutManager1);
        recycler_native.setHasFixedSize(true);
        recycler_native.setAdapter(nativeAppAdapter);
        nativeCategory();
    }


    private void nativeCategory() {
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child(NATIVE_CATEGORY).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                nativeAppArrayList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    NativeApp apps = snapshot.getValue(NativeApp.class);
                    nativeAppArrayList.add(apps);
                    nativeAppAdapter.loadData(nativeAppArrayList);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "error" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onCancelled: " + databaseError.toException());
            }
        });

        nativeAppAdapter.setOnItemSelectListener(new NativeAppAdapter.OnItemSelectListener() {
            @Override
            public void click(View view, NativeApp category) {
                final String appPackageName = category.getPack();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });
    }


    private void ads2() {
        nativeAppArrayList2 = new ArrayList<>();
        nativeAppAdapter2 = new NativeAppAdapter(this, nativeAppArrayList2);
        recycler_native2.setNestedScrollingEnabled(false);
        GridLayoutManager gridLayoutManager1;
        gridLayoutManager1 = new GridLayoutManager(this, 1) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler_native2.setLayoutManager(gridLayoutManager1);
        recycler_native2.setHasFixedSize(true);
        recycler_native2.setAdapter(nativeAppAdapter2);
        nativeCategory2();
    }


    private void nativeCategory2() {
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child(NATIVE_CATEGORY).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                nativeAppArrayList2 = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    NativeApp apps = snapshot.getValue(NativeApp.class);
                    nativeAppArrayList2.add(apps);
                    nativeAppAdapter2.loadData(nativeAppArrayList2);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "error" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onCancelled: " + databaseError.toException());
            }
        });

        nativeAppAdapter2.setOnItemSelectListener(new NativeAppAdapter.OnItemSelectListener() {
            @Override
            public void click(View view, NativeApp category) {
                final String appPackageName = category.getPack();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });
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