package com.kiwikk.myawesomeproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.kiwikk.myawesomeproject.fragments.HomeFragment;
import com.kiwikk.myawesomeproject.fragments.NewsFragment;
import com.kiwikk.myawesomeproject.fragments.NotificationsFragment;
import com.kiwikk.myawesomeproject.fragments.SettingsFragment;


public class MainActivity extends AppCompatActivity {
    private ChipNavigationBar chipNavigationBar;
    private SharedPreferences sharedPreferences;
    private EditText input;

    int mYear, mMonth, mDay;
    StringBuilder date;

    private Fragment fragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        chipNavigationBar = findViewById(R.id.chipNavigation);

        sharedPreferences = getSharedPreferences("com.kiwikk.myawesomeproject", MODE_PRIVATE);
        chipNavigationBar.setItemSelected(R.id.home, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.news:
                        fragment = new NewsFragment();
                        break;
                    case R.id.notifications:
                        fragment = new NotificationsFragment();
                        break;
                    case R.id.settings:
                        fragment = new SettingsFragment();
                        break;
                }

                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // if (sharedPreferences.getBoolean("firstrun", true)) {
        //introduce();
        //   sharedPreferences.edit().putBoolean("firstrun", false).apply();
        // }
    }

//    private void introduce() {
//        Person person = new Person();
//        getPersonName(person);
//    }
//
//    private void getPersonName(final Person person) {
//        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
//        alertBuilder.setTitle("Давай знакомиться");
//        alertBuilder.setMessage("Я - твой мотивационный помощник Мотя, а ты?");
//
//        input = new EditText(this);
//        alertBuilder.setView(input);
//        final String[] name = new String[1];
//
//        alertBuilder.setPositiveButton("Продолжим", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                name[0] = String.valueOf(input.getText());
//                person.setName(name[0]);
//
//                getBirthDate(person);
//            }
//        });
//
//        alertBuilder.setNegativeButton("Отстань", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                name[0] = "Конопешка";
//                Toast.makeText(getApplicationContext(),
//                        "Ну нет так нет, теперь ты - " + name[0] + ".", Toast.LENGTH_LONG).show();
//                person.setName(name[0]);
//
//                getBirthDate(person);
//            }
//        });
//
//        AlertDialog ad = alertBuilder.create();
//        ad.show();
//    }
//
//    private void getBirthDate(final Person person) {
//        final Calendar calendar = Calendar.getInstance();
//        mYear = calendar.get(Calendar.YEAR);
//        mMonth = calendar.get(Calendar.MONTH);
//        mDay = calendar.get(Calendar.DAY_OF_MONTH);
//        date = new StringBuilder();
//
//        DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
//                new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        if (Integer.toString(dayOfMonth).length() == 1)
//                            date.append(0);
//                        date.append(dayOfMonth).append(" ");
//                        if (Integer.toString(month).length() == 1) date.append(0);
//                        date.append(month).append(" ").append(year);
//
//                        person.setBirthDate(date.toString());
//
//                        DataBaseHelper dataBaseHelper = new DataBaseHelper(view.getContext());
//                        dataBaseHelper.insertPerson(person);
//                    }
//                }, mYear, mMonth, mDay);
////        DatePickerDialog datePickerDialog = new DatePickerDialog(this,  new DatePickerDialog.OnDateSetListener() {
////            @Override
////            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
////                date.append(dayOfMonth).append(" ").append(month).append(" ").append(year);
////            }
////        }, mYear, mMonth, mDay);
//        datePickerDialog.setTitle("А теперь мне нужна твоя дата рождения");
//        datePickerDialog.show();
//
//        //return date.toString();
//    }

}