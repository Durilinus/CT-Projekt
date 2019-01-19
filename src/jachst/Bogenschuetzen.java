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
public class Bogenschuetzen extends Spielfigur {  
    
    private final int radiusAttacke = 150;
    private int abstandXzuHeld;
    private int abstandYzuHeld;
    private int absoluterAbstand;
    private static Projektil kugel;
    private Held derHeld;
    
    public Bogenschuetzen(GUI G, Held heldi){
       super (G); 
       derHeld = heldi;
       pX = 60;
       pY = 440;
    }
    //@Override
    protected void attacke() {
        berechneAbstaende();
        if( radiusAttacke >= absoluterAbstand ) {
            kugel = new Projektil(pX,pY);
        }
    }
    private void berechneAbstaende(){
        abstandXzuHeld = Math.abs(this.pX - derHeld.pX);   //werden zu unterschiedlichen Zeitpunkten berechnet
        abstandYzuHeld = Math.abs(derHeld.pY - this.pY);
        absoluterAbstand = (int)Math.sqrt((abstandXzuHeld*abstandXzuHeld) + (abstandYzuHeld*abstandYzuHeld));
    }
    public void bewegeProjektil(){
        if(abstandXzuHeld > abstandYzuHeld){
            if(abstandXzuHeld > 0){
                kugel.posX--;
            } else {
                kugel.posX++;
            }
        } else {
            if(abstandYzuHeld > 0){
                kugel.posY--;
            } else {
                kugel.posY++;
            }
          }
    }
    

    //@Override
    protected void laufen() {
        pX++;
    }

    //@Override
    public void berechneBilder(long delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public void berechneSpiel(long delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public void gibAktuellesBild() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public void zeichne(Graphics g, int breite, int hoehe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public void doAnimation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
