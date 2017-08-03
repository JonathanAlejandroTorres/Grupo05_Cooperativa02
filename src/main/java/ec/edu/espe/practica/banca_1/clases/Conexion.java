package ec.edu.espe.practica.banca_1.clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class Conexion {

    private static java.sql.Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public Conexion() {
        conexion = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/cooperativa2", "root", "espe2016");

            if (conexion != null) {
                System.out.println("Conexion Establecida...");
            } else {
                System.out.println("Error en la Conexion...");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("error al conectar" + ex);
        }
    }

    public boolean ejecutarSQL(String sql) {
        try {
            Statement sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    public ResultSet ejecutarSQLSelect(String sql) {
        ResultSet resultado;
        try {
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("ejecutarSQLSelect nulo");
            return null;
        }

        return resultado;
    }

    public void ejecutarSQLInsert(int cod, String cod_cuen, String tipo, String fecha, float monto, float saldo) {
        System.out.println("cuenta"+cod);
        try {
            PreparedStatement cs = conexion.prepareStatement("INSERT INTO movimiento(CODIGO_MOVIMIENTO,CODIGO_CUENTA,TIPO,FECHA,MONTO,SALDO)VALUES(?,?,?,?,?,?)");
            cs.setInt(1, cod);
            cs.setString(2, cod_cuen);
            cs.setString(3, tipo);
            cs.setString(4, fecha);
            cs.setString(5, String.valueOf(monto));
            cs.setString(6, String.valueOf(saldo));
            int numeroRegistro = cs.executeUpdate();
            if (numeroRegistro > 0) {
                String mensaje = "Registro correcto";
            } else {
                String mesna = "Error al guardar";
            }
        } catch (SQLException ex) {
            System.out.println("ejecutarSQLSelect nulo");
            ex.printStackTrace();
        }

    }
}
