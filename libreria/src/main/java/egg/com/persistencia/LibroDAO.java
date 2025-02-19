package egg.com.persistencia;

import java.util.List;

import egg.com.entidades.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class LibroDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");

    private final EntityManager em = emf.createEntityManager();

    public void guardarLibro(Libro libro) {
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
    }

    public List<Libro> listarLibroPorISBN(int isbn) {
        return em.createQuery("SELECT l from Libro l WHERE l.isbn = :isbn", Libro.class)
                .setParameter("isbn", isbn)
                .getResultList();
    }

    public List<Libro> listarLibroPorTitulo(String titulo) {
        return em.createQuery("SELECT l from Libro l WHERE l.titulo LIKE :titulo", Libro.class)
                .setParameter("titulo", "%" + titulo + "%")
                .getResultList();
    }

    public List<Libro> listarLibrosPorAutor(String nombreAutor) {
        return em.createQuery("SELECT l FROM Libro l WHERE l.autor.nombre = :nombreAutor", Libro.class)
                .setParameter("nombreAutor", nombreAutor)
                .getResultList();
    }
    
    

    public List<Libro> listarLibrosPorEditorial(String nombreEditorial) {
        return em.createQuery("SELECT l from Libro l WHERE l.editorial.nombre = :nombreEditorial", Libro.class)
                .setParameter("nombreEditorial", nombreEditorial)
                .getResultList();
    }

}
