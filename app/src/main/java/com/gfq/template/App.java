package com.gfq.template;

import android.app.Application;
import android.content.Context;

import com.gfq.template.config.TIMConfig;
import com.tencent.imsdk.v2.V2TIMSDKConfig;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.config.CustomFaceConfig;
import com.tencent.qcloud.tim.uikit.config.GeneralConfig;
import com.tencent.qcloud.tim.uikit.config.TUIKitConfigs;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import java.lang.ref.WeakReference;

/**
 * @created GaoFuq
 * @Date 2020/7/9 16:54
 * @Descaption
 */
public class App extends Application {
    public static WeakReference<Context> appCtx;

    @Override
    public void onCreate() {
        super.onCreate();
        appCtx = new WeakReference<>(this);
        initTIM();
        initUM();
    }

    public static Context getAppCtx() {
        return appCtx.get();
    }

    private void initTIM() {
        // 配置 Config，请按需配置
        TUIKitConfigs configs = TUIKit.getConfigs();
        configs.setSdkConfig(new V2TIMSDKConfig());
        configs.setCustomFaceConfig(new CustomFaceConfig());
        configs.setGeneralConfig(new GeneralConfig());

        TUIKit.init(this, TIMConfig.SDKAppID, configs);
    }

    /**
     * 友盟初始化
     */
    private void initUM() {
        UMConfigure.init(this, "替换为Appkey,服务后台位置：应用管理 -> 应用信息 -> Appkey", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "替换为秘钥信息,服务后台位置：应用管理 -> 应用信息 -> Umeng Message Secret");
        //获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                //Log.i(TAG,"注册成功：deviceToken：-------->  " + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                //Log.e(TAG,"注册失败：-------->  " + "s:" + s + ",s1:" + s1);
            }
        });
    }
}
