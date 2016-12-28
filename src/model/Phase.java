package model;

import util.DurationFormat;

public class Phase {
	
	private String title;
	private String startTime;
	private String endTime;
	private int duration;
	
	public Phase(String title, String duration) {
		super();
		this.title = title;
		this.duration = DurationFormat.stringToSeconds(duration);
	}

	public Phase(String title, String duration, String startTime, String endTime) {
		super();
		this.title = title;
		this.duration = DurationFormat.stringToSeconds(duration);
		this.startTime = startTime;
		this.endTime = endTime;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
