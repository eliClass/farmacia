/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package CapaPresentacion;

import CapaDatos.Categoria;
import CapaDatos.Marca;
import CapaDatos.Medida;
import CapaDatos.Producto;
import CapaNegocio.CategoriaBD;
import CapaNegocio.MarcaBD;
import CapaNegocio.MedidaBD;
import CapaNegocio.ProductoBD;
import static CapaPresentacion.Menu_IU.escritorio;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class Producto_IU extends javax.swing.JInternalFrame {

    List<Categoria> lista_Categoria;
    List<Marca> lista_Marca;
    List<Medida> lista_Medida;

    /**
     * Creates new form Producto_IU
     */
    public Producto_IU() {
        initComponents();
        habilitar();
        deshabilitar();
        limpiar();
        cargarCategoria();
        cargarMarca();
        cargarMedida();
        reportar();
    }

    private void habilitar() {
        btnBuscar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnModificar.setEnabled(true);
        btnRegistrar.setEnabled(true);

    }

    private void deshabilitar() {
        btnBuscar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnRegistrar.setEnabled(false);
    }

    private void limpiar() {
        cmbCategoria.setSelectedIndex(0);
        cmbLaboratorio.setSelectedIndex(0);
        cmbMedida.setSelectedIndex(0);

        txtSerie.setText("");
        txtDescripcion.setText("");
        txtObservacion.setText("");
        txtDigemi.setText("");
        txtIdMedida.setText("");
        txtIdMarca.setText("");
        txtIdCategoria.setText("");
    }

    private void limpiar_tabla_formulario() {
        DefaultTableModel taba_temporal_producto = (DefaultTableModel) tabla_reporte_producto.getModel();
        taba_temporal_producto.setRowCount(0);
    }

    private void cargarCategoria() {
        try {
            cmbCategoria.removeAllItems();
            CategoriaBD oCategoriaBD = new CategoriaBD();

            lista_Categoria = oCategoriaBD.reportarCategoria();

            cmbCategoria.addItem("Seleccionar");

            for (int i = 0; i < lista_Categoria.size(); i++) {
                int idcategoria = lista_Categoria.get(i).getIdcategoria();
                String nombre = lista_Categoria.get(i).getCaNombre();

                cmbCategoria.addItem(nombre);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error al cargar categoria", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void cargarMarca() {
        try {
            cmbLaboratorio.removeAllItems();
            MarcaBD oMarcaBD = new MarcaBD();
            DefaultTableModel tabla_temporal;
            tabla_temporal = oMarcaBD.reportarMarca();

            lista_Marca = new ArrayList<>();

            cmbLaboratorio.addItem("Seleccionar");

            for (int i = 0; i < tabla_temporal.getRowCount(); i++) {
                int codigo = Integer.valueOf(tabla_temporal.getValueAt(i, 0).toString());
                String nombre = String.valueOf(tabla_temporal.getValueAt(i, 1));
                lista_Marca.add(new Marca(codigo, nombre));

                cmbLaboratorio.addItem(nombre);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error al cargar marca", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void cargarMedida() {
        try {
            cmbMedida.removeAllItems();
            MedidaBD oMedidaBD = new MedidaBD();
            DefaultTableModel tabla_temporal;
            tabla_temporal = oMedidaBD.reportarMedida();

            lista_Medida = new ArrayList<>();

            cmbMedida.addItem("Seleccionar");

            for (int i = 0; i < tabla_temporal.getRowCount(); i++) {
                int codigo = Integer.valueOf(tabla_temporal.getValueAt(i, 0).toString());
                String presentacion = String.valueOf(tabla_temporal.getValueAt(i, 1));
                String equivalencia = String.valueOf(tabla_temporal.getValueAt(i, 2));
                lista_Medida.add(new Medida(codigo, presentacion, equivalencia));

                cmbMedida.addItem(presentacion);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error al cargar medida", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void reportar() {
        try {
            setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
            
            limpiar_tabla_formulario();
            DefaultTableModel tabla_temporal;
            ProductoBD oProductoBD = new ProductoBD();
            tabla_temporal = oProductoBD.reportarProducto();

            int cantidad_productos_encontrados = tabla_temporal.getRowCount();
            txtCantidad.setText("" + cantidad_productos_encontrados);
            DefaultTableModel tabla_temporal_productos = (DefaultTableModel) this.tabla_reporte_producto.getModel();
            for (int i = 0; i < cantidad_productos_encontrados; i++) {
                String serie = tabla_temporal.getValueAt(i, 0).toString();
                String descripcion = tabla_temporal.getValueAt(i, 1).toString();
                String observacion = tabla_temporal.getValueAt(i, 2).toString();
                String digemi = tabla_temporal.getValueAt(i, 3).toString();
                String condicion = tabla_temporal.getValueAt(i, 4).toString();
                String categoria = tabla_temporal.getValueAt(i, 5).toString();
                String marca = tabla_temporal.getValueAt(i, 6).toString();
                String medida = tabla_temporal.getValueAt(i, 7).toString();
                Object[] data = {serie, descripcion, observacion, digemi, condicion, categoria, marca, medida};
                tabla_temporal_productos.addRow(data);

            }
            tabla_reporte_producto.setModel(tabla_temporal_productos);
            setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        } catch (Exception e) {

            setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            e.printStackTrace();
        }
    }

    private void exito(String mensaje) {
        JOptionPane.showConfirmDialog(this, mensaje, "MENSAJE", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }

    private void error(String mensaje) {
        JOptionPane.showConfirmDialog(this, mensaje, "ERROR", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
    }

    private void advertencia(String mensaje) {
        JOptionPane.showConfirmDialog(this, mensaje, "ADVERTENCIA", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtObservacion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDigemi = new javax.swing.JTextField();
        txtSerie = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbCondicion = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cmbLaboratorio = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cmbMedida = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_reporte_producto = new javax.swing.JTable();
        cmbComposicion = new javax.swing.JButton();
        txtCantidad = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtIdMedida = new javax.swing.JTextField();
        txtIdMarca = new javax.swing.JTextField();
        txtIdCategoria = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("MANTENIMIENTO DE PRODUCTOS");

        jLabel1.setText("SERIE");

        jLabel2.setText("DESCRIPCION");

        jLabel3.setText("OBSERVACION");

        txtObservacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtObservacionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtObservacionFocusLost(evt);
            }
        });
        txtObservacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtObservacionKeyPressed(evt);
            }
        });

        jLabel4.setText("DIGEMI");

        txtDigemi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDigemiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDigemiFocusLost(evt);
            }
        });
        txtDigemi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDigemiKeyPressed(evt);
            }
        });

        txtSerie.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSerieFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSerieFocusLost(evt);
            }
        });
        txtSerie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSerieActionPerformed(evt);
            }
        });
        txtSerie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSerieKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSerieKeyTyped(evt);
            }
        });

        txtDescripcion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDescripcionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDescripcionFocusLost(evt);
            }
        });
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyPressed(evt);
            }
        });

        jLabel5.setText("CONDICION");

        cmbCondicion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NORMAL", "RECETA MEDICA" }));
        cmbCondicion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbCondicionKeyPressed(evt);
            }
        });

        jLabel6.setText("CATEGORIA");

        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCategoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCategoriaItemStateChanged(evt);
            }
        });
        cmbCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbCategoriaKeyPressed(evt);
            }
        });

        jLabel7.setText("LABORATORIO");

        cmbLaboratorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbLaboratorio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbLaboratorioItemStateChanged(evt);
            }
        });
        cmbLaboratorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLaboratorioActionPerformed(evt);
            }
        });
        cmbLaboratorio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbLaboratorioKeyPressed(evt);
            }
        });

        jLabel8.setText("MEDIDA");

        cmbMedida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbMedida.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMedidaItemStateChanged(evt);
            }
        });
        cmbMedida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMedidaKeyPressed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("BUSCAR PRODUCTO"));

        jLabel9.setText("PRODUCTO");

        txtProducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProductoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProductoFocusLost(evt);
            }
        });
        txtProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProductoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProducto)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        tabla_reporte_producto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SERIE", "DESCRIPCION", "OBSERVACION", "DIGEMI", "CONDICION", "CATEGORIA", "MARCA", "MEDIDA"
            }
        ));
        tabla_reporte_producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabla_reporte_productoMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_reporte_producto);
        if (tabla_reporte_producto.getColumnModel().getColumnCount() > 0) {
            tabla_reporte_producto.getColumnModel().getColumn(0).setPreferredWidth(40);
            tabla_reporte_producto.getColumnModel().getColumn(1).setPreferredWidth(120);
            tabla_reporte_producto.getColumnModel().getColumn(2).setPreferredWidth(20);
            tabla_reporte_producto.getColumnModel().getColumn(3).setPreferredWidth(20);
            tabla_reporte_producto.getColumnModel().getColumn(7).setPreferredWidth(30);
        }

        cmbComposicion.setText("COMPOSICION");
        cmbComposicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbComposicionActionPerformed(evt);
            }
        });

        txtCantidad.setEnabled(false);

        jLabel10.setText("CANTIDAD");

        txtIdMedida.setEnabled(false);

        txtIdMarca.setEnabled(false);

        txtIdCategoria.setEnabled(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Proyecto/application.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Proyecto/disk.png"))); // NOI18N
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegistrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Proyecto/report_edit.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Proyecto/bin_empty.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Proyecto/cross.png"))); // NOI18N
        btnCerrar.setText("CERRAR");
        btnCerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCerrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Proyecto/report_magnify.png"))); // NOI18N
        btnBuscar.setText("BUSCAR");
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo)
                .addGap(18, 18, 18)
                .addComponent(btnRegistrar)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnModificar)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar)
                .addGap(140, 140, 140)
                .addComponent(btnCerrar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtObservacion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 339, Short.MAX_VALUE)
                                .addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDescripcion)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtDigemi, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbCategoria, javax.swing.GroupLayout.Alignment.LEADING, 0, 250, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbLaboratorio, 0, 250, Short.MAX_VALUE)
                                    .addComponent(cmbCondicion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbComposicion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtIdMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIdMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSerie)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtDigemi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(cmbCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(cmbLaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(cmbMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cmbComposicion))
                .addGap(2, 2, 2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSerieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSerieActionPerformed

    private void cmbCategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCategoriaItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {

            String textoSeleccionado = (String) cmbCategoria.getSelectedItem();
            if (textoSeleccionado.equals("Seleccionar")) {
                txtIdMedida.setText("");

            } else {
                int i = cmbCategoria.getSelectedIndex() - 1;
                txtIdCategoria.setText("" + lista_Categoria.get(i).getIdcategoria());
            }

        }
    }//GEN-LAST:event_cmbCategoriaItemStateChanged

    private void cmbMedidaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMedidaItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {

            String textoSeleccionado = (String) cmbMedida.getSelectedItem();
            if (textoSeleccionado.equals("Seleccionar")) {
                txtIdCategoria.setText("");

            } else {
                int i = cmbMedida.getSelectedIndex() - 1;
                txtIdMedida.setText("" + lista_Medida.get(i).getIdmedida());
            }

        }
    }//GEN-LAST:event_cmbMedidaItemStateChanged

    private void cmbLaboratorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLaboratorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLaboratorioActionPerformed

    private void cmbLaboratorioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbLaboratorioItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {

            String textoSeleccionado = (String) cmbLaboratorio.getSelectedItem();
            if (textoSeleccionado.equals("Seleccionar")) {
                txtIdMarca.setText("");

            } else {
                int i = cmbLaboratorio.getSelectedIndex() - 1;
                txtIdMarca.setText("" + lista_Marca.get(i).getIdmarca());
            }

        }
    }//GEN-LAST:event_cmbLaboratorioItemStateChanged

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
          if (txtSerie.getText().length() > 0) {

            if (txtDescripcion.getText().length() > 0) {
                if (txtIdMedida.getText().length() > 0) {

                    if (txtIdMarca.getText().length() > 0) {
                        if (txtIdCategoria.getText().length() > 0) {
                            Producto oProducto = new Producto();
                            ProductoBD oProductoBD = new ProductoBD();

                            oProducto.setpSerie(txtSerie.getText().trim());
                            oProducto.setpDescripcion(txtDescripcion.getText().toUpperCase().trim());
                            oProducto.setpObservacion(txtObservacion.getText().toUpperCase());
                            oProducto.setDigemi(txtDigemi.getText().toUpperCase());
                            oProducto.setpCondicion(cmbCondicion.getSelectedItem().toString());
                            oProducto.setIdcategoria(Integer.parseInt(txtIdCategoria.getText()));
                            oProducto.setIdmarca(Integer.parseInt(txtIdMarca.getText()));
                            oProducto.setIdmedida(Integer.parseInt(txtIdMedida.getText()));

                            boolean rpta = oProductoBD.registrarProducto(oProducto);

                            if (rpta) {
                                exito("Se registro con exito");
                                reportar();
                                limpiar();
                                deshabilitar();

                            } else {
                                error("Tienes problemas al registrar");
                            }

                        } else {
                            error("Seleccione una categoria");

                        }

                    } else {
                        error("Seleccione un laboratorio");

                    }

                } else {
                    error("Seleccione una medida");

                }

            } else {
                error("Ingrese la descripcion del producto");
                txtDescripcion.requestFocus();
            }

        } else {
            error("Ingrese el codigo del producto");
            txtSerie.requestFocus();
        }               
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
          if (txtSerie.getText().length() > 0) {
            String serie = txtSerie.getText();
            DefaultTableModel tabla_temporal;
            ProductoBD oProductoBD = new ProductoBD();
            tabla_temporal = oProductoBD.buscarProducto(serie);

            int cantidad_productos_encontrados = tabla_temporal.getRowCount();
            txtCantidad.setText("" + cantidad_productos_encontrados);

            limpiar_tabla_formulario();

            if (cantidad_productos_encontrados > 0) {
                txtDescripcion.setText(tabla_temporal.getValueAt(0, 1).toString());
                txtObservacion.setText(tabla_temporal.getValueAt(0, 2).toString());
                txtDigemi.setText(tabla_temporal.getValueAt(0, 3).toString());
                cmbCondicion.setSelectedItem(tabla_temporal.getValueAt(0, 4).toString());
                cmbMedida.setSelectedItem(tabla_temporal.getValueAt(0, 7).toString());
                cmbLaboratorio.setSelectedItem(tabla_temporal.getValueAt(0, 6).toString());
                cmbCategoria.setSelectedItem(tabla_temporal.getValueAt(0, 5).toString());
                txtIdCategoria.setText(tabla_temporal.getValueAt(0, 10).toString());
                txtIdMedida.setText(tabla_temporal.getValueAt(0, 8).toString());
                txtIdMarca.setText(tabla_temporal.getValueAt(0, 9).toString());

                DefaultTableModel tabla_temporal_producto = (DefaultTableModel) this.tabla_reporte_producto.getModel();

                for (int i = 0; i < cantidad_productos_encontrados; i++) {
                    serie = tabla_temporal.getValueAt(i, 0).toString();
                    String descripcion = tabla_temporal.getValueAt(i, 1).toString();
                    String observacion = tabla_temporal.getValueAt(i, 2).toString();
                    String digemi = tabla_temporal.getValueAt(i, 3).toString();
                    String condicion = tabla_temporal.getValueAt(i, 4).toString();
                    String categoria = tabla_temporal.getValueAt(i, 5).toString();
                    String marca = tabla_temporal.getValueAt(i, 6).toString();
                    String medida = tabla_temporal.getValueAt(i, 7).toString();

                    Object[] data = {serie, descripcion, observacion, digemi, condicion, categoria, marca, medida};
                    tabla_temporal_producto.addRow(data);

                }
                tabla_reporte_producto.setModel(tabla_temporal_producto);

            } else {
                JOptionPane.showMessageDialog(this, "No se encontro el producto");
                txtSerie.requestFocus();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ingrese serie...");
            txtSerie.requestFocus();
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
         if (txtSerie.getText().length() > 0) {
            if (txtDescripcion.getText().length() > 0) {
                if (txtIdMedida.getText().length() > 0) {
                    if (txtIdMarca.getText().length() > 0) {

                        if (txtIdCategoria.getText().length() > 0) {
                            Producto oProducto = new Producto();
                            ProductoBD oProductoBD = new ProductoBD();

                            oProducto.setpSerie(txtSerie.getText().trim());
                            oProducto.setpDescripcion(txtDescripcion.getText().toUpperCase().trim());
                            oProducto.setpObservacion(txtObservacion.getText().toUpperCase());
                            oProducto.setDigemi(txtDigemi.getText().toUpperCase());
                            oProducto.setpCondicion(cmbCondicion.getSelectedItem().toString());
                            oProducto.setIdcategoria(Integer.parseInt(txtIdMedida.getText()));
                            oProducto.setIdmarca(Integer.parseInt(txtIdMarca.getText()));
                            oProducto.setIdmedida(Integer.parseInt(txtIdCategoria.getText()));

                            boolean rpta = oProductoBD.modificarProducto(oProducto);

                            if (rpta) {
                                exito("se modificó con exito");
                                reportar();
                                limpiar();
                                deshabilitar();

                            } else {
                                error("Tienes al problemas al modificar");
                            }

                        } else {
                            error("Seleccione una medida");
                            txtIdCategoria.requestFocus();
                        }

                    } else {
                        error("Seleccione un laboratorio");
                        txtIdMarca.requestFocus();
                    }
                } else {
                    error("Seleccione una categoria");
                    txtIdMedida.requestFocus();
                }

            } else {
                error("Ingrese la descripcion del producto");
                txtDescripcion.requestFocus();
            }
        } else {
            error("Ingrese el codigo del producto");
            txtSerie.requestFocus();
        }
                                              

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
         try {
            if (txtSerie.getText().length() > 0) {
                String serie = txtSerie.getText();
                int aviso = JOptionPane.showConfirmDialog(rootPane, "Estás seguro de eliminar?");
                if (aviso == 0) {
                    ProductoBD oProductoBD = new ProductoBD();
                    boolean rpta = oProductoBD.eliminarProducto(serie);

                    if (rpta) {
                        exito("Se elimino el producto");
                        reportar();
                        limpiar();
                        deshabilitar();
                        txtSerie.requestFocus();

                    } else {
                        error("Tienes problemas al eliminar producto");
                    }

                }
            } else {
                error("Falta codigo del producto a eliminar");
                txtSerie.requestFocus();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
                  
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoKeyPressed
        // TODO add your handling code here:
           limpiar_tabla_formulario();
        String descripcion= txtProducto.getText();
        DefaultTableModel tabla_temporal;
        DefaultTableModel tabla_temporal_producto= (DefaultTableModel) tabla_reporte_producto.getModel();
        ProductoBD oProductoBD= new ProductoBD();
        tabla_temporal= oProductoBD.buscarProductoDescripcion(descripcion);
        int cant= tabla_temporal.getRowCount();
        for (int i = 0; i < cant; i++) {
             String serie = tabla_temporal.getValueAt(i, 0).toString();
                descripcion = tabla_temporal.getValueAt(i, 1).toString();
                String observacion = tabla_temporal.getValueAt(i, 2).toString();
                String digemi = tabla_temporal.getValueAt(i, 3).toString();
                String condicion = tabla_temporal.getValueAt(i, 4).toString();
                String categoria = tabla_temporal.getValueAt(i, 5).toString();
                String marca = tabla_temporal.getValueAt(i, 6).toString();
                String medida = tabla_temporal.getValueAt(i, 7).toString();
                Object[] data = {serie, descripcion, observacion, digemi, condicion, categoria, marca, medida};
                tabla_temporal_producto.addRow(data);
        }
        tabla_reporte_producto.setModel(tabla_temporal_producto);
        txtCantidad.setText("" + cant);
    
    }//GEN-LAST:event_txtProductoKeyPressed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        habilitar();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void tabla_reporte_productoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_reporte_productoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_reporte_productoMousePressed

    private void cmbComposicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbComposicionActionPerformed
        // TODO add your handling code here:
        Composicion_IU frame = new Composicion_IU();
        escritorio.add(frame);
        Dimension deskopSize = escritorio.getSize();
        Dimension FrameSize = frame.getSize();
        frame.setLocation((deskopSize.width - FrameSize.width) / 2, (deskopSize.height - FrameSize.height) / 2);
        frame.show();                    
    }//GEN-LAST:event_cmbComposicionActionPerformed

    private void txtSerieKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSerieKeyPressed
        // TODO add your handling code here:
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDescripcion.requestFocus();
        }
    }//GEN-LAST:event_txtSerieKeyPressed

    private void txtDescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyPressed
        // TODO add your handling code here:
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtObservacion.requestFocus();
        }
    }//GEN-LAST:event_txtDescripcionKeyPressed

    private void txtObservacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionKeyPressed
        // TODO add your handling code here:
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDigemi.requestFocus();
        }
    }//GEN-LAST:event_txtObservacionKeyPressed

    private void txtDigemiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDigemiKeyPressed
        // TODO add your handling code here:
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbCondicion.requestFocus();
        }
    }//GEN-LAST:event_txtDigemiKeyPressed

    private void cmbCondicionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbCondicionKeyPressed
        // TODO add your handling code here:
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbCategoria.requestFocus();
        }
    }//GEN-LAST:event_cmbCondicionKeyPressed

    private void cmbCategoriaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbCategoriaKeyPressed
        // TODO add your handling code here:
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbLaboratorio.requestFocus();
        }
    }//GEN-LAST:event_cmbCategoriaKeyPressed

    private void cmbLaboratorioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbLaboratorioKeyPressed
        // TODO add your handling code here:
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbMedida.requestFocus();
        }
    }//GEN-LAST:event_cmbLaboratorioKeyPressed

    private void cmbMedidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMedidaKeyPressed
        // TODO add your handling code here:
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnNuevo.requestFocus();
        }
    }//GEN-LAST:event_cmbMedidaKeyPressed

    private void txtSerieFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSerieFocusGained
        // TODO add your handling code here:
        txtSerie.setBackground(Color.green);
    }//GEN-LAST:event_txtSerieFocusGained

    private void txtSerieFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSerieFocusLost
        // TODO add your handling code here:
        txtSerie.setBackground(Color.white);
    }//GEN-LAST:event_txtSerieFocusLost

    private void txtDescripcionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescripcionFocusGained
        // TODO add your handling code here:
        txtDescripcion.setBackground(Color.green);
    }//GEN-LAST:event_txtDescripcionFocusGained

    private void txtDescripcionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescripcionFocusLost
        // TODO add your handling code here:
        txtDescripcion.setBackground(Color.white);
    }//GEN-LAST:event_txtDescripcionFocusLost

    private void txtObservacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtObservacionFocusGained
        // TODO add your handling code here:
        txtObservacion.setBackground(Color.green);
    }//GEN-LAST:event_txtObservacionFocusGained

    private void txtObservacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtObservacionFocusLost
        // TODO add your handling code here:
        txtObservacion.setBackground(Color.white);
    }//GEN-LAST:event_txtObservacionFocusLost

    private void txtDigemiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDigemiFocusGained
        // TODO add your handling code here:
        txtDigemi.setBackground(Color.green);
    }//GEN-LAST:event_txtDigemiFocusGained

    private void txtDigemiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDigemiFocusLost
        // TODO add your handling code here:
         txtDigemi.setBackground(Color.white);
    }//GEN-LAST:event_txtDigemiFocusLost

    private void txtSerieKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSerieKeyTyped
        // TODO add your handling code here:
       char c = evt.getKeyChar();
        if (!Character.isDigit(c) || txtSerie.getText().length() >= 13) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSerieKeyTyped

    private void txtProductoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProductoFocusGained
        // TODO add your handling code here:
        txtProducto.setBackground(Color.green);
    }//GEN-LAST:event_txtProductoFocusGained

    private void txtProductoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProductoFocusLost
        // TODO add your handling code here:
        txtProducto.setBackground(Color.white);
    }//GEN-LAST:event_txtProductoFocusLost

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cmbCategoria;
    private javax.swing.JButton cmbComposicion;
    private javax.swing.JComboBox<String> cmbCondicion;
    private javax.swing.JComboBox<String> cmbLaboratorio;
    private javax.swing.JComboBox<String> cmbMedida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_reporte_producto;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDigemi;
    private javax.swing.JTextField txtIdCategoria;
    private javax.swing.JTextField txtIdMarca;
    private javax.swing.JTextField txtIdMedida;
    private javax.swing.JTextField txtObservacion;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtSerie;
    // End of variables declaration//GEN-END:variables
}
