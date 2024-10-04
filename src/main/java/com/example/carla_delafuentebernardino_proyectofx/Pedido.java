package com.example.carla_delafuentebernardino_proyectofx;

public class Pedido {
    int numero;
    int producto_id;
    int cantidad;
    double total;

    public Pedido(int producto_id, int cantidad, double total) {
        this.producto_id = producto_id;
        this.cantidad = cantidad;
        this.total = total;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
