package com.gfq.template.im;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gfq.template.R;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactLayout;

/**
 * @created GaoFuq
 * @Date 2020/7/9 17:00
 * @Descaption
 */
public class ContactActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        // 从布局文件中获取通讯录面板
        ContactLayout contactLayout = findViewById(R.id.contact_layout);
        // 通讯录面板的默认 UI 和交互初始化
        contactLayout.initDefault();
    }
}
