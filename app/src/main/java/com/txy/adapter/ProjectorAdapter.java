package com.txy.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.txy.fragment.ProjectorFragment;

/**
 * Created by Administrator on 2016/1/14.
 */
public class ProjectorAdapter extends FragmentStatePagerAdapter {

    private int count;

    public ProjectorAdapter(FragmentManager fm, int count) {
        super(fm);
        this.count = count;
    }

    @Override
    public Fragment getItem(int position) {
        ProjectorFragment projectorFragment = new ProjectorFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position + 1);
        projectorFragment.setArguments(bundle);
        return projectorFragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return count;
    }
}
