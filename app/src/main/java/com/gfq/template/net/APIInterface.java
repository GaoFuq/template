package com.gfq.template.net;



import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
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

    public static final String BASE_URL = "";
    //分享下载APP
    public static final String SHARE_URL = BASE_URL + "";

                        /**
                         * *********************************************
                         *                                             *
    **********************               公共接口 开始                  *************************
                         *                                             *
                         * *********************************************/
    /**
     * 获取验证码
     *
     * @param map scene 场景:1.注册 2.忘记密码 ;
     *            phone 手机号码 ；
     */
    @POST(BASE_URL + "api/handle/sendCode")
    Observable<API<Object>> sendCode(@Body Map<Object, Object> map);


    /**
     * 上传图片  (单张)
     */
    @Multipart
    @POST(BASE_URL + "api/upload/uploadImg")
    Observable<API<List<String>>> uploadImg( @Part MultipartBody.Part file);

    /**
     * 上传图片  (多张)
     */
    @Multipart
    @POST(BASE_URL + "api/upload/uploadImg?scene=release")
    Observable<API<List<String>>> uploadImgs(@PartMap Map<String, RequestBody> files);



                        /**
                        * *********************************************
                        *              公共接口 结束                   *
    ********************************************************************************************
                        *               用户端 开始                    *
                        * *********************************************/




                        /**
                        * *********************************************
                        *               用户端 结束                    *
    *******************************************************************************************
                        *               车主端 开始                    *
                        * *********************************************/






                        /**
                        * *********************************************
                        *                                             *
    *********************              车主端  结束                    *************************
                        *                                             *
                        * *********************************************/


}

