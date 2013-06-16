import java.awt.Toolkit
import java.awt.image.BufferedImage
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.util.PDFImageWriter
import java.io.BufferedWriter
import java.io.FileWriter
import org.apache.pdfbox.util.PDFTextStripper

object pdf2png {

	def getDocument(path: String) {
		var document = PDDocument.load(path)

		var imageFormat = "png"
		var passWord = ""
		var startPage = 1;
		var endPage = document.getNumberOfPages()
		var savePath = "/Users/JunsangPark/Downloads/"
		var outputFile = "myFileName_"

		var writer = new PDFImageWriter()

		var success = writer.writeImage(document, imageFormat, passWord, startPage, endPage,
				savePath+outputFile, BufferedImage.TYPE_INT_RGB,
				Toolkit.getDefaultToolkit().getScreenResolution())
				System.out.println(success)

				document
	}

	def getMetaData(path: String) {
		var document = PDDocument.load(path);
		var info = document.getDocumentInformation();

		var metaData = new BufferedWriter(new FileWriter("/Users/JunsangPark/Downloads/metaData.txt"));
		var metadataText = 
			        	"Page Count=" + document.getNumberOfPages() + "\n"+
						"Title=" + info.getTitle() +"\n"+
						"Author=" + info.getAuthor() +"\n"+
						"Subject=" + info.getSubject() +"\n"+
						"Keywords=" + info.getKeywords() +"\n"+
						"Creator=" + info.getCreator() +"\n"+
						"Producer=" + info.getProducer() +"\n"+
						"Creation Date=" + info.getCreationDate() +"\n"+
						"Modification Date=" + info.getModificationDate()+"\n"+
						"Trapped=" + info.getTrapped() ; 
		metaData.write(metadataText);
		metaData.close();

		System.out.println( "Page Count=" + document.getNumberOfPages() );
		System.out.println( "Title=" + info.getTitle() );
		System.out.println( "Author=" + info.getAuthor() );
		System.out.println( "Subject=" + info.getSubject() );
		System.out.println( "Keywords=" + info.getKeywords() );
		System.out.println( "Creator=" + info.getCreator() );
		System.out.println( "Producer=" + info.getProducer() );
		System.out.println( "Creation Date=" + info.getCreationDate() );
		System.out.println( "Modification Date=" + info.getModificationDate());
		System.out.println( "Trapped=" + info.getTrapped() ); 
	}

	
	def getText(path: String) {
		var document = PDDocument.load(path);	      

		for(i<-1 to document.getPageCount()){
			var stripper = new PDFTextStripper();
			stripper.setStartPage( i );
			stripper.setEndPage( i );
			System.out.println(stripper.getText(document));

			var textData = new BufferedWriter(new FileWriter("/Users/JunsangPark/Downloads/textPage"+i+".txt"));
			var text = stripper.getText(document);
			textData.write(text);
			textData.close();

			System.out.println("=================================================");
		}
	}


	def main(args: Array[String]) {

		var loadPath = "/Users/JunsangPark/Downloads/"
		var inputFile = args.apply(0)

		var path = loadPath + inputFile

		getDocument(path)
		getMetaData(path)
		getText(path)
	}
}