package com.hrevfdz.converters;

import com.hrevfdz.models.Users;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import org.omnifaces.converter.SelectItemsConverter;

@FacesConverter("usersConverter")
public class UsersConverter extends SelectItemsConverter {

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Integer id = (value instanceof Users) ? ((Users) value).getId() : null;
        return (id != null) ? String.valueOf(id) : null;
    }
}
