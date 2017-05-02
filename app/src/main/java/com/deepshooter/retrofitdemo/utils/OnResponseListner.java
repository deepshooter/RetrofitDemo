package com.deepshooter.retrofitdemo.utils;

/**
 * Created by Avinash on 5/2/2017.
 */
public interface OnResponseListner<T> {
    void onResponse(T response, WebServices.ApiType URL, boolean isSucces);
}
