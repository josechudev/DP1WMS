package com.dp1wms.model.tabu;

import java.util.ArrayList;

public class Almacen {

    private boolean almacen[][];
    private boolean nodos[][];
    private boolean[][] productos;
    private int ancho;
    private int alto;
    private ArrayList<Rack> racks;


    public static int MIN_LARGO = 8;
    public static int MAX_LARGO = 10;

    public Almacen(int ancho, int alto){

        this.ancho = ancho;
        this.alto = alto;

        this.almacen = new boolean[ancho][alto];
        this.nodos = new boolean[ancho][alto];
        this.setProductos(new boolean[ancho][alto]);

        for(int i = 0; i < ancho; i++){
            for(int j = 0; j < alto; j++){
                this.almacen[i][j] = false;
                this.nodos[i][j] = false;
                this.getProductos()[i][j] = false;

            }
        }
        this.racks = new ArrayList<Rack>();
    }


    public void imprimirRacks(){
        for(Rack rack: this.racks){
            rack.imprimir();
        }
    }



    public void imprimirAlmacen(){

        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {

                if(almacen[i][j]){
                    System.out.print("XX");
                } else {
                    if(productos[i][j]){
                        System.out.print("PP");
                    } else if (nodos[i][j]){
                        System.out.print("NN");
                    } else {
                        System.out.print("  ");
                    }
                }
            }
            System.out.println();
        }
    }





    public boolean[][] getNodos(){
        return this.nodos;
    }

    public void setNodos(boolean[][] nodos){
        for(int i = 0; i < this.ancho; i++){
            for(int j = 0; j < this.alto; j++){
                this.nodos[i][j] = nodos[i][j];
            }
        }
    }

    public boolean[][] getAlmacen(){
        return this.almacen;
    }

    public void setAlmacen(boolean[][] almacen){
        for(int i = 0; i < this.ancho; i++){
            for(int j = 0; j < this.alto; j++){
                this.almacen[i][j] = almacen[i][j];
            }
        }
    }

    public boolean estaOcupado(int i, int j){
        return this.almacen[i][j];
    }

    public ArrayList<Rack> getRacks() {
        return this.racks;
    }
    public void setRacks(ArrayList<Rack> racks){
        this.racks = racks;
    }

    public Rack getRackporId(int id){
        for (Rack rack: this.racks) {
            if(rack.getId_rack() == id){
                return rack;
            }
        }
        return this.racks.get(0);
    }

    public int getAncho(){
        return this.ancho;
    }

    public int getAlto(){
        return this.alto;
    }

    public void limpiarNodos(){
        this.nodos = new boolean[ancho][alto];
        for(int i = 0; i < ancho; i++){
            for(int j = 0; j < alto; j++){
                this.nodos[i][j] = false;
            }
        }
    }

    public void agregar_racks(){
        for (Rack rack: this.getRacks()) {

            if(rack.esHorizontal()){
                for (int i = rack.getPosIni().x; i <= rack.getPosFin().x; i++) {
                    almacen[i][rack.getPosFin().y] = true;
                }
            } else{
                for (int i = rack.getPosIni().y; i <= rack.getPosFin().y ; i++) {
                    almacen[rack.getPosIni().x][i] = true;
                }
            }
        }
    }

    public boolean[][] getProductos() {
        return productos;
    }

    public void setProductos(boolean[][] productos) {
        this.productos = productos;
    }
}
