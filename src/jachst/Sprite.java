/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jachst;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author stefan.schaufler
 */
public abstract class Sprite extends Rectangle2D.Double {
    protected static BufferedImage[] alleBilder;
    public abstract void berechneBilder(long delta);
    public abstract void berechneSpiel(long delta);
    public abstract void gibAktuellesBild();
    public abstract void zeichne(Graphics g, int breite, int hoehe);
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
        initAlleBilder();
        //this.width = alleBilder[0].getWidth();
        //this.height = alleBilder[0].getHeight();
        
        
    }
    
    public void initAlleBilder(){
        alleBilder = new BufferedImage[5]; 
        try {
                   alleBilder[0] = ImageIO.read(new File("C:\\Users\\chris\\Desktop\\Planung\\CT-Projekt\\src\\jachst\\bilder\\HeldRechts.jpg"));
               } catch (IOException e) {
                   System.out.println("Es konnte kein Bild gefunden werden.");
               }
    }
    
    public void drawObject(Graphics g){
        g.drawImage(alleBilder[aktBild],(int) x, (int) y,null);      
    }

}
