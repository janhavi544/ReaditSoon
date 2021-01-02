package com.readitsoon.pabbas;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    MyFragmentPagerAdapter myFragmentPagerAdapter;
    ViewPager viewPager;
    Toolbar toolbar;
    TabLayout tabLayout;
    String titleChange="";
    static boolean popular=true;
    static boolean category=false;
    private BottomSheetDialog bottomSheetDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        toolbar=(Toolbar)findViewById(R.id.myToolbar);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        setSupportActionBar(toolbar);
        setPagerAdapter("ALL STORIES");
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //return super.onOptionsItemSelected(item);
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
                else if(popular==false&&category==true)
                {
                    showByPopularity.setChecked(false);
                    showByCategory.setChecked(true);
                }
                showByPopularity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showByCategory.setChecked(false);
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
                        popular=false;
                        category=true;
                        sheetView.findViewById(R.id.allStoriesCategoryText).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                titleChange="ALL STORIES";
                            }
                        });
                        sheetView.findViewById(R.id.economyCategoryText).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                titleChange="ECONOMY";
                            }
                        });
                        sheetView.findViewById(R.id.businessCategoryText).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                titleChange="BUSINESS";
                            }
                        });
                        sheetView.findViewById(R.id.marketCategoryText).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                titleChange="MARKET";
                            }
                        });
                        sheetView.findViewById(R.id.politicsCategoryText).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                titleChange="POLITICS";
                            }
                        });
                        sheetView.findViewById(R.id.scienceCategoryText).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                titleChange="SCIENCE";
                            }
                        });
                        if(showByCategory.isChecked()==false&&showByPopularity.isChecked()==false)
                            showByPopularity.setChecked(true);
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
                        if(titleChange.length()<1)
                            titleChange="ALL STORIES";
                        setPagerAdapter(titleChange);
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
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        myFragmentPagerAdapter.addFragment(new allStoriesFragment(),title);
        myFragmentPagerAdapter.addFragment(new myStoriesFragment(),"MY STORIES");
        viewPager.setAdapter(myFragmentPagerAdapter);
    }
}