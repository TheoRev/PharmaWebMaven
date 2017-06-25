package com.hrevfdz.models;

import com.hrevfdz.models.IngresoProducto;
import com.hrevfdz.models.Laboratory;
import com.hrevfdz.models.Payments;
import com.hrevfdz.models.Sale;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-25T01:18:10")
@StaticMetamodel(StockProducto.class)
public class StockProducto_ { 

    public static volatile SingularAttribute<StockProducto, Double> monto;
    public static volatile ListAttribute<StockProducto, Payments> paymentsList;
    public static volatile SingularAttribute<StockProducto, Double> costo;
    public static volatile SingularAttribute<StockProducto, String> lote;
    public static volatile ListAttribute<StockProducto, Sale> saleList;
    public static volatile SingularAttribute<StockProducto, String> presentacion;
    public static volatile SingularAttribute<StockProducto, Integer> codStock;
    public static volatile SingularAttribute<StockProducto, Integer> cantidad;
    public static volatile ListAttribute<StockProducto, IngresoProducto> ingresoProductoList;
    public static volatile SingularAttribute<StockProducto, String> nombre;
    public static volatile SingularAttribute<StockProducto, Laboratory> codLab;

}