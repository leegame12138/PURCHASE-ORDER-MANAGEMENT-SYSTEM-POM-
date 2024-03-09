
package new_oodj_assign;

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

class Admin extends User
{
    private Item it;
    private PR pr = new PR();
    private Supply sp = new Supply();
    private DailySales ds = new DailySales();
   
    public void RegisterUser()
    {
        User[] userList = new User[50];  //Start Deserialize
        int ctr = 0;
        boolean stat = true;
        boolean validation = false;
        
        try
        {
            FileInputStream file = new FileInputStream("userData.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            
            while(stat)
            {
                User temp = new User();
                try{temp = (User)in.readObject();}
                catch(ClassNotFoundException e){ break;}
               
                if (temp!=null)
                {
                    userList[ctr]=temp;
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

        
        Admin admin = new Admin();  // maybe can remove this line and use super
        admin.setUID(this.UID);
        admin.setPassword(this.password); 
        admin.setRole(this.role);
            
        userList[ctr] = admin;
        
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
        System.out.println("\nSM Entry Screen");
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
        
        System.out.println("-1. Exit");
        System.out.print("Enter your choice: ");
    }
     
     
    public void AddItem(){}

    public void ViewItem(){}
    
    public void ModifyItem(){}
    
    public void DeleteItem(){}
    
    public void AddSupplier(){sp.addSupplier();sp.addSupplyItem();}

    public void ViewSupplier(){sp.viewSupplier();sp.viewSupplyItem();}
    
    public void ModifySupplier(){sp.editSupplier();sp.editSupplyItem();}
    
    public void DeleteSupplier(){sp.deleteSupplier();sp.deleteSupplyItem();}
    
    public void AddDailySales(){ds.addDailySales();}

    public void ViewDailySales(){ds.viewDailySales();}
    
    public void ModifyDailySales(){ds.editDailySales(); }
    
    public void DeleteDailySales(){ds.deleteDailySales();}
    
    public void AddPR(){pr.GeneratePR();}
    
    public void ViewPR(){pr.ViewPR();}
    
    public void ModifyPR(){pr.Modify();}
    
    public void DeletePR(){pr.Delete();}
    
    public void AddPO(){pr.GeneratePR();}
    
    public void ViewPO(){pr.ViewPR();}
    
    public void ModifyPO(){pr.Modify();}
    
    public void DeletePO(){pr.Delete();}
    

}

