import java.io.*;
import java.util.*;


interface PlanUsuario {
    int obtenerDuracionMaxima();
    int obtenerMaxInvitados();
    // Devuelve el límite de reuniones permitidas por dia
    int obtenerMaxReunionesDiarias();
    // Devuelve las últimas “cantidad” reuniones
    List<Reunion> obtenerUltimasReuniones(int cantidad);
    // Imprime los últimos “cantidad” contactos
    void imprimirListadoContactos(int cantidad);
}
