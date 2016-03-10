package com.example.shuweizhao.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by shuweizhao on 3/7/16.
 */
public class ImageAdapter extends BaseAdapter {

    private ArrayList<String> imageInfo;
    private Context context;
    private FrameLayout layout;
    private GridView gv;
    public ImageAdapter(ArrayList<String> imageInfo, Context context, GridView gv) {
        this.imageInfo = imageInfo;
        this.context = context;
        this.gv = gv;
    }
    @Override
    public int getCount() {
        return imageInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return imageInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        FrameLayout layout = (FrameLayout)layoutInflater.inflate(R.layout.grid_image, null);
        ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                gv.getHeight()/4);
        layout.setLayoutParams(param);
        ImageView imageView = (ImageView)layout.findViewById(R.id.grid_image);
        String movie = imageInfo.get(position);
        String[] movieDetail = movie.split("\n");

        imageView.setImageResource(R.mipmap.kf3);
        return layout;
    }
}
