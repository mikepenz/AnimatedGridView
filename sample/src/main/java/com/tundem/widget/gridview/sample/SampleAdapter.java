package com.tundem.widget.gridview.sample;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.tundem.widget.gridview.adapter.AnimatedAdapter;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by mikepenz on 17.05.14.
 */
public class SampleAdapter extends AnimatedAdapter {
    int columnCount = 6;
    List<Integer> cells = new LinkedList<Integer>();

    Random r = new Random();

    public SampleAdapter() {
        for (int i = 0; i < 500; i++) {
            cells.add(r.nextInt(10));
        }
    }

    @Override
    public void removeItem(int position) {
        cells.remove(position);
    }

    @Override
    public void addItem(Object item) {
        cells.add((Integer) item);
    }

    @Override
    public int getCount() {
        return cells.size();
    }

    @Override
    public Object getItem(int i) {
        return cells.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        TextView tv;
        if (view != null) {
            tv = (TextView) view;
        } else {
            tv = new TextView(parent.getContext());
            tv.setTextColor(Color.BLACK);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundResource(android.R.color.holo_blue_dark);
        }

        int item_side = parent.getWidth() / columnCount;

        ViewGroup.LayoutParams lp = tv.getLayoutParams();
        if (lp == null) {
            lp = new AbsListView.LayoutParams(item_side, item_side);
        } else {
            lp.height = item_side;
            lp.width = item_side;
        }
        tv.setLayoutParams(lp);

        tv.setText(getItem(i).toString());

        return tv;
    }
}
