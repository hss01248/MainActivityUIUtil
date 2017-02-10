package com.hss01248.bottomtabs;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * compile 'com.readystatesoftware.systembartint:systembartint:1.0.3'
 *
 * Created by Administrator on 2017/1/11 0011.
 */

public class StatusbarUtil {

    private static int statusHeight;


    private static String getHandSetInfo() {
        String handSetInfo = "手机型号:" + Build.MODEL
                + "\n系统版本:" + Build.VERSION.RELEASE
                + "\n产品型号:" + Build.PRODUCT
                + "\n版本显示:" + Build.DISPLAY
                + "\n系统定制商:" + Build.BRAND
                + "\n设备参数:" + Build.DEVICE
                + "\n开发代号:" + Build.VERSION.CODENAME
                + "\nSDK版本号:" + Build.VERSION.SDK_INT
                + "\nCPU类型:" + Build.CPU_ABI
                + "\n硬件类型:" + Build.HARDWARE
                + "\n主机:" + Build.HOST
                + "\n生产ID:" + Build.ID
                + "\nROM制造商:" + Build.MANUFACTURER // 这行返回的是rom定制商的名称
                ;
        Log.e("tt",handSetInfo);
        return handSetInfo;
    }

    private static boolean isMeizu(){
        //魅族rom手机
        return "Meizu".equalsIgnoreCase(Build.MANUFACTURER);
    }

    private static boolean isXiaomi(){
        //小米rom手机
        return "Xiaomi".equalsIgnoreCase(Build.MANUFACTURER);
    }





    /**
     * 状态栏背景色为淡色时,为防止状态栏上白色字体看不清而采取的一系列适配措施.
     *
     * 适用于状态栏情况单一的activity,此方法中,不仅设置了背景色,还更改了状态栏文字颜色
     *
     * @param activity
     * @param colorLight UI图上给的状态栏背景色
     * @param colorDeep 在无法变更状态栏文字颜色的手机上,状态栏背景采用什么颜色.传入的是R.color.xxx
     */
    public static void setBgColorLight(Activity activity, @ColorRes int colorLight, @ColorRes int colorDeep) {
        int color = colorLight;

        if(isXiaomi()){
            setMiuiStatusBarDarkMode(activity,true);
        }else if(isMeizu()){
            setMeizuStatusBarDarkIcon(activity,true);
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setBlackFontUpM(activity,true);
        }else {
            color = colorDeep;
        }
        setBgColor(activity, color);
    }


    /**
     * 当UI图上状态栏背景为淡色时,适配时实际应设置的颜色是什么.
     * 只是提供一个颜色计算功能,没有实际去改变任何东西
     *
     * 适用于多tab切换时计算,然后就可以动态变更状态栏颜色
     * @param activity
     */
    public static @ColorRes
    int getBgColorIfLight(Activity activity, @ColorRes int colorLight, @ColorRes int colorDeep) {

        int color = colorLight;

        if(isXiaomi()){

        }else if(isMeizu()){

        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        }else {
            color = colorDeep;
        }
        return color;
    }


    /**
     * 可以无限调用,无在初始化时的限制.适用于多tab切换,其中一个或几个tab有白色,并还有其他颜色的情况
     * @param activity
     * @param isDark
     */
    public static void setFontBlack(Activity activity,boolean isDark){
        if(isXiaomi()){//对于小米手机6.0以上,应该优先调用小米的方法,而不是M上的,应该改ROM了
            setMiuiStatusBarDarkMode(activity,isDark);
        }else if(isMeizu()){
            setMeizuStatusBarDarkIcon(activity,isDark);
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setBlackFontUpM(activity,isDark);
        }else {

        }
    }




    public static int getStatusBarHeight(Context context) {

        if(statusHeight <=0){
             statusHeight = 50;
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusHeight = context.getResources().getDimensionPixelSize(resourceId);
            }

            Log.e("h","getStatusBarHeight:"+statusHeight);
            return statusHeight;

        }else {
            return statusHeight;
        }


    }

    public static void initInTabs(Activity activity){
        setBgTransparent(activity);
    }


    /**
     * 没有页面内容沉浸入状态栏的情况,不同tab不同颜色
     * @param activity
     * @param colorBeans
     * @param statusBarView
     * @param index
     */
    public static void onTabChangeIfNoThrough(Activity activity, List<StatusColorBean> colorBeans, View statusBarView, int index){



        StatusColorBean bean = colorBeans.get(index);

        setFontBlack(activity,bean.isLight);

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
        }

    }



    /**
     * 没有页面内容沉浸入状态栏的情况,不同tab不同颜色
     * @param activity
     * @param colorBeans
     * @param index
     */
    public static void onTabChangeIfHasThrough(Activity activity, List<StatusColorBean> colorBeans, int index){

        StatusColorBean bean = colorBeans.get(index);
        View statusBarView = bean.statusBarView;

        setFontBlack(activity,bean.isLight);

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
        }

    }











    private static void setStatusBarViewHeight(Activity activity, View statusBarView) {
        int height = getStatusBarHeight(activity);
        ViewGroup.LayoutParams layoutParams = statusBarView.getLayoutParams();
        layoutParams.height = height;
        statusBarView.setLayoutParams(layoutParams);
        statusBarView.setVisibility(View.VISIBLE);
    }


    /**
     * 需要在setContentview之后再调用
     * @param activity
     */
    public static void setBgColor(Activity activity, @ColorRes int colorRes) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity, true);
            SystemBarTintManager mTintManager = new SystemBarTintManager(activity);
            mTintManager.setStatusBarTintEnabled(true);
            mTintManager.setStatusBarTintResource(colorRes);
        }
    }

    public static void setBgTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity, true);
            SystemBarTintManager mTintManager = new SystemBarTintManager(activity);
            mTintManager.setStatusBarTintEnabled(true);
            mTintManager.setStatusBarTintColor(Color.TRANSPARENT);
        }
    }



    /**
     * 单纯地设置状态栏透明
     * @param activity
     */
    public static void setStatusBarTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity, true);
            //activity.getWindow().getDecorView().
        }
    }


    @TargetApi(19)
    private static void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }





    private static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean setMeizuStatusBarDarkIcon(Activity activity, boolean dark) {
        boolean result = false;
        if (activity != null) {
            try {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                activity.getWindow().setAttributes(lp);
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }

    /**
     * 6.0以上的系统,将字体改成深色
     * @param activity
     */
    private static void setBlackFontUpM(Activity activity,boolean dark){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(dark){
                activity.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }else {
                activity.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            }

        }
    }

    public static class StatusColorBean{
        public @ColorRes  int colorRes;
        public boolean isLight;//是否为浅色
        public boolean isThrough;//是否为沉浸式
        public @ColorRes  int colorResBackup;//浅色时备用的颜色

        public View statusBarView;

        /*public StatusColorBean(int colorRes, boolean isLight, boolean isThrough,int colorResBackup) {
                this.colorRes = colorRes;
                this.isLight = isLight;
                this.isThrough = isThrough;
                this.colorResBackup = colorResBackup;
        }*/

        public StatusColorBean(int colorRes, boolean isLight, boolean isThrough,int colorResBackup,View statusBarView) {
            this.colorRes = colorRes;
            this.isLight = isLight;
            this.isThrough = isThrough;
            this.colorResBackup = colorResBackup;
            this.statusBarView = statusBarView;
        }

        /**
         *
         * @param colorRes
         * @param isLight
         * @param colorResBackup
         */
        public StatusColorBean(int colorRes, boolean isLight,int colorResBackup) {
            this.colorRes = colorRes;
            this.isLight = isLight;
            this.isThrough = false;
            this.colorResBackup = colorResBackup;
        }


    }

    public interface onTabChangeListener{
        void onChange(int index);
    }
}
