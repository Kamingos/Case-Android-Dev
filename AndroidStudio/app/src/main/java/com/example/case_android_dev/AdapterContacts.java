package com.example.case_android_dev;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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
        ((CircleImageView) view.findViewById(R.id.contact_img)).setImageResource(p.image);

        return view;
    }

    Contact getContact(int position) {
        return ((Contact) getItem(position));
    }
}
