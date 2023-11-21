
/**
 * Universidad del Valle de Guatemala - POO
 * Marielos Ortíz, Sandra Pineda, Luisa Jiménez
 * Laboratorio final
 */

import java.io.*;
import java.util.*;

interface PlanUsuario {
    int obtenerDuracionMaxima();

    int obtenerMaxInvitados();

    /**
     * Devuelve la duración máxima permitida para las reuniones.
     *
     * @return La duración máxima permitida en minutos.
     */
    int obtenerMaxReunionesDiarias();

    /**
     * Devuelve el límite de reuniones permitidas por día.
     *
     * @return El límite de reuniones permitidas por día.
     */
    List<Reunion> obtenerUltimasReuniones(int cantidad);

    /**
     * Imprime los últimos "cantidad" contactos del usuario.
     *
     * @param cantidad La cantidad de contactos a imprimir.
     */
    void imprimirListadoContactos(int cantidad);
}
