/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBClasses;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HASANKA
 */
public class DB_query_users {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public DB_query_users() {
        java_connect c = java_connect.getInstance();
        conn = c.DBConnection();
    }

  
    public boolean insertIntoUser(String uname, String pass, int role) {
        String HashPass = "";
        try {
            HashPass = PasswordHash.createHash(pass);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DB_query.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(DB_query.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "insert into user (user, password, role)values ('" + uname + "', '" + HashPass + "', '"+role+"')";
        try {
            
            pst = conn.prepareStatement(sql);
            pst.execute();
            return true;
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

        return false;
    }
    
    public boolean searchUserName(String uname) {
       
        String sql = "SELECT user FROM user WHERE user = '"+uname+"'";
        try {
            
            pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            return pst.executeQuery().first();

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

        return false;
    }
    
    public Vector searchUser(String uname) {
       Vector v = new Vector();
        String sql = "SELECT user, role, id FROM user WHERE user = '"+uname+"'";
        try {
            
            pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs2 = pst.executeQuery();
            if (rs2.first()) {
                v.add(rs2.getString(1));
                v.add(rs2.getString(2));
                v.add(rs2.getString(3));
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

        return v;
    }
    
    public boolean updateUser(String uname, String pass, int role, String id) {
        String HashPass = "";
        try {
            HashPass = PasswordHash.createHash(pass);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DB_query.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(DB_query.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "UPDATE user set user='" + uname + "', password='" + HashPass + "', role='"+role+"' WHERE id='"+id+"'";
        try {
            
            pst = conn.prepareStatement(sql);
            pst.execute();
            return true;
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

        return false;
    }
    
    public boolean deleteUser(String uname, int role, String id) {
        
        String sql = "DELETE FROM user WHERE user='" + uname + "' and role='"+role+"' and id='"+id+"'";
        System.out.println(sql);
        try {
            
            pst = conn.prepareStatement(sql);
            pst.execute();
            return true;
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

        return false;
    }
    
    
    
   


}
