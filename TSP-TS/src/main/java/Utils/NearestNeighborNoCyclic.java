package Utils;

import java.util.ArrayList;

public class NearestNeighborNoCyclic {

    private long duracion;
    private int[][] distancias;
    private int[] nodos;
    private int[][] distanciasNodos;

    private static int DIST_MAX = Integer.MAX_VALUE;
    private ArrayList<NearestNeighborNodo> nodosNN;

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

    public NearestNeighborNoCyclic(int[][] distancias, int[] nodos){

        this.distancias = distancias;
        this.distanciasNodos = new int[nodos.length][nodos.length];
        this.nodosNN = new ArrayList<NearestNeighborNodo>();

        for(int i = 0; i < nodos.length; i++){
            this.distanciasNodos[i][i] = 0;
        }
        for(int i = 0; i < nodos.length; i++){
            this.nodosNN.add(new NearestNeighborNodo(nodos[i]));
        }
    }

    public void generar(){
        long t0 = System.currentTimeMillis();

        for (int i = 0; i < this.nodosNN.size(); i++) {
            for (int j = i+1; j < this.nodosNN.size(); j++) {
                NearestNeighborNodo nodoInicio = this.nodosNN.get(i);
                NearestNeighborNodo nodoFin = this.nodosNN.get(j);
                ArrayList<NearestNeighborNodo> solucion = new ArrayList<NearestNeighborNodo>();
                ArrayList<NearestNeighborNodo> noVisitados = new ArrayList<NearestNeighborNodo>();
                solucion.add(nodoInicio);
                for(NearestNeighborNodo n: this.nodosNN){
                    noVisitados.add(n);
                }
                noVisitados.remove(nodoInicio);

                NearestNeighborNodo nodoActual = solucion.get(0);
                while(nodoActual != nodoFin){
                    NearestNeighborNodo nodoMasCerca = encontrarCaminoMasCerca(nodoActual, noVisitados);
                    solucion.add(nodoMasCerca);
                    nodoActual = nodoMasCerca;
                    noVisitados.remove(nodoMasCerca);
                }
                int distancia = obtenerCosto(solucion);
                this.distanciasNodos[i][j] = distancia;
                this.distanciasNodos[j][i] = distancia;
            }
        }

        this.duracion = System.currentTimeMillis() - t0;
    }

    private NearestNeighborNodo encontrarCaminoMasCerca(NearestNeighborNodo nodoA,
                                                        ArrayList<NearestNeighborNodo> noVisitados){
        NearestNeighborNodo nodoMasCerca = noVisitados.get(0);
        int distanciaMasCorta = DIST_MAX;
        for(NearestNeighborNodo nodoCandidato: noVisitados){
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

    private int obtenerCosto(ArrayList<NearestNeighborNodo> solucion){
        int costo = 0;
        for(int i = 0; i < solucion.size() - 1; i++){
            NearestNeighborNodo nodoA = solucion.get(i);
            NearestNeighborNodo nodoB = solucion.get(i+1);
            costo += distanciaEntre(nodoA, nodoB);
        }
        return costo;
    }

    public int[][] obtenerMatrizNodos(){
        int[][] mat = new int[this.distanciasNodos.length][this.distanciasNodos.length];
        for(int i = 0; i < this.distanciasNodos.length; i++){
            for (int j = 0; j < this.distanciasNodos.length; j++) {
                mat[i][j] = this.distanciasNodos[i][j];
            }
        }
        return mat;
    }
}
