package com.mokadev.mokaidea;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {
    public static final int NEW_IDEA = 0;

	private RepoSpinnerAdapter spinnerAdapter;
	private IdeaListFragment fragment;
	private ViewPager viewPager;
	private Toolbar toolbar;
	private Spinner repos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        // Repos spinner
        repos = (Spinner) findViewById(R.id.spinner_repo);
        spinnerAdapter = new RepoSpinnerAdapter(this);
        repos.setAdapter(spinnerAdapter);
        repos.setOnItemSelectedListener(this);

        IdeaSwipeRefresh srlayout = (IdeaSwipeRefresh) findViewById(R.id.swipe_container);

        // Initialize repo fragment
        if(savedInstanceState == null)
            fragment = new IdeaListFragment();

		if(fragment == null)
			fragment = new IdeaListFragment();

        // Sets list view on creation
        fragment.setIdeaSwipeRefresh(srlayout);

        // get view pager.
        viewPager = (ViewPager) findViewById(R.id.main_view_pager);
		viewPager.setAdapter(new MainViewPagerAdapter(getResources(),
				getSupportFragmentManager(), fragment, new Fragment()));

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override public void onPageScrolled(int i, float v, int i2) { }
			@Override public void onPageSelected(int i) { }

			@Override
			public void onPageScrollStateChanged(int state) {
				switch(state) {
					case ViewPager.SCROLL_STATE_DRAGGING:
						fragment.getIdeaSwipeRefresh().setEnabled(false);
						break;

					case ViewPager.SCROLL_STATE_SETTLING:
					case ViewPager.SCROLL_STATE_IDLE:
						fragment.getIdeaSwipeRefresh().setEnabled(true);
				}
			}
		});
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

}