package com.microcreditcard.utils;

import java.util.UUID;

public class GeraUUID {
    public static void main(String[] args) {
        final String uuid = UUID.randomUUID().toString();
        System.out.println("UUID gerado: " + uuid);
    }
}
