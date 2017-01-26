package PDFExperiments;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class PDFExperiment {

	public static void main(String[] args) {
//		String folderName = "/Users/fcac/Desktop/PDF_TO_IMAGES/Teste2/_Prototype_Ideation_/";
		String folderName = "/Users/fcac/Downloads/TEMP/Ebooks_TEMP/Academic_Writing/PDF/";
		String outputPath = folderName+"imgOutput";
		new File(outputPath).mkdir();
		File folder = new File(folderName);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
				renderImageFromPDFFile(i+11, listOfFiles[i], outputPath);
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}
	}

	public static void renderImageFromPDFFile(int pdfIndex, File pdfDocument, String outputPath){
		try {
//			File file = new File(folderName + fileNameInput);
			System.out.println("Document: " + pdfDocument.getName());
			System.out.print("[");
			PDDocument document = PDDocument.load(pdfDocument);
			PDFRenderer renderer = new PDFRenderer(document);
			int dpi = 100;
			ImageType imageType = ImageType.RGB;
			String fileNameInput = pdfDocument.getName();
			String outputPrefix = fileNameInput.substring( 0, fileNameInput.lastIndexOf( '.' ));
			String imageFormat = "jpg";
			boolean success = true;
			int lastPercentage = 99;
			for (int i = 0; i < document.getNumberOfPages(); i++)
			{
				int percentage = Math.round((i*1.0f)/(document.getNumberOfPages()*1.0f)*100);
				if(percentage-lastPercentage>=1){
					//System.out.print(String.format("%03d", percentage));
					System.out.print(".");
				}
				lastPercentage = percentage;
				BufferedImage image = renderer.renderImageWithDPI(i, dpi, imageType);
				//String fileName = outputPrefix + (i + 1) + "." + imageFormat;
				String fileName = String.format("%s/%03d_%s_%03d.%s", outputPath, pdfIndex, outputPrefix, (i+1), imageFormat);
				success &= ImageIOUtil.writeImage(image, fileName, dpi);
			}
			System.out.print("] = ");
			System.out.println(success);
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
