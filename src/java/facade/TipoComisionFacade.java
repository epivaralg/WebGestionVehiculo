/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.TipoComision;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.TipoComision_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.Comision;
import java.util.Collection;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class TipoComisionFacade extends AbstractFacade<TipoComision> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoComisionFacade() {
        super(TipoComision.class);
    }

    public boolean isComisionCollectionEmpty(TipoComision entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoComision> tipoComision = cq.from(TipoComision.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoComision, entity), cb.isNotEmpty(tipoComision.get(TipoComision_.comisionCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Comision> findComisionCollection(TipoComision entity) {
        TipoComision mergedEntity = this.getMergedEntity(entity);
        Collection<Comision> comisionCollection = mergedEntity.getComisionCollection();
        comisionCollection.size();
        return comisionCollection;
    }
    
}
