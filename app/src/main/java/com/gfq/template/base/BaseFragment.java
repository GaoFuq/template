package com.gfq.template.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.gfq.template.R;
import com.gfq.template.utils.ComUtil;


/**
 * create by 高富强
 * on {2019/9/18} {16:29}
 * desctapion:
 * 使用viewpager,当behavior=0时，在 lazyLoadData()方法中可实现懒加载数据。
 * 当behavior=1时，无影响。
 * <p>
 * 当使用viewpager2时，无影响。
 */
public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {
    protected final String TAG = getClass().getSimpleName();

    protected T binding;
    protected NavController controller;
    protected TextView tvBaseFunction;
    protected TextView tvBaseTitle;
    protected View baseTitle;
    protected TextView tvBaseLeftFunction;
    private ProgressBar loading;
    protected ImageView ivBack;

    private boolean isViewCreated = false;
    private boolean currentIsVisible = false;
    private View rootView;

    protected abstract int layout();


    protected abstract void initView();

    protected abstract void handleClick();


    public BaseFragment() {
    }

    protected AppCompatActivity mActivity;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        loge(TAG + "--onCreateView");
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_base, parent, false);
            FrameLayout container = rootView.findViewById(R.id.base_fragment_container);
            try {
                binding = DataBindingUtil.inflate(inflater, layout(), container, false);
                container.addView(binding.getRoot());
            } catch (Exception e) {
                View noBindingContent = inflater.inflate(layout(), container, false);
                container.addView(noBindingContent);
            }
        }
        isViewCreated = true;
        if (fitSysWindow()) {
            rootView.findViewById(R.id.statusbar).setVisibility(View.VISIBLE);
        }
        initBaseAction(rootView);
        initView();
        handleClick();
        try {
            controller = NavHostFragment.findNavController(this);
        } catch (Exception ignore) {
        }
        if (getUserVisibleHint()) {
            dispatch(true);
        }
        return rootView;
    }

    public boolean fitSysWindow() {
        return false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        loge(TAG + "--setUserVisibleHint isVisibleToUser = " + isVisibleToUser);
        if (isViewCreated) {
            if (isVisibleToUser && !currentIsVisible) {
                dispatch(true);
            } else if (!isVisibleToUser && currentIsVisible) {
                dispatch(false);
            }
        }
    }


    private void dispatch(boolean isVisible) {
        if (currentIsVisible == isVisible) {
            return;
        }
        currentIsVisible = isVisible;
        if (isVisible) {
            lazyLoadData();
        } else {
            stopLoadData();
        }
    }

    private void stopLoadData() {

    }

    protected abstract void lazyLoadData();

    @Override
    public void onPause() {
        super.onPause();
        if (!currentIsVisible && getUserVisibleHint()) {
            dispatch(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isOnResumeReLoadData() && currentIsVisible && getUserVisibleHint()) {
            dispatch(true);
        }
    }

    public boolean isOnResumeReLoadData() {
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        loge(TAG + "--onDestroyView ");
        currentIsVisible = false;
        isViewCreated = false;
    }

    private void initBaseAction(View view) {
        loading = view.findViewById(R.id.loading);
        ivBack = view.findViewById(R.id.iv_back);


        ivBack.setOnClickListener(v -> {
            if (controller != null) {
                boolean boo = controller.popBackStack();
                if (!boo) {
                    mActivity.finish();
                }
            } else {
                mActivity.finish();
            }
        });
        tvBaseFunction = view.findViewById(R.id.tv_func);
        tvBaseLeftFunction = view.findViewById(R.id.tv_func_left);
        tvBaseTitle = view.findViewById(R.id.tv_title);
        baseTitle = view.findViewById(R.id.base_title);
        setTvBaseTitle();
        setTvBaseFunction();
        setTvBaseFunctionLeft();
        if (setUseBaseTitle()) {
            baseTitle.setVisibility(View.VISIBLE);
        } else {
            baseTitle.setVisibility(View.GONE);
        }
    }


    public void nav(int id) {
        controller.navigate(id);
    }

    public void nav(int id, Bundle args) {
        controller.navigate(id, args);
    }

    public void setTvBaseTitle() {
    }

    public void setTvBaseFunction() {
    }

    public void setTvBaseFunctionLeft() {

    }

    public boolean setUseBaseTitle() {
        return true;
    }


    public void toast(String msg) {
        ComUtil.toast(msg);
    }

    public void loading() {
        loading.setVisibility(View.VISIBLE);
    }

    public void loaded() {
        loading.setVisibility(View.GONE);
    }

    protected void logd(String msg) {
        Log.d(TAG, msg);
    }

    protected void loge(String msg) {
        Log.e(TAG, msg);
    }


}
