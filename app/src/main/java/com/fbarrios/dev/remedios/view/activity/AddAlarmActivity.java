package com.fbarrios.dev.remedios.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;

import com.fbarrios.dev.remedios.R;
import com.fbarrios.dev.remedios.view.fragment.AddAlarmFragment;
import com.fbarrios.dev.remedios.view.fragment.AddDrugFragment;

public class AddAlarmActivity extends BaseActivity {

    private String fragmentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            fragmentName = extra.getString(FRAGMENT_NAME);

            if (fragmentName.equals(ADD_ALARM)) {
                callFragment(new AddAlarmFragment());

            } else if (fragmentName.equals(ADD_DRUG)) {
                callFragment(new AddDrugFragment());
            }
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void callFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_add_alarm, fragment).commit();
    }
}
