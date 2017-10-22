package com.example.apple.bizinabi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.apple.bizinabi.R;
import com.nifty.cloud.mb.core.NCMB;


/**
 * Created by apple on 2017/10/21.
 */

public class StartActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        String KEY_1 = "4f5b8c87586e2d05d2e0d3ee3199d57165103f1d3739bd1aeb1b2216cb717b45";
        String KEY_2 = "95d95b41f49a7088ec991e6c5da12408e72db60dd1c337fd87f9666a238fd584";

        NCMB.initialize(this.getApplicationContext(),KEY_1,KEY_2);

        ImageButton startButton = (ImageButton)findViewById(R.id.start_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, TabActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
