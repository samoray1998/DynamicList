package com.example.dynamiclist;

public class Client {
    private int IDc;
    private String Name;
    private int Age;

    public Client( String name, int age) {

        Name = name;
        Age = age;
    }

    public Client() {
    }

    public int getIDc() {
        return IDc;
    }

    public void setIDc(int IDc) {
        this.IDc = IDc;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    @Override
    public String toString() {
        return "Client{" +
                "IDc=" + IDc +
                ", Name='" + Name + '\'' +
                ", Age=" + Age +
                '}';
    }
}
