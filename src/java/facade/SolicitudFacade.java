/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.Solicitud;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.Solicitud_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.HistoriaEstado;
import ejb.Ruta;
import ejb.Usuario;
import ejb.Comision;
import ejb.Usuario;
import ejb.TipoSolicitud;
import ejb.TipoUsoVehiculo;
import ejb.Usuario;
import java.util.Collection;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class SolicitudFacade extends AbstractFacade<Solicitud> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudFacade() {
        super(Solicitud.class);
    }

    public boolean isHistoriaEstadoCollectionEmpty(Solicitud entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Solicitud> solicitud = cq.from(Solicitud.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(solicitud, entity), cb.isNotEmpty(solicitud.get(Solicitud_.historiaEstadoCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<HistoriaEstado> findHistoriaEstadoCollection(Solicitud entity) {
        Solicitud mergedEntity = this.getMergedEntity(entity);
        Collection<HistoriaEstado> historiaEstadoCollection = mergedEntity.getHistoriaEstadoCollection();
        historiaEstadoCollection.size();
        return historiaEstadoCollection;
    }

    public boolean isRutaCollectionEmpty(Solicitud entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Solicitud> solicitud = cq.from(Solicitud.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(solicitud, entity), cb.isNotEmpty(solicitud.get(Solicitud_.rutaCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Ruta> findRutaCollection(Solicitud entity) {
        Solicitud mergedEntity = this.getMergedEntity(entity);
        Collection<Ruta> rutaCollection = mergedEntity.getRutaCollection();
        rutaCollection.size();
        return rutaCollection;
    }

    public boolean isIdUsuarioAprobadorEmpty(Solicitud entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Solicitud> solicitud = cq.from(Solicitud.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(solicitud, entity), cb.isNotNull(solicitud.get(Solicitud_.idUsuarioAprobador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Usuario findIdUsuarioAprobador(Solicitud entity) {
        return this.getMergedEntity(entity).getIdUsuarioAprobador();
    }

    public boolean isIdComisionEmpty(Solicitud entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Solicitud> solicitud = cq.from(Solicitud.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(solicitud, entity), cb.isNotNull(solicitud.get(Solicitud_.idComision)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Comision findIdComision(Solicitud entity) {
        return this.getMergedEntity(entity).getIdComision();
    }

    public boolean isIdUsuarioGestorEmpty(Solicitud entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Solicitud> solicitud = cq.from(Solicitud.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(solicitud, entity), cb.isNotNull(solicitud.get(Solicitud_.idUsuarioGestor)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Usuario findIdUsuarioGestor(Solicitud entity) {
        return this.getMergedEntity(entity).getIdUsuarioGestor();
    }

    public boolean isIdTipoComisionEmpty(Solicitud entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Solicitud> solicitud = cq.from(Solicitud.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(solicitud, entity), cb.isNotNull(solicitud.get(Solicitud_.idTipoComision)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoSolicitud findIdTipoComision(Solicitud entity) {
        return this.getMergedEntity(entity).getIdTipoComision();
    }

    public boolean isIdTipoUsoVehiculoEmpty(Solicitud entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Solicitud> solicitud = cq.from(Solicitud.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(solicitud, entity), cb.isNotNull(solicitud.get(Solicitud_.idTipoUsoVehiculo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoUsoVehiculo findIdTipoUsoVehiculo(Solicitud entity) {
        return this.getMergedEntity(entity).getIdTipoUsoVehiculo();
    }

    public boolean isIdUsuarioEmpty(Solicitud entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Solicitud> solicitud = cq.from(Solicitud.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(solicitud, entity), cb.isNotNull(solicitud.get(Solicitud_.idUsuario)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Usuario findIdUsuario(Solicitud entity) {
        return this.getMergedEntity(entity).getIdUsuario();
    }
    
}
