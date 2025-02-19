package egg.com.servicio;

import egg.com.entidades.Editorial;
import egg.com.persistencia.EditorialDAO;
import java.util.List;

public class EditorialServicio {

    private EditorialDAO editorialDAO = new EditorialDAO();

    public void crearEditorial(String nombre) {
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(true);
        editorialDAO.guardar(editorial);
    }

    public List<Editorial> listarEditoriales() {
        return editorialDAO.listarTodos();
    }

    public Editorial buscarPorId(Integer id) throws Exception {
        Editorial editorial = editorialDAO.buscarPorId(id);
        if (editorial == null) {
            throw new Exception("No se encontró la editorial con ID: " + id);
        }
        return editorial;
    }

    public void modificarEditorial(Integer id, String nuevoNombre) throws Exception {
        Editorial editorial = buscarPorId(id);
        editorial.setNombre(nuevoNombre);
        editorialDAO.actualizar(editorial);
    }

    public void eliminarEditorial(Integer id) throws Exception {
        editorialDAO.eliminar(id);
    }

    public void cerrar() {
        editorialDAO.cerrar();
    }
}
