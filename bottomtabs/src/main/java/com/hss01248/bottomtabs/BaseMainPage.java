package com.hss01248.bottomtabs;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/2/10 0010.
 */

public abstract class BaseMainPage {
    protected Activity activity;
    public ViewGroup getRootView() {
        return rootView;
    }

    public BaseMainPage(Activity mainActivity){
        activity = mainActivity;
        rootView = (ViewGroup) View.inflate(mainActivity,getLayoutRes(),null);
    }

    protected abstract int getLayoutRes();


    private ViewGroup rootView;

    public void initData(){
        if(!hasInitData){
            initDataReally();
        }
    }



    private boolean hasInitData;

    protected void setHasInitDataSuccess(){
        this.hasInitData = true;
    }

    public abstract void onResume();
    public abstract void onpause();
    protected abstract void initDataReally();

    public abstract void onTabShow();
    public abstract void onTabHide();







}
