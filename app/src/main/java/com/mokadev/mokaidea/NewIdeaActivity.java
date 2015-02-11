package com.mokadev.mokaidea;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class NewIdeaActivity extends ActionBarActivity {
    Toolbar toolbar;
    int repoid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_idea);

        // Initialize toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("New idea");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get repoid
        //repoid = getIntent().getIntExtra("repoid", -1);

        repoid = RepoManager.getInstance().getLoadedRepository().id;

        /*if (repoid == -1) {
            Intent intent = new Intent();
            setResult(ResultID.MISSING_EXTRAS, intent);
            finish();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_idea, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(ResultID.FAILED, intent);
        finish();
    }

    public void done(MenuItem item) {
        Intent intent = new Intent();
        setResult(ResultID.SUCCESS, intent);
        finish();
    }
}
