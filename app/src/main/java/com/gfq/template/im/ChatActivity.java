package com.gfq.template.im;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.gfq.template.R;
import com.tencent.imsdk.v2.V2TIMConversation;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;

/**
 * @created GaoFuq
 * @Date 2020/7/9 16:58
 * @Descaption
 */
public class ChatActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        String name = getIntent().getStringExtra("name");
        // 从布局文件中获取聊天面板
        ChatLayout chatLayout = findViewById(R.id.chat_layout);
// 单聊面板的默认 UI 和交互初始化
        chatLayout.initDefault();
// 传入 ChatInfo 的实例，这个实例必须包含必要的聊天信息，一般从调用方传入
// 构造 mChatInfo 可参考 StartC2CChatActivity.java 的方法 startConversation


        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setType(V2TIMConversation.V2TIM_C2C);
        chatInfo.setId(name);
//        if (!TextUtils.isEmpty(mSelectedItem.getRemark())) {//是否有备注
//            chatName = mSelectedItem.getRemark();
//        } else if (!TextUtils.isEmpty(mSelectedItem.getNickname())) {
//            chatName = mSelectedItem.getNickname();
//        }
        chatInfo.setChatName(name);

        chatLayout.setChatInfo(chatInfo);
    }
}
