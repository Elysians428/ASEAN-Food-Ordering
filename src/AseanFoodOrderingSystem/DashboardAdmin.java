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
        btnKelolaStok = new javax.swing.JButton();

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

        btnKelolaStok.setText("Kelola stok");
        btnKelolaStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKelolaStokActionPerformed(evt);
            }
        });

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
                        .addComponent(jLabel1)
                        .addGap(45, 45, 45)
                        .addComponent(btnKelolaStok))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnKelolaStok)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTotalPendapatan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblTotalTransaksi)
                    .addComponent(lblTotalMenu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
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

    private void btnKelolaStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKelolaStokActionPerformed
        // TODO add your handling code here:
  javax.swing.JPanel panelPopup = new javax.swing.JPanel(new java.awt.BorderLayout(5, 10));
    
    // 2. Membuat dropdown (JComboBox) untuk memilih ukuran
    String[] listUkuran = {"Small", "Medium", "Large"};
    javax.swing.JComboBox<String> comboUkuran = new javax.swing.JComboBox<>(listUkuran);
    
    javax.swing.JPanel panelAtas = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
    panelAtas.add(new javax.swing.JLabel("Pilih Ukuran (Size): "));
    panelAtas.add(comboUkuran);
    panelPopup.add(panelAtas, java.awt.BorderLayout.NORTH);

    // 3. Kolom No., Nama Makanan, Stok Saat Ini, dan ID Asli (Tersembunyi)
    String[] judulKolom = {"No.", "Nama Makanan", "Stok Saat Ini", "ID Asli"};
    DefaultTableModel modelStok = new DefaultTableModel(null, judulKolom) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    javax.swing.JTable tabelPopup = new javax.swing.JTable(modelStok);
    
    // Menyembunyikan kolom "ID Asli" (indeks ke-3) agar rapi
    tabelPopup.getColumnModel().getColumn(3).setMinWidth(0);
    tabelPopup.getColumnModel().getColumn(3).setMaxWidth(0);
    tabelPopup.getColumnModel().getColumn(3).setWidth(0);

    javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(tabelPopup);
    scrollPane.setPreferredSize(new java.awt.Dimension(450, 250));
    panelPopup.add(scrollPane, java.awt.BorderLayout.CENTER);

    // 4. Fungsi untuk mengisi data tabel dengan Nomor Urut 1-11
    java.util.function.Consumer<String> urusDataStok = (ukuranTerpilih) -> {
        modelStok.setRowCount(0); // Bersihkan tabel
        try {
            Connection conn = konfKoneksi();
            if (conn == null) return;
            
            String sql = "SELECT v.id_varian, m.nama_makanan, v.stok FROM varian_porsi v "
                       + "JOIN makanan m ON v.id_makanan = m.id_makanan WHERE v.ukuran = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, ukuranTerpilih);
            ResultSet rs = pst.executeQuery();

            int noUrut = 1;
            while (rs.next()) {
                modelStok.addRow(new Object[]{
                    noUrut++, 
                    rs.getString("nama_makanan"),
                    rs.getInt("stok"),
                    rs.getInt("id_varian")
                });
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil data stok: " + e.getMessage());
        }
    };

    // Jalankan pengisian data pertama kali (default: Small)
    urusDataStok.accept(comboUkuran.getSelectedItem().toString());

    // Aksi jika dropdown Ukuran diubah
    comboUkuran.addActionListener(new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            urusDataStok.accept(comboUkuran.getSelectedItem().toString());
        }
    });

    // 5. Tampilkan pop-up dialog daftar stok
    int pilihan = JOptionPane.showConfirmDialog(this, panelPopup, "Manajemen Kontrol Stok Restoran", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    // 6. Proses edit / refill stok saat tombol OK diklik
    if (pilihan == JOptionPane.OK_OPTION) {
        int barisTerpilih = tabelPopup.getSelectedRow();
        if (barisTerpilih == -1) {
            JOptionPane.showMessageDialog(this, "Proses dibatalkan karena tidak ada menu yang dipilih.");
            return;
        }

        // Ambil data dari baris tabel popup yang diklik admin
        String idVarianAsli = tabelPopup.getValueAt(barisTerpilih, 3).toString();
        String namaMenu = tabelPopup.getValueAt(barisTerpilih, 1).toString();
        String ukuranSekarang = comboUkuran.getSelectedItem().toString();
        int stokLama = Integer.parseInt(tabelPopup.getValueAt(barisTerpilih, 2).toString());

        // Memunculkan kotak dialog pengisian stok dengan informasi batas maksimal 20
        String inputStokBaru = JOptionPane.showInputDialog(this, 
                "Menu: " + namaMenu + " (" + ukuranSekarang + ")\n" +
                "Stok Saat Ini: " + stokLama + " porsi (Maksimal: 20)\n\n" +
                "Tips Tambah: Gunakan tanda '+' untuk menambah (Contoh: +5)\n" +
                "Masukkan Jumlah Baru / Perubahan Stok:", 
                stokLama);

        if (inputStokBaru != null && !inputStokBaru.trim().isEmpty()) {
            String inputBersih = inputStokBaru.trim();
            int stokAkhirHasilPerhitungan = 0;

            try {
                // LOGIKA REFILL: Jika admin mengetik teks diawali '+' (misal: +5)
                if (inputBersih.startsWith("+")) {
                    String angkaSaja = inputBersih.substring(1).trim(); 
                    int jumlahRefill = Integer.parseInt(angkaSaja);
                    
                    if (jumlahRefill < 0) {
                        JOptionPane.showMessageDialog(this, "Jumlah penambahan tidak boleh negatif!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    stokAkhirHasilPerhitungan = stokLama + jumlahRefill; 
                } else {
                    // LOGIKA TIMPA: Jika langsung mengetik angka biasa
                    stokAkhirHasilPerhitungan = Integer.parseInt(inputBersih);
                }

                // VALIDASI UTAMA: Cek jika stok akhir kurang dari 0 atau MELEBIHI MAKSIMAL 20
                if (stokAkhirHasilPerhitungan < 0) {
                    JOptionPane.showMessageDialog(this, "Total stok tidak boleh bernilai negatif!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if (stokAkhirHasilPerhitungan > 20) {
                    JOptionPane.showMessageDialog(this, "Gagal! Stok maksimal untuk porsi ini adalah 20 porsi.\nInput kamu menghasilkan: " + stokAkhirHasilPerhitungan + " porsi.", "Batas Maksimal Stok", JOptionPane.ERROR_MESSAGE);
                    return; // Membatalkan proses update data ke database jika lebih dari 20
                }

                // Update database menggunakan ID Varian Asli jika lolos validasi
                Connection conn = konfKoneksi();
                String sqlUpdate = "UPDATE varian_porsi SET stok = ? WHERE id_varian = ?";
                java.sql.PreparedStatement pstUpdate = conn.prepareStatement(sqlUpdate);
                pstUpdate.setInt(1, stokAkhirHasilPerhitungan);
                pstUpdate.setInt(2, Integer.parseInt(idVarianAsli));
                
                int sukses = pstUpdate.executeUpdate();
                if (sukses > 0) {
                    JOptionPane.showMessageDialog(this, "Sukses memperbarui stok!\n" + namaMenu + " (" + ukuranSekarang + ") kini menjadi: " + stokAkhirHasilPerhitungan + " porsi.");
                    loadStatistik(); // Refresh counter dashboard admin
                }
                conn.close();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Input tidak valid! Harap masukkan format angka yang benar (contoh: 20 atau +5).", "Kesalahan Input", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan ke database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    }//GEN-LAST:event_btnKelolaStokActionPerformed

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
    private javax.swing.JButton btnKelolaStok;
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
