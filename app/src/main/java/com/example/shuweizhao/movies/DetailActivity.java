package com.example.shuweizhao.movies;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by shuweizhao on 3/7/16.
 */
public class DetailActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().add(R.id.container,
//                    new DetailFragment())
//                    .commit();
        //}
    }
}
