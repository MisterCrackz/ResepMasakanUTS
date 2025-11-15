import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class AplikasiResepFrame extends javax.swing.JFrame {

    private ManajemenResep manajer;
    private DefaultListModel<Resep> listModel;

    public AplikasiResepFrame() {
        initComponents();
        setupModernUI();
        
        // Inisialisasi Manajer
        manajer = new ManajemenResep();
        
        // Setup model untuk JList
        listModel = new DefaultListModel<>();
        jListResep.setModel(listModel);
        
        // Matikan tombol Update/Hapus
        btnUpdate.setEnabled(false);
        btnHapus.setEnabled(false);

        // Event listener untuk JList
        jListResep.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    jListResepValueChanged();
                }
            }
        });
        
        // Window di tengah layar
        this.setLocationRelativeTo(null);
    }
    
    /**
     * Setup Modern UI - Menambahkan styling modern pada komponen
     */
    private void setupModernUI() {
        // Set Color Scheme Modern
        Color primaryColor = new Color(41, 128, 185);      // Biru modern
        Color secondaryColor = new Color(52, 152, 219);    // Biru terang
        Color accentColor = new Color(46, 204, 113);       // Hijau
        Color dangerColor = new Color(231, 76, 60);        // Merah
        Color warningColor = new Color(243, 156, 18);      // Orange
        Color backgroundColor = new Color(236, 240, 241);   // Abu-abu terang
        Color cardColor = Color.WHITE;
        
        // Set background utama
        getContentPane().setBackground(backgroundColor);
        
        // Style untuk JList
        jListResep.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jListResep.setBackground(cardColor);
        jListResep.setSelectionBackground(secondaryColor);
        jListResep.setSelectionForeground(Color.WHITE);
        jListResep.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        jListResep.setFixedCellHeight(40);
        
        // Custom renderer untuk JList agar lebih menarik
        jListResep.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, 
                    int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);
                
                label.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)),
                    BorderFactory.createEmptyBorder(8, 12, 8, 12)
                ));
                
                if (isSelected) {
                    label.setBackground(secondaryColor);
                    label.setForeground(Color.WHITE);
                } else {
                    label.setBackground(cardColor);
                    label.setForeground(new Color(44, 62, 80));
                }
                
                return label;
            }
        });
        
        // Style ScrollPane untuk JList
        jScrollPane1.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        jScrollPane1.getViewport().setBackground(cardColor);
        
        // Style untuk Text Field
        txtJudul.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtJudul.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        // Style untuk Text Areas
        Font textAreaFont = new Font("Segoe UI", Font.PLAIN, 13);
        
        areaBahan.setFont(textAreaFont);
        areaBahan.setLineWrap(true);
        areaBahan.setWrapStyleWord(true);
        areaBahan.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        
        areaInstruksi.setFont(textAreaFont);
        areaInstruksi.setLineWrap(true);
        areaInstruksi.setWrapStyleWord(true);
        areaInstruksi.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        
        // Style ScrollPane untuk TextAreas
        jScrollPane2.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        jScrollPane3.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        
        // Style untuk Labels
        Font labelFont = new Font("Segoe UI", Font.BOLD, 12);
        Color labelColor = new Color(52, 73, 94);
        
        jLabel1.setFont(labelFont);
        jLabel1.setForeground(labelColor);
        jLabel2.setFont(labelFont);
        jLabel2.setForeground(labelColor);
        jLabel3.setFont(labelFont);
        jLabel3.setForeground(labelColor);
        
        // Style untuk Header
        jLabel4.setFont(new Font("Segoe UI", Font.BOLD, 20));
        jLabel4.setForeground(primaryColor);
        
        // Style untuk Buttons dengan warna berbeda
        styleButton(btnTambah, accentColor, "✓ ");
        styleButton(btnUpdate, warningColor, "⟳ ");
        styleButton(btnHapus, dangerColor, "✕ ");
        styleButton(btnImpor, primaryColor, "📥 ");
        styleButton(btnEkspor, primaryColor, "📤 ");
        styleButton(btnClear, new Color(149, 165, 166), "⌫ ");
    }
    
    /**
     * Method helper untuk styling button
     */
    private void styleButton(JButton button, Color bgColor, String icon) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        // Tambahkan icon di teks
        String originalText = button.getText();
        if (!originalText.startsWith(icon)) {
            button.setText(icon + originalText);
        }
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }
    
    private void jListResepValueChanged() {
        int selectedIndex = jListResep.getSelectedIndex();
        
        if (selectedIndex != -1) {
            Resep resepTerpilih = listModel.getElementAt(selectedIndex);
            
            txtJudul.setText(resepTerpilih.getJudul());
            areaBahan.setText(resepTerpilih.getBahan());
            areaInstruksi.setText(resepTerpilih.getInstruksi());
            
            btnUpdate.setEnabled(true);
            btnHapus.setEnabled(true);
        } else {
            btnUpdate.setEnabled(false);
            btnHapus.setEnabled(false);
        }
    }
    
    private void clearFields() {
        txtJudul.setText("");
        areaBahan.setText("");
        areaInstruksi.setText("");
        jListResep.clearSelection();
    }

    private void updateList() {
        listModel.clear();
        ArrayList<Resep> daftar = manajer.getDaftarResep();
        for (Resep r : daftar) {
            listModel.addElement(r);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jListResep = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        txtJudul = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaBahan = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaInstruksi = new javax.swing.JTextArea();
        btnTambah = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnImpor = new javax.swing.JButton();
        btnEkspor = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikasi Resep Masakan");
        setBackground(new java.awt.Color(0, 0, 0));

        jScrollPane1.setViewportView(jListResep);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Judul Resep:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Bahan-bahan:");

        areaBahan.setColumns(20);
        areaBahan.setRows(5);
        jScrollPane2.setViewportView(areaBahan);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Instruksi:");

        areaInstruksi.setColumns(20);
        areaInstruksi.setRows(5);
        jScrollPane3.setViewportView(areaInstruksi);

        btnTambah.setText("Tambah Resep Baru");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update Resep Terpilih");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus Resep Terpilih");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnImpor.setText("Impor ");
        btnImpor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImporActionPerformed(evt);
            }
        });

        btnEkspor.setText("Ekspor ");
        btnEkspor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEksporActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Daftar Resep");

        btnClear.setText("Clear Form");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtJudul)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClear))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(btnImpor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(btnEkspor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTambah)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate)
                                .addGap(18, 18, 18)
                                .addComponent(btnHapus)))
                        .addGap(0, 85, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(btnClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTambah)
                            .addComponent(btnUpdate)
                            .addComponent(btnHapus))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnImpor)
                            .addComponent(btnEkspor)))
                    .addComponent(jScrollPane1))
                .addContainerGap(125, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {                                          
        String judul = txtJudul.getText().trim();
        String bahan = areaBahan.getText().trim();
        String instruksi = areaInstruksi.getText().trim();
        
        if (judul.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Judul resep tidak boleh kosong!", 
                "Validasi Error", 
                JOptionPane.WARNING_MESSAGE);
            txtJudul.requestFocus();
            return;
        }
        
        Resep resepBaru = new Resep(judul, bahan, instruksi);
        manajer.tambahResep(resepBaru);
        
        updateList(); 
        clearFields(); 
        JOptionPane.showMessageDialog(this, 
            "Resep '" + judul + "' berhasil ditambahkan!", 
            "Berhasil", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {                                          
        int selectedIndex = jListResep.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, 
                "Pilih resep yang ingin di-update terlebih dahulu!", 
                "Peringatan", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String judul = txtJudul.getText().trim();
        String bahan = areaBahan.getText().trim();
        String instruksi = areaInstruksi.getText().trim();
        
        if (judul.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Judul resep tidak boleh kosong!", 
                "Validasi Error", 
                JOptionPane.WARNING_MESSAGE);
            txtJudul.requestFocus();
            return;
        }
        
        Resep resepUpdate = new Resep(judul, bahan, instruksi);
        manajer.updateResep(selectedIndex, resepUpdate);
        
        updateList();
        jListResep.setSelectedIndex(selectedIndex); 
        JOptionPane.showMessageDialog(this, 
            "Resep '" + judul + "' berhasil diperbarui!", 
            "Berhasil", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int selectedIndex = jListResep.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, 
                "Pilih resep yang ingin dihapus terlebih dahulu!", 
                "Peringatan", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Resep resep = listModel.getElementAt(selectedIndex);
        int konfirmasi = JOptionPane.showConfirmDialog(this, 
                "Anda yakin ingin menghapus resep '" + resep.getJudul() + "'?",
                "Konfirmasi Hapus", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        
        if (konfirmasi == JOptionPane.YES_OPTION) {
            manajer.hapusResep(selectedIndex);
            updateList();
            clearFields();
            JOptionPane.showMessageDialog(this, 
                "Resep berhasil dihapus!", 
                "Berhasil", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void btnImporActionPerformed(java.awt.event.ActionEvent evt) {                                         
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Pilih File JSON untuk Diimpor");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JSON Files", "json"));

        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            
            if (manajer.imporDariJSON(fileToOpen)) {
                updateList(); 
                clearFields();
                JOptionPane.showMessageDialog(this, 
                    "Data berhasil diimpor dari:\n" + fileToOpen.getName(), 
                    "Impor Berhasil", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Gagal mengimpor data dari file.\nPastikan format file benar.", 
                    "Impor Gagal", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void btnEksporActionPerformed(java.awt.event.ActionEvent evt) {                                          
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Simpan sebagai File JSON");
        fileChooser.setSelectedFile(new File("resep-masakan.json"));
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JSON Files", "json"));

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            
            // Pastikan ekstensi .json
            if (!fileToSave.getName().endsWith(".json")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".json");
            }
            
            if (manajer.eksporKeJSON(fileToSave)) {
                JOptionPane.showMessageDialog(this, 
                    "Data berhasil diekspor ke:\n" + fileToSave.getName(), 
                    "Ekspor Berhasil", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Gagal mengekspor data ke file.", 
                    "Ekspor Gagal", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {                                         
        clearFields();
    }
    
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AplikasiResepFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AplikasiResepFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaBahan;
    private javax.swing.JTextArea areaInstruksi;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnEkspor;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnImpor;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<Resep> jListResep;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txtJudul;
    // End of variables declaration//GEN-END:variables
}
