/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package AseanFoodOrderingSystem; 

// ====================================================================
// TARUH SEMUA IMPORT DI SINI (Di luar class, di bawah package)
// ====================================================================
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author lenovo
 */
public class FormDineIn extends javax.swing.JFrame {
        
     
    public FormDineIn() {
        
        initComponents();
        tampilkanMejaTersedia(); 
        tampilkanTabelKetersediaanMeja();
       
        
    }
    // HAPUS BAGIAN INI (Baris 11-16 di dalam class kamu)
    
    public void tampilkanTabelKetersediaanMeja() {
       String[] judulKolom = {"No. Meja", "Status Meja"};
        javax.swing.table.DefaultTableModel modelMeja = new javax.swing.table.DefaultTableModel(null, judulKolom) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Mengunci cell agar tidak bisa diketik manual
            }
        };
        
        // Menghubungkan model ke JTable yang sudah kamu drag di NetBeans
        tabelMejaAdmin.setModel(modelMeja);
        
        try {
            java.sql.Connection conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/db_restoran_asean", "root", "");
            java.sql.Statement stmt = conn.createStatement();
            String sql = "SELECT no_meja, status_meja FROM data_meja ORDER BY no_meja ASC";
            java.sql.ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                Object[] data = {
                    "Meja " + rs.getInt("no_meja"),
                    rs.getString("status_meja")
                };
                modelMeja.addRow(data);
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Gagal memuat tabel status meja: " + e.getMessage());
        }
    }
    public void tampilkanMejaTersedia() {
  cbMeja.removeAllItems(); 
        try {
            java.sql.Connection conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/db_restoran_asean", "root", "");
            java.sql.Statement stmt = conn.createStatement();
            String sql = "SELECT no_meja FROM data_meja WHERE status_meja = 'Tersedia'";
            java.sql.ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                cbMeja.addItem("Meja " + rs.getInt("no_meja"));
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Gagal memuat data meja: " + e.getMessage());
        }
    }

    private void tampilkanMenuDineIn() {
        String[] judulKolom = {"ID Makanan", "Nama Makanan", "Asal Negara", "Ukuran Porsi", "Harga"};
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(null, judulKolom) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Membuat tabel tidak bisa diedit ketik manual oleh user
            }
        };
        
        
        try {
            java.sql.Connection conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/db_restoran_asean", "root", "");
            java.sql.Statement stmt = conn.createStatement();
            
            // Query JOIN antara tabel makanan dan varian_porsi yang dikhususkan untuk Dine In
            String sql = "SELECT m.id_makanan, m.nama_makanan, m.asal_negara, v.ukuran, v.harga " +
                         "FROM makanan m JOIN varian_porsi v ON m.id_makanan = v.id_makanan " +
                         "WHERE v.bisa_dine_in = 1";
            java.sql.ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                Object[] data = {
                    rs.getInt("id_makanan"),
                    rs.getString("nama_makanan"),
                    rs.getString("asal_negara"),
                    rs.getString("ukuran"),
                    rs.getDouble("harga")
                };
                model.addRow(data);
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Gagal memuat menu makanan: " + e.getMessage());
        }
        // ... isi kode fungsi menu ...
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        h = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbMeja = new javax.swing.JComboBox<>();
        txtNama = new javax.swing.JTextField();
        btnPesan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelMejaAdmin = new javax.swing.JTable();
        btnEditStatusMeja = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        h.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        h.setText("Nama Pelanggan");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel1.setText("Pesan Meja");

        cbMeja.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        cbMeja.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Meja 1", "Meja 2", "Meja 3", "Meja 4", "Meja 5", "Meja 6", "Meja 7", "Meja 8", "Meja 9", "Meja 10" }));

        txtNama.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N

        btnPesan.setText("Pesan Sekarang");
        btnPesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesanActionPerformed(evt);
            }
        });

        tabelMejaAdmin.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelMejaAdmin);

        btnEditStatusMeja.setText("Edit Status Meja");
        btnEditStatusMeja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditStatusMejaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditStatusMeja))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(h, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(288, 288, 288)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbMeja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPesan)))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btnEditStatusMeja))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(90, 90, 90)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(h)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMeja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesanActionPerformed
        // TODO add your handling code here:
        try {
            // 1. Validasi input nama dan nomor meja agar tidak kosong
            if (txtNama.getText().trim().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Nama pelanggan tidak boleh kosong!");
                return;
            }
            if (cbMeja.getSelectedItem() == null) {
                javax.swing.JOptionPane.showMessageDialog(this, "Meja tidak tersedia atau penuh!");
                return;
            }

            String namaPelanggan = txtNama.getText();
            String teksMeja = cbMeja.getSelectedItem().toString();
            
            // Ekstrak angka nomor meja (Misal: "Meja 3" -> 3)
            int noMeja = Integer.parseInt(teksMeja.replaceAll("[^0-9]", ""));
            
            // Karena tabel menu dine-in dihapus, kita set ID Varian default ke null/0 
            // dan total_harga_rp ke 0.0 terlebih dahulu.
            int idVarianDefault = 1;
            double hargaDefault = 0.0;

            // 2. Koneksi ke Database
            java.sql.Connection conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/db_restoran_asean", "root", "");

            // 3. Simpan transaksi booking meja ke database
            String sqlSimpan = "INSERT INTO pesanan (nama_pelanggan, tipe_pesanan, no_meja, id_varian, total_harga_rp) VALUES (?, 'DINE IN', ?, ?, ?)";
            java.sql.PreparedStatement pstSimpan = conn.prepareStatement(sqlSimpan);
            
            pstSimpan.setString(1, namaPelanggan); 
            pstSimpan.setInt(2, noMeja);           
            pstSimpan.setInt(3, idVarianDefault);         
            pstSimpan.setDouble(4, hargaDefault);   
            
            pstSimpan.executeUpdate();

            // 4. Update status meja di database menjadi 'Terisi'
            String sqlMeja = "UPDATE data_meja SET status_meja = 'Terisi' WHERE no_meja = ?";
            java.sql.PreparedStatement pstMeja = conn.prepareStatement(sqlMeja);
            pstMeja.setInt(1, noMeja);
            pstMeja.executeUpdate();

            // Notifikasi sukses memesan meja
            javax.swing.JOptionPane.showMessageDialog(this, "Booking Meja Berhasil! " + namaPelanggan + " resmi menempati Meja " + noMeja);

            // 5. Reset input nama & refresh isi ComboBox Meja
            txtNama.setText("");
            tampilkanMejaTersedia();
            tampilkanTabelKetersediaanMeja();

            // Tutup resources database
            pstSimpan.close();
            pstMeja.close();
            conn.close();

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Gagal memproses transaksi: " + e.getMessage());
        }
    }//GEN-LAST:event_btnPesanActionPerformed

    private void btnEditStatusMejaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditStatusMejaActionPerformed

        int barisTerpilih = tabelMejaAdmin.getSelectedRow();
        
        if (barisTerpilih == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Silakan pilih meja yang ingin diubah dari tabel terlebih dahulu!");
            return;
        }
        
        // 2. Ambil data meja dari baris terpilih
        String teksMeja = tabelMejaAdmin.getValueAt(barisTerpilih, 0).toString();
        String statusSekarang = tabelMejaAdmin.getValueAt(barisTerpilih, 1).toString();
        int noMeja = Integer.parseInt(teksMeja.replaceAll("[^0-9]", ""));
        
        // 3. Logika toggle status: Tersedia <-> Terisi
        String statusBaru = statusSekarang.equals("Tersedia") ? "Terisi" : "Tersedia";
        
        int konfirmasi = javax.swing.JOptionPane.showConfirmDialog(this, 
                "Ubah status " + teksMeja + " menjadi '" + statusBaru + "'?", 
                "Konfirmasi Kontrol Meja Admin", javax.swing.JOptionPane.YES_NO_OPTION);
                
        if (konfirmasi == javax.swing.JOptionPane.YES_OPTION) {
            try {
                java.sql.Connection conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/db_restoran_asean", "root", "");
                String sqlUpdate = "UPDATE data_meja SET status_meja = ? WHERE no_meja = ?";
                java.sql.PreparedStatement pst = conn.prepareStatement(sqlUpdate);
                pst.setString(1, statusBaru);
                pst.setInt(2, noMeja);
                
                int sukses = pst.executeUpdate();
                if (sukses > 0) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Sukses! Status " + teksMeja + " diganti menjadi: " + statusBaru);
                    
                    // REFRESH SEKALIGUS biar data ComboBox dan Tabel Admin sinkron
                    tampilkanMejaTersedia(); 
                    tampilkanTabelKetersediaanMeja(); 
                }
                
                pst.close();
                conn.close();
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Gagal mengupdate database: " + e.getMessage());
            }
        }   
    }//GEN-LAST:event_btnEditStatusMejaActionPerformed

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
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FormDineIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               new MenuUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditStatusMeja;
    private javax.swing.JButton btnPesan;
    private javax.swing.JComboBox<String> cbMeja;
    private javax.swing.JLabel h;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelMejaAdmin;
    private javax.swing.JTextField txtNama;
    // End of variables declaration//GEN-END:variables
}


