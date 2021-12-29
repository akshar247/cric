package com.livecrickettv.livet20.star.sports.live;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.Spinner;
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
import com.livecrickettv.livet20.star.sports.live.ads.Ads_Preference;
import com.livecrickettv.livet20.star.sports.live.ads.All_Ads_Config;
import com.livecrickettv.livet20.star.sports.live.ads.All_Ads_Config2;
import com.livecrickettv.livet20.star.sports.live.ads.All_Ads_Config3;
import com.livecrickettv.livet20.star.sports.live.ads.NativeApp;
import com.livecrickettv.livet20.star.sports.live.ads.NativeAppAdapter;
import com.livecrickettv.livet20.star.sports.live.exoplayerr.extension.MultiQualitySelectorAdapter;
import com.livecrickettv.livet20.star.sports.live.exoplayerr.media.ExoMediaSource;
import com.livecrickettv.livet20.star.sports.live.exoplayerr.media.SimpleMediaSource;
import com.livecrickettv.livet20.star.sports.live.exoplayerr.ui.ExoVideoPlaybackControlView;
import com.livecrickettv.livet20.star.sports.live.exoplayerr.ui.ExoVideoView;

import java.util.ArrayList;

import static com.livecrickettv.livet20.star.sports.live.exoplayerr.orientation.OnOrientationChangedListener.SENSOR_LANDSCAPE;
import static com.livecrickettv.livet20.star.sports.live.exoplayerr.orientation.OnOrientationChangedListener.SENSOR_PORTRAIT;

public class SimpleVideoViewActivity extends AppCompatActivity {
    private ExoVideoView videoView;
    private View wrapper;
    Uri uri;
    String link,name;
    RecyclerView recycler_native;
    NativeAppAdapter nativeAppAdapter;
    Ads_Preference preference;
    public static final String NATIVE_CATEGORY = "customNative";
    ArrayList<NativeApp> nativeAppArrayList;
    public FrameLayout banner;
    private final String[] modes = new String[]{"RESIZE_MODE_FIT", "RESIZE_MODE_FIXED_WIDTH"
            , "RESIZE_MODE_FIXED_HEIGHT", "RESIZE_MODE_FILL", "RESIZE_MODE_ZOOM"};
    private Spinner modeSpinner;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_video_view);
        banner=findViewById(R.id.banner_player);
        preference =new Ads_Preference(this);
        if (preference.get_inter_interval().equalsIgnoreCase("0")){
            new All_Ads_Config(this).Interstitial_Ads();
        }
        if (preference.get_inter_interval().equalsIgnoreCase("2")){
            new All_Ads_Config(this).Interstitial_Ads();
        }


        new All_Ads_Config(this).Custom_Interstitial_Show();
        new All_Ads_Config3(this).Banner_Ads(banner);
        recycler_native = (RecyclerView) findViewById(R.id.recycler_native);
        new All_Ads_Config3(this).Native_Ads(findViewById(R.id.native_container));
        ads();

        wrapper = findViewById(R.id.wrapper);

        initSpinner();
        initControllerMode();
        initVideoView();
        initCustomViews();
    }

    private void initVideoView() {
        videoView = findViewById(R.id.videoView);
        videoView.setPortrait(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT);
        videoView.setBackListener((view, isPortrait) -> {
            if (isPortrait) {
                finish();
            }
            return false;
        });
        videoView.setOrientationListener(orientation -> {
            if (orientation == SENSOR_PORTRAIT) {
                changeToPortrait();
            } else if (orientation == SENSOR_LANDSCAPE) {
                changeToLandscape();
            }
        });

//        videoView.setGestureEnabled(false);
//
//
//        SimpleMediaSource mediaSource = new SimpleMediaSource("http://flv2.bn.netease.com/videolib3/1604/28/fVobI0704/SD/fVobI0704-mobile.mp4");
//        mediaSource.setDisplayName("Apple HLS");
        link=getIntent().getExtras().getString("link");
        name=getIntent().getExtras().getString("namee");
        uri= Uri.parse(link);

        SimpleMediaSource mediaSource = new SimpleMediaSource(uri);
        mediaSource.setDisplayName(name);

        //demo only,not real multi quality, urls are the same actually
    /*    List<ExoMediaSource.Quality> qualities = new ArrayList<>();
        ExoMediaSource.Quality quality;
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.YELLOW);
        SpannableString spannableString = new SpannableString("1080p");
        spannableString.setSpan(colorSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        quality = new SimpleQuality(spannableString, mediaSource.uri());
        qualities.add(quality);

        spannableString = new SpannableString("720p");
        colorSpan = new ForegroundColorSpan(Color.LTGRAY);
        spannableString.setSpan(colorSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        quality = new SimpleQuality(spannableString, mediaSource.uri());
        qualities.add(quality);

        mediaSource.setQualities(qualities);*/
//        videoView.changeWidgetVisibility(R.id.exo_player_controller_back,View.INVISIBLE);
        videoView.setMultiQualitySelectorNavigator(new MultiQualitySelectorAdapter.MultiQualitySelectorNavigator() {
            @Override
            public boolean onQualitySelected(ExoMediaSource.Quality quality) {
                //  quality.setUri(Uri.parse("https://media.w3.org/2010/05/sintel/trailer.mp4"));
                return false;
            }
        });
        videoView.play(mediaSource, false);

    }

    private void initSpinner() {
        modeSpinner = findViewById(R.id.spinner);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                videoView.setResizeMode(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapter.addAll(modes);
        modeSpinner.setAdapter(adapter);
    }

    private void initControllerMode() {
        CheckBox all = findViewById(R.id.all);
        CheckBox top = findViewById(R.id.top);
        CheckBox topLandscape = findViewById(R.id.topLandscape);
        CheckBox bottom = findViewById(R.id.bottom);
        CheckBox bottomLandscape = findViewById(R.id.bottomLandscape);
        CheckBox none = findViewById(R.id.none);
        findViewById(R.id.applyControllerMode).setOnClickListener(v -> {
            int mode = ExoVideoPlaybackControlView.CONTROLLER_MODE_NONE;
            if (all.isChecked()) {
                mode |= ExoVideoPlaybackControlView.CONTROLLER_MODE_ALL;
            }
            if (top.isChecked()) {
                mode |= ExoVideoPlaybackControlView.CONTROLLER_MODE_TOP;
            }

            if (topLandscape.isChecked()) {
                mode |= ExoVideoPlaybackControlView.CONTROLLER_MODE_TOP_LANDSCAPE;
            }
            if (bottom.isChecked()) {
                mode |= ExoVideoPlaybackControlView.CONTROLLER_MODE_BOTTOM;
            }
            if (bottomLandscape.isChecked()) {
                mode |= ExoVideoPlaybackControlView.CONTROLLER_MODE_BOTTOM_LANDSCAPE;
            }
            if (none.isChecked()) {
                mode |= ExoVideoPlaybackControlView.CONTROLLER_MODE_NONE;
            }

            videoView.setControllerDisplayMode(mode);
            Toast.makeText(SimpleVideoViewActivity.this, "change controller display mode", Toast.LENGTH_SHORT).show();
        });


    }

    private void initCustomViews() {
        findViewById(R.id.addToTop).setOnClickListener(v -> {
            View view = getLayoutInflater().inflate(R.layout.cutom_view_top, null, false);
            videoView.addCustomView(ExoVideoPlaybackControlView.CUSTOM_VIEW_TOP, view);
        });

        findViewById(R.id.addToTopLandscape).setOnClickListener(v -> {
            View view = getLayoutInflater().inflate(R.layout.cutom_view_top_landscape, null, false);
            videoView.addCustomView(ExoVideoPlaybackControlView.CUSTOM_VIEW_TOP_LANDSCAPE, view);
        });


        findViewById(R.id.addToBottomLandscape).setOnClickListener(v -> {
            View view = getLayoutInflater().inflate(R.layout.cutom_view_bottom_landscape, null, false);
            videoView.addCustomView(ExoVideoPlaybackControlView.CUSTOM_VIEW_BOTTOM_LANDSCAPE, view);
        });
    }

    private void changeToPortrait() {

        // WindowManager operation is not necessary
        WindowManager.LayoutParams attr = getWindow().getAttributes();
//        attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Window window = getWindow();
        window.setAttributes(attr);
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        wrapper.setVisibility(View.GONE);
    }


    private void changeToLandscape() {

        // WindowManager operation is not necessary

        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        Window window = getWindow();
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        wrapper.setVisibility(View.GONE);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT > 23) {
            videoView.resume();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if ((Build.VERSION.SDK_INT <= 23)) {
            videoView.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT <= 23) {
            videoView.pause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Build.VERSION.SDK_INT > 23) {
            videoView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.releasePlayer();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return videoView.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
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
            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot dataSnapshot) {
                nativeAppArrayList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    NativeApp apps = snapshot.getValue(NativeApp.class);
                    nativeAppArrayList.add(apps);
                    nativeAppAdapter.loadData(nativeAppArrayList);
                }
            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError databaseError) {
                Toast.makeText(SimpleVideoViewActivity.this, "error" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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


}