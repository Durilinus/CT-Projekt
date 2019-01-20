/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jachst;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author chris
 */
public class Projektil extends Thread {

    private static int posX;
    private static int posY;
    private static int dx;
    private static int dy;
    private static int zielX;
    private static int zielY;
    private static boolean fliegt = true;
    private static Rectangle hitbox;


    public Projektil(int startX, int startY, int zX, int zY) {
        hitbox = new Rectangle(startX, startY, 10, 10);
        posX = startX;
        posY = startY;
        zielX = zX;
        zielY = zY;
        berechneAbstaende();
        

    }
   

    public void run() {
        fliegt = true;
        int delay = 4;
        int flugweite = 0;
        while (fliegt == true) {
            bewegeRichtungZiel();
            hitbox.setLocation(posX, posY);
            if(flugweite == 300){
                fliegt = false;
            }
            flugweite++;
            System.out.println("fliegt");
            try {
                Thread.sleep(delay);
            } catch (Exception e) {

            }
        }
        
       
    }
    public void berechneAbstaende(){
        dx = posX - zielX;
        dy = posY - zielY;
    }
    
    public int getX(){
        return posX;
    }
    public int getY(){
        return posY;
    }
    public static Rectangle gibHitbox(){
        return hitbox;
    }

    private void bewegeRichtungZiel() {
        berechneAbstaende();
        if(Math.abs(dx) > Math.abs(dy)){
            if(dx > 0){
                posX--;
            } else {
                posX++;
            }
        } else {
            if(dy > 0){
                posY--;
            } else {
                posY++;
            }
          }      
    }

}
