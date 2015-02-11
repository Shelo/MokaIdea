package com.mokadev.mokaidea;

public class Idea {
	private String shortDescription;
	private int idRepository;
	private boolean upVoted;
	private String author;
	private String title;
	private int idAuthor;
	private String date;
	private int upVotes;
	private int id;

	private Idea(Builder builder) {
		shortDescription 	= builder.shortDescription;
		idRepository 		= builder.idRepository;
		idAuthor 			= builder.idAuthor;
		upVotes				= builder.upVotes;
		upVoted 			= builder.upVoted;
		author				= builder.author;
		title 				= builder.title;
		date				= builder.date;
		id 					= builder.id;
	}

	public String getDate() {
		return date;
	}

	public static class Builder {
		private String shortDescription;
		private int idRepository;
		private boolean upVoted;
		private String author;
		private String title;
		private int idAuthor;
		private int upVotes;
		private String date;
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

		public Builder author(String author) {
			this.author = author;
			return this;
		}

		public Builder date(String date) {
			this.date = date;
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

	public String getAuthor() {
		return author;
	}
}
