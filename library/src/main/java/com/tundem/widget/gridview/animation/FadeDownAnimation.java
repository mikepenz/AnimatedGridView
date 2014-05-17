package com.tundem.widget.gridview.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class FadeDownAnimation extends Animation {

    int mToHeight;
    View mView;

    public FadeDownAnimation(View view) {
        this.mView = view;
        this.mToHeight = view.getHeight();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newHeight;
        newHeight = (int) (mToHeight * ((interpolatedTime - 1) * -1));
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