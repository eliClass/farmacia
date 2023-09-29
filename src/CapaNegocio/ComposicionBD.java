/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;

import CapaConexion.Conexion;
import CapaDatos.Composicion;
import CapaDatos.Marca;
import com.mysql.cj.xdevapi.PreparableStatement;
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
public class ComposicionBD {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sql;

  public List<Composicion> reportarComposicion(){
      List<Composicion> lista = new ArrayList<>();
      sql= "SELECT idcomposicion,coNombre,pSErie FROM composicion ORDER BY idcomposicion ASC";
      try {
          PreparedStatement pst = cn.prepareStatement(sql);
          ResultSet rs = pst.executeQuery();
          
          while (rs.next()) {
              Composicion oCorrelativo = new Composicion();
              
              oCorrelativo.setIdcomposicion(rs.getInt(1));
              oCorrelativo.setCoNombre(rs.getString(2));
              oCorrelativo.setpSerie(rs.getString(3));
              
              lista.add(oCorrelativo);
              
          }
          
      } catch (Exception e) {
          JOptionPane.showMessageDialog(null, e, "ERROR AL REPORTAR CORRELATIVO...", JOptionPane.ERROR_MESSAGE);
          e.printStackTrace();
          return null;
      }
      return lista;
  }
   
  public boolean registrarComposicion(Composicion co){
        boolean rpta = false;

        sql = "INSERT INTO composicion(idcomposicion,coNombre,pSerie) VALUES (0,?,?)";

        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, co.getCoNombre());
            pst.setString(2, co.getpSerie());

            rpta = pst.executeUpdate() == 1 ? true : false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "PROBLEMAS AL REGISTRAR LAS COMPOSICIONES...", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return rpta;
        }
        return rpta;
  }
  
  public boolean eliminarComposicion(int idcomposicion){
      boolean rpta= false;
      sql= "DELETE FROM composicion WHERE idcomposicion=?";
      try {
          PreparedStatement pst = cn.prepareStatement(sql);
          pst.setInt(1, idcomposicion);
          
          rpta = pst.executeUpdate() == 1 ? true : false;
          
      } catch (Exception e) {
          JOptionPane.showMessageDialog(null,e,"PROBLEMAS AL ELIMINAR", JOptionPane.ERROR_MESSAGE);
          e.printStackTrace();
          return rpta;
      }
      return rpta;
  }
  
}
