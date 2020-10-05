package com.kiwikk.myawesomeproject.elements;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.kiwikk.myawesomeproject.R;
import com.kiwikk.myawesomeproject.fragments.HomeFragment;

import java.time.LocalDate;

public class WeekCard extends BottomSheetDialogFragment {
    WeekButton weekButton;
    EditText input;
    LinearLayout tasksLayout;
    LayoutInflater inflater;
    TableRow switchRow, switchTextRow;
    LocalDate myDate;

    public WeekCard(WeekButton weekButton) {
        this.weekButton = weekButton;
        myDate = HomeFragment.getPerson().getLDate().plusWeeks(weekButton.getID());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_layout, container, false);
        this.inflater = inflater;
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        createDays();
        setMonth();
        addTask();
    }

    private void addTask() {
        ImageButton createButton = getView().findViewById(R.id.addButton);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
                alertBuilder.setTitle("Добавляем план на неделю");

                input = new EditText(getContext());
                alertBuilder.setView(input);
                final String[] task = new String[1];

                alertBuilder.setPositiveButton("Продолжим", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        task[0] = String.valueOf(input.getText());
                        createTask(task[0]);
                    }
                });


                AlertDialog ad = alertBuilder.create();
                ad.show();
            }
        });
    }

    private void createTask(String text) {
        tasksLayout = getView().findViewById(R.id.tasksLayout);
        Task task = new Task(getContext(), getView());
        task.setText(text);
        tasksLayout.addView(task);
    }

    private void setMonth() {
        TextView textView = getView().findViewById(R.id.dateTextView);
        StringBuilder month = new StringBuilder();
        month.append(myDate.getMonth());

        if (myDate.getMonth() != myDate.plusDays(7).getMonth())
            month.append(" - ").append(myDate.plusDays(7).getMonth());

        month.append(" ").append(myDate.getYear());
        textView.setText(month.toString());
    }

    private void createDays() {
        switchRow = getView().findViewById(R.id.switchTableRow);
        switchTextRow = getView().findViewById(R.id.switchTextTableRow);

        LinearLayout.LayoutParams pr = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pr.setMargins(50, 0, 0, 0);
        switchTextRow.setLayoutParams(pr);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(0, -110, 0, 0);
        switchRow.setLayoutParams(p);


        TableRow.LayoutParams params = new TableRow.LayoutParams(130, 130);

        for (int i = 0; i < 7; i++) {
            DaySwitch daySwitch = new DaySwitch(this.getContext(), i);
            daySwitch.setLayoutParams(params);

            TextView textView = new TextView(this.getContext());
            textView.setText(Integer.toString(myDate.plusDays(i).getDayOfMonth()));
            textView.setLayoutParams(params);

            switchTextRow.addView(textView);
            switchRow.addView(daySwitch);
        }
    }

}
