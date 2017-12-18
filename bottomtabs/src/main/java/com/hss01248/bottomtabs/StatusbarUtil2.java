package com.hss01248.bottomtabs;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.githang.statusbar.StatusBarCompat;

import java.util.List;

/**
 * Created by huangshuisheng on 2017/12/18.
 */

public class StatusbarUtil2 {

    public static void initInTabs(Activity activity){
        //StatusBarCompat.setStatusBarColor(activity, Color.TRANSPARENT);
        StatusBarCompat.setTranslucent(activity.getWindow(),true);
        //StatusBarCompat.setFitsSystemWindows(activity.getWindow(),false);
    }

    public static void onTabChangeIfHasThrough(Activity activity, List<StatusbarUtil.StatusColorBean> colorBeans, int index){

        StatusbarUtil.StatusColorBean bean = colorBeans.get(index);
        View statusBarView = bean.statusBarView;

        //StatusBarCompat.setFitsSystemWindows(activity.getWindow(),bean.isThrough);

        //StatusBarCompat.setTranslucent(activity.getWindow(),bean.isThrough);
        if(!bean.isThrough){
            StatusBarCompat.setStatusBarColor(activity,activity.getResources().getColor(bean.colorRes));
        }else {
            StatusBarCompat.setStatusBarColor(activity, Color.TRANSPARENT);
            StatusBarCompat.setTranslucent(activity.getWindow(),true);
        }
        StatusBarCompat.setLightStatusBar(activity.getWindow(),bean.isLight);

        if(bean.isThrough){
            statusBarView.setVisibility(View.GONE);
            return;
        }

        statusBarView.setVisibility(View.VISIBLE);


       /* setFontBlack(activity,bean.isLight);

        if(bean.isThrough){
            statusBarView.setVisibility(View.GONE);
            return;
        }


        setStatusBarViewHeight(activity, statusBarView);



        if(bean.isLight){
            int color = getBgColorIfLight(activity,bean.colorRes,bean.colorResBackup);
            statusBarView.setBackgroundResource(color);
        }else {
            statusBarView.setBackgroundResource(bean.colorRes);
        }*/

    }
}
