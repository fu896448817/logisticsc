package com.wrt.xinsilu;

import android.os.Environment;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.fb.push.FeedbackPush;
import com.xsl.lerist.llibrarys.application.LMultidexApplication;
import com.xsl.lerist.llibrarys.utils.GlideImageLoader;

import org.xutils.x;

import java.io.File;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.jpush.android.api.JPushInterface;


/**
 * Created by wangsong on 2016/6/24 0024.
 */
public class MyApplication extends LMultidexApplication {
    private static MyApplication instance;
    public static final String APPPATH = Environment.getExternalStorageDirectory() + File.separator + "Xslwl_client";
    public static final String PATH_TEMP = APPPATH + File.separator + "Temp";
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Fresco.initialize(this);
        //init xUtils
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志

        FeedbackPush.getInstance(this).init(false);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        Log.i("----------->", JPushInterface.getRegistrationID(this));
        //自定义字体
//        customFont("fonts/FZLanTingHeiS-L-GB-Regular.TTF");

        initGalleryFinal();
    }
    public static MyApplication getInstance() {
        return instance;
    }

    public void initGalleryFinal() {
        //设置主题
//ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(getResources().getColor(R.color.gentleman_black))
                .setFabNornalColor(getResources().getColor(R.color.gentleman_black))
                .setFabPressedColor(getResources().getColor(R.color.colorAccent2))
                .build();
//配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
//                .setCropSquare(true)
//                .setEnablePreview(true)
                .build();

//配置imageloader
        ImageLoader imageloader = new GlideImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(getApplicationContext(), imageloader, theme)
                .setFunctionConfig(functionConfig)
                .setNoAnimcation(true)
                .setEditPhotoCacheFolder(new File(PATH_TEMP))
                .setTakePhotoFolder(new File(PATH_TEMP))
                .build();
        GalleryFinal.init(coreConfig);
    }
   /* @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
       MultiDex.install(this);
}*/
}
