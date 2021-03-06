package com.example.rmd2k.guitarstudio_android.MyZone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.rmd2k.guitarstudio_android.DataModel.NotesModel;
import com.example.rmd2k.guitarstudio_android.R;

/**
 * Created by CHT1HTSH3236 on 2018/4/20.
 */

public class GuitarNoteListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private NotesModel notesModel;

    public GuitarNoteListAdapter(Context context) {
        this.mContext = context;
        this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        notesModel = NotesModel.getInstance(context);
    }

    @Override
    public int getCount() {
        return notesModel.getGuitarNotesFilesNum();
    }

    @Override
    public Object getItem(int position) {
        return notesModel.getGuitarNotesFile(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.note_title_list_view_cell, null);
        }

        MySwipeLayout mySwipeLayout = (MySwipeLayout)convertView;

        TextView tvNoteTitle = mySwipeLayout.findViewById(R.id.tvNoteTitle);
        tvNoteTitle.setText(notesModel.getGuitarNotesFile(position));
        Button btnDelete = mySwipeLayout.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notesModel.deleteGuitarNotes(notesModel.getGuitarNotesFile(position), position);
                notifyDataSetChanged();
            }
        });

        if (mySwipeLayout.isBtnDeleteVisible) {
            mySwipeLayout.hideDeleteButton();
        }

        return convertView;
    }

}
