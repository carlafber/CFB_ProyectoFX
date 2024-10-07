package com.example.carla_delafuentebernardino_proyectofx.DAO;

import com.example.carla_delafuentebernardino_proyectofx.classes.Producto;
import com.example.carla_delafuentebernardino_proyectofx.util.Alerta;
import com.example.carla_delafuentebernardino_proyectofx.util.Conectar;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAO {
    public static Producto obtener(int id) {
        Producto producto = null;
        String sql = "SELECT * FROM producto WHERE id = ?";

        try {
            Connection connection = Conectar.conectar(); //Conectar a la bbdd
            PreparedStatement statement = connection.prepareStatement(sql); //Preparar la consulta SQL
            statement.setInt(1, id); //Establecer el id de la reserva a obtener

            try (ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    String nombre = resultSet.getString("nombre");
                    double precio = resultSet.getDouble("precio");
                    int cantidad = resultSet.getInt("cantidad");

                    producto = new Producto(nombre, precio, cantidad);
                    producto.setId(id);
                }
            }
        } catch (SQLException e) {
            Alerta.mensajeError(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }

    public static void actualizarCantidad(int id, int cantidad) {
        String sql = "UPDATE producto SET cantidad = cantidad - ? WHERE id = ?";
        try {
            Connection connection = Conectar.conectar();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, cantidad);
            statement.setInt(2, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            Alerta.mensajeError(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}