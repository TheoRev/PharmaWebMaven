package com.hrevfdz.services;

import java.util.List;

public interface IPharmacy<T> {

    public boolean Create(T t) throws Exception;

    public boolean Update(T t) throws Exception;

    public boolean Delete(T t) throws Exception;

    public List<T> findAll() throws Exception;

    public T findBy(String q) throws Exception;

    public List<T> findByQuery(String q) throws Exception;
    
    public List<T> findByNativeQuery(String nq) throws Exception;
    
    public List<T> findByCriteriaQuery();
}
