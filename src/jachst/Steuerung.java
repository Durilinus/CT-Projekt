/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jachst;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author stefan.schaufler
 */
public class Steuerung {

    private static Umgebung welt[];
    private int aktuelleWelt = 0;
    private Flugmobs dieFlugMobs[];
    private Held rolf;
    private GUI dieGUI;
    private Bogenschuetzen dieBodenMobs[];
    private Timer t1;
    private Rectangle hitboxenBodenMobs[];
    private Rectangle hitboxenFlugMobs[];
    private Rectangle hitboxHeld;
    private Rectangle hitboxHindernisse[];
    private Hindernis dasHindernis[];
    private int kastenBreite = 50;
    private int kastenHoehe = 50;
    private final int KASTENMENGEX = 20;
    private final int KASTENMENGEY = 10;
    private static final int hindernisPX[] = {100, 200, 300, 400};
    private static final int hindernisPY[] = {400, 400, 400, 400};
    Sprite sprite;
    private JachstFrame jFrame;

    Steuerung(GUI gui) {
        dieGUI = gui;
        neuesSpiel();
    }

    public void neuesSpiel() {

        welt = new Umgebung[10];
        // generiereNächsteWelt();
        dieGUI.repaint();
        initFiguren();

        t1 = new Timer(4, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                bewegeAlleMobs(1, false);

                bewegeHeld();

                rolf.pY = rolf.getSprungPos();
                rolf.fallen();
                dieGUI.repaint();
                rolf.pruefeHeldInDerLuft();
                //System.out.println("springt: "+rolf.gibInDerLuft());
            }
        });

        t1.start();
    }

    public void zeichneHintegrundWelt(Graphics g) {
        welt[aktuelleWelt].zeichneHintergrund(g);
    }

    public int getAktuelleWelt() {
        return aktuelleWelt;
    }

    public void incAktuelleWelt() {
        aktuelleWelt++;
    }

    private void initHindernisse() {
        welt[aktuelleWelt].aufbau();
    }

    public void generiereNächsteWelt() {

        switch (aktuelleWelt) {
            case 0:
                welt[aktuelleWelt] = new Umgebung("level/level1.txt");
                break;

            case 1:
                welt[aktuelleWelt] = new Umgebung("level/level2.txt");
                break;
            case 2:
                welt[aktuelleWelt] = new Umgebung("level/level3.txt");
                break;
            case 3:
                welt[aktuelleWelt] = new Umgebung("level/level4.txt");
                break;
            case 4:
                welt[aktuelleWelt] = new Umgebung("level/level5.txt");
                break;
            case 5:
                welt[aktuelleWelt] = new Umgebung("level/level6.txt");
                break;

        }

        initHindernisse();
        if (hitboxHindernisse != null) {
            aenderHitboxenHindernis();
        }

    }

    public void stop() {
        t1.stop();
        JOptionPane.showConfirmDialog(dieGUI, "Gewonnen, Glückwunsch!");
    }

    public void springenderRolf() {
        rolf.springe();
    }

    public void aendereHeldRichtung(int richtung) {
        rolf.richtungWechsel(richtung);
    }

    public void bewegeAlleMobs(int richtungRechts, boolean springt) {
        for (int i = 0; i < dieFlugMobs.length; i++) {
            dieFlugMobs[i].laufen();
        }
        for (int i = 0; i < dieBodenMobs.length; i++) {
            dieBodenMobs[i].laufen();
        }
    }

    public void initKaesten() {
        kastenBreite = dieGUI.getWidth() / KASTENMENGEX;
        kastenHoehe = dieGUI.getHeight() / KASTENMENGEY;
    }

    public void bewegeHeld() {
        pruefeHeldAnHindernis();
        rolf.laufen();
        if (pruefeHeldAnHindernis() == true) {
            if (rolf.getRichtung() == 1) {
                rolf.pX -= 2;

            }
            if (rolf.getRichtung() == 2) {
                rolf.pX += 2;

            }
            //  aendereHeldRichtung(0);
        }
        System.out.println("X-Koordinate: " + rolf.pX);
        System.out.println("Y-Koordinate: " + rolf.pY);

    }

    public void zeichneAlles(Graphics g) {

        zeichneHintegrundWelt(g);

        for (int i = 0; i < welt[aktuelleWelt].level.length; i++) {
            welt[aktuelleWelt].level[i].zeichne(g);
        }

        //System.out.println("zeichnet");
        // g.drawRect(rolf.pX, rolf.pY, kastenHoehe, kastenHoehe);
        if (rolf.getLeben() == true) {
            if (rolf.gibInDerLuft() == false) {

                switch (rolf.getRichtung()) {

                    case 0:
                        dieGUI.trashHeldlinks();
                        dieGUI.trashHeldrechts();
                        dieGUI.trashHeldspringtlinks();
                        dieGUI.trashHeldspringtrechts();
                        if (dieGUI.Heldsteht.once == false) {
                            dieGUI.addHeldsteht();
                        }
                        dieGUI.Heldsteht.drawObjects(g);
                        break;

                    case 1:
                        dieGUI.trashHeldsteht();
                        dieGUI.trashHeldlinks();
                        dieGUI.trashHeldspringtlinks();
                        dieGUI.trashHeldspringtrechts();
                        if (dieGUI.Heldrechts.once == false) {
                            dieGUI.addHeldrechts();
                        }
                        dieGUI.Heldrechts.drawObjects(g);
                        break;

                    case 2:
                        dieGUI.trashHeldrechts();
                        dieGUI.trashHeldsteht();
                        dieGUI.trashHeldspringtlinks();
                        dieGUI.trashHeldspringtrechts();
                        if (dieGUI.Heldlinks.once == false) {
                            dieGUI.addHeldlinks();
                        }
                        dieGUI.Heldlinks.drawObjects(g);
                        break;

                }
            } else {
                switch (rolf.getRichtung()) {

                    case 0:
                        dieGUI.trashHeldlinks();
                        dieGUI.trashHeldrechts();
                        dieGUI.trashHeldsteht();
                        dieGUI.trashHeldspringtlinks();
                        if (dieGUI.Heldspringtrechts.once == false) {
                            dieGUI.addHeldspringtrechts();
                        }
                        dieGUI.Heldspringtrechts.drawObjects(g);
                        break;

                    case 1:
                        dieGUI.trashHeldsteht();
                        dieGUI.trashHeldlinks();
                        dieGUI.trashHeldrechts();
                        dieGUI.trashHeldspringtlinks();
                        if (dieGUI.Heldspringtrechts.once == false) {
                            dieGUI.addHeldspringtrechts();
                        }
                        dieGUI.Heldspringtrechts.drawObjects(g);
                        break;

                    case 2:
                        dieGUI.trashHeldrechts();
                        dieGUI.trashHeldsteht();
                        dieGUI.trashHeldlinks();
                        dieGUI.trashHeldspringtrechts();
                        if (dieGUI.Heldspringtlinks.once == false) {
                            dieGUI.addHeldspringtlinks();
                        }
                        dieGUI.Heldspringtlinks.drawObjects(g);
                        break;

                }

            }
        }

    }

    public void initFiguren() {
        generiereNächsteWelt();
        // initHindernisse();
        rolf = new Held(this, dieGUI);
        rolf.setStartPos();

        initHitboxen();

        dieFlugMobs = new Flugmobs[10];
        for (int i = 0; i < dieFlugMobs.length; i++) {
            dieFlugMobs[i] = new Flugmobs(dieGUI);
        }
        dieBodenMobs = new Bogenschuetzen[10];
        for (int i = 0; i < dieBodenMobs.length; i++) {
            dieBodenMobs[i] = new Bogenschuetzen(dieGUI);
        }
    }

    public void aktualisiereHitboxen() {
        hitboxHeld.setLocation((int) rolf.pX, (int) rolf.pY);
    }

    public int getHeldX() {
        return rolf.pX;
    }

    public int getHeldY() {
        return rolf.pY;
    }

    public boolean pruefeHeldAnHindernis() {
        aktualisiereHitboxen();
        
        for (int i = 0; i < hitboxHindernisse.length; i++) {

            if (hitboxHeld.intersects(hitboxHindernisse[i]) == true) {
                if(welt[aktuelleWelt].level[i].gibTödlich() == true){
                    rolf.leben = false;
                }
                System.out.println("erwiScht!!!!!!!!!!!!!!!!!!");
                rolf.setBerührtHindernis(true);

                return true;
            }
        }
        rolf.setBerührtHindernis(false);
        return false;
    }

    public void initHitboxen() {
        hitboxHeld = new Rectangle(rolf.pX, rolf.pY, kastenBreite, kastenHoehe);
        hitboxHindernisse = new Rectangle[welt[aktuelleWelt].level.length];
        for (int i = 0; i < hitboxHindernisse.length; i++) {
            if (welt[aktuelleWelt].level[i].gibTödlich() == false){
                    hitboxHindernisse[i] = new Rectangle(welt[aktuelleWelt].level[i].getX(), welt[aktuelleWelt].level[i].getY(), welt[aktuelleWelt].level[i].getRadZuSeite(), welt[aktuelleWelt].level[i].getRadZuSeite());
            } else {
                    hitboxHindernisse[i] = new Rectangle(welt[aktuelleWelt].level[i].getX(), welt[aktuelleWelt].level[i].getY() + 35, welt[aktuelleWelt].level[i].getRadZuSeite(), welt[aktuelleWelt].level[i].getRadZuSeite()- 35);

            }
        }
    }

    public void aenderHitboxenHindernis() {
        for (int i = 0; i < hitboxHindernisse.length; i++) {
            hitboxHindernisse[i].setLocation(welt[aktuelleWelt].level[i].getX(), welt[aktuelleWelt].level[i].getY());
        }
    }

}
