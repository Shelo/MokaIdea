package com.mokadev.mokaidea;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity
        implements AdapterView.OnItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    public static final int NEW_IDEA = 0;

	RepoSpinnerAdapter spinnerAdapter;
	SwipeRefreshLayout srlayout;
	IdeaListFragment fragment;
	Toolbar toolbar;
	Spinner repos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ideas");

        // Repos spinner
        repos = (Spinner) findViewById(R.id.spinner_repo);
        spinnerAdapter = new RepoSpinnerAdapter(this);
        repos.setAdapter(spinnerAdapter);
        repos.setOnItemSelectedListener(this);

        // Initialize repo fragment
        FragmentManager manager = getSupportFragmentManager();
        if(savedInstanceState == null) {
            fragment = new IdeaListFragment();
            manager
                    .beginTransaction()
                    .add(R.id.swipe_container, fragment)
                    .commit();
        }

        srlayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        srlayout.setOnRefreshListener(this);
        srlayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView textView = (TextView) parent.getChildAt(0);

        if (textView != null)
            textView.setTextColor(Color.WHITE);

        Repository repo = RepoManager.getInstance().get(position);
        RepoManager.getInstance().load(repo.id);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void newEntry(MenuItem item) {
        Intent intent = new Intent(this, NewIdeaActivity.class);
        startActivityForResult(intent, NEW_IDEA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NEW_IDEA) {
            if (resultCode == ResultID.SUCCESS) {
                String title = data.getStringExtra("title");
                String desc = data.getStringExtra("desc");
            }
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                srlayout.setRefreshing(false);
            }
        }, 5000);
    }

}