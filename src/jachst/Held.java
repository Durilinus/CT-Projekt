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
public class Held extends Spielfigur{
    @Override
    protected void attacke() {
       
    }
// FAAAG
    @Override
    protected void laufen() {
        if(rechts){
            pX++;
        }else{
            pX--;
        }
    }
    @Override
    public void berechneBilder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void berechneSpiel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void gibAktuellesBild() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void zeichne(Graphics g) {
        g.drawRect(pX, pY, 5, 10);
    }    
}
