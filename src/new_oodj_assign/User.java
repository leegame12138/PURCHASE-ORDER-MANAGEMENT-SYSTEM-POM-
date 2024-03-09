/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package new_oodj_assign;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;


    class User implements java.io.Serializable{
    protected String UID;
    protected String password;
    protected String role;
    protected static String CurrentUID;
    protected static final long serialVersionUID = 3746308932813848007L;
    private User[] userList = new User[50];
    public static int Uctr;
    
//    public void Menu();
    
    public void printUserList()
    {

        try
        {         
            FileInputStream file = new FileInputStream("userData.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            
            while(true)
            {
                User temp = new User();
                try{temp = (User)in.readObject();}
                catch(ClassNotFoundException e){ in.close();break;}
               
                System.out.println(temp);      
                
                FileHandling f2 = new FileHandling("VerifyUserList.txt");
                FileHandlingMultithreading f3 = new FileHandlingMultithreading(f2);
                f3.run();
            }

        }
        catch(IOException ex){}
    }
    
    
    public   void UpdateLatestID() //for encrypted 6txt file
    {
        String[] strList;
        
        try(FileInputStream file = new FileInputStream("userData.txt");
            ObjectInputStream in = new ObjectInputStream(file);)
        {         
            
            while(true)
            {
                
                User temp = new User();
                try{temp = (User)in.readObject();}
                catch(ClassNotFoundException e){ in.close();break;}
                
                strList = temp.UID.split("U");
                Uctr = Integer.parseInt(strList[1]);

            }
            
        }      
        catch(IOException ex){Uctr++;}
        
    }
    
    public boolean login(){
        
        boolean stat = false;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- Login Page---");
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
                User temp = new User();
                try{temp = (User)in.readObject();}
                catch(ClassNotFoundException e){break;}
                
                if (temp!=null)
                {                System.out.println(temp);
                                System.out.println("DKAGDJHASGDBBBBBBBB");


                    if (this.UID.equals(temp.UID))
                    {
                        if(this.password.equals(temp.password)){stat=true;role = temp.role;CurrentUID = temp.UID;break;}
                    }
                    else{stat=false;}
                }
                else{stat=false;break;}
            }
        }
        catch(IOException ex){}
        
        return stat;}
    
    public String toString(){return this.UID + "|" + this.password + "|" + this.role; }

    public String getRole(){return role;}
    public String getUID(){return UID;}
    public String getPassword(){return password;}
    public void setRole(String r){role = r;}
    public void setUID(String un){UID = un;}
    public void setPassword(String p){password = p;}
    
    } //End class