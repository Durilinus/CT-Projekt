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
    private int maxSprungweite;
    private int sprungPosition;
    private Timer t2;
    private boolean fällt;
    public Held(){
        maxSprungweite = 100;
        sprungPosition = 0;
    }
    @Override
    protected void attacke() {
       
    }

    protected void laufen(boolean richtung) {
        rechts = richtung;
        
        if(rechts == true){
            pX++;  
        }else{
            pX--;
        }
      
    }
    
     
    public void berechneSprung() {
      
        t2 = new Timer(20, new ActionListener(){
              public void actionPerformed(ActionEvent ae){
                   
                      pY = pY +  Math.abs( (-(1/50)*((sprungPosition - 50)^2)));
                      sprungPosition++;
                      System.out.println(pY);
                      pX++;
                   
              }
            });
        
        t2.start();
        while(sprungPosition != maxSprungweite){ 
            t2.stop();
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
    public void zeichne(Graphics g, int breite, int hoehe) {
      //  g.drawRect(pX, pY, 5, 10);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(alleBilder[aktBild],pX , pY, breite , hoehe ,null);
    }    

    @Override
    public void doAnimation() {
      aktBild++;
      if(aktBild >= alleBilder.length){
          aktBild = 0;
      }
    }
}
