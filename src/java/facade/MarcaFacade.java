/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.Marca;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.Marca_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.Linea;
import java.util.Collection;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class MarcaFacade extends AbstractFacade<Marca> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MarcaFacade() {
        super(Marca.class);
    }

    public boolean isLineaCollectionEmpty(Marca entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Marca> marca = cq.from(Marca.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(marca, entity), cb.isNotEmpty(marca.get(Marca_.lineaCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Linea> findLineaCollection(Marca entity) {
        Marca mergedEntity = this.getMergedEntity(entity);
        Collection<Linea> lineaCollection = mergedEntity.getLineaCollection();
        lineaCollection.size();
        return lineaCollection;
    }
    
}
