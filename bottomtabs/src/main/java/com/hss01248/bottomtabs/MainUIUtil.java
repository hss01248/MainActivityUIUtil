package com.hss01248.bottomtabs;

import android.app.Activity;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.TabStripBuild;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;

/**
 * Created by Administrator on 2017/2/10 0010.
 */

public class MainUIUtil {
    private ViewPager viewPager ;
   private PagerBottomTabLayout bottomTabLayout;
   private List<BaseMainPage> pages;
   private Activity activity;
   private List<StatusbarUtil.StatusColorBean> beans;
   private List<TabItemBean> tabItemBeanList;
    private OnTabChangeListener tabChangeListener;
    private int currentPageIndex;

    public Controller getTabController() {
        return controller;
    }

    private   Controller controller;
    public static class TabItemBean{
       public  @DrawableRes int drawable;
        public @DrawableRes int selectedDrawable;
        public @NotNull String text;
        public @ColorRes int selectedColor;

        public TabItemBean(@DrawableRes int drawable, @DrawableRes int selectedDrawable, @NotNull String text,@ColorRes int selectedColor) {
            this.drawable = drawable;
            this.selectedDrawable = selectedDrawable;
            this.text = text;
            this.selectedColor = selectedColor;
        }
    }

    public interface OnTabChangeListener{
        void onTabSelected(int currentPage);
    }

    public void setOnTabChangeListener(OnTabChangeListener tabChangeListener){
        this.tabChangeListener = tabChangeListener;
    }

    private MainUIUtil(Activity activity){
        this.activity = activity;
        activity. setContentView(R.layout.activity_main2);
        viewPager = (ViewPager) activity.findViewById(R.id.vp_container);
        bottomTabLayout = (PagerBottomTabLayout) activity.findViewById(R.id.tab);


    }

    private  static MainUIUtil instance;
    public static MainUIUtil getInstance(Activity activity){
        if(instance==null){
            synchronized (MainUIUtil.class){
                if(instance ==null){
                    instance = new MainUIUtil(activity);
                }
            }
        }
        return instance;
    }

    public void addInfos(List<BaseMainPage> pages, List<StatusbarUtil.StatusColorBean> beans,List<TabItemBean> tabItemBeanList){
        this.pages = pages;
        this.beans = beans;
        this.tabItemBeanList = tabItemBeanList;
        initTabs();
        initViewpager();
        StatusbarUtil.initInTabs(activity);
        pages.get(0).initData();


    }

    private void initTabs() {
       TabStripBuild builder =  bottomTabLayout.builder();

        for(TabItemBean bean :tabItemBeanList){
            builder.addTabItem(bean.drawable,bean.selectedDrawable,bean.text,activity.getResources().getColor(bean.selectedColor));
        }

         controller =  builder.build();

        controller.addTabItemClickListener(new OnTabItemSelectListener() {
            @Override
            public void onSelected(int index, Object tag) {
                viewPager.setCurrentItem(index,false);

                // pages.get(index).initData();
                StatusbarUtil.onTabChangeIfHasThrough(activity,beans,index);
            }

            @Override
            public void onRepeatClick(int index, Object tag) {

            }
        });

    }

    private void initViewpager() {

        final PagerAdapter pagerAdapter = new PagerAdapter() {


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
                BaseMainPage page = pages.get(position);
                View view = page.getRootView();
                if(container.indexOfChild(view) >=0){
                    view.setVisibility(View.INVISIBLE);
                    page.onTabHide();
                }
                //container.removeView(view);
            }

            @Override
            public int getItemPosition(Object object) {

                return super.getItemPosition(object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                BaseMainPage page = pages.get(position);
                View view = page.getRootView();
                if(container.indexOfChild(view) >=0){
                    view.setVisibility(View.VISIBLE);
                }else {
                    container.addView(view);
                }
                page.onTabShow();

                return view;
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
                if(tabChangeListener!=null){
                    tabChangeListener.onTabSelected(position);
                }
                currentPageIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public void onResume(){
        BaseMainPage page = pages.get(currentPageIndex);
        page.onResume();

    }
    public void onPause(){
        BaseMainPage page = pages.get(currentPageIndex);
        page.onResume();
    }





}
