/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.Vehiculo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.Vehiculo_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.Comision;
import ejb.EstadoVehiculo;
import ejb.Linea;
import ejb.TipoVehiculo;
import java.util.Collection;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class VehiculoFacade extends AbstractFacade<Vehiculo> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VehiculoFacade() {
        super(Vehiculo.class);
    }

    public boolean isComisionCollectionEmpty(Vehiculo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Vehiculo> vehiculo = cq.from(Vehiculo.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(vehiculo, entity), cb.isNotEmpty(vehiculo.get(Vehiculo_.comisionCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Comision> findComisionCollection(Vehiculo entity) {
        Vehiculo mergedEntity = this.getMergedEntity(entity);
        Collection<Comision> comisionCollection = mergedEntity.getComisionCollection();
        comisionCollection.size();
        return comisionCollection;
    }

    public boolean isIdEstadoVehiculoEmpty(Vehiculo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Vehiculo> vehiculo = cq.from(Vehiculo.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(vehiculo, entity), cb.isNotNull(vehiculo.get(Vehiculo_.idEstadoVehiculo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public EstadoVehiculo findIdEstadoVehiculo(Vehiculo entity) {
        return this.getMergedEntity(entity).getIdEstadoVehiculo();
    }

    public boolean isIdLineaEmpty(Vehiculo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Vehiculo> vehiculo = cq.from(Vehiculo.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(vehiculo, entity), cb.isNotNull(vehiculo.get(Vehiculo_.idLinea)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Linea findIdLinea(Vehiculo entity) {
        return this.getMergedEntity(entity).getIdLinea();
    }

    public boolean isIdTipoVehiculoEmpty(Vehiculo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Vehiculo> vehiculo = cq.from(Vehiculo.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(vehiculo, entity), cb.isNotNull(vehiculo.get(Vehiculo_.idTipoVehiculo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoVehiculo findIdTipoVehiculo(Vehiculo entity) {
        return this.getMergedEntity(entity).getIdTipoVehiculo();
    }
    
}
