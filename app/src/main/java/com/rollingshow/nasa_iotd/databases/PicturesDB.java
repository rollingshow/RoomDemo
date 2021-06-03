package com.rollingshow.nasa_iotd.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rollingshow.nasa_iotd.Picture;

import com.rollingshow.nasa_iotd.interfaces.Products;

@Database(entities={Picture.class}, version=1)
public abstract class PicturesDB extends RoomDatabase {
    public abstract Products products();

    private static final String DB_NAME="room.db";
    private static PicturesDB INSTANCE=null;

    @SuppressWarnings("unused")
    static PicturesDB get(Context ctx) {
        if (INSTANCE==null) {
            INSTANCE=create(ctx);
        }
        return(INSTANCE);
    }
    public static PicturesDB create(Context ctxt) {
        PicturesDB database;
        database = Room.databaseBuilder(ctxt.getApplicationContext(), PicturesDB.class, DB_NAME)
                .allowMainThreadQueries()
                .build();

        return database;
    }
}
