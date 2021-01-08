package com.readitsoon.pabbas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class myStoriesAdapter extends RecyclerView.Adapter<myStoriesAdapter.Viewholder> {
    private static List<ModelClass> modelClassList;
    private static List<Boolean> isBookmarked;
    private Context context;
    public myStoriesAdapter(Context context, List<ModelClass> modelClassList, List<Boolean> isBookmarked) {
        this.modelClassList = modelClassList;
        this.isBookmarked=isBookmarked;
        this.context=context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new Viewholder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        final ModelClass item = modelClassList.get(position);
            String title = item.getTitle();
            String date = item.getDateAndTime();
            // String urlToImage = item.getUrlToImage();
            String content=item.getContent();
            final String url = item.getUrl();
            //holder.setData(title,tag,dateTime);
            if(title!=null)
                holder.titleText.setText(title);
            if(date!=null)
                holder.dateTimeText.setText(date);
            ImageButton bookmark=(ImageButton)holder.imageButton;
            bookmark.setBackgroundResource(R.drawable.ic_baseline_bookmark_24);
            bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        isBookmarked.set(position,false);
                        v.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24);
                        Toast.makeText(context, "Removed from My Stories", Toast.LENGTH_SHORT).show();
                }
            });
        //if(urlToImage!=null)
        //  Picasso.get().load(urlToImage).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView titleText;
        TextView tagText;
        TextView dateTimeText;
        ImageButton imageButton;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.imageView);
            titleText=(TextView) itemView.findViewById(R.id.titleText);
            tagText=(TextView) itemView.findViewById(R.id.tagText);
            dateTimeText=(TextView) itemView.findViewById(R.id.dateText);
            imageButton=(ImageButton)itemView.findViewById(R.id.imageButton);
        }
    }

}
