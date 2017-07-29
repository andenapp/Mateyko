package com.anden.mateyko.internal;

/**
 * Created by Ignacio on 29/7/2017.
 */

public abstract class BaseStepAdapter implements StepAdapter {

    private StepObservable stepObservable = new StepObservable();

    @Override
    public void registerObserver(StepObserver observer) {
        stepObservable.registerObserver(observer);
    }

    @Override
    public void unregisterObserver(StepObserver observer) {
        stepObservable.unregisterObserver();
    }

    public void moveNext(){
        stepObservable.notifyNext();
    }
}
