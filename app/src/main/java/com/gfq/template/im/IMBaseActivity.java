package com.gfq.template.im;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gfq.template.App;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IMEventListener;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

/**
 * @created GaoFuq
 * @Date 2020/7/9 17:32
 * @Descaption
 */
public class IMBaseActivity extends AppCompatActivity {
    private static final String TAG = IMBaseActivity.class.getSimpleName();

    // 监听做成静态可以让每个子类重写时都注册相同的一份。
    private static IMEventListener mIMEventListener = new IMEventListener() {
        @Override
        public void onForceOffline() {
            ToastUtil.toastLongMessage("您的帐号已在其它终端登录");
            logout(App.getAppCtx());
        }
    };

    public static void logout(Context context) {
        UserInfo userInfo = new UserInfo();
//        Intent intent = new Intent(context, LoginForDevActivity.class);
//        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra(Constants.LOGOUT, true);
//        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TUIKit.addIMEventListener(mIMEventListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        boolean login = UserInfo.getInstance().isAutoLogin();
//        if (!login) {
//            BaseActivity.logout(DemoApplication.instance());
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
