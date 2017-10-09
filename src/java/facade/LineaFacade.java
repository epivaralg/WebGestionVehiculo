/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.Linea;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.Linea_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.Marca;
import ejb.Vehiculo;
import java.util.Collection;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class LineaFacade extends AbstractFacade<Linea> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LineaFacade() {
        super(Linea.class);
    }

    public boolean isIdMarcaEmpty(Linea entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Linea> linea = cq.from(Linea.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(linea, entity), cb.isNotNull(linea.get(Linea_.idMarca)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Marca findIdMarca(Linea entity) {
        return this.getMergedEntity(entity).getIdMarca();
    }

    public boolean isVehiculoCollectionEmpty(Linea entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Linea> linea = cq.from(Linea.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(linea, entity), cb.isNotEmpty(linea.get(Linea_.vehiculoCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Vehiculo> findVehiculoCollection(Linea entity) {
        Linea mergedEntity = this.getMergedEntity(entity);
        Collection<Vehiculo> vehiculoCollection = mergedEntity.getVehiculoCollection();
        vehiculoCollection.size();
        return vehiculoCollection;
    }
    
}
