package com.gfq.template;

import android.app.Application;
import android.content.Context;

//import com.gfq.umenglib.UmengLib;
//
//import com.umeng.commonsdk.UMConfigure;
//import com.umeng.message.IUmengRegisterCallback;
//import com.umeng.message.PushAgent;
//import com.umeng.socialize.PlatformConfig;

import java.lang.ref.WeakReference;

/**
 * @created GaoFuq
 * @Date 2020/7/9 16:54
 * @Descaption
 */
public class App extends Application {
    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
//        initUM();
    }



    /**
     * 友盟初始化
     */
    private void initUM() {
//        UmengLib.init(this, "", "");
//        //设置第三方登录和分享
//        PlatformConfig.setWeixin("key", "secret");
    }
}
