package com.utn.PhoneLines.exceptions;

public class PhoneNotExistsException  extends Throwable{

    public PhoneNotExistsException(String not_exist) {
        super("message");
    }

    public PhoneNotExistsException() {

    }
}
