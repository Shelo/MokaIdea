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

    public void populate() {
        new RepoAsyncRetrieve().execute("http://104.131.186.70/mokaidea/retrieve_repos_and_ideas.php?id_user=1");
    }

    public void reload() {
        if (ideaAdapter != null)
            ideaAdapter.notifyDataSetChanged();
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
            if (repo.getName().equals(repoName)) {
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
                JSONArray array = new JSONArray(json);


                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);

                    String repoName = jsonObject.getString("name");
                    int repoID = jsonObject.getInt("id_repository");

                    Repository repo = new Repository(repoName, repoID);

                    JSONArray jideas = jsonObject.getJSONArray("ideas");

                    for (int j = 0; j < jideas.length(); j++) {
                        JSONObject jidea = jideas.getJSONObject(j);

                        Idea idea = new Idea.Builder(jidea.getInt("id_idea"))
                                .title(jidea.getString("title"))
                                .shortDescription(jidea.getString("short_description"))
                                .upVoted(false)
                                .idAuthor(jidea.getInt("id_author"))
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
