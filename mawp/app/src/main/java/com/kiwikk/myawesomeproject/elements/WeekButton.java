package com.kiwikk.myawesomeproject.elements;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;

@SuppressLint("AppCompatCustomView")
public class WeekButton extends Button {
    private int ID;
    private Color color;
    private Boolean isLived;

    public WeekButton(Context context, int id) {
        super(context);
        ID = id;
    }

    public WeekButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WeekButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getID() {
        return ID;
    }

}
