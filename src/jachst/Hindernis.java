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
public class Hindernis {
    private boolean tödlich;
    private int boxX, boxY;
    private int radiusZuSeite;
    
    
    public Hindernis(int pX, int pY, boolean töten){
      radiusZuSeite = 50;
      tödlich = töten;
      boxX = pX*50;
      boxY = pY*50;
    }
    
    public boolean gibTödlich(){
        return tödlich;
    }
    public void zeichne(Graphics g){
        g.drawRect(boxX, boxY, radiusZuSeite, radiusZuSeite);
    }
    public int getX(){
        return boxX;
    }
    public int getY(){
        return boxY;
    }
    public int getRadZuSeite(){
        return radiusZuSeite;
    }
    
}
