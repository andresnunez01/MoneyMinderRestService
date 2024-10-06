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
import org.lasalle.services.controller.ControllerNotas;
import org.lasalle.services.model.Notas;

/**
 *
 * @author ANDRE
 */

@Path("notas")
public class RestNotas {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("save")
    public Response save(@QueryParam("titulo") String tituloQ,
                         @QueryParam("contenido") String descripcionQ,
                         @QueryParam("idUsuario") int idUsuarioQ){
        
        String out = "";
        try{
            ControllerNotas cn = new ControllerNotas();
            cn.saveNota(new Notas(0, tituloQ, descripcionQ, idUsuarioQ));
            out = """
                     {"response":"Nota Creada"}
                     """;
        }catch (Exception e){
            out = """
                     {"response":"Error al registrar la nota"}
                     """;
        }
        return Response.ok(out).build();
    }
    
    
    @Path("getNotas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotas(@QueryParam("idUsuario") int idUser){
        String out = "Error al procesar perfil";
        List<Notas> listNotas = null;
        //Gson gson = new Gson();
        try {
            ControllerNotas cn = new ControllerNotas();
            listNotas = cn.getAll(idUser);
            //out = gson.toJson(listUsers);
           
                    Gson gson = new Gson();
                    out = gson.toJson(listNotas);     //transformar el objeto del usuario a json para su utilizacion en xcode           
                
                
            
            
        } catch (Exception e) {
            out = """
                  {"response": "%S"}
                  """;
            
            out = String.format(out, e);
        }
        
        return Response.ok(out).build();
    }
    
}
