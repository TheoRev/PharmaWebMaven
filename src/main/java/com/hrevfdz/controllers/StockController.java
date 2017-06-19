package com.hrevfdz.controllers;

import com.hrevfdz.dao.IngresoProductoDAO;
import com.hrevfdz.dao.LaboratoryDAO;
import com.hrevfdz.dao.StockProductoDAO;
import com.hrevfdz.models.IngresoProducto;
import com.hrevfdz.models.Laboratory;
import com.hrevfdz.models.StockProducto;
import com.hrevfdz.services.IPharmacy;
import com.hrevfdz.util.AccionUtil;
import com.hrevfdz.util.QueriesUtil;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class StockController extends IngresoProdController implements Serializable {

    private IPharmacy<StockProducto> dao;
    private List<StockProducto> stockProductos;
    private List<Laboratory> labs;
    private StockProducto stockProducto;
    private List<IngresoProducto> ingresoProductos;
    private IngresoProducto ingresoProducto;
    private String accion;

    @PostConstruct
    public void init() {
        stockProducto = new StockProducto();
        ingresoProducto = new IngresoProducto();
        doFindAll();
        doQuerySelectLab();
    }

    public void doFindAll() {
        FacesMessage message = null;
        dao = new StockProductoDAO();

        try {
            stockProductos = dao.findAll();
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR EN DB", e.getMessage());
        }

        if (message != null) {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    private void asignarDatos(StockProducto sp) throws ParseException {
        ingresoProducto.setCantidad(sp.getCantidad());
        ingresoProducto.setCosto(sp.getMonto());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fec = new Date();
        ingresoProducto.setFecha(sdf.parse(sdf.format(fec)));
        sdf = new SimpleDateFormat("HH:mm:ss");
        ingresoProducto.setHora(sdf.parse(sdf.format(fec)));
        ingresoProducto.setCodStock(sp);
    }

    public void doCreate() {
        FacesMessage msg = null;
        dao = new StockProductoDAO();
        IngresoProductoDAO idao = new IngresoProductoDAO();

        try {
            boolean result = dao.Create(stockProducto);

            if (result) {
                stockProductos.add(stockProductos.size(), stockProducto);
                String query = "SELECT st FROM StockProducto st WHERE st.codStock = (SELECT MAX(st.codStock) FROM StockProducto st)";
                StockProducto tempStock = dao.findBy(query);
                doFindAllIngreso();
                asignarDatos(tempStock);
                boolean result2 = idao.Create(ingresoProducto);
                stockProducto = new StockProducto();

                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Datos guardado correctamente");
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "El proceso no se ejecutó");
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "FATAL ERROR", e.getMessage());
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void doUpdate(StockProducto sp) {
        FacesMessage msg = null;
        dao = new StockProductoDAO();
        IngresoProductoDAO idao = new IngresoProductoDAO();

        try {
            boolean result = dao.Update(stockProducto);

            if (result) {
                stockProductos.clear();
                doFindAll();
//                String query = "SELECT st FROM StockProducto st WHERE st.codStock = " + stockProducto.getCodStock();
//                StockProducto tempStock = dao.findBy(query);
                asignarDatos(sp);
                boolean result2 = idao.Create(ingresoProducto);
                doFindAllIngreso();
                if (result2) {
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Se modificó correctamente");
                } else {
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "No se registro correctamente el ingreso del producto");
                }
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "El proceso no se ejecutó");
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "FATAL ERROR", e.getMessage());
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void doDelete(StockProducto sp) {
        FacesMessage msg = null;
        dao = new StockProductoDAO();

        try {
            boolean result = dao.Delete(stockProducto);

            if (result) {
                stockProductos.clear();
                doFindAll();
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Se eliminó correctamente");
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "El proceso no se ejecutó");
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "FATAL ERROR", e.getMessage());
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void doQuerySelectLab() {
        FacesMessage msg = null;
        IPharmacy<Laboratory> dao = new LaboratoryDAO();

        try {
            labs = dao.findByQuery(QueriesUtil.STOCK_X_LABORATORY);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "FATAL ERROR", e.getMessage());
        }

        if (msg != null) {
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public String colorearFila(int cant) {
        if (cant > 5) {
            return "background-color: #81F7D8";
        } else if (cant == 0) {
            return "background-color: #F78181";
        } else {
            return "background-color: #F1F577";
        }
    }

    public void doNew() {
        accion = AccionUtil.CREATE;
        stockProducto = new StockProducto();
        doQuerySelectLab();
    }

    public void doUpgrade(StockProducto sp) {
        accion = AccionUtil.UPDATE;
        stockProducto = sp;
        doQuerySelectLab();
    }

    public void doExecute() {
        switch (accion) {
            case AccionUtil.CREATE:
                doCreate();
                break;
            case AccionUtil.UPDATE:
                doUpdate(stockProducto);
                break;
        }
    }

    public IPharmacy<StockProducto> getDao() {
        return dao;
    }

    public void setDao(IPharmacy<StockProducto> dao) {
        this.dao = dao;
    }

    public List<StockProducto> getStockProductos() {
        return stockProductos;
    }

    public void setStockProductos(List<StockProducto> stockProductos) {
        this.stockProductos = stockProductos;
    }

    public StockProducto getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(StockProducto stockProducto) {
        this.stockProducto = stockProducto;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public List<Laboratory> getLabs() {
        return labs;
    }

    public void setLabs(List<Laboratory> labs) {
        this.labs = labs;
    }

    public List<IngresoProducto> getIngresoProductos() {
        return ingresoProductos;
    }

    public void setIngresoProductos(List<IngresoProducto> ingresoProductos) {
        this.ingresoProductos = ingresoProductos;
    }

    public IngresoProducto getIngresoProducto() {
        return ingresoProducto;
    }

    public void setIngresoProducto(IngresoProducto ingresoProducto) {
        this.ingresoProducto = ingresoProducto;
    }

}
