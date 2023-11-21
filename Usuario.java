import java.io.*;
import java.util.*;

/**
 * Clase que representa un usuario del sistema de gestión de reuniones.
 */

class Usuario implements PlanUsuario {
    // Nombre de usuario
    private String nombreUsuario;
    // Contraseña
    private String contraseña;
    // Plan del usuario
    private String tipoPlan;
    // Lista de reuniones del usuario
    private List<Reunion> listaReuniones;
    // Lista de contactos del usuario
    private List<String> listaContactos;
    private static HashMap<String, Usuario> usuarios = new HashMap<>();

    /**
     * Constructor de la clase Usuario.
     *
     * @param nombreUsuario Nombre de usuario del nuevo usuario.
     * @param contraseña    Contraseña del nuevo usuario.
     * @param tipoPlan      Tipo de plan del nuevo usuario.
     */
    public Usuario(String nombreUsuario, String contraseña, String tipoPlan) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.tipoPlan = tipoPlan;
        this.listaReuniones = new ArrayList<>();
        this.listaContactos = new ArrayList<>();
    }

    /**
     * Registra un nuevo usuario.
     *
     * @return Nuevo usuario registrado.
     */
    public static Usuario registrar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese su nombre de usuario:");
        String nombreUsuario = sc.nextLine();
        System.out.println("Ingrese su contraseña:");
        String contraseña = sc.nextLine();
        System.out.println("Seleccione su plan (gratis o premium):");
        String tipoPlan = sc.nextLine();
        Usuario usuario = new Usuario(nombreUsuario, contraseña, tipoPlan);
        CsvManager.escribirUsuarios(Arrays.asList(usuario));
        return usuario;
    }

    /**
     * Autentica al usuario.
     *
     * @param nombreUsuario Nombre de usuario para la autenticación.
     * @param contraseña    Contraseña asociada al nombre de usuario.
     * @return Usuario autenticado o null si la autenticación falla.
     */
    public static Usuario autenticar(String nombreUsuario, String contraseña) {
        // Implementación para autenticar a un usuario existente
        if (usuarios.containsKey(nombreUsuario)) { // Verifica que el nombre de usuario exista en el mapa de usuarios.
            Usuario usuario = usuarios.get(nombreUsuario); // Obtiene el usuario correspondiente al nombre de usuario.
            if (usuario.contraseña.equals(contraseña)) { // Compara la contraseña ingresada con la del usuario.
                return usuario; // Retorna el usuario si la contraseña coincide.
            } else {
                return null; // Retorna null si la contraseña no coincide.
            }
        } else {
            return null; // Retorna null si el nombre de usuario no existe en el mapa de usuarios.
        }
    }

    public static void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.nombreUsuario, usuario);
    }

    // Cambia el plan del usuario
    public void cambiarPlan() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Seleccione su nuevo plan (gratis o premium):");
        String nuevoPlan = sc.nextLine();
        this.tipoPlan = nuevoPlan;
        CsvManager.actualizarUsuarios();
    }

    /**
     * Cambia la contraseña del usuario.
     *
     * @param nuevaContraseña Nueva contraseña para el usuario.
     */
    public void cambiarContraseña(String nuevaContraseña) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese su nueva contraseña:");
        String nuevacontraseña = sc.nextLine();
        this.contraseña = nuevacontraseña;
        CsvManager.actualizarUsuarios();
    }

    /**
     * Devuelve la duración máxima permitida para las reuniones según el tipo de
     * plan.
     *
     * @return Duración máxima permitida para las reuniones.
     */
    public int obtenerDuracionMaxima() {
        if (this.tipoPlan.equals("premium")) {
            return 60;
        } else {
            return 15;
        }
    }

    /**
     * Devuelve la cantidad máxima de invitados permitida según el tipo de plan.
     *
     * @return Cantidad máxima de invitados permitida.
     */
    public int obtenerMaxInvitados() {
        if (this.tipoPlan.equals("premium")) {
            return 20;
        } else {
            return 5;
        }
    }

    /**
     * Devuelve el límite de reuniones permitidas por día según el tipo de plan.
     *
     * @return Límite de reuniones permitidas por día.
     */
    public int obtenerMaxReunionesDiarias() {
        if (this.tipoPlan.equals("premium")) {
            return 5;
        } else {
            return 2;
        }
    }

    /**
     * Devuelve las últimas "cantidad" reuniones según el tipo de plan.
     *
     * @param cantidad Cantidad de reuniones a obtener.
     * @return Lista de las últimas reuniones.
     */
    public List<Reunion> obtenerUltimasReuniones(int cantidad) {
        int limite = 0;
        if (this.tipoPlan.equals("premium")) {
            limite = 10;
        } else {
            limite = 3;
        }
        cantidad = Math.min(cantidad, limite);
        cantidad = Math.min(cantidad, this.listaReuniones.size());
        List<Reunion> ultimasReuniones = new ArrayList<>();
        for (int i = this.listaReuniones.size() - 1; i >= this.listaReuniones.size() - cantidad; i--) {
            ultimasReuniones.add(this.listaReuniones.get(i));
        }
        return ultimasReuniones;
    }

    /**
     * Imprime los últimos "cantidad" contactos según el tipo de plan.
     *
     * @param cantidad Cantidad de contactos a imprimir.
     */
    public void imprimirListadoContactos(int cantidad) {
        int limite = 0;
        if (this.tipoPlan.equals("premium")) {
            limite = 60;
        } else {
            limite = 15;
        }
        cantidad = Math.min(cantidad, limite);
        cantidad = Math.min(cantidad, this.listaContactos.size());
        System.out.println("Sus últimos " + cantidad + " contactos son:");
        for (int i = this.listaContactos.size() - 1; i >= this.listaContactos.size() - cantidad; i--) {
            System.out.println(this.listaContactos.get(i));
        }
    }

    /**
     * Añade una reunión a la lista de reuniones del usuario.
     *
     * @param reunion Reunión a añadir.
     */
    public void añadirReunion(Reunion reunion) {
        this.listaReuniones.add(reunion);
    }

    // Añade un contacto a la lista de contactos del usuario
    public void añadirContacto(String contacto) {
        this.listaContactos.add(contacto);
    }

    // Devuelve el nombre de usuario
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    // Devuelve el tipo de plan
    public String getTipoPlan() {
        return this.tipoPlan;
    }

    // Devuelve la lista de reuniones
    public List<Reunion> getListaReuniones() {
        return this.listaReuniones;
    }

    // Devuelve la lista de contactos
    public List<String> getListaContactos() {
        return this.listaContactos;
    }

    public void crearReunion(String fecha, String hora, String nombre, String pin, String notas,
            int duracion, List<String> invitados, String estado) {
        Reunion reunion = new Reunion(this);
        reunion.setFecha(fecha);
        reunion.setHora(hora);
        reunion.setNombre(nombre);
        reunion.setPin(pin);
        reunion.setNotas(notas);
        reunion.setDuracion(duracion);
        reunion.setEstado(estado);
        reunion.setListaInvitados(invitados);

        this.listaReuniones.add(reunion);
        for (String invitado : invitados) {
            this.listaContactos.add(invitado);
        }
    }
}
