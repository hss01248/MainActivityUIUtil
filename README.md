# MainActivityUIUtil
[![](https://jitpack.io/v/hss01248/MainActivityUIUtil.svg)](https://jitpack.io/#hss01248/MainActivityUIUtil)

底部tab+viewpager+状态栏变色/字体变色兼容,

viewpager的各页面建立了BaseMainPage,

实现了数据的懒加载,以及各page的生命周期控制.



statusBarUtil:可单独拷贝出来使用

沉浸式和非沉浸式状态栏,变色以及背景为白色时状态栏字体变色,多tab切换时状态栏变色的综合解决方案

tab切换时几个tab在visiable和invisiable之间切换,不会重复遍历viewtree

# 效果图:

 ![mainactivity](mainactivity.jpg)

# 使用

### MainActivity中:

oncreate方法中调用:

```
MainUIUtil.getInstance(this);//保存一个引用到mainactivity中

mainUIUtil.addInfos(baseMainPages,statusbarConfigBeans,tabItemBeans);
```

自定义的配置放在baseMainPages,statusbarConfigBeans,tabItemBeans三个list中,具体请看demo.

onResume()和onPause()方法中分别调用:

```
MainUIUtil.onResume();
MainUIUtil.onPause();
```



### 具体的tab页面

应继承BaseMainPage类,并实现生命周期方法:

```
	public abstract void onTabShow();
    public abstract void onTabHide();
    public abstract void onResume();
    public abstract void onpause();
```



## gradle

**Step 1.** Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```
    allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }
```

**Step 2.** Add the dependency

```
    dependencies {
            compile 'com.github.hss01248:MainActivityUIUtil:1.0.1'
    }
```



# 感谢

[PagerBottomTabStrip](https://github.com/tyzlmjj/PagerBottomTabStrip)