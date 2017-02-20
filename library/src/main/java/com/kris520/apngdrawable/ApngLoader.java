package com.kris520.apngdrawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by xiejing on 17/2/17.
 *
 * 图片加载器
 */
public class ApngLoader {
    private static class SingletonCreator {
        public static final ApngLoader instance = new ApngLoader();
    }
    private static ApngLoader getInstance() {
        return SingletonCreator.instance;
    }

    private ScheduledThreadPoolExecutor excutor = new ScheduledThreadPoolExecutor(1, new ThreadPoolExecutor.DiscardPolicy());
    private Context appContext = null;
    private Handler uiHandler = null;

    public static void init(Context context) {
        getInstance().appContext = context.getApplicationContext();
        getInstance().uiHandler = new Handler(Looper.getMainLooper());
    }

    public static Context getAppContext() {
        return getInstance().appContext;
    }

    public static void loadImage(final String uri, final ImageView imageView, final ApngImageLoadingListener listener) {
        getInstance().excutor.execute(new Runnable() {
            @Override
            public void run() {
                ApngImageUtils.Scheme urlType = ApngImageUtils.Scheme.ofUri(uri);
                Bitmap decodeBitmap = null;
                switch(urlType) {
                    case FILE:
                        decodeBitmap = ApngImageUtils.decodeFileToDrawable(uri, null);
                        break;
                    case ASSETS:
                        String filePath = ApngImageUtils.Scheme.ASSETS.crop(uri);
                        try {
                            InputStream inputStream = getAppContext().getAssets().open(filePath);
                            decodeBitmap = BitmapFactory.decodeStream(inputStream);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (OutOfMemoryError e) {
                            e.printStackTrace();
                        }
                        break;
                }
                Drawable drawable = ApngImageUtils.bitmapToDrawable(uri, imageView, decodeBitmap);;

                // 将结果通知ui业务层
                final Drawable finalDrawable = drawable;
                getInstance().uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (finalDrawable != null) {
                            imageView.setImageDrawable(finalDrawable);
                            if (listener != null)
                                listener.onLoadingComplete(uri, imageView, finalDrawable);
                            if (finalDrawable instanceof ApngDrawable)
                                ((ApngDrawable)finalDrawable).start();
                        } else {
                            if (listener != null)
                                listener.onLoadFailed(uri, imageView);
                        }
                    }
                });
            }
        });
    }

}
