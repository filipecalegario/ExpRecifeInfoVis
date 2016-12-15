package util;

public class DurationFormat {

	public static String secondsToString(int seconds) {
		return String.format("%02d:%02d", seconds / 60, seconds % 60);
	}

	public static int stringToSeconds(String time){
		String[] parts = time.split(":");
		int minutes = 0;
		int seconds = 0;
		if(parts.length == 2){
			minutes = Integer.parseInt(parts[0]);
			seconds = Integer.parseInt(parts[1]);
		} else if(parts.length == 3){
			minutes = Integer.parseInt(parts[1]);
			seconds = Integer.parseInt(parts[2]);
		}
		return minutes*60 + seconds;
	}

}
