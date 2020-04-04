package com.example.safari;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.safari.UserFragments.HomeFragment;
import com.example.safari.UserFragments.NotificationFragment;
import com.example.safari.UserFragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView=findViewById(R.id.bottomNav);

        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();


//            SharedPreferences sp = getSharedPreferences("register_login", Context.MODE_PRIVATE);
//            String usernames = sp.getString("KEY_USERNAME", "user_name");
//            System.out.println("xxxxx->" + usernames);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod= new
            BottomNavigationView.OnNavigationItemSelectedListener(){

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem usermenuItem) {
                    Fragment fragment=null;
                    switch  (usermenuItem.getItemId())
                    {
                        case R.id.home:
                            fragment=new HomeFragment();
                            break;

                        case R.id.profile:
                            fragment=new ProfileFragment();
                            break;

                        case R.id.notification:
                            fragment=new NotificationFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                    return true;
                }
            };

}

