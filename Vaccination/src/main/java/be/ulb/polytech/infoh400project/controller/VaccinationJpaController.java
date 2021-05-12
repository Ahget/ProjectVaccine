/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ulb.polytech.infoh400project.controller;

import be.ulb.polytech.infoh400project.controller.exceptions.NonexistentEntityException;
import be.ulb.polytech.infoh400project.model.VaccinationBen;
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

    public void create(VaccinationBen vaccination) {
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

    public void edit(VaccinationBen vaccination) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vaccination = em.merge(vaccination);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vaccination.getIdvaccination();
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
            VaccinationBen vaccination;
            try {
                vaccination = em.getReference(VaccinationBen.class, id);
                vaccination.getIdvaccination();
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

    public List<VaccinationBen> findVaccinationEntities() {
        return findVaccinationEntities(true, -1, -1);
    }

    public List<VaccinationBen> findVaccinationEntities(int maxResults, int firstResult) {
        return findVaccinationEntities(false, maxResults, firstResult);
    }

    private List<VaccinationBen> findVaccinationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VaccinationBen.class));
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

    public VaccinationBen findVaccination(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VaccinationBen.class, id);
        } finally {
            em.close();
        }
    }

    public int getVaccinationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VaccinationBen> rt = cq.from(VaccinationBen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
