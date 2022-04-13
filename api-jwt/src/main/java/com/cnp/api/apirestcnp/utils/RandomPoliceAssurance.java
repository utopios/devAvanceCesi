package com.cnp.api.apirestcnp.utils;

import java.util.UUID;

public class RandomPoliceAssurance {

    public static void main(String[] args) {
        System.out.println(generateString());
    }

    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

}
