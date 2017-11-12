package com.dp1wms.tabu;

import java.util.ArrayList;

public class ListaTabu {

    public ArrayList<TabuMovimiento> listaTabu;
    private int permanencia;
    private int tamanho;

    public ListaTabu (int tamanho, int permanencia){
        this.listaTabu = new ArrayList<TabuMovimiento>();
        this.tamanho = tamanho;
        this.permanencia = permanencia;
    }

    public boolean contieneMovimiento(int nodo1, int nodo2){
        for(TabuMovimiento mov: this.listaTabu){
            if(mov.igualA(nodo1, nodo2)){
                return true;
            }
        }
        return false;
    }

    public void agregarNodo(int nodo1, int nodo2){
        listaTabu.add(new TabuMovimiento(nodo1, nodo2, this.permanencia));
        if(listaTabu.size() > this.tamanho){
            listaTabu.remove(0);
        }
    }

    public void decrementarListaTabu(){
        ArrayList<TabuMovimiento> nuevaLista = new ArrayList<TabuMovimiento>();
        for(TabuMovimiento mov: this.listaTabu){
            mov.tiempo -= 1;
            if(mov.tiempo != 0){
                nuevaLista.add(mov);
            }
        }
        this.listaTabu = nuevaLista;
    }


    private class TabuMovimiento {
        public int nodo1;
        public int nodo2;
        public int tiempo;

        public TabuMovimiento(int nodoA, int nodoB, int tiempo){
            if (nodoA < nodoB){
                this.nodo1 = nodoA;
                this.nodo2 = nodoB;
            } else {
                this.nodo1 = nodoB;
                this.nodo2 = nodoA;
            }
            this.tiempo = tiempo;
        }

        public boolean igualA(int nodoA, int nodoB){
            if (nodoA < nodoB){
                return (this.nodo1 == nodoA && this.nodo2 == nodoB);
            } else {
                return (this.nodo1 == nodoB && this.nodo2 == nodoA);
            }

        }
    }

}
