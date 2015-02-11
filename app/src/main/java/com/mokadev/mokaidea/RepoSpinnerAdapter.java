package com.mokadev.mokaidea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RepoSpinnerAdapter extends ArrayAdapter<Repository> {
    LayoutInflater inflater;

    private class ViewHolder {
        TextView item;
    }

    public RepoSpinnerAdapter(Context context) {
        super(context, R.layout.spinner_item, RepoManager.getInstance());

        // Get inflater
        inflater = LayoutInflater.from(context);
        RepoManager.getInstance().setRepoAdapter(this);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        Repository repo = RepoManager.getInstance().get(position);
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.spinner_item, parent, false);
            holder.item = (TextView) convertView.findViewById(R.id.repo_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.item.setText(repo.getName());

        return convertView;
    }
}
