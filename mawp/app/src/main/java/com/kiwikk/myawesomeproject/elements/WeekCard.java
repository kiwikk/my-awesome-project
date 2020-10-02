package com.kiwikk.myawesomeproject.elements;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ViewGroup;
import android.widget.TableRow;

public class WeekCard extends AlertDialog.Builder {
    WeekButton weekButton;

    protected WeekCard(Context context, WeekButton weekButton) {
        super(context);

        this.weekButton = weekButton;
        createDays();

        setPositiveButton("Готово", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO:SAVE DATA TO DB
            }
        });
    }

    private void createDays() {
        TableRow tableRow = new TableRow(this.getContext());
        tableRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        tableRow.setPadding(20,0,0,0);

        for (int i = 0; i < 7; i++) {
            DaySwitch daySwitch = new DaySwitch(this.getContext());
            daySwitch.setLayoutParams(new TableRow.LayoutParams(130, 130));
            //TODO: ADD NUMBER OF THE DAY TO THE BUTTON

            tableRow.addView(daySwitch);
        }

        setView(tableRow);
    }

}
