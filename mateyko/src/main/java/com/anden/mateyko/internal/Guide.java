package com.anden.mateyko.internal;

import java.util.ArrayList;
import java.util.List;

/**
 * Handle one or more {@link Step}
 */
public class Guide {

    private List<Step> steps;

    public Guide(){
        this.steps = new ArrayList<>();
    }

    public void addStep(Step step){
        this.steps.add(step);
    }

}
