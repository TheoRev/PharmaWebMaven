package com.hrevfdz.models;

import com.hrevfdz.models.StockProducto;
import com.hrevfdz.models.Suppliers;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-20T00:51:12")
@StaticMetamodel(Laboratory.class)
public class Laboratory_ { 

    public static volatile SingularAttribute<Laboratory, String> nomLab;
    public static volatile ListAttribute<Laboratory, StockProducto> stockProductoList;
    public static volatile SingularAttribute<Laboratory, Suppliers> codSupplier;
    public static volatile SingularAttribute<Laboratory, Integer> codLab;

}