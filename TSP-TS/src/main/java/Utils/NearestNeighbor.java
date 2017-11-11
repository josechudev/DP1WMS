package Utils;

import java.util.ArrayList;

public class NearestNeighbor {

    private long duracion;
    private int[][] distancias;
    private ArrayList<NearestNeighborNodo> solucion;
    private ArrayList<NearestNeighborNodo> noVisitados;
    private static int DIST_MAX = Integer.MAX_VALUE;

    private class NearestNeighborNodo{
        public int valor;

        public NearestNeighborNodo(int valor){
            this.valor = valor;
        }

        @Override
        public boolean equals(Object o) {
            if(o.getClass() != this.getClass()){
                return false;
            }
            NearestNeighborNodo nodo = (NearestNeighborNodo) o;
            return nodo.valor == this.valor;
        }
    }

    public NearestNeighbor(int[][] distancias, int inicio){

        this.distancias = distancias;
        this.solucion = new ArrayList<NearestNeighborNodo>();
        this.noVisitados = new ArrayList<NearestNeighborNodo>();

        this.solucion.add(new NearestNeighborNodo(inicio));
        for(int i = 0; i < distancias.length; i++){
            if (inicio != i){
                this.noVisitados.add(new NearestNeighborNodo(i));
            }
        }
    }

    public void generar(){
        long t0 = System.currentTimeMillis();

        NearestNeighborNodo nodoActual = this.solucion.get(0);
        while(this.noVisitados.size() > 0){
            NearestNeighborNodo nodoMasCerca = encontrarCaminoMasCerca(nodoActual);
            this.solucion.add(nodoMasCerca);
            nodoActual = nodoMasCerca;
            this.noVisitados.remove(nodoMasCerca);
        }

        NearestNeighborNodo nodoInicio = this.solucion.get(0);
        this.solucion.add(nodoInicio);
        this.duracion = System.currentTimeMillis() - t0;
    }

    private NearestNeighborNodo encontrarCaminoMasCerca(NearestNeighborNodo nodoA){
        NearestNeighborNodo nodoMasCerca = this.noVisitados.get(0);
        int distanciaMasCorta = DIST_MAX;
        for(NearestNeighborNodo nodoCandidato: this.noVisitados){
            int nuevaDistancia = distanciaEntre(nodoCandidato, nodoA);
            if(nuevaDistancia < distanciaMasCorta){
                distanciaMasCorta = nuevaDistancia;
                nodoMasCerca = nodoCandidato;
            }
        }
        return nodoMasCerca;
    }

    private int distanciaEntre(NearestNeighborNodo nodoA, NearestNeighborNodo nodoB){
        return this.distancias[nodoA.valor][nodoB.valor];
    }

    public long obtenerDuracion(){
        return this.duracion;
    }

    public int obtenerCosto(){
        int costo = 0;
        for(int i = 0; i < this.solucion.size() - 1; i++){
            NearestNeighborNodo nodoA = this.solucion.get(i);
            NearestNeighborNodo nodoB = this.solucion.get(i+1);
            costo += distanciaEntre(nodoA, nodoB);
        }
        return costo;
    }

    public int[] obtenerSolucion(){
        int[] solucion = new int[this.solucion.size()];
        for(int i = 0; i < this.solucion.size(); i++){
            solucion[i] = this.solucion.get(i).valor;
        }
        return solucion;
    }
}
