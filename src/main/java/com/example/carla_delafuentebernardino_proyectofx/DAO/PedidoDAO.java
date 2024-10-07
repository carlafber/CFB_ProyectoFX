package com.example.carla_delafuentebernardino_proyectofx.DAO;

import com.example.carla_delafuentebernardino_proyectofx.classes.Pedido;
import com.example.carla_delafuentebernardino_proyectofx.classes.Producto;
import com.example.carla_delafuentebernardino_proyectofx.util.Alerta;
import com.example.carla_delafuentebernardino_proyectofx.util.Conectar;

import java.io.IOException;
import java.sql.*;

public class PedidoDAO {
    public static boolean anadir(Producto producto, Pedido pedido) {
        int cambios;
        String sql = "INSERT INTO Pedido (producto_id, cantidad, total) VALUES (?,?,?)";
        try {
            Connection connection = Conectar.conectar();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, producto.getId());
            statement.setInt(2, pedido.getCantidad());
            statement.setDouble(3, pedido.getTotal());

            cambios = statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int numero_nuevo = resultSet.getInt(1);
                pedido.setNumero(numero_nuevo);
            }

            return cambios == 1;
        } catch (SQLException e) {
            Alerta.mensajeError(e.getMessage());
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
