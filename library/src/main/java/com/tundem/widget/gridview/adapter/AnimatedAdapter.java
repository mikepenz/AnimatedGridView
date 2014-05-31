package com.tundem.widget.gridview.adapter;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;

import com.tundem.widget.gridview.animation.ScaleUpAnimation;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mikepenz on 16.05.14.
 */
public abstract class AnimatedAdapter<T> extends BaseAdapter {
    public int duration = 500;
    private List<Integer> newFieldsToAnimate = new LinkedList<Integer>();

    abstract public void removeItem(int position);

    public void addItem(T item, boolean visible) {
        if (visible) {
            newFieldsToAnimate.add(getCount());
        }
    }

    @Override
    public View getView(int i, final View view, ViewGroup parent) {

        if (view != null && newFieldsToAnimate.contains(i)) {
            ViewCompat.setHasTransientState(view, true);
            ScaleUpAnimation fd = new ScaleUpAnimation(view);
            view.getLayoutParams().height = 0;
            fd.setDuration(duration);
            fd.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    ViewCompat.setHasTransientState(view, false);
                    view.clearAnimation();
                    view.invalidate();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            view.startAnimation(fd);
            newFieldsToAnimate.remove(newFieldsToAnimate.indexOf(i));
        }

        return view;
    }
}
