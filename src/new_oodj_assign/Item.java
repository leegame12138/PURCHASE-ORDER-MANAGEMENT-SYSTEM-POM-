package new_oodj_assign;


//import java.io.*;
//import java.util.Scanner;
//import java.util.ArrayList;
//
///**
// *
// * @author QinJun
// */
//
//public class Item 
//{
//    private static int idCounter = 0;
//    private String ItemID;
//    private String Name;
//    private String Price;
//    private String Quantity;
//    private String ROL;
//    private String Desc;
//
//    public Item(String ItemID, String Name, String Price, String Quantity, String ROL, String Desc, String Status)
//    {
//        this.ItemID = ItemID;
//        this.Name = Name;
//        this.Price = Price;
//        this.Quantity = Quantity;
//        this.ROL = ROL;
//        this.Desc = Desc;
//    }
//
//    public static String generateItemID()
//    {
//        String formattedCounter = String.format("IT%03d", idCounter);
//        idCounter++;
//        return formattedCounter;
//    }
//
//    public String getItemID()
//    {
//        return ItemID;
//    }
//
//    public String getName()
//    {
//        return Name;
//    }
//
//    public String getPrice()
//    {
//        return Price;
//    }
//
//    public String getQuantity()
//    {
//        return Quantity;
//    }
//
//    public String getROL()
//    {
//        return ROL;
//    }
//
//    public String getDesc()
//    {
//        return Desc;
//    }
//
//    public String toString()
//    {
//        return ItemID + "|" + Name + "|" + Price + "|" + Quantity + "|" + ROL + "|" + Desc;
//    }
//public static String UpdateLatestID() 
//{
//    String latestItemID = "";
//    try {
//        FileInputStream file = new FileInputStream("Item.txt");
//        Scanner scanner = new Scanner(file);
//
//        while (scanner.hasNextLine()) 
//        {
//            String line = scanner.nextLine();
//            String[] part = line.split("\\|");
//            latestItemID = part[0]; 
//        }
//        
//        scanner.close();
//    } catch (IOException ex) 
//    {
//    }
//    if (!latestItemID.isEmpty()) 
//    {
//        String[] strList = latestItemID.split("IT");
//        idCounter = Integer.parseInt(strList[1]) + 1;
//    }
//    return generateItemID();
//}
//    

///*
//            Item TmpItem = new Item(part[0], part[1], part[2], part[3], part[4], part[5]);
//            ItemList.add(TmpItem);
//        }
//        fileScanner.close();
//    }
//    catch (IOException Ex)
//    {
//        System.out.println("File Error");
//    }
//
//    System.out.println("List of Item:");
//    System.out.println("===================");
//
//    for (Item item : ItemList)
//    {
//        System.out.println("Test 1");
//        System.out.println(item.getItemID() + "|" + item.getName() + "|" + item.getPrice() + "|" + item.getQuantity() + "|" + item.getROL() + "|" + item.getDesc());
//        System.out.println("Test 2");
//
//    }
//
//    System.out.println("===================");
//} */
//
//public static void DeleteItemID() {
//    String tempFile = "temp.txt";
//    File oldFile = new File("Item.txt");
//    File newFile = new File(tempFile);
//    Scanner Sc = new Scanner(System.in);
//    
//
//    int line = 0;
//    String currentLine;
//    String part[];
//    System.out.println("Enter ItemID(eg.IT 00(the number) of the item you want to delete:");
//    String itemIDToDelete = Sc.next();
//
//    try {
//        FileWriter fw = new FileWriter(tempFile, true);
//        BufferedWriter bw = new BufferedWriter(fw);
//        PrintWriter pw = new PrintWriter(bw);
//
//        FileReader fr = new FileReader("Item.txt");
//        BufferedReader br = new BufferedReader(fr);
//
//        while ((currentLine = br.readLine()) != null) 
//        {
//            line++;
//            part = currentLine.split("\\|");
//            if (part[0].equalsIgnoreCase(itemIDToDelete)) 
//            {
//                System.out.println("Do you confirm you want to delete this item? (Y/N)");
//                String option = Sc.next();
//                if (option.equalsIgnoreCase("Y")) 
//                {    
//                    continue;   
//                } 
//                else 
//                {
//                    System.out.println("Changes were not saved.");
//                    pw.println(currentLine);
//                }
//            } else {
//                pw.println(currentLine);
//            }
//        }
//        pw.flush();
//        pw.close();
//        br.close();
//
//        oldFile.delete();
//        File dump = new File("Item.txt");
//        newFile.renameTo(dump);
//        System.out.println("Item with ItemID " + itemIDToDelete + " has been deleted.");
//
//    } catch (IOException Ex) {
//        System.out.println("File Error");
//    }
//}
//
//public void WriteFile()
//    {
//        File Finput = new File("Item.txt");
//        try
//        {
//            FileWriter fw = new FileWriter(Finput, true);
//            BufferedWriter bw = new BufferedWriter(fw);
//            PrintWriter pw = new PrintWriter(bw);
//            String line = ItemID + "|" + Name + "|" + Price + "|" + Quantity + "|" + ROL + "|" + Desc + "\n";
//            pw.write(line);
//            pw.close();
//        }
//        catch (IOException Ex)
//        {
//            System.out.println("File Error");
//        }
//    }
//    
//public static void modifyItem() {
//    Scanner Sc = new Scanner(System.in);
//    System.out.println("Enter ItemID (e.g. IT00 - the number) of the item you want to modify:");
//    String itemID = Sc.next();
//    String status="Existed";
//    
//    
//    if (!itemID.matches("^IT\\d{3}$")) {
//        System.out.println("Invalid Item ID format. Please use the format IT00.");
//        return;
//    }
//    System.out.println("Enter New Item Name:");
//    String newName = Sc.next();
//    System.out.println("Enter New Item Price:");
//    String newPrice = Sc.next();
//    System.out.println("Enter New Item Quantity:");
//    String newQuantity = Sc.next();
//    System.out.println("Enter New Item ROL:");
//    String newRoL = Sc.next();
//    System.out.println("Enter New Item Desc:");
//    String newDesc = Sc.next();
//    
//    System.out.println("Do you want to save these changes?(Y/N)");
//    String option=Sc.next();
//    
//    if(!option.equalsIgnoreCase("Y"))
//    {
//        System.out.println("Change were not saved.");
//        return;
//    }
//
//    String tempFile = "temp.txt";
//    String filepath = "Item.txt";
//    File oldFile = new File(filepath);
//    File newFile = new File(tempFile);
//    
//    try {
//        FileWriter fw = new FileWriter(tempFile, true);
//        BufferedWriter bw = new BufferedWriter(fw);
//        PrintWriter pw = new PrintWriter(bw);
//
//        Scanner x = new Scanner(new File(filepath));
//        
//        while (x.hasNextLine()) 
//        {
//            String line = x.nextLine();
//            String[] part = line.split("\\|");
//
//            if (part.length == 7 && part[0].equals(itemID)) 
//            {
//                pw.println(itemID + "|" + newName + "|" + newPrice + "|" + newQuantity + "|" + newRoL + "|" + newDesc + "|" + status );
//            } else 
//            {
//                pw.println(line);
//            }
//        }
//        
//        x.close();
//        pw.flush();
//        pw.close();
//   
//        oldFile.delete();
//        File dump = new File(filepath);
//        newFile.renameTo(dump);
//        
//        System.out.println("Item modified successfully.");
//    } catch (IOException Ex) {
//        System.out.println("Error while modifying item: " + Ex.getMessage());
//    }
//}
//
//

//
//

// ++++++++++++++++++++++++++++++++++

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Item 
{
    private static int idCounter = 0;
    private String ItemID;
    private String Name;
    private String Price;
    private String Quantity;
    private String ROL;
    private String Desc;
    private String Status; 

    public Item(String ItemID, String Name, String Price, String Quantity, String ROL, String Desc, String Status)
    {
        this.ItemID = ItemID;
        this.Name = Name;
        this.Price = Price;
        this.Quantity = Quantity;
        this.ROL = ROL;
        this.Desc = Desc;
        this.Status=Status;
    }

    public static String generateItemID()
    {
        String formattedCounter = String.format("IT%03d", idCounter);
        idCounter++;
        return formattedCounter;
    }

    public String getItemID()
    {
        return ItemID;
    }

    public String getName()
    {
        return Name;
    }

    public String getPrice()
    {
        return Price;
    }

    public String getQuantity()
    {
        return Quantity;
    }

    public String getROL()
    {
        return ROL;
    }

    public String getDesc()
    {
        return Desc;
    }
    public String getStatus()
    {
        return Status;
    }

    public String toString()
    {
        return ItemID + "|" + Name + "|" + Price + "|" + Quantity + "|" + ROL + "|" + Desc + "|" + Status;
    }

    public static String UpdateLatestID() 
    {
        String latestItemID = "";
        try {
            FileInputStream file = new FileInputStream("Item.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) 
            {
                String line = scanner.nextLine();
                String[] part = line.split("\\|");
                latestItemID = part[0]; 
            }

            scanner.close();
        } catch (IOException ex) 
        {
        }
        if (!latestItemID.isEmpty()) 
        {
            String[] strList = latestItemID.split("IT");
            idCounter = Integer.parseInt(strList[1]) + 1;
        }
        return generateItemID();
    }
    
    public static ArrayList<Item> ViewItemList()
    {
        ArrayList<Item> ItemList = new ArrayList<>();
        try
        {
            File MyItem = new File("Item.txt");
            Scanner fileScanner = new Scanner(MyItem);
            ItemList.clear();
            while (fileScanner.hasNextLine())
            {
                String Line = fileScanner.nextLine();
                String[] part = Line.split("\\|");

                Item TmpItem = new Item(part[0], part[1], part[2], part[3], part[4], part[5], part[6]);
                if(TmpItem.Status.equalsIgnoreCase("Existed"))
                {
                    ItemList.add(TmpItem);
                }
            }
            fileScanner.close();
        }
        catch (IOException Ex)
        {
            System.out.println("File Error");
        }

        System.out.println("List of Item:");
        System.out.println("===================");

        for (Item item : ItemList)
        {
            System.out.println(item.getItemID() + "|" + item.getName() + "|" + item.getPrice() + "|" + item.getQuantity() + "|" + item.getROL() + "|" + item.getDesc() + "|" +item.getStatus());
        }

        System.out.println("===================");
        return ItemList;
    }
    
    
//    public static void ViewItemList()
//{
//    ArrayList<Item> ItemList = new ArrayList<>();
//    try
//    {
//        File MyItem = new File("Item.txt");
//        Scanner fileScanner = new Scanner(MyItem);
//        ItemList.clear();
//        while (fileScanner.hasNextLine())
//        {
//            String Line = fileScanner.nextLine();
//            String[] part = Line.split("\\|");
//
//            if(!("Deleted".equals(part[part.length - 1])))
//                {
//            System.out.println(Line);  // TEMPORARY Print solution, cuz QJ code line 116 cant run
//                }
//        }
//    }
//    catch (IOException Ex)
//    {
//        System.out.println("File Error");
//    }
//}
    
    public static void DeleteItemID() {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Enter ItemID (e.g. IT00 - the number) of the item you want to modify:");
        String itemID = Sc.next();

        if (!itemID.matches("^IT\\d{3}$")) {
            System.out.println("Invalid Item ID format. Please use the format IT00.");
            return;
        }

        String status = "Deleted"; 
        System.out.println("Do you want to save these changes?(Y/N)");
        String option=Sc.next();

        if(!option.equalsIgnoreCase("Y"))
        {
            System.out.println("Change were not saved.");
            return;
        }

        String tempFile = "temp.txt";
        String filepath = "Item.txt";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);

        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            Scanner x = new Scanner(new File(filepath));

            while (x.hasNextLine()) 
            {
                String line = x.nextLine();
                String[] part = line.split("\\|");

                if (part.length == 7 && part[0].equals(itemID)) 
                {
                    pw.println(itemID + "|" + part[1] + "|" + part[2] + "|" + part[3] + "|" + part[4] + "|" + part[5] + "|" + status );
                } else 
                {
                    pw.println(line);
                }
            }

            x.close();
            pw.flush();
            pw.close();

            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(dump);

            System.out.println("Item with ItemID " + itemID + " has been marked as Deleted.");
        } catch (IOException Ex) {
            System.out.println("Error while marking item as Deleted: " + Ex.getMessage());
        }
    }

    public static boolean detectRol(String itemID) {
        boolean status = false;

         ArrayList<Item> ItemList = new ArrayList<>();
         try{
            File MyItem = new File("Item.txt");
            Scanner fileScanner = new Scanner(MyItem);
            ItemList.clear();
            while (fileScanner.hasNextLine())
            {
                String Line = fileScanner.nextLine();
                String[] part = Line.split("\\|");

                Item TmpItem = new Item(part[0], part[1], part[2], part[3], part[4], part[5], part[6]);
                ItemList.add(TmpItem);
            }
            fileScanner.close();

            for(Item item:ItemList)
            {
                if(item.getItemID().equalsIgnoreCase(itemID))
                {
                    int quantity=Integer.parseInt( item.getQuantity());
                    int rol=Integer.parseInt(item.getROL());

                    if(rol > quantity)
                    {
                    System.out.println("Item with ItemID:"+" "+ itemID+" "+ "has low stock" +","+"Quantity:"+ quantity);
                    status = true;
                    }
                    else if(rol < quantity)
                    {
                    System.out.println("Item with ItemID:"+" "+ itemID+" "+ "has sufficient stock" +","+"Quantity:"+ quantity);
                    status = false;            
                    }
                    break;
                }
            }
        }
        catch (IOException Ex)
        {
            System.out.println("File Error");
        }
            return status;

    }

    public void WriteFile()
            {
                File Finput = new File("Item.txt");
                try
                {
                    FileWriter fw = new FileWriter(Finput, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw);
                    String line = ItemID + "|" + Name + "|" + Price + "|" + Quantity + "|" + ROL + "|" + Desc + "|" + Status+ "\n";
                    pw.write(line);
                    pw.close();
                }
                catch (IOException Ex)
                {
                    System.out.println("File Error");
                }
            }

    public static void modifyItem() {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Enter ItemID (e.g. IT00 - the number) of the item you want to modify:");
        String itemID = Sc.next();
        String status="Existed";


        if (!itemID.matches("^IT\\d{3}$")) {
            System.out.println("Invalid Item ID format. Please use the format IT00.");
            return;
        }
        System.out.println("Enter New Item Name:");
        String newName = Sc.next();
        System.out.println("Enter New Item Price:");
        String newPrice = Sc.next();
        System.out.println("Enter New Item Quantity:");
        String newQuantity = Sc.next();
        System.out.println("Enter New Item ROL:");
        String newRoL = Sc.next();
        System.out.println("Enter New Item Desc:");
        String newDesc = Sc.next();

        System.out.println("Do you want to save these changes?(Y/N)");
        String option=Sc.next();

        if(!option.equalsIgnoreCase("Y"))
        {
            System.out.println("Change were not saved.");
            return;
        }

        String tempFile = "temp.txt";
        String filepath = "Item.txt";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);

        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            Scanner x = new Scanner(new File(filepath));

            while (x.hasNextLine()) 
            {
                String line = x.nextLine();
                String[] part = line.split("\\|");

                if (part.length == 7 && part[0].equals(itemID)) 
                {
                    pw.println(itemID + "|" + newName + "|" + newPrice + "|" + newQuantity + "|" + newRoL + "|" + newDesc + "|" + status );
                } else 
                {
                    pw.println(line);
                }
            }

            x.close();
            pw.flush();
            pw.close();

            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(dump);

            System.out.println("Item modified successfully.");
        } catch (IOException Ex) {
            System.out.println("Error while modifying item: " + Ex.getMessage());
        }
    }
}