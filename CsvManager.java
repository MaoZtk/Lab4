import java.io.*;
import java.util.*;

// Clase para manejar la lectura y escritura de archivos CSV
class CsvManager {
    // Nombre del archivo que contiene los datos de los usuarios
    private static final String ARCHIVO_USUARIOS = "usuarios.csv";
    // Nombre del archivo que contiene los datos de las reuniones
    private static final String ARCHIVO_REUNIONES = "reuniones.csv";

    // Escribe información de usuarios en un archivo CSV
    public static void escribirUsuarios(List<Usuario> usuarios) {
        try {
            FileWriter fw = new FileWriter(ARCHIVO_USUARIOS, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (Usuario usuario : usuarios) {
                pw.println(usuario.getNombreUsuario() + "," + usuario.getTipoPlan());
            }
            pw.close();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Escribe información de reuniones en un archivo CSV
    public static void escribirReuniones(List<Reunion> reuniones) {
        try {
            FileWriter fw = new FileWriter(ARCHIVO_REUNIONES, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (Reunion reunion : reuniones) {
                if (reunion != null) { 
                    pw.println(reunion.getNombre() + "," + reunion.getPin() + "," + reunion.getNotas() + "," + reunion.getDuracion() + "," + reunion.getEstado() + "," + reunion.getFecha() + "," + reunion.getHora() + "," + reunion.getListaInvitados() + "," + (reunion.getUsuario() != null ? reunion.getUsuario().getNombreUsuario() : ""));
                }
            }
            pw.close();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    // Lee información de usuarios de un archivo CSV
    public static List<Usuario> leerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (FileReader fr = new FileReader(ARCHIVO_USUARIOS);
             BufferedReader br = new BufferedReader(fr)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombreUsuario = datos[0];
                String tipoPlan = datos[1];
                Usuario usuario = new Usuario(nombreUsuario, null, tipoPlan);
                usuarios.add(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    // Lee información de reuniones de un archivo CSV
    public static List<Reunion> leerReuniones() {
        List<Reunion> reuniones = new ArrayList<>();
        try {
            FileReader fr = new FileReader(ARCHIVO_REUNIONES);
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            while (linea != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0];
                String pin = datos[1];
                String notas = datos[2];
                int duracion = Integer.parseInt(datos[3]);
                String estado = datos[4];
                String fecha = datos[5];
                int hora = Integer.parseInt(datos[6]);
                List<String> listaInvitados = Arrays.asList(datos[7].split(","));
                String nombreUsuario = datos[8];
                Usuario usuario = buscarUsuario(nombreUsuario);
                Reunion reunion = new Reunion(nombre, pin, notas, duracion, estado, fecha, hora, listaInvitados, usuario);
                reuniones.add(reunion);
                linea = br.readLine();
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reuniones;
    }

    // Busca un usuario por su nombre de usuario
    public static Usuario buscarUsuario(String nombreUsuario) {
        List<Usuario> usuarios = leerUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    // Actualiza la información de los usuarios en el archivo CSV
    public static void actualizarUsuarios() {
        List<Usuario> usuarios = leerUsuarios();
        try (FileWriter fw = new FileWriter(ARCHIVO_USUARIOS);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            for (Usuario usuario : usuarios) {
                pw.println(usuario.getNombreUsuario() + "," + usuario.getTipoPlan());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Lee información de contactos de un archivo CSV
    public static List<String> leerContactos() {
        List<String> contactos = new ArrayList<>();
        try {
            FileReader fr = new FileReader(ARCHIVO_REUNIONES);
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            while (linea != null) {
                // Separa los datos de la reunión por comas
                String[] datos = linea.split(",");
                // Obtiene la lista de invitados de la reunión
                String invitados = datos[7];
                // Separa los nombres de los invitados por comas
                String[] nombres = invitados.split(",");
                // Añade cada nombre a la lista de contactos si no está repetido
                for (String nombre : nombres) {
                    if (!contactos.contains(nombre)) {
                        contactos.add(nombre);
                    }
                }
                linea = br.readLine();
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contactos;
    }
}
