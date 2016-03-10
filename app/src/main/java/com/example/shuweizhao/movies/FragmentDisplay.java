package com.example.shuweizhao.movies;

import android.content.Intent;
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
    private ImageAdapter imageAdapter;
    private GridView gv;

    public FragmentDisplay() {

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        gv = (GridView)rootView.findViewById(R.id.movieCollection);
        imageAdapter = new ImageAdapter(new ArrayList<String>(128), getActivity());
        gv.setAdapter(imageAdapter);
        FetchDataTask fetchDataTask = new FetchDataTask(getActivity(), imageAdapter, gv);
        fetchDataTask.execute("top_rated");
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), DetailActivity.class);
                String movieInfo = (String)imageAdapter.getItem(position);
                i.putExtra(Intent.EXTRA_TEXT, movieInfo);
                startActivity(i);
            }
        });
        return rootView;
    }
}
