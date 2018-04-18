package com.example.wxj.my_httpurlconnection.net;

/**
 * Created by wxj on 2018/4/18.
 */

public interface CallBack {
    public void onResponse(String response);
    public void onFailed(Exception e);
}
