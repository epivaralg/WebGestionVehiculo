/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.EstadoPiloto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.EstadoPiloto_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.Piloto;
import java.util.Collection;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class EstadoPilotoFacade extends AbstractFacade<EstadoPiloto> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoPilotoFacade() {
        super(EstadoPiloto.class);
    }

    public boolean isPilotoCollectionEmpty(EstadoPiloto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<EstadoPiloto> estadoPiloto = cq.from(EstadoPiloto.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(estadoPiloto, entity), cb.isNotEmpty(estadoPiloto.get(EstadoPiloto_.pilotoCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Piloto> findPilotoCollection(EstadoPiloto entity) {
        EstadoPiloto mergedEntity = this.getMergedEntity(entity);
        Collection<Piloto> pilotoCollection = mergedEntity.getPilotoCollection();
        pilotoCollection.size();
        return pilotoCollection;
    }
    
}
