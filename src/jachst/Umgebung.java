/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jachst;

import java.awt.Graphics;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author stefan.schaufler
 */
public class Umgebung extends Sprite {

    Scanner scan;

    private void scanner() {

        try {
            scan = new Scanner(new File("level/level_1.txt"));
            while (scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Hindernis[] level = new Hindernis[5];


    @Override
    public void gibAktuellesBild() {
    }

    @Override
    public void zeichne(Graphics g, int breite, int hoehe) {
    }

    @Override
    public void berechneBilder(long delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void berechneSpiel(long delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doAnimation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
