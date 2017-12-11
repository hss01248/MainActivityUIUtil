package com.hss01248.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.hss01248.bottomtabs.BaseMainPage;
import com.hss01248.bottomtabs.MyStatusBarUtil;
import com.hss01248.bottomtabs.StatusbarUtil;
import com.hss01248.demo.pages.Page1;
import com.hss01248.demo.pages.Page2;
import com.hss01248.demo.pages.Page3;
import com.hss01248.demo.pages.Page4;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;

public class MainActivity2 extends AppCompatActivity {
    ViewPager viewPager ;
    PagerBottomTabLayout bottomTabLayout;
    List<BaseMainPage> pages;
    Activity activity;

   // List<View> viewList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;

       // StatusbarUtil.setBgColorLight(this,R.color.white,R.color.colorPrimary);//设置状态栏颜色为白色,第二个颜色为无法变更状态栏字体时,状态栏背景色

        setContentView(R.layout.activity_main2);
       // StatusbarUtil.setBgColor(this,R.color.base);//设置为某一种统一的颜色时

        viewPager = (ViewPager) findViewById(R.id.vp_container);

        bottomTabLayout = (PagerBottomTabLayout) findViewById(R.id.tab);
        initTabs();
        initViewpager();
        initStatusBg();


    }
    List<StatusbarUtil.StatusColorBean> beans;
    private void initStatusBg() {
        beans = new ArrayList<>();
        beans.add(new StatusbarUtil.StatusColorBean(R.color.colorPrimary,false,false,R.color.base,pages.get(0).getRootView().findViewById(R.id.v_statusbar)));
        beans.add(new StatusbarUtil.StatusColorBean(R.color.white,true,false,R.color.base,pages.get(1).getRootView().findViewById(R.id.v_statusbar)));
        beans.add(new StatusbarUtil.StatusColorBean(R.color.white,true,true,R.color.base,pages.get(2).getRootView().findViewById(R.id.v_statusbar)));//透明且沉浸状态栏
        beans.add(new StatusbarUtil.StatusColorBean(R.color.colorPrimaryDark,false,false,R.color.base,pages.get(3).getRootView().findViewById(R.id.v_statusbar)));
        //StatusbarUtil.initInTabs(this);
    }

    private void initTabs() {
        Controller controller =  bottomTabLayout.builder()
                .addTabItem(android.R.drawable.ic_menu_camera, "相机")
                .addTabItem(android.R.drawable.ic_menu_compass, "位置")
                .addTabItem(android.R.drawable.ic_menu_search, "搜索")
                .addTabItem(android.R.drawable.ic_menu_help, "帮助")
                .build();

        controller.addTabItemClickListener(new OnTabItemSelectListener() {
            @Override
            public void onSelected(int index, Object tag) {
                viewPager.setCurrentItem(index,true);
               // pages.get(index).initData();
                StatusbarUtil.onTabChangeIfHasThrough(MainActivity2.this,beans,index);
            }

            @Override
            public void onRepeatClick(int index, Object tag) {

            }
        });
    }

    private void initViewpager() {
        pages = new ArrayList<>();
        pages.add(new Page1(this));
        pages.add(new Page2(this));
        pages.add(new Page3(this));
        pages.add(new Page4(this));

        MyStatusBarUtil.setStatusBarColor(activity,R.color.colorPrimary,false);
       /* viewList = new ArrayList<View>(pages.size());// 将要分页显示的View装入数组中
        viewList.add(View.inflate(this,R.layout.tab_0_through,null));
        viewList.add(View.inflate(this,R.layout.tab_1_through,null));
        viewList.add(View.inflate(this,R.layout.tab_2_through,null));
        viewList.add(View.inflate(this,R.layout.tab_3_through,null));*/



        PagerAdapter pagerAdapter = new PagerAdapter() {


            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {

                return pages.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(pages.get(position).getRootView());

            }

            @Override
            public int getItemPosition(Object object) {

                return super.getItemPosition(object);
            }



            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(pages.get(position).getRootView());
                return pages.get(position).getRootView();
            }

        };
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pages.get(position).initData();
                switch (position){
                    case 0:
                        MyStatusBarUtil.setStatusBarColor(activity,R.color.colorPrimary,false);
                        break;
                    case 1:
                        MyStatusBarUtil.setStatusBarColor(activity,R.color.white,true);
                        break;
                    case 2:
                        MyStatusBarUtil.setIntoStatusBar(activity,true);
                        break;
                    case 3:
                        MyStatusBarUtil.setStatusBarColor(activity,R.color.colorPrimaryDark,false);
                        break;
                    default:break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
