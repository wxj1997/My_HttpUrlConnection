package com.example.wxj.my_httpurlconnection.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by wxj on 2018/4/18.
 */

public class HttpUtil {
    public static void requestGet(final CallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection huc = null;
                try {
                    URL url = new URL("http://10.0.2.2/get.php");
                    huc = (HttpURLConnection) url.openConnection();
                    huc.setRequestMethod("GET");
                    huc.connect();
                    if (huc.getResponseCode() == 200) {
                        /*从服务器获取流数据*/
                        InputStream is = huc.getInputStream();
                        String str = Stream2String(is);
                        callBack.onResponse(str);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (huc != null) {
                        huc.disconnect();
                    }
                }
            }
        }).start();
    }

    private static String Stream2String(InputStream is) {
        StringBuilder sb = new StringBuilder();
        byte[] buffer = new byte[512];
        int hasRead = -1;
        try {
            while ((hasRead = is.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, hasRead));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
