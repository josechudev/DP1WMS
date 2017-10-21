package com.dp1wms.dao;

import com.dp1wms.model.Empleado;

public interface RepositoryMantEmpleado {

    Empleado obtenerEmpleadoPorIdUsuario(long idUsuario);
}
