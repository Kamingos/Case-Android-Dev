package com.example.case_android_dev;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

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

        lw.isClickable();
        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (MainActivity.contacts_list.get(position).canDel == true) {
                    delDialog(position);
                } else {
                    Toast toast = Toast.makeText(ContactsActivity.this, "Вы не можете\nудалить себя", Toast.LENGTH_LONG);
                    toast.show();
                }
            }

        });

        buttons();
    }

    private void buttons()
    {
        // кнопка добавления нового элемента в ListView
        BtnAcAdd = (ImageButton) findViewById(R.id.plus_btn);
        BtnAcAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                arrElementsAddDialog();
            }
        });
    }

    // алерт диалог по добавлению элементов
    private void arrElementsAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ConstraintLayout cl = (ConstraintLayout) getLayoutInflater().inflate(R.layout.alert_add_array, null);
        builder.setView(cl);

        // кнопки
        builder.setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AlertDialog alertDialog = (AlertDialog) dialogInterface;

                // инициализация
                TextView textView = (TextView) alertDialog.findViewById(R.id.sel_num);
                EditText editText1 = (EditText) alertDialog.findViewById(R.id.edit_text_sel_num_1);
                EditText editText2 = (EditText) alertDialog.findViewById(R.id.edit_text_sel_num_2);

                // текст с EditText
                if (editText1.getText().toString() != null && editText2.getText().toString() != null)
                {
                    addContact(editText1.getText().toString(), editText2.getText().toString(), R.drawable.ic_android, true);
                    adapterContacts.notifyDataSetChanged();
                } else {
                    Toast toast = Toast.makeText(ContactsActivity.this, "Ошибка", Toast.LENGTH_LONG);
                    toast.show();
                }


            }
        });
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        // вызов
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // добавление в список нового элемента
    private void addContact(String text_first, String text_second, int image, boolean canDel) {
        MainActivity.contacts_list.add(new Contact(text_first, text_second, image, canDel));
        //groupCnt.setText(MainActivity.contacts_list.size());
    }

    private void delDialog(int position)
    {
        // алерт диалог
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Удаление контакта");
        builder.setMessage("Вы уверены, что хотите совершить это действие?");

        // кнопки
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // удаление
                MainActivity.contacts_list.remove(position);
                adapterContacts.notifyDataSetChanged();
                }
        });

        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }


        });

        // вызов
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}