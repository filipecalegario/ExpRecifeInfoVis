package util;

import processing.core.PApplet;

public class ColorUtilities {
	
	/*
	def rgb(minimum, maximum, value):
    minimum, maximum = float(minimum), float(maximum)
    ratio = 2 * (value-minimum) / (maximum - minimum)
    b = int(max(0, 255*(1 - ratio)))
    r = int(max(0, 255*(ratio - 1)))
    g = 255 - b - r
    return r, g, b
    */
	
	public static int getColorHeatMap(float min, float max, float value){
		float ratio = 2 * (value-min) / (max - min);
		int r = (int) (Math.max(0, 255*(ratio - 1)));
		int b = (int) Math.max(0, 255*(1 - ratio));
		int g = 255 - b - r;
		PApplet core = new PApplet();
		return core.color(r, g, b);
	}

}
