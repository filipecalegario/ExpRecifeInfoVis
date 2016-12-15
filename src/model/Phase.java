package model;

import util.DurationFormat;

public class Phase {
	
	private String title;
	private int duration;
	
	public Phase(String title, String duration) {
		super();
		this.title = title;
		this.duration = DurationFormat.stringToSeconds(duration);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDurationInString() {
		return DurationFormat.secondsToString(this.duration);
	}
	
	public int getDurationInSeconds() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
