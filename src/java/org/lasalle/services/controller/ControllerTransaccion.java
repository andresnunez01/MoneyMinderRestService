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
import org.lasalle.services.model.Transaccion;

/**
 *
 * @author ANDRE
 */
public class ControllerTransaccion {
    
    public Transaccion saveTransaccion(Transaccion c){
        String query = "INSERT INTO transaccion VALUES (0, ?, ?, ?, ?, ?)";
        try{
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, c.getCategoria());
            pstm.setDouble(2, c.getMonto());
            pstm.setString(3, c.getDescripcion());
            pstm.setString(4, c.getRelacion());
            pstm.setInt(5, c.getIdUsuario());
            pstm.execute();
            System.out.println("Transaccion guardada correctamente");
            pstm.close();
            connMysql.close();
            
        }catch(Exception e){
            
        }
        return c;
    }
    
    
    public List<Transaccion> getAll(int idUsuario){
        List<Transaccion> listTransaccion = new ArrayList<>();
        String query = "SELECT * FROM transaccion";
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Transaccion t = new Transaccion();
                t.setIdTransaccion(rs.getInt("idTransaccion"));
                t.setCategoria(rs.getString("categoria"));
                t.setMonto(rs.getDouble("monto"));
                t.setDescripcion(rs.getString("descripcion"));
                t.setRelacion(rs.getString("relacion"));
                t.setIdUsuario(rs.getInt("idUsuario"));
                //u.setlastConnection(rs.getString("lastConnection"));
                //u.setToken(rs.getString("token"));
                //listUsers.add();
                if(t.getIdUsuario() == idUsuario){
                listTransaccion.add(t);
                }
            }
            return listTransaccion;
            
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return listTransaccion;
        }
    }
}
