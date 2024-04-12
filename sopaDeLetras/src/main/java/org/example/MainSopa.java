package org.example;

import Utils.Utils;
import model.SopaDeLetras;

public class MainSopa {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        SopaDeLetras sopaDeLetras = new SopaDeLetras(10, 10);
        System.out.println("Bienvenidos a la sopa de letras");
        String cadena = Utils.LeerString("Introduce una cadena de 100 letras:", 100, 100);
        sopaDeLetras.crearSopadeLetras(cadena);

        int palabrasEncontradas = 0;

        while (palabrasEncontradas < 5) {
            mostrarSopaDeLetras(sopaDeLetras.getSopa(), sopaDeLetras.getDescubiertas());
            String palabra = Utils.LeerString("Introduce la palabra a buscar:", 1, 10);

            if (sopaDeLetras.buscarPalabra(palabra)) {
                System.out.println("Palabra encontrada");
                palabrasEncontradas = SopaDeLetras.getPalabrasEncontradas();
                if (palabrasEncontradas == 5) {
                    System.out.println("Has encontrado todas las palabras.");
                    break;
                }
            } else {
                System.out.println("Palabra no encontrada");
            }
        }
    }

    private static void mostrarSopaDeLetras(char[][] sopa, boolean[][] descubiertas) {
        System.out.println("Sopa de letras:");
        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa[i].length; j++) {
                pintarLetra(sopa[i][j], descubiertas[i][j], ANSI_RED);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    private static void pintarLetra(char c, boolean b, String color) {
        if (b)
            System.out.print(color + c + ANSI_RESET);
        else
            System.out.print(c);
    }
}
