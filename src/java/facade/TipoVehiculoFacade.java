/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.TipoVehiculo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.TipoVehiculo_;
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
public class TipoVehiculoFacade extends AbstractFacade<TipoVehiculo> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoVehiculoFacade() {
        super(TipoVehiculo.class);
    }

    public boolean isVehiculoCollectionEmpty(TipoVehiculo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoVehiculo> tipoVehiculo = cq.from(TipoVehiculo.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoVehiculo, entity), cb.isNotEmpty(tipoVehiculo.get(TipoVehiculo_.vehiculoCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Vehiculo> findVehiculoCollection(TipoVehiculo entity) {
        TipoVehiculo mergedEntity = this.getMergedEntity(entity);
        Collection<Vehiculo> vehiculoCollection = mergedEntity.getVehiculoCollection();
        vehiculoCollection.size();
        return vehiculoCollection;
    }
    
}
