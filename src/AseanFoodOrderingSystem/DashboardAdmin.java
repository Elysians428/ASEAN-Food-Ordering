/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package AseanFoodOrderingSystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
/**
 *
 * @author lenovo
 */
public class DashboardAdmin extends javax.swing.JFrame {

    /**
     * Creates new form DashboardAdmin
     */
    public DashboardAdmin() {
        initComponents();
        this.setLocationRelativeTo(null); // Membuat window muncul di tengah layar
        loadStatistik();                  // Ambil data card statistik
        loadTabelRiwayat();
    }
    private Connection konfKoneksi() {
        try {
            String url = "jdbc:mysql://localhost:3306/db_restoran_asean"; // Sesuaikan port xampp kamu jika bukan 3306
            String user = "root";
            String pass = "";
            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi Database Dashboard Gagal: " + e.getMessage());
            return null;
        }
    }
    public void loadStatistik() {
        try {
            Connection conn = konfKoneksi();
            if (conn == null) return;
            Statement stmt = conn.createStatement();

           
            ResultSet rsSatu = stmt.executeQuery("SELECT SUM(total_harga_rp) AS total FROM pesanan");
            if (rsSatu.next()) {
                lblTotalPendapatan.setText("Rp " + rsSatu.getInt("total"));
            }

       
            ResultSet rsDua = stmt.executeQuery("SELECT COUNT(*) AS total FROM pesanan");
            if (rsDua.next()) {
                lblTotalTransaksi.setText(String.valueOf(rsDua.getInt("total")));
            }

          
            ResultSet rsTiga = stmt.executeQuery("SELECT COUNT(*) AS total FROM makanan");
            if (rsTiga.next()) {
                lblTotalMenu.setText(String.valueOf(rsTiga.getInt("total")));
            }
            String queryStok = "SELECT m.nama_makanan, v.ukuran, v.stok FROM varian_porsi v "
                             + "JOIN makanan m ON v.id_makanan = m.id_makanan WHERE v.stok <= 5";
            ResultSet rsStok = stmt.executeQuery(queryStok);
            
            StringBuilder pesanStok = new StringBuilder();
            while (rsStok.next()) {
                pesanStok.append("- ")
                         .append(rsStok.getString("nama_makanan"))
                         .append(" (").append(rsStok.getString("ukuran")).append(")")
                         .append(" sisa ").append(rsStok.getInt("stok")).append(" porsi!\n");
            }
            
            
            if (pesanStok.length() > 0) {
                JOptionPane.showMessageDialog(this, 
                    "⚠️ Peringatan Stok Kritis:\n" + pesanStok.toString(), 
                    "Sistem Kontrol Stok", JOptionPane.WARNING_MESSAGE);
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat statistik: " + e.getMessage());
        }
    }
    public void loadTabelRiwayat() {
        DefaultTableModel model = (DefaultTableModel) tableRiwayat.getModel();
        model.setRowCount(0); // Bersihkan isi tabel sebelum memuat data baru

        try {
            Connection conn = konfKoneksi();
            if (conn == null) return;
            Statement stmt = conn.createStatement();
            
            // Query JOIN antara tabel pesanan, varian_porsi, dan makanan agar infonya lengkap
            String query = "SELECT p.id_pesanan, p.nama_pelanggan, p.tipe_pesanan, "
                         + "COALESCE(p.no_meja, '-') AS no_meja, m.nama_makanan, v.ukuran, "
                         + "p.total_harga_rp, p.waktu_pesan "
                         + "FROM pesanan p "
                         + "JOIN varian_porsi v ON p.id_varian = v.id_varian "
                         + "JOIN makanan m ON v.id_makanan = m.id_makanan "
                         + "ORDER BY p.id_pesanan DESC";
            
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                // Menggabungkan nama makanan dan varian ukuran porsi (contoh: Rendang - Large)
                String menuDanVarian = rs.getString("nama_makanan") + " (" + rs.getString("ukuran") + ")";
                
                model.addRow(new Object[]{
                    rs.getInt("id_pesanan"),
                    rs.getString("nama_pelanggan"),
                    rs.getString("tipe_pesanan"),
                    rs.getString("no_meja"),
                    menuDanVarian,
                    "Rp " + rs.getInt("total_harga_rp"),
                    rs.getTimestamp("waktu_pesan")
                });
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat tabel riwayat: " + e.getMessage());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTotalPendapatan = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblTotalTransaksi = new javax.swing.JLabel();
        lblTotalMenu = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableRiwayat = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Dashboard Administrasi Restoran");

        jLabel2.setText("Total Pendapatan:");

        lblTotalPendapatan.setText("RP.0");

        jLabel3.setText("Jumlah Transaksi:");

        lblTotalTransaksi.setText("0");

        lblTotalMenu.setText("Total Menu ASEAN:");

        tableRiwayat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Pesanan", "Nama Pelanggan", "Tipe Pesanan", "No meja", "Menu & Varian", "Total Harga", "Waktu Pesanan"
            }
        ));
        jScrollPane1.setViewportView(tableRiwayat);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTotalPendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTotalTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTotalMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTotalPendapatan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblTotalTransaksi)
                    .addComponent(lblTotalMenu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotalMenu;
    private javax.swing.JLabel lblTotalPendapatan;
    private javax.swing.JLabel lblTotalTransaksi;
    private javax.swing.JTable tableRiwayat;
    // End of variables declaration//GEN-END:variables
}
