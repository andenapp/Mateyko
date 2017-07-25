package com.anden.mateyko;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by ignacio on 19/07/17.
 */

public class Mateyko implements MateykoView.OnDissmisListener{

    private Activity activity;

    private MateykoView frame;

    public Mateyko(Activity activity){
        this.activity = activity;
        this.frame = new MateykoView(activity);
    }

    public Mateyko setView(View view){
        this.frame.addView(view);

        return this;
    }

    public Mateyko setBackground(int resourceId){
        frame.setBackground(resourceId);

        return this;
    }

    public Mateyko setTarget(View target){

        int[] pos = new int[2];

        target.getLocationOnScreen(pos);

        int radius;

        if (target.getHeight() > target.getWidth()) {
            radius = target.getHeight() / 2;
        } else {
            radius = target.getWidth() / 2;
        }

        frame.setStep(new Step(new PointF(pos[0] + target.getWidth() / 2 , pos[1] + target.getHeight() / 2), radius));

        return this;
    }

    public void show(){
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();

        params.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        params.format = PixelFormat.TRANSLUCENT;

        frame.setOnDissmisListener(this);

        activity.getWindowManager().addView(frame, params);
    }

    @Override
    public void onDismiss(MateykoView view) {
        activity.getWindowManager().removeViewImmediate(frame);
    }
}
