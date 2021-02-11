package com.utn.PhoneLines.projection;

import java.time.LocalDateTime;

public interface InvoiceUserAndDate {
//dice amount en minusculas porque asi puse en el store procedure
    String getNumber();
    Integer getCallsamount();
    double getTotalprice();
    boolean getPaid();
    LocalDateTime getDuedate();
    LocalDateTime getDateinvoice();



}
