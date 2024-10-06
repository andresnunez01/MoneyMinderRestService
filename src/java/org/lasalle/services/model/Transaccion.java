/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.model;

/**
 *
 * @author ANDRE
 */
public class Transaccion {
    private int idTransaccion;
    private String categoria; //division de tipos de gastos
    private double monto; //monto total
    private String descripcion; //descripcion detallada del gasto
    private String relacion; //persona o lugar al que se le debe
    private int idUsuario;

    public Transaccion() {
    }

    public Transaccion(int idTransaccion, String categoria, double monto, String descripcion, String relacion, int idUsuario) {
        this.idTransaccion = idTransaccion;
        this.categoria = categoria;
        this.monto = monto;
        this.descripcion = descripcion;
        this.relacion = relacion;
        this.idUsuario = idUsuario;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}
