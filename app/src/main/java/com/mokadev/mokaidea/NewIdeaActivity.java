package com.mokadev.mokaidea;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


public class NewIdeaActivity extends ActionBarActivity {
    Toolbar toolbar;
    int repoid;

    EditText titleField;
    EditText descField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_idea);

        // Initialize toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("New idea");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        repoid = RepoManager.getInstance().getLoadedRepository().id;

        titleField = (EditText) findViewById(R.id.title_field);
        descField = (EditText) findViewById(R.id.description_field);
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
        String title = titleField.getText().toString().trim();
        String desc = descField.getText().toString().trim();

        if (title.equals("")) {
            Toast.makeText(this, "Title must be set!", Toast.LENGTH_SHORT).show();
            return;
        } else if (desc.equals("")) {
            Toast.makeText(this, "Description must be set!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("title", title);
        intent.putExtra("desc", desc);
        setResult(ResultID.SUCCESS, intent);
        finish();
    }
}
