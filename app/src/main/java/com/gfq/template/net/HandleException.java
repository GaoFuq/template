package com.gfq.template.net;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.net.SocketTimeoutException;


public class HandleException extends Exception {
   private int code;
   private String msg;

    public HandleException(int code,String msg) {
        this.code = code;
        this.msg=msg;
        handle(code);
    }
 public HandleException(Throwable e) {
        handle(e);
    }
    public  void handle(Throwable e) {
        if(e instanceof HttpException){
            HttpException exception= (HttpException) e;
            int code = exception.code();
            handle(code);
        }else if(e instanceof SocketTimeoutException){
//            ComUtil.toast("请求超时");
        }
    }
    public  void handle(int code) {
        switch (code){
            case APIService.HttpStatusCode.BAD_REQUEST:
//                ComUtil.toast(msg);
                break;
            case 401:
                break;
            case APIService.HttpStatusCode.NOT_FOUND:
//                ComUtil.toast("404");
                break;
            case APIService.HttpStatusCode.BAD_GATEWAY:
//                ComUtil.toast("502");
                break;
            case APIService.HttpStatusCode.INTERNAL_SERVER_ERROR:
//                ComUtil.toast("500");
                break;
        }
    }


}
