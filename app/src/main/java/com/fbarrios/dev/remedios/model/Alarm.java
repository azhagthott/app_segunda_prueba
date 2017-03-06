package com.fbarrios.dev.remedios.model;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by fbarrios80 on 22-02-17.
 */

public class Alarm extends SugarRecord {

    private AlarmCallback callback;
    private String comment;
    private int image;
    private String hour;
    private String minute;
    private boolean repeatable;
    private String drug;

    private boolean monday, tuesday, wednesday, thursday, friday, saturday, sunday;


    public Alarm(AlarmCallback callback) {
        this.callback = callback;
    }

    public Alarm() {
    }

    public Alarm(String comment, int image, String hour, String minute, boolean repeatable, List<Integer> dayOfWeek) {
        this.comment = comment;
        this.image = image;
        this.hour = hour;
        this.minute = minute;
        this.repeatable = repeatable;

    }

    public Alarm(String comment, int image, String hour, String minute, boolean repeatable, String drug, List<Integer> dayOfWeek) {
        this.comment = comment;
        this.image = image;
        this.hour = hour;
        this.minute = minute;
        this.repeatable = repeatable;
        this.drug = drug;
    }

    public Alarm(String comment, int image, String hour, String minute, boolean repeatable, String drug, boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday, boolean saturday, boolean sunday) {
        this.comment = comment;
        this.image = image;
        this.hour = hour;
        this.minute = minute;
        this.repeatable = repeatable;
        this.drug = drug;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public boolean isRepeatable() {
        return repeatable;
    }

    public void setRepeatable(boolean repeatable) {
        this.repeatable = repeatable;
    }

    public void removeAlarm(String msg) {
        callback.alarmRemove(msg);
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isMonday() {
        return monday;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public boolean isFriday() {
        return friday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public boolean isSaturday() {
        return saturday;
    }

    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }

    public boolean isSunday() {
        return sunday;
    }

    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }
}
