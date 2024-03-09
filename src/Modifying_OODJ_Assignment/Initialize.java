package Modifying_OODJ_Assignment;

import new_oodj_assign.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author LeeWenHan TP070146
 */

public class Initialize{   // To Initialize User list for first time user, in order to log in
    

    public static void registerUser()
    {
              
        File userData = new File("userData.txt");
        try
        {

            FileOutputStream file = new FileOutputStream(userData);
            ObjectOutputStream out = new ObjectOutputStream(file);

            Admin user = new Admin();
            user.setUID("U001");
            user.setPassword("123"); 
            user.setRole("Admin");

            Admin user1 = new Admin();
            user1.setUID("U002");
            user1.setPassword("123"); 
            user1.setRole("PM");
            
            
            Admin user2 = new Admin();
            user2.setUID("U003");
            user2.setPassword("123"); 
            user2.setRole("SM");            
                        System.out.println("2");
            out.writeObject(user);
            System.out.println("3");
            out.writeObject(user1);
            out.writeObject(user2);
System.out.println("1");
            // @@@@@@@@@@@@ 
            // Fun GUI 
            
            String str = "Initializing.";
            try { for(int i = 0;i<5;i++){System.out.print(str);Thread.sleep(500);str=".";}
            } catch (InterruptedException e) {Thread.currentThread().interrupt();}
            
            //GUI end
            //@@@@@@@@@@@@@
            User.Uctr++;
            file.close();
            out.close();
        }
        
        catch(IOException ex){System.out.println("Error catched");}
    }
    
}
