package com.rxjava_demo;

import android.util.Log;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
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
     * groupBy操作符，可以做分组操作
     */
    private void groupBy() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");

        Observable.range(1, 10).groupBy(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Exception {
                return integer % 3;
            }
        }).subscribe(new Consumer<GroupedObservable<Integer, Integer>>() {
            @Override
            public void accept(GroupedObservable<Integer, Integer> groupedObservable) throws Exception {
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
        Observable.just(1).map(new Function<Integer, String>() {
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

    private void flatMap() {
        Observable.fromArray(1, 2, 3, 4).flatMap(new Function<Integer, ObservableSource<String>>() {
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
     * buffer操作符，将原有Observable转换为一个新的Observable，这个新的Observable每次发送一组值，而不是一个个进行发送
     */
    private void buffer() {
        //将原Observable，按照每组三个分组，发送；
        Observable.fromArray(1, 2, 3, 4, 5, 6).buffer(3).subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) throws Exception {
                Log.e("buffer", "integers的size：：" + integers.size());
            }
        });
    }
}
