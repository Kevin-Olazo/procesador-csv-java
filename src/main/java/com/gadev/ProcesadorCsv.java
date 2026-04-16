package com.gadev;

import com.gadev.modelo.Venta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProcesadorCsv {
     private static final String SEPARADOR = ",";

     public List<Venta> cargarVentasDesdeCsv(String rutaArchivo) throws IOException{
         List<Venta> lista = new ArrayList<>();

         try {
             BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo));
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
                     System.out.println("Error: " + e.getMessage());
                 }

             }

         } catch (IOException e){
             System.out.println("Error: " + e.getMessage());
         }

         return lista;
     }

     public void imprimirVentas(List<Venta> lista){
         for(Venta v : lista){
             System.out.println(v);
         }
     }
};
