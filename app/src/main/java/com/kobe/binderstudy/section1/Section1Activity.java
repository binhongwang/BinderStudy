package com.kobe.binderstudy.section1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kobe.binderstudy.FirstAidlInterface;
import com.kobe.binderstudy.R;

public class Section1Activity extends AppCompatActivity implements View.OnClickListener {

    private FirstAidlInterface localService;
    private FirstAidlInterface remoteService;

    private Button mBtn1;
    private Button mBtn2;
    private TextView mTxtResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section1);

        mBtn1 = (Button) findViewById(R.id.btn_1);
        mBtn2 = (Button) findViewById(R.id.btn_2);
        mTxtResult = (TextView) findViewById(R.id.txt_result);
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);


        //绑定LocalService1
        Intent intent = new Intent(this, LocalService1.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

                localService = FirstAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);

        //绑定RemoteService1
        Intent intent2 = new Intent(this, RemoteService1.class);
        bindService(intent2, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

                remoteService = FirstAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_1) {
            try {
                int a = 3;
                int b = 4;
                int result = localService.add(a, b);
                Log.v("kobe", "localService" + localService, new Exception("kobe"));
                mTxtResult.setText(a + "+" + b + "=" + result);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (v.getId() == R.id.btn_2) {
            try {
                int a = 5;
                int b = 6;
                int result = remoteService.add(a, b);
                Log.v("kobe", "remoteService " + remoteService, new Exception("kobe"));
                mTxtResult.setText(a + "+" + b + "=" + result);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
