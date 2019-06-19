package com.rxjava_demo;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReqUtil {

    private String TAG = ReqUtil.class.getSimpleName();

    private static ReqUtil instance = new ReqUtil();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://fy.iciba.com/") //设置 网络请求 Url
            .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
            .build();


    private ReqUtil() {
    }

    public static ReqUtil getInstance() {
        return instance;
    }

    public void getReq(Observer observer) {
        // 步骤5：创建 网络请求接口 的实例
        ReqInterface request = retrofit.create(ReqInterface.class);
        // 步骤6：采用Observable<...>形式 对 网络请求 进行封装
        Observable<BaseResponse<ContentBean>> observable = request.getCall();
        sendReq(observable, observer);
    }

    //泛形；
    private <T> void sendReq(final Observable<BaseResponse<T>> observable, final Observer observer) {
        // 步骤7：发送网络请求
        observable.subscribeOn(Schedulers.io())               // 在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  // 回到主线程 处理请求结果
                .subscribe(new Observer<BaseResponse<T>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(final BaseResponse<T> result) {
                        Log.e(TAG, "请求成功");
                        sendData(observer, result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        BaseResponse<String> error = new BaseResponse<>();
                        error.status = 1;
                        error.msg="网络请求错误";

                        sendData(observer,error);
                        Log.d(TAG, "请求失败");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "请求成功");
                    }
                });
    }

    private <T> void sendData(final Observer observer, final BaseResponse<T> result) {
        // 步骤8：对返回的数据进行处理
        Observable.create(new ObservableOnSubscribe<BaseResponse<T>>() {
            @Override
            public void subscribe(ObservableEmitter<BaseResponse<T>> emitter) {
                emitter.onNext(result);
            }
        }).subscribe(observer);
    }


}
