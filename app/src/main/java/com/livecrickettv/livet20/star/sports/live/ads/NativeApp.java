package com.livecrickettv.livet20.star.sports.live.ads;

import android.os.Parcel;
import android.os.Parcelable;

public class NativeApp implements Parcelable {
    private int id;
    private String image;
    private String pack;


    public NativeApp(){}

    protected NativeApp(Parcel in) {
        id = in.readInt();
        image = in.readString();
        pack = in.readString();
    }

    public static final Creator<NativeApp> CREATOR = new Creator<NativeApp>() {
        @Override
        public NativeApp createFromParcel(Parcel in) {
            return new NativeApp(in);
        }

        @Override
        public NativeApp[] newArray(int size) {
            return new NativeApp[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public NativeApp(int id, String image, String pack) {
        this.id = id;
        this.image = image;
        this.pack = pack;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(image);
        dest.writeString(pack);
    }
}
