
package new_oodj_assign;

import java.io.*;
import java.util.Scanner;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;
import java.nio.file.Paths;


public class PR {
    
    public static int idCounter;
    String PRID;
    String UID;
    String ItemID;
    String SupplierID;
    String EstimateDeliveryTime;
    String Status;
    int Quantity;
    double SupplierPrice;

    
    public PR(String ItemID,String SupplierID,int Quantity,double SupplierPrice,String EstimateDeliveryTime,String Status)
    {
        
        this.UID = User.CurrentUID;
        this.ItemID = ItemID;
        this.SupplierID = SupplierID;
        this.Quantity = Quantity;
        this.SupplierPrice = SupplierPrice;
        this.EstimateDeliveryTime = EstimateDeliveryTime;
        this.Status = Status;
    }
    
    public PR(){}
    
    public static void UpdateLatestID(){
            String latestItemID=null;

        try(FileInputStream file = new FileInputStream("PR.txt");
            Scanner scanner = new Scanner(file);){
            

            while (scanner.hasNextLine()) 
            {
                String line = scanner.nextLine();
                String[] part = line.split("\\|");
                latestItemID = part[0]; 
            }

            if (latestItemID.isEmpty()){ System.out.println("PR file does not exist OR PR file is empty.");
            } else 
            {
                String[] strList = latestItemID.split("PR");
                idCounter = Integer.parseInt(strList[1]) + 1;
            }

            file.close();
        } catch (IOException ex) {}
}
    
    public static String generateID()
    {
        String formattedCounter = String.format("PR%03d", idCounter);
        idCounter++;
        return formattedCounter;
    }
    
    
    public void GeneratePR()
    {
        boolean ContinueAddingPR = true; 
        String save_option;
        this.PRID = PR.generateID();
        
        Scanner sc = new Scanner(System.in);
        
        while(ContinueAddingPR)
        {
                    boolean ValidItemID = false;
                    boolean ValidSupplierID = false;
                    boolean ValidQuantity = false;
                    boolean Matched_ItemID_SupplierID = false;
                    boolean ValidContinueAddingPR = false;
            
            while(!ValidItemID)
            {
                // ********************* Print Available Item ID code
                Item.ViewItemList(); //Temporary , wait for  proper Association    @@  UPDATED, View olny below ROL @@

                //@@@@@@@@    Input and validate Item    @@@@@@@@

                System.out.print("Enter Item ID for PR: ");
                ItemID = sc.next();

                FileHandling ItemFile = new FileHandling("Item.txt");

                if (ItemFile.CheckExist(ItemID, 0) && Item.detectRol(ItemID)){   // And CheckLowInStock(ItemID)
                    ValidItemID = true;

                    while(!ValidSupplierID)
                    {
                        File SupplyListFile = new File("SupplyList.txt");

                        //@@@@@@@@    Print Available Supplier ID code    @@@@@@@@
                        // ** TEMPORARY (might add View function into FileHandling
                        try(Scanner fileScanner = new Scanner(SupplyListFile);)
                        {
                            System.out.println("\n=== List of Available Supplier that supply " + ItemID.toUpperCase() + " === " );
                            while (fileScanner.hasNextLine())
                            {
                                String Line = fileScanner.nextLine();
                                String[] part = Line.split("\\|");

                                if(part[0].equalsIgnoreCase(ItemID))
                                {
                                     System.out.println(part[1] + " | " + part[2]);
                                }
                            }
                            fileScanner.close();

                        }catch (IOException Ex){}

                        //@@@@@@@@    ENDED Print Available Supplier ID code    @@@@@@@@


                        //@@@@@@@@    Input and validate Supplier    @@@@@@@@
                        System.out.print("Enter Supplier ID for PR: ");
                        SupplierID = sc.next();

                        FileHandling SupplyListFileHandling = new FileHandling("SupplyList.txt");

                        if (SupplyListFileHandling.CheckExist(SupplierID, 1)){


                                //@@@@@@@@    Match ItemID and SUpplierID   @@@@@@@@
                            try(Scanner fileScanner = new Scanner(SupplyListFile);)
                            {
                                while (fileScanner.hasNextLine())
                                {
                                    String Line = fileScanner.nextLine();
                                    String[] part = Line.split("\\|");

                                    if(part[0].equalsIgnoreCase(ItemID))
                                    {                                     
                                       if(part[1].equalsIgnoreCase(SupplierID))
                                        {
                                            Matched_ItemID_SupplierID = true;
                                        }
                                    }
                                }
                                fileScanner.close();

                                if(Matched_ItemID_SupplierID)
                                {
                                    ValidSupplierID = true;
                                }
                                else
                                {
                                    System.out.println("Selected Supplier ID doesn't supply Item " + ItemID.toUpperCase() + ". Try again.");
                                }                            
                            }catch (IOException Ex){}
                        }
                        else{ System.out.println("Invalid Supplier ID. ID not exist. Try again.");}
                            //@@@@@@@@    ENDED Match ItemID and SUpplierID   @@@@@@@@

                    }
                    //@@@@@@@@  ENDED Supplier Validation (While loop)   @@@@@@@@


                    while(!ValidQuantity)
                    {
                        //@@@@@@@@    Input and validate Quality   @@@@@@@@
                        try{
                            System.out.print("Enter Quantity for Item " + ItemID + " you want to buy from Supplier " + SupplierID + ": ");
                            Quantity = Integer.parseInt(sc.next());

                            if(Quantity <= 0){ throw new NumberFormatException(); }
                            else{ ValidQuantity = true; }
                            }
                        catch(NumberFormatException e){System.out.println("Invalid input, only Possitive Integer are allowed. Try again\n."); ValidQuantity = false; Quantity=0;}
                    }
                    //@@@@@@@@  ENDED Quality validation   @@@@@@@@

                    //@@@@@@@@    Get Data from Supplier   @@@@@@@@


                    File SupplyListFile = new File("SupplyList.txt");

                    try(Scanner fileScanner = new Scanner(SupplyListFile);)
                    {
                        while (fileScanner.hasNextLine())
                        {
                            String Line = fileScanner.nextLine();
                            String[] part = Line.split("\\|");

                            if(part[0].equalsIgnoreCase(SupplierID))
                            {
                                EstimateDeliveryTime = part[3];
                            }
                            else{}
                        }
                        fileScanner.close();
                    }
                    catch (IOException Ex){}                    
                    //@@@@@@@@    ENDED Get Data from Supplier   @@@@@@@@

                    //@@@@@@@@    Approved / declined / pending Status   @@@@@@@@

                    Status = "Pending";

                    //@@@@@@@@    Confirm to Save or Cancle   @@@@@@@@

                    FileHandling PRfile = new FileHandling("PR.txt");

                    if(PRfile.SaveConfirm())
                    {
                    //@@@@@@@@    Start Write to file   @@@@@@@@

                    //Create Object (OOP concept) to write as Object
                    PR pr = new PR(ItemID,SupplierID, Quantity, SupplierPrice, EstimateDeliveryTime, Status);
                    pr.PRID = this.PRID;
                    FileHandlingMultithreading PRfile2 = new FileHandlingMultithreading(PRfile);
                    PRfile2.run(pr);
                    //@@@@@@@@    ENDED Write to file   @@@@@@@@

                    System.out.println("PR Successfully Created.\n");
                    }
                    else
                    {

                        System.out.println("CANCEL adding current Item.");
                    }

                    //@@@@@@@@    ENDED Confirm to Save or Cancle   @@@@@@@@                                




                }else{System.out.println("Item Invalid, please Try again.");}

            }
        //@@@@@@@@    Input and validate Item    @@@@@@@@
        

            while (!ValidContinueAddingPR) 
            {            
                System.out.println("Do You want to Add more Item into same current PR? ( 'Y' or 'N')");
                save_option = sc.next();

                if (save_option.equalsIgnoreCase("Y")) 
                {
                    ValidContinueAddingPR = true;
                    ContinueAddingPR = true;
                }
                else if (save_option.equalsIgnoreCase("N")) 
                {
                    ValidContinueAddingPR = true;
                    ContinueAddingPR = false;
                } 
                else 
                {
                    ValidContinueAddingPR = false;
                    System.out.println("Invalid input. Re-enter again.");
                }
            }

        
        }
        System.out.println("PR Close.");

    }
        

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
    
    
    public void ViewOwnPR()
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
    
    
    public void Modify()
    {
        String PRIDtoDelete = null;
        String ItemIDtoDelete = null;
        String SupplierIDtoDelete = null;
        int NewQuantity = 0;
        boolean ValidPRID = false;
        boolean ValidItemID = false;
        boolean ValidSupplierID = false;
        boolean ValidNewQuantity = false;
        this.ViewOwnPR();
        try {
            Scanner sc = new Scanner(System.in);
            
            File file = new File("PR.txt");
            Scanner fileScanner = new Scanner(file);
            FileHandling fileHandling = new FileHandling("PR.txt");

            while(!ValidPRID)
            {
                System.out.print("Enter PRID that you want to Modify: " );
                PRIDtoDelete = sc.next();
                
                if(fileHandling.CheckExist(PRIDtoDelete, 0))
                {
                    ValidPRID = true;
                    
                }
            }
            
            while(!ValidItemID)
            {
                System.out.print("Enter ItemID that you want to Modify: " );
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
            if(ctr>1)
            {
                while(!ValidSupplierID)
                {
                    System.out.print("Enter SupplierID that you want to Modify: " );
                    SupplierIDtoDelete = sc.next();

                    if(fileHandling.CheckExist(SupplierIDtoDelete, 3))
                    {
                        ValidSupplierID = true;
                    }
                }
            }
           
            while(!ValidNewQuantity)
            {
                try{
                    System.out.println("Enter New Quantity: ");
                    NewQuantity = Integer.parseInt(sc.next());

                    if(Quantity <= 0){ throw new NumberFormatException(); }
                    else{ ValidNewQuantity = true; }
                    }
                catch(NumberFormatException e){System.out.println("Invalid input, only Possitive Integer are allowed. Try again\n.");}
            }
            
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
                        this.Quantity = NewQuantity;
                        }
                    }
                    else if (this.PRID.equals(PRIDtoDelete) && this.ItemID.equalsIgnoreCase(ItemIDtoDelete) && SupplierIDtoDelete.isBlank() )
                    {
                        this.Quantity = NewQuantity;
                    }


                    File NewFile = new File("tempPR.txt");
                    NewFile.createNewFile();
                    FileHandling tempPRFileHandling = new FileHandling("tempPR.txt");
                    FileHandlingMultithreading f = new FileHandlingMultithreading(tempPRFileHandling);
                    f.run(this);

                }
                fileScanner2.close();
                File NewFile = new File("tempPR.txt");
                if(file.delete()){System.out.println("Modify Done Successfullly.");}
                File OldFile = new File("PR.txt");
                NewFile.renameTo(OldFile);
                
            }
            else{ System.out.println("Cancel Modifying.");}
            
        } catch (IOException ex) {}
        
        
    }
    
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
        this.ViewOwnPR();
        try {
            Scanner sc = new Scanner(System.in);
            
            File file = new File("PR.txt");
            Scanner fileScanner = new Scanner(file);
            FileHandling fileHandling = new FileHandling("PR.txt");

            while(!ValidPRID)
            {
                System.out.print("Enter PRID that you want to Delete: " );
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
            if(ctr>1)
            {
                while(!ValidSupplierID)
                {
                    System.out.print("Enter SupplierID that you want to Delete: " );
                    SupplierIDtoDelete = sc.next();

                    if(fileHandling.CheckExist(SupplierIDtoDelete, 3))
                    {
                        ValidSupplierID = true;
                    }
                }
            }
           
           
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
    
    public String toString(){return this.PRID + "|" + this.UID + "|" + this.ItemID + "|" + this.SupplierID + "|" + this.Quantity + "|" + this.SupplierPrice + "|" + this.EstimateDeliveryTime + "|" + this.Status;}

    

}
