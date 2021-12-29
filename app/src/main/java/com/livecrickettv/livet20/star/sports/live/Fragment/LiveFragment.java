package com.livecrickettv.livet20.star.sports.live.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.livecrickettv.livet20.star.sports.live.Adapter.LiveMatchAdapter;
import com.livecrickettv.livet20.star.sports.live.Model.LiveMatchModel;
import com.livecrickettv.livet20.star.sports.live.R;
import com.livecrickettv.livet20.star.sports.live.ads.All_Ads_Config;
import com.livecrickettv.livet20.star.sports.live.ads.NativeApp;
import com.livecrickettv.livet20.star.sports.live.ads.NativeAppAdaptersmall;
import com.livecrickettv.livet20.star.sports.live.config.CommonFunctions;
import com.livecrickettv.livet20.star.sports.live.config.Constants;
import com.livecrickettv.livet20.star.sports.live.config.CricketLiveConfig;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class LiveFragment extends Fragment implements LiveMatchAdapter.AdapterCallback {
    RecyclerView live_matche_recycler;
    LiveMatchModel liveMatchModel;
    LiveMatchAdapter liveMatchAdapter;
    public ShimmerFrameLayout mShimmerViewContainer;

    RecyclerView recycler_native;
    NativeAppAdaptersmall nativeAppAdapter;
    public static final String NATIVE_CATEGORY = "customNative";
    ArrayList<NativeApp> nativeAppArrayList;


    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_live, viewGroup, false);
      //  CommonFunctions.createProgressBar(getActivity(),"Please wait …");
        recycler_native = (RecyclerView) inflate.findViewById(R.id.recycler_native);
        new All_Ads_Config(getActivity()).Small_Native_Ads(inflate.findViewById(R.id.native_container));
        ads();

        mShimmerViewContainer = inflate.findViewById(R.id.shimmer_view_container);
      //  mShimmerViewContainer.startShimmer();



        live_matche_recycler = (RecyclerView) inflate.findViewById(R.id.recycler_live_match);
        live_matche_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        init();
        return inflate;
    }

    private void init() {
        try {
            mylivematches();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mylivematches() {
        try {
            if (CommonFunctions.checkConnection(getActivity())) {
                String url = CricketLiveConfig.WEBURL + CricketLiveConfig.getMatches;
                Map<String, String> mParams = new HashMap<>();
                Log.e("url", url);
                Log.e("mParams", mParams.toString());
                AndroidNetworking.post(url)
                        .addBodyParameter(Constants.matchStatus,"1")
                        .addBodyParameter(Constants.offset,"0")
                        .addBodyParameter(Constants.limit,"50")
                        .setTag(url)
                        .setPriority(Priority.MEDIUM)
                        .build().getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Log.e("res", response.toString());
                            Gson gson = new Gson();
                            liveMatchModel = gson.fromJson(response.toString(), LiveMatchModel.class);
                            if (liveMatchModel.status) {
                                setLiveadapter();
                            } else {
                                Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                            }
                            mShimmerViewContainer.setVisibility(View.GONE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        try {
                            CommonFunctions.destroyProgressBar();
                            Toast.makeText(getActivity(), R.string.msg_server_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setLiveadapter() {
        try {
            if (liveMatchModel.matches.STARTED != null) {
                liveMatchAdapter = new LiveMatchAdapter(getActivity(), liveMatchModel.matches.STARTED,liveMatchModel,this);
                live_matche_recycler.setAdapter(liveMatchAdapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSubcategoryClick(LiveMatchModel.Matches.STARTED items, int position) {
        Intent i=new Intent(getActivity(),ServerActivity.class);
        startActivity(i);
    }


    private void ads() {
        nativeAppArrayList = new ArrayList<>();
        nativeAppAdapter = new NativeAppAdaptersmall(getActivity(), nativeAppArrayList);
        recycler_native.setNestedScrollingEnabled(false);
        GridLayoutManager gridLayoutManager1;
        gridLayoutManager1 = new GridLayoutManager(getActivity(), 1) {
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
                Toast.makeText(getActivity(), "error" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onCancelled: " + databaseError.toException());
            }
        });

        nativeAppAdapter.setOnItemSelectListener(new NativeAppAdaptersmall.OnItemSelectListener() {
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
