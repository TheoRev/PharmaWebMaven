package com.hrevfdz.services;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PharmacyConexion {

    private static PharmacyConexion instance = null;
    private EntityManagerFactory factory = null;

    public static PharmacyConexion getInstance() {
        if (instance == null) {
            instance = new PharmacyConexion();
        }
        return instance;
    }

    public EntityManagerFactory getFactory() {
        try {
            if (factory == null) {
                factory = Persistence.createEntityManagerFactory("PharmaWebPU");
            }
        } catch (Exception e) {
            throw e;
        }
        return factory;
    }
}
