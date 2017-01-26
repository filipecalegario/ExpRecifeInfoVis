package main;

import processing.core.PApplet;
import processing.data.Table;

public class QuestOptionsViz extends PApplet {

	@Override
	public void setup() {
		Table table = loadTable("QuestOpcoes_Reduzido.csv", "header");
		for (int row = 1; row < table.getRowCount(); row++) {
			String option = table.getString(row, "Opcao");
			int answer = table.getInt(row, "Pergunta1");
		}
		exit();
	}

	@Override
	public void draw() {

	}
	
	public void settings() {  size(800, 600); }
	//public void settings() {  size(1200, 790, PDF, "export/segments_bugs.pdf"); }
	static public void main(String[] passedArgs) {
		String[] appletArgs = new String[] { "main.QuestOptionsViz" };
		if (passedArgs != null) {
			PApplet.main(concat(appletArgs, passedArgs));
		} else {
			PApplet.main(appletArgs);
		}
	}
	
	

}
