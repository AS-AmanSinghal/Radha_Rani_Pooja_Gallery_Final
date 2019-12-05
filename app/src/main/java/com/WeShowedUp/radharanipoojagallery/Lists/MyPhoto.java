package com.WeShowedUp.radharanipoojagallery.Lists;

public class MyPhoto
{
    private String name,number;

    @Override
    public String toString() {
        return "MyPhoto{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
