package com.gadev.modelo;

import java.time.LocalDate;

public class Venta {
    private int id;
    private LocalDate fecha;
    private String producto;
    private int cantidad;
    private double precioUnitario;

    public Venta(int id, LocalDate fecha, String producto, int cantidad, double precioUnitario) {
        this.id = id;
        this.fecha = fecha;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal(){
        return cantidad * precioUnitario;
    }

    public int getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    @Override
    public String toString() {
        return "[" +  id + ", " + fecha + ", " + producto + ", " + cantidad + ", " + precioUnitario + "]";
    }
}
