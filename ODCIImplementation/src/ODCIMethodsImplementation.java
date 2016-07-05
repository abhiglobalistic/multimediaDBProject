/**
 * Created by heni on 7/2/16.
 */

import net.semanticmetadata.lire.builders.GlobalDocumentBuilder;
import net.semanticmetadata.lire.imageanalysis.features.global.AutoColorCorrelogram;
import net.semanticmetadata.lire.imageanalysis.features.global.CEDD;
import net.semanticmetadata.lire.imageanalysis.features.global.FCTH;
import oracle.CartridgeServices.*;
import oracle.ODCI.*;
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


public class ODCIMethodsImplementation {
    static GlobalDocumentBuilder globalDocumentBuilder;
    static IndexWriter iw;
    public static int ODCIIndexCreate (ODCIIndexInfo ia, String params, ODCIEnv env) throws IOException {
        globalDocumentBuilder = new GlobalDocumentBuilder(CEDD.class);
        globalDocumentBuilder.addExtractor(FCTH.class);
        globalDocumentBuilder.addExtractor(AutoColorCorrelogram.class);
        IndexWriterConfig conf = new IndexWriterConfig(new WhitespaceAnalyzer());
        IndexWriter iw = new IndexWriter(FSDirectory.open(Paths.get("index")), conf);

        return 1;
    }

    public static int ODCIIndexDrop (ODCIIndexInfo ia, ODCIEnv env){

        return 1;
    }
    public static int ODCIIndexInsert (ODCIIndexInfo ia, String rid, String newval, ODCIEnv env){
        try {
            BufferedImage img = ImageIO.read(new FileInputStream(newval));
            Document document = globalDocumentBuilder.createDocument(img, newval);
            iw.addDocument(document);
        }
        catch (Exception e) {
            System.err.println("Error reading image or indexing it.");
            e.printStackTrace();
        }
        return 1;
    }
    public static int ODCIIndexUpdate (ODCIIndexInfo ia, String rid, String oldval, String newval, ODCIEnv env){

        return 1;
    }

    public static int ODCIIndexDelete (ODCIIndexInfo ia, String rid, String oldval, ODCIEnv env){

        return 1;
    }

    public static int ODCIIndexStart (int sctx, ODCIIndexInfo ia, ODCIPredInfo op, ODCIQueryInfo qi, int strt,
                                     int stop, String cmpval, ODCIEnv env){

        return 1;
    }

    public static int ODCIIndexFetch (int nrows, ODCIRidList rids, ODCIEnv env){

        return 1;
    }

    public static int ODCIIndexClose (ODCIEnv env){

        return 1;
    }
}
