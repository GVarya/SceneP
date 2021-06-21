package com.example.project;

import android.app.Dialog;
import android.content.Intent;
import android.widget.EditText;

public class Errors {
    static boolean flag;
    static String result = "";
    static Dialog newDialog;
    static EditText input;
    public static void showConnectionErrorActivity() {
        Intent i = new Intent(MyApplication.getAppContext(), ConnectionErrorActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getAppContext().startActivity(i);
    }


//    public static void showDialog(String title, Context context) {
//        Log.i("Dialog", "show_dialog begun");
//        flag = false;
//        newDialog = new Dialog(context);
//        newDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        newDialog.setContentView(R.layout.dialog_window_question);
//        Button button_save = newDialog.findViewById(R.id.save_button);
//        TextView dialog_tv = newDialog.findViewById(R.id.textView);
//        dialog_tv.setText(title);
//        input = newDialog.findViewById(R.id.input);
//        newDialog.show();
//        button_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (v.getId() == R.id.save_button) {
//                    result = input.getText().toString();
//                    flag = true;
//                    newDialog.dismiss();
//                }
//            }
//
//        });
//
//
//    }
//
//    public static String getResult() {
//        return result;
//
//    }
}