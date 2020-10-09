package com.gfq.template.base;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;


import com.gfq.template.net.APIStatus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity implements APIStatus {
    protected final String TAG = getClass().getSimpleName();
    protected T binding;
    private ProgressBar progressBar;

    protected abstract int layout();

    protected abstract void main();

    protected abstract void handleClick();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (!setStatusBar()) {
//            StatusBarUtils.setStatusBarColor(this, 0xffffffff);
//            StatusBarUtils.setStatusBarDarkTheme(this, true);
//        }

        binding = DataBindingUtil.setContentView(this, layout());
        if (binding == null) {
            setContentView(layout());
        }

        initProgress();


        main();

        handleClick();
    }

    private void initProgress() {
        progressBar = new ProgressBar(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-2, -2);
        params.gravity = Gravity.CENTER;
        addContentView(progressBar, params);

    }


    public boolean setStatusBar() {
        return false;
    }


    protected void logd(String msg) {
        Log.d(TAG, msg);
    }

    protected void loge(String msg) {
        Log.e(TAG, msg);
    }

//    public void toast(String msg) {
//        ComUtil.toast(msg);
//    }


    @Override
    public void onAPIStart() {
        loge(TAG + " -> onAPIStart");
        if (!progressBar.isShown()) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onAPIComplete() {
        loge(TAG + " -> onAPIComplete");
        if (progressBar.isShown()) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAPIError() {
        loge(TAG + " -> onAPIError");
        if (!progressBar.isShown()) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

}
