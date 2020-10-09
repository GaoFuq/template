package com.gfq.template.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.gfq.template.net.APIInterface.BASE_URL;


public class APIService {
    private static APIInterface apiInterface;
    // 缓存文件最大限制大小50M
    private static final long cacheSize = 1024 * 1024 * 50;
    // 设置缓存文件路径
    private static String cacheDirectory = "APP_CACHE_DIRECTORY";
    private static Cache cache = new Cache(new File(cacheDirectory), cacheSize);
    public static String token = "";


    static {
        try {
            final X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();


            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient mClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS) // 设置连接超时时间
                    .writeTimeout(10, TimeUnit.SECONDS)// 设置写入超时时间
                    .readTimeout(10, TimeUnit.SECONDS)// 设置读取数据超时时间
                    .retryOnConnectionFailure(true)// 设置进行连接失败重试
                    .addInterceptor(loggingInterceptor)
//                    .addInterceptor(chain -> {
//                        Request request = chain.request()
//                                .newBuilder()
//                                .addHeader("appToken", token)
//                                .addHeader("from", "android")
//                                .build();
//                        return chain.proceed(request);
//                    })
                    .sslSocketFactory(sslSocketFactory, trustManager)
                    .hostnameVerifier((hostname, session) -> true)
                    .cache(cache)// 设置缓存
                    .build();


            Retrofit retrofit = new Retrofit.Builder()
                    .client(mClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            apiInterface = retrofit.create(APIInterface.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void setToken(String tt) {
        token = tt;
    }

    public static APIInterface api() {
        return apiInterface;
    }


    public static <T> void call(Observable<API<T>> apiObservable, APIStatus apiHost, APICallBack<T> callBack) {
        apiObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(x -> {
                    if (apiHost != null) {
                        apiHost.onAPIStart();
                    }
                })
                .doOnError(x -> {
                    if (apiHost != null) {
                        apiHost.onAPIError();
                    }
                })
                .doFinally(() -> {
                    if (apiHost != null) {
                        apiHost.onAPIComplete();
                    }
                })
                .map(api -> {
                    if (api.getErrorCode() == HttpStatusCode.SUCCESS) {
                        return api.getData();
                    }
                    throw new HandleException(api.getErrorCode(), api.getErrorMsg());
                })
                .subscribe(new DisposableObserver<T>() {
                    @Override
                    public void onNext(T t) {
                        if (callBack != null) {
                            callBack.onSuccess(t);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callBack != null) {
                            callBack.onFailed();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    public static <T> void call(Observable<API<T>> apiObservable, APIStatus apiStatus) {
        call(apiObservable, apiStatus, null);
    }

    public static <T> void call(Observable<API<T>> apiObservable, APICallBack<T> apiCallBack) {
        call(apiObservable, null, apiCallBack);
    }


    public static class HttpStatusCode{
        public static final int SUCCESS = 200;

        public static final int  BAD_REQUEST = 400;
        public static final int  UNAUTHORIZED = 401;
        public static final int  FORBIDDEN = 403;
        public static final int  NOT_FOUND = 404;
        public static final int  METHOD_NOT_ALLOWED = 405;
        public static final int  REQUEST_TIMEOUT = 408;

        public static final int  INTERNAL_SERVER_ERROR = 500;
        public static final int  BAD_GATEWAY = 502;
    }
}
