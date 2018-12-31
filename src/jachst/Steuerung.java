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
    private Rectangle hitboxHindernisse[];
    private Hindernis dasHindernis[];
    private int kastenBreite = 50;
    private int kastenHoehe = 50;
    private final int KASTENMENGEX = 20;
    private final int KASTENMENGEY = 10;
    private static final int hindernisPX[] = {100, 200, 300, 400};
    private static final int hindernisPY[] = {400, 400, 400, 400};
    
    Steuerung(GUI gui){
        dieGUI = gui;   
        neuesSpiel();    
    }
    public void neuesSpiel(){
        dieGUI.repaint();
        initFiguren(); 
       
        t1 = new Timer(5, new ActionListener(){
              public void actionPerformed(ActionEvent ae){
                  bewegeAlleMobs(1, false); 
                  
                  bewegeHeld();
                  
                  rolf.pY = rolf.getSprungPos();
                  dieGUI.repaint();
              }
            });
        
        t1.start();      
    }
    private void initHindernisse(){
        dasHindernis = new Hindernis[4];
        boolean tödlich = false;
        for (int i = 0; i < dasHindernis.length; i++) {
            dasHindernis[i] = new Hindernis(hindernisPX[i], hindernisPY[i], tödlich); 
        }
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
        pruefeHeldAnHindernis();
        rolf.laufen();
        if(pruefeHeldAnHindernis() == true){
            if(rolf.getRichtung() == 1){
                rolf.pX-= 2;
            }
            if(rolf.getRichtung() == 2){
                rolf.pX+= 2;
            }
          //  aendereHeldRichtung(0);
        } 
        System.out.println("X-Koordinate: "+rolf.pX );
        System.out.println("Y-Koordinate: "+rolf.pY );
        
    }
    public void zeichneAlles(Graphics g){
        rolf.zeichne(g, kastenBreite, kastenHoehe);
        for (int i = 0; i < dasHindernis.length; i++) {
            dasHindernis[i].zeichne(g);
        }
        g.drawRect(rolf.pX, rolf.pY, kastenBreite, kastenHoehe);
        System.out.println("zeichnet");
        
    }
    public void initFiguren(){
       initHindernisse();
       rolf = new Held(this);
       rolf.setStartPos();
       
       initHitboxen();
  
       dieFlugMobs = new Flugmobs[10];
       for(int i = 0; i < dieFlugMobs.length; i++){
           dieFlugMobs[i] = new Flugmobs();
       }
       dieBodenMobs = new Bodenmobs[10];
       for(int i = 0; i < dieBodenMobs.length; i++){
           dieBodenMobs[i] = new Bodenmobs();
       }   
    }
    public void aktualisiereHitboxen(){
        hitboxHeld.setLocation((int)rolf.pX, (int)rolf.pY);      
    }
    public int getHeldX(){
        return rolf.pX;
    }
    public boolean pruefeHeldAnHindernis(){
         aktualisiereHitboxen();
         System.out.println("aktualisiert hitboxen");
         for (int i = 0; i < hitboxHindernisse.length; i++) {
             
             if(hitboxHeld.intersects(hitboxHindernisse[i]) == true){
                 System.out.println("erwiScht!!!!!!!!!!!!!!!!!!");
                 rolf.setBerührtHindernis(true);
                 return true;
             }
         }
         rolf.setBerührtHindernis(false);
         return false;
    }
    public void initHitboxen(){
        hitboxHeld = new Rectangle(rolf.pX, rolf.pY, kastenBreite, kastenHoehe);
        hitboxHindernisse = new Rectangle[4];
        for (int i = 0; i < hitboxHindernisse.length; i++) {
            hitboxHindernisse[i] = new Rectangle(dasHindernis[i].getX(), dasHindernis[i].getY(), dasHindernis[i].getRadZuSeite(), dasHindernis[i].getRadZuSeite());    
        }     
    }   
    
}
