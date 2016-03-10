package com.example.shuweizhao.movies;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by shuweizhao on 3/7/16.
 */
public class FragmentDisplay extends android.support.v4.app.Fragment {

    public FragmentDisplay() {

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FetchDataTask fetchDataTask = new FetchDataTask();
        fetchDataTask.execute("top_rated");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = getView(inflater, container);
        return rootView;
    }

    private View getView(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        GridView gv = (GridView)rootView.findViewById(R.id.movieCollection);
        gv.setAdapter(new ImageAdapter(new ArrayList<Bitmap>(), getActivity(), gv));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), DetailActivity.class);
                startActivity(i);
            }
        });
        return rootView;
    }
}
