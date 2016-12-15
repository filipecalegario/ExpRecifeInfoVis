package examples;

import grafica.GPlot;
import grafica.GPointsArray;
import processing.core.PApplet; 

public class ExportToPdf extends PApplet {

GPlot plot;

public void setup() {
  
  textMode(SHAPE);
  
  // Prepare the points for the plot
  int nPoints = 100;
  GPointsArray points = new GPointsArray(nPoints);

  for (int i = 0; i < nPoints; i++) {
    points.add(i, 10*noise(0.1f*i));
  }

  // Create a new plot and set its position on the screen
  plot = new GPlot(this);
  plot.setPos(25, 25);
  // or all in one go
  // plot = new GPlot(this, 25, 25);

  // Set the plot title and the axis labels
  plot.setTitleText("A very simple example");
  plot.getXAxis().setAxisLabelText("x axis");
  plot.getYAxis().setAxisLabelText("y axis");

  // Add the points
  plot.setPoints(points);
}

public void draw(){
  background(150);

  // Draw it!
  plot.defaultDraw();
  exit();
}
  public void settings() {  size(500, 350, PDF, "export/graficaPlot.pdf"); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "examples.ExportToPdf" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
