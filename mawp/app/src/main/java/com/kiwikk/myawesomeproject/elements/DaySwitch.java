package com.kiwikk.myawesomeproject.elements;

import android.content.Context;

import androidx.appcompat.widget.SwitchCompat;

import com.kiwikk.myawesomeproject.R;

public class DaySwitch extends SwitchCompat {
    boolean state;

    public DaySwitch(Context context) {
        super(context);
       // setRotation(90);
        setListeners();
    }

    private void setListeners() {

    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);

        int color;
        int id;

        if (checked) {
            color = R.color.yellow_happy;
            id = R.drawable.ic_ok;
        } else {
            color = R.color.dark_gray;
            id = R.drawable.ic_not_ok;
        }


        setThumbResource(id);

        // getThumbDrawable().setColorFilter(new ColorFilter());

    }
}
