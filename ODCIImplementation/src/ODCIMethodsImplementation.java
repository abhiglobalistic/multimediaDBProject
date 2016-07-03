/**
 * Created by heni on 7/2/16.
 */

import net.semanticmetadata.lire.builders.GlobalDocumentBuilder;
import net.semanticmetadata.lire.imageanalysis.features.global.AutoColorCorrelogram;
import net.semanticmetadata.lire.imageanalysis.features.global.CEDD;
import net.semanticmetadata.lire.imageanalysis.features.global.FCTH;
import oracle.CartridgeServices.*;
import oracle.ODCI.ODCIEnv;
import oracle.ODCI.ODCIIndexInfo;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;


public class ODCIMethodsImplementation {
    static GlobalDocumentBuilder globalDocumentBuilder;

    public static int ODCIIndexCreate(ODCIIndexInfo ia, String params, ODCIEnv env) throws IOException {
        globalDocumentBuilder = new GlobalDocumentBuilder(CEDD.class);
        globalDocumentBuilder.addExtractor(FCTH.class);
        globalDocumentBuilder.addExtractor(AutoColorCorrelogram.class);
        IndexWriterConfig conf = new IndexWriterConfig(new WhitespaceAnalyzer());
        IndexWriter iw = new IndexWriter(FSDirectory.open(Paths.get("index")), conf);

        return 1;
    }

    public static int ODCIIndexDrop(ODCIIndexInfo ia, ODCIEnv env){

        return 1;
    }
}
