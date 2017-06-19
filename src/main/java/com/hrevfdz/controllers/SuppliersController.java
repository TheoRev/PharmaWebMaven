package com.hrevfdz.controllers;

import com.hrevfdz.dao.SuppliersDAO;
import com.hrevfdz.models.Suppliers;
import com.hrevfdz.services.IPharmacy;
import com.hrevfdz.util.AccionUtil;
import com.hrevfdz.util.MessagesUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class SuppliersController implements Serializable {

    private List<Suppliers> supplierses;
    private Suppliers suppliers;
    private String accion;

    @PostConstruct
    public void init() {
        suppliers = new Suppliers();
        doFindAll();
    }

    public void doFindAll() {
        FacesMessage msg = null;
        IPharmacy dao = new SuppliersDAO();

        try {
            final String query = "SELECT s FROM Suppliers s ORDER BY s.nombre";
            supplierses = dao.findByQuery(query);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.ERROR_TITLE, MessagesUtil.ERROR);
        }

        if (msg != null) {
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void doCreate() {
        FacesMessage msg = null;
        IPharmacy dao = new SuppliersDAO();

        try {
            boolean result = dao.Create(suppliers);

            if (result) {
                supplierses.add(supplierses.size(), suppliers);
                doFindAll();
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.SUCCESS_TITLE, MessagesUtil.SAVE_SUCCESS);
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.FAIL_TITLE, MessagesUtil.SAVE_FAIL);
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.ERROR_TITLE, e.getMessage());
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void doUpdate(Suppliers s) {
        FacesMessage msg = null;
        IPharmacy dao = new SuppliersDAO();

        try {
            boolean result = dao.Update(s);

            if (result) {
                supplierses.clear();
                doFindAll();
                suppliers = new Suppliers();
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.SUCCESS_TITLE, MessagesUtil.UPDATE_SUCCESS);
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.FAIL_TITLE, MessagesUtil.UPDATE_FAIL);
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.ERROR_TITLE, e.getMessage());
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void doDelete(Suppliers s) {
        FacesMessage msg = null;
        IPharmacy dao = new SuppliersDAO();

        try {
            boolean result = dao.Delete(s);

            if (result) {
                supplierses.clear();
                doFindAll();
                suppliers = new Suppliers();
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.SUCCESS_TITLE, MessagesUtil.DELETE_SUCCESS);
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.FAIL_TITLE, MessagesUtil.DELETE_FAIL);
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.ERROR_TITLE, e.getMessage());
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void doNew() {
        accion = AccionUtil.CREATE;
        suppliers = new Suppliers();
        doFindAll();
    }

    public void doUpgrade(Suppliers s) {
        accion = AccionUtil.UPDATE;
        suppliers = s;
        doFindAll();
    }

    public void doExecute() {
        switch (accion) {
            case AccionUtil.CREATE:
                doCreate();
                break;
            case AccionUtil.NUEVA:
                doCreate();
                break;
            case AccionUtil.UPDATE:
                doUpdate(suppliers);
                break;
        }
    }

    public List<Suppliers> getSupplierses() {
        return supplierses;
    }

    public void setSupplierses(List<Suppliers> supplierses) {
        this.supplierses = supplierses;
    }

    public Suppliers getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Suppliers suppliers) {
        this.suppliers = suppliers;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

}
