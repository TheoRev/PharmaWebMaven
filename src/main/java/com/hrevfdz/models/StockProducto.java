/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrevfdz.models;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author theo
 */
@Entity
@Table(name = "stock_producto", catalog = "farmasur", schema = "pharmacy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StockProducto.findAll", query = "SELECT s FROM StockProducto s")
    , @NamedQuery(name = "StockProducto.findByCodStock", query = "SELECT s FROM StockProducto s WHERE s.codStock = :codStock")
    , @NamedQuery(name = "StockProducto.findByNombre", query = "SELECT s FROM StockProducto s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "StockProducto.findByPresentacion", query = "SELECT s FROM StockProducto s WHERE s.presentacion = :presentacion")
    , @NamedQuery(name = "StockProducto.findByLote", query = "SELECT s FROM StockProducto s WHERE s.lote = :lote")
    , @NamedQuery(name = "StockProducto.findByMonto", query = "SELECT s FROM StockProducto s WHERE s.monto = :monto")
    , @NamedQuery(name = "StockProducto.findByCantidad", query = "SELECT s FROM StockProducto s WHERE s.cantidad = :cantidad")
    , @NamedQuery(name = "StockProducto.findByCosto", query = "SELECT s FROM StockProducto s WHERE s.costo = :costo")
    , @NamedQuery(name = "StockProducto.findByFecVen", query = "SELECT s FROM StockProducto s WHERE s.fecVen = :fecVen")})
public class StockProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_stock")
    private Integer codStock;
    @Size(max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 2147483647)
    @Column(name = "presentacion")
    private String presentacion;
    @Size(max = 2147483647)
    @Column(name = "lote")
    private String lote;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto")
    private Double monto;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "costo")
    private Double costo;
    @Column(name = "fec_ven")
    @Temporal(TemporalType.DATE)
    private Date fecVen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codStock")
    private List<IngresoProducto> ingresoProductoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codStock")
    private List<Sale> saleList;
    @JoinColumn(name = "cod_lab", referencedColumnName = "cod_lab")
    @ManyToOne
    private Laboratory codLab;
    @OneToMany(mappedBy = "codStock")
    private List<Payments> paymentsList;

    public StockProducto() {
    }

    public StockProducto(Integer codStock) {
        this.codStock = codStock;
    }

    public Integer getCodStock() {
        return codStock;
    }

    public void setCodStock(Integer codStock) {
        this.codStock = codStock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Date getFecVen() {
        return fecVen;
    }

    public void setFecVen(Date fecVen) {
        this.fecVen = fecVen;
    }

    @XmlTransient
    public List<IngresoProducto> getIngresoProductoList() {
        return ingresoProductoList;
    }

    public void setIngresoProductoList(List<IngresoProducto> ingresoProductoList) {
        this.ingresoProductoList = ingresoProductoList;
    }

    @XmlTransient
    public List<Sale> getSaleList() {
        return saleList;
    }

    public void setSaleList(List<Sale> saleList) {
        this.saleList = saleList;
    }

    public Laboratory getCodLab() {
        return codLab;
    }

    public void setCodLab(Laboratory codLab) {
        this.codLab = codLab;
    }

    @XmlTransient
    public List<Payments> getPaymentsList() {
        return paymentsList;
    }

    public void setPaymentsList(List<Payments> paymentsList) {
        this.paymentsList = paymentsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codStock != null ? codStock.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StockProducto)) {
            return false;
        }
        StockProducto other = (StockProducto) object;
        if ((this.codStock == null && other.codStock != null) || (this.codStock != null && !this.codStock.equals(other.codStock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hrevfdz.models.StockProducto[ codStock=" + codStock + " ]";
    }
    
}
