package com.example.carritocompras;

import java.io.Serializable;

public class CategoriaProducto implements Serializable {
    private int idcategoria;
    private int imagen;
    private String nombre;
    private String descripcion;

    public CategoriaProducto(int idcategoria, int imagen, String nombre, String descripcion) {
        this.idcategoria = idcategoria;
        this.imagen = imagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
