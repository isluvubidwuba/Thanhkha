/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Brand;
import Model.Car;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Acer
 */
public class Validation {
   private final static Scanner sc = new Scanner(System.in);
   
   public String inputString(String msg){
       System.out.print(msg);
       while (true) {           
           String result = sc.nextLine().trim();
           if(result.isEmpty()){
               System.out.println("String cannot empty, Enter again: ");
               continue;
           }
           return result;
       }
   }
   public String inputBrandID(String msg, ArrayList<Brand> bl){
        while (true) {           
           String ID = inputString(msg);
           for(Brand b : bl){
               if(b.getBrandID().equals(ID)){
                   System.out.println("Not duplicated!");
                   continue;
               }
           }
           return ID;
       }
   }
   public String inputCarID(String msg, ArrayList<Car> cl){
       while (true) {           
           String ID = inputString(msg);
           for(Car c : cl){
               if(c.getCarID().equals(ID)){
                   System.out.println("Not duplicated!");
                   continue;
               }
           }
           return ID;
       }
   }
   public double inputDouble(String msg){
       System.out.print(msg);
       while (true) {           
           double result = sc.nextDouble();
           if(result<0){
               System.out.println("Enter again(price > 0): ");
               continue;
           }
           return result;
       }
   }
   public String inputFrameID(String msg, ArrayList<Car> cl){
       while (true) {           
           String result = inputString(msg);
           if(!result.startsWith("F") && result.length() != 5){
               System.out.println("The format Frame ID must be F00000. Pls enter again: ");
               continue;
           }
           for(Car c : cl){
               if(c.getFramID().equals(result)){
                   System.out.println("Not duplicated!");
                   System.out.println("Enter again: ");
                   continue;
               }
           }
           return result;
       }
   }
      public String inputEngineID(String msg, ArrayList<Car> cl){
       while (true) {           
           String result = inputString(msg);
           if(!result.startsWith("E") && result.length() != 5){
               System.out.println("The format Frame ID must be E00000. Pls enter again: ");
               continue;
           }
           for(Car c : cl){
               if(c.getEngineID().equals(result)){
                   System.out.println("Not duplicated!");
                   System.out.println("Enter again: ");
                   continue;
               }
           }
           return result;
       }
   }
}
