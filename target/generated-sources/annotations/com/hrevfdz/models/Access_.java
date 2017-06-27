package com.hrevfdz.models;

import com.hrevfdz.models.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-27T00:38:10")
@StaticMetamodel(Access.class)
public class Access_ { 

    public static volatile SingularAttribute<Access, Date> fecha;
    public static volatile SingularAttribute<Access, Date> hora;
    public static volatile SingularAttribute<Access, Integer> id;
    public static volatile SingularAttribute<Access, Users> userId;

}