import java.awt.Toolkit
import java.awt.image.BufferedImage

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.util.PDFImageWriter

object pdf2png {

  def getDocument(path: String) {
    var document = PDDocument.load(path)

    var writer = new PDFImageWriter()
    
    var imageFormat = "png"
    var passWord = ""
    var startPage = 1;
    var endPage = document.getNumberOfPages()
    var savePath = "/Users/JunsangPark/Downloads/"
    var outputFile = "myFileName_"
      
    var success = writer.writeImage(document, imageFormat, passWord, startPage, endPage,
      savePath+outputFile, BufferedImage.TYPE_INT_RGB,
      Toolkit.getDefaultToolkit().getScreenResolution())
    System.out.println(success)

    document
  }

  def main(args: Array[String]) {
    
    var loadPath = "/Users/JunsangPark/Downloads/"
    var inputFile = "test.pdf"
    
    var path = loadPath + inputFile
    	
    getDocument(path)

    //       System.out.println(e.getMessage());
  }

}