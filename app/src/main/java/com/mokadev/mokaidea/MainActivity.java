package com.mokadev.mokaidea;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity
        implements AdapterView.OnItemSelectedListener {
    Toolbar toolbar;
    Spinner repos;
    RepoSpinnerAdapter spinnerAdapter;
    Fragment fragment;

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
        fragment = manager.findFragmentById(R.id.fragment_container);

        if(fragment == null) {
            fragment = new IdeaListFragment();
            manager
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.i("onItemSelected(parent, view, " + position + ", id)", "Selected.");
        Repository repo = RepoManager.getInstance().get(position);
        RepoManager.getInstance().load(repo.id);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


}