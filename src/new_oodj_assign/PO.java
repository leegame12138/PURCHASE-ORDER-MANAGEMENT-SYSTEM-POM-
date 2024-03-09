/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package new_oodj_assign;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author wc2le
 */
public class PO {
    
    // add po
    public static int idCounter;
    String PRID;
    String UID;
    String ItemID;
    String SupplierID;
    String EstimateDeliveryTime;
    String Status;
    int Quantity;
    double SupplierPrice;
    
    
        public void ViewPR()
    {
            try (FileInputStream file = new FileInputStream("PR.txt");){
            Scanner scanner = new Scanner(file);
            
            System.out.println("\n===========================================================================================");
            System.out.println("PRID | UID | ItemID | SupplierID | EstimateDeliveryTime | SupplierPrice | Quantity | Status;");
            while (scanner.hasNextLine()) 
            {
                String line = scanner.nextLine();
                String[] part = line.split("\\|");
                PR pr = new PR();
                pr.PRID = part[0];
                pr.UID = part[1];
                pr.ItemID = part[2];
                pr.SupplierID = part[3];
                pr.Quantity = Integer.parseInt(part[4]);
                pr.SupplierPrice = Double.parseDouble(part[5]);
                pr.EstimateDeliveryTime = part[6];
                pr.Status = part[7];
                
                if(!("Deleted".equals(pr.Status)))
                {
                    System.out.println(pr);  //Output as object
                }
                
            }

            file.close();
        } catch (IOException ex) {}

    }

    public void ViewOwnPO()
    {
        try(FileInputStream file = new FileInputStream("PR.txt");){

        Scanner scanner = new Scanner(file);

        System.out.println("\n===========================================================================================");
        System.out.println("PRID | UID | ItemID | SupplierID | EstimateDeliveryTime | SupplierPrice | Quantity | Status;");
        while (scanner.hasNextLine()) 
        {
            String line = scanner.nextLine();
            String[] part = line.split("\\|");
            PR pr = new PR();
            pr.PRID = part[0];
            pr.UID = part[1];
            pr.ItemID = part[2];
            pr.SupplierID = part[3];
            pr.Quantity = Integer.parseInt(part[4]);
            pr.SupplierPrice = Double.parseDouble(part[5]);
            pr.EstimateDeliveryTime = part[6];
            pr.Status = part[7];

            if(!("Deleted".equals(pr.Status) && pr.PRID == User.CurrentUID))
            {
                System.out.println(pr);  //Output as object
            }   
        }

        file.close();
        } catch (IOException ex) {}

    }
    
        
        
    //Delete PO
        public void Delete()
    {
        String PRIDtoDelete = null;
        String ItemIDtoDelete = null;
        String SupplierIDtoDelete = null;
        int NewQuantity = 0;
        boolean ValidPRID = false;
        boolean ValidItemID = false;
        boolean ValidSupplierID = false;
        boolean ValidNewQuantity = false;
        this.ViewOwnPO();
        try {
            Scanner sc = new Scanner(System.in);
            
            File file = new File("PO.txt");
            Scanner fileScanner = new Scanner(file);
            FileHandling fileHandling = new FileHandling("PO.txt");

            while(!ValidPRID)
            {
                System.out.print("Enter POID that you want to Delete: " );
                PRIDtoDelete = sc.next();
                
                if(fileHandling.CheckExist(PRIDtoDelete, 0))
                {
                    ValidPRID = true;
                }
            }
            
            while(!ValidItemID)
            {
                System.out.print("Enter ItemID that you want to Delete: " );
                ItemIDtoDelete = sc.next();
                
                if(fileHandling.CheckExist(ItemIDtoDelete, 2))
                {
                    ValidItemID = true;
                }
            }
            


            int ctr = 0;
            while (fileScanner.hasNextLine()) 
            {
                String line = fileScanner.nextLine();
                String[] part = line.split("\\|");
                this.PRID = part[0];
                this.UID = part[1];
                this.ItemID = part[2];
                this.SupplierID = part[3];
                this.Quantity = Integer.parseInt(part[4]);
                this.SupplierPrice = Double.parseDouble(part[5]);
                this.EstimateDeliveryTime = part[6];
                this.Status = part[7];
                
                if(this.PRID.equals(PRIDtoDelete) && this.ItemID.equalsIgnoreCase(ItemIDtoDelete) ){ctr++;}
                
            }
            fileScanner.close();           
           
            if(fileHandling.SaveConfirm())
            {
            
                Scanner fileScanner2 = new Scanner(file);


                while (fileScanner2.hasNextLine()) 
                {
                    String line = fileScanner2.nextLine();
                    String[] part = line.split("\\|");
                    this.PRID = part[0];
                    this.UID = part[1];
                    this.ItemID = part[2];
                    this.SupplierID = part[3];  
                    this.Quantity = Integer.parseInt(part[4]);
                    this.SupplierPrice = Double.parseDouble(part[5]);
                    this.EstimateDeliveryTime = part[6];
                    this.Status = part[7];

                    if(this.PRID.equals(PRIDtoDelete) && this.ItemID.equalsIgnoreCase(ItemIDtoDelete) && !SupplierIDtoDelete.isBlank())
                    {
                        if(this.SupplierID.equals(SupplierIDtoDelete))
                        {
                        this.Status = "Deleted";
                        }
                    }
                    else if (this.PRID.equals(PRIDtoDelete) && this.ItemID.equalsIgnoreCase(ItemIDtoDelete) && SupplierIDtoDelete.isBlank() )
                    {
                        this.Status = "Deleted";
                    }


                    File NewFile = new File("tempPR.txt");
                    NewFile.createNewFile();
                    FileHandling tempPRFileHandling = new FileHandling("tempPR.txt");
                    FileHandlingMultithreading f = new FileHandlingMultithreading(tempPRFileHandling);
                    f.run(this);

                }
                fileScanner2.close();
                File NewFile = new File("tempPR.txt");
                if(file.delete()){System.out.println("Delete Done Successfullly.");}
                File OldFile = new File("PR.txt");
                NewFile.renameTo(OldFile);
                
            }
            else{ System.out.println("Cancel Deleting.");}
            
        } catch (IOException ex) {}
        
        
    }
    
    
    
    
}
