/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UIDesign;

import DBClasses.table_update;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author HASANKA
 */
public class MajorFrame extends JFrame{

    public static String WIN_NAME;
    public Theme theme = new Theme();
    public table_update tb = table_update.getInstance();
    
    public MajorFrame() throws HeadlessException {
        //setUndecorated(true);
        
//        setPreferredSize(UIDesign.ScreenRes.getSize());
//        setMaximumSize(UIDesign.ScreenRes.getSize());


    }
    
    public void setButtonOver(JButton bt, Color color) {
        bt.setBackground(color);
    }

    public void setButtonExit(JButton bt, Color color) {

        bt.setBackground(color);
    }
    
    public void setWindowUndecorated(boolean b){
        setUndecorated(b);
    }
    
}
