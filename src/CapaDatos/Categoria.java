/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDatos;

/**
 *
 * @author USER
 */
public class Categoria {
    
    private int idcategoria;
    private String caNombre;

    public Categoria() {
    }

    public Categoria(int idcategoria, String caNombre) {
        this.idcategoria = idcategoria;
        this.caNombre = caNombre;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getCaNombre() {
        return caNombre;
    }

    public void setCaNombre(String catNombre) {
        this.caNombre = catNombre;
    }
    
}
