package com.readitsoon.pabbas.fragments;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.loader.content.Loader;

import com.readitsoon.pabbas.News;
import com.readitsoon.pabbas.NewsLoader;
import com.readitsoon.pabbas.NewsPreference;
import com.readitsoon.pabbas.R;

import java.util.List;

public class AllStoryFragment extends BaseFragment{
    public static final String LOG_TAG = AllStoryFragment.class.getName();
    public static String category;
    public AllStoryFragment(String category) {
            this.category=category;
    }
        @NonNull
        @Override
        public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        String url = NewsPreference.getPreferredUrl(getContext(),category);
        Log.e("news", url);

        // Create a new loader for the given URL
        return new NewsLoader(getActivity(),url);
    }
}
