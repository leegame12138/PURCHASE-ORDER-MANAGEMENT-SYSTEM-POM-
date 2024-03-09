
package Modifying_OODJ_Assignment;

import new_oodj_assign.*;

/**
 *
 * @author LeeWenHan TP070146
 */

public class FileHandlingMultithreading extends Thread{
    
    private FileHandling FileObj;
    
    FileHandlingMultithreading(FileHandling FileObj){this.FileObj =FileObj;}

    
    public void run(Object obj){
        
        FileObj.Write2File(obj);
        
    }
}
