package egg.com.servicio;

import egg.com.entidades.Autor;
import egg.com.entidades.Editorial;
import egg.com.entidades.Libro;
import egg.com.persistencia.LibroDAO;
import java.util.List;

public class LibroServicio {
    private final LibroDAO libroDAO = new LibroDAO();

    public void crearLibro(int isbn, String titulo, int anio, int ejemplares, boolean alta, Autor autor, Editorial editorial) throws Exception {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título del libro no puede estar vacío");
        }
        if (libroDAO.listarLibroPorISBN(isbn).size() > 0) {
            throw new Exception("Ya existe un libro con el ISBN: " + isbn);
        }
        
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setAlta(alta);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        libroDAO.guardarLibro(libro);
        System.out.println("Libro guardado con éxito.");
    }

    public void darAltaBajaLibro(int isbn, boolean estado) throws Exception {
        List<Libro> libros = libroDAO.listarLibroPorISBN(isbn);
        if (libros.isEmpty()) {
            throw new Exception("No se encontró un libro con el ISBN: " + isbn);
        }
        Libro libro = libros.get(0);
        libro.setAlta(estado);
        libroDAO.guardarLibro(libro);
    }

    public void editarLibro(int isbn, String nuevoTitulo, int nuevoAnio, int nuevosEjemplares) throws Exception {
        List<Libro> libros = libroDAO.listarLibroPorISBN(isbn);
        if (libros.isEmpty()) {
            throw new Exception("No se encontró un libro con el ISBN: " + isbn);
        }
        Libro libro = libros.get(0);
        if (nuevoTitulo != null && !nuevoTitulo.trim().isEmpty()) {
            libro.setTitulo(nuevoTitulo);
        }
        libro.setAnio(nuevoAnio);
        libro.setEjemplares(nuevosEjemplares);
        libroDAO.guardarLibro(libro);
    }

    public List<Libro> buscarLibroPorISBN(int isbn) {
        return libroDAO.listarLibroPorISBN(isbn);
    }
    
    public List<Libro> buscarLibroPorTitulo(String titulo) {
        return libroDAO.listarLibroPorTitulo(titulo);
    }
    
    public List<Libro> buscarLibrosPorAutor(String nombreAutor) {
        return libroDAO.listarLibrosPorAutor(nombreAutor);
    }
    
    public List<Libro> buscarLibrosPorEditorial(String nombreEditorial) {
        return libroDAO.listarLibrosPorEditorial(nombreEditorial);
    }
}
