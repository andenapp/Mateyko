package com.anden.mateyko.internal;

import android.view.LayoutInflater;
import android.view.View;

import com.anden.mateyko.R;
import com.anden.mateyko.widget.MateykoView;

/**
 * Created by ignacio on 28/07/17.
 */

public class OneStepAdapter extends BaseStepAdapter {

    private int stepLayoutResourceId;
    private int viewNextResourceId;

    public OneStepAdapter(int stepLayoutResourceId, int viewNextResourceId){
        this.stepLayoutResourceId = stepLayoutResourceId;
        this.viewNextResourceId = viewNextResourceId;
    }

    @Override
    public View getStepView(MateykoView parent, int position) {

        View stepView = LayoutInflater.from(parent.getContext()).inflate(stepLayoutResourceId, parent, false);

        View viewNext = stepView.findViewById(viewNextResourceId);

        if(viewNext != null){
            viewNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveNext();
                }
            });
        }

        return stepView;
    }

    @Override
    public int getStepCount() {
        return 1;
    }
}
