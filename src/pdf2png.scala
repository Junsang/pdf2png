import java.awt.Toolkit
import java.awt.image.BufferedImage

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.util.PDFImageWriter

object pdf2png {

  def getDocument(path: String) {
    var document = PDDocument.load(path)

    var writer = new PDFImageWriter()
    var endPage = document.getNumberOfPages()
    var success = writer.writeImage(document, "png", "", 1, endPage,
      "/Users/JunsangPark/Downloads/myFileName", BufferedImage.TYPE_INT_RGB,
      Toolkit.getDefaultToolkit().getScreenResolution())
    System.out.println(success)

    document
  }

  def main(args: Array[String]) {
    var path = "/Users/JunsangPark/Downloads/test.pdf"
    	
    getDocument(path)

    //       System.out.println(e.getMessage());
  }

}