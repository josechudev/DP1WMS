package Utils;

import java.awt.*;
import java.util.ArrayList;

public class NearestInsertion {

    private int[][] distancias;
    private long duracion;

    private ArrayList<NearestInsertionNodo> tour;
    private ArrayList<NearestInsertionNodo> noVisitados;

    private static int DIST_MAX = Integer.MAX_VALUE;

    public NearestInsertion(int[][] distancias, int nodo_inicio){
        this.distancias = distancias;

        this.duracion = 0;

        NearestInsertionNodo inicio = new NearestInsertionNodo(nodo_inicio);

        //inicializar tour
        this.tour = new ArrayList<NearestInsertionNodo>();
        this.tour.add(inicio);

        //inicializar no-visitados
        this.noVisitados = new ArrayList<NearestInsertionNodo>();
        for(int i = 0; i < distancias.length; i++){
            if(i != nodo_inicio){
                this.noVisitados.add(new NearestInsertionNodo(i));
            }
        }

        long t0 = System.currentTimeMillis(); //tiempo de inicion
        //encontrar el 2do nodo más cercano para formar el tour inicial
        NearestInsertionNodo nodoCercano = this.noVisitados.get(0);
        int menorDist = distanciaEntre(inicio, nodoCercano);
        for(NearestInsertionNodo nodoCandidato: this.noVisitados){
            int d = distanciaEntre(inicio, nodoCandidato);
            if(d < menorDist){
                nodoCercano = nodoCandidato;
                menorDist = d;
            }
        }
        this.tour.add(nodoCercano);
        this.tour.add(inicio);
        int ind = this.noVisitados.indexOf(nodoCercano);
        this.noVisitados.remove(nodoCercano);
        this.duracion = System.currentTimeMillis() - t0; //tiempo fin de inicializacion del algoritmo
    }

    public void generar(){
        long t0 = System.currentTimeMillis(); //tiempo inicio de iteracion
        while(this.noVisitados.size() > 0){ //iterar mientras no se hayan visitados todos los nodos
            NearestInsertionNodo nodoCercano = encontrarNodoCercanoTour();

            NearestInsertionNodo[] camino = encontrarMejorCamino(nodoCercano);

            insertarNuevoNodo(camino[0], camino[1], nodoCercano);
        }
        this.duracion += (System.currentTimeMillis() - t0); //tiempo fin de iteracion, suma al anterior
    }

    /**
     * Encuentra el nodo más cercano al tour que no ha sido visitado
     * @return NearestInsertNodo
     */
    private NearestInsertionNodo encontrarNodoCercanoTour(){
        NearestInsertionNodo nodoCercano = this.noVisitados.get(0);
        int menorDistancia = DIST_MAX;

        for(NearestInsertionNodo nodoNoVistado: this.noVisitados){
            for(NearestInsertionNodo nodoTour: this.tour){
                int d = distanciaEntre(nodoTour, nodoNoVistado);
                if(menorDistancia > d){
                    nodoCercano = nodoNoVistado;
                    menorDistancia = d;
                }
            }
        }
        return nodoCercano;
    }

    /**
     * Encuentra el mejor camino (edge) en el cual se debería insertar el nodo más cercano
     * @param nodoK: NearestInsertionNodo
     * @return NearestInsertNodo[]
     */
    private NearestInsertionNodo[] encontrarMejorCamino(NearestInsertionNodo nodoK){
        NearestInsertionNodo[] camino = new NearestInsertionNodo[2];
        int distanciaExtra = DIST_MAX;

        for(int i = 0; i < this.tour.size() - 1; i++){
            NearestInsertionNodo nodoI = this.tour.get(i);
            NearestInsertionNodo nodoJ = this.tour.get(i+1);
            int d = distanciaEntre(nodoI,nodoK) + distanciaEntre(nodoJ, nodoK) - distanciaEntre(nodoI, nodoJ);
            if(distanciaExtra > d){
                distanciaExtra = d;
                camino[0] = nodoI;
                camino[1] = nodoJ;
            }
        }
        return camino;
    }

    /**
     * Insert un nuevo nodo K al tour entre I y J
     * Elimina nodo K de los no visitados
     * @param nodoI : NearestInsertionNodo
     * @param nodoJ : NearestInsertionNodo
     * @param nodoK : NearestInsertionNodo
     */
    private void insertarNuevoNodo(NearestInsertionNodo nodoI, NearestInsertionNodo nodoJ, NearestInsertionNodo nodoK){
        int indexI = this.tour.indexOf(nodoI);

        this.tour.add(indexI + 1, nodoK);

        this.noVisitados.remove(nodoK);
    }

    /**
     * Calcula la distancia entre nodo A y nodo B
     * @param nodoA : NearestInsertionNodo
     * @param nodoB : NearestInsertionNodo
     * @return
     */
    private int distanciaEntre(NearestInsertionNodo nodoA, NearestInsertionNodo nodoB){
        return this.distancias[nodoA.valor][nodoB.valor];
    }

    /**
     * Suma de distancias de caminos
     * @return
     */
    public int obtenerCosto (){
        int costo = 0;
        for(int i = 0; i < this.tour.size()-1; i++){
            NearestInsertionNodo nodoA = this.tour.get(i);
            NearestInsertionNodo nodoB = this.tour.get(i+1);
            costo += distanciaEntre(nodoA, nodoB);
        }
        return costo;
    }

    /**
     * Obtener el camino construido / solucion
     */
    public int[] obtenerSolucion(){
        int[] solucion = new int[this.tour.size()];
        for(int i = 0; i < this.tour.size(); i++){
            solucion[i] = this.tour.get(i).valor;
        }
        return solucion;
    }

    /**
     * Obtiene el tiempo de que tomó en construir el camino/tour
     * @return long
     */
    public long obtenerDuracion(){
        return this.duracion;
    }

    private class NearestInsertionNodo{

        int valor;

        public NearestInsertionNodo(int valor){
            this.valor = valor;
        }

        @Override
        public boolean equals(Object o) {
            if(o.getClass() != this.getClass()){
                return false;
            }
            NearestInsertionNodo nodo = (NearestInsertionNodo) o;
            return nodo.valor == this.valor;
        }

    }
}
