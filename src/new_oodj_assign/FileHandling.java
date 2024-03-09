package new_oodj_assign;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author LeeWenHan TP070146
 */

public class FileHandling {
    
    String FileName;
    
    public FileHandling(String txtFileName){FileName = txtFileName;}  

    
    
    public synchronized void Write2File(Object obj){

        try{
            File FInput = new File(FileName);
            FileWriter fw=new FileWriter(FInput,true);
            BufferedWriter bw=new BufferedWriter(fw);
            PrintWriter pw=new PrintWriter(bw);

            
            pw.write(obj.toString() + "\n");
            pw.close();//Once you close the final writer, all the writer will be close too
            
        }
        catch(IOException Ex){System.out.println("File Error");}

    }

    
    public boolean CheckExist(String IDtoBeChecked,int columnIndex){ //Havent tested
    
        File FInput = new File(FileName);
        
        boolean stat = false;
        int lastColumnIndex;
        
        try(Scanner fileScanner = new Scanner(FInput);)
        {
            while (fileScanner.hasNextLine())
            {
                String Line = fileScanner.nextLine();
                String[] part = Line.split("\\|");
                
                lastColumnIndex = part.length - 1;
                if(part[columnIndex].equalsIgnoreCase(IDtoBeChecked))
                {
                    if(part[lastColumnIndex].equalsIgnoreCase("Existed") || part[lastColumnIndex].equalsIgnoreCase("Pending"))
                    {
                        stat = true;
                    }
//                    else if (part[lastColumnIndex].equalsIgnoreCase("Deleted"))
//                    { 
//                        System.out.println("Selected Object -" + IDtoBeChecked + "- is Deleted."); 
//                        stat = false; 
//                    }
//                    else
//                    {
//                        System.out.println("Status Error in Database.");
//                        stat = false;
//                    }
                }

            }
        }
        catch(Exception E){}
                 
        return stat;
    
    }
    
    public boolean SaveConfirm()
    {
        String save_option;
        boolean SaveStatus = false;
        boolean ValidInput = false;
        
        
        try
        {
            Scanner sc2 = new Scanner(System.in);
            while (!ValidInput) 
            {            
                System.out.println("Save changes made? 'Y' for yes, 'N' for no.");
                System.out.println("Only key in Y or N!!!");
                save_option = sc2.next();

                if (save_option.equalsIgnoreCase("Y")) 
                {
                    ValidInput = true;
                    SaveStatus = true;
                }
                else if (save_option.equalsIgnoreCase("N")) 
                {
                    ValidInput = true;
                    SaveStatus = false;
                } 
                else 
                {
                    ValidInput = false;
                    System.out.println("Invalid input. Re-enter again.");
                }
            }
        }
        catch(Exception e){}
        return SaveStatus;
    }
    
    

}

