/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jachst;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author stefan.schaufler
 */
public abstract class Sprite extends Rectangle2D.Double {
    BufferedImage[] alleBilder;
    public abstract void berechneBilder(long delta);
    public abstract void berechneSpiel(long delta);
    public abstract void gibAktuellesBild();
    public abstract void zeichne(Graphics g);
    public abstract void doAnimation();
    protected double dx;
    protected double dy;
    
    long delay;
    long animation;
    GUI parent;
    int aktBild = 0;
    
    public Sprite(){
    
        this.x = x;
        this.y = y;
        this.delay = delay;
        this.width = alleBilder[0].getWidth();
        this.height = alleBilder[0].getHeight();
        
    }
    
    
}
