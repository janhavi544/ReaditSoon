package com.readitsoon.pabbas;

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
import com.readitsoon.pabbas.fragments.myStoriesFragment;

public class MainActivity extends AppCompatActivity {
    CategoryFragmentPagerAdapter categoryFragmentPagerAdapter;
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
                            titleChange="ALL STORIES";
                        }
                    });
                    sheetView.findViewById(R.id.societyCategoryText).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            titleChange="SOCIETY";
                        }
                    });
                    sheetView.findViewById(R.id.businessCategoryText).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            titleChange="BUSINESS";
                        }
                    });
                    sheetView.findViewById(R.id.environmentCategoryText).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            titleChange="ENVIRONMENT";
                        }
                    });
                    sheetView.findViewById(R.id.cultureCategoryText).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            titleChange="CULTURE";
                        }
                    });
                    sheetView.findViewById(R.id.scienceCategoryText).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            titleChange="SCIENCE";
                        }
                    });
                sheetView.findViewById(R.id.sportCategoryText).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        titleChange="SPORT";
                    }
                });
                sheetView.findViewById(R.id.fashionCategoryText).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        titleChange="FASHION";
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
        categoryFragmentPagerAdapter.addFragment(new myStoriesFragment(),"MY STORIES",1);
        viewPager.setAdapter(categoryFragmentPagerAdapter);
    }
}