package DBClasses;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.JTable;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author VRG
 */
public class table_update {

    private static table_update instance = new table_update();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    private String sql;
    private javax.swing.JTable table;

    private table_update() {

        java_connect c = java_connect.getInstance();
        conn = c.DBConnection();
    }

    public static table_update getInstance() {
        return instance;
    }

    public void update_table(String sql, JTable table) {
        this.sql = sql;
        this.table = table;

        try {

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }
    }

}
