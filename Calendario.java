import java.io.*;
import java.util.*;

// Clase que contiene métodos relacionados con la gestión del calendario
class Calendario {
    // Lista de reuniones del calendario
    private List<Reunion> listaReuniones;
    // Lista de contactos del calendario
    private List<String> listaContactos;
    // Usuario asociado al calendario
    private Usuario usuario;

    // Constructor
    public Calendario(Usuario usuario) {
        this.listaReuniones = new ArrayList<>();
        this.listaContactos = new ArrayList<>();
        this.usuario = usuario;
    }

    // Obtiene las últimas reuniones según el tipo de usuario
    public List<Reunion> obtenerUltimasReuniones() {
        return this.usuario.obtenerUltimasReuniones(this.listaReuniones.size());
    }

    // Imprime los últimos contactos según el tipo de usuario
    public void imprimirListadoContactos() {
        this.usuario.imprimirListadoContactos(this.listaContactos.size());
    }

    // Añade una reunión al calendario
    public void añadirReunion(Reunion reunion) {
        this.listaReuniones.add(reunion);
        CsvManager.escribirReuniones(Arrays.asList(reunion));
    }

    // Añade un contacto al calendario
    public void añadirContacto(String contacto) {
        this.listaContactos.add(contacto);
    }

    // Devuelve la lista de reuniones
    public List<Reunion> getListaReuniones() {
        return this.listaReuniones;
    }

    // Devuelve la lista de contactos
    public List<String> getListaContactos() {
        return this.listaContactos;
    }

    // Devuelve el usuario asociado al calendario
    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setListaReuniones(List<Reunion> leerReuniones) {
    }

    public void setListaContactos(List<String> leerContactos) {
    }
}
