package com.mokadev.mokaidea;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import org.w3c.dom.Text;


public class IdeaFragment extends Fragment {
	public static final String ID_IDEA = "moka.ID_IDEA";

	private Idea idea;

	private TextView description;
	private TextView author;
	private TextView title;
	private TextView date;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int idIdea = (int) getArguments().getSerializable(ID_IDEA);
		idea = RepoManager.getInstance().getLoadedRepository().findIdeaWithId(idIdea);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_idea, container);

		description = (TextView) view.findViewById(R.id.title);
		author		= (TextView) view.findViewById(R.id.author);
		title		= (TextView) view.findViewById(R.id.title);
		date		= (TextView) view.findViewById(R.id.date);

		description.setText(getString(R.string.lorem_ipsum));
		author.setText(idea.getAuthor());
		title.setText(idea.getTitle());
		date.setText(idea.getDate());

		return view;
	}
}
