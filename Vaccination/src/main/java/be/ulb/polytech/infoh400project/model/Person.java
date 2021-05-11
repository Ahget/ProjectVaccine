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
@Table(name = "Person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findByIdperson", query = "SELECT p FROM Person p WHERE p.ID_Person = :ID_Person"),
    @NamedQuery(name = "Person.findByIdcard", query = "SELECT p FROM Person p WHERE p.ID_Card = :ID_Card"),
    @NamedQuery(name = "Person.findByName", query = "SELECT p FROM Person p WHERE p.Name = :Name"),
    @NamedQuery(name = "Person.findBySurname", query = "SELECT p FROM Person p WHERE p.Surname = :Surname"),
    @NamedQuery(name = "Person.findByDateofbirth", query = "SELECT p FROM Person p WHERE p.Date_Of_Birth = :Date_Of_Birth"),
    @NamedQuery(name = "Person.findByPhonenumber", query = "SELECT p FROM Person p WHERE p.Phone_Number = :Phone_Number"),
    @NamedQuery(name = "Person.findByEmail", query = "SELECT p FROM Person p WHERE p.Email = :Email"),
    @NamedQuery(name = "Person.findDuplicate", query = "SELECT p FROM Person p WHERE p.Name = :firstname AND p.Surname = :Surname AND p.Date_Of_Birth = :Date_Of_Birth")})

public class Person implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Person")
    private Integer idperson;
    @Column(name = "Name")
    private String name;
    @Column(name = "Surname")
    private String surname;
    @Column(name = "Date_Of_Birth")
    @Temporal(TemporalType.DATE)
    private Date dateofbirth;
    @Column(name = "Phone_Number")
    private String phonenumber;
    @Column(name = "Email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idperson")
    private List<Patient> patientList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idperson")
    private List<Doctor> doctortList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idperson")
    private List<Adress> AdressList;
    
    
    public void Person(){
    
    }

    public Integer getIdperson() {
        return idperson;
    }

    public void setIdperson(Integer idperson) {
        this.idperson = idperson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }
    
}
