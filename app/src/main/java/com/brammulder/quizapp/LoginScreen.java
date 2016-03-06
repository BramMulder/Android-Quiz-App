package com.brammulder.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class LoginScreen extends Activity {

    private EditText insertName;
    ImageView userImageView;

    private DBHelper dbHelper;
    private Users users;
    private static String userName;

    /**
     * Lets the User choose a name, which is then added to the database
     * Lets the User choose an image from the photo library on the phone
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        insertName = (EditText) findViewById(R.id.insertName);
        dbHelper = new DBHelper(this);

        userImageView = (ImageView) findViewById(R.id.userImageLogin);
        userImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), 1337);

            }
        });
    }

    /**
     * Catches result from the photo library intent
     * @param reqCode
     * @param resCode
     * @param data
     */
    public void onActivityResult(int reqCode, int resCode, Intent data) {
        if (resCode == RESULT_OK) {
            if (reqCode == 1337) {
                userImageView.setImageURI(data.getData());
            }
        }
    }

    /**
     * When the uses clicks the OK button, the user's name is added to the database
     * If the username is already in the database a toast pops-up welcoming them back
     * @param v
     */
    public void buttonClicked(View v) {
        //Add name to the database
        userName = String.valueOf(insertName.getText());
        if (userName.equals("")) {
            String popup = "Please Insert a Name";
            Toast.makeText(LoginScreen.this, popup, Toast.LENGTH_LONG).show();
        } else {
            try {
                users = new Users(insertName.getText().toString());
                dbHelper.addName(users);
            } catch (SQLiteConstraintException e) {
                String popup = "Welcome Back: " + insertName.getText().toString();
                Toast.makeText(LoginScreen.this, popup, Toast.LENGTH_LONG).show();
            }

            //Open Main Menu
            Intent i;
            i = new Intent(getApplicationContext(),
                    MainMenu.class);
            startActivity(i);

        }
    }

    public String getUserName() {
        return userName;
    }

}



