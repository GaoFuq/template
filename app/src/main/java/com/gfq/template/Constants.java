package com.gfq.template;

import android.os.Environment;

/**
 * @created GaoFuq
 * @Date 2020/7/10 10:01
 * @Descaption
 */
public class Constants {


    //文件路径
    public static final String APP_DIRECTORY = Environment.getExternalStorageDirectory() + "/weddingCar";
    public static final String APP_PIC_DIRECTORY = APP_DIRECTORY + "/pictures";
    public static final String APP_CACHE_DIRECTORY = APP_DIRECTORY + "/caches";
    public static final String APP_DOWNLOAD_DIRECTORY = APP_DIRECTORY + "/download";


    public static final String FILE_PROVIDER = App.getAppCtx().getPackageName()+".provider";

}
