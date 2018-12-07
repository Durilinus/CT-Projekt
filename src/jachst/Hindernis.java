/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jachst;

/**
 *
 * @author stefan.schaufler
 */
public class Hindernis {
    private boolean tödlich;
    private int boxX, boxY;
    private boolean begehbar;
    
    public Hindernis(int pX, int pY, boolean begehbarjaein, boolean töten){
      tödlich = töten;
      begehbar = begehbarjaein;
      boxX = pX;
      boxY = pY;
    }
    
    public boolean gibTödlich(){
        return tödlich;
    }
    public boolean gibBegehbar(){
        return begehbar;
    }
    
    
}
