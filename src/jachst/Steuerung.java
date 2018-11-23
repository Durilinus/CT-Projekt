/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jachst;

import java.awt.Graphics;

/**
 *
 * @author stefan.schaufler
 */
public class Steuerung {
    private Flugmobs dieFlugMobs[];
    private Held rolf;
    private GUI dieGUI;
    private Bodenmobs dieBodenMobs[];
    
    Steuerung(GUI gui){
        System.out.println("faggot");
        dieGUI = gui;
        neuesSpiel();
        
    }
    public void neuesSpiel(){
        initFiguren();
        rolf.setStartPos();
        dieGUI.repaint();
        
    }
    public void zeichneAlles(Graphics g){
        rolf.zeichne(g);
    }
    public void initFiguren(){
       rolf=new Held();
       dieFlugMobs = new Flugmobs[10];
       for(int i = 0; i < dieFlugMobs.length; i++){
           dieFlugMobs[i] = new Flugmobs();
       }
       dieBodenMobs = new Bodenmobs[10];
       for(int i = 0; i < dieBodenMobs.length; i++){
           dieBodenMobs[i] = new Bodenmobs();
       }
       
       
       
    }
    
    
}
