package com.dp1wms.dao;

import com.dp1wms.model.Seccion;
import com.dp1wms.model.TipoEmpleado;
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

    int obtenerNumEmpleadosDeTipoEmp(long idTipoEmpleado);

    List<Seccion> obtenerSeccionesDeTipoEmpleado(long idTipoEmpleado);

    boolean actualizarPermisos(TipoEmpleado tipoEmpleado,
                               List<Seccion> secciones);

    TipoEmpleado crearTipoEmpleado(TipoEmpleado tipoEmpleado, List<Seccion> secciones);
}
