
import net.semanticmetadata.lire.builders.GlobalDocumentBuilder;
import net.semanticmetadata.lire.imageanalysis.features.global.AutoColorCorrelogram;
import net.semanticmetadata.lire.imageanalysis.features.global.CEDD;
import net.semanticmetadata.lire.imageanalysis.features.global.FCTH;
//import oracle.CartridgeServices.*;
//import oracle.ODCI.ODCIEnv;
//import oracle.ODCI.ODCIIndexInfo;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import javaapplication2.Similarity;
import net.semanticmetadata.lire.builders.DocumentBuilder;
import net.semanticmetadata.lire.searchers.GenericFastImageSearcher;
import net.semanticmetadata.lire.searchers.ImageSearchHits;
import net.semanticmetadata.lire.searchers.ImageSearcher;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Fields;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;


public class ODCIMethodsImplementation {
    static GlobalDocumentBuilder globalDocumentBuilder;
    static IndexWriter iw;
    static IndexReader ir;
    static Document document;
    static ImageSearcher searcher;
    public static int ODCIIndexCreate(ODCIIndexInfo ia, String params, ODCIEnv env) throws IOException {
        // Filters used ( in order to extract predefined lire features )
        globalDocumentBuilder = new GlobalDocumentBuilder(CEDD.class);
        globalDocumentBuilder.addExtractor(FCTH.class);
        globalDocumentBuilder.addExtractor(AutoColorCorrelogram.class);
        //Analyse the image before being indexed ( taking the most relevant elements of the image to be indexed )
        // Here we are just going to create the configuration of the index writer, which needs an analyzer to be created
        IndexWriterConfig conf = new IndexWriterConfig(new WhitespaceAnalyzer());
        //Constructs a new IndexWriter per the settings given in conf.
        //FSDirectory.open(Paths.get("index")) is the directory were indexes will be stored
        IndexWriter iw = new IndexWriter(FSDirectory.open(Paths.get("index")), conf);
        
                return 1;
    }

    public static int ODCIIndexDrop(ODCIIndexInfo ia, ODCIEnv env) throws IOException{
        //List composed of the files in the directory 
        String[] list = FSDirectory.open(Paths.get("index")).listAll();
        //deleting all files of indexes from the directory
        for (String file : list) {
            FSDirectory.open(Paths.get("index")).deleteFile(file);
        }

        return 1;
    }
    public static int ODCIIndexInsert(ODCIIndexInfo ia, String rid, String newval, ODCIEnv env){
        try {
            //Reading an image
            BufferedImage img = ImageIO.read(new FileInputStream(newval));
            // Create a document based on the feature extractor we create
            document = globalDocumentBuilder.createDocument(img, newval);
            // Adds the document to the index.
            iw.addDocument(document);
            return 1;
        }
        catch (Exception e) {
            System.err.println("Error reading image or indexing it.");
            e.printStackTrace();
        }
        return 1;
    }
    public static int ODCIIndexUpdate(ODCIIndexInfo ia, String rid, String oldval, String newval, ODCIEnv env) throws IOException{
        // Field is a pair (key, value) => in our case ( path , image)
        //Here we are going to extract the field related to the path of the old image
        IndexableField field = document.getField(oldval);
        //Constructs a Term with the given field
        // Term is used basically to search 
        Term term = new Term(field.toString());
        // Reading the new image and create the new document based on its features
        BufferedImage img = ImageIO.read(new FileInputStream(newval));
        Document new_document = globalDocumentBuilder.createDocument(img, newval);
        //Updates a document by first deleting the document containing term and then adding the new document to the index
        iw.updateDocument(term, new_document);
        return 1;
    }

    public static int ODCIIndexDelete(ODCIIndexInfo ia, String rid, String oldval, ODCIEnv env) throws IOException{
        
        IndexableField field = document.getField(oldval);
        Term term = new Term(field.toString());
        //Removes field with the specified name from the document.
        iw.deleteDocuments(term);
        return 1;
    }

    public static int ODCIIndexStart(int IN, ODCIIndexInfo ia,  ODCIPredInfo pi,  ODCIQueryInfo qi,  int strt, int stop, <valargs>,  ODCIEnv env) throws IOException{
        //Read indexes 
        IndexReader ir = DirectoryReader.open(FSDirectory.open(Paths.get("index")));
      

        return 1;
    }
    public static int ODCIIndexFetch(String IN, int OUT ,int nrows, String[] rids, ODCIEnv env) throws IOException{
         // Compare the index of the image to the indexes the reader have
        ImageSearchHits hits = Similarity.similarity(IN, ir);
        //Writing the list of paths of the most similar images 
        for (int i = 0; i < hits.length(); i++) {
            String fileName = ir.document(hits.documentID(i)).getValues(DocumentBuilder.FIELD_NAME_IDENTIFIER)[0];
            System.out.println(hits.score(i) + ": \t" + fileName);
        }
    

        return 1;
    }
    
    public static  int ODCIIndexClose(int  IN, ODCIEnv env) throws IOException{
        ir.close();
        return 1;
    }   
}