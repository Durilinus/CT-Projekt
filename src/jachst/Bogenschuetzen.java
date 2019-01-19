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
    }
    //@Override
    protected void attacke() {
        
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
        if(Math.abs(this.pX - derHeld.pX) > Math.abs(derHeld.pY - this.pY)){
            if(Math.abs(this.pX - derHeld.pX) > 0){
                dieRichtung = 3;
            } else {
                dieRichtung = 1;
            }
        } else {
            if(Math.abs(derHeld.pY - this.pY) > 0){
                dieRichtung = 2;
            } else {
                dieRichtung = 4;
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
