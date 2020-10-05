package com.kiwikk.myawesomeproject.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.kiwikk.myawesomeproject.R;
import com.kiwikk.myawesomeproject.database.DataBaseHelper;
import com.kiwikk.myawesomeproject.elements.WeekButton;
import com.kiwikk.myawesomeproject.person.Person;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private static final int WEEK_ROWS = 80;
    private static final int WEEK_COLUMNS = 52;

    private EditText input;
    private static View view;
    private TableLayout tableLayout;

    private int mYear, mMonth, mDay;
    private StringBuilder date;

    private SharedPreferences sharedPreferences;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private static Person person;
    private static DataBaseHelper dbHelper;
    private SQLiteDatabase db;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        dbHelper = new DataBaseHelper(getContext());
        sharedPreferences = getActivity().getSharedPreferences("com.kiwikk.myawesomeproject", Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        if (sharedPreferences.getBoolean("firstrun", true)) {
            introduce();
            sharedPreferences.edit().putBoolean("firstrun", false).apply();
        } else {
            db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query("PERSON",
                    new String[]{"_id", "NAME", "DATE_OF_BIRTH"},
                    "_id = 1", null,
                    //new String[]{Integer.toString(1)},
                    null, null, null);

            cursor.moveToFirst();
            person = new Person(cursor.getString(1), cursor.getString(2));
            cursor.close();
        }
        createTable();
        colorWeeks();
        return view;
    }

    private void createTable() {
        tableLayout = view.findViewById(R.id.tableLayout);
        for (int i = 0; i <= WEEK_ROWS; i++) {
            TableRow tableRow = new TableRow(this.getContext());

            for (int j = 0; j <= WEEK_COLUMNS; j++) {
                if (i == 0) {
                    TextView textView = new TextView(this.getContext());
                    if (j != 0 && j % 10 == 0 || j == 1) textView.setText(Integer.toString(j));
                    else textView.setText("");
                    textView.setPadding(20, 0, 0, 0);

                    tableRow.addView(textView);
                    continue;
                }

                int id = (i - 1) * 52 + j;
                WeekButton weekButton = new WeekButton(this.getContext(), id, getFragmentManager());

                if (id < person.getWeeks())
                    weekButton.setLived();

                TableRow.LayoutParams p = new TableRow.LayoutParams(70, 70);
                p.setMargins(-5, 0, -5, 0);
                weekButton.setLayoutParams(p);

                if (j == 0) {
                    TextView textView = new TextView(this.getContext());
                    if (i % 10 == 0 || i == 1) textView.setText(Integer.toString(i));
                    else textView.setText("");

                    TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
                    params.setMargins(0, -15, 10, -15);
                    textView.setLayoutParams(params);

                    tableRow.addView(textView);
                    continue;
                }
                TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, -10, 25, -10);
                tableRow.setLayoutParams(params);

                tableRow.addView(weekButton, j);
            }
            tableLayout.addView(tableRow, i);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void introduce() {
        person = new Person();
        getPersonName(person);
    }

    private void getPersonName(final Person person) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this.getContext());
        alertBuilder.setTitle("Давай знакомиться");
        alertBuilder.setMessage("Я - твой мотивационный помощник Мотя, а ты?");

        input = new EditText(this.getContext());
        alertBuilder.setView(input);
        final String[] name = new String[1];

        alertBuilder.setPositiveButton("Продолжим", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name[0] = String.valueOf(input.getText());
                person.setName(name[0]);

                getBirthDate(person);
            }
        });

        alertBuilder.setNegativeButton("Отстань", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name[0] = "Вася";
                Toast.makeText(getContext(),
                        "Ну нет так нет, теперь ты - " + name[0] + ".", Toast.LENGTH_LONG).show();
                person.setName(name[0]);

                getBirthDate(person);
            }
        });

        AlertDialog ad = alertBuilder.create();
        ad.show();
    }

    private void getBirthDate(final Person person) {
        final Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        date = new StringBuilder();
        db = dbHelper.getWritableDatabase();

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

                        colorWeeks();

                        dbHelper.insertPerson(person, db);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.setTitle("А теперь мне нужна твоя дата рождения");
        datePickerDialog.setCancelable(false);
        datePickerDialog.show();
    }

    private void colorWeeks() {
        TableLayout tableLayout = view.findViewById(R.id.tableLayout);
        for (int i = 1; i <= WEEK_ROWS; i++) {
            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);

            for (int j = 1; j <= WEEK_COLUMNS; j++) {
                WeekButton weekButton = (WeekButton) tableRow.getChildAt(j);
                if (weekButton.getID() < person.getWeeks())
                    weekButton.setLived();
            }
        }
    }

    public static Person getPerson() {
        return person;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
    }

    public static DataBaseHelper getDbHelper() {
        return dbHelper;
    }
}