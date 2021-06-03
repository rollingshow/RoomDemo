package com.rollingshow.nasa_iotd.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rollingshow.nasa_iotd.Picture;

@Dao
public
interface Products {
    @Query("SELECT * FROM pictures ORDER BY date DESC")
    Picture[] selectAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNew(Picture... pictures);
}
