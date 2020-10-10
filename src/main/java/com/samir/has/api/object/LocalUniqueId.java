package com.samir.has.api.object;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlValue;
import java.util.Objects;
import java.util.UUID;

public class LocalUniqueId {

    static int count =0;

    private final String defaultUid = UUID.randomUUID().toString();
    private final String customerUid = UUID.randomUUID().toString();
    private final String invoiceUid = UUID.nameUUIDFromBytes("0123456789".getBytes()).toString();

    private final static int __CUSTOMER__ = 1;
    private final static int __INVOICE__ = 2;

    @XmlValue
    @JsonProperty("uid")
    private String uid = null;

    private LocalUniqueId(){
        extractUId(0);
    }

    public LocalUniqueId(String uid){
        this.uid = uid;
    }

    private LocalUniqueId(final int x){
        extractUId(x);
        String suffix;
        switch (x){
            case __CUSTOMER__:  suffix = "CUST/";
                break;
            case __INVOICE__:  suffix = "INV/";
                break;
            default: suffix = "";
        }
        uid = suffix.concat(uid);
    }

    private String  extractUId(final int x){
        switch (x){
            case __CUSTOMER__:  uid = customerUid;
                break;
            case __INVOICE__:  uid = invoiceUid;
                break;
            default: uid = defaultUid;
        }
        String[] divided = uid.split("-");
        return uid = divided[0];
    }

    public static LocalUniqueId randomUniqueId() {
        return new LocalUniqueId();
    }

    public static LocalUniqueId randomCustomerUniqueId() {
        return new LocalUniqueId(LocalUniqueId.__CUSTOMER__);
    }
    public static LocalUniqueId randomInvoiceUniqueId() {
        LocalUniqueId uniqueId = new LocalUniqueId(LocalUniqueId.__INVOICE__);
        return uniqueId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof LocalUniqueId))
            return false;
        return this.uid.equals( ( (LocalUniqueId) obj).uid);
    }

    @Override
    public String toString(){
        return uid;
    }

    /*
    private static final String AB = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static SecureRandom rnd = new SecureRandom();

    public static String randomString(){
        int len = 8;
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }
    */
}
