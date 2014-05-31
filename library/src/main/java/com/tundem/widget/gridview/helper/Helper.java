package com.tundem.widget.gridview.helper;

import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.HeaderViewListAdapter;

import com.tundem.widget.gridview.HeaderGridView;
import com.tundem.widget.gridview.IAnimatedGridView;
import com.tundem.widget.gridview.adapter.AnimatedAdapter;
import com.tundem.widget.gridview.animation.ScaleDownAnimation;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by mikepenz on 17.05.14.
 */


public class Helper {

    public static void animateAddCells(IAnimatedGridView gridView, LinkedList<Object> cells, int duration) {
        AnimatedAdapter aa = gridView.getAnimatedAdapter();
        if (aa != null) {
            aa.duration = duration;


            boolean isVisible = gridView.isVisible(aa.getCount() - 1);
            for (Object o : cells) {
                aa.addItem(o, isVisible);
            }

            aa.notifyDataSetChanged();
        }
    }

    public static void animateDeleteRow(IAnimatedGridView gridView, Set<Integer> rows, int duration) {
        Set<Integer> cells = new TreeSet<Integer>();
        for (int row : rows) {
            for (int i = 0; i < gridView.getNumColumns(); i++) {
                cells.add(row * gridView.getNumColumns() + i);
            }
        }
        animateDeleteCells(gridView, cells, duration);
    }

    public static void animateDeleteCells(final IAnimatedGridView gridView, final Set<Integer> cells, int duration) {
        final List<View> views = new LinkedList<View>();

        int fieldOffset = 0;
        if (gridView.getAdapter() instanceof HeaderGridView.HeaderViewGridAdapter || gridView.getAdapter() instanceof HeaderViewListAdapter) {
            fieldOffset = gridView.getNumColumns();
        }
        for (int removedFieldToAnimate : cells) {
            final View v = gridView.getViewByPosition(removedFieldToAnimate + fieldOffset);
            if (v != null) {
                views.add(v);
            }
        }

        int tempHeight = -1;
        for (int i = 0; i < views.size(); i++) {
            View v = views.get(i);
            tempHeight = v.getHeight();

            ScaleDownAnimation sa = new ScaleDownAnimation(v);
            sa.setDuration(duration);

            ViewCompat.setHasTransientState(v, true);
            v.startAnimation(sa);
        }

        /*
        BaseAdapter adapter = gridView.getBaseAdapter();
         if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        */


        final int height = tempHeight;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (height != -1) {
                    for (View v : views) {
                        v.getLayoutParams().height = height;
                    }
                }

                for (View v : views) {
                    ViewCompat.setHasTransientState(v, false);
                    v.clearAnimation();
                    v.invalidate();
                }

                BaseAdapter adapter = gridView.getBaseAdapter();
                if (adapter != null && adapter instanceof AnimatedAdapter) {
                    AnimatedAdapter animatedAdapter = ((AnimatedAdapter) adapter);

                    int removedFields = 0;
                    for (int cell : cells) {
                        animatedAdapter.removeItem(cell - removedFields);
                        removedFields = removedFields + 1;
                    }
                }

                adapter.notifyDataSetChanged();

                gridView.onAnimationFinish();
            }
        }, duration + 50);
    }


    /**
     * A SMALL EXTRA FOR THOSE WHO LOVE OPEN SOURCE ;D
     */

    /**
     * smoothScroll a specific item to the center of the list :D
     *
     * @param position
     */

    public static void smoothScrollToCenterPosition(GridView gridView, int position) {
        int pos = position;
        if (position <= gridView.getFirstVisiblePosition()) {
            pos = position - (gridView.getChildCount() / 2);
            if (pos < 0) {
                pos = 0;
            }
            gridView.smoothScrollToPosition(pos);
        } else {
            pos = position + (gridView.getChildCount() / 2);
            if (pos >= gridView.getCount()) {
                pos = gridView.getCount() - 1;
                if (pos < 0) {
                    pos = 0;
                }
            }
        }
        gridView.smoothScrollToPosition(pos);
    }

    /**
     * HELPER METHODS!!
     */

    public static View getViewByPosition(GridView gridView, int position) {
        int firstPosition = gridView.getFirstVisiblePosition();
        int lastPosition = gridView.getLastVisiblePosition();

        if ((position < firstPosition) || (position > lastPosition)) {
            return null;
        }

        return gridView.getChildAt(position - firstPosition);
    }

    /**
     * Helper to calculate if a specific position is visible
     *
     * @param position
     * @return
     */

    public static boolean isVisible(GridView gridView, int position) {
        int wantedPosition = position; // Whatever position you're looking for
        int firstPosition = gridView.getFirstVisiblePosition(); // - getHeaderViewsCount(); // This is the same as child #0
        int wantedChild = wantedPosition - firstPosition;
        // Say, first visible position is 8, you want position 10, wantedChild will now be 2
        // So that means your view is child #2 in the ViewGroup:
        if (wantedChild < 0 || wantedChild >= gridView.getChildCount()) {
            return false;
        }

        return true;
    }


    public static int getCenterPosition(GridView gridView) {
        return gridView.getFirstVisiblePosition() + (gridView.getChildCount() / 2); // - getHeaderViewsCount(); // This is the same as child #0
    }
}
