/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;

import CapaConexion.Conexion;
import CapaDatos.DetalleCuentaBancaria;
import CapaDatos.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class DetalleCuentaBancariasBD {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sql;

    public DefaultTableModel buscarDetalleCuentasBancarias(String ruc) {
        DefaultTableModel modelo;
        String[] titulos = {"ID", "BANCO", "CUENTA", "NRO_CUENTA", "RUC"};
        String[] registros = new String[5];
        modelo = new DefaultTableModel(null, titulos);

        sql = "SELECT iddetallecuentasbancarias,banco,cuenta,nroCuenta,provRuc FROM detallecuentasbancarias "
                + "WHERE provRuc=?";
        try {

            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, ruc);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                registros[0] = rs.getString("iddetallecuentasbancarias");
                registros[1] = rs.getString("banco");
                registros[2] = rs.getString("cuenta");
                registros[3] = rs.getString("nroCuenta");
                registros[4] = rs.getString("provRuc");

                modelo.addRow(registros);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "ERROR AL BUSCAR DETALLE_CUENTAS_BANCARIAS_BD", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return modelo;
    }

    public boolean registrarDetalleCuentasBancarias(DetalleCuentaBancaria d) {
        boolean rpta = false;
        sql = "INSERT INTO detallecuentasbancarias(iddetallecuentasbancarias,banco,cuenta,nroCuenta,provRuc) VALUES (NULL,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, d.getBanco());
            pst.setString(2, d.getCuenta());
            pst.setString(3, d.getNroCuenta());
            pst.setString(4, d.getProvRuc());

            rpta = pst.executeUpdate() == 1 ? true : false;

            pst.close();
            cn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "PROBLEMAS AL REGISTRAR DETALLE_CUENTAS_BANCARIAS_BD", JOptionPane.ERROR_MESSAGE);
            return rpta;
        }
        return rpta;
    }

    public boolean eliminarDetalleCuentasBancarias(int iddetallecuentasbancarias) {
        boolean rpta = false;
        try {
            sql = "DELETE FROM detallecuentasbancarias WHERE iddetallecuentasbancarias=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, iddetallecuentasbancarias);

            rpta = pst.executeUpdate() == 1 ? true : false;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "PROBLEMAS AL ELIMINAR DETALLE_CUENTAS_BANCARIAS BD", JOptionPane.ERROR_MESSAGE);
            return rpta;
        }
        return rpta;
    }

}
