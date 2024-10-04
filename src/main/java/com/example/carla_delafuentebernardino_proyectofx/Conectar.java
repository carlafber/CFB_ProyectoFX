package com.example.carla_delafuentebernardino_proyectofx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Conectar {
    private static Connection connection;
    public static Connection conectar() throws SQLException {

        if (connection == null || connection.isClosed()) {
            String url = "jdbc:mysql://localhost:3306/Tienda?serverTimezone=UTC";
            String username = "root";
            String password = "toor";
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}

/*public class Conectar {
    private static Connection con;

    public static Connection conectar() throws ClassNotFoundException, SQLException, IOException {
        Properties properties = new Properties();
        String host = "";
        String port = "";
        String name = "";
        String username = "";
        String password = "";

        try {
            properties.load(new FileInputStream(new File("src/main/resources/configuration/database.properties")));
            host = properties.getProperty("host");
            port = properties.getProperty("port");
            name = properties.getProperty("name");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                    username, password);

            return con;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void desconectar() throws SQLException {
        if (con != null) {
            con.close();
        }
    }


    public static boolean estadoConexion() {
        boolean ok = false;
        try {
            if (con != null && con.isValid(0)) {
                ok = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ok;
    }
}*/
