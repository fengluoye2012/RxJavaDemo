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
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class OtherActivity extends BaseActivity {

    @Override
    protected List<String> initData() {

        List<String> list = new ArrayList<>();
        list.add("过滤：：filter");
        list.add("过滤：：distinct");
        return list;
    }

    @Override
    protected void iniListeners() {
        mainAdapter.setItemViewClick(new MainAdapter.ItemViewClick() {
            @Override
            public void itemClick(int pos) {
                switch (pos) {
                    case 0:
                        filter();
                        break;

                    case 1:
                        distinct();
                        break;

                    default:
                        break;
                }
            }
        });
    }

    /**
     * distinct操作符，用于Observable发送的元素的去重
     * distinct操作符只允许还没有发射过的数据项通过。
     */
    private void distinct() {
        Observable.fromArray(1, 2, 3, 2, 1, 5).distinct().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e("distinct", "distinct：：" + integer);
            }
        });
    }

    /**
     * filter过滤操作符，对Observable发送的内容根据自定义的规则进行过滤
     */
    private void filter() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(5);
        list.add(3);
        list.add(30);
        Observable.fromIterable(list).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer % 2 == 0;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e("filter", "我是：：" + integer);
            }
        });

        Flowable.create(new FlowableOnSubscribe<Object>() {
            @Override
            public void subscribe(FlowableEmitter<Object> emitter) throws Exception {
               Thread.sleep(3000);
               emitter.onNext("我休息三秒钟后才发送");
            }
        }, BackpressureStrategy.BUFFER).observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

}
