package egg.com.servicio;
import java.util.List;
//import egg.com.entidades.Libro;
//import egg.com.servicio.*;
import egg.com.entidades.Autor;
import egg.com.persistencia.AutorDAO;
public class AutorServicio {

    private final AutorDAO autorDAO;

    public AutorServicio() {
        this.autorDAO = new AutorDAO();
    }

    public void crearAutor(String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del autor no puede estar vacío");
        }

        try {
            Autor autorNuevo = new Autor();
            autorNuevo.setNombre(nombre.trim());
            autorNuevo.setAlta(true);
            
            autorDAO.guardarAutor(autorNuevo);
        } catch (Exception e) {
            throw new Exception("Error al guardar el autor: " + e.getMessage(), e);
        }
    }

    public Autor buscarAutor(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo");
        }

        Autor autor = autorDAO.buscarAutor(id);
        if (autor == null) {
            throw new Exception("No se encontró un autor con el ID: " + id);
        }
        return autor;
    }

    public void listarAutores() throws Exception {
        List<Autor> autores = autorDAO.listarTodos();
        if (autores == null || autores.isEmpty()) {
            throw new Exception("No hay autores disponibles para listar");
        }
        imprimirLista(autores);
    }

    public void imprimirLista(List<Autor> listaRecibida) throws Exception {
        if (listaRecibida == null || listaRecibida.isEmpty()) {
            throw new Exception("La lista de autores está vacía");
        }
        
        for (Autor uniAutor : listaRecibida) {
            if (uniAutor != null && uniAutor.getNombre() != null) {
                System.out.println(uniAutor.getNombre());
            } else {
                System.out.println("Autor sin nombre válido");
            }
        }
    }
}
