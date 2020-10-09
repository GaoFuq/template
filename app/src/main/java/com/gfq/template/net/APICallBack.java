package com.gfq.template.net;

public interface APICallBack<T> {
    void onSuccess(T t);
    void onFailed();
}
