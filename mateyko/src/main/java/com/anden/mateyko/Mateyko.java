package com.anden.mateyko;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;

import com.anden.mateyko.internal.OneStepAdapter;
import com.anden.mateyko.internal.Step;
import com.anden.mateyko.internal.StepAdapter;
import com.anden.mateyko.widget.MateykoView;

/**
 * Created by ignacio on 19/07/17.
 */

public class Mateyko implements MateykoView.OnDissmisListener{

    private Activity activity;

    private MateykoView frame;

    private StepAdapter adapter;

    private Mateyko(Activity activity){
        this.activity = activity;
        this.frame = new MateykoView(activity);
    }

    public Mateyko setView(View view){
        this.frame.addView(view);

        return this;
    }

    public void setBackground(int resourceId){
        frame.setBackground(resourceId);
    }

    private void setStepAdapter(StepAdapter adapter){
        frame.setAdapter(adapter);
    }

    public void setTarget(View target){

        int[] pos = new int[2];

        target.getLocationOnScreen(pos);

        int radius;

        if (target.getHeight() > target.getWidth()) {
            radius = target.getHeight() / 2;
        } else {
            radius = target.getWidth() / 2;
        }

        frame.setStep(new Step(new PointF(pos[0] + target.getWidth() / 2 , pos[1] + target.getHeight() / 2), radius));
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

    public static class Builder{

        private Activity activity;

        private View target;

        private int resourceId = 0;

        private OneStepAdapter adapter;

        public Builder(Activity activity){
            this.activity = activity;
        }

        public Builder setTarget(View target){
            this.target = target;
            return this;
        }

        public Builder setBackground(int resourceId){
            this.resourceId = resourceId;

            return this;
        }

        public Builder setView(int layoutResourceId, int nextResourceId){
            this.adapter = new OneStepAdapter(layoutResourceId, nextResourceId);
            return this;
        }

        public Mateyko build(){
            Mateyko mateyko = new Mateyko(activity);
            mateyko.setTarget(target);
            mateyko.setStepAdapter(adapter);
            mateyko.setBackground(resourceId);

            return mateyko;
        }

    }
}
