/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ulb.polytech.infoh400project.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Benjamin
 */
@Entity
@Table(name = "Vaccination")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vaccination.findAll", query = "SELECT v FROM Vaccination v"),
    @NamedQuery(name = "Vaccination.findByIDvaccination", query = "SELECT v FROM Vaccination v WHERE v.ID_Vaccination = :ID_Vaccination"),
    @NamedQuery(name = "Vaccination.findByPatient", query = "SELECT v FROM Vaccination v WHERE v.Patient = :Patient"),
    @NamedQuery(name = "Vaccination.findByDoctor", query = "SELECT v FROM Vaccination v WHERE v.Doctor = :Doctor"),
    @NamedQuery(name = "Vaccination.findByVaccin", query = "SELECT v FROM Vaccination v WHERE v.Vaccin = :Vaccin"),
    @NamedQuery(name = "Vaccination.findByDateTime", query = "SELECT v FROM Vaccination v WHERE v.Date_Time = :Date_Time"),
    @NamedQuery(name = "Vaccination.findDuplicate", query = "SELECT v FROM Vaccination v WHERE v.Patient = :Patient AND p.Vaccin = :Vaccin")})

public class Vaccination implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Vaccination")
    private Integer idvaccination;
    @JoinColumn(name = "Patient", referencedColumnName = "ID_Patient")
    @ManyToOne(optional = false)
    private Person patient;
    @JoinColumn(name = "Doctor", referencedColumnName = "ID_Doctor")
    @ManyToOne(optional = false)
    private Person doctor;
    @JoinColumn(name = "Vaccin", referencedColumnName = "ID_Vaccin")
    @ManyToOne(optional = false)
    private Person vaccin;
    @Column(name = "Date_Time")
    @Temporal(TemporalType.DATE)
    private Date date_time;
    @Column(name = "State")
    private Boolean state;
    
    public void Vaccination(){
        
    }

    public Integer getIdvaccination() {
        return idvaccination;
    }

    public void setIdvaccination(Integer idvaccination) {
        this.idvaccination = idvaccination;
    }

    public Person getPatient() {
        return patient;
    }

    public void setPatient(Person patient) {
        this.patient = patient;
    }

    public Person getDoctor() {
        return doctor;
    }

    public void setDoctor(Person doctor) {
        this.doctor = doctor;
    }

    public Person getVaccin() {
        return vaccin;
    }

    public void setVaccin(Person vaccin) {
        this.vaccin = vaccin;
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
    
}
