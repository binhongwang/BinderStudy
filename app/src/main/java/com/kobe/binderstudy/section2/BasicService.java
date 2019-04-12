package com.kobe.binderstudy.section2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public class BasicService extends Service {

    private MyBasicBinder myBasicBinder = new MyBasicBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return myBasicBinder;
    }


    public class MyBasicBinder extends Binder {
        /*
        Client
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
         */

        // server
        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if(code == 1) {
                int a = data.readInt();
                int b = data.readInt();
                reply.writeInt(a + b);
                return true;
            }
            return super.onTransact(code, data, reply, flags);
        }
    }
}
