package com.hrevfdz.dao;

import com.hrevfdz.models.Access;
import com.hrevfdz.services.PharmacyService;
import java.util.List;

public class AccessDAO extends PharmacyService<Access> {

    public AccessDAO() {
        super(Access.class);
    }

    @Override
    public List<Access> findByCriteriaQuery() {
        return super.findByCriteriaQuery(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Access> findByNativeQuery(String nq) throws Exception {
        return super.findByNativeQuery(nq); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Access> findByQuery(String q) throws Exception {
        return super.findByQuery(q); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Access findBy(String q) throws Exception {
        return super.findBy(q); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Access> findAll() throws Exception {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Delete(Access t) throws Exception {
        return super.Delete(t); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Update(Access t) throws Exception {
        return super.Update(t); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Create(Access t) throws Exception {
        return super.Create(t); //To change body of generated methods, choose Tools | Templates.
    }

}
