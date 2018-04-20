package com.example.wxj.my_httpurlconnection;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wxj.my_httpurlconnection.net.CallBack;
import com.example.wxj.my_httpurlconnection.net.HttpUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton;
    private TextView mTextView;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDate();
    }

    private void initDate() {
        mButton.setOnClickListener(this);
        mHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mTextView.setText((String)msg.obj);
            }
        };
    }

    private void initView() {
        mButton=(Button)findViewById(R.id.btn_get);
        mTextView=(TextView)findViewById(R.id.tv_show_msg);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_get:
                showGetData();
                break;
        }

    }

    private void showGetData() {
        HttpUtil.requestGet(new CallBack<String>() {
            @Override
            public void onResponse(String response) {
                Message msg=mHandler.obtainMessage();
                msg.obj=response;
                mHandler.sendMessage(msg);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}
