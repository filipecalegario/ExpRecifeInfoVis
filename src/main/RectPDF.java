package main;

import java.io.IOException;
import java.io.PrintWriter;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.FilteredTextRenderListener;
import com.itextpdf.text.pdf.parser.LocationTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.RegionTextRenderFilter;
import com.itextpdf.text.pdf.parser.RenderFilter;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import processing.core.PApplet;
import processing.data.Table;

public class RectPDF extends PApplet{

	public void setup(){

		Table table = loadTable("pdfAnnotation.csv", "header");

		for (int row = 0; row < table.getRowCount(); row++) {
			try {
				float x1 = table.getFloat(row, "x1");
				float y1 = table.getFloat(row, "y1");
				float x2 = table.getFloat(row, "x2");
				float y2 = table.getFloat(row, "y2");
				int page = table.getInt(row, "page");
				String file = table.getString(row, "file");
				PdfReader reader = new PdfReader(file);
				String output = getTextFromRectangle(x1, y1, x2, y2, reader, page);
				System.out.println(output);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static public void main(String[] passedArgs) {
		String[] appletArgs = new String[] { "main.RectPDF" };
		if (passedArgs != null) {
			PApplet.main(concat(appletArgs, passedArgs));
		} else {
			PApplet.main(appletArgs);
		}
	}

	public static String getTextFromRectangle(float x1, float y1, float x2, float y2, PdfReader reader, int pageNumber) throws IOException {

		// Get the retangle coodinates
		float llx = x1;
		float lly = y1;
		float urx = x2;
		float ury = y2;

		Rectangle rect = new Rectangle(llx, lly, urx, ury);
		RenderFilter filter = new RegionTextRenderFilter(rect);
		TextExtractionStrategy strategy = 
				new FilteredTextRenderListener(
						new LocationTextExtractionStrategy(), filter);

		return PdfTextExtractor.getTextFromPage(reader, pageNumber, strategy);
	}

}
