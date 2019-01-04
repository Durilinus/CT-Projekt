/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jachst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stefan
 */
public class Leser {
    File file;
    int height =10;
    int width = 20;
    int[][] map;

    public Leser(File f) {
        file = f;
        map = new int[width][height];
        main();
    }

    public void main() {
        try {
            Scanner s = new Scanner(file);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (s.hasNextInt()) {
                        map[x][y] = s.nextInt();
                        
                    }
                }
            }
            s.close();
        } catch (FileNotFoundException ex) {
            System.out.print("Scanner Fehler/ datei nicht gefunden");
            Logger.getLogger(Leser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public int gibMap(int x, int y){
        return map[x][y];
    }
}
