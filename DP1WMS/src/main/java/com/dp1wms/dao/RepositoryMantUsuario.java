package com.dp1wms.dao;

import com.dp1wms.model.UsuarioModel.Usuario;

import java.util.List;

public interface RepositoryMantUsuario {
    List<Usuario> selectAllUsuario();

    int newIdUsuario();

    void createUsuario(Usuario auxUsuario);

    void updateUsuario(Usuario auxUsuario);

    void deleteUsuario(Usuario auxUsuario);

}
