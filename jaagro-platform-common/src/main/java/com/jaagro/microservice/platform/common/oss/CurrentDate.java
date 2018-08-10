package com.jaagro.microservice.platform.common.oss;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tobiasy
 */
public class CurrentDate {
    public static String getPackageName() {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sf.format(date);
        return str;
    }

    public static void main(String[] args) {
        System.out.println(getPackageName());
        System.out.println("getWaybillSettlementSettlementViaSettlementId".length());
    }
}
