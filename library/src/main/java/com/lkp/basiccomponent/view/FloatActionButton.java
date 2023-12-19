package com.lkp.basiccomponent.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * @Author: KempLi
 * @Date: 2023/12/19 9:39
 * @Description: 带悬浮动画的按钮
 */
public final class FloatActionButton extends AppCompatImageView {

    /** 动画显示时长 */
    private static final int ANIM_TIME = 300;

    public FloatActionButton(@NonNull Context context) {
        super(context);
    }

    public FloatActionButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatActionButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 显示
     */
    public void show() {
        removeCallbacks(mHideRunnable);
        postDelayed(mShowRunnable, ANIM_TIME * 2);
    }

    /**
     * 隐藏
     */
    public void hide() {
        removeCallbacks(mShowRunnable);
        post(mHideRunnable);
    }

    /**
     * 显示悬浮球动画
     */
    private final Runnable mShowRunnable = () -> {
        if (getVisibility() == View.INVISIBLE) {
            setVisibility(View.VISIBLE);
        }
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
        valueAnimator.setDuration(ANIM_TIME);
        valueAnimator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            setAlpha(value);
            setScaleX(value);
            setScaleY(value);
        });
        valueAnimator.start();
    };

    /**
     * 隐藏悬浮球动画
     */
    private final Runnable mHideRunnable = () -> {
        if (getVisibility() == View.INVISIBLE) {
            return;
        }
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1f, 0f);
        valueAnimator.setDuration(ANIM_TIME);
        valueAnimator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            setAlpha(value);
            setScaleX(value);
            setScaleY(value);
            if (value == 0) {
                setVisibility(View.INVISIBLE);
            }
        });
        valueAnimator.start();
    };
}