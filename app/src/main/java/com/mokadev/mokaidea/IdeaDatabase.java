package com.mokadev.mokaidea;

import java.util.ArrayList;
import java.util.UUID;

public class IdeaDatabase {
	private static ArrayList<Idea> ideas;

	static {
		ideas = new ArrayList<>();

		for(int i = 0; i < 10; i++) {
			Idea idea = new Idea();
			idea.setTitle("Idea #" + i);
			idea.setShortDescription("Descripcion corta " + i);
			ideas.add(idea);
		}
	}

	public static ArrayList<Idea> getIdeas() {
		return ideas;
	}

	public static Idea getIdea(UUID id) {
		for(Idea idea : ideas)
			if(idea.getId().equals(id))
				return idea;
		return null;
	}
}
