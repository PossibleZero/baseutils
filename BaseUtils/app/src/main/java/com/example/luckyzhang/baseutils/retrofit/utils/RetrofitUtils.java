package com.example.luckyzhang.baseutils.retrofit.utils;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aaron on 16/5/16.
 * RetrofitUtils帮助类
 * ps:HttpClient的配置是可以设置的
 */
public class RetrofitUtils {

    private volatile static Retrofit singleton;
    private static String baseUrl = "https://api.douban.com/v2/"; //普通请求
    private static OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
//    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();


    //    private RetrofitUtils() {
//
////        File cacheFile = new File(LagouApplication.getInstance().getCacheDir(), "cache");
////        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
//
////        OkHttpClient.Builder builder = okHttpClient.newBuilder();
//        okHttpClient.readTimeout(5000, TimeUnit.MILLISECONDS)
//                .connectTimeout(5000, TimeUnit.MILLISECONDS);
//
////        okHttpClient.sslSocketFactory(createBadSslSocketFactory());
//        try {
//            okHttpClient.sslSocketFactory(SSLFactoryUtil.getSSLSocketFactory(new InputStream[]{
//                    LagouApplication.getInstance().getAssets().open("partjob.cer")
//            }, null, null));
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        okHttpClient.interceptors().add(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
////                chain.request().newBuilder().addHeader();
//                return null;
//            }
//        });
//    }
    private RetrofitUtils() {
    }


    /**
     * 初始化retrofit一些配置
     */
    public static void init() {
        okHttpClient.readTimeout(5000, TimeUnit.MILLISECONDS)
                .connectTimeout(5000, TimeUnit.MILLISECONDS);
        try {
            okHttpClient.sslSocketFactory(SSLFactoryUtil.getSSLSocketFactory(
                    /* 用来设置cert的认证证书 new InputStream[]{LagouApplication.getInstance().getAssets().open("partjob.cer")}*/
                    null, null, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* 添加拦截器 okHttpClient.addInterceptor(new HeaderInterceptor());*/
    }

    /**
     * 测试拦截器
     */
    public static class HeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl httpUrl = request.url().newBuilder()
                    .addQueryParameter("token", "tokenValue")
                    .build();

            request = request.newBuilder()
                    .url(httpUrl)
                    //添加header
                    .header("User-Agent", "Your-App-Name")
                    .header("Accept", "application/vnd.yourapi.v1.full+json")
                    .build();

            return chain.proceed(request);
        }
    }

    public static <T> T createApi(Context context, Class<T> clazz) {
        if (singleton == null) {
            synchronized (RetrofitUtils.class) {
                if (singleton == null) {
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(baseUrl)
                            .client(okHttpClient.build())
                            .addConverterFactory(gsonConverterFactory)//设置远程地址
                            /* 用来设置rxjava的 .addCallAdapterFactory(rxJavaCallAdapterFactory)*/;
                    singleton = builder.build();
                }
            }
        }
        return singleton.create(clazz);
    }


}
