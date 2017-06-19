package com.hrevfdz.controllers;

import com.hrevfdz.dao.LaboratoryDAO;
import com.hrevfdz.dao.SuppliersDAO;
import com.hrevfdz.models.Laboratory;
import com.hrevfdz.models.Suppliers;
import com.hrevfdz.services.IPharmacy;
import com.hrevfdz.util.AccionUtil;
import com.hrevfdz.util.MessagesUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LaboratoryController implements Serializable {

    private List<Laboratory> laboratorios;
    private Laboratory laboratorio;
    private List<Suppliers> supplierses;
    private String accion;

    @PostConstruct
    public void init() {
        laboratorio = new Laboratory();
        doListarLabs();
        doListSuppliers();
    }

    public void doListarLabs() {
        FacesMessage message = null;
        IPharmacy<Laboratory> dao = new LaboratoryDAO();

        try {
            laboratorios = dao.findAll();
        } catch (Exception ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR EN DB", ex.getMessage());
        }

        if (message != null) {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void doCreate() {
        FacesMessage msg = null;
        IPharmacy dao = new LaboratoryDAO();

        try {
            boolean result = dao.Create(laboratorio);

            if (result) {
                laboratorios.add(laboratorios.size(), laboratorio);
                laboratorio = new Laboratory();
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.SUCCESS_TITLE, MessagesUtil.DELETE_SUCCESS);
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, MessagesUtil.ERROR_TITLE, MessagesUtil.ERROR);
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, MessagesUtil.FAIL_TITLE, e.getMessage());
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void doUpdate(Laboratory lab) {
        FacesMessage msg = null;
        IPharmacy dao = new LaboratoryDAO();

        try {
            boolean result = dao.Update(lab);

            if (result) {
                laboratorios.clear();
                doListarLabs();
//                selectedTratamiento = null;
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.SUCCESS_TITLE, MessagesUtil.DELETE_SUCCESS);
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, MessagesUtil.ERROR_TITLE, MessagesUtil.ERROR);
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, MessagesUtil.FAIL_TITLE, e.getMessage());
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void doDelete(Laboratory lab) {
        FacesMessage msg = null;
        IPharmacy dao = new LaboratoryDAO();

        try {
            boolean result = dao.Delete(lab);

            if (result) {
                laboratorios.clear();
                doListarLabs();
//                selectedTratamiento = null;
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.SUCCESS_TITLE, MessagesUtil.DELETE_SUCCESS);
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, MessagesUtil.ERROR_TITLE, MessagesUtil.ERROR);
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, MessagesUtil.FAIL_TITLE, e.getMessage());
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void doListSuppliers() {
        FacesMessage msg = null;
        IPharmacy<Suppliers> dao = new SuppliersDAO();
        try {
            final String query = "SELECT s FROM Suppliers s";
            supplierses = dao.findByQuery(query);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, MessagesUtil.FAIL_TITLE, MessagesUtil.ERROR + ": " + e.getMessage());
        }

        if (msg != null) {
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void doNew() {
        accion = AccionUtil.CREATE;
        laboratorio = new Laboratory();
    }

    public void doUpgrade(Laboratory l) {
        accion = AccionUtil.UPDATE;
        laboratorio = l;
    }

    public void doExecute() {
        switch (accion) {
            case AccionUtil.CREATE:
                doCreate();
                break;
            case AccionUtil.UPDATE:
                doUpdate(laboratorio);
                break;
        }
    }

    public List<Laboratory> getLaboratorios() {
        return laboratorios;
    }

    public void setLaboratorios(List<Laboratory> laboratorios) {
        this.laboratorios = laboratorios;
    }

    public Laboratory getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratory laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public List<Suppliers> getSupplierses() {
        return supplierses;
    }

    public void setSupplierses(List<Suppliers> supplierses) {
        this.supplierses = supplierses;
    }

}
