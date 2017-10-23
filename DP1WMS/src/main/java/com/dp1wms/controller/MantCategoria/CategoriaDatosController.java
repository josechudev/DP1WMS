package com.dp1wms.controller.CategoriaController;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.model.CategoriaProducto;
import org.springframework.stereotype.Component;

@Component
public class CategoriaDatosController implements FxmlController{
    private CategoriaController v_parentController;

    @Override
    public void initialize() {

    }

    public void _setData(CategoriaProducto categoriaProducto, int i) {
    }

    public void setV_parentController(CategoriaController v_parentController) {
        this.v_parentController = v_parentController;
    }
}
