package com.dp1wms.dao;

import com.dp1wms.model.UsuarioModel.Usuario;

import java.util.List;

public interface RepositoryMantUsuario {
    List<Usuario> selectAllUsuario();

    void createUsuario(Usuario auxUsuario);

    void updateUsuario(Usuario auxUsuario, boolean auxModificarPassword);

    void deleteUsuario(Usuario auxUsuario);

    Usuario findUsuariobyId(int auxIdUser);

    boolean existeUsuario(String auxNameUser);

    boolean coincideUsuarioId(String auxNameUser, int auxIdUser);
}