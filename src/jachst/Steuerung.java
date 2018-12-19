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
    
    private static int kastenBreite;
    private static int kastenHoehe;
    private static final int KASTENMENGEX = 10;
    private static final int KASTENMENGEY = 6;
    
    Steuerung(GUI gui){
        dieGUI = gui;
        neuesSpiel();
        
    }
    public void neuesSpiel(){
        initFiguren();
        rolf.setStartPos();
        dieGUI.repaint();
        t1 = new Timer(10, new ActionListener(){
              public void actionPerformed(ActionEvent ae){
                  bewegeAlleMobs(); 
                  dieGUI.repaint();
              }
            });
        t1.start();    
    }
    public void bewegeAlleMobs(){
        for (int i = 0; i < dieFlugMobs.length; i++) {
            dieFlugMobs[i].laufen();   
        }
        for (int i = 0; i < dieBodenMobs.length; i++) {
            dieBodenMobs[i].laufen();
            
        }
    }
    public void bewegeHeld(){
        rolf.laufen();
        System.out.println("X-Koordinate: "+rolf.pX );
    }
    public void zeichneAlles(Graphics g){
        rolf.zeichne(g);
        System.out.println("zeichnet");
    }
    
    public void initFiguren(){
       kastenBreite = dieGUI.getWidth() / KASTENMENGEX;
       kastenHoehe = dieGUI.getHeight() / KASTENMENGEY;
        
       rolf = new Held();
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
        hitboxHeld = new Rectangle(rolf.pX,rolf.pY, kastenBreite, kastenHoehe);
    }
    
    
}
