package com.example.hoang.newsproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.viewpagerindicator.IconPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoang on 3/10/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter implements IconPagerAdapter{

    private List<Fragment> listFragment = new ArrayList<Fragment>();
    private List<String> listTitleFragment = new ArrayList<String>();

    public ViewPagerAdapter(FragmentManager manager){
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getIconResId(int index) {
        return 0;
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    public void addFragment(Fragment fragment, String title){
        listFragment.add(fragment);
        listTitleFragment.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitleFragment.get(position);
    }
}
