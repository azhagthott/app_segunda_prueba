package com.fbarrios.dev.remedios.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.fbarrios.dev.remedios.R;
import com.fbarrios.dev.remedios.model.Alarm;
import com.fbarrios.dev.remedios.model.AlarmCallback;
import com.fbarrios.dev.remedios.view.activity.AlarmDetailActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by fbarrios80 on 22-02-17.
 */

public class AlarmListAdapter extends RecyclerView.Adapter<AlarmListAdapter.ViewHolder> {

    private List<Alarm> alarms = new ArrayList<>();
    private Context context;
    private ViewHolder viewHolder;
    private AlarmCallback callback;

    public AlarmListAdapter(List<Alarm> alarms, Context context) {
        this.alarms = alarms;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_list_view, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Alarm alarm = alarms.get(position);

        holder.alarmHourTextView.setText(alarm.getHour() + ":" + alarm.getMinute());
        holder.alarmDescriptionTextView.setText(alarm.getComment());
        holder.alarmIconImageView.setImageDrawable(ContextCompat.getDrawable(context, alarm.getImage()));
        holder.repeatableCheckBox.setChecked(alarm.isRepeatable());

        holder.mondayCheckBox.setChecked(alarm.isMonday());
        holder.tuesdayCheckBox.setChecked(alarm.isTuesday());
        holder.wednesdayCheckBox.setChecked(alarm.isWednesday());
        holder.thursdayCheckBox.setChecked(alarm.isThursday());
        holder.fridayCheckBox.setChecked(alarm.isFriday());
        holder.saturdayCheckBox.setChecked(alarm.isSaturday());
        holder.sundayCheckBox.setChecked(alarm.isSunday());

        holder.mondayCheckBox.setEnabled(false);
        holder.tuesdayCheckBox.setEnabled(false);
        holder.wednesdayCheckBox.setEnabled(false);
        holder.thursdayCheckBox.setEnabled(false);
        holder.fridayCheckBox.setEnabled(false);
        holder.saturdayCheckBox.setEnabled(false);
        holder.sundayCheckBox.setEnabled(false);

        holder.sharedView.isHapticFeedbackEnabled();
        holder.sharedView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);

                int removeAlarm = holder.getAdapterPosition();
                alertDialog("Hola!", "¿Quieres eliminar esta alarma?", alarm, removeAlarm);
                return false;
            }
        });

        holder.sharedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), AlarmDetailActivity.class);

                intent.putExtra("HOUR", alarm.getHour());
                intent.putExtra("MINUTE", alarm.getMinute());
                intent.putExtra("COMMENT", alarm.getComment());

                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation((Activity) v.getContext(), holder.sharedView, "mainTransition");

                v.getContext().startActivity(intent, options.toBundle());
            }
        });

        holder.alarmOnOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int disableAlarm = holder.getAdapterPosition();

                if (!buttonView.isChecked()) {
                    Toast.makeText(context, "Alarma descativada", Toast.LENGTH_SHORT).show();
                    holder.repeatableCheckBox.setEnabled(false);

                } else {
                    Toast.makeText(context, "Alarma activada", Toast.LENGTH_SHORT).show();
                    holder.repeatableCheckBox.setEnabled(true);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView alarmHourTextView;
        private TextView alarmDescriptionTextView;
        private ImageView alarmIconImageView;
        private CheckBox repeatableCheckBox;

        private CheckBox mondayCheckBox;
        private CheckBox tuesdayCheckBox;
        private CheckBox wednesdayCheckBox;
        private CheckBox thursdayCheckBox;
        private CheckBox fridayCheckBox;
        private CheckBox saturdayCheckBox;
        private CheckBox sundayCheckBox;

        private Switch alarmOnOffSwitch;

        private CardView sharedView;

        public ViewHolder(View view) {
            super(view);

            sharedView = (CardView) view.findViewById(R.id.sharedView);
            alarmHourTextView = (TextView) view.findViewById(R.id.alarmHourTextView);
            alarmDescriptionTextView = (TextView) view.findViewById(R.id.alarmDescriptionTextView);
            alarmIconImageView = (ImageView) view.findViewById(R.id.alarmIconImageView);
            repeatableCheckBox = (CheckBox) view.findViewById(R.id.repeatableCheckBox);

            mondayCheckBox = (CheckBox) view.findViewById(R.id.mondayCheckBox);
            tuesdayCheckBox = (CheckBox) view.findViewById(R.id.tuesdayCheckBox);
            wednesdayCheckBox = (CheckBox) view.findViewById(R.id.wednesdayCheckBox);
            thursdayCheckBox = (CheckBox) view.findViewById(R.id.thursdayCheckBox);
            fridayCheckBox = (CheckBox) view.findViewById(R.id.fridayCheckBox);
            saturdayCheckBox = (CheckBox) view.findViewById(R.id.saturdayCheckBox);
            sundayCheckBox = (CheckBox) view.findViewById(R.id.sundayCheckBox);

            alarmOnOffSwitch = (Switch) view.findViewById(R.id.alarmOnOffSwitch);

        }
    }

    private void alertDialog(String title, String msg, final Alarm alarm, final int position) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);

        alert.setIcon(R.mipmap.ic_launcher)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alarm.delete();
                        alarms.remove(position);
                        notifyItemRemoved(position);

                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO: dismiss
                    }
                })
                .show();
    }
}
