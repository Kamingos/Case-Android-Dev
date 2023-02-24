package com.example.case_android_dev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    static ArrayList<Contact> contacts_list = new ArrayList<Contact>();

    ImageButton BtnForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnForm = (ImageButton) findViewById(R.id.form_btn);

        BtnForm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeScene();
            }
        });
    }

    private void changeScene()
    {
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }
}