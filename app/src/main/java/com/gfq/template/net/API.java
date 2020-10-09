package com.gfq.template.net;


public class API<T> {

    private int errorCode;

    private String errorMsg;

    private T data;


    public int getErrorCode() {
        return errorCode;
    }

    public API<T> setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public API<T> setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public T getData() {
        return data;
    }

    public API<T> setData(T data) {
        this.data = data;
        return this;
    }
}
