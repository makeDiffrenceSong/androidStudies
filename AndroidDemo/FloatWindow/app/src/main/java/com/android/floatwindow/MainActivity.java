package com.android.floatwindow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnShow;
    private Button btnHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShow = findViewById(R.id.view_show);
        btnHide = findViewById(R.id.view_hide);

        btnShow.setOnClickListener(this);
        btnHide.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,FloatControlService.class);
        switch (v.getId()) {
            case R.id.view_show:
                intent.putExtra(FloatControlService.ACTION,FloatControlService.SHOW);
                break;
            case R.id.view_hide:
                intent.putExtra(FloatControlService.ACTION,FloatControlService.HIDE);
                break;
        }
        startService(intent);
    }
}
