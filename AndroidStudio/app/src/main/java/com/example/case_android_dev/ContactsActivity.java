package com.example.case_android_dev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    AdapterContacts adapterContacts;

    ImageButton BtnAcAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);

        ListView lw = (ListView) findViewById(R.id.list_view);

        fillContacts(1);
        adapterContacts = new AdapterContacts(this, MainActivity.contacts_list);

        lw.setAdapter(adapterContacts);



        BtnAcAdd = (ImageButton) findViewById(R.id.plus_btn);
        BtnAcAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addContact("новый", "аккаунт", R.drawable.ic_group);
                adapterContacts.notifyDataSetChanged();
            }
        });
    }

    void addContact(String text_first, String text_second, int image) {
        MainActivity.contacts_list.add(new Contact(text_first, text_second, image));
    }

    void fillContacts(int count) {
        for (int i = 0; i < count; i++) {
            addContact("Строка 1", "Строка 2", R.drawable.ic_group);
        }
    }
}