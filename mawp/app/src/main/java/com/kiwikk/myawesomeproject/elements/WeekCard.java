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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.kiwikk.myawesomeproject.R;

public class WeekCard extends BottomSheetDialogFragment {
    WeekButton weekButton;
    EditText input;
    LinearLayout tasksLayout;
    LayoutInflater inflater;

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


    private void createDays() {
        TableRow tableRow = getView().findViewById(R.id.switchTableRow);
//        tableRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
//        tableRow.setPadding(20,0,0,0);

        for (int i = 0; i < 7; i++) {
            DaySwitch daySwitch = new DaySwitch(this.getContext());
            daySwitch.setLayoutParams(new TableRow.LayoutParams(130, 130));
            //TODO: ADD NUMBER OF THE DAY TO THE BUTTON

            tableRow.addView(daySwitch);
        }
    }

}
