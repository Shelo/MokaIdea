package com.mokadev.mokaidea;

import android.support.v4.app.Fragment;

public class IdeaListActivity extends FragmentedActivity {
	@Override
	protected Fragment getFragment() {
		return new IdeaListFragment();
	}
}
