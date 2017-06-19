/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrevfdz.models;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lheo2
 */
@Entity
@Table(name = "start_work", catalog = "farmasur", schema = "pharmacy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StartWork.findAll", query = "SELECT s FROM StartWork s")
    , @NamedQuery(name = "StartWork.findById", query = "SELECT s FROM StartWork s WHERE s.id = :id")
    , @NamedQuery(name = "StartWork.findByFecha", query = "SELECT s FROM StartWork s WHERE s.fecha = :fecha")
    , @NamedQuery(name = "StartWork.findByCapital", query = "SELECT s FROM StartWork s WHERE s.capital = :capital")})
public class StartWork implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capital")
    private double capital;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users userId;

    public StartWork() {
    }

    public StartWork(Integer id) {
        this.id = id;
    }

    public StartWork(Integer id, Date fecha, double capital) {
        this.id = id;
        this.fecha = fecha;
        this.capital = capital;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StartWork)) {
            return false;
        }
        StartWork other = (StartWork) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hrevfdz.models.StartWork[ id=" + id + " ]";
    }
    
}
