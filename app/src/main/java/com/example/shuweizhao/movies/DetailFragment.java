package com.example.shuweizhao.movies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by shuweizhao on 3/7/16.
 */
public class DetailFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.detail_fragment, container, false);
        Intent intent = getActivity().getIntent();
        String[] params = null;
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            String movieInfo = intent.getStringExtra(Intent.EXTRA_TEXT);
            params = movieInfo.split("\n");
        }
        TextView overview = (TextView) rootView.findViewById(R.id.overview_text);
        SimpleDraweeView poster = (SimpleDraweeView) rootView.findViewById(R.id.detail_poster);
        TextView title = (TextView)rootView.findViewById(R.id.detail_title);
        TextView vote_count = (TextView) rootView.findViewById(R.id.vote_average);
        TextView release_date = (TextView) rootView.findViewById(R.id.release_date);
        title.setText("Title\n" + params[0]);
        Uri uri = Uri.parse("http://image.tmdb.org/t/p/w342" + params[1]);
        poster.setImageURI(uri);
        overview.setText(params[2]);
        vote_count.setText("Vote Average\n" + params[3]);
        release_date.setText("Release Date\n" + params[4]);
        return rootView;
    }
}
