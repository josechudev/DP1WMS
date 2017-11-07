package com.dp1wms.dao;

import com.dp1wms.model.Empleado;
import com.dp1wms.model.Seccion;
import com.dp1wms.model.TipoEmpleado;
import com.dp1wms.model.UsuarioModel.UsuarioXEmpleado;

import java.util.ArrayList;
import java.util.List;

public interface RepositoryMantTipoEmpleado {

    List<TipoEmpleado> selectAllTipoEmpleado();

    void createTipoEmpleado(TipoEmpleado auxTipoEmpleado);

    void updateTipoEmpleado(TipoEmpleado auxTipoEmpleado);

    void deleteTipoEmpleado(TipoEmpleado auxTipoEmpleado);

    TipoEmpleado obtenerTipoEmpleadoPorIdTipo(Long auxIdTipo);

    TipoEmpleado obtenerTipoEmpleadoPorDescripcion(String auxDescripcion);

    List<TipoEmpleado> buscarTipoEmpleado(String descripcion);

    List<Seccion> obtenerTodasLasSecciones();

    List<Seccion> obtenerSeccionesDeTipoEmpleado(long idTipoEmpleado);

    boolean actualizarPermisos(long idTipoEmpleado,
                               ArrayList<Seccion> inserts,
                               ArrayList<Seccion> updates,
                               ArrayList<Seccion> deletes);

    TipoEmpleado crearTipoEmpleado(TipoEmpleado tipoEmpleado);
}
