package com.mokadev.mokaidea;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;

public class RepoManager extends ArrayList<Repository> {
    private static RepoManager instance;
    private Repository loadedRepository;
    private RepoSpinnerAdapter repoAdapter;
    private IdeaListFragment.IdeaAdapter ideaAdapter;

    private RepoManager() {


        // Debug
        //populateDefault();
        populate();

        loadedRepository = new Repository("Loading", 42);
        add(loadedRepository);

        // Default loaded repo
        //load(get(0));
    }

    public static RepoManager getInstance() {
        if (instance == null)
            instance = new RepoManager();

        return instance;
    }

    public void setRepoAdapter(RepoSpinnerAdapter repoAdapter) {
        this.repoAdapter = repoAdapter;
    }

    public void setIdeaAdapter(IdeaListFragment.IdeaAdapter ideaAdapter) {
        this.ideaAdapter = ideaAdapter;
    }

    public void populateDefault() {
        for (Repository repo : DummyDB.repositories)
            add(repo);
    }

    public void populate() {
        new RepoAsyncRetrieve().execute("http://104.131.186.70/mokaidea/retrieve_all.php");
    }

    public void load(int repoid) {
        for (Repository repo : this)
            if (repo.id == repoid) {
                loadedRepository = repo;
                break;
            }

        if (ideaAdapter != null)
            ideaAdapter.notifyDataSetChanged();
    }

    public void load(Repository repo) {
        loadedRepository = repo;

        if (ideaAdapter != null)
            ideaAdapter.notifyDataSetChanged();
    }

    public void load(String repoName) {
        for (Repository repo : this)
            if (repo.name.equals(repoName)) {
                loadedRepository = repo;
                break;
            }

        if (ideaAdapter != null)
            ideaAdapter.notifyDataSetChanged();
    }

    public Repository getLoadedRepository() {
        return loadedRepository;
    }

    private class RepoAsyncRetrieve extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String json = in.readLine();
                JSONObject jsonObject = new JSONObject(json);

                Iterator<String> it = jsonObject.keys();

                while (it.hasNext()) {
                    String key = it.next();
                    Repository repo = new Repository("Repository " + key, Integer.parseInt(key));
                    JSONArray array = jsonObject.getJSONArray(key);

                    Log.i("array.length():", String.valueOf(array.length()));

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jidea = array.getJSONObject(i);

                        Idea idea = new Idea.Builder(jidea.getInt("id_idea"))
                                .title(jidea.getString("title"))
                                .shortDescription(jidea.getString("short_description"))
                                .upVoted(false)
                                .build();

                        repo.add(idea);
                    }

                    add(repo);
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            RepoManager.getInstance().remove(0);
            RepoManager.getInstance().load(RepoManager.getInstance().get(0));
            repoAdapter.notifyDataSetChanged();
        }
    }
}
