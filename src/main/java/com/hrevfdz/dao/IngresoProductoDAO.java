package com.hrevfdz.dao;

import java.util.List;

import com.hrevfdz.models.IngresoProducto;
import com.hrevfdz.services.PharmacyService;

public class IngresoProductoDAO extends PharmacyService<IngresoProducto> {

    public IngresoProductoDAO() {
        super(IngresoProducto.class);
    }

    @Override
    public boolean Create(IngresoProducto t) throws Exception {
        return super.Create(t);
    }

    @Override
    public boolean Update(IngresoProducto t) throws Exception {
        return super.Update(t);
    }

    @Override
    public boolean Delete(IngresoProducto t) throws Exception {
        return super.Delete(t);
    }

    @Override
    public List<IngresoProducto> findAll() throws Exception {
    	super.setCod("codigo");
        return super.findAll();
    }

    @Override
    public IngresoProducto findBy(String q) throws Exception {
        return super.findBy(q);
    }

    @Override
    public List<IngresoProducto> findByQuery(String q) throws Exception {
        return super.findByQuery(q);
    }

    @Override
    public List<IngresoProducto> findByNativeQuery(String nq) throws Exception {
        return super.findByNativeQuery(nq);
    }

}
