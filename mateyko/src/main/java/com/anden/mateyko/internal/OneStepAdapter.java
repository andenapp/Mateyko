package com.anden.mateyko.internal;

import android.view.LayoutInflater;
import android.view.View;

import com.anden.mateyko.widget.MateykoView;

/**
 * Created by ignacio on 28/07/17.
 */

public class OneStepAdapter implements StepAdapter {

    private int stepLayoutResourceId;

    public OneStepAdapter(int stepLayoutResourceId){
        this.stepLayoutResourceId = stepLayoutResourceId;
    }

    @Override
    public View getStepView(MateykoView parent, int position) {
        return LayoutInflater.from(parent.getContext()).inflate(stepLayoutResourceId, parent, false);
    }

    @Override
    public int getStepCount() {
        return 1;
    }
}
