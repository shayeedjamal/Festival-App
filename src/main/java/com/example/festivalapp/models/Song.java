package com.example.festivalapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;
import java.util.List;

public class Song implements Parcelable {



    private File Song;
    private String Name;

    public Song(File song, String name) {
        Song = song;
        Name = name;
    }

    public File getSong() {
        return Song;
    }

    public void setSong(File song) {
        Song = song;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    protected Song(Parcel in) {
        Name = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);

    }
}
