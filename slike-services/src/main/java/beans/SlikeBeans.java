package beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import entities.Slika;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class SlikeBeans {

    private final static Logger LOGGER = Logger.getLogger(SlikeBeans.class.getName());

    @PersistenceContext(unitName = "slike-jpa")
    private EntityManager em;

    @PostConstruct
    public void start() {
        LOGGER.log(Level.INFO, "Ustvarjanje UporabnikZrno zrno");
    }

    @PreDestroy
    public void reset() {
        LOGGER.log(Level.INFO, "Uničenje UporabnikZrno zrno");
    }

    public List<Slika> getSlikaList(QueryParameters query) {

        return JPAUtils.queryEntities(em, Slika.class, query);
    }
    public Long getSlikaCount(QueryParameters query) {

        return JPAUtils.queryEntitiesCount(em, Slika.class, query);
    }

    public List<Slika> getSlikaList() {

        TypedQuery<Slika> query = em.createNamedQuery("Slika.getAll", Slika.class);

        return query.getResultList();

    }

    public Slika getSlika(Integer id) {

        Slika slika = em.find(Slika.class, id);

        if (slika == null) {
            throw new NotFoundException();
        }

        return slika;
    }

    @Transactional
    public void createSlika(Slika s) {
        if (s != null) {
            em.persist(s);
        }
    }

    public Slika putSlika(String id, Slika slika) {

        Slika c = em.find(Slika.class, id);

        if (c == null) {
            return null;
        }

        try {
            beginTx();
            slika.setId(c.getId());
            slika = em.merge(slika);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return slika;
    }

    public boolean deleteSlika(String id) {

        Slika slika = em.find(Slika.class, id);

        if (slika != null) {
            try {
                beginTx();
                em.remove(slika);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
            }
        } else
            return false;

        return true;
    }


    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }
}
