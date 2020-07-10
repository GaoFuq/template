package com.cq1080.template.im;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gfq.template.R;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationLayout;

/**
 * @created GaoFuq
 * @Date 2020/7/9 16:52
 * @Descaption
 */
public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // 从布局文件中获取会话列表面板
        ConversationLayout conversationLayout = findViewById(R.id.conversation_layout);
        // 初始化聊天面板
        conversationLayout.initDefault();
    }
}
