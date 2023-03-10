package com.example.case_android_dev;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    static ArrayList<Contact> contacts_list = new ArrayList<Contact>();

    ImageButton BtnGroup;

    ImageButton BtnVideoCam;

    ImageButton BtnMic;

    ImageButton BtnExit;

    ImageButton BtnHand;

    ImageButton BtnMsg;

    ImageButton BtnForm;

    TextView groupCnt;

    ImageButton BtnExp;

    int inputText1;

    int inputText2;

    boolean isVideoCamOn;

    static boolean isMicOn;

    AdapterMain adapterMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // грид и адаптер
        GridView gridView = (GridView) findViewById(R.id.grid_main);
        adapterMain = new AdapterMain(this, contacts_list);

        // установка адаптера
        gridView.setAdapter(adapterMain);

        // одноразовое добавление одного объекта в список
        if (contacts_list.size() < 1) {
            contacts_list.add(new Contact("Вы", "галочка на главном экране тоже работает", R.drawable.my_photo, false));
        }

        buttons();

        // сообщение
        Toast toast = Toast.makeText(MainActivity.this, "Чтобы добавить контактов\nнажмите на значок группы", Toast.LENGTH_LONG);
        toast.show();
    }

    // для счётчика списка
    @Override
    protected void onResume() {
        super.onResume();

        // обновление адаптера
        adapterMain.notifyDataSetChanged();

        // кол-во элементов в списке: contacts_list
        groupCnt = (TextView) findViewById(R.id.group_cnt);
        groupCnt.setText(arrSize());
    }

    // кнопки
    private void buttons() {

        // кнопка галочки up
        BtnExp = (ImageButton) findViewById(R.id.expand_btn);
        BtnExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                debugDialog();
            }
        });

        // кнопка сообщения up
        BtnMsg = (ImageButton) findViewById(R.id.chat_btn);
        BtnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smsDialog();
            }
        });

        // кнопка группы up
        BtnGroup = (ImageButton) findViewById(R.id.group_btn);
        BtnGroup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeSceneGroup();
            }
        });

        // кнопка формы up
        BtnForm = (ImageButton) findViewById(R.id.form_btn);
        BtnForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contacts_list.size() >= 2) {
                    arrElementsSwap();
                } else {
                    Toast toast = Toast.makeText(MainActivity.this, "Недостаточно элементов \n для перестановки", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        // кнопка руки ( привет / реакция ) down
        BtnHand = (ImageButton) findViewById(R.id.hand_btn);
        BtnHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handDialog();
            }
        });

        // кнопка выхода down
        BtnExit = (ImageButton) findViewById(R.id.exit_btn);
        BtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAndRemoveTask();
            }
        });

        // кнопка микрофона down
        BtnMic = (ImageButton) findViewById(R.id.micro_btn);
        BtnMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isMicOn) {
                    BtnMic.setImageResource(R.drawable.ic_microphone);
                    isMicOn = true;
                } else if (isMicOn) {
                    BtnMic.setImageResource(R.drawable.ic_microphone_off);
                    isMicOn = false;
                }
            }
        });

        // кнопка видео down
        BtnVideoCam = (ImageButton) findViewById(R.id.video_btn);
        BtnVideoCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isVideoCamOn) {
                    BtnVideoCam.setImageResource(R.drawable.ic_videocam);
                    isVideoCamOn = true;
                } else if (isVideoCamOn) {
                    BtnVideoCam.setImageResource(R.drawable.ic_videocam_off);
                    isVideoCamOn = false;
                }
            }
        });
    }

    // алерт диалог для перемешивания элементов списка
    private void arrElementsSwap() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ConstraintLayout cl = (ConstraintLayout) getLayoutInflater().inflate(R.layout.alert_change_array, null);
        builder.setView(cl);

        // кнопки
        builder.setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AlertDialog alertDialog = (AlertDialog) dialogInterface;

                // инициализация
                EditText editText1 = (EditText) alertDialog.findViewById(R.id.edit_text_sel_num_1);
                EditText editText2 = (EditText) alertDialog.findViewById(R.id.edit_text_sel_num_2);

                // текст с EditText
                // проверяет не превышают ли вводимые числа размер contacts_list
                if (Integer.parseInt(editText1.getText().toString()) <= contacts_list.size()
                        && Integer.parseInt(editText2.getText().toString()) <= contacts_list.size()
                        && (editText1.getText() != null)
                        && (editText2.getText() != null)) {
                    inputText1 = Integer.parseInt(editText1.getText().toString());
                    inputText2 = Integer.parseInt(editText2.getText().toString());

                    // меняем элементы местами
                    Collections.swap(contacts_list, inputText1 - 1, inputText2 - 1);

                    // обновление адаптера
                    adapterMain.notifyDataSetChanged();
                } else {
                    Toast toast = Toast.makeText(MainActivity.this, "Ошибка", Toast.LENGTH_LONG);
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

    // размер списка
    private String arrSize() {
        return Integer.toString(contacts_list.size());
    }

    // алерт диалог для перехода в SMS приложение
    private void smsDialog() {
        // алерт диалог
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Переход к стороннему приложению");
        builder.setMessage("Вы уверены, что хотите совершить это действие?");

        // кнопки
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms:"));
                startActivity(intent);
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

    // алерт диалог для приветствия
    private void handDialog() {
        // алерт диалог
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Привет");

        // кнопка
        builder.setPositiveButton("Закрыть", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        // вызов
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // смена сцены
    private void changeSceneGroup() {
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }

    private void debugDialog()
    {
        // алерт диалог
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("В этом окне всё, что есть в приложении ( на всякий случай )");
        builder.setMessage("1. я сделал поддержку дневной и ночной темы, т.к сам пользуюсь ночной\n" +
                "2. Можно добовлять, удалять и перемешивать контакты\n" +
                "3. На главном экране можно прокручивать пользователей, когда их больше 2\n" +
                "4. Все свои действия сохранял на GitHub\n\n" +
                "Хочу от себя сказать, что это был классный опыт. за 6 дней я с нуля всё это изучил и воплатил в жизнь, это классно");

        // кнопка
        builder.setNegativeButton("Закрыть", new DialogInterface.OnClickListener() {
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