/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jachst;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author stefan.schaufler
 */
public class Bogenschuetzen {  
    
    private final int radiusAttacke = 300;
    private int abstandXzuHeld;
    private int abstandYzuHeld;
    private int absoluterAbstand;
    private static Projektil kugel;
    private Held derHeld;
    private Rectangle hitboxProjektil;
    private int pX;
    private int pY;
    private GUI gui;
    private boolean schiesst;
    
    public Bogenschuetzen( Held heldi,GUI g){
       
       
       this.pX = 500;
       this.pY = 400;
       
       derHeld = heldi;
       gui = g;
       
    }
    public int getX(){
        return pX;
    }
    public int getY(){
        return pY;
    }
    public boolean kugelIsAlive(){
        if(kugel != null){
            return kugel.isAlive();
        }
        return false;
    }
    public boolean kugelExist(){
        if(kugel == null){
            return false;
        }
        return true;
    }
    public int getProjektilX(){
    return kugel.getX();
    }
    public int getProjektilY(){
        return kugel.getY();
    }
    public static Rectangle gibHitboxProjektil(){
        return kugel.gibHitbox();
    }
    public static boolean pruefeKugelAlive(){
        return kugel.isAlive();
    }
    //@Override
    protected void attacke() {
        berechneAbstaende();    
        if( (radiusAttacke >= absoluterAbstand) && (kugel == null || kugel.isAlive() == false)) {
            kugel = new Projektil(pX,pY,derHeld.pX,derHeld.pY);
            kugel.start();
            schiesst = true;
        }else{
            schiesst = false;
        }
    }
    private void berechneAbstaende(){
        abstandXzuHeld = Math.abs(this.pX - derHeld.pX);   //werden zu unterschiedlichen Zeitpunkten berechnet
        abstandYzuHeld = Math.abs(derHeld.pY - this.pY);
        absoluterAbstand = (int)Math.sqrt((abstandXzuHeld*abstandXzuHeld) + (abstandYzuHeld*abstandYzuHeld));
    }
    //@Override
    protected void laufen() {
        pX++;
    }
    
    public boolean gibSchiesst(){
        return schiesst;
    }

    public void zeichneSchiesst(){
        gui.trashGegnerlaedtnachlinks();
        gui.trashGegnerlaedtnachrechts();
        if(pX > derHeld.pX){
           gui.trashGegnerschiesstrechts();
           if(gui.Gegnerschiesstlinks.once == false){
               gui.addGegnerschiesstlinks();
           }
        }else{
            gui.trashGegnerschiesstlinks();
            if(gui.Gegnerschiesstrechts.once == false){
               gui.addGegnerschiesstrechts();
            }
        }      
    }
    
    public void zeichneLaedtnach(){
        gui.trashGegnerschiesstlinks();
        gui.trashGegnerschiesstrechts();
        
        if(pX > derHeld.pX){
            gui.trashGegnerlaedtnachrechts();
            if(gui.Gegnerlaedtnachlinks.once == false){
                gui.addGegnerlaedtnachlinks();
            }
        }else{
            gui.trashGegnerlaedtnachlinks();
            if(gui.Gegnerlaedtnachrechts.once == false){
                gui.addGegnerlaedtnachrechts();
            }          
        }
        
    }

    
}
