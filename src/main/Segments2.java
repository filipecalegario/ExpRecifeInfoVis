package main;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import grafica.GPlot;
import model.Phase;
import model.Session;
import processing.core.PApplet;
import processing.data.Table;
import util.DurationFormat; 

public class Segments2 extends PApplet {

	GPlot plot;
	HashMap<String, Integer> cores;
	Map<String, Session> sessions = new LinkedHashMap<>();

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
		int indexY = 0;
		float x = 0;
		float y = 0;
		float upperMargin = 20f;
		float leftMargin = 140f;
		float sectionHeight = 20f;
		//======================

		//======================
		
		Set<String> set = sessions.keySet();
		String out = String.format("%s,%s,%s,%s,%s,%s", "Session", "Source", "Target","Weight", "Option", "Participant");
		System.out.println(out);
		for (String key : set) {
			Session s = sessions.get(key);
			y = (sectionHeight * indexY++) + upperMargin;
			x = 0;
			textAlign(LEFT, TOP);
			textSize(14);
			fill(0);
			text(s.getParticipant(), 5, y);
			int maxTesting = 0;
			for (Phase p : s.getPhases()) {
				int cor = 0;
				if(cores.containsKey(p.getTitle())){
					cor = cores.get(p.getTitle());
				} else {
					cor = cores.get("Default");
				}

				float largura = durationToPixels(p.getDurationInString());

				noStroke();
				fill(cor);
				rect(x + leftMargin, y, largura, 20);
				//System.out.println(entrevistado + " " + currentEntrevistado + " " + fase + " " + largura + " " + x + " " + y);
				stroke(127);
				line(0, y, width, y);
				x = x + largura;
				if(p.getTitle().equals("Testing")){
					maxTesting = Math.max(p.getDurationInSeconds(), maxTesting);
				}
			}
			textAlign(LEFT, TOP);
			textSize(14);
			fill(0);
			text(DurationFormat.secondsToString(s.calculateTotalDurationInSeconds()), 810 + leftMargin, y );
			text(DurationFormat.secondsToString(maxTesting), 810 + leftMargin + 50, y);
			//System.out.println(s.getParticipant() + ",00:" + DurationFormat.secondsToString(maxTesting));
			s.printTransactionsReport2();
		}
		text("TOTAL", 810 + leftMargin, 2);
		text("MAX_TEST", 810 + leftMargin + 50, 2);
		for (int i = 0; i < 27; i++) {
			float myX = i*30 + leftMargin;
			line(myX, 0, myX, height);
			textAlign(LEFT, TOP);
			textSize(8);
			//noStroke();
			fill(0);
			text(DurationFormat.secondsToString(i*60), myX+3, 5);
		}
		
		//exit();
	}

	public float durationToPixels(String duration){
		return (DurationFormat.stringToSeconds(duration)/2.0f);
	}
	public void settings() {  size(1200, 790); }
	//public void settings() {  size(1200, 790, PDF, "export/segments_v0.0.3.pdf"); }
	static public void main(String[] passedArgs) {
		String[] appletArgs = new String[] { "main.Segments2" };
		if (passedArgs != null) {
			PApplet.main(concat(appletArgs, passedArgs));
		} else {
			PApplet.main(appletArgs);
		}
	}
}
