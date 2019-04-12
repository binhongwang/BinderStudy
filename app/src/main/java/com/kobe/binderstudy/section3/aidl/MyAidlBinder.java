package com.kobe.binderstudy.section3.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;


public class MyAidlBinder extends Binder implements IMyAidlInterface {
    public static final int ADD = 1;

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply,
                                 int flags) throws RemoteException {
        if (code == ADD) {
            int a = data.readInt();
            int b = data.readInt();
            int result = add(a, b);
            reply.writeInt(result);
            return true;
        }
        return super.onTransact(code, data, reply, flags);
    }

    @Override
    public int add(int a, int b) {
        //重写这个函数
        return 0;
    }

    public static IMyAidlInterface asInterface(IBinder binder) {
        if (binder instanceof Binder) {
            return (IMyAidlInterface) binder;
        } else {
            return new MyAidlBinderProxy(binder);
        }
    }
}
