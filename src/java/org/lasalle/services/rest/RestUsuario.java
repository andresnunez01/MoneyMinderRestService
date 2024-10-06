/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.lasalle.services.controller.ControllerUsuario;
import org.lasalle.services.model.Usuario;

/**
 *
 * @author ANDRE
 */
@Path("usuario")
public class RestUsuario {
    @Path("validar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response validar(@QueryParam("usuario") String usuario,
                            @QueryParam("contraseña") String contraseña){
        String out = "Usuario o contraseña incorrecto";
        List<Usuario> listUsers = null;
        //Gson gson = new Gson();
        int usuarioId = 0;
        String usuarioOut = "";
        try {
            ControllerUsuario cu = new ControllerUsuario();
            listUsers = cu.getAll();
            //out = gson.toJson(listUsers);
           
            for (Usuario user : listUsers) {
                System.out.println(user.getUsuario());
                if(user.getUsuario().equals(usuario) && user.getContraseña().equals(contraseña)){
                    Gson gson = new Gson();
        
                    out = gson.toJson(user);
                }
                
            }
            
        } catch (Exception e) {
            out = """
                  {"response": "%S"}
                  """;
            
            out = String.format(out, e);
        }
        
        return Response.ok(out).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("save")
    public Response save(@QueryParam("nombre") String nombreF,
                         @QueryParam("usuario") String usuarioF,
                         @QueryParam("contraseña") String contraF){
        
        String out = "";
        try{
            ControllerUsuario cu = new ControllerUsuario();
            cu.saveUsuario(new Usuario(0, nombreF, usuarioF, contraF));
            out = """
                     {"response":"Usuario Creado"}
                     """;
        }catch (Exception e){
            out = """
                     {"response":"Error al registrar"}
                     """;
        }
        return Response.ok(out).build();
    }
    
    @Path("getInfoPerfil")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfoPerfil(@QueryParam("idUsuario") int idUser){
        String out = "Error al procesar perfil";
        List<Usuario> listUsers = null;
        //Gson gson = new Gson();
        try {
            ControllerUsuario cu = new ControllerUsuario();
            listUsers = cu.getAll();
            //out = gson.toJson(listUsers);
           
            for (Usuario user : listUsers) {
                System.out.println(user.getUsuario());
                if(user.getIdUsuario() == idUser){
                    
                    Gson gson = new Gson();
                    out = gson.toJson(user);     //transformar el objeto del usuario a json para su utilizacion en xcode           
                }
                
            }
            
        } catch (Exception e) {
            out = """
                  {"response": "%S"}
                  """;
            
            out = String.format(out, e);
        }
        
        return Response.ok(out).build();
    }
    
    @Path("updateInfoPerfil")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateInfoPerfil(@QueryParam("idUsuario") int idUser,
                                     @QueryParam("nombre") String nombreF,
                                     @QueryParam("usuario") String usuarioF,
                                     @QueryParam("contraseña") String contraF){
        String out = "Error al actualizar perfil";
        List<Usuario> listUsers = null;
        //Gson gson = new Gson();
        try {
            ControllerUsuario cu = new ControllerUsuario();
            listUsers = cu.getAll();
            //out = gson.toJson(listUsers);
           
            for (Usuario user : listUsers) {
                System.out.println(user.getUsuario());
                if(user.getIdUsuario() == idUser){
                    
                    user.setNombre(nombreF);
                    user.setUsuario(usuarioF);
                    user.setContraseña(contraF);
                    
                    cu.updateUsuario(user);
                    
                    out =  """
                           {"response": "Usuario actualizado exitosamente"}
                           """;    //transformar el objeto del usuario a json para su utilizacion en xcode           
                }
                
            }
            
        } catch (Exception e) {
            out = """
                  {"response": "%S"}
                  """;
            
            out = String.format(out, e);
        }
        
        return Response.ok(out).build();
    }
}
