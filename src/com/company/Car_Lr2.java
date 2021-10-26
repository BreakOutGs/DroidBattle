package com.company;

public class Car_Lr2 {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private int id;
    private String model;
    private int year;
    private float price;
    String registrationNumber;
    Car_Lr2(int Id, String Model, float Price, int Year, String RegistrationNumber){
        this.id = Id;
        this.model = Model;
        this.year = Year;
        this.registrationNumber = RegistrationNumber;
        this.price = Price;
    }

    public float getPrice() {
        return price;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getYear() {
        return year;
    }

    public String getModel() {
        return model;
    }
    public String getInfoInString(){
        String toStr = "";
        toStr+= "Id: "+ ANSI_CYAN+String.valueOf(id)+ANSI_RESET;
        toStr+= " Model: "+ ANSI_PURPLE+String.valueOf(model)+ANSI_RESET;
        toStr+= " Price: "+ ANSI_GREEN+String.valueOf(price)+ANSI_RESET;
        toStr+= " Year: "+ ANSI_YELLOW+String.valueOf(year)+ANSI_RESET;
        toStr+= " Registration number: "+ANSI_BLUE+String.valueOf(registrationNumber)+ANSI_RESET;
        toStr+=" \n";
        return toStr;
    }

    @Override
    public String toString() {
        String toStr = "";
        toStr+= "Id: "+ ANSI_CYAN+String.valueOf(id)+ANSI_RESET;
        toStr+= " Model: "+ ANSI_PURPLE+String.valueOf(model)+ANSI_RESET;
        toStr+= " Price: "+ ANSI_GREEN+String.valueOf(price)+ANSI_RESET;
        toStr+= " Year: "+ ANSI_YELLOW+String.valueOf(year)+ANSI_RESET;
        toStr+= " Registration number: "+ANSI_BLUE+String.valueOf(registrationNumber)+ANSI_RESET;
        toStr+=" \n";
        return toStr;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
