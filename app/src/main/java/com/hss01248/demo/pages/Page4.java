package com.hss01248.demo.pages;

import android.app.Activity;
import android.os.Handler;

import com.hss01248.bottomtabs.BaseMainPage;
import com.hss01248.demo.R;
import com.hss01248.pagestate.PageManager;

/**
 * Created by Administrator on 2017/2/10 0010.
 */

public class Page4 extends BaseMainPage {
    public Page4(Activity mainActivity) {
        super(mainActivity);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.tab_3_through;
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
                pageManager.showContent();
                setHasInitDataSuccess();

            }
        },3000);
    }
}
