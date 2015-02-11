package com.mokadev.mokaidea;

import java.util.UUID;

public class Idea {
	private String title;
	private String shortDescription;
	private UUID id;
	private boolean upVoted;

	public Idea() {
		id = UUID.randomUUID();
	}

	public UUID getId() {
		return id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setUpVoted(boolean upVoted) {
		this.upVoted = upVoted;
	}

	public boolean isUpVoted() {
		return upVoted;
	}
}
