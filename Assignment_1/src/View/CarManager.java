package View;

import Controller.*;
import Model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CarManager {

    public static void main(String[] args) {
        BrandList bl = new BrandList();
        CarList cl = new CarList(bl);
        Validation vl = new Validation();
        
        // enter brands and cars file`
        boolean out = true;
        while (out) {
            //String bFile = vl.inputString("Enter brand file: ");
            String bFile = "brands.txt";
            if (!bl.loadFromFile(bFile)) {
                
                System.out.println("Enter again!");
                continue;
            }
            System.out.println("Load brand file success!");
            //String cFile = vl.inputString("Enter car file: ");
            String cFile = "cars.txt";
            if (!cl.loadFromFile(cFile)) {
                System.out.println("Enter again!");
                continue;
            }
            out = false;
            System.out.println("Load car file success!");
        }

        ArrayList<String> creatMenu = new ArrayList<>();
        creatMenu.add("----------------MENU----------------");
        creatMenu.add("1. List all brands");
        creatMenu.add("2. Add a new brand");
        creatMenu.add("3. Search a brand based on its ID");
        creatMenu.add("4. Update brand details");
        creatMenu.add("5. Save brands to the file, named brands.txt");
        creatMenu.add("6. List all cars in ascending order of brand names");
        creatMenu.add("7. List cars based on a part of an input brand name");
        creatMenu.add("8. Add a car");
        creatMenu.add("9. Remove a car based on its ID");
        creatMenu.add("10. Update a car based on its ID");
        creatMenu.add("11. Save cars to file, named cars.txt");
        creatMenu.add("12. Exit");
        Menu menu = new Menu();
        while(true){
            int choice = menu.int_getChoice(creatMenu);
            switch(choice){
                case 1: 
                    bl.listBrand();
                    break;
                case 2:
                    bl.addBrand();
                    break;
                case 3: 
                    String Id = vl.inputString("Enter the ID to search: ").trim();
                    int pos = bl.searchID(Id);
                    if(pos > 0){
                        System.out.println(bl.get(pos).toString()); 
                    }
                    else
                        System.out.println("Not found!");
                    break;
                case 4:
                    bl.updateBrand();
                    break;
                case 5:
                    if(bl.saveToFile("brands.txt"))     
                        System.out.println("Save to file successful!");
                    break;
                case 6:
                    cl.listCars();
                    break;
                case 7:
                    cl.printBasedBrandName();
                    break;
                case 8:
                    cl.addCar();
                    break;
                case 9:
                    cl.removeCar();
                    break;
                case 10:
                    if(cl.updateCar())
                        System.out.println("Update car success!");
                    else
                        System.out.println("Not found!");
                    break;
                case 11:
                    if(cl.saveToFile("cars.txt"))
                        System.out.println("Cars saved successful!");
                    break;
                case 12:
                    System.out.println("Close program!");
                    return;
                default:
                    System.out.println("Invalid choice!!");
                    break;
            }
        }
    }

}
class Menu {

    public int int_getChoice(ArrayList<String> list) {
        int numItems = list.size();
        for (String item : list) {
            System.out.println(item);
        }
        
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.print("Enter your choice (1-" + (numItems - 1) + "): ");
            String input = scanner.nextLine().trim();

            try {
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= numItems) {
                    break;
                }
            } catch (NumberFormatException e) {
            }

            System.out.println("Invalid choice. Please try again.");
        }

        return choice;
    }

}