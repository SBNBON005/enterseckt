package com.bongani;

public class Attribute {

    private String createdAt;
    private String name;
    private String type;
    private String size;

    public Attribute(){}

    public Attribute(String createdAt, String name, String type, String size){
        this.createdAt = createdAt;
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getSize(){
        return size;
    }
}
