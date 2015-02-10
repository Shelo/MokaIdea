package com.mokadev.mokaidea;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class RepoSpinnerAdapter extends ArrayAdapter<String> {
    public RepoSpinnerAdapter(Context context, int resource) {
        super(context, resource, RepoManager.getInstance());
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
