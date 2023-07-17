/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Brand;
import Model.Car;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author Acer
 */
public class CarList {

    private ArrayList<Car> cl;
    private BrandList bl;
    private Validation vl;

    //để sử dụng các hàm bên trong BrandList, validation phải khởi chạy nó
    public CarList(BrandList bl) {
        cl = new ArrayList();
        this.bl = bl;
        vl = new Validation();
    }

    public boolean loadFromFile(String file) {
        File f = new File(file);
        if (!f.exists()) {
            return false;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String line = reader.readLine();
            while (line != null) {
                //C01, B7-2018, red, F12345, E12345
                //dùng split để tách và sử lý chuỗi
//                String data[] = line.split(", ");
//                String ID = data[0];
//                String brandID = data[1];
//                String color = data[2];
//                String frameID = data[3];
//                String engineID = data[4];
// chuyền brand bằng cách tìm kiếm pos sau đó chuyền vào cars
                StringTokenizer st = new StringTokenizer(line, ",");
                String carID = st.nextToken().trim();
                String brandID = st.nextToken().trim();
                String carColor = st.nextToken().trim();
                String framdID = st.nextToken().trim();
                String engineID = st.nextToken().trim();
                int pos = bl.searchID(brandID);
                if (pos >= 0) {
                    Brand brand = bl.get(pos);
                    //đủ dữ liệu sau đó add từng line vào:
                    Car nCar = new Car(carID, brand, carColor, framdID, engineID);
                    cl.add(nCar);
                }
                line = reader.readLine();
            }
            return true;

        } catch (IOException e) {
            System.out.println("File not found! " + e.getMessage());
            return false;
        }
    }

    public boolean saveToFile(String file) {
        //su dung PrintWritter
        try (PrintWriter pw = new PrintWriter(file)) {
            for (Car c : cl) {
                pw.println(c.toString());
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File error " + e.getMessage());
            return false;
        }
    }

    public int searchID(String carID) {
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getCarID().equals(carID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchEngine(String eID) {
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getEngineID().equals(eID)) {
                return i;
            }

        }
        return -1;
    }

    public int searchFrameID(String fID) {
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getFramID().equals(fID)) {
                return i;
            }

        }
        return -1;
    }

    public void addCar() {
        // gom: Car ID, brand ID, color, frame ID, engine ID
        String carID = vl.inputCarID("Enter carID: ", cl);
        System.out.println("Choice a brand: ");
        Brand b = bl.getUserChoice();
        String color = vl.inputString("Enter color: ");
        String frameID = vl.inputFrameID("Enter frame ID(F00000): ", cl);
        String engine = vl.inputEngineID("Enter engine ID(E0000): ", cl);
        Car nCar = new Car(carID, b, color, frameID, engine);
        cl.add(nCar);
    }

    public void printBasedBrandName() {
        String aPartOfName = vl.inputString("Enter a part of name: ");
        int count = 0;
        for (Car c : cl) {
            String brandName = c.getBrandID().getBrandName();
            if (brandName.contains(aPartOfName)) {
                System.out.println(c.screenString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No car is detected!");
        }
    }

    public boolean removeCar() {
        String removedID = vl.inputString("Enter ID to removed: ");
        int pos = searchID(removedID);
        if (pos < 0) {
            System.out.println("Not found.");
            return false;
        } else {
            cl.remove(pos);
            System.out.println("Removed success!");
            return true;
        }
    }

    public boolean updateCar() {
        String carID = vl.inputString("Enter car ID to update: ");
        int pos = searchID(carID);
        if (pos < 0) {
            System.out.println("Not found!");
            return false;
        } else {
            //can updated:  brand ID, color, frame ID, engine ID
            System.out.println("Choice Brand: ");
            Brand b = bl.getUserChoice();
            String color = vl.inputString("Enter the new color: ");
            String frameID = vl.inputFrameID("Enter the new frame ID: ", cl);
            String engineID = vl.inputEngineID("Enter the new engine ID: ", cl);
            //update sau khi co du thong tin moi
            Car c = cl.get(pos);
            c.setBrandID(b);
            c.setColor(color);
            c.setFramID(frameID);
            c.setEngineID(engineID);
            return true;
        }
    }

    public void listCars() {
        Collections.sort(cl);
        for (Car c : cl) {
            System.out.println(c.toString());
        }
    }

}
