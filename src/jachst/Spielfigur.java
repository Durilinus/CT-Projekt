/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jachst;

import java.awt.Rectangle;

/**
 *
 * @author stefan.schaufler
 */
public class Spielfigur {
    protected boolean nahkampf;
    protected int leben, pX, dieRichtung;
    protected static int pY;
    protected Rectangle hitbox;
    public final int STEHEN = 0;
    public final int RECHTS = 1;
    public final int LINKS = 2;
    GUI gui;

    public Spielfigur(GUI g){
       gui = g; 
    }
    
    //protected abstract void attacke();    
    //protected abstract void laufen();    
    protected void richtungWechsel(int richtung){
        dieRichtung = richtung;
    }
    protected int getRichtung(){
        return dieRichtung;
    }
}
