package com.kobe.binderstudy.section3;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.kobe.binderstudy.section3.aidl.MyAidlBinder;


public class MyAidlService extends Service {

    private IBinder aidlBinder = new AidlBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return aidlBinder;
    }

    public class AidlBinder extends MyAidlBinder {

        @Override
        public int add(int a, int b) {
            //server端的binder线程池中
            Log.v("kobe","server端的binder线程池中", new Exception("kobe"));
            return a + b;
        }
    }
}
