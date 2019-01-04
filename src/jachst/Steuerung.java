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
    private Umgebung welt;
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
        welt = new Umgebung("level/level1.txt");
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
    private void initHindernisse(){
        welt.aufbau(); 
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
        for (int i = 0; i < welt.level.length; i++) {
            welt.level[i].zeichne(g);
        }
        g.drawRect(rolf.pX, rolf.pY, kastenBreite, kastenHoehe);
        System.out.println("zeichnet");
        
        dieGUI.Held.drawObjects(g);
       
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
        hitboxHindernisse = new Rectangle[welt.level.length];
        for (int i = 0; i < hitboxHindernisse.length; i++) {
            hitboxHindernisse[i] = new Rectangle(welt.level[i].getX(), welt.level[i].getY(), welt.level[i].getRadZuSeite(), welt.level[i].getRadZuSeite());    
        }     
    }   
    
}
