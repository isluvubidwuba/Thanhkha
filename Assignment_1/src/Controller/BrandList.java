/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Brand;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import View.Menuu;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 *
 * @author Acer
 */
public class BrandList {

    private ArrayList<Brand> bl;
    private Validation vl;

    public BrandList() {
        bl = new ArrayList<>();
        vl = new Validation();
    }

    public ArrayList<Brand> getBl() {
        return bl;
    }

    public boolean loadFromFile(String file) {
        File f = new File(file);
        //sử dụng BufferedReader để đọc
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            // read từng dòng
            // sử dụng readLine
            while (line != null) {
                //B7-2018, BMW 730Li (2018), Harman Kardon: 3.749
                // split de chia
                String data[] = line.split(", ");
                String ID = data[0].trim();
                String brandName = data[1].trim(); 
                //chia theo dinh dang
                String soundBrand = data[2].split(": ")[0].trim();
                String price_raw = data[2].split(": ")[1].trim();
                double price = Double.parseDouble(price_raw);

                //xử lý dòng
//                StringTokenizer st = new StringTokenizer(line, ",");
//                String id = st.nextToken().trim();
//                String name = st.nextToken().trim();
//                String str = st.nextToken().trim();
//                //trong file brands.txt co : nen phai xu ly
//                st = new StringTokenizer(str, ":");
//                String sound = st.nextToken().trim();
//                double price = Double.parseDouble(st.nextToken().trim());
                //Dùng dữ kiện đúc brand

                Brand nBrand = new Brand(ID, brandName, soundBrand, price);
                bl.add(nBrand);
                line = reader.readLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("File not found " + e.getMessage());
            return false;
        }
    }

    public boolean saveToFile(String file) {
        //sử dụng PrintWriter để savefile
        // save format: <ID, brand name, sound brand:price>
        try (PrintWriter pw = new PrintWriter(file)) {
            for (Brand b : bl) {
                pw.println(b.getBrandID() + ", " + b.getBrandName()+ ", " + b.getSoundBrand()+ ": " + b.getPrice());
            }
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e.getMessage());
            return false;
        }
    }

    public int searchID(String bID) {
        for (int i = 0; i < bl.size(); i++) {
            if (bl.get(i).getBrandID().equals(bID)) {
                return i;
            }
        }
        return -1;
    }

    public Brand getUserChoice() {
        Menuu mnu = new Menuu();
        return (Brand) mnu.ref_getChoice(bl);
    }
    /**
     * 
     */
    public void addBrand(){
        //Brand ID, brand name, sound brand, price
       String ID = vl.inputBrandID("Enter Brand ID: ", bl);
       String brandName = vl.inputString("Enter Brand Name: ");
       String soundBrand = vl.inputString("Enter sound Brand: ");
       double price = vl.inputDouble("Enter price: ");
       Brand nB = new Brand(ID, brandName, soundBrand, price);
       bl.add(nB);
       System.out.println("Add success!");
    }
    public void updateBrand(){
        String brandID = vl.inputString("Enter brand ID to update: ");
        int pos = searchID(brandID);
        if(pos < 0) System.out.println("Not found !");
        else {
            String brandName = vl.inputString("Enter new brand Name: ");
            String soundBrand = vl.inputString("Enter new sound Brand: ");
            double price = vl.inputDouble("Enter new price: ");
            // update vao list bang cach dung index la pos de update
           Brand findBrand = bl.get(pos);
           findBrand.setBrandName(brandName);
           findBrand.setPrice(price);
           findBrand.setSoundBrand(soundBrand);
           System.out.println("Update success");
        }
    }
    public void listBrand(){
        Iterator iter = bl.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next().toString());
        }
//        for (Brand b : bl) {
//            System.out.println(b.toString());
//        }
    }
    public Brand get(int index) {
        if (index >= 0 && index < bl.size()) {
            return bl.get(index);
        } else {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
    }

}
