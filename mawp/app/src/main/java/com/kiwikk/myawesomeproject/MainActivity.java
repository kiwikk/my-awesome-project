package com.kiwikk.myawesomeproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.kiwikk.myawesomeproject.fragments.HomeFragment;
import com.kiwikk.myawesomeproject.fragments.NewsFragment;
import com.kiwikk.myawesomeproject.fragments.NotificationsFragment;
import com.kiwikk.myawesomeproject.fragments.SettingsFragment;


public class MainActivity extends AppCompatActivity {
    private ChipNavigationBar chipNavigationBar;
    private Fragment fragment=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chipNavigationBar=findViewById(R.id.chipNavigation);
        chipNavigationBar.setItemSelected(R.id.home, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i){
                    case R.id.home:
                    fragment = new HomeFragment();
                    break;
                    case R.id.news:
                        fragment=new NewsFragment();
                        break;
                    case R.id.notifications:
                        fragment=new NotificationsFragment();
                        break;
                    case R.id.settings:
                        fragment=new SettingsFragment();
                        break;
                }

                if(fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                }
            }
        });
    }
}