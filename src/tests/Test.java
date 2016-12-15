package tests;

import util.DurationFormat;

public class Test {

	public static void main(String[] args) {
		System.out.println(DurationFormat.secondsToString(125));
		System.out.println(DurationFormat.stringToSeconds("00:02:05"));
		System.out.println(DurationFormat.stringToSeconds("26:20"));

	}

}
