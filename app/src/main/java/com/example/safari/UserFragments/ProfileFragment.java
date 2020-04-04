package com.example.safari.UserFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.safari.R;
import com.example.safari.SessionManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView fname = (TextView) v.findViewById(R.id.Uname);
        TextView uEmail = (TextView) v.findViewById(R.id.Uemail);
        TextView uPhone = (TextView) v.findViewById(R.id.Uphone);
        TextView uDob = (TextView) v.findViewById(R.id.Udob);

        final Button Logout = (Button) v.findViewById(R.id.driver_logout);
        Logout.setOnClickListener(this);

         //Getting the users data from SharedPreferences SessionManager class
         SharedPreferences sm = getActivity().getSharedPreferences(SessionManager.SHARED_PREF_NAME , Context.MODE_PRIVATE);
         String name = sm.getString( SessionManager.KEY_USERNAME, "user_name");
         fname.setText(name);
         String email = sm.getString(SessionManager.KEY_EMAIL, "user_email");
         uEmail.setText(email);
         String phone = sm.getString(SessionManager.KEY_PHONENO, "user_phone");
         uPhone.setText(phone);
         String dob = sm.getString(SessionManager.KEY_DOB, "user_dob");
         uDob.setText(dob);
         return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.driver_logout:
                SessionManager.getInstance(getContext()).logout();
        }
    }
}

