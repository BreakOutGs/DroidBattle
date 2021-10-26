package com.company;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // варіант 4
    static Scanner keyboard = new Scanner(System.in);

    public static void main1(String[] args) {
        int number = 0;
    Lr1 numberOfLuke = new  Lr1(2,1);
    int count = 0;
    String s="";
    System.out.printf("Введіть кількість чисел Люка: ");
    number = keyboard.nextInt();
    numberOfLuke.generateNumber(number);
    numberOfLuke.printCubeValues();
    }
    public static float func(float x, float y){
        return x*2+y*3;
    }
    public static void main2(String[] args) {
       String[] models = {"Tesla Cybertrack","Model S","Model X","Model 3","Model Y","Model S"};
       float[] prices = {50000,45000,60000,35000,23000, 43000};
       int[] years = {2019,2016,2016,2013,2019,2014};
       String[] regNumbers = {"PU142521BG", "HN121454YC", "PT1432NH","He5295ll0","Cb2077CO","N3636jd"};

       String modelFilter = models[1];
       int currentYear = 2021;
       int yearsFilter = 6;
       int YearFilter = 2016;
       int costFilter = 50000;

       int n = 6;
        List<Car_Lr2> carList = new LinkedList<Car_Lr2>();
            for(int i=0;i<n;i++){
                carList.add(new Car_Lr2(i+1,models[i],prices[i],years[i],regNumbers[i]));
                System.out.print(carList.get(i).getInfoInString());
            }
        System.out.print("Список машин з моделлю "+modelFilter+" \n");

        for (Car_Lr2 car:carList) {
            if(car.getModel() == modelFilter){
                System.out.print(car.getInfoInString());
            }
        }
        System.out.print("Список машин з моделлю "+modelFilter+ " і використання більше "+ yearsFilter+ " років: \n");
        for (Car_Lr2 car:carList) {
            car.setId(222);
            if(car.getModel() == modelFilter && (currentYear-car.getYear())>yearsFilter){
                System.out.print(car);
            }
        }
        System.out.print("Автомобілі "+ YearFilter + " року і ціна більше "+ costFilter +"\n");
        for (Car_Lr2 car:carList) {
            if(car.getYear()==YearFilter && car.getPrice()>costFilter){
                System.out.print(car);
            }
        }

        float x1 = 0F;
        float x2 = 4.5F;
        float y1 = 0F;
        float y2 = 10.3F;
        float e = 0.1f;
        float max = func(x1,y1);
        while(x1<x2){
            while (y1<y2){
                if(max<func(x1,y1)) max = func(x1,y1);
                y1+=e;
            }
            x1+=e;
        }

    }
    public static void main3(String[] args) {
        
    }
}
