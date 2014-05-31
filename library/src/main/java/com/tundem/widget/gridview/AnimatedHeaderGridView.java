package com.tundem.widget.gridview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.WrapperListAdapter;

import com.tundem.widget.gridview.adapter.AnimatedAdapter;
import com.tundem.widget.gridview.helper.Helper;
import com.tundem.widget.gridview.listener.AnimationListener;

import java.util.LinkedList;
import java.util.Set;

/**
 * Created by mikepenz on 16.05.14.
 */
public class AnimatedHeaderGridView extends HeaderGridView implements IAnimatedGridView {
    public AnimatedHeaderGridView(Context context) {
        super(context);
        this.setLayoutAnimation(Helper.getLayoutAnimation());
    }

    public AnimatedHeaderGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setLayoutAnimation(Helper.getLayoutAnimation());
    }

    public AnimatedHeaderGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setLayoutAnimation(Helper.getLayoutAnimation());
    }

    /**
     * ANIMATION LOGIC :D
     */
    public void animateAddCells(LinkedList<Object> cells, int duration) {
        Helper.animateAddCells(this, cells, duration);
    }

    public void animateDeleteRow(Set<Integer> rows, int duration) {
        Helper.animateDeleteRow(this, rows, duration);
    }

    public void animateDeleteCells(final Set<Integer> cells, int duration) {
        Helper.animateDeleteCells(this, cells, duration);
    }

    public BaseAdapter getBaseAdapter() {
        Adapter adapter = getAdapter();
        if (adapter != null) {
            if (adapter instanceof WrapperListAdapter) {
                adapter = ((WrapperListAdapter) adapter).getWrappedAdapter();
            }

            if (adapter instanceof BaseAdapter) {
                return (BaseAdapter) adapter;
            }
        }
        return null;
    }

    public AnimatedAdapter getAnimatedAdapter() {
        Adapter adapter = getBaseAdapter();
        if (adapter != null && adapter instanceof AnimatedAdapter) {
            return (AnimatedAdapter) adapter;
        }
        return null;
    }

    /**
     * LISTENER!!
     */

    private AnimationListener animationListener;

    public void setAnimationListener(AnimationListener animationListener) {
        this.animationListener = animationListener;
    }

    public void onAnimationFinish() {
        if (animationListener != null) {
            animationListener.onAnimationFinish();
        }
    }

    /**
     * A SMALL EXTRA FOR THOSE WHO LOVE OPEN SOURCE ;D
     */

    /**
     * smoothScroll a specific item to the center of the list :D
     *
     * @param position
     */

    public void smoothScrollToCenterPosition(int position) {
        Helper.smoothScrollToCenterPosition(this, position);
    }

    /**
     * HELPER METHODS!!
     */

    public View getViewByPosition(int position) {
        return Helper.getViewByPosition(this, position);
    }

    /**
     * Helper to calculate if a specific position is visible
     *
     * @param position
     * @return
     */

    public boolean isVisible(int position) {
        return Helper.isVisible(this, position);
    }


    public int getCenterPosition() {
        return Helper.getCenterPosition(this);
    }
}
