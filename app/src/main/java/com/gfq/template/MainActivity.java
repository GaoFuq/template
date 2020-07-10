package com.gfq.template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.gfq.template.R;
import com.gfq.template.im.IMTestMainActivity;
import com.tencent.qcloud.tim.uikit.component.indexlib.IndexBar.widget.IndexBar;

public class MainActivity extends AppCompatActivity {
    String ss="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IndexBar bar = findViewById(R.id.indexBar);

        findViewById(R.id.test).setOnClickListener(v -> startActivity(new Intent(this, IMTestMainActivity.class)));

    }

}