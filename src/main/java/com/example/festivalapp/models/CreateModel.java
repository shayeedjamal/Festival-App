package com.example.festivalapp.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "createlineup_table")
public class CreateModel implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;

@ColumnInfo(name="Artist")
    private String artistOne;
    private String artistTwo;
    private String artistThree;
    private String artistFour;

    public CreateModel( String artistOne, String artistTwo, String artistThree, String artistFour) {
        this.artistOne = artistOne;
        this.artistTwo = artistTwo;
        this.artistThree = artistThree;
        this.artistFour = artistFour;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArtistOne() {
        return artistOne;
    }

    public void setArtistOne(String artistOne) {
        this.artistOne = artistOne;
    }

    public String getArtistTwo() {
        return artistTwo;
    }

    public void setArtistTwo(String artistTwo) {
        this.artistTwo = artistTwo;
    }

    public String getArtistThree() {
        return artistThree;
    }

    public void setArtistThree(String artistThree) {
        this.artistThree = artistThree;
    }

    public String getArtistFour() {
        return artistFour;
    }

    public void setArtistFour(String artistFour) {
        this.artistFour = artistFour;
    }

    protected CreateModel(Parcel in) {
        id = in.readLong();
        artistOne = in.readString();
        artistTwo = in.readString();
        artistThree = in.readString();
        artistFour = in.readString();
    }

    public static final Creator<CreateModel> CREATOR = new Creator<CreateModel>() {
        @Override
        public CreateModel createFromParcel(Parcel source) {
            return new CreateModel(source);

        }

        @Override
        public CreateModel[] newArray(int size) {
            return new CreateModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(artistOne);
        dest.writeString(artistTwo);
        dest.writeString(artistThree);
        dest.writeString(artistFour);
    }
}
