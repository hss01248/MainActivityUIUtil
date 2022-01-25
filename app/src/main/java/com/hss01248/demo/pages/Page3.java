package com.hss01248.demo.pages;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;

import com.hss01248.bottomtabs.BaseMainPage;
import com.hss01248.demo.R;
import com.hss01248.demo.databinding.Tab2ThroughBinding;
import com.hss01248.pagestate.PageManager;

/**
 * Created by Administrator on 2017/2/10 0010.
 */

public class Page3 extends BaseMainPage<Tab2ThroughBinding> {
    public Page3(Activity mainActivity) {
        super(mainActivity);
    }

    @Override
    protected Tab2ThroughBinding initRealViewBinding(LayoutInflater layoutInflater) {
        return Tab2ThroughBinding.inflate(layoutInflater);
    }


    @Override
    protected void initViewReally() {

    }


    @Override
    public void onResume() {

    }

    @Override
    public void onpause() {

    }

    PageManager pageManager;
    @Override
    protected void initDataReally() {

        if(pageManager==null){
            pageManager =    PageManager.init(getRootView().findViewById(R.id.text), true, new Runnable() {
                @Override
                public void run() {
                    pageManager.showEmpty();
                }
            });
        }
        pageManager.showLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pageManager.showEmpty();

            }
        },3000);
    }
}
