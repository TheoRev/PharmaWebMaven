package com.hrevfdz.controllers;

import com.hrevfdz.dao.AccessDAO;
import com.hrevfdz.dao.StartWorkDAO;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.hrevfdz.dao.UsersDAO;
import com.hrevfdz.models.Access;
import com.hrevfdz.models.StartWork;
import com.hrevfdz.models.Users;
import com.hrevfdz.services.IPharmacy;
import com.hrevfdz.util.MessagesUtil;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ManagedBean
@SessionScoped
public class UsersController implements Serializable {
    
    private List<Users> usuarios;
    private Users usuario;
    
    private StartWork startWork;
    
    @PostConstruct
    public void init() {
        usuario = new Users();
        if (getStartWork() == null) {
            setStartWork(new StartWork());
        }
//        showWellcomeMsge();
    }
    
    public String logear() {
        FacesMessage message = null;
        String ruta = null;
        final String query = "select u from Users u where u.username='" + usuario.getUsername()
                + "' and u.password='" + usuario.getPassword() + "'";
        IPharmacy<Users> dao = new UsersDAO();
        
        try {
            Users u = dao.findBy(query);
            if (u != null) {
                if (u.getUsername().equals(usuario.getUsername())
                        && u.getPassword().equals(usuario.getPassword())) {
                    
                    if (findStartWork()) {
                        ruta = "views/sale?faces-redirect=true";
                    } else {
                        ruta = "views/start_work?faces-redirect=true";
                    }
                    
                    createAccess(u);
                    
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", usuario);
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ACCESO CONCEDIDO",
                            "Bienvenido" + usuario.getUsername().toUpperCase());
                } else {
                    message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR EN LA VALIDACION",
                            "Usuario o Password incorrectos");
                    ruta = "index";
                }
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR EN LA VALIDACION",
                        "Usuario o Password incorrectos");
                ruta = "index";
            }
        } catch (Exception ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR EN LA VALIDACION", "Usuario o Password incorrectos");
            ruta = "index";
        }
        
        FacesContext.getCurrentInstance().addMessage(null, message);
        return ruta;
    }
    
    private void createAccess(Users u) throws ParseException, Exception {
        IPharmacy<Access> daoAcc = new AccessDAO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fec = new Date();
        Access access = new Access();
        access.setFecha(sdf.parse(sdf.format(fec)));
        sdf = new SimpleDateFormat("HH:mm:ss");
        access.setHora(sdf.parse(sdf.format(fec)));
        access.setUserId(u);
        
        daoAcc.Create(access);
    }
    
    private boolean findStartWork() {
        IPharmacy<StartWork> dao = new StartWorkDAO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaAct = new Date();
        boolean result = false;
        
        try {
            String query = "SELECT o FROM StartWork o WHERE o.fecha = '" + sdf.format(fechaAct) + "' AND o.capital > 0";
            StartWork ow = dao.findBy(query);
            if (ow != null) {
                result = true;
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
    
    public String doCreate(Users u) {
        FacesMessage msg = null;
        IPharmacy<StartWork> dao = new StartWorkDAO();
        String ruta = "";
        
        try {
            
            IPharmacy<Users> daoUs = new UsersDAO();
            String query = "SELECT u FROM Users u WHERE u.username = '" + u.getUsername() + "' AND u.password = '" + u.getPassword() + "'";
            Users us = daoUs.findBy(query);
            
            getStartWork().setUserId(us);
            boolean result = dao.Create(startWork);
            
            if (result) {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessagesUtil.SUCCESS_TITLE, MessagesUtil.SAVE_SUCCESS);
                ruta = "sale?faces-redirect=true";
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, MessagesUtil.FAIL_TITLE, MessagesUtil.SAVE_FAIL);
                ruta = "start_work?faces-redirect=true";
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, MessagesUtil.ERROR_TITLE,
                    e.getMessage());
        }
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return ruta;
    }
    
    private void showWellcomeMsge() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "ACCESO CONCEDIDO", "Bienvenido " + getUsuario().getUsername().toUpperCase());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    public List<Users> getUsuarios() {
        return usuarios;
    }
    
    public void setUsuarios(List<Users> usuarios) {
        this.usuarios = usuarios;
    }
    
    public Users getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Users usuario) {
        this.usuario = usuario;
    }
    
    public StartWork getStartWork() {
        return startWork;
    }
    
    public void setStartWork(StartWork startWork) {
        this.startWork = startWork;
    }
    
}
