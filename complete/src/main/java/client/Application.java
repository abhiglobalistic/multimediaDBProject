/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author Okba
 */
public class Application {
    public static void main(String [] args){
        LireClient lireClient = new LireClient();
        String response = lireClient.get("/greeting");
        System.out.println(response);
    }
}
