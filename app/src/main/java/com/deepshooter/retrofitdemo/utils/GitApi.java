package com.deepshooter.retrofitdemo.utils;



import com.deepshooter.retrofitdemo.ui.forgotpassword.ForgotPassword;
import com.deepshooter.retrofitdemo.ui.getForgotPasswordResponse.GetForgotPasswordResponse;
import com.deepshooter.retrofitdemo.ui.getNewsDetails.GetNewsDetails;
import com.deepshooter.retrofitdemo.ui.getNewsList.GetNewsList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Avinash on 5/2/2017.
 */

public interface GitApi {


    @GET("news/list")
    Call<GetNewsList> getNewsListCall(@Header("lang") String lang);

    @GET("news/details/{id}")
    Call<GetNewsDetails> getNewsDetailsCall(@Path("id") String Id);


    @Headers({
            "Authorization:Bearer !@_api_@!",
            "Accept:application/json",

    })


    @POST("forget/password")
    Call<GetForgotPasswordResponse> getForgotPasswordResponseCall(@Header("lang") String lang , @Body ForgotPassword forgotPassword);

}
