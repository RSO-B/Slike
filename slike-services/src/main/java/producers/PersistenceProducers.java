package producers;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.Produces;

public class PersistenceProducers {
    @PersistenceUnit(unitName = "slike-jpa")
    private EntityManagerFactory emf;

    @Produces
    @RequestScoped
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void disposeEntityManager(@Disposes EntityManager entityManager) {

        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
