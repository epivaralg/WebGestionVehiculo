/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.Licencia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.Licencia_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.Piloto;
import ejb.TipoLicencia;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class LicenciaFacade extends AbstractFacade<Licencia> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LicenciaFacade() {
        super(Licencia.class);
    }

    public boolean isIdPilotoEmpty(Licencia entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Licencia> licencia = cq.from(Licencia.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(licencia, entity), cb.isNotNull(licencia.get(Licencia_.idPiloto)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Piloto findIdPiloto(Licencia entity) {
        return this.getMergedEntity(entity).getIdPiloto();
    }

    public boolean isIdTipoLicenciaEmpty(Licencia entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Licencia> licencia = cq.from(Licencia.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(licencia, entity), cb.isNotNull(licencia.get(Licencia_.idTipoLicencia)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoLicencia findIdTipoLicencia(Licencia entity) {
        return this.getMergedEntity(entity).getIdTipoLicencia();
    }
    
}
