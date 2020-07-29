package com.gfq.template;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.gfq.template.base.BaseActivity;
import com.gfq.template.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected int layout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
    }


    @Override
    protected void handleClick() {

    }


}