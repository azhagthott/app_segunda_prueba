package com.fbarrios.dev.remedios.view.fragment;

import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.fbarrios.dev.remedios.R;
import com.fbarrios.dev.remedios.model.Alarm;
import com.fbarrios.dev.remedios.model.AlarmCallback;
import com.fbarrios.dev.remedios.model.Drug;
import com.fbarrios.dev.remedios.model.InsertAlarm;
import com.fbarrios.dev.remedios.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class AddAlarmFragment extends Fragment implements View.OnClickListener, AlarmCallback {

    private static final int MELODY_SELECTED_RC = 100;

    private TimePicker timePicker;
    private Switch repeatableSwitch;
    private EditText descriptionEditText;
    private Button saveButton;

    // day of the week
    private CheckBox mondayCheckBox;
    private CheckBox tuesdayCheckBox;
    private CheckBox wednesdayCheckBox;
    private CheckBox thursdayCheckBox;
    private CheckBox fridayCheckBox;
    private CheckBox saturdayCheckBox;
    private CheckBox sundayCheckBox;

    //medlody selector
    private Button melodySelector;

    private Spinner drugSpinner;
    private int imageAlarm;

    public AddAlarmFragment() {
    }

    public AddAlarmFragment newInstance() {
        return new AddAlarmFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_alarm, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);

        List<Drug> drugList = Drug.listAll(Drug.class);

        List<String> drugNamesList = new ArrayList<>();

        for (Drug drug : drugList) {
            drugNamesList.add(drug.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, drugNamesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drugSpinner.setAdapter(adapter);
    }

    private void findView(View view) {

        timePicker = (TimePicker) view.findViewById(R.id.timePicker);
        repeatableSwitch = (Switch) view.findViewById(R.id.repeatableSwitch);
        descriptionEditText = (EditText) view.findViewById(R.id.descriptionEditText);
        saveButton = (Button) view.findViewById(R.id.saveButton);

        mondayCheckBox = (CheckBox) view.findViewById(R.id.mondayCheckBox);
        tuesdayCheckBox = (CheckBox) view.findViewById(R.id.tuesdayCheckBox);
        wednesdayCheckBox = (CheckBox) view.findViewById(R.id.wednesdayCheckBox);
        thursdayCheckBox = (CheckBox) view.findViewById(R.id.thursdayCheckBox);
        fridayCheckBox = (CheckBox) view.findViewById(R.id.fridayCheckBox);
        saturdayCheckBox = (CheckBox) view.findViewById(R.id.saturdayCheckBox);
        sundayCheckBox = (CheckBox) view.findViewById(R.id.sundayCheckBox);

        drugSpinner = (Spinner) view.findViewById(R.id.drugSpinner);

        melodySelector = (Button) view.findViewById(R.id.melodySelector);

        melodySelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);

                startActivityForResult(intent, MELODY_SELECTED_RC);

                Log.d("TAG", "onClick: " + intent.getStringExtra(RingtoneManager.EXTRA_RINGTONE_DEFAULT_URI));
            }
        });

        saveButton.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MELODY_SELECTED_RC) {
            //TODO: read melody selected
        }
    }

    private void saveAlarm() {

        InsertAlarm insertAlarm = new InsertAlarm(this);
        insertAlarm.create(timePicker, mondayCheckBox, tuesdayCheckBox, wednesdayCheckBox, thursdayCheckBox, fridayCheckBox, saturdayCheckBox, sundayCheckBox, repeatableSwitch, drugSpinner, descriptionEditText);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.saveButton) {
            saveAlarm();
            startActivity(new Intent(getContext(), MainActivity.class));
        }

    }

    @Override
    public void alarmAdded(Alarm alarm) {
        Toast.makeText(getContext(), "Alarma agregada", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void alarmRemove(String msg) {

    }

    @Override
    public void error() {
        Toast.makeText(getContext(), "Oops algo pasó!", Toast.LENGTH_SHORT).show();
    }
}
