#### RxJava

##### 1 了解RxJava 

先来了解下[ReactiveX官网]([http://reactivex.io/](http://reactivex.io/)) 

ReactiveX是一个库，用于通过使用可观察序列来**编写异步和基于事件的程序**。

它扩展了**观察者模式**以支持数据和/或事件序列，并添加了允许您以声明方式组合序列的运算符，同时抽象出对低级线程，同步，线程安全，并发数据结构和非线程等问题的关注。阻止I / O；

![ReactiveX](/Users/fengluoye/Desktop/分享/ReactiveX.png)



##### RxJava的优势

**在Android的SDK中，给开发者提供的用于异步操作的原生内容有AsyncTask和Handler。**对于简单的异步请求来说，使用Android原生的AsyncTask和Handler即可满足需求，但是对于复杂的业务逻辑而言，依然使用AsyncTask和Handler会导致代码结构混乱，代码的可读性非常差。
 **但是RxJava的异步操作是基于观察者模式实现的，在越来越复杂的业务逻辑中，RxJava依旧可以保持简洁**



##### 2 RxJva 操作符

RxJava操作符的分类

![](/Users/fengluoye/Desktop/分享/RxJava操作符.png)







##### 主要常用操作符







##### 线程切换











https://www.jianshu.com/p/2d3d7c77dc92

https://www.jianshu.com/p/d997805b37d4

