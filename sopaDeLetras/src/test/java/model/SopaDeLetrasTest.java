package model;

import model.SopaDeLetras;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SopaDeLetrasTest {

    private SopaDeLetras sopa;

    @BeforeEach
    void setUp() {
        // Configuramos una sopa de letras de 5x5 para las pruebas
        sopa = new SopaDeLetras(5, 5);

        // Creamos una sopa de letras específica para cada prueba
        char[][] matrizSopa = {
                {'H', 'O', 'L', 'A', 'M'},
                {' ', ' ', ' ', ' ', 'U'},
                {' ', ' ', ' ', ' ', 'N'},
                {' ', ' ', ' ', ' ', 'D'},
                {' ', ' ', ' ', ' ', 'O'}
        };
        sopa.setSopa(matrizSopa);
    }

    @Test
    void buscarPalabra_PalabraEncontradaVerticalmente() {
        assertTrue(sopa.buscarPalabra("HOLA"));
    }

    @Test
    void buscarPalabra_PalabraNoEncontrada() {
        assertFalse(sopa.buscarPalabra("MUNDO"));
    }

    @Test
    void marcarLetrasDescubiertas_MarcaLetrasCorrectamente() {
        boolean[][] descubiertas = new boolean[5][5]; // Array de 5x5 para la sopa
        sopa.setDescubiertas(descubiertas);

        int[][] coords = {{0, 0}, {0, 1}, {0, 2}, {0, 3}};
        sopa.marcarLetrasDescubiertas(coords);

        assertTrue(sopa.getDescubiertas()[0][0]); // Letra 'H' marcada como descubierta
        assertTrue(sopa.getDescubiertas()[0][1]); // Letra 'O' marcada como descubierta
        assertTrue(sopa.getDescubiertas()[0][2]); // Letra 'L' marcada como descubierta
        assertTrue(sopa.getDescubiertas()[0][3]); // Letra 'A' marcada como descubierta
    }

    @Test
    void comprobarPalabra_DevuelveCoordenadasCorrectas() {
        int[][] coords = sopa.comprobarPalabra("HOLA", 0, 0, true); // Buscar "HOLA" desde (0,0) verticalmente
        assertNotNull(coords); // Debería devolver coordenadas válidas
        assertEquals(4, coords.length); // Debería haber 4 coordenadas (una por cada letra de "HOLA")
    }

    @Test
    void crearSopadeLetras_CreaSopaConLetrasCorrectas() {
        String cadena = "ABCDEFGHIJ";
        sopa.crearSopadeLetras(cadena);

        char[][] matrizSopa = sopa.getSopa();
        assertNotNull(matrizSopa);

        assertEquals('A', matrizSopa[0][0]);
        assertEquals('B', matrizSopa[0][1]);
        assertEquals('C', matrizSopa[0][2]);
        assertEquals('D', matrizSopa[1][0]);
        assertEquals('E', matrizSopa[1][1]);
        assertEquals('F', matrizSopa[1][2]);
        assertEquals('G', matrizSopa[2][0]);
        assertEquals('H', matrizSopa[2][1]);
        assertEquals('I', matrizSopa[2][2]);
        assertEquals('J', matrizSopa[3][0]);
    }
}
