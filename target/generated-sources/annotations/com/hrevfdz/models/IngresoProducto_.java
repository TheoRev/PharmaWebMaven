package com.hrevfdz.models;

import com.hrevfdz.models.StockProducto;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-14T18:20:59")
@StaticMetamodel(IngresoProducto.class)
public class IngresoProducto_ { 

    public static volatile SingularAttribute<IngresoProducto, Date> fecha;
    public static volatile SingularAttribute<IngresoProducto, Integer> codigo;
    public static volatile SingularAttribute<IngresoProducto, Double> costo;
    public static volatile SingularAttribute<IngresoProducto, Date> hora;
    public static volatile SingularAttribute<IngresoProducto, StockProducto> codStock;
    public static volatile SingularAttribute<IngresoProducto, Integer> cantidad;

}