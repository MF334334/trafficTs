package com.example.trafficts.Adapters_framents;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FragmentsAdapters extends FragmentPagerAdapter {

    public List<Fragment> mFragmentList;

    public FragmentsAdapters(@NonNull FragmentManager fm, List<Fragment> mFragmentList) {
        super(fm);
        this.mFragmentList = mFragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList==null?null:mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList==null?0:mFragmentList.size();
    }

    @NotNull
    @Override
    public Object instantiateItem(@NotNull ViewGroup container, int position)
    {
        //处理position。让数组下标落在[0,fragmentList.size)中，防止越界
        position = position % mFragmentList.size();

        return super.instantiateItem(container, position);
    }
}
