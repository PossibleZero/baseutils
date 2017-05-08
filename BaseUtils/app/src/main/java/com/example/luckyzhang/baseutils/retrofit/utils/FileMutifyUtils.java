package com.example.luckyzhang.baseutils.retrofit.utils;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by luckyzhang on 2017/5/8.
 * 文件上传的utils
 */

public class FileMutifyUtils {

    public static final String MULTIPART_FORM_DATA = "multipart/form-data";


    /**
     * 获取RequestBody的描述信息
     *
     * @param descriptionString
     * @return
     */
    @NonNull
    public static RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
    }

    /**
     * 获取MultipartBody.Part的路程
     *
     * @param context
     * @param partName
     * @param fileUri
     * @return
     */
    @NonNull
    public static MultipartBody.Part prepareFilePart(Context context, String partName, Uri fileUri) {
        String path = fileUri.getPath();
        File file = FileUtils.getFile(context, fileUri);

        // 为file建立RequestBody实例
        RequestBody requestFile =
                RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);

        // MultipartBody.Part借助文件名完成最终的上传
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

}
