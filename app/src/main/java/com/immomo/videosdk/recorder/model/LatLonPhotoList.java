package com.immomo.videosdk.recorder.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by chenxin on 2018/10/18.
 */

public class LatLonPhotoList implements Parcelable {

    @Expose
    public String site;

    @Expose
    public String sid;

    @Expose
    public List<String> photos;

    @Expose
    public List<LatLonPhoto> photoList;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.site);
        dest.writeString(this.sid);
        dest.writeStringList(this.photos);
        dest.writeTypedList(this.photoList);
    }

    public LatLonPhotoList() {
    }

    protected LatLonPhotoList(Parcel in) {
        this.site = in.readString();
        this.sid = in.readString();
        this.photos = in.createStringArrayList();
        this.photoList = in.createTypedArrayList(LatLonPhoto.CREATOR);
    }

    public static final Creator<LatLonPhotoList> CREATOR = new Creator<LatLonPhotoList>() {
        @Override
        public LatLonPhotoList createFromParcel(Parcel source) {
            return new LatLonPhotoList(source);
        }

        @Override
        public LatLonPhotoList[] newArray(int size) {
            return new LatLonPhotoList[size];
        }
    };
}
