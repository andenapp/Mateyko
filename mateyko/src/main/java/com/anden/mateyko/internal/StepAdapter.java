package com.anden.mateyko.internal;

import android.view.View;

import com.anden.mateyko.widget.MateykoView;

/**
 * Created by ignacio on 28/07/17.
 */

public interface StepAdapter {
    View getStepView(MateykoView parent, int position);
    int getStepCount();
    void registerObserver(StepObserver observer);
    void unregisterObserver(StepObserver observer);
}
