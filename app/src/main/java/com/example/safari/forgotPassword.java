package com.example.safari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class forgotPassword extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {

    EditText email;
    Button sendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        sendEmail = findViewById(R.id.email_send);
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotPwd();
            }
        });

    }

    public void forgotPwd() {
        //Validating the email field
        email = findViewById(R.id.emailReset);
        String Email = email.getText().toString();

        if (TextUtils.isEmpty(Email)) {
            email.setError("Please enter your email");
            email.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email.setError("Enter a valid email");
            email.requestFocus();
            return;
        }

        JSONObject params = new JSONObject();
        try {
            params.put("email", Email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URL.forgotpwd_url, params, this, this);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonRequest);
    }

        @Override
        public void onErrorResponse (VolleyError error){
            System.out.println("ErrorRes => " + error);
            Toast.makeText(forgotPassword.this, "Error=>" + error, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResponse (JSONObject response){
            System.out.println("Res => " + response);
            try {
//                String success = response.getString("message");
                String fail = response.getString("error");

                if (!fail.equals("")) {
                    Toast.makeText(forgotPassword.this, "Password reset message sent!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), resetPassword.class);
                    startActivity(intent);
                }
//                else if (!fail.equals("")){
//                    Toast.makeText(forgotPassword.this, "Password reset message sent!", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(getApplicationContext(), resetPassword.class);
//                    startActivity(intent);
//                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
}


