package com.utn.PhoneLines.projection;

import java.time.LocalDateTime;

public interface CallClientOffice {

    String getNumberorigin();
    String getCitydestination();
    String getCityorigin();
    String getNumberdestination();
    String getCityDestination();
    Integer getDuration();
    LocalDateTime getDatecall();
    double getTotalprice();
    double getCostprice();
    double getSaleprice();


}
