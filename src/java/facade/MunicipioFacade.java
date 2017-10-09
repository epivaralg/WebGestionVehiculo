/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.Municipio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.Municipio_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.Ruta;
import ejb.Departamento;
import java.util.Collection;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class MunicipioFacade extends AbstractFacade<Municipio> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipioFacade() {
        super(Municipio.class);
    }

    public boolean isRutaCollectionEmpty(Municipio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Municipio> municipio = cq.from(Municipio.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(municipio, entity), cb.isNotEmpty(municipio.get(Municipio_.rutaCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Ruta> findRutaCollection(Municipio entity) {
        Municipio mergedEntity = this.getMergedEntity(entity);
        Collection<Ruta> rutaCollection = mergedEntity.getRutaCollection();
        rutaCollection.size();
        return rutaCollection;
    }

    public boolean isIdDepartamentoEmpty(Municipio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Municipio> municipio = cq.from(Municipio.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(municipio, entity), cb.isNotNull(municipio.get(Municipio_.idDepartamento)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Departamento findIdDepartamento(Municipio entity) {
        return this.getMergedEntity(entity).getIdDepartamento();
    }
    
}
