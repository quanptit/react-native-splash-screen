package org.devio.rn.splashscreen;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * SplashScreen
 * 启动屏
 * from：http://www.devio.org
 * Author:CrazyCodeBoy
 * GitHub:https://github.com/crazycodeboy
 * Email:crazycodeboy@gmail.com
 */
public class SplashScreenModule extends ReactContextBaseJavaModule {
    public SplashScreenModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "SplashScreen";
    }

    /**
     * 打开启动屏
     */
    @ReactMethod
    public void show() {
        SplashScreen.show(getCurrentActivity());
    }

    //#region my code
    /**
     * 关闭启动屏
     */
    @ReactMethod
    public void hide() {
        try {
            if (isDoNotShowAds())
                SplashScreen.hide(getCurrentActivity());
            else
                SplashScreen.requestHide(getCurrentActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isDoNotShowAds() {
        return getBooleanSetting("REMOVE_ADS", false)
                || getBooleanSetting("IS_VIP", false);
    }

    public boolean getBooleanSetting(String pKey, boolean mDefaultValue) {
        SharedPreferences mSharePreferences = PreferenceManager.getDefaultSharedPreferences(getReactApplicationContext());
        return mSharePreferences.getBoolean(pKey, mDefaultValue);
    }
    //#endregion
}