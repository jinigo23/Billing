package com.gia.billing.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragment_list = new ArrayList<Fragment>();
    private ArrayList<String> title_list = new ArrayList<String>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragment_list.get(position);
    }

    @Override
    public int getCount() {
        return fragment_list.size();
    }

    public void pageFragment(Fragment fragment, String title) {
        fragment_list.add(fragment);
        title_list.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title_list.get(position);
    }
}
