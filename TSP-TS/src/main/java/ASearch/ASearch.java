package ASearch;


import java.util.ArrayList;
import java.util.List;

public class ASearch {

    public static boolean esquinas = true;

    private static Nodo salida;
    private static int[][] puntajeG;
    private static int[][] puntajeH;
    private static int[][] puntajeF;
    private static Nodo[][] origen;
    private static boolean[][] muros;

    public static Nodo convertirNodo(int x, int y) {
        return new Nodo(x, y);
    }

    public static List<Nodo> generar(int inicioX, int inicioY, int finX, int finY, boolean[][] murosAlmacen) {
        return (generar(convertirNodo(inicioX, inicioY), convertirNodo(finX, finY), murosAlmacen));
    }

    private static List<Nodo> generar(Nodo inicio, Nodo fin, boolean[][] murosAlmacen) {
        List<Nodo> nodosAbiertos = new ArrayList<Nodo>();
        List<Nodo> nodosCerrados = new ArrayList<Nodo>();
        muros = murosAlmacen;

        puntajeG = new int[muros.length][muros[0].length];

        puntajeF = new int[muros.length][muros[0].length];

        puntajeH = new int[muros.length][muros[0].length];

        origen = new Nodo[muros.length][muros[0].length];

        nodosAbiertos.add(inicio);

        puntajeG[inicio.x][inicio.y] = 0;
        puntajeH[inicio.x][inicio.y] = calcularHeuristica(inicio);
        puntajeF[inicio.x][inicio.y] = puntajeH[inicio.x][inicio.y];

        while (nodosAbiertos.size() > 0) {
            Nodo actual = menorNodo(nodosAbiertos);
            if (actual == null) break;
            if (actual == fin) return recontruirCamino(actual);

            nodosAbiertos.remove(actual);
            nodosCerrados.add(actual);

            List<Nodo> vecinos = obtenerVecinos(actual);
            for (Nodo n : vecinos) {
                if (nodosCerrados.contains(n)) continue;

                int tempPuntajeG = puntajeG[actual.x][actual.y] + distanciaEntre(n, actual);

                boolean seguir = false;

                if (!nodosAbiertos.contains(n)) {
                    nodosAbiertos.add(n);
                    seguir = true;
                } else if (tempPuntajeG < puntajeG[n.x][n.y]) seguir = true;

                if (seguir) {
                    origen[n.x][n.y] = actual;
                    puntajeG[n.x][n.y] = tempPuntajeG;
                    puntajeH[n.x][n.y] = calcularHeuristica(n);
                    puntajeF[n.x][n.y] = puntajeG[n.x][n.y] + puntajeH[n.x][n.y];
                }
            }
        }
        return new ArrayList<Nodo>();
    }

    private static List<Nodo> obtenerVecinos(Nodo n) {
        List<Nodo> encontrado = new ArrayList<Nodo>();
        if (!muros[n.x + 1][n.y]) encontrado.add(convertirNodo(n.x + 1, n.y));
        if (!muros[n.x - 1][n.y]) encontrado.add(convertirNodo(n.x - 1, n.y));
        if (!muros[n.x][n.y + 1]) encontrado.add(convertirNodo(n.x, n.y + 1));
        if (!muros[n.x][n.y - 1]) encontrado.add(convertirNodo(n.x, n.y - 1));
        if (esquinas) {
            if (!muros[n.x + 1][n.y + 1] && (!muros[n.x + 1][n.y]) || !muros[n.x][n.y + 1])
                encontrado.add(convertirNodo(n.x + 1, n.y + 1));
            if (!muros[n.x - 1][n.y + 1] && (!muros[n.x - 1][n.y]) || !muros[n.x][n.y + 1])
                encontrado.add(convertirNodo(n.x - 1, n.y + 1));
            if (!muros[n.x - 1][n.y - 1] && (!muros[n.x - 1][n.y]) || !muros[n.x][n.y - 1])
                encontrado.add(convertirNodo(n.x - 1, n.y - 1));
            if (!muros[n.x + 1][n.y - 1] && (!muros[n.x + 1][n.y]) || !muros[n.x][n.y - 1])
                encontrado.add(convertirNodo(n.x + 1, n.y - 1));
        } else {
            if (!muros[n.x + 1][n.y + 1] && (!muros[n.x + 1][n.y]) && !muros[n.x][n.y + 1])
                encontrado.add(convertirNodo(n.x + 1, n.y + 1));
            if (!muros[n.x - 1][n.y + 1] && (!muros[n.x - 1][n.y]) && !muros[n.x][n.y + 1])
                encontrado.add(convertirNodo(n.x + 1, n.y + 1));
            if (!muros[n.x - 1][n.y - 1] && (!muros[n.x - 1][n.y]) && !muros[n.x][n.y - 1])
                encontrado.add(convertirNodo(n.x + 1, n.y + 1));
            if (!muros[n.x + 1][n.y - 1] && (!muros[n.x + 1][n.y]) && !muros[n.x][n.y - 1])
                encontrado.add(convertirNodo(n.x + 1, n.y + 1));

        }
        return encontrado;

    }

    private static Nodo menorNodo(List<Nodo> nodosAbiertos) {
        int menor = -1;
        Nodo encontrado = null;
        for (Nodo n : nodosAbiertos) {
            int dist = origen[n.x][n.y] == null ? -1 : puntajeG[origen[n.x][n.y].x][origen[n.x][n.y].y]
                    + distanciaEntre(n, origen[n.x][n.y]) + calcularHeuristica(n);
            if (dist < menor || menor == -1) {
                menor = dist;
                encontrado = n;
            }
        }
        return encontrado;

    }

    private static List<Nodo> recontruirCamino(Nodo n) {
        if (origen[n.x][n.y] != null) {
            List<Nodo> camino = recontruirCamino(origen[n.x][n.y]);
            camino.add(n);
            return camino;
        } else {
            List<Nodo> camino = new ArrayList<Nodo>();
            camino.add(n);
            return camino;
        }
    }

    private static int distanciaEntre(Nodo n1, Nodo n2) {
        return (int) Math.round(10 * Math.sqrt(Math.pow(n1.x - n2.x, 2) + Math.pow(n1.y - n2.y, 2)));
    }

    private static int calcularHeuristica(Nodo inicio) {
        return 10 * (Math.abs(inicio.x - salida.x) + Math.abs(inicio.y - salida.y));
    }

}
