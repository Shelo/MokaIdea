package com.mokadev.mokaidea;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
	Fragment allIdeas;
	Fragment drafts;
	String allIdeasPageTitle;
	String draftsPageTitle;

	public MainViewPagerAdapter(Resources resources, FragmentManager fm, Fragment allIdeas, Fragment drafts) {
		super(fm);

		this.allIdeas = allIdeas;
		this.drafts = drafts;

		allIdeasPageTitle = resources.getString(R.string.all_ideas_page_title);
		draftsPageTitle = resources.getString(R.string.drafts_page_title);
	}

	@Override
	public Fragment getItem(int i) {
		switch(i) {
			case 0: return allIdeas;
			case 1: return drafts;
			default: return null;
		}
	}

	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch(position) {
			case 0: return allIdeasPageTitle;
			case 1: return draftsPageTitle;
			default: return null;
		}
	}
}
