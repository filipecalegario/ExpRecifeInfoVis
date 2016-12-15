package main;

import java.util.HashMap;

import grafica.GPlot;
import model.Phase;
import model.Session;
import processing.core.PApplet;
import processing.data.Table;
import util.DurationFormat; 

public class Segments extends PApplet {

	GPlot plot;
	HashMap<String, Integer> cores;

	public void setup() {
		// Define the window size
		cores = new HashMap<String, Integer>();
		cores.put("Mounting", color(137,47,137));
		cores.put("Mapping", color(224,218,45));
		cores.put("Testing", color(213,108,49));
		cores.put("Bug", color(220,41,41));
		cores.put("Consulting", color(40,40,130));
		cores.put("Planning", color(40,130,130));
		cores.put("Default", color(151,151,151));

		HashMap<String, Session> sessions = new HashMap<>();
		Table table = loadTable("segmentacaoVideo3.csv", "header");
		for (int row = 0; row < table.getRowCount(); row++) {
			String entrevistado = table.getString(row, "entrevistado");
			String fase = table.getString(row, "fase");
			String duracao = table.getString(row, "duracao");
			String session = table.getString(row, "session");
			Session s = null;
			if(sessions.containsKey(entrevistado)){
				s = sessions.get(entrevistado);
			} else {
				s = new Session(entrevistado, session);
				sessions.put(entrevistado, s);
			}
			if(s != null){
				s.addPhase(new Phase(fase, duracao));
			}
		}
	}

	public void draw() {
		background(255);
		noLoop();
		Table table = loadTable("segmentacaoVideo3.csv", "header");
		String entrevistado = "";
		int indexY = 0;
		int x = 0;
		int y = 0;
		int upperMargin = 5;
		int leftMargin = 140;
		//======================

		//======================
		for (int row = 0; row < table.getRowCount(); row++) {
			String currentEntrevistado = table.getString(row, "entrevistado");

			if(!currentEntrevistado.equals(entrevistado)){
				y = (20 * indexY++) + upperMargin;
				x = 0;
				textAlign(LEFT, TOP);
				textSize(14);
				fill(0);
				text(currentEntrevistado, 5, y);
			}

			String fase = table.getString(row, "fase");
			int cor = 0;
			if(cores.containsKey(fase)){
				cor = cores.get(fase);
			} else {
				cor = cores.get("Default");
			}

			String duracao = table.getString(row, "duracao");
			int largura = durationToPixels(duracao);

			noStroke();
			fill(cor);
			rect(x + leftMargin, y, largura, 20);
			//System.out.println(entrevistado + " " + currentEntrevistado + " " + fase + " " + largura + " " + x + " " + y);

			x = x + largura;
			entrevistado = currentEntrevistado;
		}
		System.out.println("Foi!");
		//exit();
	}

	public int durationToPixels(String duration){
		return Math.round(DurationFormat.stringToSeconds(duration)/2.0f);
	}
	public void settings() {  size(1200, 770); }
	//public void settings() {  size(1200, 770, PDF, "export/segments_v0.0.3.pdf"); }
	static public void main(String[] passedArgs) {
		String[] appletArgs = new String[] { "main.Segments" };
		if (passedArgs != null) {
			PApplet.main(concat(appletArgs, passedArgs));
		} else {
			PApplet.main(appletArgs);
		}
	}
}
