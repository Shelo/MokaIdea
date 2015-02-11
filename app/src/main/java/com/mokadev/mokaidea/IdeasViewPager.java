package com.mokadev.mokaidea;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

public class IdeasViewPager extends FragmentActivity {


	private ViewPager viewPager;
	private ArrayList<Idea> ideas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		viewPager = new ViewPager(this);
		viewPager.setId(R.id.ideas_view_pager);
		setContentView(viewPager);

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
	}
}
