/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.TipoBitacora;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.TipoBitacora_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.Bitacora;
import java.util.Collection;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class TipoBitacoraFacade extends AbstractFacade<TipoBitacora> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoBitacoraFacade() {
        super(TipoBitacora.class);
    }

    public boolean isBitacoraCollectionEmpty(TipoBitacora entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoBitacora> tipoBitacora = cq.from(TipoBitacora.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoBitacora, entity), cb.isNotEmpty(tipoBitacora.get(TipoBitacora_.bitacoraCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Bitacora> findBitacoraCollection(TipoBitacora entity) {
        TipoBitacora mergedEntity = this.getMergedEntity(entity);
        Collection<Bitacora> bitacoraCollection = mergedEntity.getBitacoraCollection();
        bitacoraCollection.size();
        return bitacoraCollection;
    }
    
}
