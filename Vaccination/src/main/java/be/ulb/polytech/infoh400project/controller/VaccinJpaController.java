/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ulb.polytech.infoh400project.controller;

import be.ulb.polytech.infoh400project.controller.exceptions.NonexistentEntityException;
import be.ulb.polytech.infoh400project.model.Vaccin;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ahmed
 */
public class VaccinJpaController implements Serializable {

    public VaccinJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vaccin vaccin) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vaccin);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vaccin vaccin) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vaccin = em.merge(vaccin);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vaccin.getIDVaccin();
                if (findVaccin(id) == null) {
                    throw new NonexistentEntityException("The vaccin with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vaccin vaccin;
            try {
                vaccin = em.getReference(Vaccin.class, id);
                vaccin.getIDVaccin();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vaccin with id " + id + " no longer exists.", enfe);
            }
            em.remove(vaccin);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vaccin> findVaccinEntities() {
        return findVaccinEntities(true, -1, -1);
    }

    public List<Vaccin> findVaccinEntities(int maxResults, int firstResult) {
        return findVaccinEntities(false, maxResults, firstResult);
    }

    private List<Vaccin> findVaccinEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vaccin.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Vaccin findVaccin(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vaccin.class, id);
        } finally {
            em.close();
        }
    }

    public int getVaccinCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vaccin> rt = cq.from(Vaccin.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
