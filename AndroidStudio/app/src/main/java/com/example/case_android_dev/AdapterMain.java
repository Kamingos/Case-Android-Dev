package com.example.case_android_dev;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterMain extends BaseAdapter {
    Context myContext;
    LayoutInflater lInflater;
    ArrayList<Contact> objects = new ArrayList<Contact>();

    public AdapterMain(Context context, ArrayList<Contact> contacts) {
        myContext = context;
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
            view = lInflater.inflate(R.layout.person_item, parent, false);
        }

        Contact p = getContact(position);

        ((CircleImageView) view.findViewById(R.id.person_avatar)).setImageResource(p.image);
        ((TextView) view.findViewById(R.id.person_text)).setText(p.text_first);

        return view;
    }

    Contact getContact(int position) {
        return ((Contact) getItem(position));
    }
}
