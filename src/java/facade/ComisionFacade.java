/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.Comision;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.Comision_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.Piloto;
import ejb.TipoComision;
import ejb.Vehiculo;
import ejb.Solicitud;
import java.util.Collection;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class ComisionFacade extends AbstractFacade<Comision> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComisionFacade() {
        super(Comision.class);
    }

    public boolean isIdPilotoEmpty(Comision entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Comision> comision = cq.from(Comision.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(comision, entity), cb.isNotNull(comision.get(Comision_.idPiloto)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Piloto findIdPiloto(Comision entity) {
        return this.getMergedEntity(entity).getIdPiloto();
    }

    public boolean isIdTipoComisionEmpty(Comision entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Comision> comision = cq.from(Comision.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(comision, entity), cb.isNotNull(comision.get(Comision_.idTipoComision)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoComision findIdTipoComision(Comision entity) {
        return this.getMergedEntity(entity).getIdTipoComision();
    }

    public boolean isPlacaEmpty(Comision entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Comision> comision = cq.from(Comision.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(comision, entity), cb.isNotNull(comision.get(Comision_.placa)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Vehiculo findPlaca(Comision entity) {
        return this.getMergedEntity(entity).getPlaca();
    }

    public boolean isSolicitudCollectionEmpty(Comision entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Comision> comision = cq.from(Comision.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(comision, entity), cb.isNotEmpty(comision.get(Comision_.solicitudCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Solicitud> findSolicitudCollection(Comision entity) {
        Comision mergedEntity = this.getMergedEntity(entity);
        Collection<Solicitud> solicitudCollection = mergedEntity.getSolicitudCollection();
        solicitudCollection.size();
        return solicitudCollection;
    }
    
}
