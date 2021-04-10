package com.posjava.redesocial.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Util {

    public static ResponseEntity getResposeError(String message) {
        return new ResponseEntity<>(null, setHeaderMessage(message), HttpStatus.BAD_REQUEST);
    }

    private static HttpHeaders setHeaderMessage(String message) {
        HttpHeaders header = new HttpHeaders();
        header.add("message-error", message);
        return header;
    }

}
