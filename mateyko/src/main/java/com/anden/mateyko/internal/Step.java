package com.anden.mateyko.internal;

import android.graphics.PointF;

import com.anden.mateyko.graphics.Ring;

/**
 * Handle info for target point
 */

public class Step {

    /*Target point center*/
    private PointF targetCenter;

    private Ring firstRing;
    private Ring secondRing;

    private float radius;

    public Step(PointF start, float radius){
        this.targetCenter = start;
        this.radius = radius;
        init();
    }

    private void init(){
        firstRing = new Ring(targetCenter, radius, radius * 1.2f);
        secondRing = new Ring(targetCenter, radius * 1.2f, radius * 1.6f);
    }

    public PointF getTargetCenter() {
        return targetCenter;
    }

    public float getRadius() {
        return radius;
    }

    public Ring getFirstRing() {
        return firstRing;
    }

    public Ring getSecondRing() {
        return secondRing;
    }
}
