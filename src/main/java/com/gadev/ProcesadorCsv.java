package com.gadev;

import com.gadev.modelo.Venta;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ProcesadorCsv {
     private static final String SEPARADOR = ",";

     public List<Venta> cargarVentasDesdeCsv(String rutaArchivo) throws IOException{
         List<Venta> lista = new ArrayList<>();

         try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
             reader.readLine();

             String linea;
             while((linea = reader.readLine()) != null){
                 String[] datos = linea.split(SEPARADOR);

                 try {
                     int id = Integer.parseInt(datos[0]);
                     LocalDate fecha = LocalDate.parse(datos[1]);
                     String producto = datos[2];
                     int cantidad = Integer.parseInt(datos[3]);
                     double precioUnitario = Double.parseDouble(datos[4]);

                     lista.add(new Venta(id, fecha, producto, cantidad, precioUnitario));
                 } catch (NumberFormatException | DateTimeParseException e) {
                     System.out.println("Error de formato en la linea: " + linea);
                 }
             }

         }

         return lista;
     }

     public void generarReporteResumen(List<Venta> ventas, String rutaSalida) throws IOException{
         try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaSalida))) {
             writer.write("=== REPORTE DE VENTAS ===");
             writer.newLine();
             writer.write("Total de transacciones validas: " + ventas.size());
             writer.newLine();

             int totalProductos = calcularCantidadProductosVendidos(ventas);
             double ingresoTotal = calcularSubtotalProductos(ventas);

             writer.write("Total de artículos vendidos: " + totalProductos);
             writer.newLine();
             writer.write("Ingreso Total Bruto: $" + ingresoTotal);
             writer.newLine();
             writer.write("=========================");
         }
     }

     public int calcularCantidadProductosVendidos(List<Venta> ventas){
         return ventas.stream()
                 .mapToInt(Venta::getCantidad)
                 .sum();
     }

     public double calcularSubtotalProductos(List<Venta> ventas){
         return ventas.stream()
                 .mapToDouble(Venta::getSubtotal)
                 .sum();
     }

     public void imprimirVentas(List<Venta> lista){
         for(Venta v : lista){
             System.out.println(v);
         }
     }
};
