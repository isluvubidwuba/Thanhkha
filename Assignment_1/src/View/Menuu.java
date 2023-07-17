/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class Menuu {
    public int int_getChoice(ArrayList<?> options) {
        int response;
        int N = options.size();
        
        for (int i = 0; i < N; i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        
        System.out.print("Please choose an option 1.." + N + ": ");
        Scanner scanner = new Scanner(System.in);
        response = scanner.nextInt();
        return response;
    }
    
    public <E> E ref_getChoice(ArrayList<E> options) {
        int response;
        int N = options.size();
        do {
            response = int_getChoice(options);
        } while (response < 1 || response > N);
        
        return options.get(response - 1);
    }
}
