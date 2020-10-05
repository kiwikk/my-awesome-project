package com.kiwikk.myawesomeproject.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.kiwikk.myawesomeproject.R;
import com.kiwikk.myawesomeproject.person.Person;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Button changeNameButton;
    private Button changeBDateButton;
    private SwitchCompat notiffs;
    private Button getDonut;
    private View view;

    private int mYear, mMonth, mDay;
    private StringBuilder date;

    private static SQLiteDatabase db;
    private Person person;
    private LayoutInflater inflater;


    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        db = HomeFragment.getDbHelper().getWritableDatabase();
        person = HomeFragment.getPerson();
        setButtons();
        return view;
    }

    private void setButtons() {
        changeNameButton = view.findViewById(R.id.change_name);
        changeBDateButton = view.findViewById(R.id.change_bday);
        notiffs = view.findViewById(R.id.notifs);
        getDonut = view.findViewById(R.id.getDonut);

        setChangeName();
        setChangeBDate();
        setNotifs();
        setGetDonut();
    }

    private void setChangeName() {
        changeNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNewName();
            }
        });
    }

    private void getNewName() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this.getContext());
        alertBuilder.setTitle("Так ты не " + person.getName() + "???");
        alertBuilder.setMessage("А я всё ещё Мотя..");

        final EditText input = new EditText(this.getContext());
        alertBuilder.setView(input);
        final String[] name = new String[1];

        alertBuilder.setPositiveButton("Продолжим", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name[0] = String.valueOf(input.getText());
                person.setName(name[0]);

                HomeFragment.getDbHelper().updateDB(person, db, "NAME");
            }
        });

        alertBuilder.setNegativeButton("Да " + person.getName() + " я, " + person.getName() + "...",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),
                                "Ну нет так нет", Toast.LENGTH_LONG).show();
                    }
                });

        AlertDialog ad = alertBuilder.create();
        ad.show();
    }

    private void setChangeBDate() {

        changeBDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNewBDate();
            }
        });

    }

    private void getNewBDate() {
        final Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        date = new StringBuilder();

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), AlertDialog.THEME_HOLO_LIGHT,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if (Integer.toString(dayOfMonth).length() == 1)
                            date.append(0);
                        date.append(dayOfMonth).append(" ");
                        if (Integer.toString(month + 1).length() == 1) date.append(0);
                        date.append(month + 1).append(" ").append(year);

                        person.setBirthDate(date.toString());
                        HomeFragment.getDbHelper().updateDB(person, db, "DATE_OF_BIRTH");
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.setTitle("А теперь мне нужна твоя дата рождения");
        datePickerDialog.setCancelable(false);
        datePickerDialog.show();
    }

    private void setNotifs() {
        notiffs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    notiffs.setText("Включить уведомления");
                    Toast.makeText(getContext(),
                            "Уведомления отключены", Toast.LENGTH_LONG).show();
                } else {
                    notiffs.setText("Отключить уведомления");
                    Toast.makeText(getContext(),
                            "Уведомления включены", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setGetDonut() {
        getDonut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                inflater = getLayoutInflater();
                View dInf = inflater.inflate(R.layout.donut_layout, null);
                builder.setView(dInf);
                builder.setTitle("Держи :з");

                builder.setPositiveButton("Спасибо", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),
                                "Обращайся))", Toast.LENGTH_LONG).show();
                    }
                });

                builder.setNegativeButton("Нет ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),
                                "Ну нет так нет", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
            }
        });

    }
}