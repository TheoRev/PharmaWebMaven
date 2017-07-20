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
@Table(name = "sale", catalog = "farmasur", schema = "pharmacy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sale.findAll", query = "SELECT s FROM Sale s")
    , @NamedQuery(name = "Sale.findByCodSale", query = "SELECT s FROM Sale s WHERE s.codSale = :codSale")
    , @NamedQuery(name = "Sale.findByCantidad", query = "SELECT s FROM Sale s WHERE s.cantidad = :cantidad")
    , @NamedQuery(name = "Sale.findByPrecio", query = "SELECT s FROM Sale s WHERE s.precio = :precio")
    , @NamedQuery(name = "Sale.findBySubtotal", query = "SELECT s FROM Sale s WHERE s.subtotal = :subtotal")
    , @NamedQuery(name = "Sale.findByFecha", query = "SELECT s FROM Sale s WHERE s.fecha = :fecha")
    , @NamedQuery(name = "Sale.findByHora", query = "SELECT s FROM Sale s WHERE s.hora = :hora")})
public class Sale implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_sale")
    private Integer codSale;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private double precio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "subtotal")
    private Double subtotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @JoinColumn(name = "cod_stock", referencedColumnName = "cod_stock")
    @ManyToOne(optional = false)
    private StockProducto codStock;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Users userId;

    public Sale() {
    }

    public Sale(Integer codSale) {
        this.codSale = codSale;
    }

    public Sale(Integer codSale, int cantidad, double precio, Date fecha, Date hora) {
        this.codSale = codSale;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Integer getCodSale() {
        return codSale;
    }

    public void setCodSale(Integer codSale) {
        this.codSale = codSale;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
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

    public StockProducto getCodStock() {
        return codStock;
    }

    public void setCodStock(StockProducto codStock) {
        this.codStock = codStock;
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
        hash += (codSale != null ? codSale.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sale)) {
            return false;
        }
        Sale other = (Sale) object;
        if ((this.codSale == null && other.codSale != null) || (this.codSale != null && !this.codSale.equals(other.codSale))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hrevfdz.models.Sale[ codSale=" + codSale + " ]";
    }
    
}
