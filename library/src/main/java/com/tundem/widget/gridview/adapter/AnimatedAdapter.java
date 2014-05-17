package com.tundem.widget.gridview.adapter;

import android.widget.BaseAdapter;

/**
 * Created by mikepenz on 16.05.14.
 */
public abstract class AnimatedAdapter extends BaseAdapter {
    abstract public void removeItem(int position);

    abstract public void addItem(Object item);
}
