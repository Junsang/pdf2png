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
//		var savePath = "/Users/JunsangPark/Downloads/"
//		var outputFile = "myFileName_"
		var savePath = path+"_"

		
		var writer = new PDFImageWriter()

		var success = writer.writeImage(document, imageFormat, passWord, startPage, endPage,
				savePath, BufferedImage.TYPE_INT_RGB,
				Toolkit.getDefaultToolkit().getScreenResolution())
				System.out.println(success)

				document
	}

	def getMetaData(path: String) {
		var document = PDDocument.load(path);
		var info = document.getDocumentInformation();
		
		var metaData = new BufferedWriter(new FileWriter(path+"_metaData.txt"));
		var metadataText = 
			        	"Page Count=" + document.getNumberOfPages() + "\n"+
						"Title=" + info.getTitle() +"\n"+
						"Author=" + info.getAuthor() +"\n"+
						"Subject=" + info.getSubject() +"\n"+
						"Keywords=" + info.getKeywords() +"\n"+
						"Creator=" + info.getCreator() +"\n"+
						"Producer=" + info.getProducer() +"\n"+
						"Creation Date=" + info.getCreationDate().getTime() +"\n"+
						"Modification Date=" + info.getModificationDate().getTime()+"\n"+
						"Trapped=" + info.getTrapped() ; 
		metaData.write(metadataText);
		metaData.close();
	
		
		var metaData_PageCount = new BufferedWriter(new FileWriter(path+"_metaData_PageCount.txt"));
		var pageCountText = ""+document.getNumberOfPages()
		metaData_PageCount.write(pageCountText);
		metaData_PageCount.close();

		var metaData_Title = new BufferedWriter(new FileWriter(path+"_metaData_Title.txt"));
		var titleText = ""+info.getTitle()
		metaData_Title.write(titleText);
		metaData_Title.close();
		
		var metaData_Author = new BufferedWriter(new FileWriter(path+"_metaData_Author.txt"));
		var authorText = ""+info.getAuthor()
		metaData_Author.write(authorText);
		metaData_Author.close();
		
		var metaData_Creator = new BufferedWriter(new FileWriter(path+"_metaData_Creator.txt"));
		var creatorText = ""+info.getCreator()
		metaData_Creator.write(creatorText);
		metaData_Creator.close();
		
		//creation Date
		var metaData_cDate = new BufferedWriter(new FileWriter(path+"_metaData_cDate.txt"));
		var cDateText = ""+info.getCreationDate().getTime()
		metaData_cDate.write(cDateText);
		metaData_cDate.close();
		
		//modification Date
		var metaData_mDate = new BufferedWriter(new FileWriter(path+"_metaData_mDate.txt"));
		var mDateText = ""+info.getModificationDate().getTime()
		metaData_mDate.write(mDateText);
		metaData_mDate.close();
		
		
		System.out.println( "Page Count=" + document.getNumberOfPages() );
		System.out.println( "Title=" + info.getTitle() );
		System.out.println( "Author=" + info.getAuthor() );
		System.out.println( "Subject=" + info.getSubject() );
		System.out.println( "Keywords=" + info.getKeywords() );
		System.out.println( "Creator=" + info.getCreator() );
		System.out.println( "Producer=" + info.getProducer() );
		System.out.println( "Creation Date=" + info.getCreationDate().getTime() );
		System.out.println( "Modification Date=" + info.getModificationDate().getTime());
		System.out.println( "Trapped=" + info.getTrapped() ); 
		
		
		document.close()
	}

	
	def getText(path: String) {
		var document = PDDocument.load(path);	      

		for(i<-1 to document.getPageCount()){
			var stripper = new PDFTextStripper();
			stripper.setStartPage( i );
			stripper.setEndPage( i );
			System.out.println(stripper.getText(document));

			var textData = new BufferedWriter(new FileWriter(path+"_textPage"+i+".txt"));
			var text = stripper.getText(document);
			textData.write(text);
			textData.close();
			

			System.out.println("=================================================");
		}
		
			document.close()
	}


	def main(args: Array[String]) {

		var inputFile = args.apply(0)
		var loadPath = args.apply(1)//"/Users/JunsangPark/Downloads/"

		var path = loadPath + inputFile

		getDocument(path)
		getMetaData(path)
		getText(path)
	}
}