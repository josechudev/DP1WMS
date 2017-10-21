package com.dp1wms.dao;

import com.dp1wms.model.UsuarioModel.Usuario;

import java.util.List;

public interface RepositoryMantUsuario {
    public List<Usuario> selectAllUsuario();

    public int newIdUsuario();

    public void createUsuario(Usuario auxUsuario);

    public void updateUsuario(Usuario auxUsuario);

    public void deleteUsuario(Usuario auxUsuario);

}
