package com.xin.model;

import java.util.List;

public class Kind {
    //新增的字段
    //private Hobby hobby;
    private List<Product> products;

    private String kindid;

    private String kindname;

    private String description;

    public String getKindid() {
        return kindid;
    }

    public void setKindid(String kindid) {
        this.kindid = kindid == null ? null : kindid.trim();
    }

    public String getKindname() {
        return kindname;
    }

    public void setKindname(String kindname) {
        this.kindname = kindname == null ? null : kindname.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


}