package com.anden.mateyko.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.anden.mateyko.Mateyko;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.target).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showGuide(view);
            }
        });
    }

    private void showGuide(View target){

        Mateyko mateyko = new Mateyko.Builder(MainActivity.this)
                .setView(R.layout.mateyco_guide_layout, R.id.test_button)
                .setBackground(R.drawable.background)
                .setTarget(target)
                .build();

        mateyko.show();
    }
}
