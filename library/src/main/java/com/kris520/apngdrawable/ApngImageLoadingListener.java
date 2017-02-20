package com.kris520.apngdrawable;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;


/**
 * Created by xiejing on 16/3/8.
 */
public class ApngImageLoadingListener {

    private ApngPlayListener playListener = null; // 播放监听

    public ApngImageLoadingListener(ApngPlayListener playListener) {
        this.playListener = playListener;

    }

    public void onLoadingComplete(String imageUri, ImageView imageView, Drawable loadedImage) {
        if (loadedImage instanceof ApngDrawable) {
            ((ApngDrawable) loadedImage).setPlayListener(playListener);
            ((ApngDrawable)loadedImage).start();
        }
    }

    public void onLoadFailed(String imageUri, ImageView imageView) {

    }
}
