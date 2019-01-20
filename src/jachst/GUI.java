/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jachst;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;

/**
 *
 * @author stefan.schaufler
 */
public class GUI extends javax.swing.JPanel implements Runnable {

    /**
     * Creates new form GUI
     */
    
     
    Spielfigur F = new Spielfigur(this);
    private BufferedImage Projektil;
    private static final long serialVersionUID = 1L;
    boolean game_lauft = true;

    
    public Steuerung strg;
    long delta = 0;
    long last = 0;
    long fps = 0;
    
    Thread t;
    Thread r;
    Thread s;
    boolean once;
    
    Sprite Heldrechts;
    Sprite Heldlinks;
    Sprite Heldsteht;
    Sprite Heldspringtlinks;
    Sprite Heldspringtrechts;
    Sprite Gegnerschiesstlinks;
    Sprite Gegnerschiesstrechts;
    Sprite Gegnerlaedtnachlinks;
    Sprite Gegnerlaedtnachrechts;
    Vector<Sprite> actors;
    
    BufferedImage[] heldlinks;
    BufferedImage[] heldrechts;
    BufferedImage[] heldsteht;
    BufferedImage[] heldspringtlinks;
    BufferedImage[] heldspringtrechts;
    BufferedImage[] gegnerschiesstlinks;
    BufferedImage[] gegnerschiesstrechts;
    BufferedImage[] gegnerlaedtnachlinks;
    BufferedImage[] gegnerlaedtnachrechts;
    
    public GUI() {
        initComponents();
        this.setFocusable(true);
        strg = new Steuerung(this);
        initAlleBilder();            
    }
    
    Held H = new Held(strg,this);
    Bogenschuetzen Gegner = new Bogenschuetzen(H,this);
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        strg.initKaesten();
        strg.zeichneAlles(g);
        g.setColor(Color.red);
        g.drawString("FPS: "+Long.toString(fps),20,10);
    }
   
    private BufferedImage[] loadBilderHeldRechts(int bilder){
              
            BufferedImage[] anim = new BufferedImage[bilder];
            BufferedImage source = null;
            
            
            try{
                source = ImageIO.read(new File("bilder/Held_rechts.png"));
            }catch(IOException ioe){ioe.printStackTrace();}
            
            for(int x=0;x<bilder;x++){
                anim[x] = source.getSubimage(x*source.getWidth()/bilder, 0, source.getWidth()/bilder, source.getHeight());        
            }
            
            return anim;
        
    }
    private BufferedImage[] loadBilderHeldLinks(int bilder){
              
            BufferedImage[] anim = new BufferedImage[bilder];
            BufferedImage source = null;
            
            
            try{
                source = ImageIO.read(new File("bilder/Held_links.png"));
            }catch(IOException ioe){ioe.printStackTrace();}
            
            for(int x=0;x<bilder;x++){
                anim[x] = source.getSubimage(x*source.getWidth()/bilder, 0, source.getWidth()/bilder, source.getHeight());        
            }
            
            return anim;
        
    }
    
    private BufferedImage[] loadBilderHeldSteht(int bilder){
        
            BufferedImage[] anim = new BufferedImage[bilder];
            BufferedImage source = null;
            
            
            try{
                source = ImageIO.read(new File("bilder/Held_steht.png"));
            }catch(IOException ioe){ioe.printStackTrace();}
            
            for(int x=0;x<bilder;x++){
                anim[x] = source.getSubimage(x*source.getWidth()/bilder, 0, source.getWidth()/bilder, source.getHeight());        
            }
            
            return anim;
    }
    
    private BufferedImage[] loadBilderHeldSpringtLinks(int bilder){
        
            BufferedImage[] anim = new BufferedImage[bilder];
            BufferedImage source = null;
            
            
            try{
                source = ImageIO.read(new File("bilder/Heldspringtlinks.png"));
            }catch(IOException ioe){ioe.printStackTrace();}
            
            for(int x=0;x<bilder;x++){
                anim[x] = source.getSubimage(x*source.getWidth()/bilder, 0, source.getWidth()/bilder, source.getHeight());        
            }
            
            return anim;
    }
    
    private BufferedImage[] loadBilderHeldSpringtRechts(int bilder){
        
            BufferedImage[] anim = new BufferedImage[bilder];
            BufferedImage source = null;
            
            
            try{
                source = ImageIO.read(new File("bilder/Heldspringtrechts.png"));
            }catch(IOException ioe){ioe.printStackTrace();}
            
            for(int x=0;x<bilder;x++){
                anim[x] = source.getSubimage(x*source.getWidth()/bilder, 0, source.getWidth()/bilder, source.getHeight());        
            }
            
            return anim;
    }
    private BufferedImage[] loadBilderGegnerSchiesstLinks(int bilder){
        
            BufferedImage[] anim = new BufferedImage[bilder];
            BufferedImage source = null;
            
            
            try{
                source = ImageIO.read(new File("bilder/GegnerSchiesstLinks.png"));
            }catch(IOException ioe){ioe.printStackTrace();}
            
            for(int x=0;x<bilder;x++){
                anim[x] = source.getSubimage(x*source.getWidth()/bilder, 0, source.getWidth()/bilder, source.getHeight());        
            }
            
            return anim;
    }
    
    private BufferedImage[] loadBilderGegnerSchiesstRechts(int bilder){
        
            BufferedImage[] anim = new BufferedImage[bilder];
            BufferedImage source = null;
            
            
            try{
                source = ImageIO.read(new File("bilder/GegnerSchiesstRechts.png"));
            }catch(IOException ioe){ioe.printStackTrace();}
            
            for(int x=0;x<bilder;x++){
                anim[x] = source.getSubimage(x*source.getWidth()/bilder, 0, source.getWidth()/bilder, source.getHeight());        
            }
            
            return anim;
    }
    
    private BufferedImage[] loadBilderGegnerLaedtNachLinks(int bilder){
        
            BufferedImage[] anim = new BufferedImage[bilder];
            BufferedImage source = null;
            
            
            try{
                source = ImageIO.read(new File("bilder/GegnerLaedtNachLinks.png"));
            }catch(IOException ioe){ioe.printStackTrace();}
            
            for(int x=0;x<bilder;x++){
                anim[x] = source.getSubimage(x*source.getWidth()/bilder, 0, source.getWidth()/bilder, source.getHeight());        
            }
            
            return anim;
    }
    
    private BufferedImage[] loadBilderGegnerLaedtNachRechts(int bilder){
        
            BufferedImage[] anim = new BufferedImage[bilder];
            BufferedImage source = null;
            
            
            try{
                source = ImageIO.read(new File("bilder/GegnerLaedtNachRechts.png"));
            }catch(IOException ioe){ioe.printStackTrace();}
            
            for(int x=0;x<bilder;x++){
                anim[x] = source.getSubimage(x*source.getWidth()/bilder, 0, source.getWidth()/bilder, source.getHeight());        
            }
            
            return anim;
    }
    
    private void computeDelta(){
        delta = System.nanoTime() -last;
        last = System.nanoTime();
        
        fps = ((long) 1e9)/delta;
    }
    
    private void doLogic(){
        
        Vector<Sprite> trash = new Vector<Sprite>();
      
      if(actors.size() > 3){
          actors.remove(4);
      } 
        for(Movable mov:actors){
           mov.doLogic(delta);
           mov.move(delta);
           
           Sprite check = (Sprite)mov;
           if(check.remove){
              trash.add(check); 
           }
           
           if(trash.size()>0){
               for(Sprite s: trash){
                   actors.remove(s);
               }
           }
       }
      
    }
    
    
    @Override
    public void run() {
        while(game_lauft){
            computeDelta();
            
            doLogic();
            repaint();
            
            try{
                Thread.sleep(4);
            }catch(InterruptedException e){
                System.out.println("run error 143112");
            }
            System.out.println("actors"+actors.size());
            //System.out.println("once"+Heldsteht.once);
        }        
    }
    public void endscreen(){
        
    }
    
    public void zeichneProjektil(Graphics g){
       g.drawImage(Projektil, strg.bogenschuetze.getProjektilX(), strg.bogenschuetze.getProjektilY(), 10, 10, null);
    }
    
    public void initAlleBilder(){
        
        last = System.nanoTime();
        
        actors = new Vector<Sprite>();
        
        heldlinks = this.loadBilderHeldLinks(4);
        heldrechts = this.loadBilderHeldRechts(4);
        heldsteht = this.loadBilderHeldSteht(2);
        heldspringtlinks = this.loadBilderHeldSpringtLinks(1);
        heldspringtrechts = this.loadBilderHeldSpringtRechts(1);
        gegnerschiesstlinks = this.loadBilderGegnerSchiesstLinks(2);
        gegnerschiesstrechts = this.loadBilderGegnerSchiesstRechts(2);
        gegnerlaedtnachlinks = this.loadBilderGegnerLaedtNachLinks(2);
        gegnerlaedtnachrechts = this.loadBilderGegnerLaedtNachRechts(2);
        Heldlinks = new Sprite(heldlinks,strg.getHeldX(),strg.getHeldY(),100,this,strg);
        Heldrechts = new Sprite(heldrechts,strg.getHeldX(),strg.getHeldY(),100,this,strg);
        Heldsteht = new Sprite(heldsteht,strg.getHeldX(),strg.getHeldY(),100,this,strg);
        Heldspringtlinks = new Sprite(heldspringtlinks,strg.getHeldX(),strg.getHeldY(),100,this,strg);
        Heldspringtrechts = new Sprite(heldspringtrechts,strg.getHeldX(),strg.getHeldY(),0,this,strg);
        Gegnerschiesstlinks = new Sprite(gegnerschiesstlinks,strg.bogenschuetze.getX(),strg.bogenschuetze.getY(),300,this,strg);
        Gegnerschiesstrechts = new Sprite(gegnerschiesstrechts,strg.bogenschuetze.getX(),strg.bogenschuetze.getY(),300,this,strg);
        Gegnerlaedtnachlinks = new Sprite(gegnerlaedtnachlinks,strg.bogenschuetze.getX(),strg.bogenschuetze.getY(),300,this,strg);
        Gegnerlaedtnachrechts = new Sprite(gegnerlaedtnachrechts,strg.bogenschuetze.getX(),strg.bogenschuetze.getY(),300,this,strg);

        
        try{
           Projektil = ImageIO.read(new File("bilder/Projektil.png"));
        }catch(IOException ioe){ioe.printStackTrace();}
        
        if(!this.once){
            this.once = true;
            t = new Thread(this);
            t.start();
        }
        
    }
    
    public void trashHeldsteht(){
        Heldsteht.remove = true;
        Heldsteht.once = false;
    }
    
    public void trashHeldlinks(){
        Heldlinks.remove = true;
        Heldlinks.once = false;
    }
    
    public void trashHeldrechts(){
        Heldrechts.remove = true;
        Heldrechts.once = false;
    }
    
    public void trashHeldspringtlinks(){
        Heldspringtlinks.remove = true;
        Heldspringtlinks.once = false;
    }
    
    public void trashHeldspringtrechts(){
        Heldspringtrechts.remove = true;
        Heldspringtrechts.once = false;
    }
    
    public void trashGegnerschiesstlinks(){
        Gegnerschiesstlinks.remove = true;
        Gegnerschiesstlinks.once = false;
    }
    
    public void trashGegnerschiesstrechts(){
        Gegnerschiesstrechts.remove = true;
        Gegnerschiesstrechts.once = false;
    }
    
    public void trashGegnerlaedtnachlinks(){
        Gegnerlaedtnachlinks.remove = true;
        Gegnerlaedtnachlinks.once = false;
    }
    
    public void trashGegnerlaedtnachrechts(){
        Gegnerlaedtnachrechts.remove = true;
        Gegnerlaedtnachrechts.once = false;
    }
    
    public void addHeldsteht(){
        Heldsteht = new Sprite(heldsteht,strg.getHeldX(),strg.getHeldY(),100,this,strg);
        actors.add(Heldsteht); 
        Heldsteht.once=true;
    }
    
    public void addHeldlinks(){
        Heldlinks = new Sprite(heldlinks,strg.getHeldX(),strg.getHeldY(),100,this,strg);
        actors.add(Heldlinks);
        Heldlinks.once = true;
    }
    
    public void addHeldrechts(){
        Heldrechts = new Sprite(heldrechts,strg.getHeldX(),strg.getHeldY(),100,this,strg);
        actors.add(Heldrechts);
        Heldrechts.once=true;
    }
    
    public void addHeldspringtlinks(){
        Heldspringtlinks = new Sprite(heldspringtlinks,strg.getHeldX(),strg.getHeldY(),0,this,strg);
        actors.add(Heldspringtlinks);
        Heldspringtlinks.once=true;
    }
    
    public void addHeldspringtrechts(){
        Heldspringtrechts = new Sprite(heldspringtrechts,strg.getHeldX(),strg.getHeldY(),0,this,strg);
        actors.add(Heldspringtrechts);
        Heldspringtrechts.once=true;
    }
    
    public void addGegnerschiesstlinks(){
        Gegnerschiesstlinks = new Sprite(gegnerschiesstlinks,strg.bogenschuetze.getX(),strg.bogenschuetze.getY(),300,this,strg);     
        actors.add(Gegnerschiesstlinks);
        Gegnerschiesstlinks.once=true;
    }
    
    public void addGegnerschiesstrechts(){
        Gegnerschiesstrechts = new Sprite(gegnerschiesstrechts,strg.bogenschuetze.getX(),strg.bogenschuetze.getY(),300,this,strg);
        actors.add(Gegnerschiesstrechts);
        Gegnerschiesstrechts.once=true;
    }
    
    public void addGegnerlaedtnachlinks(){
        Gegnerlaedtnachrechts = new Sprite(gegnerlaedtnachlinks,strg.bogenschuetze.getX(),strg.bogenschuetze.getY(),300,this,strg);
        actors.add(Gegnerlaedtnachlinks);
        Gegnerlaedtnachlinks.once=true;
    }
    
    public void addGegnerlaedtnachrechts(){
        Gegnerlaedtnachrechts = new Sprite(gegnerlaedtnachrechts,strg.bogenschuetze.getX(),strg.bogenschuetze.getY(),300,this,strg);
        actors.add(Gegnerlaedtnachrechts);
        Gegnerlaedtnachrechts.once=true;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(51, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1000, 500));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
     
       if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
                strg.aendereHeldRichtung(1);           
       }
       
     
       if(evt.getKeyCode() == KeyEvent.VK_LEFT){
                strg.aendereHeldRichtung(2); 
                
       }
       
       
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_RIGHT ){
            strg.aendereHeldRichtung(0);
        }
        if(evt.getKeyCode() == KeyEvent.VK_SPACE){              
                strg.springenderRolf();
                
       }
    }//GEN-LAST:event_formKeyReleased

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
       
    }//GEN-LAST:event_formKeyTyped

    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
