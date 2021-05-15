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
@Table(name = "adress", catalog = "projet_vaccin", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adress.findAll", query = "SELECT a FROM Adress a"),
    @NamedQuery(name = "Adress.findByIDAdress", query = "SELECT a FROM Adress a WHERE a.iDAdress = :iDAdress"),
    @NamedQuery(name = "Adress.findByStreetName", query = "SELECT a FROM Adress a WHERE a.streetName = :streetName"),
    @NamedQuery(name = "Adress.findByHouseNumber", query = "SELECT a FROM Adress a WHERE a.houseNumber = :houseNumber"),
    @NamedQuery(name = "Adress.findByCity", query = "SELECT a FROM Adress a WHERE a.city = :city"),
    @NamedQuery(name = "Adress.findByZIPCode", query = "SELECT a FROM Adress a WHERE a.zIPCode = :zIPCode"),
    @NamedQuery(name = "Adress.findByCountry", query = "SELECT a FROM Adress a WHERE a.country = :country")})
public class Adress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Adress", nullable = false)
    private Integer iDAdress;
    @Basic(optional = false)
    @Column(name = "Street_Name", nullable = false, length = 255)
    private String streetName;
    @Basic(optional = false)
    @Column(name = "House_Number", nullable = false, length = 255)
    private String houseNumber;
    @Basic(optional = false)
    @Column(name = "City", nullable = false, length = 255)
    private String city;
    @Basic(optional = false)
    @Column(name = "ZIP_Code", nullable = false, length = 255)
    private String zIPCode;
    @Basic(optional = false)
    @Column(name = "Country", nullable = false, length = 255)
    private String country;

    public Adress() {
    }

    public Adress(Integer iDAdress) {
        this.iDAdress = iDAdress;
    }

    public Adress(Integer iDAdress, String streetName, String houseNumber, String city, String zIPCode, String country) {
        this.iDAdress = iDAdress;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.city = city;
        this.zIPCode = zIPCode;
        this.country = country;
    }

    public Integer getIDAdress() {
        return iDAdress;
    }

    public void setIDAdress(Integer iDAdress) {
        this.iDAdress = iDAdress;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZIPCode() {
        return zIPCode;
    }

    public void setZIPCode(String zIPCode) {
        this.zIPCode = zIPCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDAdress != null ? iDAdress.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adress)) {
            return false;
        }
        Adress other = (Adress) object;
        if ((this.iDAdress == null && other.iDAdress != null) || (this.iDAdress != null && !this.iDAdress.equals(other.iDAdress))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.ulb.polytech.infoh400project.model.Adress[ iDAdress=" + iDAdress + " ]";
    }
    
}
