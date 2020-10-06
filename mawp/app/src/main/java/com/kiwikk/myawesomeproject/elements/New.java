package com.kiwikk.myawesomeproject.elements;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.kiwikk.myawesomeproject.R;

public class New extends LinearLayout {
    View view;
    TextView textView;

    public New(Context context, View view) {
        super(context);
        init(context);
        //setSaveEnabled(true);
        this.view = view;
    }

    public New(Context context) {
        super(context);
    }

    public New(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public New(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public New(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.notif_layout, this, true);
        setOrientation(LinearLayout.VERTICAL);
    }

    public void setText(String text) {
        textView = findViewById(R.id.notifsText);
        textView.setText(text);
    }

}
