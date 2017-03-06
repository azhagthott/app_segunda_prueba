package com.fbarrios.dev.remedios.view.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.fbarrios.dev.remedios.R;

public class AlarmDetailActivity extends AppCompatActivity {

    private TextView commentDetailTexView;
    private Toolbar toolbar;
    private CollapsingToolbarLayout sharedView;

    private ImageView mainImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_detail);
        setSupportActionBar(toolbar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        sharedView = (CollapsingToolbarLayout) findViewById(R.id.sharedView);
        commentDetailTexView = (TextView) findViewById(R.id.commentDetailTexView);
        mainImage = (ImageView) findViewById(R.id.mainImage);

        String comment = getIntent().getStringExtra("COMMENT");
        String hour = getIntent().getStringExtra("HOUR");
        String minute = getIntent().getStringExtra("MINUTE");

        if (hour != null && minute != null) {
            sharedView.setTitle(hour + ":" + minute);
        }

        if (Integer.valueOf(hour) > 8 && Integer.valueOf(hour) < 20) {
            mainImage.setBackground(ContextCompat.getDrawable(this, R.drawable.sunset_400_240));
        } else {
            mainImage.setBackground(ContextCompat.getDrawable(this, R.drawable.sunrise_400_240));
        }

        if (comment != null) {
            commentDetailTexView.setText(comment);
        }

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
