
/**
 * Universidad del Valle de Guatemala - POO
 * Marielos Ortíz, Sandra Pineda, Luisa Jiménez
 * Laboratorio final
 */

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que representa el sistema de gestión de reuniones.
 */
public class Sistema {
    private List<Usuario> usuarios;
    private Calendario calendario;
    private Usuario usuarioActual;

    /**
     * Constructor de la clase Sistema. Carga la lista de usuarios al iniciar el
     * sistema.
     */
    public Sistema() {
        this.usuarios = cargarUsuarios();
        this.calendario = calendario;
        this.usuarioActual = usuarioActual;
    }

    /**
     * Cambia el tipo de plan del usuario actual.
     */
    public void cambiarTipoPlan() {
        // Implementación para cambiar el tipo de plan del usuario actual
        this.usuarioActual.cambiarPlan();
        System.out.println("Su plan ha sido cambiado exitosamente.");
    }

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param nombreUsuario Nombre de usuario del nuevo usuario.
     * @param contraseña    Contraseña del nuevo usuario.
     * @param tipoPlan      Tipo de plan del nuevo usuario.
     */
    public void registrarUsuario(String nombreUsuario, String contraseña, String tipoPlan) {
        Usuario nuevoUsuario = new Usuario(nombreUsuario, contraseña, tipoPlan);
        Usuario.agregarUsuario(nuevoUsuario); // Llama al método agregarUsuario para añadir el nuevo usuario al mapa de
                                              // usuarios.
        System.out.println("Usuario registrado exitosamente.");
        guardarUsuarios(); // Guarda la lista de usuarios después de registrar uno nuevo.
    }

    /**
     * Inicia sesión en el sistema con un nombre de usuario y contraseña.
     *
     * @param nombreUsuario Nombre de usuario para iniciar sesión.
     * @param contraseña    Contraseña asociada al nombre de usuario.
     */
    public void iniciarSesion(String nombreUsuario, String contraseña) {
        Usuario usuario = Usuario.autenticar(nombreUsuario, contraseña);
        if (usuario != null) {
            this.usuarioActual = usuario;
            this.calendario = new Calendario(usuario);
            this.calendario.setListaReuniones(CsvManager.leerReuniones());
            this.calendario.setListaContactos(CsvManager.leerContactos());
            System.out.println("Ha iniciado sesión exitosamente.");
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos.");
        }
    }

    /**
     * Crea una nueva reunión en el sistema.
     *
     * @param fecha     Fecha de la reunión.
     * @param hora      Hora de la reunión.
     * @param nombre    Nombre de la reunión.
     * @param pin       Pin de la reunión.
     * @param notas     Notas de la reunión.
     * @param duracion  Duración de la reunión en minutos.
     * @param invitados Lista de invitados a la reunión.
     * @param estado    Estado a mostrar durante la reunión.
     */
    public void crearReunion(String fecha, String hora, String nombre, String pin, String notas,
            int duracion, List<String> invitados, String estado) {
        // Implementación para crear una reunión
        if (this.usuarioActual != null) {
            // Crear una reunión usando los métodos de Usuario
            this.usuarioActual.crearReunion(fecha, hora, nombre, pin, notas, duracion, invitados, estado);
            System.out.println("Su reunión ha sido creada exitosamente.");
        } else {
            System.out.println("Debe iniciar sesión antes de crear una reunión.");
        }
    }

    public void listarReunionesUsuarioActual() {
        // Implementación para listar las reuniones programadas para el usuario actual
        System.out.println("Sus reuniones programadas son:");
        for (Reunion reunion : this.calendario.getListaReuniones()) {
            if (reunion.getUsuario().equals(this.usuarioActual)) {
                reunion.imprimirReunion();
            }
        }
    }

    public void listarUltimasReunionesUsuarioActual() {
        // Implementación para listar las últimas reuniones del usuario actual según su
        // plan
        System.out.println("Sus últimas reuniones son:");
        for (Reunion reunion : this.usuarioActual.obtenerUltimasReuniones(this.calendario.getListaReuniones().size())) {
            reunion.imprimirReunion();
        }
    }

    public void listarContactosUsuarioActual() {
        // Implementación para listar los contactos del usuario actual según su plan
        this.usuarioActual.imprimirListadoContactos(this.calendario.getListaContactos().size());
    }

    public void cambiarContraseñaUsuario(String nuevaContraseña) {
        // Implementación para cambiar la contraseña del usuario actual
        this.usuarioActual.cambiarContraseña(nuevaContraseña);
        System.out.println("Su contraseña ha sido cambiada exitosamente.");
    }

    public void imprimirListadoContactos() {
    }

    private List<Usuario> cargarUsuarios() {
        // Intenta leer los usuarios desde el archivo CSV
        List<Usuario> usuarios = CsvManager.leerUsuarios();
        // Si la lectura falla o el archivo está vacío, crea una nueva lista.
        return usuarios != null ? usuarios : new ArrayList<>();
    }

    private void guardarUsuarios() {
        // Guarda la lista de usuarios en el archivo CSV
        CsvManager.escribirUsuarios(usuarios);
    }

    /**
     * Obtiene el usuario actual.
     *
     * @return Usuario actual.
     */
    public Usuario getUsuarioActual() {
        return this.usuarioActual;
    }

}
