package com.dp1wms.controller.racks;

import com.dp1wms.controller.superficies.SuperficieGridController;
import com.dp1wms.model.Rack;
import com.dp1wms.util.RackUtil;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class GridRacksController extends SuperficieGridController {

    private VistaRacksController vistaRacksController;

    private Color RACK_PIXEL_COLOR = Color.BLUE;
    private Rack[][] distRacks;

    private Rack rackSeleccionado;
    private Point2D posicionActual;

    GridRacksController(int uLargo, int uAncho, VistaRacksController vistaRacksController){
        super(uLargo, uAncho);
        this.vistaRacksController = vistaRacksController;
        this.distRacks = new Rack[uLargo][uAncho];
        this.posicionActual = new Point2D(0,0);
    }

    @Override
    public void cellClicked(MouseEvent event, Rectangle rectangle){
        if (rackSeleccionado != null){
            desmarcarRack(rackSeleccionado);
        } else {
            Rectangle prevRect = (Rectangle) nodeGrid[(int) posicionActual.getX()][(int) posicionActual.getY()];
            prevRect.setStroke(Color.GRAY);
            prevRect.setStrokeWidth(0.5);
        }
        if (distRacks[getColumnIndex(rectangle)][getRowIndex(rectangle)] != null){
            marcarRack(distRacks[getColumnIndex(rectangle)][getRowIndex(rectangle)]);
        } else {
            marcarSeleccion(rectangle);
        }
        actualizarPosicion(rectangle);
    }

    void anadirRack(Rack rack){
        ArrayList<Point2D> posiciones = RackUtil.getListaDePosicionesOcupadas(rack);
        int x, y;

        for(Point2D p: posiciones){
            x = (int) p.getX();
            y = (int) p.getY();

            dibujarRack(x, y);
            distRacks[x][y] = rack;
        }
    }

    private void dibujarRack(int i, int j){
        this.remove(i, j);
        Rectangle r = new Rectangle();
        r.setFill(RACK_PIXEL_COLOR);
        r.setHeight(UNIT_PIXEL_WIDTH);
        r.setWidth(UNIT_PIXEL_WIDTH);
        r.setStroke(Color.GRAY);
        this.add(r, i, j);
    }

    private void marcarSeleccion(Rectangle rectangle){
        rectangle.setStroke(Color.AQUAMARINE);
        rectangle.setStrokeWidth(1);
        vistaRacksController.rackSeleccionado(false);
    }

    private void marcarRack(Rack rack){
        ArrayList<Point2D> posiciones = RackUtil.getListaDePosicionesOcupadas(rack);
        int x, y;
        for (Point2D p: posiciones){
            x = (int) p.getX();
            y = (int) p.getY();
            Rectangle r = (Rectangle) nodeGrid[x][y];
            r.setFill(Color.AQUAMARINE);
        }
        rackSeleccionado = rack;
        vistaRacksController.rackSeleccionado(true);
    }

    private void desmarcarRack(Rack rack){
        ArrayList<Point2D> posiciones = RackUtil.getListaDePosicionesOcupadas(rack);
        int x, y;
        for (Point2D p: posiciones){
            x = (int) p.getX();
            y = (int) p.getY();
            Rectangle r = (Rectangle) nodeGrid[x][y];
            r.setFill(Color.BLUE);
        }
        rackSeleccionado = null;
        vistaRacksController.rackSeleccionado(false);
    }

    private void actualizarPosicion(Rectangle rectangle){
        posicionActual = new Point2D(getColumnIndex(rectangle), getRowIndex(rectangle));
        vistaRacksController.actualizarPosicion((int) posicionActual.getX(), (int) posicionActual.getY());
    }

    void eliminarRack(Rack rack){
        ArrayList<Point2D> posiciones = RackUtil.getListaDePosicionesOcupadas(rack);
        int x, y;
        for (Point2D p: posiciones){
            x = (int) p.getX();
            y = (int) p.getY();
            remove(x, y);
            resetTile(x, y);
            distRacks[x][y] = null;
        }
        rackSeleccionado = null;
    }

    Rack getRackSeleccionado(){
        return rackSeleccionado;
    }

}
