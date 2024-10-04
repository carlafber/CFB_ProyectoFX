package com.example.carla_delafuentebernardino_proyectofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class TicketController {

    @FXML
    private Button bt_confirmar;

    @FXML
    private Button bt_salir;

    @FXML
    private Button bt_volver;

    @FXML
    private ComboBox<Integer> cb_cantidad;

    @FXML
    private Label txt_precio;

    @FXML
    private Label txt_producto;

    @FXML
    private Label txt_total;

    Producto producto;


    @FXML
    void onConfirmarClick(ActionEvent event) {
        Pedido pedido;
        if(cb_cantidad.getValue() == 0){
            Alerta.mensajeError("Seleccione un cantidad del producto.");
        } else{
            String total = txt_total.getText();
            total = total.replace("€", "").trim();
            pedido = new Pedido(producto.getId(), cb_cantidad.getValue(), Double.valueOf(total));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMACIÓN");
            alert.setHeaderText(null);
            alert.setContentText("El pedido se va a confirmar, asegúrate de que los datos son correctos.");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    if(PedidoDAO.anadir(producto, pedido)){
                        Alerta.mensajeInfo("ÉXITO", "Pedido realizado correctamente.");

                        ProductoDAO.actualizarCantidad(producto.getId(), cb_cantidad.getValue());

                        try{
                            FXMLLoader fxmlLoader = new FXMLLoader(TiendaApplication.class.getResource("inicio.fxml"));
                            Parent root = fxmlLoader.load();
                            InicioController controller = fxmlLoader.getController();

                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                        } catch (IOException e) {
                            Alerta.mensajeError(e.getMessage());
                        }
                    }
                }
            });
        }
    }

    @FXML
    void onSalirClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void onVolverClick(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(TiendaApplication.class.getResource("inicio.fxml"));
            Parent root = fxmlLoader.load();
            InicioController controller = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = (Stage) bt_volver.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            Alerta.mensajeError(e.getMessage());
        }

    }

    public void salida(Producto producto) {
        this.producto = producto;
        txt_producto.setText(producto.getNombre());
        txt_precio.setText(producto.getPrecio() + "€");
        cb_cantidad.getItems().clear();
        for (int i = 1; i <= producto.getCantidad(); i++) {
            cb_cantidad.getItems().add(i);
        }
        if (!cb_cantidad.getItems().isEmpty()) {
            cb_cantidad.setValue(0);
        }

        cb_cantidad.valueProperty().addListener((observable, oldValue, newValue) -> {
            txt_total.setText(calcularTotal(producto.getPrecio()) + "€");
        });
        if(cb_cantidad.getItems().isEmpty()){
            Alerta.mensajeError("No hay cantidad del producto.");
        }
    }

    private double calcularTotal(double precio) {
        double total;
        int cantidad = cb_cantidad.getValue();
        if(cantidad != 0){
            total = precio * cantidad;
        } else {
            total = 0;
        }
        return total;
    }

}
