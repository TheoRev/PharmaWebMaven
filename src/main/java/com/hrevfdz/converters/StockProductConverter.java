package com.hrevfdz.converters;

import com.hrevfdz.models.StockProducto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import org.omnifaces.converter.SelectItemsConverter;

@FacesConverter("stockConverter")
public class StockProductConverter extends SelectItemsConverter {

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Integer codigo = (value instanceof StockProducto) ? ((StockProducto) value).getCodStock() : null;
        return (codigo != null) ? String.valueOf(codigo) : null;
    }

}
