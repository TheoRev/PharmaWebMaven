package com.hrevfdz.models;

import com.hrevfdz.models.StockProducto;
import com.hrevfdz.models.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-21T20:25:45")
@StaticMetamodel(Payments.class)
public class Payments_ { 

    public static volatile SingularAttribute<Payments, String> descripcion;
    public static volatile SingularAttribute<Payments, Date> fecha;
    public static volatile SingularAttribute<Payments, Integer> codigo;
    public static volatile SingularAttribute<Payments, Double> monto;
    public static volatile SingularAttribute<Payments, StockProducto> codStock;
    public static volatile SingularAttribute<Payments, Users> userId;

}