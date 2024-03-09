/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package new_oodj_assign;

/**
 *
 * @author wc2le
 */
public class SM {
    private Item it;
    private PR pr = new PR();
    private Supply sp = new Supply();
    private DailySales ds = new DailySales();
    
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
        System.out.println("13. Display list of PO");
        
        System.out.println("\n-- Supplier --");
        System.out.println("14. Add Supplier");
        System.out.println("15. Delete Supplier");
        System.out.println("16. Edit Supplier");
        System.out.println("17. Display list of SupplyList");
        
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
    
    public void ViewPO(){}

    
    
}
