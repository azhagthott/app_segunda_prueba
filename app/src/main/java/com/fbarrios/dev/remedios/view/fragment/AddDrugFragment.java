package com.fbarrios.dev.remedios.view.fragment;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fbarrios.dev.remedios.R;
import com.fbarrios.dev.remedios.adapters.DrugListAdapter;
import com.fbarrios.dev.remedios.model.Drug;
import com.fbarrios.dev.remedios.model.DrugCallback;
import com.fbarrios.dev.remedios.model.InsertDrug;

import java.util.List;

public class AddDrugFragment extends Fragment implements DrugCallback {

    private FloatingActionButton fabAddDrug;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerViewDrug;
    private Context context = getContext();
    private DrugListAdapter adapter;
    private Button saveDialogButton;
    private Button cancelDialogButton;

    private EditText drugEditText;
    private EditText commentEditText;
    private EditText countEditText;

    private DrugCallback callback = this;

    public AddDrugFragment() {
    }

    public AddDrugFragment newInstance() {
        return new AddDrugFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_drug, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);

        adapter = new DrugListAdapter(listDrugs(), context);
        layoutManager = new LinearLayoutManager(context);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewDrug.setLayoutManager(layoutManager);
        recyclerViewDrug.setHasFixedSize(true);
        recyclerViewDrug.setAdapter(adapter);


        fabAddDrug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDrugDialog(getContext());
            }
        });
    }

    private void findViews(View view) {
        recyclerViewDrug = (RecyclerView) view.findViewById(R.id.recyclerViewDrug);
        fabAddDrug = (FloatingActionButton) view.findViewById(R.id.fabAddDrug);
    }

    private List<Drug> listDrugs() {
        return Drug.listAll(Drug.class);
    }

    private void addDrugDialog(final Context context) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_drug_dialog);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        saveDialogButton = (Button) dialog.findViewById(R.id.saveDialogButton);

        saveDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drugEditText = (EditText) dialog.findViewById(R.id.drugEditText);
                commentEditText = (EditText) dialog.findViewById(R.id.commentEditText);
                countEditText = (EditText) dialog.findViewById(R.id.countEditText);

                InsertDrug insert = new InsertDrug(callback);
                insert.validate(drugEditText, commentEditText, countEditText, adapter);
                dialog.dismiss();
            }
        });

        cancelDialogButton = (Button) dialog.findViewById(R.id.cancelDialogButton);
        cancelDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        adapter.notifyDataSetChanged();
        dialog.show();
    }

    @Override
    public void drugCallbackAdded(Drug drug) {
        adapter.notifyDataSetChanged();
        Toast.makeText(getContext(), "Agregado: " + drug.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void drugCallbackRemoved(Drug drug) {
        Toast.makeText(getContext(), "Eliminado: " + drug.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error(Exception e) {
        Log.d("ERROR", "add drug fail: " + e);
        Toast.makeText(context, "Nombre y cantidad no pueden ser vacios", Toast.LENGTH_SHORT).show();
    }
}
