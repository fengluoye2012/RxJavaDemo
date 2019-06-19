package com.rxjava_demo;

public class BaseResponse<T> {

    //0 成功 1 失败；
    public int status;
    public T content;
    public String msg;
}
