package com.kobe.binderstudy.section1;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;

import com.kobe.binderstudy.FirstAidlInterface;

public class RemoteService1 extends Service {

    private Binder remoteBinder = new RemoteBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return remoteBinder;
    }

    public class RemoteBinder extends FirstAidlInterface.Stub {

        @Override
        public int add(int a, int b) {
            Log.v("kobe", "service add on pid " + Process.myPid(), new Exception("kobe"));
            return a + b;
        }
    }

}
