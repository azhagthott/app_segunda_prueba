package com.fbarrios.dev.remedios.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fbarrios.dev.remedios.R;
import com.fbarrios.dev.remedios.model.Drug;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fbarrios80 on 27-02-17.
 */

public class DrugListAdapter extends RecyclerView.Adapter<DrugListAdapter.ViewHolder> {

    private List<Drug> drugs = new ArrayList<>();
    private Context context;
    private ViewHolder viewHolder;
    private LayoutInflater layoutInflater;

    public DrugListAdapter(List<Drug> drugs, Context context) {
        this.drugs = drugs;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drug_list_view, parent, false);
        viewHolder = new DrugListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Drug drug = drugs.get(position);

        holder.countTextView.setText(String.valueOf(drug.getCount()));
        holder.drugNameTextView.setText(drug.getName());
        holder.drugDescriptionTextView.setText(drug.getComment());

        holder.drugNameDescriptionLinearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                final int removeDrug = holder.getAdapterPosition();

                new AlertDialog.Builder(view.getContext())
                        .setIcon(ContextCompat.getDrawable(view.getContext(), R.mipmap.ic_launcher))
                        .setTitle(R.string.app_name)
                        .setMessage("¿Desea eliminar el medicamento?")
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                drug.delete();
                                drugs.remove(removeDrug);
                                notifyItemRemoved(removeDrug);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();

                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return drugs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout drugCountLinearLayout;
        private LinearLayout drugNameDescriptionLinearLayout;
        private TextView countTextView;
        private TextView drugNameTextView;
        private TextView drugDescriptionTextView;

        public ViewHolder(View view) {
            super(view);

            drugCountLinearLayout = (LinearLayout) view.findViewById(R.id.drugCountLinearLayout);
            drugNameDescriptionLinearLayout = (LinearLayout) view.findViewById(R.id.drugNameDescriptionLinearLayout);

            countTextView = (TextView) view.findViewById(R.id.countTextView);
            drugNameTextView = (TextView) view.findViewById(R.id.drugNameTextView);
            drugDescriptionTextView = (TextView) view.findViewById(R.id.drugDescriptionTextView);

        }

    }

}


