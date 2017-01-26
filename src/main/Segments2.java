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
	Map<String, Session> sessionsByParticipants = new LinkedHashMap<>();
	String selected = "Mounting Mapping Testing Consulting Planning Bug";
	String g14 = "P03, P04, P05, P06, P07, P08, P09, P10, P11, P13, P14, P17, P18, P19";
	String g05 = "P01, P02, P12, P15, P16";
	//	String selected = "Mounting";
	//	String selected = "Mapping";
	//	String selected = "Testing";
	//	String selected = "Consulting";
	//	String selected = "Planning";
	//	String selected = "Bug";
	//	String selected = "Mapping Testing";
	//	String selected = "Consulting Planning Bug";
	//	String selected = "Consulting Planning";
	//	String selected = "Mounting Consulting Planning Bug";
	int durationSelected = 0;
	int gephiIndex = 0;

	public void setup() {
		// Define the window size
		cores = new HashMap<String, Integer>();
		//		cores.put("Mounting", color(137,47,137));
		//		cores.put("Mapping", color(224,218,45));
		//		cores.put("Testing", color(213,108,49));
		//		cores.put("Bug", color(220,41,41));
		//		cores.put("Consulting", color(40,40,130));
		//		cores.put("Planning", color(40,130,130));
		// ============================
		//		cores.put("Mounting",color(215,48,39));
		//		cores.put("Mapping",color(252,141,89));
		//		cores.put("Testing",color(254,224,144));
		//		cores.put("Bug",color(224,243,248));
		//		cores.put("Consulting",color(145,191,219));
		//		cores.put("Planning",color(69,117,180));
		// ============================
		cores.put("Bug", color(228,26,28));
		cores.put("Consulting", color(55,126,184));
		cores.put("Planning", color(77,175,74));
		cores.put("Mounting", color(152,78,163));
		cores.put("Testing", color(255,127,0));
		cores.put("Mapping", color(255,255,51));
		// ============================
		//		cores.put("Consulting",color(27,158,119));
		//		cores.put("Testing",color(217,95,2));
		//		cores.put("Mounting",color(117,112,179));
		//		cores.put("Bug",color(231,41,138));
		//		cores.put("Planning",color(102,166,30));
		//		cores.put("Mapping",color(230,171,2));
		// ============================
		cores.put("Default", color(151,151,151));

		Table table = loadTable("ProcessedMergedCSV_v0.0.4_PXX_TwoGroups_Corrected.csv", "header");
		for (int row = 0; row < table.getRowCount(); row++) {
			String participant = table.getString(row, "Participant");
			String group = table.getString(row, "Group");
			String fase = table.getString(row, "Phase");
			String duracao = table.getString(row, "Duration");
			String option = table.getString(row, "Option");
			String startTime = table.getString(row, "StartTime");
			String endTime = table.getString(row, "EndTime");
			Session s = null;
			if(sessionsByParticipants.containsKey(participant)){
				s = sessionsByParticipants.get(participant);
			} else {
				s = new Session(participant, option, group);
				sessionsByParticipants.put(participant, s);
			}
			if(s != null){
				s.addPhase(new Phase(fase, duracao,startTime,endTime));
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
		float leftMargin = 55f;
		float sectionHeight = 20f;
		//======================

		//======================

		Set<String> participantsKeys = sessionsByParticipants.keySet();
		//		/String out = String.format("%s,%s,%s,%s,%s,%s", "Session", "Source", "Target","Weight", "Option", "Participant");
		//System.out.println(out);
		for (String participantKey : participantsKeys) {
			Session s = sessionsByParticipants.get(participantKey);
			durationSelected = 0;
			//			if (!s.getGroup().equals("G05")) {
			if (true) {
				//			if (s.getGroup().equals("G05")) {
				y = (sectionHeight * indexY++) + upperMargin;
				x = 0;
				textAlign(LEFT, TOP);
				textSize(14);
				fill(0);
				text(s.getParticipant(), 5, y);
				int maxTesting = 0;
				for (Phase p : s.getPhases()) {
					int cor = 0;
					//					if (cores.containsKey(p.getTitle())) {
					//						cor = cores.get(p.getTitle());
					//					} else {
					//						cor = cores.get("Default");
					//					}
					//					if (cores.containsKey(p.getTitle())) {
					//					if(p.getTitle().equals("Bug")){
					if(selected.contains(p.getTitle())){
						cor = cores.get(p.getTitle());
					} else {
						cor = cores.get("Default");
					}

					if (selected.contains(p.getTitle())) {
						durationSelected = durationSelected + p.getDurationInSeconds();
					}

					float largura = durationToPixels(p.getDurationInString());

					noStroke();
					fill(cor);
					rect(x + leftMargin, y, largura, 20);
					if (!s.getParticipant().equals("P01_Y")) {
						//System.out.println(entrevistado + " " + currentEntrevistado + " " + fase + " " + largura + " " + x + " " + y);
						stroke(127);
						strokeWeight(1f);
						line(0, y, width, y);
					} else {
						stroke(0);
						strokeWeight(2f);
						line(0, y, width, y);
					}
					x = x + largura;
					//					if (p.getTitle().equals(selected)) {

					//				if (p.getTitle().equals("Testing") && s.getOption().equals("X")) {
					//					String substring = s.getParticipant().substring(3, s.getParticipant().length());
					//					String inputVideoFile = substring + ".mov";
					//					String outputVideoFile = s.getParticipant() + "_"+p.getTitle()+"_" + counter++ + ".mov";
					//					String output = String.format("ffmpeg -i %s -c copy -ss %s -to %s %s &&", inputVideoFile, p.getStartTime(),p.getEndTime(), outputVideoFile);
					//					//System.out.println(output);
					//				}
				}
				//				stroke(127);
				//				strokeWeight(0.5f);
				//				line(0, y+sectionHeight, width, y+sectionHeight);
				textAlign(LEFT, TOP);
				textSize(14);
				fill(0);
				//				text(DurationFormat.secondsToString(s.calculateTotalDurationInSeconds()), 810 + leftMargin + 5, y);
				text(DurationFormat.secondsToString(durationSelected), 810 + leftMargin + 5, y);
				//text(DurationFormat.secondsToString(maxTesting), 810 + leftMargin + 50, y);
				//System.out.println(s.getParticipant() + ",00:" + DurationFormat.secondsToString(maxTesting));
				if(s.getGroup().equals("G14")){	
					gephiIndex = s.printTransactionsReport3(gephiIndex);
				}
			}
		}
		y = (sectionHeight * (indexY)) + upperMargin;
		stroke(127);
		strokeWeight(1f);
		line(0, y, width, y);


		text("TOTAL", 810 + leftMargin + 5, 2);
		//text("MAX_TEST", 810 + leftMargin + 50, 2);
		float myX = 0;
		int indexX = 0;
		for (int i = 0; i < 27; i++) {
			myX = i*30 + leftMargin;
			line(myX, 0, myX, height);
			textAlign(LEFT, TOP);
			textSize(8);
			//noStroke();
			fill(0);
			text(DurationFormat.secondsToString(i*60), myX+3, 5);
			indexX = i;
		}
		myX = (indexX+1)*30 + leftMargin;
		line(myX, 0, myX, height);
		System.out.println("OK: " + selected);
		//exit();
	}

	public float durationToPixels(String duration){
		return (DurationFormat.stringToSeconds(duration)/2.0f);
	}
	//	public void settings() {  size(917, 582); }
	//	public void settings() {  size(917, 222); }
	public void settings() {  size(917, 782); }
	//	public void settings() {  size(917, 582, PDF, "export/G14_"+selected+".pdf"); }
	//	public void settings() {  size(917, 222, PDF, "export/G05_"+selected+".pdf"); }
	//	public void settings() {  size(917, 782, PDF, "export/All_"+selected+".pdf"); }
	static public void main(String[] passedArgs) {
		String[] appletArgs = new String[] { "main.Segments2" };
		if (passedArgs != null) {
			PApplet.main(concat(appletArgs, passedArgs));
		} else {
			PApplet.main(appletArgs);
		}
	}
}
