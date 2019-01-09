/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jachst;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author stefan.schaufler
 */

public class Held extends Spielfigur{
    
    private final int STARTX = 0;
    private final int STARTY = 400;
    private Hindernis dieHindernisse[];
    private static Sprung derSprung;
    private boolean berührtHindernis;
    private Steuerung control;
    
    GUI gui;
    

    public Held(Steuerung strg, GUI G){
        super(G);
        control = strg;
        gui = G;
    }
    //@Override
    //protected void attacke() {
       
    //}
    public void fallen(){  
        if(derSprung != null && derSprung.isAlive() == false && direktÜberHindernis() == false && STARTY != pY){
            derSprung.positionY++;
        }
    }
    public void setBerührtHindernis(boolean i){
        berührtHindernis = i;
        
    }
    
    public boolean getBerührtHindernis(){
        return berührtHindernis;
    }
    public void aktualisiereBerührtHindernis(){
        control.pruefeHeldAnHindernis();
    }
    public boolean direktÜberHindernis(){
        pY++;
        if(control.pruefeHeldAnHindernis() == true){
            pY--;
            Sprung.heldÜberHindernis = true;
            return true;
        }
        pY--;
        Sprung.heldÜberHindernis = true;
        return false;
    }
    public boolean direktUnterHindernis(){
        pY--;
        if(control.pruefeHeldAnHindernis() == true){
            pY++;
            Sprung.heldÜberHindernis = true;
            return true;
        }
        pY++;
        Sprung.heldÜberHindernis = true;
        return false;
    }

    protected void laufen() {
        if(pX >= 1000){  
             pX = 0;
             control.incAktuelleWelt();
             control.generiereNächsteWelt();
             
        }
        if(direktÜberHindernis() == false){
            derSprung.heldÜberHindernis = false;
        }
        if(dieRichtung == RECHTS){
            pX++; 
        }
        if(dieRichtung == LINKS){
            pX--;
        }
        if(dieRichtung == STEHEN){
            pX = pX;
        }
      
    }
    
    
    public void springe(){     
        
        if(derSprung == null || derSprung.isAlive() == false) {
         derSprung = new Sprung(this);
         derSprung.start();
         System.out.println("gesprungen");
        }
    }
    public int getSprungPos(){
        return Sprung.positionY;
    }
    
    public void setStartPos(){
       pX = STARTX;
       pY = STARTY;
       dieRichtung = 0;
    }
    //@Override
    

    //@Override
    public void berechneSpiel(long delta) {
        
    }

    //@Override
    public void gibAktuellesBild() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //@Override
    public void zeichne(Graphics g, int breite, int hoehe) {
      //  g.drawRect(pX, pY, 5, 10);
        
        Graphics2D g2 = (Graphics2D) g;
        //g2.drawImage(alleBilder[aktBild],(int)pX , (int)pY, breite , hoehe ,null);
        
    }    

   //@Override
    
}
