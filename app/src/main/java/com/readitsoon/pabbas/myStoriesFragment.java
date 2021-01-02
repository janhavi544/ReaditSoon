package com.readitsoon.pabbas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class myStoriesFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.my_stories_fragment, container, false);
        RecyclerView recyclerView=v.findViewById(R.id.recyclerViewMy);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        List<ModelClass> myModelClasses=new ArrayList<>();
        myModelClasses.add(new ModelClass("", "1", "02/01/2021","dsabhdgv","bcbcbdbcb"));
        //allStoriesFragment.isBookmarked
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        adapter mAdapter=new adapter(myModelClasses);
//                        //dbHelper.addtoCategory(arrayList.get(i), Constant.TABLE_CATEGORY);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        return v;
    }
}
