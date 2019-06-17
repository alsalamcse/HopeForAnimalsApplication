package com.samniya.jamal.hopeforanimalsapplication;

public class MyAnimal {

    public String name;
    public String kind;
    public String age;
    public String price;
    public String color;
    private String owner;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOwner() {
        return owner;
    }


    public void setOwner(String email) {
        this.owner=owner;
    }

    public void setKey(String kind) {
        this.kind=kind;
    }


    @Override
    public String toString() {
        return "MyAnimal{" +
                "name='" + name + '\'' +
                ", kind='" + kind + '\'' +
                ", age='" + age + '\'' +
                ", Price='" + price + '\'' +
                ", color='" + color + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }


}