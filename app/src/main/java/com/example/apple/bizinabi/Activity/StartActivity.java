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

        NCMB.initialize(this.getApplicationContext(),"c627d792fb6024d4e751895717030b9834b1095dd30a2f4151c42f4f925fa72c","95d95b41f49a7088ec991e6c5da12408e72db60dd1c337fd87f9666a238fd584");

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
