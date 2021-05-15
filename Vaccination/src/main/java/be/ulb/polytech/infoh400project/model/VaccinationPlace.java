/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ulb.polytech.infoh400project.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ahmed
 */
@Entity
@Table(name = "vaccination_place", catalog = "projet_vaccin", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VaccinationPlace.findAll", query = "SELECT v FROM VaccinationPlace v"),
    @NamedQuery(name = "VaccinationPlace.findByIDVaccinationPlace", query = "SELECT v FROM VaccinationPlace v WHERE v.iDVaccinationPlace = :iDVaccinationPlace")})
public class VaccinationPlace implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Vaccination_Place", nullable = false)
    private Integer iDVaccinationPlace;

    public VaccinationPlace() {
    }

    public VaccinationPlace(Integer iDVaccinationPlace) {
        this.iDVaccinationPlace = iDVaccinationPlace;
    }

    public Integer getIDVaccinationPlace() {
        return iDVaccinationPlace;
    }

    public void setIDVaccinationPlace(Integer iDVaccinationPlace) {
        this.iDVaccinationPlace = iDVaccinationPlace;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDVaccinationPlace != null ? iDVaccinationPlace.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VaccinationPlace)) {
            return false;
        }
        VaccinationPlace other = (VaccinationPlace) object;
        if ((this.iDVaccinationPlace == null && other.iDVaccinationPlace != null) || (this.iDVaccinationPlace != null && !this.iDVaccinationPlace.equals(other.iDVaccinationPlace))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.ulb.polytech.infoh400project.model.VaccinationPlace[ iDVaccinationPlace=" + iDVaccinationPlace + " ]";
    }
    
}
