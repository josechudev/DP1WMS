package com.dp1wms.tabu;

import com.dp1wms.model.tabu.Nodo;

import java.util.ArrayList;

public class Segmento {
    public int distancia;
    public ArrayList<Nodo> camino;

    public Segmento(){
        this.camino = new ArrayList<>();
        this.distancia = 0;
    }
    public void calcularDistancia(){
        this.distancia = 0;
        for(int i = 0; i < camino.size() - 1; i++){
            Nodo a = camino.get(i);
            Nodo b = camino.get(i+1);
            this.distancia += this.distanciaEntreNodos(a,b);
        }
    }

    public Nodo ultimoNodo(){
        if(this.camino.size() == 0) return null;
        return this.camino.get(this.camino.size() - 1);
    }

    public int distanciaEntreNodos(Nodo a, Nodo b){
        return Math.abs(a.x - b.x)  + Math.abs(a.y - b.y);
    }
}
