/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.TipoUsoVehiculo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.TipoUsoVehiculo_;
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
public class TipoUsoVehiculoFacade extends AbstractFacade<TipoUsoVehiculo> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoUsoVehiculoFacade() {
        super(TipoUsoVehiculo.class);
    }

    public boolean isSolicitudCollectionEmpty(TipoUsoVehiculo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoUsoVehiculo> tipoUsoVehiculo = cq.from(TipoUsoVehiculo.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoUsoVehiculo, entity), cb.isNotEmpty(tipoUsoVehiculo.get(TipoUsoVehiculo_.solicitudCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Solicitud> findSolicitudCollection(TipoUsoVehiculo entity) {
        TipoUsoVehiculo mergedEntity = this.getMergedEntity(entity);
        Collection<Solicitud> solicitudCollection = mergedEntity.getSolicitudCollection();
        solicitudCollection.size();
        return solicitudCollection;
    }
    
}
