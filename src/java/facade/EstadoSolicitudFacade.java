/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.EstadoSolicitud;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.EstadoSolicitud_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.RazonEstado;
import java.util.Collection;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class EstadoSolicitudFacade extends AbstractFacade<EstadoSolicitud> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoSolicitudFacade() {
        super(EstadoSolicitud.class);
    }

    public boolean isRazonEstadoCollectionEmpty(EstadoSolicitud entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<EstadoSolicitud> estadoSolicitud = cq.from(EstadoSolicitud.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(estadoSolicitud, entity), cb.isNotEmpty(estadoSolicitud.get(EstadoSolicitud_.razonEstadoCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<RazonEstado> findRazonEstadoCollection(EstadoSolicitud entity) {
        EstadoSolicitud mergedEntity = this.getMergedEntity(entity);
        Collection<RazonEstado> razonEstadoCollection = mergedEntity.getRazonEstadoCollection();
        razonEstadoCollection.size();
        return razonEstadoCollection;
    }
    
}
