package com.mokadev.mokaidea;

import java.util.ArrayList;
import java.util.List;

public class DummyDB {
    static List<Repository> repositories = new ArrayList<>();

    static {
        for (int i = 1; i <= 20; i++) {
            Repository add = new Repository("Repository" + i, i);
            repositories.add(add);

            for (int j = 1; j <= 50; j++) {
                Idea idea = new Idea();
                idea.setTitle("Repository " + i + "'s idea #" + j);
                idea.setShortDescription("Description for idea " + j);
                idea.setUpVoted((j % 2) == 0);
                add.add(idea);
            }
        }
    }
}
