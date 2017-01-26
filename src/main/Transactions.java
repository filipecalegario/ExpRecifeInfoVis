package main;

import java.io.PrintWriter;

import processing.core.PApplet;
import processing.data.Table;

public class Transactions extends PApplet{
	
	public void setup(){
		PrintWriter output = createWriter("totalTransactions.csv");
		
		Table table = loadTable("TransactionsCSV.csv", "header");
		output.println(String.format("%s,%s,%s,%s", "Session", "Source", "Target","Weight"));
		for (int row = 0; row < table.getRowCount(); row++) {
			String session = table.getString(row, "Session");
			int grandTotal = table.getInt(row, "GrandTotal");
			int tMapMount = table.getInt(row, "TMapMount");
			int tMapTest = table.getInt(row, "TMapTest");
			int tMountTest = table.getInt(row, "TMountTest");
			output.println(String.format("%s,%s,%s,%.2f", session, "Mapping", "Mounting",50.f*(tMapMount/(grandTotal*1.0f))));
			output.println(String.format("%s,%s,%s,%.2f", session, "Mapping", "Testing",50.f*(tMapTest/(grandTotal*1.0f))));
			output.println(String.format("%s,%s,%s,%.2f", session, "Testing", "Mounting",50.f*(tMountTest/(grandTotal*1.0f))));
		}
		output.flush(); // Writes the remaining data to the file
		output.close(); // Finishes the file
		exit();
	}
	
	static public void main(String[] passedArgs) {
		String[] appletArgs = new String[] { "main.Transactions" };
		if (passedArgs != null) {
			PApplet.main(concat(appletArgs, passedArgs));
		} else {
			PApplet.main(appletArgs);
		}
	}
	
	

}
