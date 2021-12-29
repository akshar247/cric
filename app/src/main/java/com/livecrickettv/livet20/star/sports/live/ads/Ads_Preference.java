package com.livecrickettv.livet20.star.sports.live.ads;

import android.content.Context;
import android.content.SharedPreferences;

public class Ads_Preference {
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String ALTERNATE = "ALTERNATE";
    String ALTERNATE1 = "ALTERNATE1";
    String ALTERNATE3 = "ALTERNATE3";

    String ADS_ON_OFF = "ADS_ON_OFF";
    String ADMOB_OPENAD_FLAG = "ADMOB_OPENAD_FLAG";
    String PRIORITY = "PRIORITY";
    String INTER_INTERVAL = "INTER_INTERVAL";

    String ADMOB_APP_ID = "ADMOB_APP_ID";

    String ADMOB_INTERSTITIAL_ID = "ADMOB_INTERSTITIAL_ID";
    String BACK_PRESS_INTER_AD = "BACK_PRESS_INTER_AD";

    String videolink = "videolink";
    String videoname = "videoname";

    String ADMOB_REWARDED_ID = "ADMOB_REWARDED_ID";

    String ADMOB_NATIVE_ADVANCED_ID = "ADMOB_NATIVE_ADVANCED_ID";
    String ADMOB_NATIVE_ADVANCED_ID_2 = "ADMOB_NATIVE_ADVANCED_ID_2";
    String ADMOB_NATIVE_ADVANCED_ID_3 = "ADMOB_NATIVE_ADVANCED_ID_3";

    String ADMOB_BANNER_ID = "ADMOB_BANNER_ID";
    String ADMOB_BANNER_ID_2 = "ADMOB_BANNER_ID_2";
    String ADMOB_BANNER_ID_3 = "ADMOB_BANNER_ID_3";

    String ADMOB_OPEN_AD_ID = "ADMOB_OPEN_AD_ID";

    String FACEBOOK_APP_ID = "FACEBOOK_APP_ID";
    String FACEBOOK_INTERSTITIAL_ID = "FACEBOOK_INTERSTITIAL_ID";
    String FACEBOOK_NATIVE_ID = "FACEBOOK_NATIVE_ID";
    String FACEBOOK_BANNER_ID = "FACEBOOK_BANNER_ID";
    String image = "image";
    String pack = "pack";

    String NO_RUN = "NO_RUN";
    String NEW_APP_ICON = "NEW_APP_ICON";
    String NEW_APP_LINK = "NEW_APP_LINK";
    String DOWNLOAD_AND_UPDATE_TEXT = "DOWNLOAD_AND_UPDATE_TEXT";
    String STAY_TEXT_1 = "STAY_TEXT_1";
    String STAY_TEXT_2 = "STAY_TEXT_2";
    String BACK_BUTTON_VISIBILITY = "BACK_BUTTON_VISIBILITY";
    String NEW_APP_NAME = "NEW_APP_NAME";
    String NEW_APP_VERSION_CHECK = "NEW_APP_VERSION_CHECK";
    String CUSTOM_ADS_ON_OFF = "CUSTOM_ADS_ON_OFF";
    String SECOND_ADS = "SECOND_ADS";

    public static String TEST_DEVICE_ID = "e1e83878-27d8-4906-a734-1e125bb91c83";


    public Ads_Preference(Context context) {
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences("Ravi Muliya", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    //Priority Id's
    public String get_ads_on_off() {
        return sharedPreferences.getString(ADS_ON_OFF, "");
    }

    public void set_ads_on_off(String ads_on_off) {
        this.editor.putString(this.ADS_ON_OFF, ads_on_off).commit();
    }

    public String get_no_run() {
        return sharedPreferences.getString(NO_RUN, "");
    }

    public void set_no_run(String ads_no_run) {
        this.editor.putString(this.NO_RUN, ads_no_run).commit();
    }

    public String get_priority() {
        return sharedPreferences.getString(PRIORITY, "");
    }

    public void set_priority(String priority) {
        this.editor.putString(this.PRIORITY, priority).commit();
    }


    public String get_inter_interval() {
        return sharedPreferences.getString(INTER_INTERVAL, "");
    }

    public void set_inter_interval(String interinter) {
        this.editor.putString(this.INTER_INTERVAL, interinter).commit();
    }

    public String get_second_ads() {
        return sharedPreferences.getString(SECOND_ADS, "");
    }

    public void set_second_ads(String secondads) {
        this.editor.putString(this.SECOND_ADS, secondads).commit();
    }

    public String get_open_ad_on_off() {
        return sharedPreferences.getString(ADMOB_OPENAD_FLAG, "");
    }

    public String get_custom_ads_on_off() {
        return sharedPreferences.getString(CUSTOM_ADS_ON_OFF, "");
    }

    public String get_videolink() {
        return sharedPreferences.getString(videolink, "");
    }

    public void set_videolink(String native_advance) {
        this.editor.putString(this.videolink, native_advance).commit();
    }

    public String get_videoname() {
        return sharedPreferences.getString(videoname, "");
    }

    public void set_videoname(String native_advance) {
        this.editor.putString(this.videoname, native_advance).commit();
    }

    public String get_back_inter() {
        return sharedPreferences.getString(BACK_PRESS_INTER_AD, "");
    }

    public void set_back_inter(String native_advance) {
        this.editor.putString(this.BACK_PRESS_INTER_AD, native_advance).commit();
    }


    public void set_custom_ads_on_off(String custom_ads_on_off) {
        this.editor.putString(this.CUSTOM_ADS_ON_OFF, custom_ads_on_off).commit();
    }

    public void set_open_ad_on_off(String flag) {
        this.editor.putString(this.ADMOB_OPENAD_FLAG, flag).commit();
    }

    public String get_new_link() {
        return sharedPreferences.getString(NEW_APP_LINK, "");
    }

    public String get_download_and_update_text() {
        return sharedPreferences.getString(DOWNLOAD_AND_UPDATE_TEXT, "");
    }

    public void set_download_and_update_text(String new_link) {
        this.editor.putString(this.DOWNLOAD_AND_UPDATE_TEXT, new_link).commit();
    }

    public String get_stay_text1() {
        return sharedPreferences.getString(STAY_TEXT_1, "");
    }

    public void set_stay_text1(String new_link) {
        this.editor.putString(this.STAY_TEXT_1, new_link).commit();
    }

    public String get_stay_text2() {
        return sharedPreferences.getString(STAY_TEXT_2, "");
    }

    public void set_stay_text2(String new_link) {
        this.editor.putString(this.STAY_TEXT_2, new_link).commit();
    }

    public String get_back_button_visibility() {
        return sharedPreferences.getString(BACK_BUTTON_VISIBILITY, "");
    }

    public void set_back_button_visibility(String back_button) {
        this.editor.putString(this.BACK_BUTTON_VISIBILITY, back_button).commit();
    }

    public String get_new_app_name() {
        return sharedPreferences.getString(NEW_APP_NAME, "");
    }

    public void set_new_app_name(String back_button) {
        this.editor.putString(this.NEW_APP_NAME, back_button).commit();
    }

    public String get_new_app_version() {
        return sharedPreferences.getString(NEW_APP_VERSION_CHECK, "");
    }

    public void set_new_app_version(String new_version) {
        this.editor.putString(this.NEW_APP_VERSION_CHECK, new_version).commit();
    }


    public void set_new_link(String new_link) {
        this.editor.putString(this.NEW_APP_LINK, new_link).commit();
    }

    public String get_new_app_icon() {
        return sharedPreferences.getString(NEW_APP_ICON, "");
    }

    public void set_new_app_icon(String new_app_icon) {
        this.editor.putString(this.NEW_APP_ICON, new_app_icon).commit();
    }

    public boolean get_alternate() {
        return sharedPreferences.getBoolean(ALTERNATE, true);
    }

    public void set_alternate(boolean flag) {
        this.editor.putBoolean(this.ALTERNATE, flag).commit();
    }

    public boolean get_alternate1() {
        return sharedPreferences.getBoolean(ALTERNATE1, true);
    }

    public void set_alternate1(boolean flag) {
        this.editor.putBoolean(this.ALTERNATE1, flag).commit();
    }


    public boolean get_alternate3() {
        return sharedPreferences.getBoolean(ALTERNATE3, true);
    }

    public void set_alternate3(boolean flag) {
        this.editor.putBoolean(this.ALTERNATE3, flag).commit();
    }

    //Admob Id's
    public String get_admob_app_id() {
        return sharedPreferences.getString(ADMOB_APP_ID, "");
    }

    public void set_admob_app_id(String appid) {
        this.editor.putString(this.ADMOB_APP_ID, appid).commit();
    }


    public String get_admob_rewarded_id() {
        return sharedPreferences.getString(ADMOB_REWARDED_ID, "");
    }

    public String get_admob_interstitial_id() {
        return sharedPreferences.getString(ADMOB_INTERSTITIAL_ID, "");
    }

    public void set_admob_interstitial_id(String interstitial) {
        this.editor.putString(this.ADMOB_INTERSTITIAL_ID, interstitial).commit();
    }

    public String get_admob_native_advance_id() {
        return sharedPreferences.getString(ADMOB_NATIVE_ADVANCED_ID, "");
    }

    public String get_admob_native_advance_id_2() {
        return sharedPreferences.getString(ADMOB_NATIVE_ADVANCED_ID_2, "");
    }

    public String get_admob_native_advance_id_3() {
        return sharedPreferences.getString(ADMOB_NATIVE_ADVANCED_ID_3, "");
    }

    public void set_admob_native_advance_id(String native_advance) {
        this.editor.putString(this.ADMOB_NATIVE_ADVANCED_ID, native_advance).commit();
    }

    public void set_admob_native_advance_id_2(String native_advance) {
        this.editor.putString(this.ADMOB_NATIVE_ADVANCED_ID_2, native_advance).commit();
    }

    public void set_admob_native_advance_id_3(String native_advance) {
        this.editor.putString(this.ADMOB_NATIVE_ADVANCED_ID_3, native_advance).commit();
    }

    public String get_admob_banner_id() {
        return sharedPreferences.getString(ADMOB_BANNER_ID, "");
    }

    public String get_admob_banner_id_2() {
        return sharedPreferences.getString(ADMOB_BANNER_ID_2, "");
    }

    public String get_admob_banner_id_3() {
        return sharedPreferences.getString(ADMOB_BANNER_ID_3, "");
    }

    public void set_admob_banner_id(String banner) {
        this.editor.putString(this.ADMOB_BANNER_ID, banner).commit();
    }

    public void set_admob_banner_id_2(String banner) {
        this.editor.putString(this.ADMOB_BANNER_ID_2, banner).commit();
    }

    public void set_admob_banner_id_3(String banner) {
        this.editor.putString(this.ADMOB_BANNER_ID_3, banner).commit();
    }

    public String get_admob_open_ad_id() {
        return sharedPreferences.getString(ADMOB_OPEN_AD_ID, "");
    }

    public void set_admob_open_ad_id(String open_ad) {
        this.editor.putString(this.ADMOB_OPEN_AD_ID, open_ad).commit();
    }

    //Facebook Id's

    public String get_facebook_app_id() {
        return sharedPreferences.getString(FACEBOOK_APP_ID, "");
    }

    public void set_facebook_app_id(String appid) {
        this.editor.putString(this.FACEBOOK_APP_ID, appid).commit();
    }

    public String get_facebook_interstitial_id() {
        return sharedPreferences.getString(FACEBOOK_INTERSTITIAL_ID, "");
    }

    public void set_facebook_interstitial_id(String interstitialId) {
        this.editor.putString(this.FACEBOOK_INTERSTITIAL_ID, interstitialId).commit();
    }

    public String get_facebook_native_id() {
        return sharedPreferences.getString(FACEBOOK_NATIVE_ID, "");
    }

    public void set_facebook_native_id(String native_id) {
        this.editor.putString(this.FACEBOOK_NATIVE_ID, native_id).commit();
    }

    public String get_facebook_banner_id() {
        return sharedPreferences.getString(FACEBOOK_BANNER_ID, "");
    }

    public void set_facebook_banner_id(String banner_id) {
        this.editor.putString(this.FACEBOOK_BANNER_ID, banner_id).commit();
    }

    public boolean getKey() {
        return sharedPreferences.getBoolean("key", false);
    }

    public void set_key(boolean key) {
        this.editor.putBoolean("key", key).commit();
    }

}
