/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modifying_OODJ_Assignment;


import new_oodj_assign.*;
import java.util.Scanner;

/**
 *
 * @author TEOH HUEY YING TP069734
 */

abstract class Save {
    
    public String saveOption() {
        
        Scanner sc = new Scanner(System.in);
        String save_option = null;
        boolean is_save = true;
        while (is_save) {            
            System.out.println("Save changes made?");
            System.out.println("Only key in Y or N!!!");
            save_option = sc.next();
            if (save_option.equals("Y") || save_option.equals("N")) {
                is_save = false;
                break;
                
            } else {
                System.out.println("Invalid input. Re-enter again.");
            }
        }
        return save_option;
    }
    
}