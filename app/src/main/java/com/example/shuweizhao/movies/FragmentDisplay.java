package com.example.shuweizhao.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    public static String choice = "popular";
    public FragmentDisplay() {

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String temp = "";
        if (id == R.id.popular) {
            temp = "popular";
        }
        if (id == R.id.top_rated) {
            temp = "top_rated";
        }
        if (!choice.equals(temp)) {
            choice = temp;
            FetchDataTask fetchDataTask = new FetchDataTask(getActivity(), imageAdapter, gv);
            fetchDataTask.execute(temp);
        }
        return super.onOptionsItemSelected(item);
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
        fetchDataTask.execute(choice);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), DetailActivity.class);
                //illegal
                String movieInfo = FetchDataTask.adapter.getItem(position);
                i.putExtra(Intent.EXTRA_TEXT, movieInfo);
                startActivity(i);
            }
        });
        return rootView;
    }
}
