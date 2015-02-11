package com.mokadev.mokaidea;

import java.util.ArrayList;
import java.util.List;

public class RepoManager extends ArrayList<String> {
    private static RepoManager instance;

    private RepoManager() {
        // Debug
        loadDefault();
    }

    public static RepoManager getInstance() {
        if (instance == null)
            instance = new RepoManager();

        return instance;
    }



    public void loadDefault() {
        for (int i = 1; i <= 20; i++)
            add("Item " + i);
    }
}
