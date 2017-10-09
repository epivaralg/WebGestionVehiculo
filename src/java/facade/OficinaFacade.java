/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.Oficina;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.Oficina_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.Departamento;
import ejb.Unidad;
import java.util.Collection;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class OficinaFacade extends AbstractFacade<Oficina> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OficinaFacade() {
        super(Oficina.class);
    }

    public boolean isIdDepartamentoEmpty(Oficina entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Oficina> oficina = cq.from(Oficina.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(oficina, entity), cb.isNotNull(oficina.get(Oficina_.idDepartamento)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Departamento findIdDepartamento(Oficina entity) {
        return this.getMergedEntity(entity).getIdDepartamento();
    }

    public boolean isUnidadCollectionEmpty(Oficina entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Oficina> oficina = cq.from(Oficina.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(oficina, entity), cb.isNotEmpty(oficina.get(Oficina_.unidadCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Unidad> findUnidadCollection(Oficina entity) {
        Oficina mergedEntity = this.getMergedEntity(entity);
        Collection<Unidad> unidadCollection = mergedEntity.getUnidadCollection();
        unidadCollection.size();
        return unidadCollection;
    }
    
}
