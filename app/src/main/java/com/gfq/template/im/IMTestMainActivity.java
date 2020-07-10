package com.gfq.template.im;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.gfq.template.R;
import com.gfq.template.databinding.ActivityImTestMainBinding;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

/**
 * @created GaoFuq
 * @Date 2020/7/9 17:59
 * @Descaption
 */
public class IMTestMainActivity extends IMBaseActivity {
    ActivityImTestMainBinding binding;
    private String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = DataBindingUtil.setContentView(this, R.layout.activity_im_test_main);

        xxx();

        binding.btn.setOnClickListener(v -> registerOrLogin());

        binding.btnChat.setOnClickListener(v -> startC2CChat());
    }

    //注册一个用于测试聊天的对象
    private void xxx() {
        String xx = GenerateTestUserSig.genTestUserSig("test");
        TUIKit.login("test", xx, new IUIKitCallBack() {
            @Override
            public void onError(String module, final int code, final String desc) {
            }

            @Override
            public void onSuccess(Object data) {
            }
        });
    }

    private void startC2CChat() {
        if(name==null)return;
        Intent intent = new Intent(IMTestMainActivity.this, ChatActivity.class).putExtra("name",name);
        startActivity(intent);
        finish();
    }

    private void registerOrLogin() {
        String userName = binding.edit.getText().toString();
        if(userName.length()==0){
            ToastUtil.toastShortMessage("请输入用户名");
            return;
        }

        // 获取userSig函数
        String userSig = GenerateTestUserSig.genTestUserSig(userName);

        TUIKit.login(userName, userSig, new IUIKitCallBack() {
            @Override
            public void onError(String module, final int code, final String desc) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        ToastUtil.toastLongMessage("登录失败, errCode = " + code + ", errInfo = " + desc);
                    }
                });
            }

            @Override
            public void onSuccess(Object data) {
                name = userName;
                runOnUiThread(new Runnable() {
                    public void run() {
                        ToastUtil.toastLongMessage("登录成功 ");
                    }
                });


            }
        });


    }
}
