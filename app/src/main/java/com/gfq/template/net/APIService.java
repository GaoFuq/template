package com.gfq.template.net;

import android.util.Log;

import com.gfq.template.utils.ComUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.gfq.template.Constants.APP_CACHE_DIRECTORY;
import static com.gfq.template.net.APIInterface.BASE_URL;


public class APIService {
    private static APIInterface apiInterface;
    // 缓存文件最大限制大小50M
    private static final long cacheSize = 1024 * 1024 * 50;
    // 设置缓存文件路径
    private static String cacheDirectory = APP_CACHE_DIRECTORY;
    private static Cache cache = new Cache(new File(cacheDirectory), cacheSize);
    public static String token = "";
    public static String msg;


    static {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient mClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS) // 设置连接超时时间
                .writeTimeout(30, TimeUnit.SECONDS)// 设置写入超时时间
                .readTimeout(30, TimeUnit.SECONDS)// 设置读取数据超时时间
                .retryOnConnectionFailure(true)// 设置进行连接失败重试
                .addInterceptor(loggingInterceptor)
                .addInterceptor(chain -> {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("authorization", token)
                            .build();
                    return chain.proceed(request);
                })
                .cache(cache)// 设置缓存
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .client(mClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiInterface = retrofit.create(APIInterface.class);
    }

    public static void setToken(String tt) {
        token = tt;
    }

    public static APIInterface api() {
        return apiInterface;
    }

    public static <T> void call(Observable<API<T>> apiObservable, OnCallBack<T> onCallBack) {
        Log.e("APIService token = ", token);
        apiObservable.compose(upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map(tApi -> {
                    msg = tApi.getMsg();
                    if (tApi.getCode() == 200 || tApi.getCode() == 204) {
                        return tApi.getData();
                    } else if(tApi.getCode()==401){
                          HandleException.getInstance().Handle(tApi.getMsg(),tApi.getCode());
                          return null;
                    }else {
                        throw new RuntimeException("xxx");
                    }
                }))
                .subscribe(new DisposableObserver<T>() {
                    @Override
                    public void onNext(T t) {
                        onCallBack.onSuccess(t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ComUtil.toast(msg);
                        onCallBack.onError(msg);
                        HandleException.getInstance().Handle(e);

                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }


}
