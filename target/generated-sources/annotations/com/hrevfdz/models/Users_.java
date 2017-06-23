package com.hrevfdz.models;

import com.hrevfdz.models.Access;
import com.hrevfdz.models.Payments;
import com.hrevfdz.models.Sale;
import com.hrevfdz.models.StartWork;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-22T22:48:57")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> password;
    public static volatile ListAttribute<Users, StartWork> startWorkList;
    public static volatile ListAttribute<Users, Payments> paymentsList;
    public static volatile ListAttribute<Users, Sale> saleList;
    public static volatile ListAttribute<Users, Access> accessList;
    public static volatile SingularAttribute<Users, Integer> id;
    public static volatile SingularAttribute<Users, String> username;

}