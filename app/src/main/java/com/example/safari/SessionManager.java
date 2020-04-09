package com.example.safari;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.safari.Models.DLocation;
import com.example.safari.Models.Users;
import com.example.safari.Models.originDesLocation;

public class SessionManager {
        public static final String SHARED_PREF_NAME = "register_login";
        private static final String KEY_ID = "user_id";
        public static final String KEY_USERNAME = "user_name";
        public static final String KEY_PHONENO = "user_phone";
        public static final String KEY_EMAIL = "user_email";
        public static final String KEY_DOB = "user_dob";
        public static final String KEY_DRIVERID = "driverID";
        public static final String KEY_LATITUDE = "driver_latitude";
        public static final String KEY_LONGITUDE = "driver_longitude";
        public static final String KEY_OLATITUDE = "olatitude";
        public static final String KEY_OLONGITUDE = "olongitude";
        public static final String KEY_DLATITUDE = "dest_latitude";
        public static final String KEY_DLONGITUDE = "dest_longitude";
        public static final String KEY_DISTANCE = "total_distance";
        public static final String KEY_PRICE = "total_price";


    private static SessionManager mInstance;
        private static Context ctx;

        private SessionManager(Context context) {
            ctx = context;
        }

        public static synchronized SessionManager getInstance(Context context) {
            if (mInstance == null) {
                mInstance = new SessionManager(context);
            }
            return mInstance;
        }

        //this method will store the user data in shared preferences
        public void userLogin(Users user) {
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_USERNAME, user.getName());
            editor.putString(KEY_PHONENO, user.getPhoneno());
            editor.putString(KEY_EMAIL , user.getEmail());
            editor.putString(KEY_DOB , user.getDob());
            editor.putString(KEY_DRIVERID,user.getDriverId());
            editor.apply();
        }

        //this method will store the driver's latitude and longitude values.
        public void driverLocation(DLocation dlocation){
            SharedPreferences DriverLocation = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = DriverLocation.edit();
            editor.putString(KEY_LATITUDE, dlocation.getLatitude());
            editor.putString(KEY_LONGITUDE, dlocation.getLongitude());
            editor.apply();
        }


        public void userCoordinates(originDesLocation coordinates){
            SharedPreferences UserCoordinates = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = UserCoordinates.edit();
            editor.putString(KEY_OLATITUDE, coordinates.getoLatitude());
            editor.putString(KEY_OLONGITUDE, coordinates.getoLongitude());
            editor.putString(KEY_DLATITUDE, coordinates.getDLatitude());
            editor.putString(KEY_DLONGITUDE, coordinates.getDLongitude());
            editor.putString(KEY_DISTANCE, coordinates.getDistance());
            editor.putString(KEY_PRICE, coordinates.getPrice());
            editor.apply();
        }


        //this method will checker whether user is already logged in or not
        public boolean isLoggedIn() {
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(KEY_USERNAME, null) != null;
        }

        //this method will give the logged in user
        public Users getUser() {
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return new Users(
                    sharedPreferences.getInt(KEY_ID, -1),
                    sharedPreferences.getString(KEY_USERNAME, "user_name"),
                    sharedPreferences.getString(KEY_PHONENO, "user_phone"),
                    sharedPreferences.getString(KEY_EMAIL, "user_email"),
                    sharedPreferences.getString(KEY_DOB, "user_dob"),
                    sharedPreferences.getString(KEY_DRIVERID, "driverID"))
                    ;
        }

        //this method will logout the user
        public void logout() {
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            ctx.startActivity(new Intent(ctx, Login.class));
        }

}
