package com.readitsoon.pabbas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    MyFragmentPagerAdapter myFragmentPagerAdapter;
    ViewPager viewPager;
    Toolbar toolbar;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        toolbar=(Toolbar)findViewById(R.id.myToolbar);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        setSupportActionBar(toolbar);
        setPagerAdapter();
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setPagerAdapter() {
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        myFragmentPagerAdapter.addFragment(new allStoriesFragment(),"ALL STORIES");
        myFragmentPagerAdapter.addFragment(new myStoriesFragment(),"MY STORIES");
        viewPager.setAdapter(myFragmentPagerAdapter);
    }
}