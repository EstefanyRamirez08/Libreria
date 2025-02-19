package egg.com;

import egg.com.entidades.Autor;
import egg.com.entidades.Editorial;
import egg.com.servicio.AutorServicio;
import egg.com.servicio.EditorialServicio;
import egg.com.servicio.LibroServicio;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibroServicio libroServicio = new LibroServicio();
        AutorServicio autorServicio = new AutorServicio();
        EditorialServicio editorialServicio = new EditorialServicio();

        int opcion = 0;
        do {
            System.out.println("\n--- MENÚ DE LIBRERÍA ---");
            System.out.println("1. Crear Autor");
            System.out.println("2. Crear Editorial");
            System.out.println("3. Crear Libro");
            System.out.println("4. Buscar Libro por ISBN");
            System.out.println("5. Buscar Libro por Título");
            System.out.println("6. Buscar Libros por Autor");
            System.out.println("7. Buscar Libros por Editorial");
            System.out.println("8. Dar de Alta/Baja un Libro");
            System.out.println("9. Editar un Libro");
            System.out.println("10. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            try {
                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el nombre del autor: ");
                        String nombreAutor = scanner.nextLine();
                        autorServicio.crearAutor(nombreAutor);
                        System.out.println("Autor creado con éxito.");
                        break;
                    case 2:
                        System.out.print("Ingrese el nombre de la editorial: ");
                        String nombreEditorial = scanner.nextLine();
                        editorialServicio.crearEditorial(nombreEditorial);
                        System.out.println("Editorial creada con éxito.");
                        break;
                    case 3:
                        System.out.print("Ingrese ISBN: ");
                        int isbn = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Ingrese título: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Ingrese año: ");
                        int anio = scanner.nextInt();
                        System.out.print("Ingrese cantidad de ejemplares: ");
                        int ejemplares = scanner.nextInt();
                        System.out.print("¿Está de alta? (true/false): ");
                        boolean alta = scanner.nextBoolean();
                        scanner.nextLine();

                        System.out.print("Ingrese ID del autor: ");
                        int idAutor = scanner.nextInt();
                        scanner.nextLine();
                        Autor autor = autorServicio.buscarAutor(idAutor);

                        System.out.print("Ingrese ID de la editorial: ");
                        int idEditorial = scanner.nextInt();
                        scanner.nextLine();
                        Editorial editorial = editorialServicio.buscarPorId(idEditorial);

                        libroServicio.crearLibro(isbn, titulo, anio, ejemplares, alta, autor, editorial);
                        break;
                    case 4:
                        System.out.print("Ingrese ISBN: ");
                        int buscarISBN = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println(libroServicio.buscarLibroPorISBN(buscarISBN));
                        break;
                    case 5:
                        System.out.print("Ingrese Título: ");
                        String buscarTitulo = scanner.nextLine();
                        System.out.println(libroServicio.buscarLibroPorTitulo(buscarTitulo));
                        break;
                    case 6:
                        System.out.print("Ingrese nombre del Autor: ");
                        String buscarAutor = scanner.nextLine();
                        System.out.println(libroServicio.buscarLibrosPorAutor(buscarAutor));
                        break;
                    case 7:
                        System.out.print("Ingrese nombre de la Editorial: ");
                        String buscarEditorial = scanner.nextLine();
                        System.out.println(libroServicio.buscarLibrosPorEditorial(buscarEditorial));
                        break;
                    case 8:
                        System.out.print("Ingrese ISBN del libro a cambiar estado: ");
                        int isbnAltaBaja = scanner.nextInt();
                        System.out.print("Ingrese estado (true: alta, false: baja): ");
                        boolean estado = scanner.nextBoolean();
                        scanner.nextLine();
                        libroServicio.darAltaBajaLibro(isbnAltaBaja, estado);
                        System.out.println("Estado del libro actualizado.");
                        break;
                    case 9:
                        System.out.print("Ingrese ISBN del libro a editar: ");
                        int isbnEditar = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nuevo título (deje en blanco para no modificar): ");
                        String nuevoTitulo = scanner.nextLine();
                        System.out.print("Nuevo año: ");
                        int nuevoAnio = scanner.nextInt();
                        System.out.print("Nueva cantidad de ejemplares: ");
                        int nuevosEjemplares = scanner.nextInt();
                        scanner.nextLine();
                        libroServicio.editarLibro(isbnEditar, nuevoTitulo, nuevoAnio, nuevosEjemplares);
                        System.out.println("Libro editado con éxito.");
                        break;
                    case 10:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción inválida, intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // Limpiar buffer en caso de error
            }
        } while (opcion != 10);

        scanner.close();
    }
}
