package me.violantic.mcc.shop.exception;

import java.io.File;

/**
 * Created by Ethan on 5/5/2016.
 */
public class MCCException extends RuntimeException {

    private String message = null;

    public MCCException(File file) {
        message = (file == null) ? "" : "";
    }

    public MCCException(String path) {

    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
