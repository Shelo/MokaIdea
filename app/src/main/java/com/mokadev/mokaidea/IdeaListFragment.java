package com.mokadev.mokaidea;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class IdeaListFragment extends ListFragment {
    IdeaAdapter adapter;
    IdeaSwipeRefresh ideaSwipeRefresh;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        adapter = new IdeaAdapter(RepoManager.getInstance().getLoadedRepository());
		setListAdapter(adapter);
        RepoManager.getInstance().setIdeaAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Idea idea = RepoManager.getInstance().getLoadedRepository().get(position);
		Intent intent = new Intent(getActivity(), IdeasViewPagerActivity.class);
		Log.d("MOKA.DEBUG", "idea: " + idea.getTitle());
		intent.putExtra(IdeaFragment.ID_IDEA, idea.getId());
		startActivity(intent);
	}

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (ideaSwipeRefresh != null)
            ideaSwipeRefresh.setListView(getListView());
    }

    public void setIdeaSwipeRefresh(IdeaSwipeRefresh ideaSwipeRefresh) {
        this.ideaSwipeRefresh = ideaSwipeRefresh;
    }

    public class IdeaAdapter extends ArrayAdapter<Idea> {
        private class ViewHolder {
            TextView title;
            TextView desc;
            CheckBox upVote;
        }

        public IdeaAdapter(ArrayList<Idea> ideas) {
            super(getActivity(), android.R.layout.simple_list_item_1, ideas);
        }

        @Override
        public int getCount() {
            return RepoManager.getInstance().getLoadedRepository().size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            Log.i("getView(" + position + ", convertView, parent)", "Update.");

            // if the view is first asked, it will be null, if so, we should inflate the xml.
            if(convertView == null) {
                holder = new ViewHolder();
                convertView = getActivity().getLayoutInflater().inflate(R.layout.idea_item, null);
                holder.title = (TextView) convertView.findViewById(R.id.idea_item_title);
                holder.desc = (TextView) convertView.findViewById(R.id.idea_item_short_desc);
                holder.upVote = (CheckBox) convertView.findViewById(R.id.idea_item_up_vote);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            // get idea at that position.
            //Idea idea = getItem(position);
            Idea idea = RepoManager.getInstance().getLoadedRepository().get(position);

            // fill view fields.
            holder.title.setText(idea.getTitle());
            holder.desc.setText(idea.getShortDescription());
            holder.upVote.setChecked(idea.isUpVoted());

            return convertView;
        }
	}
}
