package com.readitsoon.pabbas.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.readitsoon.pabbas.MainActivity;
import com.readitsoon.pabbas.News;
import com.readitsoon.pabbas.ObjectSerializer;
import com.readitsoon.pabbas.R;
import com.readitsoon.pabbas.Webview;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    private  static  Context mContext;
    private List<News> mNewsList;
    /**
     * Constructs a new {@link NewsAdapter}
     * @param context of the app
     * @param newsList is the list of news, which is the data source of the adapter
     */
    public NewsAdapter(Context context, List<News> newsList) {
        mContext = context;
        mNewsList = newsList;

    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView sectionTextView;
        //private TextView authorTextView;
        private TextView dateTextView;
        private ImageView thumbnailImageView;
        private ImageView shareImageView;
        private TextView trailTextView;
        private CardView cardView;
        private ImageView bookmarkImageView;
        ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_card);
            sectionTextView = itemView.findViewById(R.id.section_card);
            bookmarkImageView=itemView.findViewById(R.id.bookmark_image_card);
            dateTextView = itemView.findViewById(R.id.date_card);
            thumbnailImageView = itemView.findViewById(R.id.thumbnail_image_card);
            shareImageView = itemView.findViewById(R.id.share_image_card);
            trailTextView = itemView.findViewById(R.id.trail_text_card);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // Find the current news that was clicked on
        final News currentNews = mNewsList.get(position);

        holder.titleTextView.setText(currentNews.getTitle());
        holder.sectionTextView.setText(currentNews.getSection());

        // Get time difference between the current date and web publication date and
        // set the time difference on the textView
        //holder.dateTextView.setText(getTimeDifference(formatDate(currentNews.getDate())));
        holder.dateTextView.setText(formatDate(currentNews.getDate()));
        // Get string of the trailTextHTML and convert Html text to plain text
        // and set the plain text on the textView
        String trailTextHTML = currentNews.getTrailTextHtml();
        holder.trailTextView.setText(Html.fromHtml(Html.fromHtml(trailTextHTML).toString()));

            if(MainActivity.bookmarkedUrl!=null&&MainActivity.bookmarkedUrl.contains(currentNews.getUrl()))
            {
                if(MainActivity.bookmarked!=null&&MainActivity.bookmarked.contains(currentNews)==false)
                {
                    MainActivity.bookmarked.add(currentNews);
                }
                holder.bookmarkImageView.setBackgroundResource(R.drawable.ic_baseline_bookmark_24);
            }
            else
            holder.bookmarkImageView.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24);

            holder.bookmarkImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(MainActivity.bookmarkedUrl!=null&&MainActivity.bookmarkedUrl.contains(currentNews.getUrl()))
                    {
                        //it already contains this item so unbookmark it and remove from list
                        holder.bookmarkImageView.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24);
                        MainActivity.bookmarked.remove(currentNews);
                        MainActivity.bookmarkedUrl.remove(currentNews.getUrl());
                        SharedPreferences sharedPreferences=mContext.getSharedPreferences("com.readitsoon.pabbas",Context.MODE_PRIVATE);
                        try{
                            sharedPreferences.edit().putString("bookmarked", ObjectSerializer.serialize(MainActivity.bookmarkedUrl)).apply();
                            Log.i("bookmarked",ObjectSerializer.serialize(MainActivity.bookmarkedUrl));
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                        Toast.makeText(mContext, "Removed from My Stories successfully!!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(mContext, MainActivity.class);
                        intent.putExtra("finish","finish");
                        mContext.startActivity(intent);
                    }
                    else
                    {
                        //else bookmark it and add in list
                        holder.bookmarkImageView.setBackgroundResource(R.drawable.ic_baseline_bookmark_24);
                        MainActivity.bookmarked.add(currentNews);
                        MainActivity.bookmarkedUrl.add(currentNews.getUrl());
                        SharedPreferences sharedPreferences=mContext.getSharedPreferences("com.readitsoon.pabbas",Context.MODE_PRIVATE);
                        try{
                            sharedPreferences.edit().putString("bookmarked", ObjectSerializer.serialize(MainActivity.bookmarkedUrl)).apply();
                            Log.i("bookmarked",ObjectSerializer.serialize(MainActivity.bookmarkedUrl));
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                        Toast.makeText(mContext, "Added to My Stories successfully!!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(mContext, MainActivity.class);
                        intent.putExtra("finish","finish");
                        mContext.startActivity(intent);
                    }
                }
            });

        // Set an OnClickListener to open a website with more information about the selected article
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent webIntent=new Intent(mContext, Webview.class);
                webIntent.putExtra("url",currentNews.getUrl());
                mContext.startActivity(webIntent);
            }
        });

        if (currentNews.getThumbnail() == null) {
            holder.thumbnailImageView.setVisibility(View.GONE);
        } else {
            holder.thumbnailImageView.setVisibility(View.VISIBLE);
            // Load thumbnail with glide
            Glide.with(mContext.getApplicationContext())
                    .load(currentNews.getThumbnail())
                    .into(holder.thumbnailImageView);
        }
        // Set an OnClickListener to share the data with friends via email or  social networking
        holder.shareImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareData(currentNews);
            }
        });
    }


    /**
     * Share the article with friends in social network
     * @param news {@link News} object
     */
    private void shareData(News news) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                news.getTitle() + " : " + news.getUrl());
        mContext.startActivity(Intent.createChooser(sharingIntent,
                mContext.getString(R.string.share_article)));
    }

    /**
     *  Clear all data (a list of {@link News} objects)
     */
    public void clearAll() {
        mNewsList.clear();
        notifyDataSetChanged();
    }

    /**
     * Add  a list of {@link News}
     * @param newsList is the list of news, which is the data source of the adapter
     */
    public void addAll(List<News> newsList) {
        mNewsList.clear();
        mNewsList.addAll(newsList);
        notifyDataSetChanged();
    }

    /**
     * Convert date and time in UTC (webPublicationDate) into a more readable representation
     * in Local time
     *
     * @param dateStringUTC is the web publication date of the article (i.e. 2014-02-04T08:00:00Z)
     * @return the formatted date string in Local time(i.e "Jan 1, 2000  2:15 AM")
     * from a date and time in UTC
     */
    private String formatDate(String dateStringUTC) {
        // Parse the dateString into a Date object
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss'Z'");
        Date dateObject = null;
        try {
            dateObject = simpleDateFormat.parse(dateStringUTC);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Initialize a SimpleDateFormat instance and configure it to provide a more readable
        // representation according to the given format, but still in UTC
        SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy  h:mm a", Locale.ENGLISH);
        String formattedDateUTC = df.format(dateObject);
        // Convert UTC into Local time
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = df.parse(formattedDateUTC);
            df.setTimeZone(TimeZone.getDefault());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df.format(date);
    }

    /**
     * Get the formatted web publication date string in milliseconds
     * @param formattedDate the formatted web publication date string
     * @return the formatted web publication date in milliseconds
     */
    private static long getDateInMillis(String formattedDate) {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("MMM d, yyyy  h:mm a");
        long dateInMillis;
        Date dateObject;
        try {
            dateObject = simpleDateFormat.parse(formattedDate);
            dateInMillis = dateObject.getTime();
            return dateInMillis;
        } catch (ParseException e) {
            Log.e("Problem parsing date", e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get the time difference between the current date and web publication date
     * @param formattedDate the formatted web publication date string
     * @return time difference (i.e "9 hours ago")
     */
    private CharSequence getTimeDifference(String formattedDate) {
        long currentTime = System.currentTimeMillis();
        long publicationTime = getDateInMillis(formattedDate);
        return DateUtils.getRelativeTimeSpanString(publicationTime, currentTime,
                DateUtils.SECOND_IN_MILLIS);
    }
}
