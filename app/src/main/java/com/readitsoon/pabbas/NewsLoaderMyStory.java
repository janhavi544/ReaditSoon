package com.readitsoon.pabbas;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import com.readitsoon.pabbas.adapter.NewsAdapter;
import com.readitsoon.pabbas.util.QueryUtils;

import java.util.List;

public class NewsLoaderMyStory extends AsyncTaskLoader<List<News>> {
    /** Tag for log messages */
    private static final String LOG_TAG = NewsLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link NewsLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public NewsLoaderMyStory(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        // Trigger the loadInBackground() method to execute.
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of news.
        List<News> newsData = MainActivity.bookmarked;
        return newsData;
    }
}
