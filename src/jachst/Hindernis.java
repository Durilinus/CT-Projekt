/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jachst;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author stefan.schaufler
 */
public class Hindernis {
    private boolean tödlich;
    private int boxX, boxY;
    private int radiusZuSeite;
    private static BufferedImage imageHindernis;
    private static BufferedImage imageTödlich;
    static {
        try {
            imageHindernis = ImageIO.read(new File("bilder/Hindernis.png"));
        } catch (IOException e) {
            System.out.println("Es konnte kein Bild gefunden werden");
        }
    }
    static {
        try {
            imageTödlich = ImageIO.read(new File("bilder/HindernisTödlich.png"));
        } catch (IOException e) {
            System.out.println("Es konnte kein Bild gefunden werden");
        }
    }
    
    
    public Hindernis(int pX, int pY, boolean töten){
      tödlich = töten;
      radiusZuSeite = 48;
      
      boxX = pX*50;
      boxY = pY*50;
    }
    
    public boolean gibTödlich(){
        return tödlich;
    }
    public void zeichne(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        if(tödlich == false){
           g2.drawImage(imageHindernis, boxX, boxY, radiusZuSeite, radiusZuSeite, null);
        }
        else {
            g2.drawImage(imageTödlich, boxX, boxY, radiusZuSeite, radiusZuSeite, null);
        }
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
