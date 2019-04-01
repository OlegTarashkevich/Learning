package com.prilaga.news.util;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by Oleg Tarashkevich on 05/04/2017.
 */

public final class AnimationUtil {

    public static ValueAnimator rotate(View view, long duration) {
        return rotate(view, duration, 360.0F, 0.0F);
    }

    public static ValueAnimator rotate(View view, long duration, float... values) {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(view, "rotation", values);
        rotation.setDuration(duration);
        return rotation;
    }
}
