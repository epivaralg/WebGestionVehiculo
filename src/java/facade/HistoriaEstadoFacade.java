/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.HistoriaEstado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.HistoriaEstado_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.RazonEstado;
import ejb.Solicitud;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class HistoriaEstadoFacade extends AbstractFacade<HistoriaEstado> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoriaEstadoFacade() {
        super(HistoriaEstado.class);
    }

    public boolean isIdRazonEstadoEmpty(HistoriaEstado entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<HistoriaEstado> historiaEstado = cq.from(HistoriaEstado.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(historiaEstado, entity), cb.isNotNull(historiaEstado.get(HistoriaEstado_.idRazonEstado)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public RazonEstado findIdRazonEstado(HistoriaEstado entity) {
        return this.getMergedEntity(entity).getIdRazonEstado();
    }

    public boolean isIdSolicitudEmpty(HistoriaEstado entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<HistoriaEstado> historiaEstado = cq.from(HistoriaEstado.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(historiaEstado, entity), cb.isNotNull(historiaEstado.get(HistoriaEstado_.idSolicitud)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Solicitud findIdSolicitud(HistoriaEstado entity) {
        return this.getMergedEntity(entity).getIdSolicitud();
    }
    
}
