package model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import processing.core.PApplet;

public class Session {

	private String participant;
	private String option;
	private String group;
	private Vector<Phase> phases;

	public Session(String participant, String option, String group) {
		super();
		this.participant = participant;
		this.option = option;
		this.group = group;
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
	
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
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
		Map<String, Integer> numTransactionsByName = this.getTransactions();
		Set<String> transactionNames = numTransactionsByName.keySet();
		//System.out.println("=====" + this.participant + "=====");
		int quantTotal = 0;
		for (String transactionName : transactionNames) {
			quantTotal = quantTotal + numTransactionsByName.get(transactionName);
		}
		//System.out.println(this.participant + " " + quantTotal);
		PApplet core = new PApplet();
		//PrintWriter output = core.createWriter("export/transactions_"+ this.getParticipant() +".csv");
		for (String transactionName : transactionNames) {
			int quant = numTransactionsByName.get(transactionName);
			String[] parts = transactionName.split("-");
			String[] partsParticipant = this.participant.split("_");
			String out = String.format("%s,%s,%s,%.2f,%s,%s", this.participant, parts[0], parts[1], quant*1.f, partsParticipant[0], this.option);
			//String out = String.format("%s,%s,%s,%s,%s", this.participant, transactionName, quant, this.option);
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
			if(!phase.getTitle().equals("Distinct Item")){
				String transaction = lastPhaseTitle + "-" + phase.getTitle();
				if(transicoes.containsKey(transaction)){
					int currentQuantity = transicoes.get(transaction);
					transicoes.put(transaction, currentQuantity+1);
				} else {
					transicoes.put(transaction, 1);
				}
				lastPhaseTitle = phase.getTitle();
			}
		}
		return transicoes;
	}

	public int printTransactionsReport3(int gephiIndex) {
		int index = gephiIndex;
		Map<String, Integer> numTransactionsByName = this.getTransactions();
		Set<String> transactionNames = numTransactionsByName.keySet();
		//System.out.println("=====" + this.participant + "=====");
		int quantTotal = 0;
		for (String transactionName : transactionNames) {
			quantTotal = quantTotal + numTransactionsByName.get(transactionName);
		}
		//System.out.println(this.participant + " " + quantTotal);
		PApplet core = new PApplet();
		//PrintWriter output = core.createWriter("export/transactions_"+ this.getParticipant() +".csv");
		for (String transactionName : transactionNames) {
			int quant = numTransactionsByName.get(transactionName);
			String[] parts = transactionName.split("-");
			String[] partsParticipant = this.participant.split("_");
			String out = String.format("%s,%s,%s,%.2f,%s,%s,%s", this.participant, parts[0], parts[1], quant*1.f, partsParticipant[0], this.option, "Directed");
			//String out = String.format("%s,%s,%s,%s,%s", this.participant, transactionName, quant, this.option);
			System.out.println(out);
			//output.println(out);
			//System.out.println(this.participant + "," + parts[0] + "," + parts[1] + "," + map);
		}
		//output.flush(); // Writes the remaining data to the file
		//output.close(); // Finishes the file
		return index;
	}

}
