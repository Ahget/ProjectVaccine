/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ulb.polytech.infoh400project.controller;

import be.ulb.polytech.infoh400project.controller.exceptions.NonexistentEntityException;
import be.ulb.polytech.infoh400project.model.Patient;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import be.ulb.polytech.infoh400project.model.Person;
import be.ulb.polytech.infoh400project.model.Vaccination;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ahmed
 */
public class PatientJpaController implements Serializable {

    public PatientJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Patient patient) {
        
        if (patient.getVaccinationList() == null) {
            patient.setVaccinationList(new ArrayList<Vaccination>());
        }
        
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Person idperson = patient.getIdperson();
            if (idperson != null) {
                idperson = em.getReference(idperson.getClass(), idperson.getIdperson());
                patient.setIdperson(idperson);
            }
            List<Vaccination> attachedVaccinationList = new ArrayList<Vaccination>();
            for (Vaccination VaccinationListVaccinationToAttach : patient.getVaccinationList()) {
                //VaccinationListVaccinationToAttach = em.getReference( VaccinationListVaccinationToAttach.getState(), VaccinationListVaccinationToAttach.getIDVaccination());
                //attachedVaccinationList.add(VaccinationListVaccinationToAttach);
            }
            patient.setVaccinationList(attachedVaccinationList);
            em.persist(patient);
            if (idperson != null) {
                idperson.getPatientList().add(patient);
                idperson = em.merge(idperson);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Patient patient) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Patient persistentPatient = em.find(Patient.class, patient.getIDPatient());
            Person idpersonOld = persistentPatient.getIdperson();
            Person idpersonNew = patient.getIdperson();
            if (idpersonNew != null) {
                idpersonNew = em.getReference(idpersonNew.getClass(), idpersonNew.getIdperson());
                patient.setIdperson(idpersonNew);
            }
            patient = em.merge(patient);
            if (idpersonOld != null && !idpersonOld.equals(idpersonNew)) {
                idpersonOld.getPatientList().remove(patient);
                idpersonOld = em.merge(idpersonOld);
            }
            if (idpersonNew != null && !idpersonNew.equals(idpersonOld)) {
                idpersonNew.getPatientList().add(patient);
                idpersonNew = em.merge(idpersonNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = patient.getIDPatient();
                if (findPatient(id) == null) {
                    throw new NonexistentEntityException("The patient with id " + id + " no longer exists.");
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
            Patient patient;
            try {
                patient = em.getReference(Patient.class, id);
                patient.getIDPatient();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The patient with id " + id + " no longer exists.", enfe);
            }
            Person idperson = patient.getIdperson();
            if (idperson != null) {
                idperson.getPatientList().remove(patient);
                idperson = em.merge(idperson);
            }
            em.remove(patient);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Patient> findPatientEntities() {
        return findPatientEntities(true, -1, -1);
    }

    public List<Patient> findPatientEntities(int maxResults, int firstResult) {
        return findPatientEntities(false, maxResults, firstResult);
    }

    private List<Patient> findPatientEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Patient.class));
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

    public Patient findPatient(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Patient.class, id);
        } finally {
            em.close();
        }
    }

    public int getPatientCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Patient> rt = cq.from(Patient.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
