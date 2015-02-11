package com.mokadev.mokaidea;

import java.util.UUID;

public class Idea {
	private String shortDescription;
	private int idRepository;
	private boolean upVoted;
	private String title;
	private int idAuthor;
	private int upVotes;
	private int id;

	Idea(Builder builder) {
		this.shortDescription 	= builder.shortDescription;
		this.idRepository 		= builder.idRepository;
		this.idAuthor 			= builder.idAuthor;
		this.upVoted 			= builder.upVoted;
		this.title 				= builder.title;
		this.id 				= builder.id;
	}

	public static class Builder {
		private String shortDescription;
		private int idRepository;
		private boolean upVoted;
		private String title;
		private int idAuthor;
		private int upVotes;
		private int id;

		public Builder(int id) {
			this.id = id;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder idRepository(int idRepository) {
			this.idRepository = idRepository;
			return this;
		}

		public Builder idAuthor(int idAuthor) {
			this.idAuthor = idAuthor;
			return this;
		}

		public Builder upVoted(boolean upVoted) {
			this.upVoted = upVoted;
			return this;
		}

		public Builder shortDescription(String shortDescription) {
			this.shortDescription = shortDescription;
			return this;
		}

		public Builder upVotes(int upVotes) {
			this.upVotes = upVotes;
			return this;
		}

		public Idea build() {
			return new Idea(this);
		}
	}

	public int getId() {
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

	public int getUpVotes() {
		return upVotes;
	}

	public int getIdAuthor() {
		return idAuthor;
	}

	public int getIdRepository() {
		return idRepository;
	}
}
