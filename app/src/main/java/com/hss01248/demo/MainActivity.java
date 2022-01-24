package com.hss01248.demo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.hss01248.bottomtabs.BaseMainPage;
import com.hss01248.bottomtabs.MainUIUtil;
import com.hss01248.bottomtabs.StatusbarUtil;
import com.hss01248.demo.pages.Page1;
import com.hss01248.demo.pages.Page2;
import com.hss01248.demo.pages.Page3;
import com.hss01248.demo.pages.Page4;
import com.hss01248.pagestate.PageManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*ViewPager viewPager ;
    PagerBottomTabLayout bottomTabLayout;
    List<View> viewList;
    View vStatusBar;*/
    MainUIUtil mainUIUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PageManager.initInApp(getApplicationContext());


        List<BaseMainPage> pages = new ArrayList<>();
        pages.add(new Page1(this));
        pages.add(new Page2(this));
        pages.add(new Page3(this));
        pages.add(new Page4(this));

        List<StatusbarUtil.StatusColorBean>   beans = new ArrayList<>();
        beans.add(new StatusbarUtil.StatusColorBean(R.color.colorPrimary,false,false,R.color.base,pages.get(0).getStatusBarView()));
        beans.add(new StatusbarUtil.StatusColorBean(R.color.white,true,false,R.color.base,pages.get(1).getStatusBarView()));
        beans.add(new StatusbarUtil.StatusColorBean(R.color.white,true,true,R.color.base,pages.get(2).getStatusBarView()));//透明且沉浸状态栏
        beans.add(new StatusbarUtil.StatusColorBean(R.color.colorPrimaryDark,false,false,R.color.base,pages.get(3).getStatusBarView()));

        List<MainUIUtil.TabItemBean> tabBeans = new ArrayList<>();
        tabBeans.add(new MainUIUtil.TabItemBean(R.mipmap.home_normal,R.mipmap.home_pressed,"首页",R.color.colorPrimary));
        tabBeans.add(new MainUIUtil.TabItemBean(R.mipmap.question_normal,R.mipmap.question_pressed,"问答",R.color.base));
        tabBeans.add(new MainUIUtil.TabItemBean(R.mipmap.article_normal,R.mipmap.article_pressed,"文章",R.color.base));
        tabBeans.add(new MainUIUtil.TabItemBean(R.mipmap.my_normal,R.mipmap.my_pressed,"我的",R.color.base));



        mainUIUtil =  MainUIUtil.getInstance(this);
        mainUIUtil.addInfos(pages,beans,tabBeans);


    }

}
