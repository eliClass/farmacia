/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;

import CapaConexion.Conexion;
import CapaDatos.Categoria;
import CapaDatos.Medida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class CategoriaBD {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sql;

    public List<Categoria> reportarCategoria() {
        List<Categoria> lista = new ArrayList<>();
        sql = "SELECT idcategoria, caNombre FROM categoria";
        try {

            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Categoria oCategoria = new Categoria();

                oCategoria.setIdcategoria(rs.getInt(1));
                oCategoria.setCaNombre(rs.getString(2));

                lista.add(oCategoria);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "ERROR AL REPORTAR USUARIO BD...", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return lista;
    }

    public boolean registrarCategoria(Categoria c) {
        boolean rpta = false;

        sql = "INSERT INTO categoria(idcategoria,caNombre) VALUES (0,?)";

        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, c.getCaNombre());

            rpta = pst.executeUpdate() == 1 ? true : false;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "PROBLEMAS AL REGISTRAR MARCA...", JOptionPane.ERROR_MESSAGE);
            return rpta;
        }
        return rpta;
    }

    public boolean modificarCategoria(Categoria c) {
        boolean rpta = false;
        sql = "UPDATE categoria SET caNombre=? WHERE idcategoria=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, c.getCaNombre());
            pst.setInt(2, c.getIdcategoria());

            rpta = pst.executeUpdate() == 1 ? true : false;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error al modificar categoria BD..", JOptionPane.ERROR_MESSAGE);
            return rpta;
        }

        return rpta;

    }

    public boolean eliminarCategoria(Categoria c) {
        boolean rpta = false;
        sql = "DELETE FROM categoria WHERE idcategoria=?";

        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, c.getIdcategoria());
            rpta = pst.executeUpdate() == 1 ? true : false;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "PROBLEMAS AL ELIMINAR", JOptionPane.ERROR_MESSAGE);
            return rpta;
        }
        return rpta;
    }

    public List<Categoria> lista = new ArrayList<>();
    public List<Categoria>buscarCategorias(String valor){
         List<Categoria> lista= new ArrayList();
         sql= "SELECT idcategoria,caNombre FROM categoria WHERE caNombre LIKE ?";
         try {
              PreparedStatement pst = cn.prepareStatement(sql);
              pst.setString(1, "%" + valor + "%");
              
              ResultSet rs= pst.executeQuery();
              while (rs.next()) {       
                  Categoria oCategoria= new Categoria();
                  oCategoria.setIdcategoria(rs.getInt(1));
                  oCategoria.setCaNombre(rs.getString(2));
                  
                  lista.add(oCategoria);
                  
                 
             }
             
         } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e, "ERROR AL BUSCAR CATEGORIAS", JOptionPane.ERROR_MESSAGE);
            return null;
         }
         return lista;
     }
}
