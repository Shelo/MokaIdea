package com.mokadev.mokaidea;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class IdeasViewPagerActivity extends ActionBarActivity {
	private ViewPager viewPager;
	private ArrayList<Idea> ideas;
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ideas_view_pager);

		// Initialize toolbar
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle(RepoManager.getInstance().getLoadedRepository().getName());
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		// initilize viewPager.
		viewPager = new ViewPager(this);
		viewPager.setId(R.id.ideas_view_pager);

		FrameLayout layout = (FrameLayout) findViewById(R.id.fragment_container);
		layout.addView(viewPager);

		ideas = RepoManager.getInstance().getLoadedRepository();

		viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
			@Override
			public Fragment getItem(int i) {
				Bundle arguments = new Bundle();
				arguments.putSerializable(IdeaFragment.ID_IDEA, ideas.get(i).getId());
				IdeaFragment fragment = new IdeaFragment();
				fragment.setArguments(arguments);
				return fragment;
			}

			@Override
			public int getCount() {
				return ideas.size();
			}
		});

		int id = (int) getIntent().getSerializableExtra(IdeaFragment.ID_IDEA);
		for(int i = 0; i < ideas.size(); i++)
			if(ideas.get(i).getId() == id) {
				viewPager.setCurrentItem(i);
				break;
			}
	}
}
