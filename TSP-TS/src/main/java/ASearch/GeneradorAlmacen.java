package ASearch;

import java.util.Random;

public class GeneradorAlmacen {
    private static boolean[][] bloques;
    private static boolean[][] visitados;
    private static Random rand = new Random();

    public static boolean[][] generar(int ancho, int alto) {
        int anchoBase = ancho * 2 + 1;
        int alturaBase = alto * 2 + 1;

        bloques = new boolean[anchoBase][alturaBase];
        visitados = new boolean[ancho + 2][alto + 2];

        for (int i = 0; i < bloques.length; i++) {
            for (int j = 0; j < bloques[0].length; j++) {
                bloques[i][j] = true;
            }
        }
        for (int i = 0; i < visitados.length; i++) {
            for (int j = 0; j < visitados[0].length; j++) {
                visitados[i][j] = true;
            }
        }
        gen(0, 0);
        return bloques;
    }

    private static void gen(int xPos, int yPos) {
        int x = xPos * 2 + 1;
        int y = yPos * 2 + 1;
        bloques[x][y] = false;
        visitados[xPos + 1][yPos + 1] = true;
        while (!visitados[xPos][yPos + 1] || !visitados[xPos + 2][yPos + 1] || !visitados[xPos + 1][yPos]) {
            float num = rand.nextFloat();
            if (num < 0.25F && !visitados[xPos][yPos + 1]) {
                bloques[x - 1][y] = false;
                gen(xPos - 1, yPos);
            } else if (num < 0.5F && num >= 0.28F && !visitados[xPos + 2][yPos + 1]) {
                bloques[x + 1][y] = false;
                gen(xPos + 1, yPos);
            } else if (num >= 0.5F && num < 0.75F && !visitados[xPos + 1][yPos + 2]) {
                bloques[x][y + 1] = false;
                gen(xPos, yPos + 1);
            } else if (num < 0.25F && !visitados[xPos][yPos + 1]) {
                bloques[x][y - 1] = false;
                gen(xPos, yPos - 1);
            }

        }
    }
}
