package com.samir.has.api.object;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.security.SecureRandom;
import java.util.UUID;

public class LocalUniqueId {

    private final UUID uuid = UUID.randomUUID();
    @JsonProperty("uid") private String uid;

    public static LocalUniqueId randomUniqueId() {
        return new LocalUniqueId();
    }

    public LocalUniqueId(String id){
        uid = id;
    }

    private LocalUniqueId(){
        extractUId();
    }

    private String extractUId(){
        uid = uuid.toString();
        String[] divided = uid.split("-");
        return uid = divided[0];
    }

    public boolean equals(LocalUniqueId luid){
        return this.uid.equals(luid.uid);
    }

    @Override
    public String toString(){
        return uid;
    }



    private static final String AB = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static SecureRandom rnd = new SecureRandom();

    public static String randomString(){
        int len = 8;
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

}
