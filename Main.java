import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Sistema sistema;
    private static Reunion reunion;

    public static void main(String[] args) {
        sistema = new Sistema();
        boolean salir = false;
        while (!salir) {
            System.out.println("Bienvenido al sistema de reuniones.");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            Scanner sc = new Scanner(System.in);
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre de usuario:"); // Pide el nombre de usuario.
                    String nombreUsuario = sc.nextLine(); // Lee el nombre de usuario e inicializa la variable.
                    System.out.println("Ingrese la contraseña:"); // Pide la contraseña.
                    String contraseña = sc.nextLine(); // Lee la contraseña.
                    System.out.println("Ingrese el tipo de plan:"); // Pide el tipo de plan.
                    String tipoPlan = sc.nextLine(); // Lee el tipo de plan.
                    sistema.registrarUsuario(nombreUsuario, contraseña, tipoPlan);
                break;
            
                case 2:
                    System.out.println("Ingrese su nombre de usuario:"); // Pide el nombre de usuario.
                    String nombreUsuario1 = sc.nextLine();
                    System.out.println("Ingrese su contraseña:"); // Pide la contraseña.
                    String contraseña1 = sc.nextLine();
                    sistema.iniciarSesion(nombreUsuario1, contraseña1); // Llama al método iniciarSesion con los datos ingresados.
                    mostrarMenu();    
                break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
        System.out.println("Gracias por usar el sistema de reuniones.");
    }

    public static void mostrarMenu() {
        boolean salir = false;
        while (!salir) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Modo reuniones");
            System.out.println("2. Modo calendario");
            System.out.println("3. Modo perfil");
            System.out.println("4. Salir");
            Scanner sc = new Scanner(System.in);
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    mostrarMenuReuniones();
                    break;
                case 2:
                    mostrarMenuCalendario();
                    break;
                case 3:
                    mostrarMenuPerfil();
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    public static void mostrarMenuReuniones() {
        boolean salir = false;
        while (!salir) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Definir reunión");
            System.out.println("2. Volver a empezar");
            System.out.println("3. Salir");
            Scanner sc = new Scanner(System.in);
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                if (sistema.getUsuarioActual() != null) {
                    System.out.println("Defina la fecha de la reunión (dd/mm/aaaa):");
                    String fecha = sc.nextLine();
                    System.out.println("Defina la hora de la reunión:");
                    String hora = sc.nextLine();
                    System.out.println("Defina el nombre de la reunión:");
                    String nombre = sc.nextLine();
                    System.out.println("Defina el pin de la reunión:");
                    String pin = sc.nextLine();
                    System.out.println("Defina las notas de la reunión:");
                    String notas = sc.nextLine();
                    System.out.println("Defina la duración de la reunión (en minutos):");
                    int duracion = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Defina el estado a mostrar mientras está en esta reunión (disponible u ocupado):");
                    String estado = sc.nextLine();
                    System.out.println("Defina la lista de invitados (separados por comas):");
                    String invitadosInput = sc.nextLine();
                    List<String> listaInvitados = Arrays.asList(invitadosInput.split(","));
                    
                    // Llama al método crearReunion con los parámetros obtenidos
                    sistema.crearReunion(fecha, hora, nombre, pin, notas, duracion, listaInvitados, estado);
                    System.out.println("Reunión creada exitosamente.");
                } else {
                    System.out.println("Debe iniciar sesión antes de crear una reunión.");
                }
                break;
                case 2:
                    if (reunion != null) {
                        reunion.volverEmpezar(); // Llamar al método volverEmpezar de la instancia de Reunion
                    } else {
                        System.out.println("No hay ninguna reunión definida.");
                    }
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    // Muestra el menú de calendario con las opciones disponibles
    public static void mostrarMenuCalendario() {
        boolean salir = false;
        while (!salir) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Listar últimas reuniones");
            System.out.println("2. Imprimir listado de contactos");
            System.out.println("3. Salir");
            Scanner sc = new Scanner(System.in);
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    sistema.listarUltimasReunionesUsuarioActual();            
                    break;
                case 2:
                    sistema.imprimirListadoContactos();
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    // Muestra el menú de perfil con las opciones disponibles
    public static void mostrarMenuPerfil() {
        boolean salir = false;
        while (!salir) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Modificar el tipo de cliente");
            System.out.println("2. Cambiar contraseña");
            System.out.println("3. Salir");
            Scanner sc = new Scanner(System.in);
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    sistema.cambiarTipoPlan();
                    break;
                case 2:
                    sistema.cambiarContraseñaUsuario(null);
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    public static void volverEmpezarReunion() {
        reunion.volverEmpezar();
        System.out.println("El proceso de definición de reunión ha sido reiniciado.");
    }

}

