/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.Usuario_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.Bitacora;
import ejb.Solicitud;
import ejb.Rol;
import ejb.Unidad;
import java.util.Collection;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public boolean isBitacoraCollectionEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotEmpty(usuario.get(Usuario_.bitacoraCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Bitacora> findBitacoraCollection(Usuario entity) {
        Usuario mergedEntity = this.getMergedEntity(entity);
        Collection<Bitacora> bitacoraCollection = mergedEntity.getBitacoraCollection();
        bitacoraCollection.size();
        return bitacoraCollection;
    }

    public boolean isSolicitudCollectionEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotEmpty(usuario.get(Usuario_.solicitudCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Solicitud> findSolicitudCollection(Usuario entity) {
        Usuario mergedEntity = this.getMergedEntity(entity);
        Collection<Solicitud> solicitudCollection = mergedEntity.getSolicitudCollection();
        solicitudCollection.size();
        return solicitudCollection;
    }

    public boolean isSolicitudCollection1Empty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotEmpty(usuario.get(Usuario_.solicitudCollection1)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Solicitud> findSolicitudCollection1(Usuario entity) {
        Usuario mergedEntity = this.getMergedEntity(entity);
        Collection<Solicitud> solicitudCollection1 = mergedEntity.getSolicitudCollection1();
        solicitudCollection1.size();
        return solicitudCollection1;
    }

    public boolean isSolicitudCollection2Empty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotEmpty(usuario.get(Usuario_.solicitudCollection2)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Solicitud> findSolicitudCollection2(Usuario entity) {
        Usuario mergedEntity = this.getMergedEntity(entity);
        Collection<Solicitud> solicitudCollection2 = mergedEntity.getSolicitudCollection2();
        solicitudCollection2.size();
        return solicitudCollection2;
    }

    public boolean isIdRolEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotNull(usuario.get(Usuario_.idRol)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Rol findIdRol(Usuario entity) {
        return this.getMergedEntity(entity).getIdRol();
    }

    public boolean isIdUnidadEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotNull(usuario.get(Usuario_.idUnidad)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Unidad findIdUnidad(Usuario entity) {
        return this.getMergedEntity(entity).getIdUnidad();
    }
    
}
