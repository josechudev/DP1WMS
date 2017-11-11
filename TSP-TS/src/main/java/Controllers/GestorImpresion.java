package Controllers;

import Models.Almacen;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GestorImpresion {
    public static void imprimirAlmancen(Almacen alm, List<Point> posNodos, int[] sol2) {
        List<Integer> aux = new ArrayList<Integer>();
        for (int i = 0; i < sol2.length; i++) {
            aux.add(sol2[i]);
        }
        for (int i = 0; i < alm.getAncho(); i++) {
            for (int j = 0; j < alm.getAlto(); j++) {
                if(alm.getAlmacen()[i][j]==false){
                    if(posNodos.contains(new Point(i,j))){
                        int pos = posNodos.indexOf(new Point(i,j));
                        System.out.printf("%2d", aux.indexOf(pos));
                    }
                    else {
                        System.out.print(" _");
                    }
                }
                else if(alm.getAlmacen()[i][j]==true){
                    System.out.print(" x");
                }
            }
            System.out.println();
        }
    }
}
