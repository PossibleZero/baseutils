package com.example.luckyzhang.baseutils.retrofit.utils;

import com.example.luckyzhang.baseutils.retrofit.bean.Book;
import com.example.luckyzhang.baseutils.retrofit.bean.BookLists;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by luckyzhang on 2017/5/4.
 * 请求接口
 */

public interface InterService {

    //====================== 设置get方法 =========================
    //通过GET方法来进行设置
    @GET("book/search")
    Call<BookLists> getSeachBooks(@Query("q") String name, @Query("tag") String tag, @Query("start") int start,
                                  @Query("count") int count);

    @GET("book/search")
    Call<BookLists> getQueryMapBooks(@QueryMap Map<String, String> options);

    @GET("book/{id}")
    Call<Book> getPathBooks(@Path("id") String id);

    //====================== 设置post方法 =========================
    // 测试的demo上的
    //post方法添加每一个field
    @FormUrlEncoded
    @POST("user/edit")
    Call<Book> updateUser(@Field("first_name") String first, @Field("last_name") String last);

    //以一个对象的形式添加
    @POST("users/new")
    Call<Book> createUser(@Body Book book);

    //通过post方法,通过Map形式添加
    @FormUrlEncoded
    @POST("user/edit")
    Call<Book> addUser(@FieldMap Map<String, String> fields);

    //====================== 文件上传 =========================
    //上传单个文件
    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadFile(@Part("description") ResponseBody description, @Part MultipartBody.Part file);

    //上传多个文件
    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadMutipleFiles(@Part("description") RequestBody description,
                                          @Part MultipartBody.Part file1, @Part MultipartBody.Part file2
    );


    //====================== 设置header =========================
    //添加header的方法
    //第一种方法
    @Headers("Cache-Control: max-age=640000")
    @GET("book/search")
    Call<BookLists> setHeaderBooks1(@Query("q") String name,
                                    @Query("tag") String tag, @Query("start") int start,
                                    @Query("count") int count);


    //第二种方法
    @Headers({
            "Accept: application/vnd.yourapi.v1.full+json",
            "User-Agent: Your-App-Name"
    })
    @GET("book/search")
    Call<BookLists> setHeaderBooks2(@Query("q") String name,
                                    @Query("tag") String tag, @Query("start") int start,
                                    @Query("count") int count);

    //第三种方法是通过拦截器Interceptor,详情请看RetrofitUtils中的HeaderInterceptor
}
