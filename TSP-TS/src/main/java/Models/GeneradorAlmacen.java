package Models;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

public class GeneradorAlmacen {
    private static boolean[][] varBloques;
    private static Random varRandGenerator = new Random();
    
    private static String varName = "Almacen N 1";
    private static int varNumPrueba = 70;
    private static int varNumMinPrueba = 25;
    private static int varAumentoPrueba = 30;
    
    //Dimensiones del almacen
    public static int varAncho = 230 + 2;
    public static int varAltura = 150 + 2;
    
    private static int[] varPuerta = {0,varAltura/2};
    
    private static boolean[] varAreaValida = {false, false, false, false, false, false, false, false, false, false}; 
    private static int varNumArea = 6;
    private static int [][][] varArea = {{{1,1},{50,50}},
                            {{51,1},{170,50}},
                            {{171,1},{230,120}},
                            {{1,51},{120,150}},
                            {{121,51},{170,120}},
                            {{121,121},{230,150}}};

    
    //Datos del Rack
    private static int varTamMinRack = 10;
    private static int varTamAumentoRack = 10;
    
    //Datos  pruebas
    private static int[][][] varCoordenada = new int[100][100][2];
    private static int[] varCantCoordenada = new int[100];
    private static int varCentrado = 3;
    
    public static void generarCaso(){
        //Creamos el almacen, con racks y areas
        generarAlmacen();
        
        //Creamos las pruebas
        for(int k = 0; k < varNumPrueba; k++){
            int cantPedido = varNumMinPrueba + varRandGenerator.nextInt(varAumentoPrueba);
            varCantCoordenada[k] = cantPedido;
            crearPunto(k);
        }
        
        //Mostramos la data generada
        mostrarAlmacen();
        for(int k = 0; k < varNumPrueba; k++){
            mostrarListaCoord(k);
            mostrarCoordEnAlmacen(k);
        }
        
        //Invertimos para que concuerde
        invertirBloques();
    }
    
    private static void invertirBloques(){
        for (int i = 0; i < varAncho; i++) {
            for (int j = 0; j < varAltura; j++) {
                varBloques[i][j] = !varBloques[i][j];
            }
        }
    }
    
    //BEGIN - Generamos nuestro almacen
    private static void generarAlmacen() {

        varBloques = new boolean[varAncho][varAltura];
        
        //Creo el almacen, vacio y sin diferenciar las areas
        for (int i = 0; i < (varAncho); i++) {
            varBloques[i][0] = false;
            for (int j = 1; j < (varAltura -1); j++) {
                varBloques[i][j] = true;
            }
            varBloques[i][varAltura - 1] = false;
        }
        
        for(int j = 0; j < varAltura; j++){
            varBloques[0][j] = false;
        }
        
        for(int j = 0; j < varAltura; j++){
            varBloques[varAncho - 1][j] = false;
        }
        
        //Creamos la puerta
        varBloques[varPuerta[0]][varPuerta[1]] = true;
        
        //Llenamos las areas
        for(int i = 0; i < varNumArea; i++){
            llenarArea(varArea[i], i);
        }
        
        //Clausuramos el Area E
        cerrarArea(varArea[4], 4);
    }
    
    private static void llenarArea(int[][] dimensionAreas, int numArea){
        int posX, posY, sizeRack, direccionRack;
        int maxIteraciones = 100000;
        for(int i = 0; i < maxIteraciones; i++){
            int randomX = dimensionAreas[1][0] - dimensionAreas[0][0];
            if(randomX <= 0)
                randomX = 1;
            posX = (dimensionAreas[0][0] + varRandGenerator.nextInt(randomX) );
            if(posX <= dimensionAreas[0][0])
                posX = dimensionAreas[0][0] + 1;
            if(posX >= dimensionAreas[1][0])
                posX = dimensionAreas[1][0] - 1;
            
            int randomY = dimensionAreas[1][1] - dimensionAreas[0][1];
            if(randomY <= 0)
                randomY = 1;
            posY = (dimensionAreas[0][1] + varRandGenerator.nextInt(randomY) );
            if(posY <= dimensionAreas[0][1])
                posY = dimensionAreas[0][1] + 1;
            if(posY >= dimensionAreas[1][1])
                posY = dimensionAreas[1][1] - 1;
            
            if(!posicionValidadRack(posX, posY, 0))
                continue;
            
            sizeRack = varTamMinRack + varRandGenerator.nextInt(varTamAumentoRack);
            direccionRack = varRandGenerator.nextInt(2);
            
            switch (direccionRack){
                //ESTE
                case 0:
                    for(int k = 0; k < sizeRack; k++){
                        
                        if( (!posicionValidadRack(posX + k, posY, 1)) || ( (posX + k) >= dimensionAreas[1][0]) ){
                            break;
                        }
                        varBloques[posX + k][posY] = false;
                    }
                    break;
                //SUR
                case 1:
                    for(int k = 0; k < sizeRack; k++){
                        if( (!posicionValidadRack(posX, posY + k, 2)) || ( (posY + k) >= dimensionAreas[1][1]) ){
                            break;
                        }
                        varBloques[posX][posY + k] = false;
                    }
                    break;
                default :
                    break;
            }
        }
        varAreaValida[numArea] = true;
    }
    
    private static void cerrarArea(int[][] dimensionAreas, int numArea){
        for(int i = dimensionAreas[0][0]; i <= dimensionAreas[1][0]; i++){
            for(int j = dimensionAreas[0][1]; j <= dimensionAreas[1][1]; j++){
                varBloques[i][j] = false;
            }
        }
        varAreaValida[numArea] = false;
    }
    
    private static boolean posicionValidadRack(int posX, int posY, int direccion){
        switch (direccion){
            case 0:
                if( !varBloques[posX-1][posY-1] || !varBloques[posX-1][posY] || !varBloques[posX-1][posY+1] ||
                        !varBloques[posX][posY-1] || !varBloques[posX][posY] || !varBloques[posX][posY+1] ||
                        !varBloques[posX+1][posY-1] || !varBloques[posX+1][posY] || !varBloques[posX+1][posY+1] )
                    return false;
                break;
            case 1:
                if( !varBloques[posX-1][posY-1] || /*!varBloques[posX-1][posY] ||*/ !varBloques[posX-1][posY+1] ||
                        !varBloques[posX][posY-1] || !varBloques[posX][posY] || !varBloques[posX][posY+1] ||
                        !varBloques[posX+1][posY-1] || !varBloques[posX+1][posY] || !varBloques[posX+1][posY+1] )
                    return false;
                break;
            case 2:
                if( !varBloques[posX-1][posY-1] || !varBloques[posX-1][posY] || !varBloques[posX-1][posY+1] ||
                        /*!varBloques[posX][posY-1] ||*/!varBloques[posX][posY] || !varBloques[posX][posY+1] ||
                        !varBloques[posX+1][posY-1] || !varBloques[posX+1][posY] || !varBloques[posX+1][posY+1] )
                    return false;
                break;
            default:
                break;
        }
        return true;
    }
    //END - Generamos nuestro almacen
    
    //BEGIN - Generamos nuestras pruebas
    private static void crearPunto(int numCaso){
        for(int i = 0; i < varCantCoordenada[numCaso] ;){
            
            int areaRandom = varRandGenerator.nextInt(varNumArea);
            if( !varAreaValida[areaRandom] )
                continue;
            int posX = varArea[areaRandom][0][0] + varRandGenerator.nextInt(varArea[areaRandom][1][0] - varArea[areaRandom][0][0]);
            int posY = varArea[areaRandom][0][1] + varRandGenerator.nextInt(varArea[areaRandom][1][1] - varArea[areaRandom][0][1]);
            
            if(nuevaCoordenadaValida(i, posX, posY,varCoordenada[numCaso], varArea[areaRandom])){
                varCoordenada[numCaso][i][0] = posX;
                varCoordenada[numCaso][i][1] = posY;
                i++;
            }
        }
    }
    
    private static boolean nuevaCoordenadaValida(int numIteracion, int coordX, int coordY, int[][] coordenadaAcumulada, int[][] limites){
        if(! varBloques[coordX][coordY] )
            return false;
        
        if( (coordX <  (limites[0][0] + varCentrado) ) || (coordY <  (limites[0][1] + varCentrado) ) ||
                (coordX >  (limites[1][0] - varCentrado) ) || (coordY >  (limites[1][1] - varCentrado) ) )
            return false;
        
        if( !(
                (!varBloques[coordX+1][coordY] && varBloques[coordX+2][coordY]) ||
                (!varBloques[coordX][coordY+1] && varBloques[coordX][coordY+2]) ||
                (!varBloques[coordX-1][coordY] && varBloques[coordX-2][coordY]) ||
                (!varBloques[coordX][coordY-1] && varBloques[coordX][coordY-2]) )
                )
            return false;
        
        for(int i = 0; i < numIteracion; i++)
            if( (coordenadaAcumulada[i][0] == coordX) && (coordenadaAcumulada[i][1] == coordY) )
                return false;
        return true;
    }
    //END - Generamos nuestras pruebas
    
    //BEGIN - Mostramos la data generada
    private static void mostrarAlmacen(){
        try{
            PrintStream out = new PrintStream(new FileOutputStream( varName + ".csv"));
            System.setOut(out);
            for (int j = 0; j < varAltura; j++) {
                for (int i = 0; i < varAncho; i++) {
                    if(varBloques[i][j])
                        out.print(" ");
                    else
                        out.print("\u25A0");
                }
                out.println("");
            }
        }
        catch(IOException e1){
            System.out.println("Error during writing Almacen");
        }
        
    }
    
    private static void mostrarListaCoord(int numCaso){
        try{
            PrintStream out = new PrintStream(new FileOutputStream( varName + " - Coordenadas Caso " + (numCaso + 1) + ".csv"));
            System.setOut(out);
            out.println(varCantCoordenada[numCaso]);
            for (int i = 0; i < varCantCoordenada[numCaso]; i++) {
                out.println(varCoordenada[numCaso][i][0] + "," + varCoordenada[numCaso][i][1] );
            }
        }
        catch(IOException e1){
            System.out.println("Error during writing lista de coordenadas");
        }
    }
    
    private static void mostrarCoordEnAlmacen(int numCaso){
        try{
            PrintStream out = new PrintStream(new FileOutputStream( varName + " - Pos. relativa Caso " + (numCaso + 1) + ".csv"));
            System.setOut(out);
            boolean saltar;
            for (int j = 0; j < varAltura; j++) {
                for (int i = 0; i < varAncho; i++) {
                    saltar = false;
                    for(int k = 0; k < varCantCoordenada[numCaso]; k++){
                        if( (varCoordenada[numCaso][k][0] == i) && (varCoordenada[numCaso][k][1] == j) ){
                            out.print("X");
                            saltar = true;
                        }
                    }
                    if(!saltar){
                        if(varBloques[i][j])
                            out.print(" ");
                        else
                            out.print("\u25A0");
                    }
                }
                out.println("");
            }
        }
        catch(IOException e1){
            System.out.println("Error during writing Posicion relativa de coordenadas - Caso N " + (numCaso+1));
        }
    }
    //END - Mostramos la data generada
    
    //BEGIN - Interaccion con el resto del programa
    public static boolean[][] _almacen(){
        return varBloques;
    }
    
    public static int _numCasos(){
        return varNumPrueba;
    }
    
    public static int[][] _coordCaso(int numCaso){
        return varCoordenada[numCaso];
    }
    
    public static int _cantCoordCaso(int numCaso){
        return varCantCoordenada[numCaso];
    }
    //END - Interaccion con el resto del programa
}
