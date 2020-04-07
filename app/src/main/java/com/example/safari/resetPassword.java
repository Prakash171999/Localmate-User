package com.example.safari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class resetPassword extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {
    EditText email, token, reset_pwd, reset_cpwd;
    Button changePwdBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        changePwdBtn = findViewById(R.id.changePwd);

        changePwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResetPassword();
            }
        });
    }

    public void ResetPassword() {
        //Validating the email field
        email = findViewById(R.id.emailReset);
        String Email = email.getText().toString();

        token = findViewById(R.id.tokenkey);
        String TokenKey = token.getText().toString();

        reset_pwd = findViewById(R.id.reset_pwd);
        String pwdReset = reset_pwd.getText().toString();

        reset_cpwd = findViewById(R.id.reset_cpwd);
        String cpwdReset = reset_cpwd.getText().toString();

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

        if (TextUtils.isEmpty(TokenKey)) {
            token.setError("Please enter your Token Key");
            token.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(pwdReset)) {
            reset_pwd.setError("Please enter your new password");
            reset_pwd.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(cpwdReset)) {
            reset_cpwd.setError("Please enter your password");
            reset_cpwd.requestFocus();
            return;
        }
        if (pwdReset.length() < 8)
        {
            reset_pwd.setError("The password should not be less than 8 digits.");
            reset_pwd.requestFocus();
            return ;
        }

        if(!pwdReset.equals(cpwdReset)){
            reset_cpwd.setError("The password doesn't match!");
            reset_cpwd.requestFocus();
        }
        JSONObject params = new JSONObject();
        try {
            params.put("email", Email);
            params.put("token", TokenKey);
            params.put("password", pwdReset);
            params.put("password_confirmation", cpwdReset);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URL.resetpwd_url, params, this, this);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println("ErrorRes => " + error);
        Toast.makeText(resetPassword.this, "Error=>" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        System.out.println("Res => " + response);
        if (response != null) {
            try {
                String resetmsg = response.getString("msg");
//                String resetmsgError = response.getString("error");
                if(!resetmsg.equals("")){
                    Toast.makeText(resetPassword.this, "Password reset done!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(resetPassword.this, "Password reset error!", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(resetPassword.this, "No response!", Toast.LENGTH_SHORT).show();
        }
    }
}
