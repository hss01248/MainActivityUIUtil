package com.hss01248.bottomtabs;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * Created by huangshuisheng on 2017/12/11.
 */

public class MyStatusBarUtil {



    public static void setStatusBarColor(Activity activity,int statusbarColorRes,boolean needTextBlack){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT){
            return;
        }


        setTranslucentStatus(activity,R.color.trans);


        View statusBarView = activity.findViewById(R.id.v_status);
        if(statusBarView ==null){
            statusBarView = View.inflate(activity,R.layout.view_status,null);
            ViewParent parent= activity.findViewById(android.R.id.content).getParent();
            if(parent instanceof LinearLayout){
                LinearLayout linearLayout = (LinearLayout) parent;
                linearLayout.addView(statusBarView,0);
            }
        }else {
            statusBarView.setVisibility(View.VISIBLE);
        }
        statusBarView.setBackgroundResource(statusbarColorRes);


            StatusbarUtil.setFontBlack(activity,needTextBlack);

    }

    public static void setIntoStatusBar(Activity activity,boolean needTextBlack){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT){
            return;
        }

        setTranslucentStatus(activity,R.color.trans);

        View statusBarView = activity.findViewById(R.id.v_status);
        if(statusBarView !=null){
            statusBarView.setVisibility(View.GONE);
        }


            StatusbarUtil.setFontBlack(activity,needTextBlack);

    }

    public static void setTranslucentStatus(Activity activity,int statusBarPlaceColor) {

        // 5.0以上系统状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }



}
