/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DBClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 *
 * @author HASANKA
 */
public class Backup {
    Home.Home home = null;
    public Backup(Home.Home home) {
        this.home=home;
    }
String time;
    
    public void runBackup(String time){
        this.time=time;
        try {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddhhmmss");
            String t = sdf.format(d);
            
            File bp = new File("src/backuploc.inf");
            Scanner sc = new Scanner(bp);
            String drive=sc.next();
            
            File f = new File(drive+":/backup");
            if (!f.exists()) {
                f.mkdir();
            }
            
            try {
                File file2 = new File("src/data.inf");
                Scanner sc2 = new Scanner(file2);
                String ab2=sc2.next();
                
                Runtime.getRuntime().exec("C:\\Program Files (x86)\\MySQL\\MySQL Server 5.5\\bin\\mysqldump.exe -uroot -p123 "+ab2+" -r "+drive+":/backup\\backup_"+time+"_"+t+".sql");
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            SwingWorker sw = new SwingWorker() {
                
                @Override
                protected Object doInBackground() throws Exception {
                    for(int i = 0;i< 50; i++){
                        try {
                            home.setProcess("Running database backup");
                            Thread.sleep(i/2);
                        } catch (Exception e) {
                            home.setProcess("Error, backup failed");
                            e.printStackTrace();
                        }
                    }
                    home.setProcess("Backup successfull");
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };
        
            sw.execute();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
