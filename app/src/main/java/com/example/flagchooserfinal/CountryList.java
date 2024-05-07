package com.example.flagchooserfinal;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class CountryList implements Parcelable {

    String countryName;
    String countryDetails;
    int image;

    public CountryList(String countryName, String countryDetails, int image) {
        this.countryName = countryName;
        this.countryDetails = countryDetails;
        this.image = image;
    }

    protected CountryList(Parcel in) {
        countryName = in.readString();
        countryDetails = in.readString();
        image = in.readInt();
    }

    public static final Creator<CountryList> CREATOR = new Creator<CountryList>() {
        @Override
        public CountryList createFromParcel(Parcel in) {
            return new CountryList(in);
        }

        @Override
        public CountryList[] newArray(int size) {
            return new CountryList[size];
        }
    };

    public String getCountryName() {
        return countryName;
    }

    public String getCountryDetails() {
        return countryDetails;
    }

    public int getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "CountryList{" +
                "countryName='" + countryName + '\'' +
                ", countryDetails='" + countryDetails + '\'' +
                ", image=" + image +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(countryName);
        dest.writeString(countryDetails);
        dest.writeInt(image);
    }
}