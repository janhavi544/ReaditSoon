package com.readitsoon.pabbas;

import android.content.Context;
import android.view.View;

public class MyOnClickListener implements View.OnClickListener {
    Context context;
    public MyOnClickListener(Context context) {
    this.context=context;
    }

    @Override
    public void onClick(View v) {
//        int itemPosition = allStoriesFragment.recyclerView.getChildLayoutPosition(v);
//        ModelClass item = allStoriesFragment.modelClasses.get(itemPosition);
//        Intent intent=new Intent(context,StoriesActivity.class);
//        intent.putExtra("item",item.getTitle());
//        context.startActivity(intent);
    }
}
