package com.kiwikk.myawesomeproject.elements;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentManager;

import com.kiwikk.myawesomeproject.R;

@SuppressLint("AppCompatCustomView")
public class WeekButton extends Button {
    private int ID;
    private Color color;
    private Boolean isLived;
    private WeekCard weekCard;
    private FragmentManager fragmentManager;

    public WeekButton(final Context context, int id, final FragmentManager fragmentManager) {
        super(context);
        ID = id;
        weekCard = new WeekCard();
        this.fragmentManager=fragmentManager;

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weekCard.show(fragmentManager, weekCard.getTag());
            }
        });
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

    public void setLived() {
        isLived = true;
        setBackgroundResource(R.drawable.ic_cross);
        setEnabled(false);
    }
}
