package com.dp1wms.dao;

import com.dp1wms.model.Empleado;
import com.dp1wms.model.TipoEmpleado;
import com.dp1wms.model.UsuarioModel.Usuario;
import com.dp1wms.model.UsuarioModel.UsuarioXEmpleado;

import java.util.List;

public interface RepositoryMantEmpleado {

    List<Empleado> selectAllEmpleado();

    Empleado obtenerEmpleadoPorIdUsuario(long idUsuario);

    List<UsuarioXEmpleado> obtenerUsuarioXEmpleadoPorIdUsuario();

    void createEmpleado(Usuario auxUsuario, Empleado auxEmpleado, TipoEmpleado auxTipoEmpleado);

    void updateEmpleado(Usuario auxUsuario, Empleado auxEmpleado, TipoEmpleado auxTipoEmpleado);

    void deleteEmpleado(Usuario auxUsuario, Empleado auxEmpleado);

    Usuario findUsuariobyName(String auxName);

}
