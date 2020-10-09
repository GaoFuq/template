package com.gfq.template.net;



import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * retrofit 使用
 * ·@Path：所有在网址中的参数（URL的问号前面），如：http://102.10.10.132/api/Accounts/{accountId}
 * <p>
 * ·Query：URL问号后面的参数，如：http://102.10.10.132/api/Comments?access_token={access_token}
 * <p>
 * ·QueryMap：相当于多个@Query
 * <p>
 * ·Field：用于POST请求，提交单个数据
 * <p>
 * ·Body：相当于多个@Field，以对象的形式提交
 */

public interface APIInterface {

    public static final String BASE_URL = "https://www.wanandroid.com/";
    //分享下载APP
    public static final String SHARE_URL = BASE_URL + "";


    @POST(BASE_URL + "api/handle/sendCode")
    Observable<API<Object>> sendCode(@Body Map<Object, Object> map);


    /**
     * 上传文件
     */
    @Multipart
    @POST(BASE_URL + "general/upload")
    Observable<API<String>> uploadFile(@Part MultipartBody.Part file);

    /**
     * 上传图片  (多张)
     */
    @Multipart
    @POST(BASE_URL + "general/upload/batch")
    Observable<API<List<String>>> uploadBatchFile(@PartMap Map<String, RequestBody> files);


//    @GET(BASE_URL + "wxarticle/chapters/json")
//    Observable<API<List<Cha>>> getCha();


}

