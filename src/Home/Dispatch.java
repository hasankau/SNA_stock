/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Home;

import DBClasses.DB_Query_Release;
import DBClasses.DB_query;
import DBClasses.java_connect;
import UIDesign.PanelFrame;
import UIDesign.Theme;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author HASANKA
 */
public class Dispatch extends PanelFrame {

//    DBClasses.DB_query_stock db = new DB_query_stock();
    Home home = null;
    DefaultTableModel dtm;
    DB_query db = new DB_query();
    //DB

    /**
     * Creates new form Inventory
     */
    public Dispatch() {
        initComponents();
        autoId();
        tb.update_table("select item_code, description from item", jTable1);
        updateTable();
    }

    public Dispatch(Home home) {
        this();
        this.home = home;
        price = price.setScale(2, RoundingMode.HALF_UP);
        q = q.setScale(2, RoundingMode.HALF_UP);
        ft = ft.setScale(2, RoundingMode.HALF_UP);
        payed = payed.setScale(2, RoundingMode.HALF_UP);

    }

    /**
     * Design methods
     *
     * @param bt
     */
    /**
     * Select from table
     *
     */
    SimpleDateFormat sdf2 = new SimpleDateFormat("YYYY/MM/dd");

    private void selectItemStock() {
        try {
            int row = jTable1.getSelectedRow();
            item_code.setText(jTable1.getModel().getValueAt(row, 0).toString().trim());
            desc.setText(jTable1.getModel().getValueAt(row, 1).toString().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void clearFields() {
        item_code.setText("");
    }

    private void autoId() {
        DB_Query_Release d = new DB_Query_Release();
        String id = d.autoId("despatch_note");
        String sdnno = "SDN" + id;
        sdn.setText(sdnno);
    }
    
    private void autoNewId() {
        DB_Query_Release d = new DB_Query_Release();
        int id = Integer.parseInt(d.autoId("despatch_note"))+1;
        String sdnno = "SDN" + id;
        sdn.setText(sdnno);
    }
    
    private void updateTable(){
        tb.update_table("SELECT\n"
                + "\n"
                + "     item.`item_code` AS 'Item Code',\n"
                + "     item.`description` AS Description,\n"
                + "  despatch_items.`qty` AS 'Released Qty'\n"
                + "FROM\n"
                + "     `despatch_note` despatch_note INNER JOIN `despatch_items` despatch_items ON despatch_note.`id` = despatch_items.`despatch_note_id`\n"
                + "     INNER JOIN `item` item ON despatch_items.`item_id` = item.`id`\n"
                + "WHERE\n"
                + "     sdn_no = '" + sdn.getText().trim() + "'", jTable3);
    }

//    private void addItem() {
//        if (!item_code.getText().isEmpty() & !qty.getText().isEmpty()) {
//            String icode = item_code.getText().trim();
//            String des = desc.getText().trim();
//            String qt = qty.getText().trim();
//            dtm = (DefaultTableModel) jTable3.getModel();
//            double aqd = Double.parseDouble(qt);
//
//            String qs = db.hasStock(icode);
//            double qd = Double.parseDouble(qs);
//
//            if (qd >= aqd) {
//
//                boolean b = false;
//                int id = 0;
//                check:
//                if (dtm.getRowCount() > 0) {
//                    for (int i = 0; i < dtm.getRowCount(); i++) {
//                        if (jTable3.getValueAt(i, 0).toString().equals(icode)) {
//                            id = i;
//                            b = true;
//                            break;
//                        }
//                    }
//                }
//
//                Vector v = new Vector();
//                v.add(icode);
//                v.add(des);
//                v.add(qt);
//
//                if (b) {
//                    BigDecimal qa = new BigDecimal(qty.getText())
//                            .add(new BigDecimal(dtm.getValueAt(id, 2).toString()));
//
//                    dtm.setValueAt(qa.toPlainString(), id, 2);
//                    jTable3.setSelectionBackground(Theme.btColorOrange);
//                    jTable3.setRowSelectionInterval(id, id);
//                } else {
//                    dtm.addRow(v);
//                    jTable3.setSelectionBackground(Theme.navColorSelect);
//                    jTable3.setRowSelectionInterval(jTable3.getRowCount() - 1, jTable3.getRowCount() - 1);
//                }
//
//            } else {
//                JOptionPane.showMessageDialog(null, "The quantity is not enough", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "No item selected", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }

    private void print() {
        try {
            String sdno = sdn.getText();
            InputStream is = new FileInputStream(new File("src/dispatch.jrxml"));

            JasperReport jr = JasperCompileManager.compileReport(is);
            Map m = new HashMap();
            m.put("sdn", sdno);
            JasperPrint jp = JasperFillManager.fillReport(jr, m, java_connect.ConnecrDb());
            //JasperPrintManager.printReport(jp, true);
            JasperViewer.viewReport(jp, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private boolean save() {
//        String sdnn = sdn.getText().trim();
//        Date date = dateChooserCombo1.getSelectedDate().getTime();
//
//        String da = sdf2.format(date);
//        boolean b = false;
//        home.setProcess("Saving stock dispatch note");
//        b = db.insertIntoSdn(sdnn, da);
//        if (b) {
//            for (int i = 0; i < jTable3.getRowCount(); i++) {
//                String icode = jTable3.getValueAt(i, 0).toString();
//                String qt = jTable3.getValueAt(i, 2).toString();
//
//                b = db.insertIntoSdnItems(sdnn, icode, qt);
//                if (b) {
//                    b = db.updateStock(icode, qt);
//                }
//
//            }
//
//        } else {
//            home.setProcess("Error occured");
//            JOptionPane.showMessageDialog(null, "Could not be saved", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//        return b;
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        desc = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        item_code = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        manu = new javax.swing.JComboBox();
        qty = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        sdn = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel9 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();

        jPanel2.setMinimumSize(new java.awt.Dimension(0, 185));
        jPanel2.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Item Code");

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add Item");
        jButton1.setContentAreaFilled(false);
        jButton1.setOpaque(true);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 153, 153));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Delete");
        jButton2.setContentAreaFilled(false);
        jButton2.setOpaque(true);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 153, 153));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Update");
        jButton3.setContentAreaFilled(false);
        jButton3.setOpaque(true);
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Qty ");

        jButton6.setBackground(new java.awt.Color(255, 153, 0));
        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Print");
        jButton6.setContentAreaFilled(false);
        jButton6.setOpaque(true);
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton6MouseExited(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        desc.setColumns(20);
        desc.setRows(5);
        jScrollPane2.setViewportView(desc);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Description");

        jButton8.setBackground(new java.awt.Color(0, 153, 153));
        jButton8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Delete All");
        jButton8.setContentAreaFilled(false);
        jButton8.setOpaque(true);
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton8MouseExited(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        item_code.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_codeActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Manufacturer");

        manu.setEditable(true);
        manu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manuActionPerformed(evt);
            }
        });

        qty.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        qty.setText("0");
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                qtyKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(manu, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(item_code, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(299, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(item_code, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(manu, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46))))))
        );

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(51, 51, 51));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/white/SEARCH_16x16-32.png"))); // NOI18N
        jButton5.setContentAreaFilled(false);
        jButton5.setOpaque(true);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 153, 153));
        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Reset");
        jButton4.setContentAreaFilled(false);
        jButton4.setMinimumSize(new java.awt.Dimension(50, 30));
        jButton4.setOpaque(true);
        jButton4.setPreferredSize(new java.awt.Dimension(50, 30));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton4MouseExited(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        sdn.setEditable(false);
        sdn.setMinimumSize(new java.awt.Dimension(50, 30));
        sdn.setPreferredSize(new java.awt.Dimension(50, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("SDN No");

        jScrollPane1.setMinimumSize(new java.awt.Dimension(23, 168));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 168));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setRowHeight(20);
        jTable1.setSelectionBackground(new java.awt.Color(0, 153, 153));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jScrollPane4.setMinimumSize(new java.awt.Dimension(23, 168));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(452, 168));

        jTable3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Itemcode", "Description", "Released Qty"
            }
        ));
        jTable3.setRowHeight(20);
        jTable3.setSelectionBackground(new java.awt.Color(0, 153, 153));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable3);

        dateChooserCombo1.setCurrentView(new datechooser.view.appearance.AppearancesList("Light",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    dateChooserCombo1.setFormat(2);

    jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabel9.setText("Date");

    jButton7.setBackground(new java.awt.Color(0, 153, 153));
    jButton7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButton7.setForeground(new java.awt.Color(255, 255, 255));
    jButton7.setText("New SDN");
    jButton7.setContentAreaFilled(false);
    jButton7.setMinimumSize(new java.awt.Dimension(50, 30));
    jButton7.setOpaque(true);
    jButton7.setPreferredSize(new java.awt.Dimension(50, 30));
    jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            jButton7MouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            jButton7MouseExited(evt);
        }
    });
    jButton7.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton7ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jButton5)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(sdn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE))))
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(10, 10, 10)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jTextField1)
                .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sdn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(dateChooserCombo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(10, 10, 10)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(12, 12, 12))
    );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        setButtonOver(jButton1, Theme.btColorMouseOver);
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        setButtonOver(jButton2, Theme.btColorMouseOver);
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
        setButtonOver(jButton3, Theme.btColorMouseOver);
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        setButtonExit(jButton1, Theme.navColorSelect);
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        setButtonExit(jButton2, Theme.navColorSelect);
    }//GEN-LAST:event_jButton2MouseExited

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        setButtonExit(jButton3, Theme.navColorSelect);
    }//GEN-LAST:event_jButton3MouseExited

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String key = "'" + jTextField1.getText().trim() + "%'";
        tb.update_table("select item_code,description from item where item_code like " + key + " or description like " + key + " or manufacturer like " + key, jTable1);

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        String key = "'" + jTextField1.getText().trim() + "%'";
        tb.update_table("select item_code,description from item where item_code like " + key + " or description like " + key + " or manufacturer like " + key, jTable1);
    }//GEN-LAST:event_jTextField1ActionPerformed
    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (item_code.getText() != null) {
            String man = "";
            if (manu.getSelectedItem()!=null) {
                man=manu.getSelectedItem().toString().trim();
            }
            String icode = item_code.getText();
            String des = desc.getText(),
                    sdnno = sdn.getText().trim(),
                    date = sdf2.format(dateChooserCombo1.getSelectedDate().getTime());

            
            
            String qt = qty.getText();
            double q = Double.parseDouble(qt);

            if (q >= 1) {
                home.setProcess("Saving item");
                boolean b = false;
                boolean b2 = false;

                if (!db.sdnExists(sdnno)) {
                    db.insertIntoSdn(sdnno, date);
                }
                if (!db.itemExists(icode)) {
                    b = db.insertIntoItem(icode, des, man, qt);

                }else{
                    db.updateItemStock(icode, qt);
                }if (!db.sdnItemExists(sdnno, icode)) {
                    b = db.insertIntoSdnItems(sdnno, icode, qt);
                    
                } else {
                    b = db.updateDispatchStock(sdnno, icode, qt);
                }

                tb.update_table("select item_code, description from item", jTable1);
                updateTable();

                if (b || b2) {
                    home.setProcess("Success");
                    JOptionPane.showMessageDialog(null, "Item Saved");
                } else {
                    home.setProcess("Error Occured");
                    JOptionPane.showMessageDialog(null, "Could not be saved!", "Error Occured", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                home.setProcess("Error Occured");
                JOptionPane.showMessageDialog(null, "Wrong value for quantity! Quantity cannot be minus", "Error Occured", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseEntered
        setButtonOver(jButton4, Theme.btColorMouseOver);
    }//GEN-LAST:event_jButton4MouseEntered

    private void jButton4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseExited
        setButtonExit(jButton4, Theme.navColorSelect);
    }//GEN-LAST:event_jButton4MouseExited

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        autoId();
        clearFields();
        updateTable();
        home.setProcess("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
        setButtonOver(jButton6, Theme.btColorOrange);
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseExited
        setButtonExit(jButton6, Theme.btColorOrangeExit);
    }//GEN-LAST:event_jButton6MouseExited

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (item_code.getText() != null) {
            String man = "";
            if (manu.getSelectedItem()!=null) {
                man=manu.getSelectedItem().toString().trim();
            }
            String icode = item_code.getText();
            String des = desc.getText(),
                    sdnno = sdn.getText().trim();
            
            
            String qt = qty.getText();
            double q = Double.parseDouble(qt);

            if (q >= 1) {
                home.setProcess("Saving item");
                boolean b = false;
                boolean b2 = false;

                
                if (db.itemExists(icode)) {
                    b = db.UpdateItem(icode, des, man, qt);

                }if (db.sdnItemExists(sdnno, icode)) {
                    b2 = db.updateDispatchQty(sdnno, icode, qt);
                }

                tb.update_table("select item_code, description from item", jTable1);
                updateTable();

                if (b || b2) {
                    home.setProcess("Success");
                    JOptionPane.showMessageDialog(null, "Item Updated");
                } else {
                    home.setProcess("Error Occured");
                    JOptionPane.showMessageDialog(null, "Could not be updated!", "Error Occured", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                home.setProcess("Error Occured");
                JOptionPane.showMessageDialog(null, "Wrong value for quantity! Quantity cannot be minus", "Error Occured", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
            print();
            tb.update_table("select item_code, description from item", jTable1);
            home.setProcess("Note saved");
            
            JOptionPane.showMessageDialog(null, "Successfull");
            dtm = (DefaultTableModel) jTable3.getModel();
            dtm.setRowCount(0);
            db.finishSdn(sdn.getText().trim());
            autoNewId();
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        selectItemStock();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dtm = (DefaultTableModel) jTable3.getModel();
        if (jTable3.getSelectedRows().length > 0) {

            int i = JOptionPane.showConfirmDialog(null, "Remove item?");
            if (i == JOptionPane.YES_OPTION) {
                String ic = jTable3.getValueAt(jTable3.getSelectedRow(), 0).toString();
                db.deleteFromDispatchItems(ic, sdn.getText().trim());
                updateTable();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No row selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    BigDecimal price = new BigDecimal("0.0");
    BigDecimal q = new BigDecimal("0.0");
    BigDecimal ft = new BigDecimal("0.0");
    BigDecimal payed = new BigDecimal(BigInteger.ONE);
    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        String key = "'" + jTextField1.getText().trim() + "%'";
        tb.update_table("select item_code,description from item where item_code like " + key + " or description like " + key + " or manufacturer like " + key, jTable1);

    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseEntered
        setButtonOver(jButton8, Theme.btColorMouseOver);
    }//GEN-LAST:event_jButton8MouseEntered

    private void jButton8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseExited
        setButtonExit(jButton8, Theme.navColorSelect);
    }//GEN-LAST:event_jButton8MouseExited

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        dtm = (DefaultTableModel) jTable3.getModel();
        if (jTable3.getSelectedRows().length > 0) {

            int i = JOptionPane.showConfirmDialog(null, "Remove items?");
            if (i == JOptionPane.YES_OPTION) {

                db.deleteAllFromDispatchItems();
                updateTable();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No rows exist", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void item_codeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_codeActionPerformed
        if (item_code.getText() != null) {
            Vector v = db.loadItem(item_code.getText().trim());
            if (v.size() > 0) {

                item_code.setText(v.get(0).toString());
                desc.setText(v.get(1).toString());
                manu.setSelectedItem(v.get(2).toString());
                qty.grabFocus();
            }
        }
    }//GEN-LAST:event_item_codeActionPerformed

    private void qtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyTyped
        int c = evt.getKeyChar();
        if (!(Character.isDigit(c) || evt.getKeyCode() == KeyEvent.VK_PERIOD)) {
            evt.consume();
        }
    }//GEN-LAST:event_qtyKeyTyped

    private void manuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_manuActionPerformed

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7MouseEntered

    private void jButton7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7MouseExited

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        autoNewId();
        dtm = (DefaultTableModel) jTable3.getModel();
        dtm.setRowCount(0);
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private javax.swing.JTextArea desc;
    private javax.swing.JTextField item_code;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox manu;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField sdn;
    // End of variables declaration//GEN-END:variables

}
