/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.RazonEstado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.RazonEstado_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.HistoriaEstado;
import ejb.EstadoSolicitud;
import java.util.Collection;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class RazonEstadoFacade extends AbstractFacade<RazonEstado> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RazonEstadoFacade() {
        super(RazonEstado.class);
    }

    public boolean isHistoriaEstadoCollectionEmpty(RazonEstado entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RazonEstado> razonEstado = cq.from(RazonEstado.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(razonEstado, entity), cb.isNotEmpty(razonEstado.get(RazonEstado_.historiaEstadoCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<HistoriaEstado> findHistoriaEstadoCollection(RazonEstado entity) {
        RazonEstado mergedEntity = this.getMergedEntity(entity);
        Collection<HistoriaEstado> historiaEstadoCollection = mergedEntity.getHistoriaEstadoCollection();
        historiaEstadoCollection.size();
        return historiaEstadoCollection;
    }

    public boolean isIdEstadoSolicitudEmpty(RazonEstado entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RazonEstado> razonEstado = cq.from(RazonEstado.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(razonEstado, entity), cb.isNotNull(razonEstado.get(RazonEstado_.idEstadoSolicitud)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public EstadoSolicitud findIdEstadoSolicitud(RazonEstado entity) {
        return this.getMergedEntity(entity).getIdEstadoSolicitud();
    }
    
}
