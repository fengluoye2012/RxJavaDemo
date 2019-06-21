package com.rxjava_demo;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 线程调度器
 */
public class SchedulersActivity extends BaseActivity {

    @Override
    protected List<String> initData() {
        List<String> list = new ArrayList<>();
        list.add("Scheduler");
        list.add("配合网络请求");
        return list;
    }

    @Override
    protected void iniListeners() {

        mainAdapter.setItemViewClick(new MainAdapter.ItemViewClick() {
            @Override
            public void itemClick(int pos) {

                switch (pos) {
                    case 0:
                        scheduler();
                        break;

                    case 1:
                        req();
                        break;
                }

            }
        });
    }


    /**
     * 多次指定线程，只有第一次有效；
     */
    public void scheduler() {

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "Name:::" + Thread.currentThread().getName() + ",,ID ::" + Thread.currentThread().getId());
                Log.e(TAG, s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.e(TAG, "Name:::" + Thread.currentThread().getName() + ",,ID ::" + Thread.currentThread().getId());
                Thread.sleep(3000);
                emitter.onNext("我休息三秒钟后才发送");
            }
        }).subscribeOn(Schedulers.io())
//                .subscribeOn(Schedulers.computation())
//                .subscribeOn(Schedulers.single())
//                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    private void req() {
        Observer observer = new Observer<BaseResponse<ContentBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponse<ContentBean> translation) {
                //更新UI；
                Log.e(TAG, translation.status + ",," + translation.content.from);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        ReqUtil.getInstance().getReq(observer);
    }
}
