
package Modifying_OODJ_Assignment;

import new_oodj_assign.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 *
 * @author LeeWenHan TP070146
 */

class Admin extends User implements java.io.Serializable
{
    private Item it;
    private PR pr = new PR();
    private Supply sp = new Supply();
    private DailySales ds = new DailySales();
    private PO po = new PO();
    
    protected static final long serialVersionUID = 3746308932813848007L;

    public void registerUser()
    {
        Admin[] userList = new Admin[50];  //Start Deserialize
        int ctr = 0;
        boolean stat = true;
        boolean validation = false;
        
        try
        {
            FileInputStream file = new FileInputStream("userData.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            
            while(stat)
            {
                Admin user = new Admin();
                try{user = (Admin)in.readObject();}
                catch(ClassNotFoundException e){ break;}
               
                if (user!=null)
                {
                    userList[ctr]=user;
                    ctr++;
                }
                else{stat=false;}
            }
        }
        catch(IOException ex){}   //End Deserialize

        
                
        NumberFormat formatter = new DecimalFormat("U000");        
        this.UID = formatter.format(User.Uctr);
        
        Scanner sc = new Scanner(System.in);
        System.out.println("UID: " + this.UID);
        System.out.print("Enter new Password: ");
        this.password = sc.nextLine();
        
        while(!validation)
        {
            System.out.print("Enter new User Role( Admin,SM,or PM ): ");
            this.role = sc.nextLine();

            if("Admin".equals(this.role) || "SM".equals(this.role) || "PM".equals(this.role)){validation=true;}
            else{System.out.print("Choose either 'Admin','SM','PM'. ");}
        }

        
//        Admin admin = new Admin();  // maybe can remove this line and use super
//        admin.setUID(this.UID);
//        admin.setPassword(this.password); 
//        admin.setRole(this.role);
            
        userList[ctr] = this;
        
        File userData = new File("userData.txt");
        try
        {
            FileOutputStream file = new FileOutputStream(userData);
            ObjectOutputStream out = new ObjectOutputStream(file);
            ctr=0;
            while(userList[ctr]!=null){out.writeObject(userList[ctr]);ctr++;}

            System.out.println("Successfully Register User.");
            User.Uctr++;
            file.close();
            out.close();
        }
        
        catch(IOException ex){System.out.println("Error catched");}
    }
    
    public static void Menu()
    {
        System.out.println("\nAdmin Entry Screen");
        System.out.println("-----------------");
        System.out.println("-- Item --");
        System.out.println("1. Add Item");
        System.out.println("2. Delete Item");
        System.out.println("3. Edit Item");
        System.out.println("4. Display list of Item");

        System.out.println("\n-- Daily Sales --");
        System.out.println("5. Add Daily Sales");
        System.out.println("6. Delete Daily Sales");
        System.out.println("7. Edit Daily Sales");  
        System.out.println("8. Display list of Daily Sales");

        
        System.out.println("\n-- PR --");
        System.out.println("9. Add PR");
        System.out.println("10. Delete PR");
        System.out.println("11. Edit PR");  
        System.out.println("12. Display list of PR");
        
        System.out.println("\n-- PO --");
        System.out.println("13. Add PO");
        System.out.println("14. Delete PO");
        System.out.println("15. Edit PO");
        System.out.println("16. Display list of PO");
        
        System.out.println("\n-- Supplier --");
        System.out.println("17. Add Supplier");
        System.out.println("18. Delete Supplier");
        System.out.println("19. Edit Supplier");
        System.out.println("20. Display list of SupplyList");
        
        System.out.println("\n-- Admin --");
        System.out.println("21. Register User");
        System.out.println("22. View Registered User List");

        
        System.out.println("-1. Exit");
        System.out.print("Enter your choice: ");
    }
    
    public void printUserList()
    {
        try
        {         
            FileInputStream file = new FileInputStream("userData.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            
            while(true)
            {
                Admin user = new Admin();
                try{user = (Admin)in.readObject();}
                catch(ClassNotFoundException e){ in.close();break;}
               
                System.out.println(user);      
                
                FileHandling f2 = new FileHandling("VerifyUserList.txt");
                FileHandlingMultithreading f3 = new FileHandlingMultithreading(f2);
                f3.run();
            }

        }
        catch(IOException ex){}
    }
     
    public void AddItem(){}

    public void ViewItem(){}
    
    public void ModifyItem(){}
    
    public void DeleteItem(){}
    
    public void AddSupplier(){sp.addSupplier();sp.addSupplyItem();}

    public void ViewSupplier(){sp.viewSupplier();sp.viewSupplyItem();}
    
    public void ModifySupplier(){sp.editSupplier();sp.editSupplyItem();}
    
    public void DeleteSupplier(){sp.deleteSupplier();sp.deleteSupplyItem();}
    
    public void AddDailySales(){ds.Add();}

    public void ViewDailySales(){ds.View();}
    
    public void ModifyDailySales(){ds.Modify(); }
    
    public void DeleteDailySales(){ds.Delete();}
    
    public void AddPR(){pr.Add();}
    
    public void ViewPR(){pr.View();}
    
    public void ModifyPR(){pr.Modify();}
    
    public void DeletePR(){pr.Delete();}
    
    public void AddPO(){}
    
    public void ViewPO(){po.View();}
    
    public void ModifyPO(){}
    
    public void DeletePO(){po.Delete();}
        
    public String toString(){return this.UID + "|" + this.password + "|" + this.role; }

    public String getRole(){return role;}
    public String getUID(){return UID;}
    public String getPassword(){return password;}
    public void setRole(String r){this.role = r;}
    public void setUID(String u){this.UID = u;}
    public void setPassword(String p){this.password = p;}
    
}

