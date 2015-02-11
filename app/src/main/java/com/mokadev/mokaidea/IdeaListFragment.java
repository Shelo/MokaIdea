package com.mokadev.mokaidea;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class IdeaListFragment extends ListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new IdeaAdapter(IdeaDatabase.getIdeas()));
	}

	private class IdeaAdapter extends ArrayAdapter<Idea> {
		public IdeaAdapter(ArrayList<Idea> ideas) {
			super(getActivity(), android.R.layout.simple_list_item_1, ideas);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Log.d("MOKA.DEBUG", String.valueOf(position));

			// if the view is first asked, it will be null, if so, we should inflate the xml.
			if(convertView == null)
				convertView = getActivity().getLayoutInflater().inflate(R.layout.idea_item, null);

			// get idea at that position.
			Idea idea = getItem(position);

			// get view fields.
			TextView title = (TextView) convertView.findViewById(R.id.idea_item_title);
			TextView desc = (TextView) convertView.findViewById(R.id.idea_item_short_desc);
			CheckBox upVote = (CheckBox) convertView.findViewById(R.id.idea_item_up_vote);

			// fill view fields.
			title.setText(idea.getTitle());
			desc.setText(idea.getShortDescription());
			upVote.setChecked(idea.isUpVoted());

			return convertView;
		}
	}
}
