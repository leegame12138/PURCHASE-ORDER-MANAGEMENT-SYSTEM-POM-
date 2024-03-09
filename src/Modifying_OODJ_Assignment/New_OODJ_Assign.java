package Modifying_OODJ_Assignment;

import new_oodj_assign.*;
import java.util.Scanner;
import java.io.File;


public class New_OODJ_Assign {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ApplicationSystem aps = new ApplicationSystem();
        int Choice;
        
        System.out.print("New System");
        
        File f = new File("userData.txt");
        if(!(f.exists() && !f.isDirectory())) { Initialize.registerUser();}
        aps.UpdateLatestID();  //Overriding
        Item.UpdateLatestID();  //Overriding
        PR.UpdateLatestID();    //Overriding
        
        
        while(true){
    
        if(aps.login())
        {
            Choice = 0;
            String role = aps.getRole();
            
            if ("SM".equals(role)){
                
                //@@@@@@@@@@@@ Temporary SM Object @@@@@@@@@@@@@@
                SM sm = new SM();
                while(Choice != -1)
                {
                    SM.Menu();
                    Choice = sc.nextInt();

                    switch(Choice)
                    {
                            case 1: //add item
                                boolean validInput = false;
                                System.out.println("Enter Item Name:");
                                String iName = sc.next();
                                System.out.println("Enter Item Price:");
                                String iPrice = sc.next();
                                System.out.println("Enter Item Quantity:");
                                String iQuantity = sc.next();
                                System.out.println("Enter Re-Order Level:");
                                String iROL = sc.next();
                                System.out.println("Enter Item Description:");
                                String iDesc = sc.next();

                                String iStatus="Existed";

                                while(!validInput)
                                {
                                    System.out.println("Do you want to save these changes?(Y/N)");
                                    String option=sc.next();
                                    
                                    if(option.equalsIgnoreCase("N"))
                                    { 
                                       System.out.println("Change were not saved.");
                                       validInput = true;
                                    }
                                    else if(option.equalsIgnoreCase("Y"))
                                    {
                                        String newItemID = Item.UpdateLatestID();
                                        Item TmpItem = new Item(newItemID, iName, iPrice, iQuantity, iROL, iDesc,iStatus);
                                        TmpItem.WriteFile();
                                        System.out.println("Item added successfully.");
                                        validInput = true;
                                    }
                                    else{System.out.println("Invalid Input, Try again with only 'N' or 'Y' ");}
                                }
                                break;
                            case 2:
                                Item.ViewItemList();
                                Item.DeleteItemID();
                                break;
                            case 3:
                                Item.ViewItemList();
                                Item.modifyItem();
                                break;
                            case 4:
                                Item.ViewItemList();
                                break;
                            case 5: //Daily Sales
                                sm.AddDailySales();
                                break;
                            case 6:
                                sm.DeleteDailySales();
                                break;
                            case 7:
                                sm.ModifyDailySales();
                                break;
                            case 8:
                                sm.ViewDailySales();
                                break;
                            case 9: // PR
                                sm.AddPR();
                                break;
                            case 10:
                                sm.DeletePR();
                                break;
                            case 11:
                                sm.ModifyPR();
                                break;
                            case 12:
                                sm.ViewPR();
                                break;
                            case 13: //PO
                                sm.ViewPO();
                                break;
                            case 14: //Supplier
                                sm.AddSupplier();
                                break;
                            case 15:
                                sm.DeleteSupplier();
                                break;
                            case 16:
                                sm.ModifySupplier();
                                break;
                            case 17:
                                sm.ViewSupplier();
                                break;
                            case -1:
                                break;
                            default:
                                System.out.println("Invalid input");
                            System.out.print(Choice);
                    }               
                }
                
            }else if("PM".equals(role)){
                
                PM pm =new PM();
                while(Choice != -1)
                {
                 PM.Menu();
                    Choice = sc.nextInt();

                    switch(Choice)
                    {
                        case 1:
                            pm.AddPO();
                            break;
                        case 2:
                            pm.DeletePO();
                            break;
                        case 3:
                            pm.ModifyPO();
                            break;
                        case 4:
                            pm.ViewPO();
                            break;
                        case -1:
                    }
                }
                
            }else if("Admin".equals(role)){
                Admin admin = new Admin();
                while(Choice != -1)
                {
                    System.out.println("Welcome Admin");
                    admin.Menu();
                    Choice = sc.nextInt();

                    switch(Choice)
                    {
                            case 1: //add item
                                boolean validInput = false;
                                System.out.println("Enter Item Name:");
                                String iName = sc.next();
                                System.out.println("Enter Item Price:");
                                String iPrice = sc.next();
                                System.out.println("Enter Item Quantity:");
                                String iQuantity = sc.next();
                                System.out.println("Enter Re-Order Level:");
                                String iROL = sc.next();
                                System.out.println("Enter Item Description:");
                                String iDesc = sc.next();

                                String iStatus="Existed";

                                while(!validInput)
                                {
                                    System.out.println("Do you want to save these changes?(Y/N)");
                                    String option=sc.next();
                                    
                                    if(option.equalsIgnoreCase("N"))
                                    { 
                                       System.out.println("Change were not saved.");
                                       validInput = true;
                                    }
                                    else if(option.equalsIgnoreCase("Y"))
                                    {
                                        String newItemID = Item.UpdateLatestID();
                                        Item TmpItem = new Item(newItemID, iName, iPrice, iQuantity, iROL, iDesc,iStatus);
                                        TmpItem.WriteFile();
                                        System.out.println("Item added successfully.");
                                        validInput = true;
                                    }
                                    else{System.out.println("Invalid Input, Try again with only 'N' or 'Y' ");}
                                }
                                break;
                            case 2:
                                Item.ViewItemList();
                                Item.DeleteItemID();
                                break;
                            case 3:
                                Item.ViewItemList();
                                Item.modifyItem();
                                break;
                            case 4:
                                Item.ViewItemList();
                                break;
                            case 5: //Daily Sales
                                admin.AddDailySales();
                                break;
                            case 6:
                                admin.DeleteDailySales();
                                break;
                            case 7:
                                admin.ModifyDailySales();
                                break;
                            case 8:
                                admin.ViewDailySales();
                                break;
                            case 9: // PR
                                admin.AddPR();
                                break;
                            case 10:
                                admin.DeletePR();
                                break;
                            case 11:
                                admin.ModifyPR();
                                break;
                            case 12:
                                admin.ViewPR();
                                break;
                            case 13: //PO Hevent Done
                                admin.AddPO();
                                break;
                            case 14:
                                admin.DeletePO();
                                break;
                            case 15:
                                admin.ModifyPO();
                                break;
                            case 16:
                                admin.ViewPO();
                                break;                                
                            case 17: //Supplier
                                admin.AddSupplier();
                                break;
                            case 18:
                                admin.DeleteSupplier();
                                break;
                            case 19:
                                admin.ModifySupplier();
                                break;
                            case 20:
                                admin.ViewSupplier();
                                break;
                            case 21:  // Admin
                                admin.registerUser();
                                break;
                            case 22:
                                admin.printUserList();
                                break;
                            case -1:
                                break;
                            default:
                                System.out.println("Invalid input");
                            System.out.print(Choice);
                    }               

                }
                    }else{System.out.println("Error occur: unrecognized User");}
            
        
            } else {System.out.println("Fail to Login.");}
    
        }

        }
    }
