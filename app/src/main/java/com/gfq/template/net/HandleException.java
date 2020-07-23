package com.gfq.template.net;


import android.content.Intent;

import com.gfq.template.App;
import com.gfq.template.utils.ComUtil;

public class HandleException extends Exception {
    private HandleException() {
    }

    private static class SingletonClassInstance {
        private static final HandleException instance = new HandleException();
    }

    public static HandleException getInstance() {
        return SingletonClassInstance.instance;
    }

    public void Handle(String message, int code) {
        if (code == 401) {
//            ShareUtil.clear();
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            intent.setClass(App.appContext, FillInfoMainActivity.class);
            App.appContext.startActivity(intent);
            ComUtil.toast("该账户登录异常，请重新登录！");
        }
    }

    public void Handle(Throwable e) {
    }
}
