package com.apothecary.irreducible.apothecary.models;

/**
 * Created by akulka2 on 11/7/15.
 */
public class PrescriptionItem {

    private String name;
    private String quantity;
    private String doses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDoses() {
        return doses;
    }

    public void setDoses(String doses) {
        this.doses = doses;
    }
}
