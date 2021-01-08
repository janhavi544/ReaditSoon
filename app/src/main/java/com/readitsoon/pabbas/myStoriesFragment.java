package com.readitsoon.pabbas;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class myStoriesFragment extends Fragment {
    static List<ModelClass> myModelClass;
    static List<Boolean> myBookmarked;
     myStoriesAdapter myStoriesAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (Build.VERSION.SDK_INT >= 26) {
            ft.setReorderingAllowed(false);
        }
        ft.detach(this).attach(this).commit();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.my_stories_fragment, container, false);
        RecyclerView myRecyclerView=v.findViewById(R.id.recyclerViewMy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
       // myModelClasses.add(new ModelClass("", "1", "02/01/2021","dsabhdgv","bcbcbdbcb"));
        //allStoriesFragment.isBookmarked
        myRecyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerView.setHasFixedSize(true);
        myModelClass=new ArrayList<>();
        myBookmarked=new ArrayList<>();
        for(int i=0;i<allStoriesFragment.isBookmarked.size();i++)
        {
            if(allStoriesFragment.isBookmarked.get(i)==true)
            {
                myModelClass.add(allStoriesFragment.modelClasses.get(i));
                myBookmarked.add(true);
            }
        }
       myStoriesAdapter=new myStoriesAdapter(getContext(),myModelClass,myBookmarked);
//                        //dbHelper.addtoCategory(arrayList.get(i), Constant.TABLE_CATEGORY);
        myRecyclerView.setAdapter(myStoriesAdapter);
        myStoriesAdapter.notifyDataSetChanged();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        myStoriesAdapter.notifyDataSetChanged();
    }
}
