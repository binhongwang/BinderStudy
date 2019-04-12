package com.kobe.binderstudy.section2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kobe.binderstudy.R;

public class Section2Activity extends AppCompatActivity implements View.OnClickListener {

    private IBinder basicBinder;

    private Button mBtn1;
    private TextView mTxtResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section2);

        mBtn1 = (Button) findViewById(R.id.btn_1);
        mTxtResult = (TextView) findViewById(R.id.txt_result);
        mBtn1.setOnClickListener(this);

        //绑定LocalService1
        Intent intent = new Intent(this, BasicService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

                basicBinder = service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {

        try {
            int a = 6;
            int b = 7;
            Parcel in = Parcel.obtain();
            in.writeInt(a);
            in.writeInt(b);
            Parcel out = Parcel.obtain();
            basicBinder.transact(1, in, out, 0);
            int result = out.readInt();
            Log.v("kobe", "basicBinder" + basicBinder, new Exception("kobe"));
            mTxtResult.setText(a + "+" + b + "=" + result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
