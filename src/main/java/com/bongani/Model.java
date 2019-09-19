package com.bongani;

public class Model {
    private String path;
    private Attribute attr;

    public Model(){

    }

    public Model(String path, Attribute attr){
        this.path = path;
        this.attr = attr;
    }

    public String getPath() {
        return this.path;
    }

    public Attribute getAttr() {
        return this.attr;
    }
}
