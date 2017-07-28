package com.anden.mateyko.graphics;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.animation.BounceInterpolator;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

/**
 * Created by Ignacio on 22/7/2017.
 */

public class Ring {

    private static final float START_ANGLE = 0f;
    private static final float SWEEP_ANGLE = 359.9999f;

    float innerRadius;
    float outerRadius;

    private PointF center;

    private RectF inner;
    private RectF outer;

    private RectF current;

    private Path path;

    private BounceInterpolator interpolator = new BounceInterpolator();

    public Ring(PointF center, float innerRadius, float outerRadius){
        this.center = center;
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
        inner = new RectF();
        fillRect(inner, center, innerRadius);
        outer = new RectF();
        fillRect(outer, center, outerRadius);
        current = new RectF();
        path = new Path();
    }

    private void fillRect(RectF rectF, PointF center, float radius){
        rectF.left = center.x - radius;
        rectF.top = center.y - radius;
        rectF.right = center.x + radius;
        rectF.bottom = center.y + radius;
    }

    public RectF getInner() {
        return inner;
    }

    public RectF getOuter() {
        return outer;
    }

    public void draw(float delta, Canvas canvas, Paint paint){

        path.reset();

        float radius = (innerRadius) + (interpolator.getInterpolation(delta) * (outerRadius - innerRadius));

        fillRect(current, center, radius);

        double start = toRadians(START_ANGLE);

        path.moveTo((float)(inner.centerX() + innerRadius * cos(start)), (float)(inner.centerY() + innerRadius * sin(start)));
        path.lineTo((float)(inner.centerX() + radius * cos(start)), (float)(inner.centerY() + radius * sin(start)));
        path.arcTo(current, START_ANGLE, SWEEP_ANGLE);
        path.arcTo(current, 70, 90);

        double end = toRadians(START_ANGLE + SWEEP_ANGLE);

        path.lineTo((float)(inner.centerX() + innerRadius * cos(end)), (float)(inner.centerY() + innerRadius * sin(end)));
        path.arcTo(inner, START_ANGLE + SWEEP_ANGLE, -START_ANGLE);
        path.arcTo(inner, 70 + 90, -70);

        canvas.drawPath(path, paint);
    }
}
