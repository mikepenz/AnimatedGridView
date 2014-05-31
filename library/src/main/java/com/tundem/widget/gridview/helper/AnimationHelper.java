package com.tundem.widget.gridview.helper;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.GridLayoutAnimationController;
import android.view.animation.TranslateAnimation;

/**
 * Created by mikepenz on 31.05.14.
 */
public class AnimationHelper {

    public static GridLayoutAnimationController getLayoutAnimation() {
        AnimationSet set = new AnimationSet(true);
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(40);
        set.addAnimation(animation);
        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        animation.setDuration(75);
        set.addAnimation(animation);
        GridLayoutAnimationController controller = new GridLayoutAnimationController(set);
        return controller;
    }
}
