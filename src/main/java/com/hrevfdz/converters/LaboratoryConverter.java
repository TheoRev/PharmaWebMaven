package com.hrevfdz.converters;

import com.hrevfdz.models.Laboratory;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import org.omnifaces.converter.SelectItemsConverter;

@FacesConverter("labConverter")
public class LaboratoryConverter extends SelectItemsConverter {

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Integer codigo = (value instanceof Laboratory) ? ((Laboratory) value).getCodLab() : null;
        return (codigo != null) ? String.valueOf(codigo) : null;
    }

}
