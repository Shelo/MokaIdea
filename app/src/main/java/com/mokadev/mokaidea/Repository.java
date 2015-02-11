package com.mokadev.mokaidea;

import java.util.ArrayList;

public class Repository extends ArrayList<Idea> {
    public enum Type {
        PUBLIC,
        PRIVATE
    }

    public String name;
    public int id;
    Type type;

    public Repository(String name, int id) {
        this.name = name;
        this.id = id;
    }

	public Idea findIdeaWithId(int id) {
		for(Idea idea : this)
			if(idea.getId() == id)
				return idea;
		return null;
	}

    public Type getType() {
        return type;
    }
}
