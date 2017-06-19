package com.hrevfdz.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class PharmacyService<T> implements IPharmacy<T> {

    protected EntityManager em;
    Class<T> obj;

    private String cod;

    public PharmacyService(Class<T> obj) {
        em = PharmacyConexion.getInstance().getFactory().createEntityManager();
        this.obj = obj;
    }

    @Override
    public boolean Create(T t) throws Exception {
        try {
            em = PharmacyConexion.getInstance().getFactory().createEntityManager();
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean Update(T t) throws Exception {
        try {
            em = PharmacyConexion.getInstance().getFactory().createEntityManager();
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean Delete(T t) throws Exception {
        try {
            em = PharmacyConexion.getInstance().getFactory().createEntityManager();
            em.getTransaction().begin();
            em.remove(em.merge(t));
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<T> findAll() throws Exception {
        List<T> lista = null;
        try {
            em = PharmacyConexion.getInstance().getFactory().createEntityManager();
            em.getTransaction().begin();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<T> root = cq.from(obj);
            cq.select(cq.from(obj));
            cq.orderBy(em.getCriteriaBuilder().desc(root.get(getCod())));
            lista = em.createQuery(cq).getResultList();
            em.close();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    @Override
    public T findBy(String q) throws Exception {
        T t = null;

        try {
            em = PharmacyConexion.getInstance().getFactory().createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery(q);
            t = (T) query.getSingleResult();
            em.close();
        } catch (Exception e) {
            throw e;
        }

        return t;
    }

    @Override
    public List<T> findByQuery(String q) throws Exception {
        List<T> lista = null;

        try {
            em = PharmacyConexion.getInstance().getFactory().createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery(q);
            lista = query.getResultList();
            em.close();
        } catch (Exception e) {
            System.out.println("ERROR HIBERNATE: " + e.getMessage());
            throw e;
        }

        return lista;
    }

    @Override
    public List<T> findByNativeQuery(String nq) throws Exception {
        List<T> lista = null;

        try {
            em = PharmacyConexion.getInstance().getFactory().createEntityManager();
            em.getTransaction().begin();
            Query query = em.createNativeQuery(nq);
            lista = query.getResultList();
            em.close();
        } catch (Exception e) {
            System.out.println("ERROR HIBERNATE: " + e.getMessage());
            throw e;
        }

        return lista;
    }

    @Override
    public List<T> findByCriteriaQuery() {
        List<T> lista = null;
        try {
            em = PharmacyConexion.getInstance().getFactory().createEntityManager();
            em.getTransaction().begin();
            CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(obj);
            Root<T> root = cq.from(obj);
            lista = em.createQuery(cq).getResultList();
            em.close();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

}
