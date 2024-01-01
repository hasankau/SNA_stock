/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBClasses;

import Template.AutoIdGeneratable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author HASANKA
 */
public class DB_Query_Release extends AutoIdGeneratable {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public DB_Query_Release() {
        java_connect c = java_connect.getInstance();
        conn = c.DBConnection();
    }

    public boolean insertIntoSdn(String sdn, String date) {
        String sql = "insert into despatch_note(sdn_no, date) values('" + sdn + "', '" + date + "')";
        try {

            pst = conn.prepareStatement(sql);
            pst.execute();

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occured!" + e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An error occured!" + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return false;
    }

    public boolean insertIntoSdnItems(String sdn, String icode, String qty) {
        String sql = "insert into despatch_items(despatch_note_id, item_id, qty) values((select id from despatch_note where sdn_no='" + sdn + "'), (select id from item where item_code='" + icode + "'), '" + qty + "')";
        try {

            pst = conn.prepareStatement(sql);
            pst.execute();

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occured!" + e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An error occured!" + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return false;
    }

    public boolean updateDispatchStock(String sdnn, String icode, String qty) {
        String sql = " update despatch_items set qty = qty+'" + qty + "' where item_id=(select id from item where item_code='" + icode + "') and despatch_note_id=(select id from despatch_note where sdn_no='" + sdnn + "')";
        try {

            pst = conn.prepareStatement(sql);
            pst.execute();

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occured!" + e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An error occured!" + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return false;
    }

    public String hasStock(String icode) {
        String qty = "0.0";
        String sql = " select qty from item where item_code='" + icode + "'";
        try {

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            if (rs.first()) {
                qty = rs.getString(1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occured!" + e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An error occured!" + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return qty;
    }

}
