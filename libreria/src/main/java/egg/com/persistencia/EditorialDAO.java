package egg.com.persistencia;

import egg.com.entidades.Editorial;
import egg.com.entidades.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class EditorialDAO {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public EditorialDAO() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("LibreriaPU");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void guardar(Editorial editorial) {
        entityManager.getTransaction().begin();
        entityManager.persist(editorial);
        entityManager.getTransaction().commit();
    }

    public Editorial buscarPorId(Integer id) {
        return entityManager.find(Editorial.class, id);
    }

    public List<Editorial> listarTodos() {
        return entityManager.createQuery("SELECT e FROM Editorial e", Editorial.class).getResultList();
    }

    public void actualizar(Editorial editorial) {
        entityManager.getTransaction().begin();
        entityManager.merge(editorial);
        entityManager.getTransaction().commit();
    }

    public void eliminar(Integer id) {
        Editorial editorial = buscarPorId(id);
        if (editorial != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(editorial);
            entityManager.getTransaction().commit();
        }
    }

    public void cerrar() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    public List<Libro> listarLibrosPorEditorial(String nombreEditorial) {
        return entityManager.createQuery("SELECT l FROM Libro l WHERE l.editorial.nombre = :nombreEditorial", Libro.class)
                .setParameter("nombreEditorial", nombreEditorial)
                .getResultList();
    }
}
