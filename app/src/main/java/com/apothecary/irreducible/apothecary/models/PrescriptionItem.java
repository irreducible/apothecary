package com.apothecary.irreducible.apothecary.models;

import java.io.Serializable;

/**
 * Created by akulka2 on 11/7/15.
 */
public class PrescriptionItem implements Serializable{

    private static final long serialVersionUID = -7588193839330546400L;

    private String name;
    private String quantity;
    private String doses;
    private String expiryDate;

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

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
