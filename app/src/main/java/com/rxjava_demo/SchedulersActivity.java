package com.rxjava_demo;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 线程调度器
 */
public class SchedulersActivity extends BaseActivity {

    @Override
    protected List<String> initData() {
        List<String> list = new ArrayList<>();
        list.add("Scheduler");
        return list;
    }

    @Override
    protected void iniListeners() {

        mainAdapter.setItemViewClick(new MainAdapter.ItemViewClick() {
            @Override
            public void itemClick(int pos) {
                scheduler();
            }
        });
    }

    public void scheduler() {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                Thread.sleep(3000);
                emitter.onNext("我休息三秒钟后才发送");
            }
        }, BackpressureStrategy.BUFFER)
                .observeOn(Schedulers.io())
//                .observeOn(Schedulers.computation())
//                .observeOn(Schedulers.single())
//                .observeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, s);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");

                    }
                });
    }

}
