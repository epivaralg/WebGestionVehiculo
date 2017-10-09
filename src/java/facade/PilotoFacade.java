/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.Piloto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.Piloto_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.Comision;
import ejb.EstadoPiloto;
import ejb.Grupo;
import ejb.Licencia;
import java.util.Collection;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class PilotoFacade extends AbstractFacade<Piloto> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PilotoFacade() {
        super(Piloto.class);
    }

    public boolean isComisionCollectionEmpty(Piloto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Piloto> piloto = cq.from(Piloto.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(piloto, entity), cb.isNotEmpty(piloto.get(Piloto_.comisionCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Comision> findComisionCollection(Piloto entity) {
        Piloto mergedEntity = this.getMergedEntity(entity);
        Collection<Comision> comisionCollection = mergedEntity.getComisionCollection();
        comisionCollection.size();
        return comisionCollection;
    }

    public boolean isIdEstadoPilotoEmpty(Piloto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Piloto> piloto = cq.from(Piloto.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(piloto, entity), cb.isNotNull(piloto.get(Piloto_.idEstadoPiloto)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public EstadoPiloto findIdEstadoPiloto(Piloto entity) {
        return this.getMergedEntity(entity).getIdEstadoPiloto();
    }

    public boolean isIdGrupoEmpty(Piloto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Piloto> piloto = cq.from(Piloto.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(piloto, entity), cb.isNotNull(piloto.get(Piloto_.idGrupo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Grupo findIdGrupo(Piloto entity) {
        return this.getMergedEntity(entity).getIdGrupo();
    }

    public boolean isLicenciaCollectionEmpty(Piloto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Piloto> piloto = cq.from(Piloto.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(piloto, entity), cb.isNotEmpty(piloto.get(Piloto_.licenciaCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Licencia> findLicenciaCollection(Piloto entity) {
        Piloto mergedEntity = this.getMergedEntity(entity);
        Collection<Licencia> licenciaCollection = mergedEntity.getLicenciaCollection();
        licenciaCollection.size();
        return licenciaCollection;
    }
    
}
