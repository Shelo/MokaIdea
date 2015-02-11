package com.mokadev.mokaidea;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

public abstract class FragmentedActivity extends ActionBarActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.idea_list_fragment);

		FragmentManager manager = getSupportFragmentManager();
		Fragment fragment = manager.findFragmentById(R.id.fragment_container);

		if(fragment == null) {
			fragment = getFragment();
			manager
					.beginTransaction()
					.add(R.id.fragment_container, fragment)
					.commit();
		}
	}

	protected abstract Fragment getFragment();
}
