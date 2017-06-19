package com.hrevfdz.converters;

import com.hrevfdz.models.StockProducto;
import com.hrevfdz.models.Suppliers;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import org.omnifaces.converter.SelectItemsConverter;

@FacesConverter("supplierConverter")
public class SuppliersConverter extends SelectItemsConverter {

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Integer codigo = (value instanceof Suppliers) ? ((Suppliers) value).getCodigo() : null;
        return (codigo != null) ? String.valueOf(codigo) : null;
    }

}
