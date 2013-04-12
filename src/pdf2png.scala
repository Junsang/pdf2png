import java.awt.Toolkit
import java.awt.image.BufferedImage

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.util.PDFImageWriter

object pdf2png {

		def getDocument(path: String) {
				var document = PDDocument.load(path)
				
				var writer = new PDFImageWriter()
				var success = writer.writeImage(document, "jpg", "", 1, 1,
                           "/Users/JunsangPark/Downloads/myFileName", BufferedImage.TYPE_INT_RGB, 
                           Toolkit.getDefaultToolkit().getScreenResolution())
                System.out.println(success)
                
                document
		}
		
		def main(args: Array[String]) {
				var path = "/Users/JunsangPark/Downloads/test.pdf"
	/*			  
//config option 1:convert all document to image
String [] args_1 =  new String[3];
args_1[0]  = "-outputPrefix";
args_1[1]  = "my_image_1";
args_1[2]  = pdfPath;
 
//config option 2:convert page 1 in pdf to image
String [] args_2 =  new String[7];
args_2[0] = "-startPage";
args_2[1] = "1"
args_2[2] = "-endPage";
args_2[3] = "1";
args_2[4] = "-outputPrefix"
args_2[5] = "my_image_2";
args_2[6] = pdfPath;
 
try {
// will output "my_image_1.jpg"
        PDFToImage.main(args_1); 
// will output "my_image_2.jpg" 
   PDFToImage.main(args_2); 
      } catch (Exception e) { logger.error(e.getMessage(),e); }
				  
		*/		  
				  
				  
				  getDocument(path)

              //       System.out.println(e.getMessage());
       }


       
}