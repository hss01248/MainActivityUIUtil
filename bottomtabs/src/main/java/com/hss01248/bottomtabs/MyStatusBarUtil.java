package com.hss01248.bottomtabs;

import android.app.Activity;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;

/**
 * Created by huangshuisheng on 2017/12/11.
 */

public class MyStatusBarUtil {



    public static void setStatusBarColor(Activity activity,int statusbarColorRes,boolean needTextBlack){
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

        if(needTextBlack){
            StatusbarUtil.setFontBlack(activity,needTextBlack);
        }
    }

    public static void setIntoStatusBar(Activity activity,boolean needTextBlack){
        View statusBarView = activity.findViewById(R.id.v_status);
        if(statusBarView !=null){
            statusBarView.setVisibility(View.GONE);
        }
        StatusbarUtil.setStatusBarTranslucent(activity);

        if(needTextBlack){
            StatusbarUtil.setFontBlack(activity,needTextBlack);
        }
    }
}
