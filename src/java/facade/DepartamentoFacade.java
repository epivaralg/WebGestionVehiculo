/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.Departamento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.Departamento_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.Oficina;
import ejb.Municipio;
import java.util.Collection;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class DepartamentoFacade extends AbstractFacade<Departamento> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartamentoFacade() {
        super(Departamento.class);
    }

    public boolean isOficinaCollectionEmpty(Departamento entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Departamento> departamento = cq.from(Departamento.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(departamento, entity), cb.isNotEmpty(departamento.get(Departamento_.oficinaCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Oficina> findOficinaCollection(Departamento entity) {
        Departamento mergedEntity = this.getMergedEntity(entity);
        Collection<Oficina> oficinaCollection = mergedEntity.getOficinaCollection();
        oficinaCollection.size();
        return oficinaCollection;
    }

    public boolean isMunicipioCollectionEmpty(Departamento entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Departamento> departamento = cq.from(Departamento.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(departamento, entity), cb.isNotEmpty(departamento.get(Departamento_.municipioCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Municipio> findMunicipioCollection(Departamento entity) {
        Departamento mergedEntity = this.getMergedEntity(entity);
        Collection<Municipio> municipioCollection = mergedEntity.getMunicipioCollection();
        municipioCollection.size();
        return municipioCollection;
    }
    
}
