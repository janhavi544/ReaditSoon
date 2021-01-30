package com.readitsoon.pabbas.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class CategoryFragmentPagerAdapter extends FragmentPagerAdapter {
    public   ArrayList<Fragment> fragmentList=new ArrayList<>();
    public   ArrayList<String> fragmentTitle=new ArrayList<>();

    public CategoryFragmentPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
      return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    public void addFragment(Fragment fragment,String title,int position)
    {
        fragmentList.add(position,fragment);
        fragmentTitle.add(position,title);

    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitle.get(position);
    }
}
