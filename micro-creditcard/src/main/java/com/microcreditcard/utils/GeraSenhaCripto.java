package com.microcreditcard.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeraSenhaCripto {
    //Comentar código abaixo depois que gerar senha criptografada do admin!
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("admin"));
    }
}
