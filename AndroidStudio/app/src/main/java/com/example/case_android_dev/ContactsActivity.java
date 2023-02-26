package com.example.case_android_dev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    AdapterContacts adapterContacts;

    ImageButton BtnAcAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);

        // создание ListView и адаптер
        ListView lw = (ListView) findViewById(R.id.list_view);
        adapterContacts = new AdapterContacts(this, MainActivity.contacts_list);

        // добавление ListView адаптера
        lw.setAdapter(adapterContacts);

        buttons();
    }

    private void buttons()
    {
        // кнопка добавления нового элемента в ListView
        BtnAcAdd = (ImageButton) findViewById(R.id.plus_btn);
        BtnAcAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addContact("Создананный акаунт", "номер: " + (MainActivity.contacts_list.size() + 1), R.drawable.ic_group);
                adapterContacts.notifyDataSetChanged();
            }
        });
    }

    // добавление в список нового элемента
    void addContact(String text_first, String text_second, int image) {
        MainActivity.contacts_list.add(new Contact(text_first, text_second, image));
        //groupCnt.setText(MainActivity.contacts_list.size());
    }


}