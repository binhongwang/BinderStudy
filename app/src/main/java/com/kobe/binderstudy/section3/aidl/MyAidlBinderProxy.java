package com.kobe.binderstudy.section3.aidl;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public class MyAidlBinderProxy implements IMyAidlInterface {

    private IBinder remoteService;

    public MyAidlBinderProxy(IBinder remoteService) {
        this.remoteService = remoteService;
    }

    @Override
    public int add(int a, int b) {
        try {
            Parcel in = Parcel.obtain();
            Parcel out = Parcel.obtain();
            in.writeInt(a);
            in.writeInt(b);
            remoteService.transact(MyAidlBinder.ADD, in, out, 0);
            int result = out.readInt();
            return result;
        } catch (RemoteException exception) {

        }
        return 0;
    }
}
