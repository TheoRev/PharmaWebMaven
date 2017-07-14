package com.hrevfdz.controllers;

import com.hrevfdz.dao.AccessDAO;
import com.hrevfdz.dao.LaboratoryDAO;
import com.hrevfdz.dao.PaymentsDAO;
import com.hrevfdz.dao.SaleDAO;
import com.hrevfdz.dao.StartWorkDAO;
import com.hrevfdz.dao.StockProductoDAO;
import com.hrevfdz.models.Access;
import com.hrevfdz.models.Laboratory;
import com.hrevfdz.models.Payments;
import com.hrevfdz.models.StartWork;
import com.hrevfdz.models.StockProducto;
import com.hrevfdz.services.IPharmacy;
import com.hrevfdz.util.AccionUtil;
import com.hrevfdz.util.MessagesUtil;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class PaymentsController implements Serializable {

    private List<Payments> paymentses;
    private Payments payments;
    private Access access;
    private List<StockProducto> productos;
    private StockProducto producto;
    private StartWork startWork;
    private List<Laboratory> laboratorys;
    private Laboratory laboratory;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");

    private String accion;
    private Date fecha = new Date();
    private String fecAct = sdf2.format(fecha);

    @PostConstruct
    public void init() {
        payments = new Payments();
        access = new Access();
        doFindAll();
        doGetLaboratories();
        doGetUserActive();
        try {
            fecha = sdf2.parse(fecAct);
        } catch (ParseException ex) {
            Logger.getLogger(PaymentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doCreate() {
        FacesMessage msg = null;
        IPharmacy<Payments> dao = new PaymentsDAO();

        try {
            boolean result = dao.Create(payments);

            if (result) {
                paymentses.add(paymentses.size(), payments);
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

    public void doUpdate(Payments p) {
        FacesMessage msg = null;
        IPharmacy<Payments> dao = new PaymentsDAO();

        try {
            boolean result = dao.Update(p);

            if (result) {
                paymentses.clear();
                doFindAll();
                payments = new Payments();
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.SUCCESS_TITLE, MessagesUtil.UPDATE_SUCCESS);
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.FAIL_TITLE, MessagesUtil.UPDATE_FAIL);
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.ERROR_TITLE, e.getMessage());
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void doDelete(Payments p) {
        FacesMessage msg = null;
        IPharmacy<Payments> dao = new PaymentsDAO();

        try {
            boolean result = dao.Delete(p);

            if (result) {
                paymentses.clear();
                doFindAll();
                payments = new Payments();
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.SUCCESS_TITLE, MessagesUtil.DELETE_SUCCESS);
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.FAIL_TITLE, MessagesUtil.DELETE_FAIL);
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.ERROR_TITLE, e.getMessage());
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void doGetLaboratories() {
        FacesMessage msg = null;
        IPharmacy<Laboratory> dao = new LaboratoryDAO();

        try {
            final String query = "SELECT l FROM Laboratory l ORDER BY l.nomLab";
            laboratorys = dao.findByQuery(query);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.ERROR_TITLE, e.getMessage());
        }

        if (msg != null) {
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void doGetUserActive() {
        FacesMessage msg = null;
        IPharmacy<Access> dao = new AccessDAO();

        try {
            final String query = "SELECT a FROM Access a WHERE a.id = (SELECT MAX(t.id) FROM Access t)";
            access = dao.findBy(query);
            this.payments.setUserId(access.getUserId());
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.ERROR_TITLE, e.getMessage());
        }

        if (msg != null) {
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void doGetProductos() {
        FacesMessage msg = null;
        IPharmacy<StockProducto> dao = new StockProductoDAO();

        try {
            final String query = "SELECT p FROM StockProducto p WHERE p.cantidad > 0 AND p.codLab.codLab = " + laboratory.getCodLab() + " ORDER BY p.nombre";
            productos = dao.findByQuery(query);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.ERROR_TITLE, e.getMessage());
        }

        if (msg != null) {
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void doGetCaja(Payments p) {
        FacesMessage msg = null;
        IPharmacy dao = null;
        sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            double totalSales;
            double totalPays;
            String q1;
            String q2;
            String q3;

            if (p == null) {
                q1 = "SELECT sw FROM StartWork sw WHERE sw.fecha = '" + sdf.format(new Date()) + "'";
                q2 = "SELECT SUM(s.subtotal) FROM Sale s WHERE s.fecha = '" + sdf.format(new Date()) + "'";
                q3 = "SELECT SUM(p.monto) FROM Payments p WHERE p.fecha = '" + sdf.format(new Date()) + "'";
            } else {
                q1 = "SELECT sw FROM StartWork sw WHERE sw.fecha = '" + sdf.format(p.getFecha()) + "'";
                q2 = "SELECT SUM(s.subtotal) FROM Sale s WHERE s.fecha = '" + sdf.format(p.getFecha()) + "'";
                q3 = "SELECT SUM(p.monto) FROM Payments p WHERE p.fecha = '" + sdf.format(p.getFecha()) + "'";
            }

            dao = (dao == null) ? new SaleDAO() : dao;
            totalSales = dao.findBy(q2) != null ? (double) dao.findBy(q2) : 0;
            dao = new PaymentsDAO();
            totalPays = dao.findBy(q3) != null ? (double) dao.findBy(q3) : 0;

            dao = new StartWorkDAO();
            startWork = (StartWork) dao.findBy(q1);
            double montoAct = (startWork.getCapital() + totalSales) - totalPays;
            startWork.setCapital(montoAct);
//            payments.setIdSw(startWork);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.ERROR_TITLE, e.getMessage());
        }

        if (msg != null) {
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void doNew() {
        accion = AccionUtil.CREATE;
        payments = new Payments();
        doGetCaja(null);
        doGetLaboratories();
        doGetUserActive();
        doFindAll();
        try {
            payments.setFecha(sdf2.parse(fecAct));
        } catch (ParseException ex) {
            Logger.getLogger(PaymentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doUpgrade(Payments p) {
        accion = AccionUtil.UPDATE;
        payments = p;
        doGetLaboratories();
        doGetUserActive();
        doGetCaja(p);
        doFindAll();
    }

    public void doExecute() {
        switch (accion) {
            case AccionUtil.CREATE:
                doCreate();
                break;
            case AccionUtil.UPDATE:
                doUpdate(payments);
                break;
        }
    }

    public void doFindAll() {
        FacesMessage msg = null;
        IPharmacy<Payments> dao = new PaymentsDAO();

        try {
            final String query = "SELECT p FROM Payments p";
            paymentses = dao.findByQuery(query);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.ERROR_TITLE, MessagesUtil.ERROR + ": " + e.getMessage());
        }

        if (msg != null) {
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void doFindByFecha() {
        FacesMessage msg = null;
        IPharmacy<Payments> dao = new PaymentsDAO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            final String query = "SELECT p FROM Payments p WHERE p.fecha = '" + sdf.format(fecha) + "'";
            paymentses = dao.findByQuery(query);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.ERROR_TITLE, MessagesUtil.ERROR + ": " + e.getMessage());
        }

        if (msg != null) {
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public List<Payments> getPaymentses() {
        return paymentses;
    }

    public void setPaymentses(List<Payments> paymentses) {
        this.paymentses = paymentses;
    }

    public Payments getPayments() {
        return payments;
    }

    public void setPayments(Payments payments) {
        this.payments = payments;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public List<StockProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<StockProducto> productos) {
        this.productos = productos;
    }

    public StockProducto getProducto() {
        return producto;
    }

    public void setProducto(StockProducto producto) {
        this.producto = producto;
    }

    public StartWork getStartWork() {
        return startWork;
    }

    public void setStartWork(StartWork startWork) {
        this.startWork = startWork;
    }

    public List<Laboratory> getLaboratorys() {
        return laboratorys;
    }

    public void setLaboratorys(List<Laboratory> laboratorys) {
        this.laboratorys = laboratorys;
    }

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFecAct() {
        return fecAct;
    }

    public void setFecAct(String fecAct) {
        this.fecAct = fecAct;
    }

}
