package com.rxjava_demo;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class OtherActivity extends BaseActivity {

    @Override
    protected List<String> initData() {

        List<String> list = new ArrayList<>();
        list.add("过滤：：filter");
        list.add("过滤：：distinct");
        list.add("辅助：：delay");
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

                    case 2:
                        delay();
                        break;

                    default:
                        break;
                }
            }
        });
    }

    //delay操作符可以让源Observable对象发送数据之前暂停一段制定的时间；和Timer类似；
    private void delay() {
        Disposable subscribe = Observable.fromArray("Hello", "Word", "Android")
                .delay(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String string) throws Exception {
                        Log.e("delay", string);
                    }
                });
    }

    /**
     * distinct操作符，用于Observable发送的元素的去重
     * distinct操作符只允许还没有发射过的数据项通过。
     */
    private void distinct() {
        Disposable distinct = Observable.fromArray(1, 2, 3, 2, 1, 5)
                .distinct()
                .subscribe(new Consumer<Integer>() {
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
        Disposable filter = Observable.fromIterable(list).filter(new Predicate<Integer>() {
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
    }

}
