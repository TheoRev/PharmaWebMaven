package com.hrevfdz.dao;

import com.hrevfdz.models.Laboratory;
import com.hrevfdz.services.PharmacyService;
import java.util.List;

public class LaboratoryDAO extends PharmacyService<Laboratory>{

    public LaboratoryDAO() {
       super(Laboratory.class);
    }

    @Override
    public List<Laboratory> findByNativeQuery(String nq) throws Exception {
        return super.findByNativeQuery(nq);
    }

    @Override
    public List<Laboratory> findByQuery(String q) throws Exception {
        return super.findByQuery(q);
    }

    @Override
    public Laboratory findBy(String q) throws Exception {
        return super.findBy(q);
    }

    @Override
    public List<Laboratory> findAll() throws Exception {
        super.setCod("codLab");
        return super.findAll();
    }

    @Override
    public boolean Delete(Laboratory t) throws Exception {
        return super.Delete(t);
    }

    @Override
    public boolean Update(Laboratory t) throws Exception {
        return super.Update(t);
    }

    @Override
    public boolean Create(Laboratory t) throws Exception {
        return super.Create(t);
    }
    
}
