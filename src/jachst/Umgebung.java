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
import java.util.Scanner;
import javax.imageio.ImageIO;


/**
 *
 * @author stefan.schaufler
 */
public class Umgebung /*extends Sprite*/ {

   // File f = new File("level/level1");
  //  Leser l = new Leser(f);
    Leser l;
    Hindernis[] level;
    int i =0;
    private static BufferedImage hintergrund;
    static {
        try {
            hintergrund = ImageIO.read(new File("bilder/hintergrund1.png"));
        } catch (IOException e) {
            System.out.println("Es konnte kein Bild gefunden werden");
        }
    }
    
    public Umgebung(String lvl){
       level = new Hindernis[15];
       File f = new File(lvl);
       l = new Leser(f);
       
    }
    

    public void aufbau() {
      //  for (int i = 0; i < level.length; i++) {
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 20; x++) {
                    if (l.gibMap(x, y) == 1) {
                        level[i] = new Hindernis(x,y,false);  
                        i++;
                        System.out.println(i+"NEUES HINDERNIS: "+ x + ","+ y);
                    }
                }
            }
        //}
    }

    //@Override
    public void gibAktuellesBild() {
    }

    //@Override
    public void zeichneHintergrund(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(hintergrund, 0,0, null);
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
    public void doAnimation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
