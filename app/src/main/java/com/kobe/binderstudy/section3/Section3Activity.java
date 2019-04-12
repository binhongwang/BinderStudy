package com.kobe.binderstudy.section3;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kobe.binderstudy.R;
import com.kobe.binderstudy.section2.BasicService;
import com.kobe.binderstudy.section3.aidl.IMyAidlInterface;
import com.kobe.binderstudy.section3.aidl.MyAidlBinder;

public class Section3Activity extends AppCompatActivity implements View.OnClickListener {

    private IMyAidlInterface myAidlInterface;

    private Button mBtn1;
    private TextView mTxtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section3);

        mBtn1 = (Button) findViewById(R.id.btn_1);
        mTxtResult = (TextView) findViewById(R.id.txt_result);
        mBtn1.setOnClickListener(this);

        //绑定LocalService1
        Intent intent = new Intent(this, MyAidlService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myAidlInterface = MyAidlBinder.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        int a = 10;
        int b = 11;
        //client 端进程中的主线程
        int result = myAidlInterface.add(a, b);
        Log.v("kobe", "myAidlInterface" + myAidlInterface, new Exception("kobe"));
        mTxtResult.setText(a + "+" + b + "=" + result);
    }
}
