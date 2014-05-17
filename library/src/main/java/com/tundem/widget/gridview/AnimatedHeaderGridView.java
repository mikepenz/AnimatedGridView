package com.tundem.widget.gridview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.WrapperListAdapter;

import com.tundem.widget.gridview.adapter.AnimatedAdapter;
import com.tundem.widget.gridview.animation.FadeUpAnimation;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by mikepenz on 16.05.14.
 */
public class AnimatedHeaderGridView extends HeaderGridView {
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
    public void animateDeleteRow(Set<Integer> rows, int duration) {
        Set<Integer> cells = new TreeSet<Integer>();
        for (int row : rows) {
            for (int i = 0; i < getNumColumns(); i++) {
                cells.add(row * getNumColumns() + i);
            }
        }
        animateDeleteCells(cells, duration);
    }

    public void animateDeleteCells(final Set<Integer> cells, int duration) {
        final List<View> views = new LinkedList<View>();

        int fieldOffset = 0;
        if (getAdapter() instanceof HeaderViewGridAdapter || getAdapter() instanceof HeaderViewListAdapter) {
            fieldOffset = getNumColumns();
        }
        for (int removedFieldToAnimate : cells) {
            final View v = getViewByPosition(removedFieldToAnimate + fieldOffset);
            if (v != null) {
                views.add(v);
            }
        }

        int tempHeight = -1;
        for (int i = 0; i < views.size(); i++) {
            View v = views.get(i);
            tempHeight = v.getHeight();

            FadeUpAnimation sa = new FadeUpAnimation(v);
            sa.setDuration(duration);
            v.startAnimation(sa);
        }

        BaseAdapter adapter = getBaseAdapter(getAdapter());
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }


        final int height = tempHeight;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (height != -1) {
                    for (View v : views) {
                        v.getLayoutParams().height = height;
                        v.requestLayout();
                        v.clearAnimation();
                    }
                }

                BaseAdapter adapter = getBaseAdapter(getAdapter());
                if (adapter != null && adapter instanceof AnimatedAdapter) {
                    AnimatedAdapter animatedAdapter = ((AnimatedAdapter) adapter);

                    int removedFields = 0;
                    for (int cell : cells) {
                        animatedAdapter.removeItem(cell - removedFields);
                        removedFields = removedFields + 1;
                    }
                }

                adapter.notifyDataSetChanged();

                onAnimationFinish();
            }
        }, duration + 50);
    }

    public BaseAdapter getBaseAdapter(Adapter adapter) {
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

    /**
     * LISTENER!!
     */

    private AnimationListener animationListener;

    public void setAnimationListener(AnimationListener animationListener) {
        this.animationListener = animationListener;
    }

    public interface AnimationListener {
        public void onAnimationFinish();
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
        int pos = position;
        if (position <= getFirstVisiblePosition()) {
            pos = position - (getChildCount() / 2);
            if (pos < 0) {
                pos = 0;
            }
            smoothScrollToPosition(pos);
        } else {
            pos = position + (getChildCount() / 2);
            if (pos >= getCount()) {
                pos = getCount() - 1;
                if (pos < 0) {
                    pos = 0;
                }
            }
        }
        smoothScrollToPosition(pos);
    }

    /**
     * HELPER METHODS!!
     */

    public View getViewByPosition(int position) {
        int firstPosition = this.getFirstVisiblePosition();
        int lastPosition = this.getLastVisiblePosition();

        if ((position < firstPosition) || (position > lastPosition)) {
            return null;
        }

        return this.getChildAt(position - firstPosition);
    }

    /**
     * Helper to calculate if a specific position is visible
     *
     * @param position
     * @return
     */

    public boolean isVisible(int position) {
        int wantedPosition = position; // Whatever position you're looking for
        int firstPosition = getFirstVisiblePosition(); // - getHeaderViewsCount(); // This is the same as child #0
        int wantedChild = wantedPosition - firstPosition;
        // Say, first visible position is 8, you want position 10, wantedChild will now be 2
        // So that means your view is child #2 in the ViewGroup:
        if (wantedChild < 0 || wantedChild >= getChildCount()) {
            return false;
        }

        return true;
    }


    public int getCenterPosition() {
        return getFirstVisiblePosition() + (getChildCount() / 2); // - getHeaderViewsCount(); // This is the same as child #0
    }
}
