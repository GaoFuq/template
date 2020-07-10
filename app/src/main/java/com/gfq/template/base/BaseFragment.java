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


    protected abstract int layout();


    protected abstract void main();

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
        View view = inflater.inflate(R.layout.fragment_base, parent, false);
        FrameLayout container = view.findViewById(R.id.base_fragment_container);
        try {
            binding = DataBindingUtil.inflate(inflater, layout(), container, false);
            container.addView(binding.getRoot());
        } catch (Exception e) {
            View noBindingContent = inflater.inflate(layout(), container, false);
            container.addView(noBindingContent);
        }
        fitSysWindow(view);
        return view;
    }

    public void fitSysWindow(View view) {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        logE("onViewCreated");
        try {
            controller = NavHostFragment.findNavController(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        initBaseAction(view);
        main();
        handleClick();
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

    protected void logD(String msg) {
        Log.d(TAG, msg);
    }

    protected void logE(String msg) {
        Log.e(TAG, msg);
    }


}
