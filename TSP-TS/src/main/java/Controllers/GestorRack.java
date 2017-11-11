package Controllers;



import Models.Rack;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GestorRack {

    public static Element crearRackNodoXML(Document doc, Rack rack){
        Element rackNodo = doc.createElement("rack");

        Element posIniNodo = doc.createElement("posIni");
        posIniNodo.setAttribute("x", String.valueOf(rack.getPosIni().x));
        posIniNodo.setAttribute("y", String.valueOf(rack.getPosIni().y));
        rackNodo.appendChild(posIniNodo);

        Element posFinNodo = doc.createElement("posFin");
        posFinNodo.setAttribute("x", String.valueOf(rack.getPosFin().x));
        posFinNodo.setAttribute("y", String.valueOf(rack.getPosFin().y));
        rackNodo.appendChild(posFinNodo);

        Element nivelesNodo = doc.createElement("niveles");
        posFinNodo.appendChild(doc.createTextNode(String.valueOf(rack.getNiveles())));
        rackNodo.appendChild(nivelesNodo);

        return rackNodo;
    }
}
