package com.dp1wms.dao;

import com.dp1wms.model.Usuario;

public interface RepositorySeguridad {
    Usuario validarCredenciales(Usuario usuario);
}
