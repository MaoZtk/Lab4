import java.io.*;
import java.util.*;

// Interfaz para usuario
interface PlanUsuario {
    // Devuelve la duración máxima permitida para las reuniones
    int obtenerDuracionMaxima();
    // Devuelve la cantidad máxima de invitados permitida
    int obtenerMaxInvitados();
    // Devuelve el límite de reuniones permitidas por dia
    int obtenerMaxReunionesDiarias();
    // Devuelve las últimas “cantidad” reuniones
    List<Reunion> obtenerUltimasReuniones(int cantidad);
    // Imprime los últimos “cantidad” contactos
    void imprimirListadoContactos(int cantidad);
}
