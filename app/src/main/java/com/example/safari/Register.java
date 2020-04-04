package com.example.safari;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.content.Intent;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;



public class Register extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {

    /**
     * Instances for date picker
     **/
    EditText displayDate;
    DatePickerDialog.OnDateSetListener dateSetListener;

    /**
     * Instances for hide and show the password
     **/
    private EditText pwd;
    private EditText c_pwd;
    private TextView toggle1;
    private TextView toggle2;

    /**
     * instances for registration
     **/
    private EditText fullname, phoneno, email, dob, password, c_password;
    private Button btn_register;
//    private static String URL_REGIST = "http://192.168.254.3/localmate/public/api/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /**Creating intent through text**/
        TextView t1 = (TextView) findViewById(R.id.have_account);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });


        /**Displaying the date picker when the text field is clicked**/
        displayDate = findViewById(R.id.dob);
        Calendar cal = Calendar.getInstance();
        final int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        final int day = cal.get(Calendar.DAY_OF_MONTH);

        displayDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        Register.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = year + "-" + month + "-" + day;
                displayDate.setText(date);
            }
        };

//        /**For hide and show password**/
//        pwd = (EditText) findViewById(R.id.password);
//        c_pwd = (EditText) findViewById(R.id.confirm_password);
//        toggle1 = (TextView) findViewById(R.id.show_1);
//        toggle2 = (TextView) findViewById(R.id.show_2);
//
//        toggle1.setVisibility(View.GONE);
//        toggle2.setVisibility(View.GONE);
//        /**Changing the password input type to password**/
//        pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        c_pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//
//        pwd.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(pwd.getText().length() > 0){
//                    toggle1.setVisibility(View.VISIBLE);
//                }
//                else {
//                    pwd.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        c_pwd.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(c_pwd.getText().length() > 0){
//                    toggle2.setVisibility(View.VISIBLE);
//                }
//                else {
//                    c_pwd.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        /**Onclick listner for changing show and hide and change the edit text field to its default input type. FOR TOGGLE1**/
//        toggle1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(toggle1.getText() == "SHOW"){
//                    toggle1.setText("HIDE");
//                    pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                    pwd.setSelection(pwd.length());
//                }
//                else{
//                    toggle1.setText("SHOW");
//                    pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                    pwd.setSelection(pwd.length());
//                }
//            }
//        });
//
//        /**Onclick listner for changing show and hide and change the edit text field to its default input type. FOR TOGGLE2**/
//        toggle2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(toggle2.getText() == "SHOW"){
//                    toggle2.setText("HIDE");
//                    c_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                    c_pwd.setSelection(c_pwd.length());
//                }
//                else{
//                    toggle2.setText("SHOW");
//                    c_pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                    c_pwd.setSelection(c_pwd.length());
//                }
//            }
//        });


        /**for registration**/
        fullname = findViewById(R.id.full_name);
        phoneno = findViewById(R.id.phoneno);
        dob = findViewById(R.id.dob);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        c_password = findViewById(R.id.confirm_password);
        btn_register = findViewById(R.id.register_btn);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }

    /**
     * Register function
     **/
    private void registerUser() {
        final String full_name = this.fullname.getText().toString().trim();
        final String phone_no = this.phoneno.getText().toString().trim();
        final String DOB = this.dob.getText().toString().trim();
        final String Email = this.email.getText().toString().trim();
        final String pswd = this.password.getText().toString().trim();
        final String c_pswd = this.c_password.getText().toString().trim();

        //validating inputs
        if (TextUtils.isEmpty(full_name)) {
            fullname.setError("Please enter your email");
            fullname.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(phone_no)) {
            phoneno.setError("Please enter your password");
            phoneno.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(DOB)) {
            dob .setError("Please enter your password");
            dob.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Email)) {
            email.setError("Please enter your password");
            email.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(pswd)) {
            password.setError("Please enter your password");
            password.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(c_pswd)) {
            c_password.setError("Please enter your password");
            c_password.requestFocus();
            return;
        }
        if (phone_no.length() != 10)
        {
            phoneno.setError("There should be 10 digit phone no.");
            phoneno.requestFocus();
            return ;
        }
        if (pswd.length() < 8)
        {
            password.setError("The password should not be less than 8 digits.");
            password.requestFocus();
            return ;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email.setError("Enter a valid email");
            email.requestFocus();
            return;
        }

        if(!pswd.equals(c_pswd)){
            c_password.setError("The password doesn't match!");
            c_password.requestFocus();
            return;
        }


        JSONObject params = new JSONObject();
        try {
            params.put("fullname", full_name);
            params.put("phoneno", phone_no);
            params.put("dob", DOB);
            params.put("email", Email);
            params.put("password", pswd);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsReq = new JsonObjectRequest(Request.Method.POST,URL.register_url, params,this, this);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsReq);
    }

    //Empty the textbox
    public void emptyRegFields()
    {
        fullname.setText("");
        phoneno.setText("");
        dob.setText("");
        email.setText("");
        password.setText("");
        c_password.setText("");
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println("ERes => "+error);
        Toast.makeText(Register.this, "Register Error!" + error, Toast.LENGTH_SHORT).show();
        btn_register.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResponse(JSONObject response) {
        System.out.println("Res => "+response);
        String success = null;
        try {
            success = response.getString("user");
            if(!success.equals("")){
                Toast.makeText(Register.this, "Register Success!", Toast.LENGTH_SHORT).show();
                emptyRegFields();
                btn_register.setVisibility(View.VISIBLE);
            }
            else {
                Toast.makeText(Register.this, "Failed to register!", Toast.LENGTH_SHORT).show();
                btn_register.setVisibility(View.VISIBLE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
