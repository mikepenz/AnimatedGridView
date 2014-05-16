package com.tundem.widget.gridview.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class FadeUpAnimation extends Animation {

    int mFromHeight;
    View mView;

    public FadeUpAnimation(View view) {
        this.mView = view;
        this.mFromHeight = view.getHeight();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newHeight;
        newHeight = (int) (mFromHeight * (1 - interpolatedTime));
        mView.getLayoutParams().height = newHeight;
        mView.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth,
                           int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}