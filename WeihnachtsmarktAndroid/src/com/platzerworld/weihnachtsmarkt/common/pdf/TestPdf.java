package com.platzerworld.weihnachtsmarkt.common.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestPdf {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File dir = new File("kegeln");
		try {
			String pdfcontent = generateHelloWorldPDF();
			dir.createNewFile();
			FileOutputStream pdfFile = new FileOutputStream(dir);
        	pdfFile.write(pdfcontent.getBytes("ISO-8859-1"));
            pdfFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private static String generateHelloWorldPDF() {
		PDFWriter mPDFWriter = new PDFWriter(PaperSize.FOLIO_WIDTH, PaperSize.FOLIO_HEIGHT);
        
        mPDFWriter.newPage();
        mPDFWriter.addRawContent("[] 0 d\n");
        mPDFWriter.addRawContent("1 w\n");
        mPDFWriter.addRawContent("0 0 1 RG\n");
        mPDFWriter.addRawContent("0 1 0 rg\n");
        mPDFWriter.addRectangle(40, 50, 280, 50);
        mPDFWriter.addText(85, 75, 18, "Code Research Laboratories");
        
        mPDFWriter.addRawContent("0 0 0 rg\n");
        mPDFWriter.addText(30, 90, 10, "� CRL", StandardFonts.DEGREES_270_ROTATION);
        
        
        String s = mPDFWriter.asString();
        return s;
	}

}
