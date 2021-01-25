package com.readitsoon.pabbas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.readitsoon.pabbas.adapter.horizontalAdapter;
import com.readitsoon.pabbas.model.ModelClass;

import java.util.ArrayList;
import java.util.List;

public class StoriesActivity extends AppCompatActivity {
    static List<ModelClass> modelClassesHorizontal=new ArrayList<>();
    static List<Boolean> isBookmarkedHorizontal=new ArrayList<>();
    static RecyclerView recyclerViewHorizontal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories);

        String title=getIntent().getStringExtra("item");
        recyclerViewHorizontal=findViewById(R.id.recyclerViewHorizontal);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewHorizontal.setLayoutManager(linearLayoutManager);
        recyclerViewHorizontal.setHasFixedSize(true);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //progressBar.setVisibility(View.VISIBLE);
        modelClassesHorizontal.clear();
        isBookmarkedHorizontal.clear();
        ModelClass m=new ModelClass("", "1", "02/01/2021","dsabhdgv","bcbcbdbcb");
        modelClassesHorizontal.add(m);
        isBookmarkedHorizontal.add(false);
        m=new ModelClass("", "2", "02/01/2021","dsabhdgv","bcbcbdbcb");
        modelClassesHorizontal.add(m);
        isBookmarkedHorizontal.add(false);
        m=new ModelClass("", "3", "02/01/2021","dsabhdgv","bcbcbdbcb");
        modelClassesHorizontal.add(m);
        isBookmarkedHorizontal.add(false);
        m=new ModelClass("", "4", "02/01/2021","dsabhdgv","bcbcbdbcb");
        modelClassesHorizontal.add(m);
        isBookmarkedHorizontal.add(false);
        m=new ModelClass("", "5", "02/01/2021","dsabhdgv","bcbcbdbcb");
        modelClassesHorizontal.add(m);
        isBookmarkedHorizontal.add(false);
        horizontalAdapter hAdapter=new horizontalAdapter(getApplicationContext(),modelClassesHorizontal,isBookmarkedHorizontal);
//                        //dbHelper.addtoCategory(arrayList.get(i), Constant.TABLE_CATEGORY);
        recyclerViewHorizontal.setAdapter(hAdapter);
        linearLayoutManager.scrollToPositionWithOffset(Integer.parseInt(title)-1, 20);
        hAdapter.notifyDataSetChanged();

    }
}