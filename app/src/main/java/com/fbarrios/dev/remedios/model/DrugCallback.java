package com.fbarrios.dev.remedios.model;

/**
 * Created by fbarrios80 on 03-03-17.
 */

public interface DrugCallback {

    void drugCallbackAdded(Drug drug);

    void drugCallbackRemoved(Drug drug);

    void error(Exception e);
}
