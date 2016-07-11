/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
/**
 *
 * @author Okba
 */
public class Application {
    final static java.lang.Integer SUCCESS = new java.lang.Integer(0);
    final static java.lang.Integer ERROR = new java.lang.Integer(1);
    //private String server = "http://127.0.0.1:8080";
    public static java.lang.Integer ODCIIndexCreate(oracle.ODCI.ODCIIndexInfo ia, java.lang.String params, oracle.ODCI.ODCIEnv env){
        
        
        HttpURLConnection connection = null;
        try{
           URL server = new URL("http://127.0.0.1:8080/createindex");
           connection = (HttpURLConnection) server.openConnection();
           connection.setRequestMethod("GET");
           String res = connection.getResponseMessage();
           System.out.println(res);
        }catch(IOException ex){
            System.out.println(ex);
        }
        return SUCCESS;
    }
    
    public static java.lang.Integer ODCIIndexDROP(oracle.ODCI.ODCIIndexInfo ia, java.lang.String params, oracle.ODCI.ODCIEnv env){
        HttpURLConnection connection = null;
        try{
           URL server = new URL("http://127.0.0.1:8080/dropindex");
           connection = (HttpURLConnection) server.openConnection();
           connection.setRequestMethod("GET");
           String res = connection.getResponseMessage();
           System.out.println(res);
        }catch(IOException ex){
            System.out.println(ex);
        }
        return SUCCESS;
    }
    
    
    public static java.lang.Integer ODCIIndexInsert(oracle.ODCI.ODCIIndexInfo ia, String rid, String newval, oracle.ODCI.ODCIEnv env){
        return SUCCESS;
    }
    public static java.lang.Integer ODCIIndexUpdate(oracle.ODCI.ODCIIndexInfo ia, String rid, String oldval, String newval, oracle.ODCI.ODCIEnv env) throws IOException{
        return SUCCESS;
    }

    public static java.lang.Integer ODCIIndexDelete(oracle.ODCI.ODCIIndexInfo ia, String rid, String oldval, oracle.ODCI.ODCIEnv env) throws IOException{
        return SUCCESS;
    }

    public static java.lang.Integer ODCIIndexStart(int IN, oracle.ODCI.ODCIIndexInfo ia,  oracle.ODCI.ODCIPredInfo pi,  oracle.ODCI.ODCIQueryInfo qi,  int strt, int stop,  oracle.ODCI.ODCIEnv env) throws IOException{
        
      return SUCCESS;
    }
    public static java.lang.Integer ODCIIndexFetch(String IN, int OUT ,int nrows, String[] rids, oracle.ODCI.ODCIEnv env) throws IOException{
       
   
        return SUCCESS;
    }
    
    public static java.lang.Integer ODCIIndexClose(int  IN, oracle.ODCI.ODCIEnv env) throws IOException{
       return SUCCESS;
    }   
    
    
    public static String similarityFunction(String reqPath, String imPath){
        HttpURLConnection connection = null;
        try{
           URL server = new URL("http://127.0.0.1:8080/similarity");
           connection = (HttpURLConnection) server.openConnection();
           connection.setRequestMethod("POST");
           String body = "";
           body += "{'reqPath':" + reqPath + ",'imPath':" + imPath + "}";
           String res = connection.getResponseMessage();
           System.out.println(res);
        }catch(IOException ex){
            System.out.println(ex);
        }
        return "ok";
    }
    
    
    public static void main(String [] args){
        System.out.println(similarityFunction("test"));
    }//jackson
    
    
}
