package com.fbarrios.dev.remedios.model;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;

import com.fbarrios.dev.remedios.R;

/**
 * Created by fbarrios80 on 06-03-17.
 */

public class InsertAlarm {

    private AlarmCallback callback;

    public InsertAlarm(AlarmCallback callback) {
        this.callback = callback;
    }

    public void create(TimePicker timePicker, CheckBox monday, CheckBox tuesday, CheckBox wednesday,
                       CheckBox thursday, CheckBox friday, CheckBox saturday, CheckBox sunday,
                       Switch repeat, Spinner drugs, EditText comment) {
        Alarm alarm = new Alarm();

        alarm.setHour(String.valueOf(timePicker.getHour()));
        // Minute
        String minute;
        if (timePicker.getMinute() < 10) {
            minute = "0" + String.valueOf(timePicker.getMinute());
        } else {
            minute = String.valueOf(timePicker.getMinute());
        }
        alarm.setMinute(minute);

        // Set image, day or night
        int imageAlarm;
        if (timePicker.getHour() >= 7 && timePicker.getHour() < 20) {
            imageAlarm = R.drawable.day;
        } else {
            imageAlarm = R.drawable.night;
        }
        alarm.setImage(imageAlarm);

        if (monday.isChecked()) {
            alarm.setMonday(true);
        }

        if (tuesday.isChecked()) {
            alarm.setTuesday(true);
        }

        if (wednesday.isChecked()) {
            alarm.setWednesday(true);
        }

        if (thursday.isChecked()) {
            alarm.setThursday(true);
        }

        if (friday.isChecked()) {
            alarm.setFriday(true);
        }

        if (saturday.isChecked()) {
            alarm.setSaturday(true);
        }

        if (sunday.isChecked()) {
            alarm.setSunday(true);
        }

        alarm.setRepeatable(repeat.isChecked());

        alarm.setDrug(drugs.getSelectedItem().toString());

        alarm.setComment(comment.getText().toString());

        callback.alarmAdded(alarm);

        alarm.save();

    }
}
