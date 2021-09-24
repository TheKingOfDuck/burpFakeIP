## burpFakeIP

下载使用：[Releases](https://github.com/TheKingOfDuck/burpFakeIP/releases/tag/1.0)

**2021/09/24**

1. 修复[M00nBack](https://github.com/M00nBack)反馈的一个bug。
2. 添加了AutoXFF的开关,并将AutoXFF默认设置不开启,如需让插件给每个请求头添加一个随机的XFF请求头可在右键菜单中选择ON开启


**2021/05/21**

使用Java重构，增加了[issue](https://github.com/TheKingOfDuck/burpFakeIP/issues/8)中提到的功能特性，新增给每个请求自动添加XFF头以及随机IP的功能，具体可见右键菜单AutoXFF,默认情况下自动添加的xff头为X-Forwarded-For,值为生成的随机IP,均可自定义。

**2020/04/25**

优化代码，新增9种请求头。


![](https://github.com/TheKingOfDuck/BurpFakeIP/blob/master/images/15597179485863.png)



四个小功能

* 伪造指定ip
* 伪造本地ip
* 伪造随机ip
* 随机ip爆破

### 0x01 伪造指定ip

在`Repeater`模块右键选择`fakeIp`菜单,然后点击`inputIP`功能,然后输入指定的ip：

![](https://github.com/TheKingOfDuck/BurpFakeIP/blob/master/images/15597184839805.png)


![](https://github.com/TheKingOfDuck/BurpFakeIP/blob/master/images/15597185444300.png)

程序会自动添加所有可伪造得字段到请求头中。

### 0x02 伪造本地ip

在`Repeater`模块右键选择`fakeIp`菜单,然后点击`127.0.0.1`功能：

![](https://github.com/TheKingOfDuck/BurpFakeIP/blob/master/images/15597186627939.png)

### 0x03 伪造随机ip

在`Repeater`模块右键选择`fakeIp`菜单,然后点击`randomIP`功能：

![](https://github.com/TheKingOfDuck/BurpFakeIP/blob/master/images/15597187304576.png)


### 0x04 随机ip爆破

**伪造随机ip爆破是本插件最核心的功能。**

将数据包发送到`Intruder`模块,在`Positions`中切换`Attack type`为`Pitchfork`模式,选择好有效的伪造字段,以及需要爆破的字段:

![](https://github.com/TheKingOfDuck/BurpFakeIP/blob/master/images/15597190596991.png)

按照箭头顺序将Payload来源设置为`Extensin-generated`,并设置负载伪`fakeIpPayloads`,然后设置第二个变量。
![](https://github.com/TheKingOfDuck/BurpFakeIP/blob/master/images/15597191239161.png)


![](https://github.com/TheKingOfDuck/BurpFakeIP/blob/master/images/15597192426317.png)

点击`Start attack`开始爆破.

![](https://github.com/TheKingOfDuck/BurpFakeIP/blob/master/images/15597193222287.png)

如上图,实现每次爆破都使用不同的伪ip进行,避免被ban。

> PS：伪造随机ip爆破的先决条件可以伪造ip绕过服务器限制。

