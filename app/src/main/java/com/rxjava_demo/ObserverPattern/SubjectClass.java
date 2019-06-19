package com.rxjava_demo.ObserverPattern;

import android.util.ArrayMap;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.Set;

/**
 * 被观察者
 */
public class SubjectClass {

    private static final String TAG = SubjectClass.class.getSimpleName();

    private static ArrayMap<String, WeakReference<NotifyChangeClass>> map = new ArrayMap<>();

    public static void register(NotifyChangeClass object) {
        if (map.containsKey(object.getClass().getName())) {
            Log.e(TAG, "不能重复注册");
            return;
        }

        WeakReference<NotifyChangeClass> weakReference = new WeakReference<>(object);
        map.put(object.getClass().getName(), weakReference);
    }


    private void notifyObserver() {
        Set<String> strings = map.keySet();
        for (String str : strings) {
            WeakReference<NotifyChangeClass> weakReference = map.get(str);
            if (weakReference == null) {
                return;
            }

            NotifyChangeClass notifyChangeClass = weakReference.get();
            notifyChangeClass.notifyChange();
        }
    }

    public static void unRegister(NotifyChangeClass obj) {
        map.remove(obj.getClass().getName());
    }
}
