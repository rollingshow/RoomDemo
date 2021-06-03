package com.rollingshow.nasa_iotd;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@SuppressWarnings("NullableProblems")
@Entity(tableName = "pictures")
public class Picture {
    @PrimaryKey
    @NonNull
    public final String date;
    @NonNull
    public final String hdurl;
    @NonNull
    public final String explanation;
    @NonNull
    public final String title;

    public Picture(String date, @NonNull String hdurl, @NonNull String explanation, @NonNull String title) {
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.title = title;
    }


}
