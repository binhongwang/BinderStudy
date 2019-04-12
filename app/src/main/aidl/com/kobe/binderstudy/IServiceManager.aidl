// IServiceManager.aidl
package com.kobe.binderstudy;

// Declare any non-default types here with import statements

interface IServiceManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    IBinder getService(String name);
}
