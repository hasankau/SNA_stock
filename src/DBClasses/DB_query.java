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
public class DB_query {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public DB_query() {
        java_connect c = java_connect.getInstance();
        conn = c.DBConnection();
    }

  
    public Vector loadBrands(){
        String sql = "SELECT brand from item";
        Vector v = new Vector();
        try {
            
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            v.add("");
            while (rs.next()) { 
                v.add(rs.getString(1));
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
    
    public Vector loadItem(String barc){
        String sql = "SELECT item_code, description, manufacturer from item where item_code = '"+barc+"'";
        Vector v = new Vector();
        try {
            
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { 
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
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
    
    public Vector loadManufacturers(){
        String sql = "SELECT distinct(manufacturer) from item";
        Vector v = new Vector();
        try {
            
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            v.add("");
            while (rs.next()) { 
                v.add(rs.getString(1));
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
    
    
    
    public String getItemCode(){
        String sql = "SELECT max(item_code)+1 from item";
        
        
        String item = "1";
        try {
            
            pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            if (rs.first()) { 
                item = rs.getString(1);
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
    
    public boolean itemExists(String icode){
        String sql = "SELECT * from item where item_code ='"+icode+"'";
        
        
        try {
            
            pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            if (rs.first()) { 
                return true;
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

        return false;
    }
    
    public String getNextUnitId(){
        String sql = "SELECT unit from unit_fixed where id=(select max(id) from unit_fixed)";
        
        
        String item = "1";
        try {
            
            pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            if (rs.first()) { 
                if (rs.getString(1)!=null) {
                    item = rs.getString(1);
                }else{
                    return "1";
                }
                
            }
        } catch (Exception e) {
            System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
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
 
   
    
    public boolean insertIntoItem(String icode, String desc, String manufacturer, String qty) {
        String sql = "insert into item(item_code, description, manufacturer, Released_Qty, Returned_Qty) values('" + icode + "', '" + desc + "', '" + manufacturer + "', '" + qty + "', '0')";
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
                JOptionPane.showMessageDialog(null, "An error occured!"+e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return false;
    }
    
    public boolean UpdateItem(String icode, String desc, String manufacturer, String qty) {
        
        String sql = "update item set description = '" + desc + "', manufacturer = '" + manufacturer + "', Released_Qty ='"+qty+"' where item_code='"+icode+"'";
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
    
    public boolean UpdateItem2(String icode, String desc, String manufacturer) {
        
        String sql = "update item set description = '" + desc + "', manufacturer = '" + manufacturer + "' where item_code='"+icode+"'";
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
    
    
    public boolean deleteFromItem(String id) {
        String sql = "delete from item where item_code='"+id+"'";
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
    
    public boolean deleteFromDispatchItems(String icode, String sdnn) {
        String sql = "delete from despatch_items where item_id =(select id from item where item_code='"+icode+"') and despatch_note_id = (select id from despatch_note where sdn_no='"+sdnn+"')";
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
    
    public boolean deleteAllFromDispatchItems() {
        String sql = "delete from despatch_items";
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
    
    public boolean insertIntoStock(String icode, String qty) {
        String sql = "update item set qty = qty+"+qty+" where item_code ='" + icode + "'";
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
    
    
    public boolean insertIntoSdn(String sdn, String date) {
        String sql = "insert into despatch_note(sdn_no, date, status) values('" + sdn + "', '" + date + "', 'pending')";
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
    
    public boolean finishSdn(String sdn) {
        String sql = "update despatch_note set status = 'finished'";
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
        String sql = " update despatch_items set qty = greatest(qty+'" + qty + "', 0) where item_id=(select id from item where item_code='" + icode + "') and despatch_note_id=(select id from despatch_note where sdn_no='" + sdnn + "')";
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
    
    public boolean updateItemStock(String icode, String qty) {
        String sql = " update item set Released_Qty = '" + qty + "', Returned_Qty ='0' where item_code='" + icode + "'";
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
    
    public boolean updateDispatchQty(String sdnn, String icode, String qty) {
        String sql = " update despatch_items set qty ='" + qty + "' where item_id=(select id from item where item_code='" + icode + "') and despatch_note_id=(select id from despatch_note where sdn_no='" + sdnn + "')";
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
    
    
    
    public boolean sdnExists(String sdnn){
        String sql = "SELECT * from despatch_note where sdn_no ='"+sdnn+"'";
        
        
        try {
            
            pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            if (rs.first()) { 
                return true;
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

        return false;
    }
    
   public boolean sdnItemExists(String sdnn, String item_code){
        String sql = "SELECT * from despatch_items where despatch_note_id =(select id from despatch_note where sdn_no = '"+sdnn+"' ) and item_id = (select id from item where item_code='"+item_code+"')";
        
        
        try {
            
            pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            if (rs.first()) { 
                return true;
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

        return false;
    }


}
