package com.example.carla_delafuentebernardino_proyectofx.controller;

import com.example.carla_delafuentebernardino_proyectofx.*;
import com.example.carla_delafuentebernardino_proyectofx.DAO.ProductoDAO;
import com.example.carla_delafuentebernardino_proyectofx.classes.Producto;
import com.example.carla_delafuentebernardino_proyectofx.util.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class InicioController {

    @FXML
    private Button bt_aro;

    @FXML
    private Button bt_cinta;

    @FXML
    private Button bt_cuerda;

    @FXML
    private Button bt_mazas;

    @FXML
    private Button bt_mazul;

    @FXML
    private Button bt_mmorada;

    @FXML
    private Button bt_mrosa;

    @FXML
    private Button bt_pelota;

    @FXML
    void onAroClick(ActionEvent event) {
        ir_ticket(bt_aro, 2);
    }

    @FXML
    void onCintaClick(ActionEvent event) {
        ir_ticket(bt_cinta, 3);
    }

    @FXML
    void onCuerdaClick(ActionEvent event) {
        ir_ticket(bt_cuerda, 5);
    }

    @FXML
    void onMAzulClick(ActionEvent event) {
        ir_ticket(bt_mazul, 8);
    }

    @FXML
    void onMMoradaClick(ActionEvent event) {
        ir_ticket(bt_mmorada, 7);
    }

    @FXML
    void onMRosaClick(ActionEvent event) {
        ir_ticket(bt_mrosa, 6);
    }

    @FXML
    void onMazasClick(ActionEvent event) {
        ir_ticket(bt_mazas, 4);
    }

    @FXML
    void onPelotaClick(ActionEvent event) {
        ir_ticket(bt_pelota, 1);
    }

    private void ir_ticket(Button boton, int id_producto) {
        Producto producto = ProductoDAO.obtener(id_producto);
        if (producto == null) {
            Alerta.mensajeError("Producto no encontrado.");
            return;
        }
        if(producto.getCantidad() == 0) {
            Alerta.mensajeInfo("INFO", "No hay unidades disponibles del producto.");
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(TiendaApplication.class.getResource("ticket.fxml"));
            Parent root = fxmlLoader.load();
            TicketController controller = fxmlLoader.getController();
            controller.salida(producto);

            Scene scene = new Scene(root);
            Stage stage = (Stage) boton.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            Alerta.mensajeError(e.getMessage());
        }
    }

}
