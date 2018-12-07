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
public abstract class Spielfigur  extends Sprite {
    protected boolean nahkampf, rechts;
    protected int leben, pX, pY;
    protected Rectangle hitbox;

    public Spielfigur(){
        
    }
    
    protected abstract void attacke();    
    protected abstract void laufen();    
}
