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
 * @author theo
 */
@Entity
@Table(name = "ingreso_producto", catalog = "farmasur", schema = "pharmacy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IngresoProducto.findAll", query = "SELECT i FROM IngresoProducto i")
    , @NamedQuery(name = "IngresoProducto.findByCodigo", query = "SELECT i FROM IngresoProducto i WHERE i.codigo = :codigo")
    , @NamedQuery(name = "IngresoProducto.findByFecha", query = "SELECT i FROM IngresoProducto i WHERE i.fecha = :fecha")
    , @NamedQuery(name = "IngresoProducto.findByHora", query = "SELECT i FROM IngresoProducto i WHERE i.hora = :hora")
    , @NamedQuery(name = "IngresoProducto.findByCantidad", query = "SELECT i FROM IngresoProducto i WHERE i.cantidad = :cantidad")
    , @NamedQuery(name = "IngresoProducto.findByCosto", query = "SELECT i FROM IngresoProducto i WHERE i.costo = :costo")})
public class IngresoProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private double costo;
    @JoinColumn(name = "cod_stock", referencedColumnName = "cod_stock")
    @ManyToOne(optional = false)
    private StockProducto codStock;

    public IngresoProducto() {
    }

    public IngresoProducto(Integer codigo) {
        this.codigo = codigo;
    }

    public IngresoProducto(Integer codigo, int cantidad, double costo) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.costo = costo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public StockProducto getCodStock() {
        return codStock;
    }

    public void setCodStock(StockProducto codStock) {
        this.codStock = codStock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IngresoProducto)) {
            return false;
        }
        IngresoProducto other = (IngresoProducto) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hrevfdz.models.IngresoProducto[ codigo=" + codigo + " ]";
    }
    
}
