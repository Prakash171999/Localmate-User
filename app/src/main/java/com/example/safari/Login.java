package com.example.safari;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

import com.example.safari.Models.Users;


public class Login extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {

    /**
     * Instances for animating the logo
     **/
    public ImageView mScanner;
    public Animation mAnimation;
    /**
     * instances for Login
     **/
    private EditText email, password, c_password;
    private Button btn_login;
    private ProgressBar loading_login;
    private Integer isDriver;
    private TextView internetConn;

    private  TextView forgotPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        TextView textView = findViewById(R.id.no_account);
        Button button = findViewById(R.id.sign_up);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);

            }
        });

        animateLogo();

        //This will check if the user is already logged in or not. If logged in directly redirect them to their respective homepage.
        if(SessionManager.getInstance(this).isLoggedIn())
        {
            startActivity(new Intent(this, home.class));
            finish();
        }

        internetConn = (TextView) findViewById(R.id.netInfo);
        /**Checking internet connection available or not**/
        if (isNetworkAvailable())
        {
            Toast.makeText (this, "Connected to the internet!", Toast.LENGTH_LONG).show();
        }
        else
        {
            internetConn.setText("No internet connection!");
            internetConn.setTextColor(Color.parseColor("#fff"));
        }

        /**for login**/
        loading_login = findViewById(R.id.progressBar2);
        email = findViewById(R.id.in_user);
        password = findViewById(R.id.in_password);
        btn_login = findViewById(R.id.login_btn);
        loading_login.setVisibility(View.GONE);
        forgotPwd = findViewById(R.id.passwordForgot);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                loginUser();
            }
        });


        forgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view3) {
                Intent intent = new Intent(getApplicationContext(), forgotPassword.class);
                startActivity(intent);
            }
        });

    }


    //Animating the image
    public void animateLogo() {
        mScanner = (ImageView) findViewById(R.id.img1);

        mScanner.setVisibility(View.VISIBLE);
        mAnimation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 6f);
        mAnimation.setDuration(150);
        mAnimation.setRepeatCount(-1);
        mAnimation.setRepeatMode(Animation.REVERSE);
        mAnimation.setInterpolator(new LinearInterpolator());
        mScanner.setAnimation(mAnimation);
    }

    //checking internet connection
    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activityNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activityNetworkInfo != null && activityNetworkInfo.isConnected();
    }


    //User login code
    public void loginUser() {
        String loginEmail = email.getText().toString();
        String loginPassword = password.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(loginEmail)) {
            email.setError("Please enter your email");
            email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(loginPassword)) {
            password.setError("Please enter your password");
            password.requestFocus();
            return;
        }
        JSONObject params = new JSONObject();
        try {
            params.put("email", loginEmail);
            params.put("password", loginPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.POST, URL.login_url, params, this, this);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonReq);

    }


    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println("ERes => " + error);
        Toast.makeText(Login.this, "Login Error!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        System.out.println("Res => " + response);
        try {
            JSONObject obj = response.getJSONObject("user");
            String userid = obj.getString("id");
            System.out.println("DriverId==>" + userid);


            //Creating a new object for the Users class
            Users user = new Users(
                    obj.getInt("id"),
                    obj.getString("fullname"),
                    obj.getString("email"),
                    obj.getString("phoneno"),
                    obj.getString("dob"),
                    obj.getString("driver_id")
            );
            //Storing the user object details in SessionManager
            SessionManager.getInstance(getApplicationContext()).userLogin(user);
            finish();


            if (!userid.equals("")) {
                Toast.makeText(Login.this, "User Login Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), home.class);
                startActivity(intent);

            } else {
                Toast.makeText(Login.this, "Login failed!", Toast.LENGTH_SHORT).show();
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

