package com.bytedance.practice5;


import com.bytedance.practice5.model.UploadResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface IApi {

    /**
     *
     * @param studentId 学生id
     * @param extraValue 额外值
     * @param from 留言学生
     * @param to 对哪个学生留言
     * @param content 留言内容
     * @param image 图片
     * @param token token
     * @return
     */
    //TODO 4
    // 补全所有注解
    @Multipart
    @POST("messages")
    Call<UploadResponse> submitMessage(@Query("student_id") String studentId,
                                     @Query("extra_value") String extraValue,
                                      @Part("from")  String from,
                                       @Part("to")  String to,
                                       @Part("content")  String content,
                                     @Part MultipartBody.Part image,
                                     @Header("token") String token);
}
