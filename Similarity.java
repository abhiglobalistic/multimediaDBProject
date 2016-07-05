/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.semanticmetadata.lire.imageanalysis.features.global.CEDD;
import net.semanticmetadata.lire.searchers.GenericFastImageSearcher;
import net.semanticmetadata.lire.searchers.ImageSearchHits;
import net.semanticmetadata.lire.searchers.ImageSearcher;
import org.apache.lucene.index.IndexReader;

/**
 *
 * @author mahdi
 */
public class Similarity {
    public static ImageSearchHits similarity(String path ,IndexReader ir) throws IOException{
        //Create a searcher based on CEDD and which extracts the 12 most similar images to our image
        ImageSearcher searcher = new GenericFastImageSearcher(12, CEDD.class);
        //Read our image using the path
        BufferedImage img = ImageIO.read(new FileInputStream(path));
        //12 elements list of the most similar images 
        ImageSearchHits hits = searcher.search(img, ir);
        return  hits;
        
    
    
    }
    
}
