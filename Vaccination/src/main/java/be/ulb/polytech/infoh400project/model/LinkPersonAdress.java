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
@Table(name = "link_person-adress", catalog = "projet-vaccin", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LinkPersonAdress.findAll", query = "SELECT l FROM LinkPersonAdress l"),
    @NamedQuery(name = "LinkPersonAdress.findByIdPA", query = "SELECT l FROM LinkPersonAdress l WHERE l.idPA = :idPA"),
    @NamedQuery(name = "LinkPersonAdress.findByType", query = "SELECT l FROM LinkPersonAdress l WHERE l.type = :type")})
public class LinkPersonAdress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_P-A", nullable = false)
    private Integer idPA;
    @Basic(optional = false)
    @Column(name = "Type", nullable = false, length = 255)
    private String type;

    public LinkPersonAdress() {
    }

    public LinkPersonAdress(Integer idPA) {
        this.idPA = idPA;
    }

    public LinkPersonAdress(Integer idPA, String type) {
        this.idPA = idPA;
        this.type = type;
    }

    public Integer getIdPA() {
        return idPA;
    }

    public void setIdPA(Integer idPA) {
        this.idPA = idPA;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPA != null ? idPA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LinkPersonAdress)) {
            return false;
        }
        LinkPersonAdress other = (LinkPersonAdress) object;
        if ((this.idPA == null && other.idPA != null) || (this.idPA != null && !this.idPA.equals(other.idPA))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.ulb.polytech.infoh400project.model.LinkPersonAdress[ idPA=" + idPA + " ]";
    }
    
}
