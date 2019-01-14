/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jachst;

/**
 *
 * @author Jannik
 */
public interface Movable {
    
    public void doLogic(long delta);
    public void move(long delta);
    
}
