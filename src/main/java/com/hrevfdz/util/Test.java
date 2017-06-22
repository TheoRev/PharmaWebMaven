package com.hrevfdz.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = new Date();

        String f = "";
        try {
            f = sdf.format(fecha);
        } catch (Exception e) {
        }
        System.out.println("Fecha actual: " + f);
    }

}
