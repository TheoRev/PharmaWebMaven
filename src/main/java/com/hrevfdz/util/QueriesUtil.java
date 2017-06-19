package com.hrevfdz.util;

public class QueriesUtil {

    public static final String STOCK_X_LABORATORY = "SELECT l FROM Laboratory l";
    public static final String STOCK_X_COD = "SELECT s FROM StockProducto s WHERE s.codStock = ";
    public static final String SALE_X_COD = "SELECT s FROM Sale s WHERE s.codSale = ";
    public static final String LABORATORY_X_SUPPLIER = "SELECT l FROM Laboratory l WHERE l.codSupplier = ";
}
