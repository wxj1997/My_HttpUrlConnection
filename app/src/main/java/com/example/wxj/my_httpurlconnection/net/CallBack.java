package com.example.wxj.my_httpurlconnection.net;

/**
 * Created by wxj on 2018/4/18.
 */

public interface CallBack<T> {
    public void onResponse(T response);
    public void onFailed(Exception e);
}
