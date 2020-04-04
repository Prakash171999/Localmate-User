package com.example.safari.UserFragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.safari.PickUpPoint;
import com.example.safari.R;
import com.example.safari.Register;
import com.example.safari.SessionManager;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context=getActivity();
        return inflater.inflate(R.layout.fragment_home, container, false);

    }
    public void onStart() {

        super.onStart();
        Button book = (Button)context.findViewById(R.id.book_btn);
        book.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PickUpPoint.class);
                        startActivity(intent);
            }
        });
    }
}
