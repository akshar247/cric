package com.livecrickettv.livet20.star.sports.live.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.livecrickettv.livet20.star.sports.live.Model.UpcomingMatchModel;
import com.livecrickettv.livet20.star.sports.live.R;
import com.livecrickettv.livet20.star.sports.live.ads.Ads_Preference;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class UpcomingMatchAdapter extends RecyclerView.Adapter<UpcomingMatchAdapter.MyViewHolder> {
    private Context mContext;
    private List<UpcomingMatchModel.Matches.NOTSTARTED> mData;
    UpcomingMatchModel upcomingMatchModel;
    AdapterCallback adapterCallback;
    Random r, r2;
    Integer[] images = {
            R.color.green,
            R.color.black,
            R.color.teal_700,
            R.color.purple_700,
            R.color.colorAccent
    };
    Integer[] images1 = {
            R.color.yellow,
            R.color.blue,
            R.color.Gray,
            R.color.purple_200,
            R.color.gray
    };

  //  private static final int MENU_ITEM_VIEW_TYPE = 0;
  //  private static final int LIST_AD_DELTA = 10;
  //  private static final int AD = 1;
    Ads_Preference preference;
    boolean showAdds = true;

    public UpcomingMatchAdapter(Context mContext, List<UpcomingMatchModel.Matches.NOTSTARTED> mData, UpcomingMatchModel upcomingMatchModel, AdapterCallback adapterCallback1) {
        this.mContext = mContext;
        this.mData = mData;
        this.adapterCallback = adapterCallback1;
        this.upcomingMatchModel = upcomingMatchModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        preference = new Ads_Preference(mContext);

        /*if (viewType == AD) {

            adViewHolder madViewHolder = new adViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.admob_native_ad_big_layout, null, false));
            return madViewHolder;
        } else {*/

            view = mInflater.inflate(R.layout.item_matches, parent, false);
            return new MyViewHolder(view);
       // }
    }

  /*  public class adViewHolder extends UpcomingMatchAdapter.MyViewHolder {
        public TemplateView Adtemplate;

        public adViewHolder(@NonNull View itemView) {
            super(itemView);
            Adtemplate = itemView.findViewById(R.id.my_template_largen);
            Adtemplate.setVisibility(View.GONE);

        }
    }*/

 /*   @Override
    public int getItemViewType(int position) {
        if (position > 0 && position % LIST_AD_DELTA == 0) {
            return AD;
        }
        return MENU_ITEM_VIEW_TYPE;
    }*/

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
       // AdLoader adLoader;
      //  if (getItemViewType(position) == MENU_ITEM_VIEW_TYPE) {
            try {
                final UpcomingMatchModel.Matches.NOTSTARTED upcomingmatch = mData.get(position);
                r = new Random();
                r2 = new Random();
                holder.matchname.setText(upcomingmatch.matchName + "");
                holder.score1.setText(upcomingmatch.t1run + "/"+upcomingmatch.t1wk+"");
                holder.score2.setText(upcomingmatch.t2run + "/"+upcomingmatch.t2wk+"");
                holder.over1.setText("(" + upcomingmatch.t1over + ")");
                holder.over2.setText("(" +upcomingmatch.t2over + ")");
                holder.tname1.setText(upcomingmatch.team1Name + "");
                holder.t1.setText(upcomingmatch.team1Name + "");
                holder.tname2.setText(upcomingmatch.team2Name + "");
                holder.t2.setText(upcomingmatch.team2Name + "");
                holder.flag1.setImageResource(images[r.nextInt(images.length)]);
                holder.flag2.setImageResource(images1[r2.nextInt(images1.length)]);

                long l = Long.parseLong(upcomingmatch.start_time + "");
                Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                cal.setTimeInMillis(l);
                String date1 = DateFormat.format("dd/MM/yyyy" + "    " + " hh:mm:ss.aa", cal).toString();
//        String Datetime = date1.format(String.valueOf(cal.getTime()));
                holder.start_time.setText(date1);
                holder.layout1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            if (adapterCallback != null)
                                adapterCallback.onSubcategoryClick(upcomingmatch, position);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } /*else if (preference.get_ads_on_off().equalsIgnoreCase("ON")) {
            try {
                if ((getItemViewType(position) == AD)) {
                    adLoader = new AdLoader.Builder(mContext, preference.get_admob_native_advance_id())
                            .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                                @Override
                                public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                                    try {
                                        // Show the ad.
                                        NativeTemplateStyle styles = new
                                                NativeTemplateStyle.Builder().build();

                                        TemplateView template = ((UpcomingMatchAdapter.adViewHolder) holder).Adtemplate;
                                        template.setStyles(styles);
                                        template.setNativeAd(unifiedNativeAd);
                                        ((adViewHolder)holder).Adtemplate.setVisibility(View.VISIBLE);
                                       // CommonFunctions.destroyProgressBar();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    CommonFunctions.destroyProgressBar();
                                }
                            })
                            *//*.withAdListener(new AdListener() {
                                @Override
                                public void onAdFailedToLoad(int errorCode) {
                                    CommonFunctions.destroyProgressBar();
                                    Toast.makeText(mContext, errorCode, Toast.LENGTH_SHORT).show();
                                    // Handle the failure by logging, altering the UI, and so on.
                                }
                            })*//*
                            .withNativeAdOptions(new NativeAdOptions.Builder()
                                    // Methods in the NativeAdOptions.Builder class can be
                                    // used here to specify individual options settings.
                                    .build())
                            .build();
                    if (this.showAdds) {
                      //  CommonFunctions.createProgressBar(mContext,"Please wait …");
                        adLoader.loadAd(new AdRequest.Builder().build());
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }*/

       /* if (preference.get_ads_on_off().equalsIgnoreCase("ON")){

        }else {
            if (position==mData.size()){
                CommonFunctions.destroyProgressBar();
            }
        }*/

  //  }

    @Override
    public int getItemCount() {
        return mData.size();

       /* int additionalContent = 0;
        if (mData.size() > 0 && LIST_AD_DELTA > 0 && mData.size() > LIST_AD_DELTA) {
            additionalContent = mData.size() / LIST_AD_DELTA;
        }
        return mData.size() + additionalContent;*/
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView flag1;
        ImageView flag2;
        TextView matchname;
        TextView score1;
        TextView score2;
        TextView over1;
        TextView over2;
        TextView tname1, t1;
        TextView start_time;
        TextView tname2, t2;
        LinearLayout layout1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            flag1 = (ImageView) itemView.findViewById(R.id.img_flag_1);
            flag2 = (ImageView) itemView.findViewById(R.id.img_flag_2);
            matchname = (TextView) itemView.findViewById(R.id.txt_matches_name);
            score1 = (TextView) itemView.findViewById(R.id.score_1);
            score2 = (TextView) itemView.findViewById(R.id.score_2);
            over1 = (TextView) itemView.findViewById(R.id.over_1);
            over2 = (TextView) itemView.findViewById(R.id.over_2);
            tname1 = (TextView) itemView.findViewById(R.id.country_name_1);
            t1 = (TextView) itemView.findViewById(R.id.countryname1);
            tname2 = (TextView) itemView.findViewById(R.id.country_name_2);
            t2 = (TextView) itemView.findViewById(R.id.countryname2);
            start_time = (TextView) itemView.findViewById(R.id.start_time);
            layout1 = (LinearLayout) itemView.findViewById(R.id.layout1);

        }
    }

    public interface AdapterCallback {
        void onSubcategoryClick(UpcomingMatchModel.Matches.NOTSTARTED items, int position);
    }

/*    private int getRealPosition(int position) {
        if (LIST_AD_DELTA == 0) {
            return position;
        } else {
            return position - position / LIST_AD_DELTA;
        }
    }*/
}
