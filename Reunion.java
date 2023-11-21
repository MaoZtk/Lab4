
/**
 * Universidad del Valle de Guatemala - POO
 * Marielos Ortíz, Sandra Pineda, Luisa Jiménez
 * Laboratorio final
 */

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Clase que representa una reunión en el sistema de gestión de reuniones.
 */

class Reunion {
    // Nombre de la reunión
    private String nombre;
    // Pin de la reunión
    private String pin;
    // Notas de la reunión
    private String notas;
    // Duración de la reunión
    private int duracion;
    // Estado a mostrar mientras se está en la reunión
    private String estado;
    // Fecha de la reunión
    private String fecha;
    // Hora de la reunión
    private int hora;
    // Lista de invitados de la reunión
    private List<String> listaInvitados;
    // Usuario que crea la reunión
    private Usuario usuario;
    // Cantidad máxima de invitados permitida
    private int maxInvitados;

    /**
     * Constructor para la creación de una reunión con detalles específicos.
     *
     * @param nombre2       Nombre de la reunión.
     * @param pin2          Pin de la reunión.
     * @param notas2        Notas de la reunión.
     * @param duracion2     Duración de la reunión en minutos.
     * @param estado2       Estado a mostrar durante la reunión.
     * @param fecha2        Fecha de la reunión.
     * @param hora2         Hora de la reunión.
     * @param invitados     Lista de invitados a la reunión.
     * @param usuarioActual Usuario que crea la reunión.
     */
    public Reunion(String nombre2, String pin2, String notas2, int duracion2, String estado2, String fecha2, int hora2,
            List<String> invitados, Usuario usuarioActual) {
        this.nombre = nombre2;
        this.pin = pin2;
        this.notas = notas2;
        this.duracion = duracion2;
        this.estado = estado2;
        this.fecha = fecha2;
        this.hora = hora2;
        this.listaInvitados = invitados;
        this.usuario = usuarioActual;

        // Utilizar operador ternario para asignar maxInvitados
        this.maxInvitados = (usuarioActual != null) ? usuarioActual.obtenerMaxInvitados() : 0;
    }

    /**
     * Constructor para la creación de una reunión asociada a un usuario.
     *
     * @param usuario Usuario que crea la reunión.
     */
    public Reunion(Usuario usuario) {
        this.usuario = usuario;
        if (usuario != null) { // Verifica que el objeto usuario no sea nulo.
            this.maxInvitados = usuario.obtenerMaxInvitados(); // Invoca el método obtenerMaxInvitados sobre el objeto
                                                               // usuario.
        } else {
            this.maxInvitados = 0; // Asigna un valor por defecto al atributo maxInvitados.
        }
    }

    // Reinicia el proceso de definición de reunión
    public void volverEmpezar() {
        this.nombre = null;
        this.pin = null;
        this.notas = null;
        this.duracion = 0;
        this.estado = null;
        this.fecha = null;
        this.hora = 0;
        this.listaInvitados = null;
    }

    // Imprime los detalles de la reunión
    public void imprimirReunion() {
        System.out.println("Nombre de la reunión: " + this.nombre);
        System.out.println("Pin de la reunión: " + this.pin);
        System.out.println("Notas de la reunión: " + this.notas);
        System.out.println("Duración de la reunión: " + this.duracion + " minutos");
        System.out.println("Estado a mostrar: " + this.estado);
        System.out.println("Fecha de la reunión: " + this.fecha);
        System.out.println("Hora de la reunión: " + this.hora);
        System.out.println("Lista de invitados: " + this.listaInvitados);
    }

    // Devuelve el nombre de la reunión
    public String getNombre() {
        return this.nombre;
    }

    // Devuelve el pin de la reunión
    public String getPin() {
        return this.pin;
    }

    // Devuelve las notas de la reunión
    public String getNotas() {
        return this.notas;
    }

    // Devuelve la duración de la reunión
    public int getDuracion() {
        return this.duracion;
    }

    // Devuelve el estado a mostrar
    public String getEstado() {
        return this.estado;
    }

    // Devuelve la fecha de la reunión
    public String getFecha() {
        return this.fecha;
    }

    // Devuelve la hora de la reunión
    public int getHora() {
        return this.hora;
    }

    // Devuelve la lista de invitados
    public List<String> getListaInvitados() {
        return this.listaInvitados;
    }

    // Devuelve el usuario que crea la reunión
    public Usuario getUsuario() {
        return this.usuario;
    }

    // Devuelve la cantidad máxima de invitados permitida
    public int getMaxInvitados() {
        return this.maxInvitados;
    }

    public void setFecha(String fecha2) {
    }

    public void setEstado(String estado2) {
    }

    public void setListaInvitados(List<String> listaInvitados2) {
    }

    public void setDuracion(int duracion2) {
    }

    public void setNotas(String notas2) {
    }

    public void setPin(String pin2) {
    }

    public void setNombre(String nombre2) {
    }

    public void setHora(String hora2) {
    }
}