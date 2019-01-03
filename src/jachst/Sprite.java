/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jachst;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author stefan.schaufler
 */
public  class Sprite extends Rectangle2D.Double {
    protected static BufferedImage[] alleBilder;
    //public abstract void berechneBilder(long delta);
    //public abstract void berechneSpiel(long delta);
    //public abstract void gibAktuellesBild();
    //public abstract void zeichne(Graphics g, int breite, int hoehe);
    //public abstract void doAnimation();
    protected double dx;
    protected double dy;
    
    long delay;
    long animation;
    GUI parent;
    int aktBild = 0;
    
    public Sprite(BufferedImage[] i,double x, double y, long delay,GUI g){
        alleBilder = i;
        this.x = x;
        this.y = y;
        this.delay = delay;
        this.width = alleBilder[0].getWidth();
        this.height = alleBilder[0].getHeight();
        parent = g;
        
    }
    
    public void berechneBilder(long delta) {
        animation += (delta/1000000);
        if(animation > delay){
           animation = 0;
           doAnimation();
        }
    }
    public void doAnimation() {
      aktBild++;
      if(aktBild >= alleBilder.length){
          aktBild = 0;
      }
    }
    

}
