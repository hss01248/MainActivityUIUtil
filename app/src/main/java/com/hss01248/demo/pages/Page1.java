package com.hss01248.demo.pages;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;

import com.hss01248.bottomtabs.BaseMainPage;
import com.hss01248.demo.R;
import com.hss01248.demo.databinding.Tab0ThroughBinding;
import com.hss01248.pagestate.PageManager;

/**
 * Created by Administrator on 2017/2/10 0010.
 */

public class Page1 extends BaseMainPage<Tab0ThroughBinding> {
    public Page1(Activity mainActivity) {
        super(mainActivity);
    }

    @Override
    protected Tab0ThroughBinding initViewBinding(LayoutInflater layoutInflater) {
        return Tab0ThroughBinding.inflate(layoutInflater);
    }

    @Override
    public View getStatusBarView() {
        return binding.vStatusbar;
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
            pageManager =    PageManager.init(getBinding().getRoot().findViewById(R.id.text), true, new Runnable() {
                @Override
                public void run() {
                    pageManager.showLoading();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pageManager.showError("实在不行了");

                        }
                    },3000);
                }
            });
        }
        pageManager.showLoading();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pageManager.showError("hshhshsh");

            }
        },3000);
    }
}
