package com.apothecary.irreducible.apothecary.models;

/**
 * Created by amore on 11/8/15.
 */
public class Medicine {
    private String name;
    private String barcode;
    private int count;
    private int expiration;

    public Medicine(String name, int count, int expiration) {
        this.name = name;
        this.count = count;
        this.expiration = expiration;

    }

    public String getName() {
        return name;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getCount() {
        return count;
    }

    public int getExpiration() {
        return expiration;
    }

}
