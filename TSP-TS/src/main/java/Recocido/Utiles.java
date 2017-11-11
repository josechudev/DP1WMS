package Recocido;

import java.util.Random;

public class Utiles {


    public static void imprimirCamino(int [] solucion){
        for (int i = 0; i < solucion.length; i++) {
            System.out.print(i);
            System.out.print("  ");
        }
    }

    public static double probabilidadAceptacion(double fObjActual, double fObjNueva, double temperatura){
        return Math.pow(Math.E, (fObjActual - fObjNueva)/temperatura);
    }

    public static double randomDouble()
    {
        Random r = new Random();
        return r.nextInt(1000) / 1000.0;
    }

    public static int [] copiarCamino(int[] solucion){
        int [] solucionCopia = new int[solucion.length];
        System.arraycopy(solucion, 0, solucionCopia, 0, solucion.length);
        return solucionCopia;
    }

}
