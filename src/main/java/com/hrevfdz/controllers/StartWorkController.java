package com.hrevfdz.controllers;

import com.hrevfdz.dao.StartWorkDAO;
import com.hrevfdz.dao.UsersDAO;
import com.hrevfdz.models.StartWork;
import com.hrevfdz.models.Users;
import com.hrevfdz.services.IPharmacy;
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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class StartWorkController implements Serializable {

    private List<StartWork> startWorks;
    private StartWork startWork;
    private List<Users> usuarios;

    @PostConstruct
    public void init() {
        try {
            if (startWork == null) {
                startWork = new StartWork();
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fec = new Date();
            startWork.setFecha(sdf.parse(sdf.format(fec)));
            
            doFindAll();
        } catch (ParseException ex) {
            Logger.getLogger(StartWorkController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doFindAll() {
        doFindAllWorks();
        doFindAllUsers();
    }

    public void doFindAllWorks() {
        FacesMessage msg = null;
        IPharmacy<StartWork> dao = new StartWorkDAO();

        try {
            startWorks = dao.findByQuery("SELECT w FROM StartWork w");
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, MessagesUtil.ERROR_TITLE, e.getMessage());
        }

        if (msg != null) {
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void doFindAllUsers() {
        FacesMessage msg = null;
        IPharmacy<Users> dao = new UsersDAO();

        try {
            usuarios = dao.findByQuery("SELECT u FROM Users u");
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, MessagesUtil.ERROR_TITLE, e.getMessage());
        }

        if (msg != null) {
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void doFindBy() {
        FacesMessage msg = null;
        IPharmacy<StartWork> dao = new StartWorkDAO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            String query = "";
            if (!sdf.format(startWork.getFecha()).equals("") && !startWork.getUserId().getUsername().equals("")) {
                query = "SELECT w FROM StartWork w WHERE w.fecha = '" + sdf.format(startWork.getFecha()) + "' AND w.userId.id = " + startWork.getUserId().getId();
            } else if (!sdf.format(startWork.getFecha()).equals("") && startWork.getUserId().getUsername().equals("")) {
                query = "SELECT w FROM StartWork w WHERE w.fecha = '" + sdf.format(startWork.getFecha()) + "'";
            } else if (sdf.format(startWork.getFecha()).equals("") && !startWork.getUserId().getUsername().equals("")) {
                query = "SELECT w FROM StartWork w WHERE w.userId.id = " + startWork.getUserId().getId();
            }

            startWorks = dao.findByQuery(query);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, MessagesUtil.ERROR_TITLE, e.getMessage());
        }

        if (msg != null) {
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public List<StartWork> getStartWorks() {
        return startWorks;
    }

    public void setStartWorks(List<StartWork> startWorks) {
        this.startWorks = startWorks;
    }

    public StartWork getStartWork() {
        return startWork;
    }

    public void setStartWork(StartWork startWork) {
        this.startWork = startWork;
    }

    public List<Users> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Users> usuarios) {
        this.usuarios = usuarios;
    }

}
