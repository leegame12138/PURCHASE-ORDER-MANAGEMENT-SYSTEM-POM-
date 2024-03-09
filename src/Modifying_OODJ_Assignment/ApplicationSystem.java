/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modifying_OODJ_Assignment;

import static Modifying_OODJ_Assignment.User.CurrentUID;
import static Modifying_OODJ_Assignment.User.Uctr;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

/**
 *
 * @author LeeWenHan TP070146
 */
public class ApplicationSystem implements java.io.Serializable{
    
    protected String UID;
    protected String password;
    protected String role;
    protected static final long serialVersionUID = 3746308932813848007L;
    private User[] userList = new User[50];
    
    public void UpdateLatestID() //for encrypted 6txt file
    {
        String[] strList;
        
        try(FileInputStream file = new FileInputStream("userData.txt");
            ObjectInputStream in = new ObjectInputStream(file);)
        {         
            
            while(true)
            {
                
                Admin user = new Admin();
                try{user = (Admin)in.readObject();}
                catch(ClassNotFoundException e){ in.close();break;}
                
                strList = user.UID.split("U");
                Uctr = Integer.parseInt(strList[1]);

            }
            
        }      
        catch(IOException ex){User.Uctr++;}
        
    }
    
        public boolean login(){
        
        boolean stat = false;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Login Page---");
        System.out.print("UID: ");
        this.UID = sc.next();
        System.out.print("Password: ");
        this.password = sc.next();
        System.out.print("\n");
        
        try(FileInputStream file = new FileInputStream("userData.txt");
            ObjectInputStream in = new ObjectInputStream(file);)
        {

            while(true)
            {
                Admin user = new Admin();
                try{user = (Admin)in.readObject();}
                catch(ClassNotFoundException e){break;}
                
                if (user!=null)
                {

                    if (this.UID.equals(user.UID))
                    {
                        if(this.password.equals(user.password)){stat=true;this.role = user.role;User.CurrentUID = user.UID;break;}
                    }
                    else{stat=false;}
                }
                else{stat=false;break;}
            }
        }
        catch(IOException ex){}
        
        return stat;}
    
        public String getRole(){return role;}

    
    
    
}
