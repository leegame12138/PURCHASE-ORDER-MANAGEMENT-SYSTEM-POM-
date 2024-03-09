/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modifying_OODJ_Assignment;


public class PM {
    
    private Item it;
    final private PR pr = new PR();
    final private Supply sp = new Supply();
    final private DailySales ds = new DailySales();
    private PO po = new PO();
    
    public static void Menu()
    {
        System.out.println("\nPM Entry Screen");
        System.out.println("-----------------");
        System.out.println("-- PO --");
        System.out.println("1. Add PO");
        System.out.println("2. Delete PO");
        System.out.println("3. Edit PO");
        System.out.println("4. Display list of PO");
        
        System.out.println("-1. Exit");
        System.out.print("Enter your choice: ");
    }
    public void AddPO(){}

    public void ViewPO(){po.ViewOwnPO();}
    
    public void ModifyPO(){}
    
    public void DeletePO(){}
}
