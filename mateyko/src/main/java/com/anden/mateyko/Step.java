package com.anden.mateyko;

import android.graphics.PointF;

/**
 * Created by ignacio on 19/07/17.
 */

public class Step {

    private PointF start;

    private Ring firstRing;
    private Ring secondRing;

    private float radius;

    public Step(PointF start, float radius){
        this.start = start;
        this.radius = radius;
        init();
    }

    private void init(){
        firstRing = new Ring(start, radius, radius * 1.2f);
        secondRing = new Ring(start, radius * 1.2f, radius * 1.6f);
    }

    public PointF getStart() {
        return start;
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
