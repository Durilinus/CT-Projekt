/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jachst;



/**
 *
 * @author chris
 */
public class Sprung extends Thread{
    

    private static boolean hochpunktErreicht = false;
    private static boolean fertig = false;
    private static int sprungHoehe = 50;
    private static int anfangY = 400;
    public static int positionY = anfangY;
    
    public Sprung(){
       
        
    }
    
    public void run(){
        int delay = 4;
        fertig = false;
        while(fertig != true){
            springen();
            try {
                Thread.sleep(delay);
            } catch (Exception e) {
                
            }
        }
        hochpunktErreicht = false;
        
    }

    private void springen() {
        if(hochpunktErreicht == false){
            positionY--;
        }
        if(positionY == (anfangY - sprungHoehe)){
            hochpunktErreicht = true;
        }
        if(hochpunktErreicht == true && positionY <= anfangY){
            positionY++;
            if(positionY == anfangY){
                fertig = true;
            }
        }
        
    }
     
    
    
}
