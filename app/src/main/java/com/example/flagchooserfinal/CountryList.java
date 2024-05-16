package com.example.flagchooserfinal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

/*
This class is a data model class designed to store all of the data for each CardView.
CountryName, country details, and the id for the country flag.

Country Details haven't 100% been implemented. Currently, it just duplicates the country name.

IMPORTANT: This class implements Parcelable to allow Android to intent this type of data to another Activity.
 */
public class CountryList implements Parcelable {

    String countryName;
    String countryDetails;
    int image;
    boolean checked;

    public CountryList(String countryName, String countryDetails, int image) {
        this.countryName = countryName;
        this.countryDetails = countryDetails;
        this.image = image;
        this.checked = false;
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
                ", checked=" + checked +
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

    public boolean isChecked(){

        return false;
    }

    public void setChecked(boolean flag){
        if (flag == true){
            checked = true;
        }
        else
            checked = false;
    }
}