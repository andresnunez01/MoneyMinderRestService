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
import org.lasalle.services.model.Notas;

/**
 *
 * @author ANDRE
 */
public class ControllerNotas {
    
    public Notas saveNota(Notas c){
        String query = "INSERT INTO notas VALUES (0, ?, ?, ?)";
        try{
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, c.getTitulo());
            pstm.setString(2, c.getContenido());
            pstm.setInt(3, c.getIdUsuario());
            pstm.execute();
            System.out.println("Nota creada correctamente");
            pstm.close();
            connMysql.close();
            
        }catch(Exception e){
            
        }
        return c;
    }
    
    
    public List<Notas> getAll(int idUsuario){
        List<Notas> listNotas = new ArrayList<>();
        String query = "SELECT * FROM notas";
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Notas n = new Notas();
                n.setIdNota(rs.getInt("idNota"));
                n.setTitulo(rs.getString("titulo"));
                n.setContenido(rs.getString("contenido"));
                n.setIdUsuario(rs.getInt("idUsuario"));
                //u.setlastConnection(rs.getString("lastConnection"));
                //u.setToken(rs.getString("token"));
                //listUsers.add();
                if(n.getIdUsuario() == idUsuario){
                listNotas.add(n);
                }
            }
            return listNotas;
            
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return listNotas;
        }
    }
}
