/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.Bitacora;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.Bitacora_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.TipoBitacora;
import ejb.Usuario;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class BitacoraFacade extends AbstractFacade<Bitacora> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BitacoraFacade() {
        super(Bitacora.class);
    }

    public boolean isIdTipoBitacoraEmpty(Bitacora entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bitacora> bitacora = cq.from(Bitacora.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bitacora, entity), cb.isNotNull(bitacora.get(Bitacora_.idTipoBitacora)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoBitacora findIdTipoBitacora(Bitacora entity) {
        return this.getMergedEntity(entity).getIdTipoBitacora();
    }

    public boolean isIdUsuarioEmpty(Bitacora entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bitacora> bitacora = cq.from(Bitacora.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bitacora, entity), cb.isNotNull(bitacora.get(Bitacora_.idUsuario)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Usuario findIdUsuario(Bitacora entity) {
        return this.getMergedEntity(entity).getIdUsuario();
    }
    
}
