package com.example.case_android_dev;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterContacts extends BaseAdapter {
    Context MyContext;
    LayoutInflater lInflater;
    ArrayList<Contact> objects = new ArrayList<Contact>();

    public AdapterContacts(Context context, ArrayList<Contact> contacts) {
        MyContext = context;
        objects = contacts;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            view = lInflater.inflate(R.layout.contact_item, parent, false);
        }

        Contact p = getContact(position);

        ((TextView) view.findViewById(R.id.contact_txt_1)).setText(p.text_first);
        ((TextView) view.findViewById(R.id.contact_txt_2)).setText(p.text_second);
        ((ImageView) view.findViewById(R.id.contact_img)).setImageResource(p.image);

        return view;
    }

    Contact getContact(int position) {
        return ((Contact) getItem(position));
    }
}
