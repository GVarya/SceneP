package com.example.project;

import android.content.Intent;

public class Errors {

    public static void showConnectionErrorActivity() {
        Intent i = new Intent(MyApplication.getAppContext(), ConnectionErrorActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getAppContext().startActivity(i);
    }


}