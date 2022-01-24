package com.hss01248.bottomtabs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;

/**
 * Created by Administrator on 2017/2/10 0010.
 */

public abstract class BaseMainPage<T extends ViewBinding> {
    protected Activity activity;


    public BaseMainPage(Activity mainActivity){
        activity = mainActivity;
        binding = initViewBinding(activity.getLayoutInflater());
    }

    //protected abstract int getLayoutRes();

    protected abstract T initViewBinding(LayoutInflater layoutInflater);

    public abstract View getStatusBarView();

    public T getBinding() {
        return binding;
    }

    protected T binding;




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

    public  void onTabShow(){};
    public  void onTabHide(){};







}
