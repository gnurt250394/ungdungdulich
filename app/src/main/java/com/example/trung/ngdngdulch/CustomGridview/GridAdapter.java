package com.example.trung.ngdngdulch.CustomGridview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.zip.Inflater;

public class GridAdapter  extends ArrayAdapter<Obj_Grid> {
    private Context context;
    private int resource;
    private List<Obj_Grid> object;
    public GridAdapter( Context context, int resource, List<Obj_Grid> objects) {
        super(context, resource, objects);
        this.context = context;
        this.object = objects;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Inflater inflater = new Inflater();

        return view;
    }
}
