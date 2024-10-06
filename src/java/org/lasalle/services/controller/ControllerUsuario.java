/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.lasalle.services.model.Usuario;

/**
 *
 * @author ANDRE
 */
public class ControllerUsuario {
    public Usuario saveUsuario(Usuario c){
        String query = "INSERT INTO usuario VALUES (0, ?, ?, ?)";
        try{
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, c.getNombre());
            pstm.setString(2, c.getUsuario());
            pstm.setString(3, c.getContraseña());
            pstm.execute();
            System.out.println("Usuario Creado Satisfactoriamente");
            pstm.close();
            connMysql.close();
            
        }catch(Exception e){
            
        }
        return c;
    }
    
    
    public Usuario validarUsuario(Usuario u){
        String query = "SELECT usuario, contraseña FROM usuario WHERE usuario = ? AND contraseña = ?";
        try{
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, u.getUsuario());
            pstm.setString(2, u.getContraseña());
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                u.setUsuario(rs.getString("username"));
                u.setContraseña(rs.getString("password"));
            }
            rs.close();
            pstm.close();
            connMysql.close();
            
        }catch(Exception e){
            
        }
        return u;
    }
    
    public List<Usuario> getAll(){
        List<Usuario> listUsers = new ArrayList<>();
        String query = "SELECT * FROM usuario";
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setUsuario(rs.getString("usuario"));
                u.setContraseña(rs.getString("contraseña"));
                u.setNombre(rs.getString("nombre"));
                //u.setlastConnection(rs.getString("lastConnection"));
                //u.setToken(rs.getString("token"));
                //listUsers.add();
                listUsers.add(u);
            }
            return listUsers;
            
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return listUsers;
        }
    }

    public Usuario updateUsuario(Usuario usuario) {
        String query = "UPDATE usuario SET nombre = ?, usuario = ?, contraseña = ? WHERE idUsuario = ?";
        try{
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, usuario.getNombre());
            pstm.setString(2, usuario.getUsuario());
            pstm.setString(3, usuario.getContraseña());
            pstm.setInt(4, usuario.getIdUsuario());
            pstm.execute();
            pstm.close();
            connMysql.close();
            
        }catch(Exception e){
            
        }
        return usuario;
    }
}
