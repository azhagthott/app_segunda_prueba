package com.fbarrios.dev.remedios.model;

/**
 * Created by fbarrios80 on 27-02-17.
 */

public interface AlarmCallback {

    void alarmAdded(Alarm alarm);

    void alarmRemove(String msg);

    void error();
}
