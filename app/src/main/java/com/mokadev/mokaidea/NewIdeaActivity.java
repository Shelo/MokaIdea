package com.mokadev.mokaidea;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;


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

        sendPost(1, title, desc, desc);

        finish();
    }

    private void sendPost(int author, String title, String desc, String sdesc) {
        try {
            JSONObject data = new JSONObject();
            data.put("id_author", author);
            data.put("title", title);
            data.put("desc", desc);
            data.put("sdesc", sdesc);
            data.put("id_repository", RepoManager.getInstance().getLoadedRepository().id);
            new AsyncPost().execute(data.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class AsyncPost extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL("http://104.131.186.70/mokaidea/push_idea.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                String data = "data="+params[0];
                conn.setDoOutput(true);
                conn.setInstanceFollowRedirects(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("charset", "UTF-8");
                conn.setFixedLengthStreamingMode(data.length());
                conn.setUseCaches(false);

                OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

                writer.write(data);
                writer.flush();

                writer.close();

                Log.i("doInBackground()", "Done.");
            } catch (IOException e) {
                Log.i("doInBackground()", "Failed.");
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(NewIdeaActivity.this, "Written!", Toast.LENGTH_SHORT).show();
        }
    }
}
