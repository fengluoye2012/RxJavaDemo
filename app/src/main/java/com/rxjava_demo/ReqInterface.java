package com.rxjava_demo;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ReqInterface {

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
    Observable<BaseResponse<ContentBean>> getCall();


}
