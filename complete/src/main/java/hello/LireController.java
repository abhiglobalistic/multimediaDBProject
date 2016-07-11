package hello;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import net.semanticmetadata.lire.builders.DocumentBuilder;
import net.semanticmetadata.lire.builders.GlobalDocumentBuilder;
import net.semanticmetadata.lire.imageanalysis.features.global.AutoColorCorrelogram;
import net.semanticmetadata.lire.imageanalysis.features.global.CEDD;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import net.semanticmetadata.lire.indexers.parallel.ParallelIndexer;
import net.semanticmetadata.lire.searchers.SimpleResult;
import net.semanticmetadata.lire.searchers.BitSamplingImageSearcher;
import net.semanticmetadata.lire.imageanalysis.features.global.EdgeHistogram;
import net.semanticmetadata.lire.imageanalysis.features.global.FCTH;
import net.semanticmetadata.lire.searchers.ImageSearchHits;
import net.semanticmetadata.lire.searchers.ImageSearcher;
import static net.semanticmetadata.lire.utils.LuceneUtils.AnalyzerType.WhitespaceAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.FSDirectory;
import org.w3c.dom.Document;
@RestController
public class LireController {

    private ParallelIndexer indexer;
    
    
    @RequestMapping("/test")
     public String test(@RequestParam(value="name", defaultValue="World") String name) {
        System.out.println(" test is Ok");
        return "TesOk";
    }     
    
    
    @RequestMapping("/createindex")
    public int createIndex(@RequestParam(value="name", defaultValue="World") String name) {
        int threadsNumber = 1;
        String indexPath = "C:\\Users\\Okba\\Documents\\Indexes";
        String imageDirectory = "C:\\Users\\Okba\\Downloads\\test1\\image.orig";
        indexer = new ParallelIndexer(threadsNumber, indexPath, imageDirectory);
        indexer.run();
        
        return 1;
    }
    
    @RequestMapping("/similarimage")
    public int getSimilarImage(@RequestParam(value="name", defaultValue="World") String name){
        File f = new File("C:\\Users\\Okba\\Downloads\\test1\\image.orig\\0.jpg");
        SimpleResult result = new SimpleResult(0, 1);
        EdgeHistogram edgHist = new EdgeHistogram();
        BitSamplingImageSearcher searcher = new BitSamplingImageSearcher(12,edgHist);
        
        //SimpleResultForEvaluation si = new SimpleResultForEvaluation(distance, indexNumber, name)
        
        return 1;
    }
    
    static GlobalDocumentBuilder globalDocumentBuilder;
    static IndexWriter iw;
    static IndexReader ir;
    static Document document;
    static ImageSearcher searcher;
    public static int createIndex() throws IOException {
        // Filters used ( in order to extract predefined lire features )
        globalDocumentBuilder = new GlobalDocumentBuilder(CEDD.class);
        globalDocumentBuilder.addExtractor(FCTH.class);
        globalDocumentBuilder.addExtractor(AutoColorCorrelogram.class);
        //Analyse the image before being indexed ( taking the most relevant elements of the image to be indexed )
        // Here we are just going to create the configuration of the index writer, which needs an analyzer to be created
        //IndexWriterConfig conf = new IndexWriterConfig(new WhitespaceAnalyzer());
        //Constructs a new IndexWriter per the settings given in conf.
        //FSDirectory.open(Paths.get("index")) is the directory were indexes will be stored
        IndexWriter iw = new IndexWriter(FSDirectory.open(Paths.get("index")), conf);
        
       return 1;
    }

    public static int dropIndex() throws IOException{
        //List composed of the files in the directory 
        String[] list = FSDirectory.open(Paths.get("index")).listAll();
        //deleting all files of indexes from the directory
        for (String file : list) {
            FSDirectory.open(Paths.get("index")).deleteFile(file);
        }

        return 1;
    }
    
    /*public static int insert(){
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
    public static int update(String rid, String oldval, String newval) throws IOException{
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

    public static int delete(String rid, String oldval) throws IOException{
        
        IndexableField field = document.getField(oldval);
        Term term = new Term(field.toString());
        //Removes field with the specified name from the document.
        iw.deleteDocuments(term);
        return 1;
    }

    public static int startSearching() throws IOException{
        //Read indexes 
        IndexReader ir = DirectoryReader.open(FSDirectory.open(Paths.get("index")));
      

        return 1;
    }
    public static int featch(String IN, int OUT ,int nrows, String[] rids, ODCIEnv env) throws IOException{
         // Compare the index of the image to the indexes the reader have
        ImageSearchHits hits = Similarity.similarity(IN, ir);
        //Writing the list of paths of the most similar images 
        for (int i = 0; i < hits.length(); i++) {
            String fileName = ir.document(hits.documentID(i)).getValues(DocumentBuilder.FIELD_NAME_IDENTIFIER)[0];
            System.out.println(hits.score(i) + ": \t" + fileName);
        }
    

        return 1;
    }
    
    public static  int close(int  IN) throws IOException{
        ir.close();
        return 1;
    }*/
    
    
    
   
    
    
    
}
