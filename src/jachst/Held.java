/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jachst;

import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author stefan.schaufler
 */

public class Held extends Spielfigur{
    final int STARTX = 0;
    final int STARTY = 80;
    @Override
    protected void attacke() {
       
    }
// FAAAG
    @Override
    protected void laufen() {
        if(rechts == true){
            pX++;
            
        }else{
            pX--;
        }
    }
    public void setStartPos(){
       pX = STARTX;
       pY = STARTY;
       rechts = true;
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
    public void zeichne(Graphics g) {
        g.drawRect(pX, pY, 5, 10);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(alleBilder[aktBild],pX , pY, 50,50,null);
    }    

    @Override
    public void doAnimation() {
      aktBild++;
      if(aktBild >= alleBilder.length){
          aktBild = 0;
      }
    }
}
