package com.readitsoon.pabbas.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.readitsoon.pabbas.EmptyRecyclerView;
import com.readitsoon.pabbas.MainActivity;
import com.readitsoon.pabbas.News;
import com.readitsoon.pabbas.NewsLoader;
import com.readitsoon.pabbas.NewsLoaderMyStory;
import com.readitsoon.pabbas.NewsPreference;
import com.readitsoon.pabbas.R;

import com.readitsoon.pabbas.adapter.MyStoryNewsAdapter;
import com.readitsoon.pabbas.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class MyStoryFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<News>>{
    private static final String LOG_TAG = MyStoryFragment.class.getName();
    /** Constant value for the news loader ID. */
    private static final int NEWS_LOADER_ID = 1;
    LinearLayout linearLayout;
    /** Adapter for the list of news */
    public static MyStoryNewsAdapter myStoryNewsAdapter;

    /** TextView that is displayed when the recycler view is empty */
    private TextView mEmptyStateTextView;

    /** Loading indicator that is displayed before the first load is completed */
    private View mLoadingIndicator;

    /** The {@link SwipeRefreshLayout} that detects swipe gestures and
     * triggers callbacks in the app.
     */

    private SwipeRefreshLayout mSwipeRefreshLayout;
    public static Context context;
    public MyStoryFragment(Context context) {
        this.context=context;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_stories_fragment, container, false);

        // Find a reference to the {@link RecyclerView} in the layout
        // Replaced RecyclerView with EmptyRecyclerView
        EmptyRecyclerView mRecyclerView = rootView.findViewById(R.id.recycler_view);
        linearLayout=rootView.findViewById(R.id.lyt_no_storiesBookmarked);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);
        //setRetainInstance(true);
        // Set the layoutManager on the {@link RecyclerView}
        mRecyclerView.setLayoutManager(layoutManager);

        // Find the SwipeRefreshLayout
        mSwipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh);
        // Set up OnRefreshListener that is invoked when the user performs a swipe-to-refresh gesture.
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
                // restart the loader
                initiateRefresh();
                Toast.makeText(getActivity(), getString(R.string.updated_just_now),
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Find the loading indicator from the layout
        mLoadingIndicator = rootView.findViewById(R.id.loading_indicator);

        // Find the empty view from the layout and set it on the new recycler view
        mEmptyStateTextView = rootView.findViewById(R.id.empty_view);
        mRecyclerView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of news as input
        myStoryNewsAdapter = new MyStoryNewsAdapter(getActivity(), new ArrayList<News>());

        // Set the adapter on the {@link recyclerView}
        mRecyclerView.setAdapter(myStoryNewsAdapter);

        // Check for network connectivity and initialize the loader
        initializeLoader(isConnected());
        List<News> bookmarked= MainActivity.bookmarked;
        onLoadFinished(onCreateLoader(0,Bundle.EMPTY),bookmarked);

        return rootView;
    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        Uri.Builder uriBuilder;
        uriBuilder = NewsPreference.getPreferredUri(getContext());

        Log.e(LOG_TAG,uriBuilder.toString());

        // Create a new loader for the given URL
        return new NewsLoaderMyStory(getActivity(), uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> newsData) {
        // Hide loading indicator because the data has been loaded
        mLoadingIndicator.setVisibility(View.GONE);

       //  Set empty state text to display "No news found."
        //mEmptyStateTextView.setText(R.string.no_news);

        // Clear the adapter of previous news data
        myStoryNewsAdapter.clearAll();

        // If there is a valid list of {@link News}, then add them to the adapter's
        // data set. This will trigger the recyclerView to update.
        if (newsData != null && !newsData.isEmpty()) {
           linearLayout.setVisibility(View.GONE);
            myStoryNewsAdapter.addAll(newsData);
        }
        // Hide the swipe icon animation when the loader is done refreshing the data
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        // Loader reset, so we can clear out our existing data.
        myStoryNewsAdapter.clearAll();
    }

    /**
     * When the user returns to the previous screen by pressing the up button in the SettingsActivity,
     * restart the Loader to reflect the current value of the preference.
     */
    @Override
    public void onResume() {
        super.onResume();
        myStoryNewsAdapter.clearAll();
        restartLoader(isConnected());
    }

    /**
     *  Check for network connectivity.
     */
    private boolean isConnected() {
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * If there is internet connectivity, initialize the loader as
     * usual. Otherwise, hide loading indicator and set empty state TextView to display
     * "No internet connection."
     *
     * @param isConnected internet connection is available or not
     */
    private void initializeLoader(boolean isConnected) {
        if (isConnected) {
            myStoryNewsAdapter.clearAll();
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();
            // Initialize the loader with the NEWS_LOADER_ID
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            mLoadingIndicator.setVisibility(View.GONE);
            // Update empty state with no connection error message and image
            mEmptyStateTextView.setText(R.string.no_internet_connection);
            mEmptyStateTextView.setCompoundDrawablesWithIntrinsicBounds(Constants.DEFAULT_NUMBER,
                    R.drawable.ic_network_check,Constants.DEFAULT_NUMBER, Constants.DEFAULT_NUMBER);
        }
    }

    /**
     * Restart the loader if there is internet connectivity.
     * @param isConnected internet connection is available or not
     */
    private void restartLoader(boolean isConnected) {
        if (isConnected) {
            myStoryNewsAdapter.clearAll();
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();
            // Restart the loader with the NEWS_LOADER_ID
            loaderManager.restartLoader(NEWS_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            mLoadingIndicator.setVisibility(View.GONE);
            // Update empty state with no connection error message and image
            mEmptyStateTextView.setText(R.string.no_internet_connection);
            mEmptyStateTextView.setCompoundDrawablesWithIntrinsicBounds(Constants.DEFAULT_NUMBER,
                    R.drawable.ic_network_check,Constants.DEFAULT_NUMBER,Constants.DEFAULT_NUMBER);

            // Hide SwipeRefreshLayout
            mSwipeRefreshLayout.setVisibility(View.GONE);
        }
    }

    /**
     * When the user performs a swipe-to-refresh gesture, restart the loader.
     */
    public void initiateRefresh() {
        myStoryNewsAdapter.clearAll();
        restartLoader(isConnected());
    }
}


