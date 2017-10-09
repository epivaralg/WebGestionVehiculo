/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.Ruta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.Ruta_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.Municipio;
import ejb.Solicitud;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class RutaFacade extends AbstractFacade<Ruta> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RutaFacade() {
        super(Ruta.class);
    }

    public boolean isIdMunicipioEmpty(Ruta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Ruta> ruta = cq.from(Ruta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(ruta, entity), cb.isNotNull(ruta.get(Ruta_.idMunicipio)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Municipio findIdMunicipio(Ruta entity) {
        return this.getMergedEntity(entity).getIdMunicipio();
    }

    public boolean isIdSolicitudEmpty(Ruta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Ruta> ruta = cq.from(Ruta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(ruta, entity), cb.isNotNull(ruta.get(Ruta_.idSolicitud)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Solicitud findIdSolicitud(Ruta entity) {
        return this.getMergedEntity(entity).getIdSolicitud();
    }
    
}
