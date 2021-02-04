package com.readitsoon.pabbas.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.readitsoon.pabbas.fragments.MyStoryFragment;

import java.util.ArrayList;

public class CategoryFragmentPagerAdapter extends FragmentStatePagerAdapter {
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

//    @Override
//    public int getItemPosition(@NonNull Object object) {
//        MyStoryFragment f = (MyStoryFragment ) object;
//        if (f != null) {
//            f.initiateRefresh();
//        }
//        return super.getItemPosition(object);
//    }

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
