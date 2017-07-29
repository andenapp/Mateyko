package com.anden.mateyko.internal;

import java.util.Observable;

/**
 * Created by Ignacio on 29/7/2017.
 */

public class StepObservable{

    private StepObserver observer;

    public StepObservable(){}

    public void registerObserver(StepObserver observer){
        this.observer = observer;
    }

    public void unregisterObserver(){
        this.observer = null;
    }

    public void notifyNext(){
        this.observer.onNext();
    }
}
