package model;

public class SopaDeLetras {

    private char[][] sopa;
    private boolean[][] descubiertas;
    private static int palabrasEncontradas;

    public SopaDeLetras(int f, int c) {
        sopa = new char[f][c];
        descubiertas = new boolean[f][c];
        palabrasEncontradas = 0;
    }

    public char[][] getSopa() {
        return sopa;
    }

    public boolean[][] getDescubiertas() {
        return descubiertas;
    }

    public static int getPalabrasEncontradas() {
        return palabrasEncontradas;
    }

    public void setSopa(char[][] nuevaSopa) {
        this.sopa = nuevaSopa;
    }

    public void setDescubiertas(boolean[][] nuevasDescubiertas) {
        this.descubiertas = nuevasDescubiertas;
    }

    public boolean buscarPalabra(String palabra) {
        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa[i].length; j++) {
                if (sopa[i][j] == palabra.charAt(0)) {
                    // Comprobar vertical
                    int[][] coordsVertical = comprobarPalabra(palabra, i, j, true);
                    if (coordsVertical != null) {
                        marcarLetrasDescubiertas(coordsVertical);
                        palabrasEncontradas++;
                        if (palabrasEncontradas == 5) {
                            return true; // Se encontraron 5 palabras, terminar la búsqueda
                        }
                    } else {
                        // Comprobar horizontal si no se encontró en vertical
                        int[][] coordsHorizontal = comprobarPalabra(palabra, i, j, false);
                        if (coordsHorizontal != null) {
                            marcarLetrasDescubiertas(coordsHorizontal);
                            palabrasEncontradas++;
                            if (palabrasEncontradas == 5) {
                                return true; // Se encontraron 5 palabras, terminar la búsqueda
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void marcarLetrasDescubiertas(int[][] coords) {
        for (int[] coord : coords) {
            descubiertas[coord[0]][coord[1]] = true;
        }
    }

    public int[][] comprobarPalabra(String palabra, int i, int j, boolean vertical) {
        int[][] coords = new int[palabra.length()][2];
        int k = 0;
        int incrementoI = (vertical) ? 1 : 0;
        int incrementoJ = (vertical) ? 0 : 1;

        while (k < palabra.length() && i < sopa.length && j < sopa[i].length) {
            if (sopa[i][j] == palabra.charAt(k)) {
                coords[k][0] = i;
                coords[k][1] = j;
                i += incrementoI;
                j += incrementoJ;
                k++;
            } else {
                return null; // Si no coincide la letra, retornar null
            }
        }

        if (k == palabra.length()) {
            return coords; // Si se encontró la palabra completa, retornar las coordenadas
        } else {
            return null; // Si no se encontró completa, retornar null
        }
    }

    public void crearSopadeLetras(String cadena) {
        crearSopadeLetrasRecursivo(sopa, cadena, 0, 0);
    }

    private void crearSopadeLetrasRecursivo(char[][] sopa, String cadena, int i, int j) {
        if (i < sopa.length && j < sopa[i].length) {
            sopa[i][j] = cadena.charAt(i * sopa[i].length + j);
            crearSopadeLetrasRecursivo(sopa, cadena, i, j + 1);  // Llamada recursiva para columna
            crearSopadeLetrasRecursivo(sopa, cadena, i + 1, j);  // Llamada recursiva para fila
        }
    }
}
