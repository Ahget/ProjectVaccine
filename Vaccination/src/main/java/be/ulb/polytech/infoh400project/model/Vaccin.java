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
@Table(name = "vaccin", catalog = "projet-vaccin", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vaccin.findAll", query = "SELECT v FROM Vaccin v"),
    @NamedQuery(name = "Vaccin.findByIDVaccin", query = "SELECT v FROM Vaccin v WHERE v.iDVaccin = :iDVaccin"),
    @NamedQuery(name = "Vaccin.findByNameVaccin", query = "SELECT v FROM Vaccin v WHERE v.nameVaccin = :nameVaccin")})
public class Vaccin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Vaccin", nullable = false)
    private Integer iDVaccin;
    @Basic(optional = false)
    @Column(name = "NameVaccin", nullable = false, length = 255)
    private String nameVaccin;

    public Vaccin() {
    }

    public Vaccin(Integer iDVaccin) {
        this.iDVaccin = iDVaccin;
    }

    public Vaccin(Integer iDVaccin, String nameVaccin) {
        this.iDVaccin = iDVaccin;
        this.nameVaccin = nameVaccin;
    }

    public Integer getIDVaccin() {
        return iDVaccin;
    }

    public void setIDVaccin(Integer iDVaccin) {
        this.iDVaccin = iDVaccin;
    }

    public String getNameVaccin() {
        return nameVaccin;
    }

    public void setNameVaccin(String nameVaccin) {
        this.nameVaccin = nameVaccin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDVaccin != null ? iDVaccin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vaccin)) {
            return false;
        }
        Vaccin other = (Vaccin) object;
        if ((this.iDVaccin == null && other.iDVaccin != null) || (this.iDVaccin != null && !this.iDVaccin.equals(other.iDVaccin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.ulb.polytech.infoh400project.model.Vaccin[ iDVaccin=" + iDVaccin + " ]";
    }
    
}
