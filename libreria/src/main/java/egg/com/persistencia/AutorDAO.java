package egg.com.persistencia;
import egg.com.entidades.Autor;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AutorDAO{
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    private final EntityManager em = emf.createEntityManager();

    public void guardarAutor(Autor autor) throws Exception{
        em.getTransaction().begin();
        em.persist(autor);
        em.getTransaction().commit();
    }

    public Autor buscarAutor(int id){
        return em.find(Autor.class, id);
    }

    public void eliminarAutor(int id) {
        Autor autor = em.find(Autor.class, id);
        if (autor != null) {
            em.getTransaction().begin();
            em.remove(autor);
            em.getTransaction().commit();
        }
    }

    public List<Autor> listarTodos() throws Exception {
        return em.createQuery("SELECT a FROM Autor a", Autor.class)
                .getResultList();
    }

    public List<Autor> listarAutoresPorNombre(String nombreABuscar) throws Exception {
        return em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre", Autor.class)
                .setParameter("nombre", "%" + nombreABuscar + "%")
                .getResultList();
    }
}
