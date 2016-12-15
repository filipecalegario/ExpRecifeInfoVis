package model;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import processing.core.PApplet;

public class Session {

	private String participant;
	private String option;
	private Vector<Phase> phases;

	public Session(String participant, String option) {
		super();
		this.participant = participant;
		this.option = option;
		this.phases = new Vector<Phase>();
	}

	public void addPhase(Phase phase){
		this.phases.addElement(phase);
	}

	public Vector<Phase> getPhases() {
		return phases;
	}

	public String getParticipant() {
		return participant;
	}

	public String getOption() {
		return option;
	}

	public int calculateTotalDurationInSeconds(){
		int total = 0;
		for (Phase phase : phases) {
			total = total + phase.getDurationInSeconds();
		}
		return total;
	}
	
	public void printTransactionsReport(){
		String lastPhaseTitle = "Beginning";
		Map<String, Integer> transicoes = new LinkedHashMap<>();
		for (Phase phase : phases) {
			String transaction = lastPhaseTitle + "-" + phase.getTitle();
			if(transicoes.containsKey(transaction)){
				int currentQuantity = transicoes.get(transaction);
				transicoes.put(transaction, currentQuantity+1);
			} else {
				transicoes.put(transaction, 1);
			}
			lastPhaseTitle = phase.getTitle();
		}
		Set<String> keys = transicoes.keySet();
		//System.out.println("=====" + this.participant + "=====");
		for (String key : keys) {
			int quant = transicoes.get(key);
			System.out.println(this.participant + "," + key + "," + quant);
		}
	}
	
	public void printTransactionsReport2(){
		Map<String, Integer> transicoes = this.getTransactions();
		Set<String> keys = transicoes.keySet();
		//System.out.println("=====" + this.participant + "=====");
		int quantTotal = 0;
		for (String key : keys) {
			quantTotal = quantTotal + transicoes.get(key);
		}
		//System.out.println(this.participant + " " + quantTotal);
		PApplet core = new PApplet();
		//PrintWriter output = core.createWriter("export/transactions_"+ this.getParticipant() +".csv");
		for (String key : keys) {
			int quant = transicoes.get(key);
			String[] parts = key.split("-");
			float map = quant;//(quant/(quantTotal*1.0f)*50.f);
			String[] partsParticipant = this.participant.split("-");
			String out = String.format("%s,%s,%s,%.2f,%s,%s", this.participant, parts[0], parts[1],map, partsParticipant[2], partsParticipant[1]);
			System.out.println(out);
			//output.println(out);
			//System.out.println(this.participant + "," + parts[0] + "," + parts[1] + "," + map);
		}
		//output.flush(); // Writes the remaining data to the file
		//output.close(); // Finishes the file
	}
	
	public Map<String, Integer> getTransactions(){
		String lastPhaseTitle = "Beginning";
		Map<String, Integer> transicoes = new LinkedHashMap<>();
		for (Phase phase : phases) {
			String transaction = lastPhaseTitle + "-" + phase.getTitle();
			if(transicoes.containsKey(transaction)){
				int currentQuantity = transicoes.get(transaction);
				transicoes.put(transaction, currentQuantity+1);
			} else {
				transicoes.put(transaction, 1);
			}
			lastPhaseTitle = phase.getTitle();
		}
		return transicoes;
	}

}
