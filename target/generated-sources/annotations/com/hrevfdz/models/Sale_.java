package com.hrevfdz.models;

import com.hrevfdz.models.StockProducto;
import com.hrevfdz.models.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-27T00:38:10")
@StaticMetamodel(Sale.class)
public class Sale_ { 

    public static volatile SingularAttribute<Sale, Date> fecha;
    public static volatile SingularAttribute<Sale, Double> precio;
    public static volatile SingularAttribute<Sale, Integer> codSale;
    public static volatile SingularAttribute<Sale, Double> subtotal;
    public static volatile SingularAttribute<Sale, Date> hora;
    public static volatile SingularAttribute<Sale, StockProducto> codStock;
    public static volatile SingularAttribute<Sale, Integer> cantidad;
    public static volatile SingularAttribute<Sale, Users> userId;

}