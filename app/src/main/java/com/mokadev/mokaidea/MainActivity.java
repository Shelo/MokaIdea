package com.mokadev.mokaidea;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Spinner;


public class MainActivity extends ActionBarActivity {
    Toolbar toolbar;
    Spinner repos;
    RepoSpinnerAdapter spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Repos spinner
        repos = (Spinner) findViewById(R.id.spinner_repo);
        spinnerAdapter = new RepoSpinnerAdapter(this);
        repos.setAdapter(spinnerAdapter);

		FragmentManager manager = getSupportFragmentManager();
		Fragment fragment = manager.findFragmentById(R.id.fragment_container);

		if(fragment == null) {
			fragment = new IdeaListFragment();
			manager
					.beginTransaction()
					.add(R.id.fragment_container, fragment)
					.commit();
		}
    }
}
