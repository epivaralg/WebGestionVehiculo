/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.TipoSolicitud;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.TipoSolicitud_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.Solicitud;
import java.util.Collection;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class TipoSolicitudFacade extends AbstractFacade<TipoSolicitud> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoSolicitudFacade() {
        super(TipoSolicitud.class);
    }

    public boolean isSolicitudCollectionEmpty(TipoSolicitud entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoSolicitud> tipoSolicitud = cq.from(TipoSolicitud.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoSolicitud, entity), cb.isNotEmpty(tipoSolicitud.get(TipoSolicitud_.solicitudCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Solicitud> findSolicitudCollection(TipoSolicitud entity) {
        TipoSolicitud mergedEntity = this.getMergedEntity(entity);
        Collection<Solicitud> solicitudCollection = mergedEntity.getSolicitudCollection();
        solicitudCollection.size();
        return solicitudCollection;
    }
    
}
