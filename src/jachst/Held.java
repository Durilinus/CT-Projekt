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
    

    public Held(Steuerung strg){
        control = strg;
    }
    @Override
    protected void attacke() {
       
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
    private boolean direktÜberHindernis(){
        pY++;
        if(control.pruefeHeldAnHindernis() == true){
            pY--;
            return true;
        }
        pY++;
        return false;
    }

    protected void laufen() {
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
        derSprung = new Sprung(this);
        derSprung.start();
        System.out.println("gesprungen");
    }
    public int getSprungPos(){
        return Sprung.positionY;
    }
    
    public void setStartPos(){
       pX = STARTX;
       pY = STARTY;
       dieRichtung = 0;
    }
    @Override
    public void berechneBilder(long delta) {
        animation += (delta/1000000);
        if(animation > delay){
           animation = 0;
           doAnimation();
        }
    }

    @Override
    public void berechneSpiel(long delta) {
        
    }

    @Override
    public void gibAktuellesBild() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void zeichne(Graphics g, int breite, int hoehe) {
      //  g.drawRect(pX, pY, 5, 10);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(alleBilder[aktBild],(int)pX , (int)pY, breite , hoehe ,null);
    }    

    @Override
    public void doAnimation() {
      aktBild++;
      if(aktBild >= alleBilder.length){
          aktBild = 0;
      }
    }
}
