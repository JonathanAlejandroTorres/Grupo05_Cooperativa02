/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.practica.banca_1.clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jeffe
 */
public class Prestamo {

    private final Conexion conn;
    private ResultSet resultado;
    private double monto;
    private int tiempo;

    public Prestamo(double monto, int tiempo) {
        this.monto = monto;
        this.tiempo = tiempo;
        conn = new Conexion();
    }

    public int monto() {
        String[] Datos = new String[5];
        DefaultTableModel model = new DefaultTableModel();
        String cuenta = "10023";
        try {
            resultado = conn.ejecutarSQLSelect("select codigo_movimiento,tipo,Extract(month from fecha),monto,saldo from movimiento where codigo_cuenta like '%" + cuenta + "%'");
            while (resultado.next()) {
                Datos[0] = resultado.getString(1);
                System.out.println("codigo  " + Datos[0]);
                Datos[1] = resultado.getString(2);
                System.out.println("tipo  " + Datos[1]);
                Datos[2] = resultado.getString(3);
                System.out.println("Fecha  " + Datos[2]);
                Datos[3] = resultado.getString(4);
                System.out.println("Monto " + Datos[3]);
                Datos[4] = resultado.getString(5);
                System.out.println("Saldo " + Datos[4]);

                //  model.addRow(Datos);
            }

            System.out.println("");
            //  Mostrar.setModel(model);
        } catch (SQLException ex) {
        }

        return 0;
    }
}
