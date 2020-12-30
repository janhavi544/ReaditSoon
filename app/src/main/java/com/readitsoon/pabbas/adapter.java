package com.readitsoon.pabbas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.Viewholder> {

    private List<ModelClass> modelClassList;

    public adapter(List<ModelClass> modelClassList) {
        this.modelClassList = modelClassList;
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
        String urlToImage = item.getUrlToImage();
        String content=item.getContent();
        final String url = item.getUrl();
       //holder.setData(title,tag,dateTime);
        if(title!=null)
            holder.titleText.setText(title);
        if(date!=null)
            holder.dateTimeText.setText(date);
        if(urlToImage!=null)
            Picasso.get().load(urlToImage).into(holder.imageView);
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

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.imageView);
            titleText=(TextView) itemView.findViewById(R.id.titleText);
            tagText=(TextView) itemView.findViewById(R.id.tagText);
            dateTimeText=(TextView) itemView.findViewById(R.id.dateText);
        }
    }

}
