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
@Table(name = "stock", catalog = "projet-vaccin", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stock.findAll", query = "SELECT s FROM Stock s"),
    @NamedQuery(name = "Stock.findByIDStock", query = "SELECT s FROM Stock s WHERE s.iDStock = :iDStock"),
    @NamedQuery(name = "Stock.findByQuantity", query = "SELECT s FROM Stock s WHERE s.quantity = :quantity")})
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Stock", nullable = false)
    private Integer iDStock;
    @Basic(optional = false)
    @Column(name = "Quantity", nullable = false)
    private int quantity;

    public Stock() {
    }

    public Stock(Integer iDStock) {
        this.iDStock = iDStock;
    }

    public Stock(Integer iDStock, int quantity) {
        this.iDStock = iDStock;
        this.quantity = quantity;
    }

    public Integer getIDStock() {
        return iDStock;
    }

    public void setIDStock(Integer iDStock) {
        this.iDStock = iDStock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDStock != null ? iDStock.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.iDStock == null && other.iDStock != null) || (this.iDStock != null && !this.iDStock.equals(other.iDStock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.ulb.polytech.infoh400project.model.Stock[ iDStock=" + iDStock + " ]";
    }
    
}
