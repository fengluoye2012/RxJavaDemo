package com.rxjava_demo;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class CreateActivity extends BaseActivity {


    @Override
    protected List<String> initData() {
        List<String> list = new ArrayList<>();
        list.add("create");
        list.add("from");
        list.add("forIterable");
        list.add("range");
        list.add("timer");
        list.add("interval");
        list.add("intervalRange");
        list.add("repeat");
        return list;
    }

    @Override
    protected void iniListeners() {

        mainAdapter.setItemViewClick(new MainAdapter.ItemViewClick() {
            @Override
            public void itemClick(int pos) {
                switch (pos) {
                    case 0:
                        create();
                        break;
                    case 1:
                        from();
                        break;
                    case 2:
                        forIterable();
                        break;

                    case 3:
                        range();
                        break;

                    case 4:
                        timer();

                        break;

                    case 5:
                        interval();

                        break;

                    case 6:
                        intervalRange();
                        break;

                    case 7:
                        repeat();
                        break;
                    default:
                        break;

                }
            }
        });
    }


    private void create() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("feng");
                emitter.onNext("luo");
                emitter.onNext("ye");
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        });
    }

    private void from() {
        //不定长度的类型的参数；
        Observable.fromArray(1, 2, 3, 4, 5, 6, 7).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "接受：：" + integer);
            }
        });
    }

    private void forIterable() {
        List<String> list = new ArrayList<>();
        list.add("tom");
        list.add("Jhon");

        Observable.fromIterable(list).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, s);
            }
        });
    }


    /**
     * range操作符发射一个范围内的有序整数序列，并且我们可以指定范围的起始和长度
     */
    private void range() {
        //从1开始,到start+count结束；
        Observable.range(1, 5).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "forIterable::" + integer);
            }
        });
    }

    //延时发送
    private void timer() {
        Observable.timer(1, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.e(TAG, "timer::" + aLong);
            }
        });
    }


    /**
     * 在initialDelay 之后从0L开发，在每period 时间间隔之后不断增加；
     */
    private void interval() {
        //延后1s后发送0，每隔2s之后发送一次；
        Observable.interval(1, 2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.e(TAG, "interval::" + aLong);
            }
        });
    }

    private void intervalRange() {
        Observable.intervalRange(1, 3, 1, 5, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.e(TAG, "延时消息：：" + aLong);
            }
        });
    }

    //
    private void repeat() {
        Flowable.range(0, 3).repeat().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "repeat::" + integer);
            }
        });
    }
}
