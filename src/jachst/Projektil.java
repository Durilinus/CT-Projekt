/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jachst;

/**
 *
 * @author chris
 */
public class Projektil {
    private static int posX;
    private static int posY;
    private boolean exist;
    
    public Projektil(int startX, int startY){
        exist = true;
        posX = startX;
        posY = startY;
    }
    public void aenderePosition(int pX, int pY){
        posX = pX;
        posY = pY;
    }
    public int getX(){
        return posX;
    }
    public int getY(){
        return posY;
    }
    public boolean exist(){
        return exist;
    }
    public void setExist(boolean existiert){
        exist = existiert;
    }
    
    
}
