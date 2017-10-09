/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.Unidad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.Unidad_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.Oficina;
import ejb.Usuario;
import java.util.Collection;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class UnidadFacade extends AbstractFacade<Unidad> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UnidadFacade() {
        super(Unidad.class);
    }

    public boolean isIdOficinaEmpty(Unidad entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Unidad> unidad = cq.from(Unidad.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(unidad, entity), cb.isNotNull(unidad.get(Unidad_.idOficina)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Oficina findIdOficina(Unidad entity) {
        return this.getMergedEntity(entity).getIdOficina();
    }

    public boolean isUsuarioCollectionEmpty(Unidad entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Unidad> unidad = cq.from(Unidad.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(unidad, entity), cb.isNotEmpty(unidad.get(Unidad_.usuarioCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Usuario> findUsuarioCollection(Unidad entity) {
        Unidad mergedEntity = this.getMergedEntity(entity);
        Collection<Usuario> usuarioCollection = mergedEntity.getUsuarioCollection();
        usuarioCollection.size();
        return usuarioCollection;
    }
    
}
