package com.deepshooter.retrofitdemo.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.IntentService;
import android.content.Context;
import android.util.Log;

import com.deepshooter.retrofitdemo.customs.ConnectionDetector;
import com.deepshooter.retrofitdemo.ui.forgotpassword.ForgotPassword;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Avinash on 5/2/2017.
 */
public class WebServices<T> {

    private T t;
    Call<T> call = null;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    ApiType apiTypeVariable;
    Context context;
    OnResponseListner<T> onResponseListner;
    android.app.ProgressDialog pdLoading;
    private static OkHttpClient.Builder builder;

    File httpCacheDirectory;
    Cache cache;

    public enum ApiType {

      getNewsList , getNewsDetails , getNewsListCache ,postForgotPassword

    }
    public final static String PublicDevService=" http://xx.xxx.xx.xxx/api/";
    public final static String PublicService = PublicDevService;

    public WebServices(OnResponseListner<T> onResponseListner) {
        if (onResponseListner instanceof Activity) {
            this.context = (Context) onResponseListner;
        } else if (onResponseListner instanceof IntentService) {
            this.context = (Context) onResponseListner;
        } else if (onResponseListner instanceof android.app.DialogFragment) {
            android.app.DialogFragment dialogFragment = (android.app.DialogFragment) onResponseListner;
            this.context = dialogFragment.getActivity();
        } else if(onResponseListner instanceof android.support.v4.app.Fragment) {
            android.support.v4.app.Fragment fragment = (android.support.v4.app.Fragment) onResponseListner;
            this.context = fragment.getActivity();
        }else{
            Fragment fragment = (Fragment) onResponseListner;
            this.context = fragment.getActivity();
        }
        this.onResponseListner= onResponseListner;


        httpCacheDirectory = new File( context.getCacheDir(), "responses");
        int cacheSize = 5 * 1024 * 1024; // 5 MiB
        cache = new Cache(httpCacheDirectory, cacheSize);

        builder = getHttpClient();
    }

    Interceptor CACHE_CONTROL_INTERCEPTOR_ONEMIN = new Interceptor() {
        @Override public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Response originalResponse = chain.proceed(chain.request());



            if (new ConnectionDetector(context).isConnectingToInternet()) {
                int maxAge = 60*60; // read from cache for 1 minute
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60*60*24 ; // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };



    public OkHttpClient.Builder getHttpClient() {
        if (builder == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            client.addInterceptor(loggingInterceptor);
            client.writeTimeout(60000, TimeUnit.MILLISECONDS);
            client.readTimeout(60000,TimeUnit.MILLISECONDS);
            client.connectTimeout(60000,TimeUnit.MILLISECONDS);
            return client;
        }
        return builder;
    }

    /*For Deleting Cache*/
    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }


    public void getNewsList(String api, ApiType apiTypes, String lang) {

        apiTypeVariable = apiTypes;

        try {
            pdLoading = ProgressDialog.getInstance(context);
            pdLoading.show();
        } catch (Exception e) {
            // e.printStackTrace();
        }


        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getNewsListCall(lang);
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();

                if (pdLoading.isShowing())
                    pdLoading.cancel();

                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {

                if (pdLoading.isShowing())
                    pdLoading.cancel();

                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }


    public void getNewsDetails(String api, ApiType apiTypes , String Id) {

        apiTypeVariable = apiTypes;

        try {
            pdLoading = ProgressDialog.getInstance(context);
            pdLoading.show();
        } catch (Exception e) {
            // e.printStackTrace();
        }



        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getNewsDetailsCall(Id);
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();


                if (pdLoading.isShowing())
                    pdLoading.cancel();

                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {

                if (pdLoading.isShowing())
                    pdLoading.cancel();

                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }



    public void getNewsListCache(String api, ApiType apiTypes, String lang) {

        apiTypeVariable = apiTypes;

        try {
            pdLoading = ProgressDialog.getInstance(context);
            pdLoading.show();
        } catch (Exception e) {
            // e.printStackTrace();
        }

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(CACHE_CONTROL_INTERCEPTOR_ONEMIN)
                .cache(cache).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(client).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getNewsListCall(lang);
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();

                if (pdLoading.isShowing())
                    pdLoading.cancel();

                onResponseListner.onResponse(t, apiTypeVariable, true);

                /*For Deleting Cache*/
                //File httpCacheDirectory = new File( context.getCacheDir(), "responses");
                //deleteDir(httpCacheDirectory);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {

                if (pdLoading.isShowing())
                    pdLoading.cancel();

                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }




    public void forgotPassword(String api, ApiType apiTypes, String lang, ForgotPassword forgotPassword) {
        apiTypeVariable = apiTypes;

        try {
            pdLoading = ProgressDialog.getInstance(context);
            pdLoading.show();
        } catch (Exception e) {
            // e.printStackTrace();
        }

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getForgotPasswordResponseCall(lang,forgotPassword);
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.d(apiTypeVariable.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

}
