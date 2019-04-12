package com.kobe.binderstudy.section1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;

import com.kobe.binderstudy.FirstAidlInterface;

public class LocalService1 extends Service {

    private LocalBinder localBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class LocalBinder extends FirstAidlInterface.Stub {

        @Override
        public int add(int a, int b) {
            Log.v("kobe", "service add on pid " + Process.myPid(), new Exception("kobe"));
            return a + b;
        }
    }
}
