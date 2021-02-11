package com.utn.PhoneLines.projection;

import java.time.LocalDateTime;


public interface CallsClient {


    String getNumberorigin();
    String getNumberdestination();
    String getCitydestination();
    LocalDateTime getDatecall();
    Integer getDuration();
    double getTotalprice();
    String getCityDestination();
    String getCityorigin();


}
