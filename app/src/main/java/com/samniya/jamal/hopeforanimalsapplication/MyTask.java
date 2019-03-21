package com.samniya.jamal.hopeforanimalsapplication;

public class MyTask {
    public int getImportant;
    public int getNecessary;
    public String Name;
    public String Price;
    public String Kind;

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setKind(String kind) {
        Kind = kind;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getName(String name) {

        return Name;
    }

    public String getPrice(String price) {
        return Price;
    }

    public String getKind(String kind) {
        return Kind;
    }

    public String getAge(String age) {
        return Age;
    }

    public MyTask(String name, String price, String kind, String age) {
        Name = name;
        Price = price;
        Kind = kind;
        Age = age;

    }

    public String Age;

    public int getText() {
        return 0;
    }

    public int getTitle() {
        return 0;
    }
}
