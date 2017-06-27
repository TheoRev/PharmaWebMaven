package com.hrevfdz.report;

import com.hrevfdz.util.AccessKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private static Conexion instancia;

    private static String driver = null;
    private static String usuario = null;
    private static String password = null;
    private static String url = null;

    private Conexion() {
    }

    static {
        try {
            url = AccessKey.URL;
            driver = AccessKey.DRIVER;
            usuario = AccessKey.USER;
            password = AccessKey.PASSWORD;

            Class.forName(driver);

        } catch (ClassNotFoundException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static Connection getConexion() throws SQLException {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException e) {
            throw e;
        }
        return conexion;
    }

    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }

        return instancia;
    }
}
