package model;

import Utils.Utils;

public class SopaDeLetras {

    private static char[][] sopa;

    public static char[][] getSopa() {
        return sopa;
    }

    public static boolean[][] getDescubiertas() {
        return descubiertas;
    }

    private static boolean[][] descubiertas;


    public SopaDeLetras(int f, int c) {
        sopa = new char[f][c];
        descubiertas = new boolean[sopa.length][sopa[0].length];
    }


    public static boolean buscarPalabra(String palabra) {
        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa[i].length; j++) {
                if (sopa[i][j] == palabra.charAt(0)) {
                    //Comprobar vertical
                    int[][] coords = comprobarPalabra(palabra, i, j);
                    if (coords != null) {
                        marcarLetrasDescubiertas(coords);
                        return true;
                    } else {
                        //Comprobar hoirzontal
                        coords = comprobarPalabra(palabra, i, j);
                        if (coords != null) {
                            marcarLetrasDescubiertas(coords);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    public static void marcarLetrasDescubiertas(int[][] coords) {
    for (int i = 0; i < coords.length; i++) {
            descubiertas[coords[i][0]][coords[i][1]] = true;
        }
    }


    public static int[][] comprobarPalabra(String palabra, int i, int j, boolean vertical) {
        int[][] coords = new int[palabra.length()][2];
        int k = 0;
        while (k < palabra.length() ) {
            if (i < sopa.length && j < sopa[i].length && sopa[i][j] == palabra.charAt(k)) {
                coords[k][0] = i;
                coords[k][1] = j;
                if (vertical)
                    i++;
                else
                    j++;
                k++;
            } else {
                return null;
            }
        }
        return coords;
    }
    public static int[][] comprobarPalabra(String palabra, int i, int j) {
        return comprobarPalabra(palabra, i, j, true);
    }


    public static char[][] crearSopadeLetras(String cadena) {
        crearSopadeLetrasRecursivo(sopa, cadena, 0, 0);
        return sopa;
    }

    private static void crearSopadeLetrasRecursivo(char[][] sopa, String cadena, int i, int j) {
        if (i < sopa.length && j < sopa[i].length) {
            sopa[i][j] = cadena.charAt(i * sopa[i].length + j);
            crearSopadeLetrasRecursivo(sopa, cadena, i, j + 1);  // Crida recursiva para columna
            crearSopadeLetrasRecursivo(sopa, cadena, i + 1, j);  // Crida recirsiva para fila
        }
    }
}