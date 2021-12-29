package com.livecrickettv.livet20.star.sports.live.ads;

import android.os.Parcel;
import android.os.Parcelable;

public class MoreApp implements Parcelable {
    private int id;
    private String name;
    private String image;
    private String pack;


    public MoreApp(){}

    protected MoreApp(Parcel in) {
        id = in.readInt();
        name = in.readString();
        image = in.readString();
        pack = in.readString();
    }

    public static final Creator<MoreApp> CREATOR = new Creator<MoreApp>() {
        @Override
        public MoreApp createFromParcel(Parcel in) {
            return new MoreApp(in);
        }

        @Override
        public MoreApp[] newArray(int size) {
            return new MoreApp[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public MoreApp(int id, String name, String image, String pack) {
        this.id = id;
        this.name = name;
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
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(pack);
    }
}
