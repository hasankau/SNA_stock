/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template;

import DBClasses.java_connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author HASANKA
 */
public class AutoIdGeneratable {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public String autoId(String table) {
        java_connect c = java_connect.getInstance();
        conn = c.DBConnection();
        String sql = "select max(id) from " + table + " where status != 'finished'";
        try {

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs != null) {
                if (rs.first() && rs.getString(1) != null) {
                    return rs.getString(1);

                } else {
                    String sql2 = "select count(id) from " + table + "";
                    pst = conn.prepareStatement(sql2);
                    ResultSet rs2 = pst.executeQuery();
                    if (rs2.first()) {
                        int i = Integer.parseInt(rs2.getString(1));
                        if (i > 0) {
                            String sql3 = "select max(id) from " + table + "";
                            pst = conn.prepareStatement(sql3);
                            ResultSet rs3 = pst.executeQuery();
                            if (rs3.first()) {
                                int i2 = Integer.parseInt(rs3.getString(1)) + 1;
                                return i2 + "";
                            }

                        }
                    }
                }

            } else {

                return "1";
            }

        } catch (Exception e) {
            e.printStackTrace();
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

        return "1";
    }

}
