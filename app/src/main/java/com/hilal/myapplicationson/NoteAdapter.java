package com.hilal.myapplicationson;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<Note> noteArrayList;

    public NoteAdapter(Activity activity, ArrayList<Note> noteArrayList) {

        this.mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.noteArrayList = noteArrayList;
    }

    public int getCount() {
        return noteArrayList.size();
    }

    public Object getItem(int position) {
        return noteArrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = mInflater.inflate(R.layout.item, null);
        TextView noteTitle = (TextView) convertView.findViewById(R.id.title);
        TextView noteDescription = (TextView) convertView.findViewById(R.id.des);

        Note note = noteArrayList.get(position);
        noteTitle.setText(note.getTitle());
        noteDescription.setText(note.getDescription());
        return convertView;
    }
}
