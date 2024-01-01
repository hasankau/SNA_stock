/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UIDesign;

import DBClasses.table_update;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author HASANKA
 */
public class PanelFrame extends JPanel{
    public Theme theme = new Theme();
    public table_update tb = table_update.getInstance();
    public void setButtonOver(JButton bt, Color color) {
        bt.setBackground(color);
    }

    public void setButtonExit(JButton bt, Color color) {

        bt.setBackground(color);
    }
    
}
