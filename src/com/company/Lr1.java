package com.company;

public class Lr1 {
    int firstNumber = 1;
    int secondNumber = 2;
    int[] numbers;

    /**
     * @param x1 Перше число послідновності: x1,x2,x1+x2,x1+x2+x2,...
     * @param x2 Друге число послідновності: x1,x2,x1+x2,x1+x2+x2,...
     */
    public Lr1(int x1,int x2){
        firstNumber = x1;
        secondNumber = x2;
    }

    /**
     * @param number
     * @author Dinchuk Vasyl
     * @version 1.0
     * Метод перевірки числа на наявність кореня з 3
     * @return boolean: Число має корінь з 3
     */
    public boolean isCube(int number){
        return  (Math.cbrt(number) % 1)==0;
    }
    /**
     * @param n кількість чисел Люка
     * Генерує масив int[n] чисел
     * @return
     */
    public void generateNumber(int n){
        numbers  = new int[n];
            numbers[0]=firstNumber;
            numbers[1]=secondNumber;
            for(int i = 2;i<n;i++){
                numbers[i] = numbers[i-1]+numbers[i-2];
            }
    }

    /**
     * Виводить утворену послідовність і кількість кубових значень в ній
     */
    public void printCubeValues(){
        int count = 0;
        String s="";
        for (int i=0;i<numbers.length;i++) {
            if(isCube(numbers[i]))
                count++;
            s+=String.valueOf(numbers[i])+" ";
        }
        System.out.print(s);
        System.out.print("Кількість кубічних чисел: "+ String.valueOf(count));
    }

}
