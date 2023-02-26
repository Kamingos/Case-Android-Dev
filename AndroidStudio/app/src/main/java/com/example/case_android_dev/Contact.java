package com.example.case_android_dev;

public class Contact {

    public Contact(String _text_first, String _text_second, int _image)
    {
        text_first = _text_first;
        text_second = _text_second;
        image = _image;
        boolean isMicOn = false;
    }

    String text_first;
    String text_second;
    int image;
}
