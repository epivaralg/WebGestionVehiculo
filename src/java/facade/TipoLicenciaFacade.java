/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.TipoLicencia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.TipoLicencia_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.Licencia;
import java.util.Collection;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class TipoLicenciaFacade extends AbstractFacade<TipoLicencia> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoLicenciaFacade() {
        super(TipoLicencia.class);
    }

    public boolean isLicenciaCollectionEmpty(TipoLicencia entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoLicencia> tipoLicencia = cq.from(TipoLicencia.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoLicencia, entity), cb.isNotEmpty(tipoLicencia.get(TipoLicencia_.licenciaCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Licencia> findLicenciaCollection(TipoLicencia entity) {
        TipoLicencia mergedEntity = this.getMergedEntity(entity);
        Collection<Licencia> licenciaCollection = mergedEntity.getLicenciaCollection();
        licenciaCollection.size();
        return licenciaCollection;
    }
    
}
