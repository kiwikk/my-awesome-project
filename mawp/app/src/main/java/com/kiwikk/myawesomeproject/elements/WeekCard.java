package com.kiwikk.myawesomeproject.elements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.kiwikk.myawesomeproject.R;

public class WeekCard extends BottomSheetDialogFragment {
    WeekButton weekButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_layout, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        createDays();
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
