package com.jumia.sales.analyser;

import java.util.Date;

public class Sale {
    private String category;
    private String productUid;
    private int nbSold;
    private Date timestamp;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductUid() {
        return productUid;
    }

    public void setProductUid(String productUid) {
        this.productUid = productUid;
    }

    public int getNbSold() {
        return nbSold;
    }

    public void setNbSold(int nbSold) {
        this.nbSold = nbSold;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "category='" + category + '\'' +
                ", productUid='" + productUid + '\'' +
                ", nbSold=" + nbSold +
                ", timestamp=" + timestamp +
                '}';
    }
}
