package com.gfq.template.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.gfq.template.utils.ComUtil;
import com.gfq.template.utils.statusbar.StatusBarUtils;

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();
    protected T binding;

    protected abstract int layout();

    protected abstract void main();

    protected abstract void handleClick();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!setStatusBar()) {
            StatusBarUtils.setStatusBarColor(this, 0xffffffff);
            StatusBarUtils.setStatusBarDarkTheme(this, true);
        }

        binding = DataBindingUtil.setContentView(this, layout());
        if (binding == null) {
            setContentView(layout());
        }
        main();

        handleClick();
    }

//    protected void setUMAlias() {
//        PushAgent mPushAgent = PushAgent.getInstance(this);
//        String pushAlias = ShareUtil.getPushAlias();
//        if (pushAlias.length() > 0) {
//            mPushAgent.addAlias(pushAlias, "app", new UTrack.ICallBack() {
//
//                @Override
//                public void onMessage(boolean isSuccess, String message) {
//                    logE("別名注冊"+message);
//                }
//            });
//        }
//    }

    public boolean setStatusBar() {
        return false;
    }


    protected void logd(String msg) {
        Log.d(TAG, msg);
    }

    protected void loge(String msg) {
        Log.e(TAG, msg);
    }

    public void toast(String msg) {
        ComUtil.toast(msg);
    }

}
