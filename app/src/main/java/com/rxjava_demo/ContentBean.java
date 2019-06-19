package com.rxjava_demo;

public class ContentBean {
    public String from;
    public String to;
    public String vendor;
    public String out;
    public int errNo;

    @Override
    public String toString() {
        return "ContentBean{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", vendor='" + vendor + '\'' +
                ", out='" + out + '\'' +
                ", errNo=" + errNo +
                '}';
    }
}
