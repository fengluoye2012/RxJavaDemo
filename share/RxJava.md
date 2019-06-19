#### RxJava分享

##### 1、了解RxJava 

先来了解下[ReactiveX官网]([http://reactivex.io/](http://reactivex.io/)) 

ReactiveX是一个库，用于通过使用可观察序列来**编写异步和基于事件的程序**。

它扩展了**观察者模式**以支持数据和/或事件序列，并添加了允许您以声明方式组合序列的运算符，同时抽象出对低级线程，同步，线程安全，并发数据结构和非线程等问题的关注。阻止I / O；

![](/Users/mac/Desktop/APPTest/RxJavaDemo/share/ReactiveX.png)



##### RxJava的优势

**在Android的SDK中，给开发者提供的用于异步操作的原生内容有AsyncTask和Handler。**对于简单的异步请求来说，使用Android原生的AsyncTask和Handler即可满足需求，但是对于复杂的业务逻辑而言，依然使用AsyncTask和Handler会导致代码结构混乱，代码的可读性非常差。
 **但是RxJava的异步操作是基于观察者模式实现的，在越来越复杂的业务逻辑中，RxJava依旧可以保持简洁。**



##### 2、RxJva 操作符

RxJava操作符的分类

![](/Users/mac/Desktop/APPTest/RxJavaDemo/share/RxJava操作符.png)

##### 3、主要常用操作符







##### 4、线程切换

- Schedulers.io() ：io操作线程  用于网络请求、读写文件等io密集型操作；
- Schedulers.computation()：CPU计算操作线程   大量计算操作；
- Schedulers.newThread()：常规新线程  耗时等操作；
- AndroidSchedulers.mainThread()   Android主线程   操作UI；



##### 4、问题

- 观察者模式在使用的过程中需要注意什么？？



- 观察者模式和发布-订阅者模式 有区别吗？

观察者模式和发布订阅模式最大的区别就是发布订阅模式有个事件调度中心。

![](/Users/mac/Desktop/APPTest/RxJavaDemo/share/观察者和发布订阅者的区别.png)

Android中的广播，EventBus都采用的是发布订阅模式；



- RxJava是如何切换线程的？

采用Handler来切换线程；

