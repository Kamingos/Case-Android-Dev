package com.example.case_android_dev;

public class Contact {

    public Contact(String _text_first, String _text_second, int _image, boolean _canDel)
    {
        text_first = _text_first;
        text_second = _text_second;
        image = _image;
        canDel = _canDel;
    }

    boolean canDel;
    String text_first;
    String text_second;
    int image;
}
