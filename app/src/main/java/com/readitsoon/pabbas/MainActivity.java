package com.readitsoon.pabbas;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.readitsoon.pabbas.adapter.CategoryFragmentPagerAdapter;
import com.readitsoon.pabbas.fragments.AllStoryFragment;
import com.readitsoon.pabbas.fragments.MyStoryFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CategoryFragmentPagerAdapter categoryFragmentPagerAdapter;
    ViewPager viewPager;
    Toolbar toolbar;
    TabLayout tabLayout;
    String titleChange="";
    static boolean popular=true;
    static boolean category=false;
    public static List<News> bookmarked=new ArrayList<>();
    public static ArrayList<String> bookmarkedUrl=new ArrayList<>();
    private BottomSheetDialog bottomSheetDialog;
    public void loadBookmarked()
    {
        SharedPreferences sharedPreferences=this.getSharedPreferences("com.readitsoon.pabbas", Context.MODE_PRIVATE);
        try{

            bookmarkedUrl=(ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("bookmarked",ObjectSerializer.serialize(new ArrayList<String>())));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        toolbar=(Toolbar)findViewById(R.id.myToolbar);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loadBookmarked();
        setPagerAdapter("ALL STORIES");
        tabLayout.setupWithViewPager(viewPager);
        // size= NewsAdapter.bookmarked.size();
     String str=getIntent().getStringExtra("finish");
     if(str!=null&&str.equals("finish"))
     {
         finish();
     }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //return super.onOptionsItemSelected(item);
        int[][] states = new int[][] {
                new int[] { android.R.attr.state_enabled}, // enabled
                new int[] {-android.R.attr.state_enabled}, // disabled
                new int[] { android.R.attr.state_pressed}  // pressed
        };
        int[][] statesDisabled = new int[][] {
                new int[] {android.R.attr.state_enabled},
        };
        int[] colors = new int[] {
                Color.parseColor("#800219"),
                Color.parseColor("#FF000000"),
                Color.RED
        };
        int [] colorsDisable=new int[]{
                Color.parseColor("#FF000000")
        };
        ColorStateList myList = new ColorStateList(states, colors);
        ColorStateList disabledList=new ColorStateList(statesDisabled,colorsDisable);
        switch (item.getItemId())
        {
            case R.id.configure:
                bottomSheetDialog=new BottomSheetDialog(MainActivity.this,R.style.bottomSheetTheme);
                View sheetView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_layout,(ViewGroup)findViewById(R.id.bottom_sheet));
                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();
                Switch showByPopularity=(Switch) sheetView.findViewById(R.id.showByPopularityButton);
                Switch showByCategory=(Switch)sheetView.findViewById(R.id.showByCategoryButton);
                if(popular==true&&category==false)
                {
                    showByPopularity.setChecked(true);
                    showByCategory.setChecked(false);
                }
                else
                {
                    popular=false;
                    category=true;
                    showByPopularity.setChecked(false);
                    showByCategory.setChecked(true);
                }
                showByPopularity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showByCategory.setChecked(false);
                        showByPopularity.setChecked(true);
                        popular=true;
                        category=false;
                        titleChange="ALL STORIES";
                        if(showByCategory.isChecked()==false&&showByPopularity.isChecked()==false)
                            showByPopularity.setChecked(true);
                    }
                });
                showByCategory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showByPopularity.setChecked(false);
                        showByCategory.setChecked(true);
                        popular=false;
                        category=true;
                        if(showByCategory.isChecked()==false&&showByPopularity.isChecked()==false)
                            showByPopularity.setChecked(true);
                    }
                });
                    sheetView.findViewById(R.id.worldCategoryText).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            titleChange="WORLD";
                            if(showByCategory.isChecked()==true)
                            sheetView.findViewById(R.id.worldCategoryText).setForegroundTintList(myList);
                            sheetView.findViewById(R.id.societyCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.businessCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.environmentCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.cultureCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.scienceCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.fashionCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.sportCategoryText).setForegroundTintList(disabledList);
                        }
                    });
                    sheetView.findViewById(R.id.societyCategoryText).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            titleChange="SOCIETY";
                            if(showByCategory.isChecked()==true)
                                sheetView.findViewById(R.id.societyCategoryText).setForegroundTintList(myList);
                            sheetView.findViewById(R.id.worldCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.businessCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.environmentCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.cultureCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.scienceCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.fashionCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.sportCategoryText).setForegroundTintList(disabledList);
                        }
                    });
                    sheetView.findViewById(R.id.businessCategoryText).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            titleChange="BUSINESS";
                            if(showByCategory.isChecked()==true)
                                sheetView.findViewById(R.id.businessCategoryText).setForegroundTintList(myList);
                            sheetView.findViewById(R.id.worldCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.societyCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.environmentCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.cultureCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.scienceCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.fashionCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.sportCategoryText).setForegroundTintList(disabledList);
                        }
                    });
                    sheetView.findViewById(R.id.environmentCategoryText).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            titleChange="ENVIRONMENT";
                            if(showByCategory.isChecked()==true)
                                sheetView.findViewById(R.id.environmentCategoryText).setForegroundTintList(myList);
                            sheetView.findViewById(R.id.worldCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.societyCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.businessCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.cultureCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.scienceCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.fashionCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.sportCategoryText).setForegroundTintList(disabledList);
                        }
                    });
                    sheetView.findViewById(R.id.cultureCategoryText).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            titleChange="CULTURE";
                            if(showByCategory.isChecked()==true)
                                sheetView.findViewById(R.id.cultureCategoryText).setForegroundTintList(myList);
                            sheetView.findViewById(R.id.worldCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.societyCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.businessCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.environmentCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.scienceCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.fashionCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.sportCategoryText).setForegroundTintList(disabledList);
                        }
                    });
                    sheetView.findViewById(R.id.scienceCategoryText).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            titleChange="SCIENCE";
                            if(showByCategory.isChecked()==true)
                                sheetView.findViewById(R.id.scienceCategoryText).setForegroundTintList(myList);
                            sheetView.findViewById(R.id.worldCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.societyCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.businessCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.environmentCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.cultureCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.fashionCategoryText).setForegroundTintList(disabledList);
                            sheetView.findViewById(R.id.sportCategoryText).setForegroundTintList(disabledList);
                        }
                    });
                sheetView.findViewById(R.id.sportCategoryText).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        titleChange="SPORT";
                        if(showByCategory.isChecked()==true)
                            sheetView.findViewById(R.id.sportCategoryText).setForegroundTintList(myList);
                        sheetView.findViewById(R.id.worldCategoryText).setForegroundTintList(disabledList);
                        sheetView.findViewById(R.id.societyCategoryText).setForegroundTintList(disabledList);
                        sheetView.findViewById(R.id.businessCategoryText).setForegroundTintList(disabledList);
                        sheetView.findViewById(R.id.environmentCategoryText).setForegroundTintList(disabledList);
                        sheetView.findViewById(R.id.cultureCategoryText).setForegroundTintList(disabledList);
                        sheetView.findViewById(R.id.scienceCategoryText).setForegroundTintList(disabledList);
                        sheetView.findViewById(R.id.fashionCategoryText).setForegroundTintList(disabledList);
                    }
                });
                sheetView.findViewById(R.id.fashionCategoryText).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        titleChange="FASHION";
                        if(showByCategory.isChecked()==true)
                            sheetView.findViewById(R.id.fashionCategoryText).setForegroundTintList(myList);
                        sheetView.findViewById(R.id.worldCategoryText).setForegroundTintList(disabledList);
                        sheetView.findViewById(R.id.societyCategoryText).setForegroundTintList(disabledList);
                        sheetView.findViewById(R.id.businessCategoryText).setForegroundTintList(disabledList);
                        sheetView.findViewById(R.id.environmentCategoryText).setForegroundTintList(disabledList);
                        sheetView.findViewById(R.id.cultureCategoryText).setForegroundTintList(disabledList);
                        sheetView.findViewById(R.id.scienceCategoryText).setForegroundTintList(disabledList);
                        sheetView.findViewById(R.id.sportCategoryText).setForegroundTintList(disabledList);
                    }
                });
                if(showByCategory.isChecked()==false&&showByPopularity.isChecked()==false)
                    showByPopularity.setChecked(true);
                sheetView.findViewById(R.id.applyTextView).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(showByCategory.isChecked()==false&&showByPopularity.isChecked()==false)
                            showByPopularity.setChecked(true);
                        Toast.makeText(MainActivity.this, "Filter applied successfully!!", Toast.LENGTH_SHORT).show();
                        if(showByCategory.isChecked()==true)
                        setPagerAdapter(titleChange);
                        if(showByPopularity.isChecked()==true)
                            setPagerAdapter("ALL STORIES");
                        bottomSheetDialog.dismiss();
                    }
                });
                sheetView.findViewById(R.id.cancelTextView).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                return true;
        }
        return false;
    }
    private void setPagerAdapter(String title) {
        categoryFragmentPagerAdapter = new CategoryFragmentPagerAdapter(getSupportFragmentManager());
       if(title==null||title.length()<=0)
           title+="ALL STORIES";
       if(title.equals("BUSINESS"))
       {
           categoryFragmentPagerAdapter.addFragment(new AllStoryFragment(getString(R.string.business)),title,0);
        }
        else if(title.equals("WORLD"))
        {
            categoryFragmentPagerAdapter.addFragment(new AllStoryFragment(getString(R.string.world)),title,0);
        }
        else if(title.equals("SCIENCE"))
        {
            categoryFragmentPagerAdapter.addFragment(new AllStoryFragment(getString(R.string.science)),title,0);
        }
        else if(title.equals("SPORT"))
        {
            categoryFragmentPagerAdapter.addFragment(new AllStoryFragment(getString(R.string.sport)),title,0);
        }
       else if(title.equals("ENVIRONMENT"))
       {
           categoryFragmentPagerAdapter.addFragment(new AllStoryFragment(getString(R.string.environment)),title,0);
       }
       else if(title.equals("SOCIETY"))
       {
           categoryFragmentPagerAdapter.addFragment(new AllStoryFragment(getString(R.string.society)),title,0);
       }
       else if(title.equals("FASHION"))
       {
           categoryFragmentPagerAdapter.addFragment(new AllStoryFragment(getString(R.string.fashion)),title,0);
       }
       else if(title.equals("CULTURE"))
       {
           categoryFragmentPagerAdapter.addFragment(new AllStoryFragment(getString(R.string.culture)),title,0);
       }
       else {
           categoryFragmentPagerAdapter.addFragment(new AllStoryFragment(title),title,0);
       }
        categoryFragmentPagerAdapter.addFragment(new MyStoryFragment(getApplicationContext()),"MY STORIES",1);
        viewPager.setAdapter(categoryFragmentPagerAdapter);
    }
}