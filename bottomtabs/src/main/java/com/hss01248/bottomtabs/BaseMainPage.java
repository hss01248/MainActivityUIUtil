package com.hss01248.bottomtabs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.viewbinding.ViewBinding;

import com.hss01248.bottomtabs.databinding.UtilTabXThroughWithStatusbarBinding;

/**
 * Created by Administrator on 2017/2/10 0010.
 */

public abstract class BaseMainPage<T extends ViewBinding> {
    protected Activity activity;


    public View getRootView(){
       return rootBinding.getRoot();
    }
    UtilTabXThroughWithStatusbarBinding rootBinding;
    public BaseMainPage(Activity mainActivity){
        activity = mainActivity;
         rootBinding = UtilTabXThroughWithStatusbarBinding.inflate(activity.getLayoutInflater());

    }

    //protected abstract int getLayoutRes();

    protected abstract T initRealViewBinding(LayoutInflater layoutInflater);

    public  View getStatusBarView(){
        return rootBinding.vStatusbarUtil;
    }

    public T getRealBinding() {
        return realBinding;
    }

    protected T realBinding;

    protected boolean isFirstTimeIn;


    public void initData(){
        if(!isFirstTimeIn){
            isFirstTimeIn = true;
            realBinding = initRealViewBinding(activity.getLayoutInflater());
            rootBinding.llPageRoot.addView(realBinding.getRoot());
            initViewReally();
        }
        if(!hasInitData){
            initDataReally();
        }
    }

    protected abstract void initViewReally();


    protected boolean hasInitData;

    protected void setHasInitDataSuccess(){
        this.hasInitData = true;
    }

    public abstract void onResume();
    public abstract void onpause();

    protected abstract void initDataReally();

    public  void onTabShow(){};
    public  void onTabHide(){};







}
