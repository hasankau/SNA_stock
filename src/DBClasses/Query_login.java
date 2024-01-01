/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DBClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author HASANKA
 */
public class Query_login {
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public Query_login() {
        java_connect c = java_connect.getInstance();
        conn = c.DBConnection();
    }
    
    public String getUid(String name, String i){
        
        
        String sql = "SELECT id from user where user='"+name+"' and role = '"+i+"'";
        
        
        String item = "";
        try {
            
            pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            if (rs.first()) { 
                    return rs.getString(1);
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occured!"+e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
            }
        }
        
        return item;
    }
    
        public boolean loging(String userName, String password, int UserType) {
        if (!userName.equals("")) {
            if (!password.equals("")) {
                String sql = "";
                if (UserType == 0) {
                    sql = "select id,password from user where role=0 and user=?";
                } else if (UserType == 1) {
                    sql = "select id,password from user where role=1 and user=?";
                }
                try {
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, userName);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        String id = rs.getString("id");
                        String HashPwd = rs.getString("password");
                        rs.close();
                        pst.close();
                        if (PasswordHash.validatePassword(password, HashPwd)) {

                            return true;

                        } else {
                            JOptionPane.showMessageDialog(null, "Username or password incorrect", "Invallid login", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Username or password incorrect", "Invallid login", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please insert valid password");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please insert valid username");
        }

        return false;
    }
}
