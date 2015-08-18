package com.example.k00na_.weatherapp.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by k00na_ on 18.8.2015.
 */
public class CurrentWeather {

    private String mIcon;
    private long mTime;
    private double mTemperature;
    private double mHumidity;
    private double mPrecipChance;
    private String mSummary;
    private String mTimeZone;

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }

    public long getmTime() {
        return mTime;
    }

    public void setmTime(long mTime) {
        this.mTime = mTime;
    }

    public String getmIcon() {
        return mIcon;
    }

    public String getFromatedData(){

        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone(mTimeZone));
        Date dateTime = new Date(getmTime() * 1000);
        String timeString = formatter.format(dateTime);

        return timeString;

    }

    public void setmIcon(String mIcon) {
        this.mIcon = mIcon;
    }

    public double getmTemperature() {
        return mTemperature;
    }

    public void setmTemperature(double mTemperature) {
        this.mTemperature = mTemperature;
    }

    public double getmHumidity() {
        return mHumidity;
    }

    public void setmHumidity(double mHumidity) {
        this.mHumidity = mHumidity;
    }

    public double getmPrecipChance() {
        return mPrecipChance;
    }

    public void setmPrecipChance(double mPrecipChance) {
        this.mPrecipChance = mPrecipChance;
    }

    public String getmSummary() {
        return mSummary;
    }

    public void setmSummary(String mSummary) {
        this.mSummary = mSummary;
    }



}
