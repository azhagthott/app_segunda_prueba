package com.fbarrios.dev.remedios.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by fbarrios80 on 22-02-17.
 */

public class Drug extends SugarRecord implements Serializable {

    private String name;
    private String comment;
    private int count;

    private DrugCallback callback;

    public Drug() {
    }

    public Drug(DrugCallback callback) {
        this.callback = callback;
    }

    public Drug(String name, String comment, int count) {
        this.name = name;
        this.comment = comment;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
