package com.lkp.basiccomponent.layout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * @Author: KempLi
 * @Date: 2023/12/19 9:42
 * @Description: 禁用水平滑动的ViewPager（一般用于 APP 首页的 ViewPager + Fragment）
 */
public final class NoScrollViewPager extends ViewPager {

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 不拦截这个事件
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // 不处理这个事件
        return false;
    }

    @Override
    public boolean executeKeyEvent(@NonNull KeyEvent event) {
        // 不响应按键事件
        return false;
    }

    @Override
    public void setCurrentItem(int item) {
        // 只有相邻页才会有动画
        super.setCurrentItem(item, Math.abs(getCurrentItem() - item) == 1);
    }
}