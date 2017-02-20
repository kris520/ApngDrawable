package com.kris520.apngdrawable;

/**
 * Created by xiejing on 16/3/11.
 */
public interface ApngPlayListener {
    void onAnimationStart(ApngDrawable drawable);
    void onAnimationEnd(ApngDrawable drawable);
    void onAnimationRepeat(ApngDrawable drawable);
}
