package com.gadev;

import com.gadev.modelo.Venta;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProcesadorCsv procesadorCsv = new ProcesadorCsv();

        String rutaEntrada = "ventas.csv";
        String rutaSalida = "reporte_final.txt";

        try {
            // Paso A: Leer el archivo
            List<Venta> lista = procesadorCsv.cargarVentasDesdeCsv(rutaEntrada);
            System.out.println("Éxito: Se cargaron " + lista.size() + " transacciones validas.");

            procesadorCsv.imprimirVentas(lista);
            procesadorCsv.generarReporteResumen(lista, rutaSalida);
            System.out.println("Reporte generado exitosamente en el archivo: " + rutaSalida);
        } catch (IOException e) {
            System.out.println("Error critico de lectura/escritura: " + e.getMessage());
        }

    }
}