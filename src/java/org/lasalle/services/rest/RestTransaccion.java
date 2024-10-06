/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.lasalle.services.controller.ControllerTransaccion;
import org.lasalle.services.model.Transaccion;

/**
 *
 * @author ANDRE
 */
@Path("transaccion")
public class RestTransaccion {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("save")
    public Response save(@QueryParam("categoria") String categoriaQ,
                         @QueryParam("monto") Double montoQ,
                         @QueryParam("descripcion") String descripcionQ,
                         @QueryParam("relacion") String relacionQ,
                         @QueryParam("idUsuario") int idUsuarioQ){
        
        String out = "";
        try{
            ControllerTransaccion ct = new ControllerTransaccion();
            ct.saveTransaccion(new Transaccion(0, categoriaQ, montoQ, descripcionQ, relacionQ, idUsuarioQ));
            out = """
                     {"response":"Transaccion Creado"}
                     """;
        }catch (Exception e){
            out = """
                     {"response":"Error al registrar"}
                     """;
        }
        return Response.ok(out).build();
    }
    
    
    @Path("getTransacciones")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransacciones(@QueryParam("idUsuario") int idUser){
        String out = "Error al procesar perfil";
        List<Transaccion> listTransaccion = null;
        //Gson gson = new Gson();
        try {
            ControllerTransaccion ct = new ControllerTransaccion();
            listTransaccion = ct.getAll(idUser);
            //out = gson.toJson(listUsers);
           
                    Gson gson = new Gson();
                    out = gson.toJson(listTransaccion);     //transformar el objeto del usuario a json para su utilizacion en xcode           
                
                
            
            
        } catch (Exception e) {
            out = """
                  {"response": "%S"}
                  """;
            
            out = String.format(out, e);
        }
        
        return Response.ok(out).build();
    }
}
