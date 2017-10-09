/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.Grupo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.Grupo_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.Piloto;
import java.util.Collection;

/**
 *
 * @author elliot.pivaral
 */
@Stateless
public class GrupoFacade extends AbstractFacade<Grupo> {

    @PersistenceContext(unitName = "GVBetaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoFacade() {
        super(Grupo.class);
    }

    public boolean isPilotoCollectionEmpty(Grupo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Grupo> grupo = cq.from(Grupo.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(grupo, entity), cb.isNotEmpty(grupo.get(Grupo_.pilotoCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Piloto> findPilotoCollection(Grupo entity) {
        Grupo mergedEntity = this.getMergedEntity(entity);
        Collection<Piloto> pilotoCollection = mergedEntity.getPilotoCollection();
        pilotoCollection.size();
        return pilotoCollection;
    }
    
}
