package com.cloud.fly.content.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {


    public static void main(String args[]) throws Exception {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        Date date1 = sdf1.parse("20190109");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

        String date2 = sdf2.format(date1);

        System.out.println(date2);

    }

}
