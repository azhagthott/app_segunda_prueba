package com.fbarrios.dev.remedios.view.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;

import com.fbarrios.dev.remedios.R;
import com.fbarrios.dev.remedios.adapters.AlarmListAdapter;
import com.fbarrios.dev.remedios.model.Alarm;

import java.util.List;

public class MainActivity extends BaseActivity {

    private LinearLayoutManager layoutManager;
    private AlarmListAdapter adapter;
    private RecyclerView recyclerViewAlarm;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViews();

        adapter = new AlarmListAdapter(alarmList(), this);
        layoutManager = new LinearLayoutManager(this);

        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewAlarm.setLayoutManager(layoutManager);
        recyclerViewAlarm.setHasFixedSize(true);
        recyclerViewAlarm.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        fab.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(1500);
                fab.setAnimation(fadeIn);
                fab.setVisibility(View.VISIBLE);
            }
        }, 500);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddAlarmActivity.class);
                intent.putExtra(FRAGMENT_NAME, ADD_ALARM);
                startActivity(intent);
            }
        });
    }


    private void findViews() {
        recyclerViewAlarm = (RecyclerView) findViewById(R.id.recyclerViewAlarm);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    private List<Alarm> alarmList() {
        return new Alarm().listAll(Alarm.class);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        switch (id) {
            case R.id.action_about:
                alert.setIcon(R.mipmap.ic_launcher)
                        .setTitle(R.string.about_title)
                        .setMessage(R.string.about_message)
                        .show();
                break;

            case R.id.action_drug:
                Intent intent = new Intent(MainActivity.this, AddAlarmActivity.class);
                intent.putExtra(FRAGMENT_NAME, ADD_DRUG);
                startActivity(intent);
                break;

            case R.id.action_test:
                alert.setIcon(R.drawable.ic_add_alert)
                        .setTitle(R.string.test_title)
                        .setMessage(R.string.test_message)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //TODO: send notificaction
                                notification();
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //TODO: dismiss
                            }
                        })
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void notification() {

        Notification.Builder notification = new Notification.Builder(this);
        notification
                .setSmallIcon(R.drawable.day)
                .setContentTitle("Remedios")
                .setAutoCancel(true)
                .setContentText("TEST: es hora de tomar su pastilla: Ritalin");


        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        Intent resultIntent = new Intent(this, MainActivity.class);

        stackBuilder.addParentStack(MainActivity.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        notification.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1, notification.build());
    }
}
