package com.kobe.binderstudy.section4;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.kobe.binderstudy.IAdd;
import com.kobe.binderstudy.IServiceManager;
import com.kobe.binderstudy.ISub;

import java.util.HashMap;

public class MyServiceManager extends Service {

    //实例化一个ServiceManager的服务端
    private ServiceManager mServiceManager = new ServiceManager();
    private HashMap<String, IBinder> mServiceList = new HashMap();



    //实例化两个Binder的服务端
    private AddService mAddService = new AddService();
    private SubService mSubService = new SubService();


    @Override
    public IBinder onBind(Intent intent) {
        if (mServiceList.size() == 0) {
            //将两个Binder的服务端加入到list中
            mServiceList.put("Add", mAddService);
            mServiceList.put("Sub", mSubService);
        }
        return mServiceManager;
    }

    //定义了三个Binder的服务端
    public class ServiceManager extends IServiceManager.Stub {
        @Override
        public IBinder getService(String name) {

            return mServiceList.get(name);
        }
    }


    public class AddService extends IAdd.Stub {
        @Override
        public int add(int a, int b) {
            return a + b;
        }
    }

    public class SubService extends ISub.Stub {
        @Override
        public int sub(int a, int b) {
            return a - b;
        }
    }

}
