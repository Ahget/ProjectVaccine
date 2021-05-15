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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ahmed
 */
@Entity
@Table(name = "doctor", catalog = "projet_vaccin", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Doctor.findAll", query = "SELECT d FROM Doctor d"),
    @NamedQuery(name = "Doctor.findByIDDoctor", query = "SELECT d FROM Doctor d WHERE d.iDDoctor = :iDDoctor")})
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Doctor", nullable = false)
    private Integer iDDoctor;
    @JoinColumn(name = "Person", referencedColumnName = "ID_Person")
    @ManyToOne(optional = false)
    private Person idperson;

    public Doctor() {
    }

    public Doctor(Integer iDDoctor) {
        this.iDDoctor = iDDoctor;
    }

    public Integer getIDDoctor() {
        return iDDoctor;
    }

    public void setIDDoctor(Integer iDDoctor) {
        this.iDDoctor = iDDoctor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDDoctor != null ? iDDoctor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doctor)) {
            return false;
        }
        Doctor other = (Doctor) object;
        if ((this.iDDoctor == null && other.iDDoctor != null) || (this.iDDoctor != null && !this.iDDoctor.equals(other.iDDoctor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.ulb.polytech.infoh400project.model.Doctor[ iDDoctor=" + iDDoctor + " ]";
    }
    
}
