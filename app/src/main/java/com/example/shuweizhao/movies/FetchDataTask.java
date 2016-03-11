package com.example.shuweizhao.movies;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by shuweizhao on 3/9/16.
 */
public class FetchDataTask extends AsyncTask<String, Void, String[]> {
    public static final String LOG_TAG = FetchDataTask.class.getSimpleName();

    private final Context mContext;
    public static ImageAdapter adapter;
    private GridView gv;

    public FetchDataTask (Context context, ImageAdapter adapter, GridView gv) {
        mContext = context;
        this.adapter = adapter;
        this.gv = gv;
    }
    private String[] getMovieDataFromJson(String movieDataJson)
    throws JSONException{
        final String POSTERPATH = "poster_path";
        final String ADULT = "adult";
        final String OVERVIEW = "overview";
        final String RELEASEDATE = "release_date";
        final String VOTEAVERAGE = "vote_average";
        final String TITLE = "title";
        final String RESULT = "results";

        JSONObject movieJson = new JSONObject(movieDataJson);
        JSONArray movieArray = movieJson.getJSONArray(RESULT);

        String[] resStr = new String[movieArray.length()];

        for (int i = 0; i < movieArray.length(); i++) {
            JSONObject movie = movieArray.getJSONObject(i);
            String title = movie.getString(TITLE) + "\n";
            String posterpath = movie.getString(POSTERPATH) + "\n";
            String overview = movie.getString(OVERVIEW) + "\n";
            String vote_average = movie.getString(VOTEAVERAGE) + "\n";
            String release_date = movie.getString(RELEASEDATE) + "\n";

            resStr[i] = title + posterpath + overview + vote_average + release_date;
        }
        return resStr;
    }
    @Override
    protected String[] doInBackground(String... params) {

        if (params[0] == null) {
            return null;
        }
        HttpURLConnection urlConnection = null;
        BufferedReader br = null;

        String moviesJsonStr = null;
        String apiKey = "7b97a06bab41d02795bac08b550c2fd2";

        try {
            final String APIKEY = "api_key";
            final String queryBaseUrl = "http://api.themoviedb.org/3/movie/" + params[0];
            Uri builduri = Uri.parse(queryBaseUrl).buildUpon().
                    appendQueryParameter(APIKEY, apiKey)
                    .build();
            System.out.println(builduri.toString());
            URL url = new URL(builduri.toString());
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream == null) {
                return null;
            }

            br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }

            if (sb.length() == 0) {
                return null;
            }
            moviesJsonStr = sb.toString();
            System.out.print(moviesJsonStr);
        }
        catch (IOException e) {
            Log.e(LOG_TAG,"Error", e);
            return null;
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (br != null) {
                try {
                    br.close();
                }
                catch (IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        try {
            return getMovieDataFromJson(moviesJsonStr);
        }
        catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String[] strings) {
        super.onPostExecute(strings);
        ArrayList<String> movieInfo = new ArrayList<>(Arrays.asList(strings));
        adapter = new ImageAdapter(movieInfo, mContext);
        gv.setAdapter(adapter);
    }
}
