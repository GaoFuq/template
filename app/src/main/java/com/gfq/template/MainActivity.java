package com.gfq.template;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gfq.gcountdown.GCountdown;
import com.gfq.template.base.BaseActivity;


public class MainActivity extends BaseActivity {
    String ss="";

    @Override
    protected int layout() {
        return R.layout.activity_main;
    }

    @Override
    protected void main() {
        TextView view = findViewById(R.id.test);
        GCountdown gCountdown = new GCountdown(view,10000,1000,"finished","onTick","秒");
        gCountdown.startTimer(getLifecycle());
    }

    @Override
    protected void handleClick() {

    }


}