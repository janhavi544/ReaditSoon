package com.readitsoon.pabbas.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.readitsoon.pabbas.model.ModelClass;
import com.readitsoon.pabbas.R;
import com.readitsoon.pabbas.adapter.adapter;

import java.util.ArrayList;
import java.util.List;

public class allStoriesFragment extends Fragment {
    public static List<ModelClass> modelClasses=new ArrayList<>();
    static List<Boolean> isBookmarked=new ArrayList<>();
    public static RecyclerView recyclerView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.all_stories_fragment, container, false);
         recyclerView=v.findViewById(R.id.recyclerViewAll);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        final ProgressBar progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        //progressBar.setVisibility(View.VISIBLE);
        modelClasses.clear();
        isBookmarked.clear();

        ModelClass m=new ModelClass("", "1", "02/01/2021","dsabhdgv","bcbcbdbcb");
                modelClasses.add(m);
                isBookmarked.add(false);
        m=new ModelClass("", "2", "02/01/2021","dsabhdgv","bcbcbdbcb");
                modelClasses.add(m);
                isBookmarked.add(false);
        m=new ModelClass("", "3", "02/01/2021","dsabhdgv","bcbcbdbcb");
                modelClasses.add(m);
                isBookmarked.add(false);
        m=new ModelClass("", "4", "02/01/2021","dsabhdgv","bcbcbdbcb");
                modelClasses.add(m);
        isBookmarked.add(false);
        m=new ModelClass("", "5", "02/01/2021","dsabhdgv","bcbcbdbcb");
                modelClasses.add(m);
                isBookmarked.add(false);
        adapter mAdapter=new adapter(getContext(),modelClasses,isBookmarked);
//                        //dbHelper.addtoCategory(arrayList.get(i), Constant.TABLE_CATEGORY);
        recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();

       //String urltemp="http://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=5229215947344c06ac9a7120d4f6b8e2";

//        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, urltemp, null, new Response.Listener<JSONObject>() {
//
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.i("response", response.toString());
//                progressBar.setVisibility(View.INVISIBLE);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("error","failed to access");
//            }
//        });
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urltemp, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                if (response.length() <= 0) {
//                    return;
//                }
//
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject jsonObject = response.getJSONObject(i);
//
//
//                        Toast.makeText(getContext(), jsonObject.toString(), Toast.LENGTH_SHORT).show();
//                       // arrayList.add(new Category(category_id, category_name, category_image, total_wallpaper));
//                        //dbHelper.addtoCategory(arrayList.get(i), Constant.TABLE_CATEGORY);
//                        //mAdapter.notifyDataSetChanged();
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
//            }
//        });
//        MySingleton.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);

        return v;
    }
}