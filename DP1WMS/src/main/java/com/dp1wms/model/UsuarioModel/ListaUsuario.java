package com.dp1wms.model.UsuarioModel;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListaUsuario {

    private List<Usuario> v_listaUsuario;

    private int v_cantUsuario;

    public ListaUsuario(){
        v_listaUsuario = new ArrayList<Usuario>();
        v_cantUsuario = 0;

        //extraer datos
        this._inicializarDatos();
    }
    private void _inicializarDatos(){

        //try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/f_usuario.txt.txt")))) {
        try (BufferedReader reader = new BufferedReader(new FileReader("f_usuario.txt"))) {

            this.v_cantUsuario = Integer.parseInt(reader.readLine());
            for (int i = 0; i < v_cantUsuario; i++) {
                int auxId = Integer.parseInt(reader.readLine());
                String auxNombre = reader.readLine();
                String auxPassword = reader.readLine();
                Usuario auxUsuario = new Usuario(auxId, auxNombre, auxPassword);

                v_listaUsuario.add(auxUsuario);

                int id2 = v_listaUsuario.get(i).getV_id();
                String s1 = v_listaUsuario.get(i).getV_nombre();
                String s2 = v_listaUsuario.get(i).getV_password();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void _guardarDatos(){

        try (BufferedWriter writer = new BufferedWriter(new PrintWriter("f_usuario.txt"))) {

            writer.write(Integer.toString(v_cantUsuario));
            writer.newLine();

            for (int i = 0; i < v_cantUsuario; i++) {
                writer.write( Integer.toString(v_listaUsuario.get(i).getV_id()) );
                writer.newLine();
                writer.write( v_listaUsuario.get(i).getV_nombre() );
                writer.newLine();
                writer.write( v_listaUsuario.get(i).getV_password() );
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    public int getV_cantUsuario(){
        return  v_cantUsuario;
    }

    public  Usuario _getUsuario(int posicion){
        return  v_listaUsuario.get(posicion);
    }

    public int _getNewId(){
        int newId = 0;
        for (int i = 0; i < v_cantUsuario; i++) {
            if(newId < v_listaUsuario.get(i).getV_id())
                newId = v_listaUsuario.get(i).getV_id();
        }
        newId++;
        return (newId);
    }

    public void _agregarUsuario(int auxId, String auxNombre, String auxPassword){
        Usuario auxUsuario = new Usuario(auxId, auxNombre, auxPassword);
        this.v_listaUsuario.add(auxUsuario);
        this.v_cantUsuario++;
        this._guardarDatos();
    }

    public void _eliminarUsuario(int auxId){
        for( Iterator<Usuario> auxIterator = this.v_listaUsuario.iterator(); auxIterator.hasNext();){
            Usuario auxUsuario = auxIterator.next();
            if(auxUsuario.getV_id() == auxId) {
                auxIterator.remove();
                this.v_cantUsuario--;
            }
        }

        this._guardarDatos();
    }

}
