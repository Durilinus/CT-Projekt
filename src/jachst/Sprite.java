/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jachst;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author stefan.schaufler
 */
public abstract class Sprite {
    private BufferedImage[] alleBilder;
    public abstract void berechneBilder();
    public abstract void berechneSpiel();
    public abstract void gibAktuellesBild();
    public abstract void zeichne(Graphics g);
    
    public Sprite(){
        System.out.println("deine Mum");
    }
    
    
}
