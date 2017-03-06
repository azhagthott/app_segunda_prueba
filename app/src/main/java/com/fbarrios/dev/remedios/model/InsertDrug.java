package com.fbarrios.dev.remedios.model;

import android.widget.EditText;

import com.fbarrios.dev.remedios.adapters.DrugListAdapter;

/**
 * Created by fbarrios80 on 05-03-17.
 */

public class InsertDrug {

    private DrugCallback callback;

    public InsertDrug(DrugCallback callback) {
        this.callback = callback;
    }

    public void validate(EditText name, EditText comment, EditText count, DrugListAdapter adapter) {

        try {

            String nameDrug = name.getText().toString();
            String commentDrug = comment.getText().toString();
            String countString = count.getText().toString();

            if (countString.trim().length() > 0) {

                if (Integer.valueOf(count.getText().toString()) > 0) {
                    int countDrug = Integer.valueOf(count.getText().toString());

                    if (nameDrug.trim().length() > 0 && countDrug > 0) {

                        Drug drug = new Drug();
                        drug.setName(nameDrug);
                        drug.setComment(commentDrug);
                        drug.setCount(countDrug);
                        drug.save();
                        callback.drugCallbackAdded(drug);
                    }
                }
            }

            adapter.notifyDataSetChanged();

        } catch (Exception e) {
            callback.error(e);
        }


    }
}
