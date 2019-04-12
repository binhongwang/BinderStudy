package com.kobe.binderstudy.section4;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kobe.binderstudy.IAdd;
import com.kobe.binderstudy.IServiceManager;
import com.kobe.binderstudy.ISub;
import com.kobe.binderstudy.R;
import com.kobe.binderstudy.section3.MyAidlService;
import com.kobe.binderstudy.section3.aidl.MyAidlBinder;

public class Section4Activity extends AppCompatActivity implements View.OnClickListener {

    private IServiceManager mServiceManager;

    private Button mBtn1;
    private Button mBtn2;
    private TextView mTxtResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section4);

        mBtn1 = (Button) findViewById(R.id.btn_1);
        mBtn2 = (Button) findViewById(R.id.btn_2);
        mTxtResult = (TextView) findViewById(R.id.txt_result);
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);

        //绑定LocalService1
        Intent intent = new Intent(this, MyServiceManager.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                //拿到了客户端
                mServiceManager = IServiceManager.Stub.asInterface(service);
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
                int a = 11;
                int b = 12;
                IAdd addService = IAdd.Stub.asInterface(mServiceManager.getService("Add"));
                int result = addService.add(a, b);
                mTxtResult.setText(a + "+" + b + "=" + result);
            } catch (RemoteException e) {

            }

        } else {
            try {
                int a = 11;
                int b = 12;
                ISub subService = ISub.Stub.asInterface(mServiceManager.getService("Sub"));
                int result = subService.sub(a, b);
                mTxtResult.setText(a + "-" + b + "=" + result);
            } catch (RemoteException e) {

            }

        }
    }
}
