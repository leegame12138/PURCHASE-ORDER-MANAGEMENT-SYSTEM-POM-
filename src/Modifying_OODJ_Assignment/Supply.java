/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modifying_OODJ_Assignment;

import new_oodj_assign.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author TEOH HUEY YING TP069734
 */

public class Supply extends Save{
    String supplierID;
    String name;
    String address;
    int estimatedDeliveryDay;
    String itemID;
    double supplierPrice;
    String status;
    String currentDate;
    String itemFilePath = "Item.txt";
    String filePath = "Supplier.txt";
    String supplyListFilePath = "SupplyList.txt";
    
    public String generateSupplierId() {
        File file = new File(this.filePath);
        try {
            int lineNumberforId;
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            LineNumberReader lnr = new LineNumberReader(br);
            while (lnr.readLine() != null) ;
            NumberFormat formatter = new DecimalFormat("SPLY000");
            lineNumberforId = lnr.getLineNumber() + 1;
            this.supplierID = formatter.format(lineNumberforId);
            
        } catch (Exception e) {
            System.err.println("something went wrong");
        }
        return this.supplierID;
    }
    
    public void viewSupplier() {
       
       try {
            File FDailySales = new File(this.filePath);
            FileReader fr = new FileReader(FDailySales);
            BufferedReader br = new BufferedReader(fr);
            LineNumberReader lnr = new LineNumberReader(new FileReader(FDailySales));
            while (lnr.readLine() != null) ;
            int lines = lnr.getLineNumber();
            
            
            for (int i = 0; i < lines; i++) {
                    String dsData = br.readLine();
                    System.out.println(dsData);
                }
            br.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        
    }
    
    public void addSupplier() {
    File file = new File(this.filePath);

    try {
        FileWriter fw = new FileWriter(file, true); // Append mode enabled
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        Scanner sc = new Scanner(System.in);

        boolean isEntry = true;
        while (isEntry) {
            viewSupplier();
            // User Input: Supplier name
            System.out.print("Enter Supplier Name: \n");
            this.name = sc.nextLine();

            // User Input: Address
            System.out.print("Enter the address here: \n");
            this.address = sc.nextLine();

            System.out.print("Enter estimated delivery day: \n");
            this.estimatedDeliveryDay = sc.nextInt();
            sc.nextLine(); // Consume newline character

            this.status = "Existed";

            // If condition for saving changes
            FileHandling fdl = new FileHandling(this.filePath);

            if (fdl.SaveConfirm()) {
                this.supplierID = generateSupplierId();
                pw.write(this.supplierID + " | " + this.name + " | " + this.address + " | " + this.estimatedDeliveryDay + " | " + this.status);
                bw.close();
                System.out.println("Supplier data added successfully.");
                break;
            } else {
                System.out.println("Do you want to re-enter supplier data?");
                System.out.println("Y or N");
                String re_enter = sc.next();

                if (re_enter.equalsIgnoreCase("N")) {
                    isEntry = false;
                    break;
                }
                
                sc.nextLine(); // Consume newline character
            }
        }
    } catch (IOException e) {
        System.out.println("An error occurred while writing to the file: " + e.getMessage());
    }
}
    
    public void deleteSupplier() {
    String[][] alllines = new String[0][];
    Scanner sc = new Scanner(System.in).useDelimiter("\\n");
    File file = new File(this.filePath);

    try {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        LineNumberReader lnr = new LineNumberReader(br);

        String eachlines;
        int changeline = 0;
        String[] eachline;
        viewSupplier();
        System.out.println("Enter the Supplier ID you wish to delete.");
        String sp_id = sc.nextLine().toUpperCase();

        while ((eachlines = br.readLine()) != null) {
            eachline = eachlines.split(" \\| ", 9);
            alllines = Arrays.copyOf(alllines, alllines.length + 1);
            alllines[changeline] = eachline;
            changeline++;
        }

        br.close();

        boolean foundMatch = false;

        for (int x = 0; x < alllines.length; x++) {
            if (alllines[x][0].equals(sp_id)) {
                foundMatch = true;
                this.status = "Deleted";
                alllines[x][4] = this.status;
    
                // Write back all lines to the file here
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
    
                for (int i = 0; i < alllines.length; i++) {
                    String line = String.join(" | ", alllines[i]);
                    bw.write(line);
                    bw.newLine();
                }
    
            bw.close();
            }
        }

        if (foundMatch) {
            // Write back all lines to the file here
        } else {
            System.out.println("No matching Daily Sales ID found.");
        }

    } catch (IOException e) {
        System.out.println("Can't find file.");
    }
}
    
    public void editSupplier() {
    String[][] alllines = new String[0][];
    Scanner sc = new Scanner(System.in).useDelimiter("\\n");
    File file = new File(this.filePath);

    try {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        LineNumberReader lnr = new LineNumberReader(br);

        String eachlines;
        int changeline = 0;
        String[] eachline;
        viewSupplier();
        System.out.println("Enter the Supplier ID you wish to edit.");
        String sp_id = sc.nextLine().toUpperCase();

        while ((eachlines = br.readLine()) != null) {
            eachline = eachlines.split(" \\| ", 9);
            alllines = Arrays.copyOf(alllines, alllines.length + 1);
            alllines[changeline] = eachline;
            changeline++;
        }

        br.close();

        boolean foundMatch = false;

        for (int x = 0; x < alllines.length; x++) {
            if (alllines[x][0].equals(sp_id)) {
                foundMatch = true;
                
                System.out.println("Edit your Supplier Name: ");
                this.name = sc.nextLine();
                alllines[x][1] = name;

                System.out.println("Enter Supplier Address: ");
                this.address = sc.nextLine();
                alllines[x][2] = this.address;
                
                System.out.println("Enter Estimated Delivery Day: ");
                this.estimatedDeliveryDay = Integer.parseInt(sc.nextLine());
                alllines[x][3] = String.valueOf(this.estimatedDeliveryDay);
    
                // Write back all lines to the file here
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
    
                for (int i = 0; i < alllines.length; i++) {
                    String line = String.join(" | ", alllines[i]);
                    bw.write(line);
                    bw.newLine();
                }
    
            bw.close();
            }
        }

        if (foundMatch) {
            // Write back all lines to the file here
        } else {
            System.out.println("No matching Daily Sales ID found.");
        }

        } catch (IOException e) {
            System.out.println("Can't find file.");
        }
    }  
    
    public void viewSupplyItem() {
       
       try {
            File file = new File(this.supplyListFilePath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            LineNumberReader lnr = new LineNumberReader(new FileReader(file));
            while (lnr.readLine() != null) ;
            int lines = lnr.getLineNumber();
            
            
            for (int i = 0; i < lines; i++) {
                    String dsData = br.readLine();
                    System.out.println(dsData);
                }
            br.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        
    }
    
    public void addSupplyItem() {
        File supplyItemFile = new File(this.supplyListFilePath);
        
        FileHandling fdl = new FileHandling(this.supplyListFilePath);
        Scanner scanner = new Scanner(System.in);
        boolean isEntry = true;
        while (isEntry) {
            
            Item.ViewItemList();
            
            //User Input: Item ID
            System.out.print("Enter the Item ID: \n");
            this.itemID = scanner.nextLine().toUpperCase();
            
            //User Input: Sales Quantity
            System.out.print("Enter the supplier price of " + this.itemID + "\n");
            this.supplierPrice = scanner.nextDouble();
            
            
            //If condition for saving changes
            String save_option = saveOption();
            
            if (save_option.equals("Y")) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                this.currentDate = formatter.format(date);
                this.supplierID = "SP001";
                Supply sply = new Supply();
                this.status = "Existed";
                
                sply.supplierID = this.supplierID;
                sply.itemID = this.itemID;
                sply.supplierPrice = this.supplierPrice;
                sply.currentDate = this.currentDate;
                sply.status = this.status;
                fdl.Write2File(sply);
                System.out.println("Supply item data added successfully.");
                break;
            } else {
                System.out.println("Do you want to re-enter Daily Sales? ");
                System.out.println("Y or N");
                String re_enter = scanner.next();
                
                if (re_enter.equals("N")) {
                    isEntry = false;
                    break;
                }
            }
        }
    } 

    public void editSupplyItem(){
        String[][] alllines = new String[0][];
        FileHandling f_ds = new FileHandling(this.supplyListFilePath);
        Scanner sc = new Scanner(System.in).useDelimiter("\\n");
        File file = new File(this.supplyListFilePath);

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            LineNumberReader lnr = new LineNumberReader(br);

            String eachlines;
            int changeline = 0;
            String[] eachline;
            viewSupplyItem();
            System.out.println("Enter the Supplier ID you wish to edit.");
            this.supplierID = sc.nextLine().toUpperCase();

            System.out.println("Enter the Item ID you wish to edit.");
            this.itemID = sc.nextLine().toUpperCase();

            while ((eachlines = br.readLine()) != null) {
                eachline = eachlines.split(" \\| ", 9);
                alllines = Arrays.copyOf(alllines, alllines.length + 1);
                alllines[changeline] = eachline;
                changeline++;

                System.out.println(eachline[1]);
               }
        
                br.close();

                boolean foundMatch = false;

                for (int x = 0; x < alllines.length; x++) {
                    if (alllines[x][1].equals(this.supplierID)) {
                        System.out.println("Supplier ID found.\n");
                        foundMatch = true;
                
                // Check itemID
                for (int a = 0; a < alllines.length; a++) {
                    if (alllines[a][0].equals(this.itemID)) {
                        System.out.println("Edit your Price: ");
                        this.supplierPrice = Double.parseDouble(sc.nextLine());
                        alllines[a][2] = String.valueOf(this.supplierPrice);
                        System.out.println(alllines[a][2]);
                        break;
                    }
                }
                
                break;
            }
        }

        if (foundMatch) {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < alllines.length; i++) {
                String line = String.join(" | ", alllines[i]);
                bw.write(line);
                bw.newLine();
            }

            bw.close();
        } else {
            System.out.println("No matching record found.");
        }

    } catch (IOException e) {
        System.out.println("Can't find file.");
    }
}
    
    public void deleteSupplyItem(){
        String[][] alllines = new String[0][];
        Scanner sc = new Scanner(System.in).useDelimiter("\\n");
        File file = new File(this.supplyListFilePath);

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            LineNumberReader lnr = new LineNumberReader(br);

            String eachlines;
            int changeline = 0;
            String[] eachline;
            viewSupplyItem();
            System.out.println("Enter the Supplier ID you wish to delete.");
            this.supplierID = sc.nextLine().toUpperCase();

            System.out.println("Enter the Item ID you wish to delete.");
            this.itemID = sc.nextLine().toUpperCase();

            while ((eachlines = br.readLine()) != null) {
                eachline = eachlines.split(" \\| ", 9);
                alllines = Arrays.copyOf(alllines, alllines.length + 1);
                alllines[changeline] = eachline;
                changeline++;

                
               }
        
                br.close();

                boolean foundMatch = false;

                for (int x = 0; x < alllines.length; x++) {
                    if (alllines[x][1].equals(this.supplierID)) {
                        System.out.println("Supplier ID found.\n");
                        foundMatch = true;
                
                // Check itemID
                for (int a = 0; a < alllines.length; a++) {
                    if (alllines[a][0].equals(this.itemID)) {
                        this.status = "Deleted";
                        alllines[a][3] = String.valueOf(this.status);
                        
                        break;
                    }
                }
                
                break;
            }
        }

        if (foundMatch) {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < alllines.length; i++) {
                String line = String.join(" | ", alllines[i]);
                bw.write(line);
                bw.newLine();
            }

            bw.close();
        } else {
            System.out.println("No matching record found.");
        }

    } catch (IOException e) {
        System.out.println("Can't find file.");
    }
}
        
    
    
    
    public String toString(){
        return this.itemID + " | " + this.supplierID + " | " + this.supplierPrice + " | " + this.status;
    }
    
}
