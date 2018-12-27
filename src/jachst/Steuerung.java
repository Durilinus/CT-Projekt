/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jachst;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


/**
 *
 * @author stefan.schaufler
 */
public class Steuerung {
    private Flugmobs dieFlugMobs[];
    private Held rolf;
    private GUI dieGUI;
    private Bodenmobs dieBodenMobs[];
    private Timer t1;
    private Rectangle hitboxenBodenMobs[];
    private Rectangle hitboxenFlugMobs[];
    private Rectangle hitboxHeld;
    
    private int kastenBreite;
    private int kastenHoehe;
    private final int KASTENMENGEX = 20;
    private final int KASTENMENGEY = 10;
    
    Steuerung(GUI gui){
        dieGUI = gui;   
        neuesSpiel();    
    }
    public void neuesSpiel(){
        dieGUI.repaint();
        initFiguren();      
        t1 = new Timer(4, new ActionListener(){
              public void actionPerformed(ActionEvent ae){
                  bewegeAlleMobs(1, false); 
                  rolf.laufen();
                  rolf.pY = rolf.getSprungPos();
                  dieGUI.repaint();
              }
            });
        
        t1.start();
        
        
    }
    
    public void springenderRolf(){
        rolf.springe();
    }
    public void aendereHeldRichtung(int richtung){
        rolf.richtungWechsel(richtung);
    }
   
    public void bewegeAlleMobs(int richtungRechts, boolean springt){
        for (int i = 0; i < dieFlugMobs.length; i++) {
            dieFlugMobs[i].laufen();   
        }
        for (int i = 0; i < dieBodenMobs.length; i++) {
            dieBodenMobs[i].laufen();     
        }
        
    }
    public void initKaesten(){
        kastenBreite = dieGUI.getWidth() / KASTENMENGEX;
        kastenHoehe = dieGUI.getHeight() / KASTENMENGEY;  
    }
    public void bewegeHeld(){
        rolf.laufen();
        System.out.println("X-Koordinate: "+rolf.pX );
    }
    public void zeichneAlles(Graphics g){
        rolf.zeichne(g, kastenBreite, kastenHoehe);
        System.out.println("zeichnet");
        
    }
    
    
    public void initFiguren(){
       rolf = new Held();
       rolf.setStartPos();
       dieFlugMobs = new Flugmobs[10];
       for(int i = 0; i < dieFlugMobs.length; i++){
           dieFlugMobs[i] = new Flugmobs();
       }
       dieBodenMobs = new Bodenmobs[10];
       for(int i = 0; i < dieBodenMobs.length; i++){
           dieBodenMobs[i] = new Bodenmobs();
       }   
    }
    public void initHitboxen(){
        hitboxHeld = new Rectangle(rolf.pX, rolf.pY, kastenBreite, kastenHoehe);
    }
    
    
}
