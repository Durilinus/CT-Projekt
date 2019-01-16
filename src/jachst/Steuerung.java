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
    private Umgebung welt[];
    private int aktuelleWelt = 0;
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
    Sprite sprite;
    
    Steuerung(GUI gui){
        dieGUI = gui;   
        neuesSpiel();    
    }
    public void neuesSpiel(){
        generiereNächsteWelt();
        welt[aktuelleWelt] = new Umgebung("level/level1.txt");
        dieGUI.repaint();
        initFiguren(); 
        
       
        t1 = new Timer(4, new ActionListener(){
              public void actionPerformed(ActionEvent ae){
                  bewegeAlleMobs(1, false); 
                  
                  bewegeHeld();
                  
                  rolf.pY = rolf.getSprungPos();
                  rolf.fallen();
                  dieGUI.repaint();
              }
            });
        
        t1.start();      
    }
    public void zeichneHintegrundWelt(Graphics g){
        welt[aktuelleWelt].zeichneHintergrund(g);
    }
    public void incAktuelleWelt(){
        aktuelleWelt++;
    }
    private void initHindernisse(){
        welt[aktuelleWelt].aufbau(); 
    }
    public void generiereNächsteWelt(){
        welt = new Umgebung[aktuelleWelt+1];
        
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
        zeichneHintegrundWelt(g);

        for (int i = 0; i < welt[aktuelleWelt].level.length; i++) {
           welt[aktuelleWelt].level[i].zeichne(g);
        }
        
        System.out.println("zeichnet");
        
        switch(rolf.getRichtung()){
          
            case 0: dieGUI.trashHeldlinks();
                    dieGUI.trashHeldrechts();
                    if(dieGUI.Heldsteht.once == false){
                        dieGUI.addHeldsteht();
                    }
                    dieGUI.Heldsteht.drawObjects(g);
                    break;
                    
            case 2: dieGUI.trashHeldrechts();
                    dieGUI.trashHeldsteht();
                   if(dieGUI.Heldlinks.once == false){    
                        dieGUI.addHeldlinks();
                    }    
                    dieGUI.Heldlinks.drawObjects(g); 
                    break;
                    
            case 1: dieGUI.trashHeldsteht();
                    dieGUI.trashHeldlinks();
                    if(dieGUI.Heldrechts.once == false){
                        dieGUI.addHeldrechts();
                    }
                    dieGUI.Heldrechts.drawObjects(g);
                    break;
        }
             
    }
    public void initFiguren(){
       initHindernisse();
       rolf = new Held(this,dieGUI);
       rolf.setStartPos();
       
       initHitboxen();
  
       dieFlugMobs = new Flugmobs[10];
       for(int i = 0; i < dieFlugMobs.length; i++){
           dieFlugMobs[i] = new Flugmobs(dieGUI);
       }
       dieBodenMobs = new Bodenmobs[10];
       for(int i = 0; i < dieBodenMobs.length; i++){
           dieBodenMobs[i] = new Bodenmobs(dieGUI);
       }   
    }
    public void aktualisiereHitboxen(){
        hitboxHeld.setLocation((int)rolf.pX, (int)rolf.pY);      
    }
    public int getHeldX(){
        return rolf.pX;
    }
    
    public int getHeldY(){
        return rolf.pY;
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
        hitboxHindernisse = new Rectangle[welt[aktuelleWelt].level.length];
        for (int i = 0; i < hitboxHindernisse.length; i++) {
            hitboxHindernisse[i] = new Rectangle(welt[aktuelleWelt].level[i].getX(), welt[aktuelleWelt].level[i].getY(), welt[aktuelleWelt].level[i].getRadZuSeite(), welt[aktuelleWelt].level[i].getRadZuSeite());    
        }     
    }   
    
}
