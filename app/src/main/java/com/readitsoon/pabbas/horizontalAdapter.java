package com.readitsoon.pabbas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class horizontalAdapter  extends RecyclerView.Adapter<horizontalAdapter.Viewholder2>{
    private static List<ModelClass> modelClassList;
    private static List<Boolean> isBookmarked;
    private Context context;
    public horizontalAdapter(Context context, List<ModelClass> modelClassList, List<Boolean> isBookmarked) {
        this.modelClassList = modelClassList;
        this.isBookmarked = isBookmarked;
        this.context = context;
    }
    @NonNull
    @Override
    public Viewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_horizontal, parent, false);

        return new Viewholder2(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder2 holder, int position) {
        final ModelClass item = modelClassList.get(position);
        String title = item.getTitle();
        String date = item.getDateAndTime();
        // String urlToImage = item.getUrlToImage();
        String content = item.getContent();
        final String url = item.getUrl();
        //holder.setData(title,tag,dateTime);
        if (title != null)
            holder.titleText.setText(title);
        if (date != null)
            holder.dateTimeText.setText(date);
        ImageButton bookmark = (ImageButton) holder.imageButton;

        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBookmarked.get(position) == false) {
                    v.setBackgroundResource(R.drawable.ic_baseline_bookmark_24);
                    isBookmarked.set(position, true);
                    Toast.makeText(context, "Added to My Stories", Toast.LENGTH_SHORT).show();

                } else {
                    isBookmarked.set(position, false);
                    v.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24);
                    Toast.makeText(context, "Removed from My Stories", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //if(urlToImage!=null)
        //  Picasso.get().load(urlToImage).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return  modelClassList.size();
    }

    public class Viewholder2 extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleText;
        TextView tagText;
        TextView dateTimeText;
        ImageButton imageButton;
        public Viewholder2(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.imageViewHorizontal);
            titleText=(TextView) itemView.findViewById(R.id.titleTextHorizontal);
            tagText=(TextView) itemView.findViewById(R.id.tagTextHorizontal);
            dateTimeText=(TextView) itemView.findViewById(R.id.dateTextHorizontal);
            imageButton=(ImageButton)itemView.findViewById(R.id.imageButtonHorizontal);
        }
    }
}
