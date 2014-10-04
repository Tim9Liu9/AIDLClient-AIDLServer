AIDLClient-AIDLServer
=====================


教程说明见这里 http://www.linuxidc.com/Linux/2012-06/63205.htm



Android aidl实现两个apk之间远程调用Service:
=======================1=========================
Aidl，Android平台的IPC方式之一，基于系统的Ibinder机制。

网上大多数例子都是在一个apk下来测试调用service，现在我在两个project下面来调用。

一个是server project，一个是client project

首先我们建立的是server project，这里面要实现aidl文件和一个service，activity只是用来启动service的，当然，你也可以通过发广播的形式来启动service。

首先看IAidlService.aidl文件：

    package com.ds.server;   
    interface IAidlService {     
        int getType();    
    }    

这样在eclipse里面自动编译的时候会在gen下面生成IAidlService.java文件（灯下我们的client project要用）。
然后新建一个service，这个service里面has a IAidlService的stub对象，service具体代码如下：

=======================2=========================
这里一定要实现onBind方法，并返回一个IAidlService.Stub对象。
再去AndroidManifest.xml注册这个service:

    <service  
                android:name=".AidlService"  
                android:enabled="true"  
                android:process=":remote" >  
                <intent-filter>  
                    <action android:name="com.ds.server.IAidlService" />  
                    <category android:name="android.intent.category.DEFAULT" />  
                </intent-filter>  
            </service>  

android:enabled="true"

android:process=":remote"这两个标签可有可无。

只要注册了这个service就行。

好了，到此，服务端已经完成。 

=======================3=========================
下面我们开始client project。

client project比较简单，需要注意的地方是，首先需要把server project中gen文件夹中aidl生成的那个IAidlService.java类以及包都拷贝到我们的client project中。

（注意：client project的包名为com.ds.client;另外一个包名com.ds.server以及这个server包下面的IAidlService.java类都是从server project的gen文件夹拷贝过来的，至于gen文件夹的其他文件就不需要拷贝过来

注意几点：

1，import com.ds.server.IAidlService;使用的是我们拷贝过来的IAidlService.java类

2，需要一个ServiceConnection对象

3，通过Intent service = new Intent(IAidlService.class.getName());

bindService(service, connection, BIND_AUTO_CREATE);来bind service。这样就可以调用aidl中定义的接口来获取service中的值了。

唉，由于在使用中没有注意拷贝server project中gen文件夹下面的包和IAidlService.java，老是出现Unable to start service Intent这样的错误。搞了好久。

注意使用的时候，先要运行server project，启动服务，然后再运行client project。 



