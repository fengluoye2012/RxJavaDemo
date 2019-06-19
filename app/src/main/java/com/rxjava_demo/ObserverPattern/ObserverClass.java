package com.rxjava_demo.ObserverPattern;


/**
 * 观察者
 */
public class ObserverClass implements NotifyChangeClass {

    public ObserverClass() {
        SubjectClass.register(this);
    }

    @Override
    public void notifyChange() {

    }


    public void onDestroy() {
        SubjectClass.unRegister(this);
    }
}
