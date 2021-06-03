package com.rollingshow.nasa_iotd;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ImageAdapter extends BaseAdapter {
    final Context ctx;
    final Picture[] pictures;

    public ImageAdapter(Context ctx, Picture[] hits) {
        this.ctx = ctx;
        this.pictures = hits;
    }

    @Override
    public int getCount() {
        return pictures.length;
    }

    @Override
    public Picture getItem(int position) {
        return pictures[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // получаем данные из коллекции
        Picture p = pictures[position];

        // создаём разметку (контейнер)
        convertView = LayoutInflater.from(ctx).inflate(R.layout.product, parent, false);
        // получаем ссылки на элементы интерфейса
        ImageView image = convertView.findViewById(R.id.h_image);
        TextView title = convertView.findViewById(R.id.h_title);
        TextView date = convertView.findViewById(R.id.h_date);
        TextView explan = convertView.findViewById(R.id.h_explanation);

        Picasso.get().load(p.hdurl).into(image);

        title.setText(p.title);
        date.setText(p.date);
        explan.setText(p.explanation);

        return convertView;
    }
}
