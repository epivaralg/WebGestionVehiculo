/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.EstadoVehiculo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.EstadoVehiculo_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.Vehiculo;
import java.util.Collection;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class EstadoVehiculoFacade extends AbstractFacade<EstadoVehiculo> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoVehiculoFacade() {
        super(EstadoVehiculo.class);
    }

    public boolean isVehiculoCollectionEmpty(EstadoVehiculo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<EstadoVehiculo> estadoVehiculo = cq.from(EstadoVehiculo.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(estadoVehiculo, entity), cb.isNotEmpty(estadoVehiculo.get(EstadoVehiculo_.vehiculoCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Vehiculo> findVehiculoCollection(EstadoVehiculo entity) {
        EstadoVehiculo mergedEntity = this.getMergedEntity(entity);
        Collection<Vehiculo> vehiculoCollection = mergedEntity.getVehiculoCollection();
        vehiculoCollection.size();
        return vehiculoCollection;
    }
    
}
