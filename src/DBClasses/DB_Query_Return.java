/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBClasses;

import Data.Item;
import Template.AutoIdGeneratable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author HASANKA
 */
public class DB_Query_Return extends AutoIdGeneratable {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public DB_Query_Return() {
        java_connect c = java_connect.getInstance();
        conn = c.DBConnection();
    }

    public boolean insertIntoSrn(String srn, String sdn, String date) {
        String sql = "insert into stock_return_note(date, despatch_note_id, srn_no, status) values('" + date + "', (select id from despatch_note where sdn_no = '" + sdn + "'), '" + srn + "', 'pending')";
        
        try {

            pst = conn.prepareStatement(sql);
            pst.execute();

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occured! fgghhh" + e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An error occured! ggggggg" + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return false;
    }

    public boolean insertIntoSrnItems(String srn, String icode, String sdn, String qty) {
        
        String sql = "insert into stock_return_items(stock_return_note_id, item_id, qty) values((select id from stock_return_note where srn_no='" + srn + "'), (select id from item where item_code='" + icode + "'), GREATEST((select qty from despatch_items where despatch_note_id = (select id from despatch_note where sdn_no = '" + sdn + "') and item_id = (select id from item where item_code = '"+icode+"'))-'"+qty+"', 0))";
        System.out.println(sql);
        try {

            pst = conn.prepareStatement(sql);
            pst.execute();

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occured! rrrrrrrr" + e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An error occured! jjjj" + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return false;
    }
    
    public boolean updateSrnItems(String srn, String icode, String qty) {
        String sql = "update stock_return_items set qty = GREATEST(qty - '"+qty+"', 0) where stock_return_note_id=(select id from stock_return_note where srn_no ='" + srn + "') and item_id = (select id from item where item_code='" + icode + "')";
        
        try {

            pst = conn.prepareStatement(sql);
            pst.execute();

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occured! vvvvvvvv" + e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An error occured! mmmmm" + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return false;
    }
    
    public boolean insertIntoSrnDistItems(String srn, String icode, String qty) {
        String sql = "insert into item_dist( stock_return_note_id, qty, item_code) values((select id from stock_return_note where srn_no ='" + srn + "'), '" + qty + "', '" + icode + "')";
        try {

            pst = conn.prepareStatement(sql);
            pst.execute();

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occured! abcc" + e, "Error", JOptionPane.ERROR_MESSAGE);
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
    
    public boolean finishSrn(String srn) {
        String sql = "update stock_return_note set status = 'finished'";
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

    public boolean updateStock(String icode, String qty) {
        String sql = " update item set Returned_Qty = '" + qty + "' where item_code='" + icode + "'";
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
    
    public boolean updateStockQty(String icode, String qty) {
        String sql = " update item set Returned_Qty = (Returned_Qty+'" + qty + "') where item_code='" + icode + "'";
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
    
    public boolean deleteDistItem(String sid) {
        String sql = "delete from item_dist where id='" + sid + "'";
        System.out.println(sql);
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
    
    public boolean deleteSrnItem(String qty, String srn, String icode) {
        String sql = "update stock_return_items set qty=qty-'"+qty+"' where stock_return_note_id =(select id from stock_return_note where srn_no='" + srn + "') and item_id = (select id from item where item_code = '"+icode+"')";
        System.out.println(sql);
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

    public Vector getSDNItems(String sdnn) {
        Vector<Item> v = new Vector();
        String sql = "SELECT\n"
                + "\n"
                + "     item.`item_code` AS 'Item Code',\n"
                + "     item.`description` AS item_description,\n"
                + "  despatch_items.`qty` AS 'Released Qty'\n"
                + "FROM\n"
                + "     `despatch_note` despatch_note INNER JOIN `despatch_items` despatch_items ON despatch_note.`id` = despatch_items.`despatch_note_id`\n"
                + "     INNER JOIN `item` item ON despatch_items.`item_id` = item.`id`\n"
                + "WHERE\n"
                + "     sdn_no ='" + sdnn + "'";
        try {

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Item item = new Item();
                item.setItem_code(rs.getString(1));
                item.setDescription(rs.getString(2));
                item.setQty(rs.getString(3));

                v.add(item);
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

        return v;
    }

    public boolean srnExists(String srn){
        String sql = "SELECT * from stock_return_note where srn_no ='"+srn+"'";
        
        
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
    
    public boolean srnItemExists(String srn, String icode){
        String sql = "SELECT * from stock_return_items where stock_return_note_id = (select id from stock_return_note where srn_no = '"+srn+"') and item_id = (select id from item where item_code = '"+icode+"')";
        
        
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
    
    public String getSrnFromSdn(String sdn){
        String sql = "SELECT srn_no from stock_return_note where despatch_note_id = (select id from despatch_note where sdn_no = '"+sdn+"')";
        
        System.out.println(sql);
        try {
            
            pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            if (rs.first()) { 
                return rs.getString(1).trim();
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

        return "";
    }
    
}


