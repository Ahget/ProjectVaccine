/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ulb.polytech.infoh400project.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ahmed
 */
@Entity
@Table(name = "vaccination", catalog = "projet_vaccin", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vaccination.findAll", query = "SELECT v FROM Vaccination v"),
    @NamedQuery(name = "Vaccination.findByIDVaccination", query = "SELECT v FROM Vaccination v WHERE v.iDVaccination = :iDVaccination"),
    @NamedQuery(name = "Vaccination.findByDataTime", query = "SELECT v FROM Vaccination v WHERE v.dataTime = :dataTime"),
    @NamedQuery(name = "Vaccination.findByVaccinationState", query = "SELECT v FROM Vaccination v WHERE v.vaccinationstate = :vaccinationstate")})
public class Vaccination implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Vaccination", nullable = false)
    private Integer iDVaccination;
    @Basic(optional = false)
    @Column(name = "data_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataTime;
    @Basic(optional = false)
    @Column(name = "VaccinationState", nullable = false)
    private short vaccinationstate;
    @JoinColumn(name = "Patient", referencedColumnName = "ID_Patient")
    @ManyToOne(optional = false)
    private Patient idpatient;
    @JoinColumn(name = "Vaccin", referencedColumnName = "ID_Vaccin")
    @ManyToOne(optional = false)
    private Vaccin idvaccin;

    public Vaccination() {
    }

    public Vaccination(Integer iDVaccination) {
        this.iDVaccination = iDVaccination;
    }

    public Vaccination(Integer iDVaccination, Date dataTime, short vaccinationstate) {
        this.iDVaccination = iDVaccination;
        this.dataTime = dataTime;
        this.vaccinationstate = vaccinationstate;
    }

    public Integer getIDVaccination() {
        return iDVaccination;
    }

    public void setIDVaccination(Integer iDVaccination) {
        this.iDVaccination = iDVaccination;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public short getVaccinationState() {
        return vaccinationstate;
    }

    public void setVaccinationState(short vaccinationstate) {
        this.vaccinationstate = vaccinationstate;
    }
    
    public Patient getIDPatient() {
        return idpatient;
    }

    public void setIDPatient(Patient idpatient) {
        this.idpatient = idpatient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDVaccination != null ? iDVaccination.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vaccination)) {
            return false;
        }
        Vaccination other = (Vaccination) object;
        if ((this.iDVaccination == null && other.iDVaccination != null) || (this.iDVaccination != null && !this.iDVaccination.equals(other.iDVaccination))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iDVaccination= " + iDVaccination + " idVaccin= " + idvaccin.toString();
    }
    
}
