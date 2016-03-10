package com.example.shuweizhao.movies;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new FragmentDisplay())
                    .commit();
        }
    }
}
