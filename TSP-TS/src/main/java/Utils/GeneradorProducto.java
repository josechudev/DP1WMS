package Utils;

import Models.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneradorProducto {

    private List<Producto> productos;

    private Random random;
    /**
     *
     * @param numProductos
     */
    public GeneradorProducto(int numProductos, int minCant, int maxCant){
        this.productos = new ArrayList<Producto>();
        this.random = new Random(maxCant);

        random.nextGaussian();
    }
}
