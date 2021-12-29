package com.livecrickettv.livet20.star.sports.live.ads;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.livecrickettv.livet20.star.sports.live.R;
import com.mopub.common.MoPub;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubView;
import com.mopub.nativeads.MoPubNative;
import com.mopub.nativeads.MoPubStaticNativeAdRenderer;
import com.mopub.nativeads.NativeErrorCode;
import com.mopub.nativeads.ViewBinder;


public class All_Ads_Config3 {

    private static InterstitialAd AMInterstitial;
    public static MoPubInterstitial fb_interstitial;
    private static String TAG = "#####";
    public Activity activity;
    private AdLoader adLoader;
    Ads_Preference preference;


    public All_Ads_Config3(Activity activity) {
        this.activity = activity;
        preference = new Ads_Preference(activity);
    }


    public void Banner_Ads(final ViewGroup viewGroup) {
        if (is_internet(activity)) {
            if (preference.get_ads_on_off().equalsIgnoreCase("ON")) {
                if (preference.get_priority().equalsIgnoreCase("FB")) {
                    Mopup_First_Adaptive_Banner(viewGroup);
                } else if (preference.get_priority().equalsIgnoreCase("AM")) {
                    Admob_First_Adaptive_Banner(viewGroup);
                } else if (preference.get_priority().equalsIgnoreCase("ALT")) {
                    if (preference.get_alternate1()) {
                        preference.set_alternate1(false);
                        Admob_First_Adaptive_Banner(viewGroup);
                    } else {
                        preference.set_alternate1(true);
                        Mopup_First_Adaptive_Banner(viewGroup);
                    }
                }
            }
        }
    }

    public void Interstitial_Ads() {
        if (is_internet(activity)) {
            if (preference.get_ads_on_off().equalsIgnoreCase("ON")) {
                    if (preference.get_priority().equalsIgnoreCase("FB")) {
                        Show_FB_Interstitial();
                    } else if (preference.get_priority().equalsIgnoreCase("AM")) {
                        Show_Interstitial();
                    } else if (preference.get_priority().equalsIgnoreCase("ALT")) {
                        if (preference.get_alternate()) {
                            preference.set_alternate(false);
                            Show_Interstitial();
                        } else {
                            preference.set_alternate(true);
                            Show_FB_Interstitial();
                        }
                    }

            }
        }
    }

    public void Native_Ads(final ViewGroup viewGroup) {

        if (is_internet(activity)) {
            if (preference.get_ads_on_off().equalsIgnoreCase("ON")) {
                if (preference.get_priority().equalsIgnoreCase("FB")) {
                    Facebook_First_Native_Ads(viewGroup);
                } else if (preference.get_priority().equalsIgnoreCase("AM")) {
                    Admob_First_Native_Ads(viewGroup);

                } else if (preference.get_priority().equalsIgnoreCase("ALT")) {
                    if (preference.get_alternate3()) {
                        preference.set_alternate3(false);
                        Admob_First_Native_Ads(viewGroup);
                    } else {
                        preference.set_alternate3(true);
                        Facebook_First_Native_Ads(viewGroup);
                    }
                }
            }
        }
    }

    public void Small_Native_Ads(final ViewGroup viewGroup) {
        if (is_internet(activity)) {
            if (preference.get_ads_on_off().equalsIgnoreCase("ON")) {
                if (preference.get_priority().equalsIgnoreCase("FB")) {
                    Facebook_Small_First_Native_Ads(viewGroup);
                } else if (preference.get_priority().equalsIgnoreCase("AM")) {
                    Admob_First_Small_Native_Ads(viewGroup);

                } else if (preference.get_priority().equalsIgnoreCase("ALT")) {
                    if (preference.get_alternate3()) {
                        preference.set_alternate3(false);
                        Admob_First_Small_Native_Ads(viewGroup);
                    } else {
                        preference.set_alternate3(true);
                        Facebook_Small_First_Native_Ads(viewGroup);
                    }
                }
            }
        }
    }

    //admob small native
    private void Admob_First_Small_Native_Ads(final ViewGroup viewGroup) {
        try {
            adLoader = new AdLoader.Builder(activity, preference.get_admob_native_advance_id_3())
                    .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                        @Override
                        public void onNativeAdLoaded(@NonNull NativeAd nativeAd) {

                            NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                    .inflate(R.layout.small_native_ad, null);
                            populateNativeAdView(nativeAd, adView);
                            viewGroup.removeAllViews();
                            viewGroup.addView(adView);
                        }
                    })
                    .withAdListener(new AdListener() {
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError error) {
                            Log.e("vvv", " Admob All_Ads_Config  onAdFailedToLoad  " + error);
                            Facebook_First_Native_Ads(viewGroup);
                        }

                    }).build();
            adLoader.loadAd(new AdRequest.Builder().build());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //banner mopup
    void Mopup_First_Adaptive_Banner(final ViewGroup viewGroup) {
        try {
            SdkConfiguration.Builder sdkConfiguration = new SdkConfiguration.Builder(preference.get_facebook_banner_id());
            MoPub.initializeSdk(activity, sdkConfiguration.build(), new SdkInitializationListener() {
                @Override
                public void onInitializationFinished() {
                    MoPubView moPubView = new MoPubView(activity);
                    moPubView.setAdUnitId(preference.get_facebook_banner_id());
                    viewGroup.removeAllViews();
                    viewGroup.addView(moPubView);
                    moPubView.loadAd();
                    moPubView.setBannerAdListener(new MoPubView.BannerAdListener() {
                        @Override
                        public void onBannerLoaded(@NonNull MoPubView moPubView) {

                        }

                        @Override
                        public void onBannerFailed(MoPubView moPubView, MoPubErrorCode moPubErrorCode) {
                            Log.e(TAG, " Facebook Banner  onAdFailedToLoad  " + moPubErrorCode);
                            try {
                                /*final AdView adView = new AdView(activity);
                                adView.setAdSize(AdSize.BANNER);
                                adView.setAdUnitId(preference.get_admob_banner_id());
                                adView.loadAd(new AdRequest.Builder().build());
                                adView.setAdListener(new AdListener() {
                                    @Override
                                    public void onAdLoaded() {
                                        super.onAdLoaded();
                                        try {
                                            viewGroup.removeAllViews();
                                            viewGroup.addView(adView);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });*/
                                Admob_First_Adaptive_Banner(viewGroup);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onBannerClicked(MoPubView moPubView) {

                        }

                        @Override
                        public void onBannerExpanded(MoPubView moPubView) {

                        }

                        @Override
                        public void onBannerCollapsed(MoPubView moPubView) {

                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //admob banner
    void Admob_First_Adaptive_Banner(ViewGroup viewGroup) {
        try {
            final AdView adView = new AdView(activity);
            adView.setAdSize(AdSize.BANNER);
            adView.setAdUnitId(preference.get_admob_banner_id_3());
            adView.loadAd(new AdRequest.Builder().build());
            adView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    try {
                        viewGroup.removeAllViews();
                        viewGroup.addView(adView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    try {
                        Mopup_First_Adaptive_Banner(viewGroup);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //inter mopup
    public void Show_FB_Interstitial() {
        try {
            if (fb_interstitial != null) {
                fb_interstitial.show();
            } else {
                FB_Load_Interstitial();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //inter mopup
    public void FB_Load_Interstitial() {
        try {
            SdkConfiguration.Builder sdkConfiguration = new SdkConfiguration.Builder(preference.get_facebook_interstitial_id());
            MoPub.initializeSdk(activity, sdkConfiguration.build(), new SdkInitializationListener() {
                @Override
                public void onInitializationFinished() {
                    Inter_Mopup();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //admob mopup
    private void Inter_Mopup() {
        try {
            fb_interstitial = new MoPubInterstitial(activity, preference.get_facebook_interstitial_id());
            fb_interstitial.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                @Override
                public void onInterstitialLoaded(MoPubInterstitial interstitial) {

                }

                @Override
                public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
                    Show_Interstitial();
                }

                @Override
                public void onInterstitialShown(MoPubInterstitial interstitial) {

                }

                @Override
                public void onInterstitialClicked(MoPubInterstitial interstitial) {

                }

                @Override
                public void onInterstitialDismissed(MoPubInterstitial interstitial) {
                    FB_Load_Interstitial();
                }
            });
            fb_interstitial.load();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //inter admob
    public void Show_Interstitial() {
        try {
            if (AMInterstitial != null) {
                AMInterstitial.show(activity);
            } else {
                // Toast.makeText(activity, "Ad did not load", Toast.LENGTH_SHORT).show();
                displayAdMobInAd();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //admob inter
    public void Load_Interstitial() {
        displayAdMobInAd();
    }

    //admob inter
    private void displayAdMobInAd() {
        try {
            if (AMInterstitial == null) {
                AdRequest adRequest = new AdRequest.Builder().build();
                InterstitialAd.load(
                        activity,
                        preference.get_admob_interstitial_id(),
                        adRequest,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {

                                AMInterstitial = interstitialAd;
                                Log.i(TAG, "onAdLoaded");
                                // Toast.makeText(activity, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                                interstitialAd.setFullScreenContentCallback(
                                        new FullScreenContentCallback() {
                                            @Override
                                            public void onAdDismissedFullScreenContent() {
                                                // Called when fullscreen content is dismissed.
                                                // Make sure to set your reference to null so you don't
                                                // show it a second time.
                                                AMInterstitial = null;
                                                Log.d("TAG", "The ad was dismissed.");
                                                displayAdMobInAd();
                                            }

                                            @Override
                                            public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                                                super.onAdFailedToShowFullScreenContent(adError);
                                                AMInterstitial = null;
                                                Log.d("TAG", "The ad failed to show.");

                                            }


                                            @Override
                                            public void onAdShowedFullScreenContent() {
                                                // Called when fullscreen content is shown.
                                                Log.d("TAG", "The ad was shown.");
                                            }
                                        });

                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.i(TAG, loadAdError.getMessage());
                                AMInterstitial = null;

                                String error =
                                        String.format(
                                                "domain: %s, code: %d, message: %s",
                                                loadAdError.getDomain(), loadAdError.getCode(), loadAdError.getMessage());
                                //     Toast.makeText(activity, "onAdFailedToLoad() with error: " + error, Toast.LENGTH_SHORT).show();

                                Show_FB_Interstitial();

                            }
                        });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //admob native
    private void Admob_First_Native_Ads(final ViewGroup viewGroup) {
        try {
            adLoader = new AdLoader.Builder(activity, preference.get_admob_native_advance_id_3())
                    .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                        @Override
                        public void onNativeAdLoaded(@NonNull NativeAd nativeAd) {

                            NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                    .inflate(R.layout.native_ad, null);
                            populateNativeAdView(nativeAd, adView);
                            viewGroup.removeAllViews();
                            viewGroup.addView(adView);
                        }
                    })
                    .withAdListener(new AdListener() {
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError error) {
                            Log.e("vvv", " Admob All_Ads_Config  onAdFailedToLoad  " + error);
                            Facebook_First_Native_Ads(viewGroup);
                        }

                    }).build();
            adLoader.loadAd(new AdRequest.Builder().build());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //native mopup
    private void Facebook_First_Native_Ads(final ViewGroup viewGroup) {

        SdkConfiguration.Builder builder = new SdkConfiguration.Builder(preference.get_facebook_native_id());
        builder.withLogLevel(MoPubLog.LogLevel.DEBUG);
        MoPub.initializeSdk(activity, builder.build(), new SdkInitializationListener() {
            @Override
            public void onInitializationFinished() {
                ViewBinder viewBinder = new ViewBinder.Builder(R.layout.mopup_native_ad)
                        .mainImageId(R.id.ad_media_image)
                        .iconImageId(R.id.ad_app_icon)
                        .titleId(R.id.ad_headline)
                        .textId(R.id.ad_body)
                        .callToActionId(R.id.ad_call_to_action)
                        .privacyInformationIconImageId(R.id.privacy_info)
                        .sponsoredTextId(R.id.ad_advertiser)
                        .build();
                MoPubStaticNativeAdRenderer adRenderer = new MoPubStaticNativeAdRenderer(viewBinder);
                MoPubNative moPubNative = new MoPubNative(activity, preference.get_facebook_native_id(), new MoPubNative.MoPubNativeNetworkListener() {
                    @Override
                    public void onNativeLoad(com.mopub.nativeads.NativeAd nativeAd) {
                        viewGroup.removeAllViews();
                        viewGroup.addView(nativeAd.createAdView(activity, viewGroup));
                        nativeAd.getMoPubAdRenderer().renderAdView(viewGroup, nativeAd.getBaseNativeAd());
                    }

                    @Override
                    public void onNativeFail(NativeErrorCode errorCode) {
                        Admob_First_Native_Ads(viewGroup);
                    }
                });
                moPubNative.registerAdRenderer(adRenderer);
                moPubNative.makeRequest();
            }
        });

    }

    //native small mopup
    private void Facebook_Small_First_Native_Ads(final ViewGroup viewGroup) {

        SdkConfiguration.Builder builder = new SdkConfiguration.Builder(preference.get_facebook_native_id());
        builder.withLogLevel(MoPubLog.LogLevel.DEBUG);
        MoPub.initializeSdk(activity, builder.build(), new SdkInitializationListener() {
            @Override
            public void onInitializationFinished() {
                ViewBinder viewBinder = new ViewBinder.Builder(R.layout.mopup_small_native_ad)
                        .mainImageId(R.id.ad_media_image)
                        .iconImageId(R.id.ad_app_icon)
                        .titleId(R.id.ad_headline)
                        .textId(R.id.ad_body)
                        .callToActionId(R.id.ad_call_to_action)
                        .privacyInformationIconImageId(R.id.privacy_info)
                        .sponsoredTextId(R.id.ad_advertiser)
                        .build();
                MoPubStaticNativeAdRenderer adRenderer = new MoPubStaticNativeAdRenderer(viewBinder);
                MoPubNative moPubNative = new MoPubNative(activity, preference.get_facebook_native_id(), new MoPubNative.MoPubNativeNetworkListener() {
                    @Override
                    public void onNativeLoad(com.mopub.nativeads.NativeAd nativeAd) {
                        viewGroup.removeAllViews();
                        viewGroup.addView(nativeAd.createAdView(activity, viewGroup));
                        nativeAd.getMoPubAdRenderer().renderAdView(viewGroup, nativeAd.getBaseNativeAd());
                    }

                    @Override
                    public void onNativeFail(NativeErrorCode errorCode) {
                        Admob_First_Native_Ads(viewGroup);
                    }
                });
                moPubNative.registerAdRenderer(adRenderer);
                moPubNative.makeRequest();
            }
        });

    }


    private void populateNativeAdView(NativeAd nativeAd, NativeAdView adView) {
        MediaView mediaView = adView.findViewById(R.id.ad_media);
        adView.setMediaView(mediaView);

        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null || nativeAd.getStarRating() < 3) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }
        adView.setNativeAd(nativeAd);
    }

    //Internet Connection
    boolean is_internet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


}
