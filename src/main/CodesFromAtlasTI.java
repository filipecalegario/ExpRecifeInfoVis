package main;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import model.Session;
import processing.core.PApplet;
import processing.data.Table;

public class CodesFromAtlasTI extends PApplet {
	
	Map<String, Vector<String>> codeGroups = new LinkedHashMap<>();

	public void setup() {
		String lines[] = loadStrings("codes/codeGroups.txt");
		//println("there are " + lines.length + " lines");
		for (int i = 0 ; i < lines.length; i++) {
			String titleAndContent[] = lines[i].split("=");
			String groupName = titleAndContent[0];
			String[] codes = titleAndContent[1].split("#");
			codeGroups.put(groupName, new Vector<String>(Arrays.asList(codes)));
		}
		
		Set<String> groups = codeGroups.keySet();
		//		/String out = String.format("%s,%s,%s,%s,%s,%s", "Session", "Source", "Target","Weight", "Option", "Participant");
		//System.out.println(out);
		for (String group : groups) {
			//System.out.print(group + "= [");
			Vector<String> vector = codeGroups.get(group);
			for (String string : vector) {
				//System.out.print(string + ", ");
			}
			//System.out.println("]");
		}
				
		Table table = loadTable("codes/quotations.csv", "header, tsv");
		//System.out.println("number of quotations: " + table.getRowCount());
		for (int row = 0; row < table.getRowCount(); row++) {
			String participant = table.getString(row, "ParticipantId");
			String id = table.getString(row, "Number");
			String quotation = table.getString(row, "Text Content");
			String document = table.getString(row, "Document");
			String codesList = table.getString(row, "Codes");
			String codes[] = codesList.split(",");
//			String element = "";
//			String positive = "";
//			String negative = "";
//			String sensation = "";
//			String metaAtt = "";
			Vector<String> element = new Vector<String>();
			Vector<String> positive = new Vector<String>();
			Vector<String> negative = new Vector<String>();
			Vector<String> sensation = new Vector<String>();
			Vector<String> metaAtt = new Vector<String>();
			Vector<String> positives = codeGroups.get("_Positive Points_");
			Vector<String> negatives = codeGroups.get("_Negative Points_");
			Vector<String> elements = codeGroups.get("ELEMENTOS");
			Vector<String> sensations = codeGroups.get("Sensations");
			Vector<String> metaAtts = codeGroups.get("Meta-attributes");
			int numCodes = table.getInt(row, "Number of Codes");
			for (int i = 0; i < codes.length; i++) {
				codes[i] = codes[i].trim();
			}
			Vector<String> codes_v = new Vector<String>(Arrays.asList(codes));
			for (int i = 0; i < codes.length; i++) {
				if(positives.contains(codes[i])){
					positive.addElement(codes[i]);
				}
				if(negatives.contains(codes[i])){
					negative.addElement(codes[i]);
				}
				if(elements.contains(codes[i])){
					element.addElement(codes[i]);
				}
				if(sensations.contains(codes[i])){
					sensation.addElement(codes[i]);
				} 
				if(metaAtts.contains(codes[i])){
					metaAtt.addElement(codes[i]);
				}
			}
			if (element.size() > 1 || positive.size() > 1 || negative.size() > 1 || sensation.size() > 1) {
				print(element.size() + "\t");
				print( positive.size() + "\t");
				print( negative.size() + "\t");
				print( sensation.size() + "\t");
				print( metaAtt.size() + "\t");
//				println(" => " + codesList + " => " + quotation);
//				println("\t" + codesList + "\t" + quotation);
				String formattedQuotation = String.format("\"%s\" (%s)", quotation, participant);
				String out  = String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s", printVector(element), printVector(positive), printVector(negative), printVector(sensation), printVector(metaAtt), id, formattedQuotation);
				System.out.println(out);
			}
		}
		exit();
	}
	
//			if(sessionsByParticipants.containsKey(participant)){
//				s = sessionsByParticipants.get(participant);
//			} else {
//				s = new Session(participant, option, group);
//				sessionsByParticipants.put(participant, s);
//			}
//			if(s != null){
//				s.addPhase(new Phase(fase, duracao,startTime,endTime));
//			}
	
	public void draw() {
 
	
	}
	
	public String printVector(Vector<String> vec){
		String result = "";
		for (String string : vec) {
			result = result + string + ", ";
		}
		if(result.length() > 0 && result.charAt(result.length()-1) == ' '){
			result = result.substring(0, result.length());
		}
		return result;
	}
	
	public void settings() {  size(917, 782); }
	
	static public void main(String[] passedArgs) {
		String[] appletArgs = new String[] { "main.CodesFromAtlasTI" };
		if (passedArgs != null) {
			PApplet.main(concat(appletArgs, passedArgs));
		} else {
			PApplet.main(appletArgs);
		}
	}
	
}
