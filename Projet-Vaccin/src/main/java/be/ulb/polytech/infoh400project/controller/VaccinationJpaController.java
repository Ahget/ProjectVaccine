/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ulb.polytech.infoh400project.controller;

import be.ulb.polytech.infoh400project.controller.exceptions.NonexistentEntityException;
import be.ulb.polytech.infoh400project.model.Vaccination;
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
public class VaccinationJpaController implements Serializable {

    public VaccinationJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vaccination vaccination) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vaccination);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vaccination vaccination) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vaccination = em.merge(vaccination);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vaccination.getIDVaccination();
                if (findVaccination(id) == null) {
                    throw new NonexistentEntityException("The vaccination with id " + id + " no longer exists.");
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
            Vaccination vaccination;
            try {
                vaccination = em.getReference(Vaccination.class, id);
                vaccination.getIDVaccination();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vaccination with id " + id + " no longer exists.", enfe);
            }
            em.remove(vaccination);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vaccination> findVaccinationEntities() {
        return findVaccinationEntities(true, -1, -1);
    }

    public List<Vaccination> findVaccinationEntities(int maxResults, int firstResult) {
        return findVaccinationEntities(false, maxResults, firstResult);
    }

    private List<Vaccination> findVaccinationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vaccination.class));
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

    public Vaccination findVaccination(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vaccination.class, id);
        } finally {
            em.close();
        }
    }

    public int getVaccinationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vaccination> rt = cq.from(Vaccination.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
