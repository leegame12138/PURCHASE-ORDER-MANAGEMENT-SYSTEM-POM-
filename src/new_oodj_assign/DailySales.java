/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package new_oodj_assign;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;
import java.text.SimpleDateFormat;  
import java.util.Arrays;
import java.util.Date;  
import javax.swing.JOptionPane;
import new_oodj_assign.FileHandling;


public class DailySales {
    
    String itemID;
    int quantity;
    String userID;
    String filePath;
    String itemFilePath;
    String status;
    String currentDate;
    String DsId;

    public DailySales(String UID) 
    {
        this.filePath = "DailySales.txt";
        this.itemFilePath = "Item.txt";
        this.userID = UID;
    }
    
    public DailySales() {}

    
  
    public void viewDailySales() {
       
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
    
    public void addDailySales() {
        File FDailySales = new File(this.filePath);
        File FItem = new File(this.itemFilePath);
        try {

            FileReader fr_ds = new FileReader(FDailySales);
            FileReader fr_item = new FileReader(FItem);
            BufferedReader br_ds = new BufferedReader(fr_ds);
            BufferedReader br_item = new BufferedReader(fr_item);
            FileHandling fdl = new FileHandling(this.filePath);
            Scanner scanner = new Scanner(System.in);
            
            
            boolean isEntry = true;
            while (isEntry) {                
                
                Item.ViewItemList();
            
                //User Input: Item ID
                System.out.println("Choose from the Item Menu above!");
                System.out.print("Enter the Item ID: \n");
                this.itemID = scanner.nextLine();
            
                //User Input: Sales Quantity
                System.out.print("Enter Sales Quantity: \n");
                this.quantity = scanner.nextInt();
//                fdl.Write2File(fdl);
                
                //If condition for saving changes
                
                
                if (fdl.SaveConfirm()) {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
                    Date date = new Date();
                    this.currentDate = formatter.format(date);
                    generateDsId();
                    DailySales ds = new DailySales(User.CurrentUID);
                    this.status = "Existed";
                    ds.DsId = this.DsId;
                    ds.userID = this.userID;
                    ds.itemID = this.itemID;
                    ds.quantity = this.quantity;
                    ds.currentDate = this.currentDate;
                    ds.status = this.status;
                    fdl.Write2File(ds);
                    System.out.println("Sales data added successfully.");
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
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    } 
    
    public void editDailySales() {
    String[][] alllines = new String[0][];
    FileHandling f_ds = new FileHandling(this.filePath);
    Scanner sc = new Scanner(System.in).useDelimiter("\\n");
    File file = new File(this.filePath);

    try {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        LineNumberReader lnr = new LineNumberReader(br);

        String eachlines;
        int changeline = 0;
        String[] eachline;

        System.out.println("Enter the Daily Sales ID you wish to edit.");
        String dsId = sc.nextLine();

        while ((eachlines = br.readLine()) != null) {
            eachline = eachlines.split(" \\| ", 9);
            alllines = Arrays.copyOf(alllines, alllines.length + 1);
            alllines[changeline] = eachline;
            changeline++;
        }

        br.close();

        boolean foundMatch = false;

        for (int x = 0; x < alllines.length; x++) {
            if (alllines[x][0].equals(dsId)) {
                foundMatch = true;
                System.out.println("Enter the Item Id: ");
                String itemID = sc.nextLine();
                alllines[x][1] = itemID;

                System.out.println("Enter the Quantity: ");
                String itemQuantity = sc.nextLine();
                alllines[x][2] = itemQuantity;
    
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

    public void deleteDailySales() {
    String[][] alllines = new String[0][];
    FileHandling f_ds = new FileHandling(this.filePath);
    Scanner sc = new Scanner(System.in).useDelimiter("\\n");
    File file = new File(this.filePath);

    try {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        LineNumberReader lnr = new LineNumberReader(br);

        String eachlines;
        int changeline = 0;
        String[] eachline;

        System.out.println("Enter the Daily Sales ID you wish to delete.");
        String dsId = sc.nextLine();

        while ((eachlines = br.readLine()) != null) {
            eachline = eachlines.split(" \\| ", 9);
            alllines = Arrays.copyOf(alllines, alllines.length + 1);
            alllines[changeline] = eachline;
            changeline++;
        }

        br.close();

        boolean foundMatch = false;

        for (int x = 0; x < alllines.length; x++) {
            if (alllines[x][0].equals(dsId)) {
                foundMatch = true;
                this.status = "Deleted";
                alllines[x][5] = this.status;
    
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
    
    public String generateDsId() {
        File file = new File(this.filePath);
        try {
            int lineNumberforId;
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            LineNumberReader lnr = new LineNumberReader(br);
            while (lnr.readLine() != null) ;
            NumberFormat formatter = new DecimalFormat("DS000");
            lineNumberforId = lnr.getLineNumber() + 1;
            this.DsId = formatter.format(lineNumberforId);
            
        } catch (Exception e) {
            System.err.println("something went wrong");
        }
        return this.DsId;
    }
    
    public String toString(){
        return this.DsId + " | " + this.userID + " | " + this.itemID + " | " + this.quantity + " | " + this.currentDate + " | " + this.status; 
    }
}
    

