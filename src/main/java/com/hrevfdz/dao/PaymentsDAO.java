package com.hrevfdz.dao;

import com.hrevfdz.models.Payments;
import com.hrevfdz.services.PharmacyService;
import java.util.List;

public class PaymentsDAO extends PharmacyService<Payments> {

    public PaymentsDAO() {
        super(Payments.class);
    }

    @Override
    public List<Payments> findByCriteriaQuery() {
        return super.findByCriteriaQuery(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Payments> findByNativeQuery(String nq) throws Exception {
        return super.findByNativeQuery(nq); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Payments> findByQuery(String q) throws Exception {
        return super.findByQuery(q); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Payments> findAll() throws Exception {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Delete(Payments t) throws Exception {
        return super.Delete(t); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Update(Payments t) throws Exception {
        return super.Update(t); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Create(Payments t) throws Exception {
        return super.Create(t); //To change body of generated methods, choose Tools | Templates.
    }

}
