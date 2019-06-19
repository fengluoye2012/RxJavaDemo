package com.rxjava_demo;

import android.util.Log;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;

public class ConversionActivity extends BaseActivity {

    @Override
    protected List<String> initData() {

        List<String> list = new ArrayList<>();
        list.add("map");
        list.add("floatMap");
        list.add("buffer");
        list.add("groupBy");
        return list;
    }

    @Override
    protected void iniListeners() {
        mainAdapter.setItemViewClick(new MainAdapter.ItemViewClick() {
            @Override
            public void itemClick(int pos) {
                switch (pos) {
                    case 0:
                        map();
                        break;
                    case 1:
                        flatMap();
                        break;
                    case 2:
                        buffer();
                        break;
                    case 3:
                        groupBy();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * map操作符，通过指定一个Func，将Observable转换为另一个Observable对象并发送
     * <p>
     * 类似与转换器，将数据由一种格式转换为另一种需要的格式；(可以是同类型的之间的转换)
     */
    private void map() {
        //将inter转换为String；
        Disposable map = Observable.just(1).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer s) throws Exception {
                return "我是Number:" + s;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("map", s);
            }
        });
    }


    /**
     * flatMap 和 Map的区别；todo
     */
    private void flatMap() {
        Disposable flatMap = Observable.just(1).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                return Observable.just("flatMap::" + integer);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("flatMap", s);
            }
        });
    }

    /**
     * buffer操作符，将原有Observable转换为一个新的Observable，这个新的Observable每次发送一组值，而不是一个个进行发送；
     * 按顺序分组；
     */
    private void buffer() {
        //将原Observable，按照每组三个分组，发送；
        final Disposable buffer = Observable.fromArray(1, 2, 3, 4, 5, 6).buffer(3).subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) throws Exception {
                Log.e("buffer", "integers的size：：" + integers.size());
                for (Integer integer : integers) {
                    Log.e("buffer", integer + "");
                }
            }
        });
    }

    /**
     * groupBy操作符，可以做分组操作
     * 按条件分组；
     * key是由groupBy()来决定的；
     */
    private void groupBy() {
        Disposable subscribe = Observable.range(1, 10).groupBy(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Exception {
                return integer % 2;
            }
        }).subscribe(new Consumer<GroupedObservable<Integer, Integer>>() {
            @Override
            public void accept(final GroupedObservable<Integer, Integer> groupedObservable) throws Exception {
                Disposable subscribe1 = groupedObservable.subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("groupBy", "key::" + groupedObservable.getKey() + ",,::" + integer);
                    }
                });

            }
        });
    }
}
