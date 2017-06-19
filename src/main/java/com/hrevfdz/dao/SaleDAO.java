package com.hrevfdz.dao;

import com.hrevfdz.models.Sale;
import com.hrevfdz.services.PharmacyConexion;
import java.util.List;

import com.hrevfdz.services.PharmacyService;
import java.util.Date;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SaleDAO extends PharmacyService<Sale> {

    public SaleDAO() {
        super(Sale.class);
    }

    @Override
    public boolean Create(Sale t) throws Exception {
        return super.Create(t);
    }

    @Override
    public boolean Update(Sale t) throws Exception {
        return super.Update(t);
    }

    @Override
    public boolean Delete(Sale t) throws Exception {
        return super.Delete(t);
    }

    @Override
    public List<Sale> findAll() throws Exception {
        super.setCod("codSale");
        return super.findAll();
    }

//    @Override
//    public Sale findBy(String q) throws Exception {
//        return super.findBy(q);
//    }

    @Override
    public List<Sale> findByQuery(String q) throws Exception {
        return super.findByQuery(q);
    }

    @Override
    public List<Sale> findByNativeQuery(String nq) throws Exception {
        return super.findByNativeQuery(nq);
    }

    public List<Sale> findByDate(Date fecha) {
        List<Sale> lista = null;
        try {
            if (fecha != null) {
                em = PharmacyConexion.getInstance().getFactory().createEntityManager();
                em.getTransaction().begin();
                CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
                Root<Sale> root = cq.from(Sale.class);
                cq.select(cq.from(Sale.class));
                Predicate p = em.getCriteriaBuilder().equal(root.get("fecha"), fecha);
                cq.where(p);
                cq.orderBy(em.getCriteriaBuilder().desc(root.get(getCod())));
                lista = em.createQuery(cq).getResultList();
                em.close();
            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

}
