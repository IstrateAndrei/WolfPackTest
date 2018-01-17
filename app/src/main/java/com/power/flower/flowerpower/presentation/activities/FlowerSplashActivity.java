package com.power.flower.flowerpower.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.power.flower.flowerpower.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Petre-pc on 1/16/2018.
 */

public class FlowerSplashActivity extends AppCompatActivity {

    @BindView(R.id.enter_button) Button enterButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flower_splash_layout);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.enter_button})
    public void onButtonClicked(View v)
    {
        switch (v.getId())
        {
            case R.id.enter_button:
                moveToMainActivity();
                break;
        }
    }

    public void moveToMainActivity()
    {
        Intent toMainIntent = new Intent(FlowerSplashActivity.this, MainActivity.class);
        startActivity(toMainIntent);
        this.finish();
    }
}
