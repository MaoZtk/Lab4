
/**
 * Universidad del Valle de Guatemala - POO
 * Marielos Ortíz, Sandra Pineda, Luisa Jiménez
 * Laboratorio final
 */

import java.io.*;
import java.util.*;

/**
 * Clase que contiene métodos relacionados con la gestión del calendario.
 * Permite manejar reuniones, contactos y está asociada a un usuario.
 */

class Calendario {
    /** Lista de reuniones del calendario */
    private List<Reunion> listaReuniones;
    /** Lista de contactos del calendario */
    private List<String> listaContactos;
    /** Usuario asociado al calendario */
    private Usuario usuario;

    /**
     * Constructor de la clase Calendario.
     *
     * @param usuario El usuario asociado al calendario.
     */
    public Calendario(Usuario usuario) {
        this.listaReuniones = new ArrayList<>();
        this.listaContactos = new ArrayList<>();
        this.usuario = usuario;
    }

    /**
     * Obtiene las últimas reuniones según el tipo de usuario.
     *
     * @return Lista de las últimas reuniones.
     */
    public List<Reunion> obtenerUltimasReuniones() {
        return this.usuario.obtenerUltimasReuniones(this.listaReuniones.size());
    }

    /**
     * Imprime los últimos contactos según el tipo de usuario.
     */
    public void imprimirListadoContactos() {
        this.usuario.imprimirListadoContactos(this.listaContactos.size());
    }

    /**
     * Añade una reunión al calendario.
     *
     * @param reunion La reunión a añadir.
     */
    public void añadirReunion(Reunion reunion) {
        this.listaReuniones.add(reunion);
        CsvManager.escribirReuniones(Arrays.asList(reunion));
    }

    /**
     * Añade un contacto al calendario.
     *
     * @param contacto El contacto a añadir.
     */
    public void añadirContacto(String contacto) {
        this.listaContactos.add(contacto);
    }

    /**
     * Devuelve la lista de reuniones del calendario.
     *
     * @return Lista de reuniones.
     */
    public List<Reunion> getListaReuniones() {
        return this.listaReuniones;
    }

    /**
     * Devuelve la lista de contactos del calendario.
     *
     * @return Lista de contactos.
     */
    public List<String> getListaContactos() {
        return this.listaContactos;
    }

    /**
     * Devuelve el usuario asociado al calendario.
     *
     * @return El usuario asociado al calendario.
     */
    public Usuario getUsuario() {
        return this.usuario;
    }

    /**
     * Establece la lista de reuniones del calendario.
     *
     * @param leerReuniones La lista de reuniones a establecer.
     */
    public void setListaReuniones(List<Reunion> leerReuniones) {
    }

    /**
     * Establece la lista de contactos del calendario.
     *
     * @param leerContactos La lista de contactos a establecer.
     */
    public void setListaContactos(List<String> leerContactos) {
    }
}