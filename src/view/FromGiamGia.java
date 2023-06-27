/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import DomainModel.AddChiTiet;
import DomainModel.ChiTietGiamGia;
import DomainModel.ChiTietSanPham;
import DomainModel.MaGiamGia;
import repository.impl.ChiTietGiamGiaRepository;
import repository.impl.GiamGiaRepository;
import service.ChiTietGiamGiaService;
import service.ChiTietSanPhamService;
import service.GiamGiaService;
import service.service_Impl.ChiTietGiamGiaServiceImpl;
import service.service_Impl.ChiTietSanPhamServiceImpl;
import service.service_Impl.GiamGiaServiceImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class FromGiamGia extends javax.swing.JFrame {

    /**
     * Creates new form FromGiamGia
     */
    private List<MaGiamGia> listMa;
    private List<ChiTietGiamGia> listctgg;
    private List<ChiTietSanPham> listctsp;
    private DefaultTableModel dtm;
    private GiamGiaService maSer;
    private ChiTietGiamGiaService ctggSer;
    private ChiTietSanPhamService ctspSer;

    public FromGiamGia() {
        initComponents();

        dateNgayBatDau.setDate(new Date());
        dateNgayKetThuc.setDate(new Date());

        maSer = new GiamGiaServiceImpl();
        

        ctggSer = new ChiTietGiamGiaServiceImpl();
        listctgg = ctggSer.getAllChiTietGiamGia();

        ctspSer = new ChiTietSanPhamServiceImpl();
        listctsp = ctspSer.getAll();

        showDataTable(listMa);
//        lblId.disable();
        setLocationRelativeTo(null);
//        showDataTable(listMa);
        showDataTableCTSP();
//        showDataTableCTGG(listctgg);
        showDataChiTietGiamGia();
    }

    private void showDataTable(List<MaGiamGia> lists) {
        dtm = (DefaultTableModel) tblGiamGia.getModel();
        listMa = maSer.getAll();
        dtm.setRowCount(0);
        for (MaGiamGia ma : lists) {
            dtm.addRow(ma.toRow());
        }
    }

    private void showDataChiTietGiamGia() {
        listctgg = ctggSer.getAllChiTietGiamGia();
        dtm = (DefaultTableModel) tblChiTietGiamGia.getModel();
        dtm.setRowCount(0);
        for (ChiTietGiamGia ctgg : listctgg) {
            Object[] data = {
                ctgg.getMaGiamGia(),
                ctgg.getMaGiay(),
                ctgg.getTenSanPham(),
                ctgg.getNgayBatDau(),
                ctgg.getNgayKetThuc(),
                ctgg.getSoTienConLai(),
                ctgg.getTrangThai() == 1 ? "Đang hoạt động" : "Không hoạt động"
            };
            dtm.addRow(data);
        }
    }
//
//    private void showDataChiTietGiamGia1(List<ChiTietGiamGia> lists) {
//        dtm = (DefaultTableModel) tblChiTietGiamGia.getModel();
//        dtm.setRowCount(0);
//        for (ChiTietGiamGia ctgg : lists) {
//            dtm.addRow(ctgg.CTGG());
//        }
//    }

    private void showDataTableCTSP() {
        listctsp = ctspSer.getAll();
        dtm = (DefaultTableModel) tblChiTietSP.getModel();
        dtm.setRowCount(0);
        for (ChiTietSanPham ctsp : listctsp) {
            Object[] data = {
                ctsp.getMaGiay(),
                ctsp.getTenGiay(),
                ctsp.getTenMauSac(),
                ctsp.getSize(),
                ctsp.getTenChatLieu(),
                ctsp.getHangGiay(),
                ctsp.getSoLuong(),
                ctsp.getGiaBan(),};
            dtm.addRow(data);
        }
    }
//    (100/text)*dongia

//    private void showDataTableCTGG(List<ChiTietGiamGia> y) {
//        dtm = (DefaultTableModel) tblChiTietGiamGia.getModel();
//        dtm.setRowCount(0);
//        for (ChiTietGiamGia ctgg : y) {
//            dtm.addRow(ctgg.CTGG());
//        }
//    }
    private AddChiTiet getFormChiTietGiamGia() {
        AddChiTiet add = new AddChiTiet();
        int row = tblChiTietSP.getSelectedRow();
        String maSP = ctspSer.layIdByMa(tblChiTietSP.getValueAt(row, 0).toString());
        String maGG = maSer.layIdByMa(tblGiamGia.getValueAt(row, 0).toString());
        add.setIdChiTietGiay(maSP);
        add.setIdMaGiamGia(maGG);
        double tienConLai = Double.parseDouble(tblChiTietSP.getValueAt(row, 7).toString()) - ((Double.parseDouble(txtPhanTramGiam.getText())) / 100) * Double.parseDouble(tblChiTietSP.getValueAt(row, 7).toString());
        add.setSoTienConLai(tienConLai);
        if (rdoCo.isSelected()) {
            add.setTrangThai(1);
        } else {
            add.setTrangThai(0);
        }
        return add;
    }

    private MaGiamGia getData() {
        MaGiamGia ma = new MaGiamGia();
        ma.setMaGiamGia(txtMa.getText());
        ma.setPhanTramGiam(Integer.parseInt(txtPhanTramGiam.getText()));
        ma.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        ma.setNgayBatDau(dateNgayBatDau.getDate());
        ma.setNgayketThuc(dateNgayKetThuc.getDate());
        ma.setDieuKienGiamGia(txtDieuKien.getText());
        if (rdoCo.isSelected()) {
            ma.setTrangThai(1);
        } else {
            ma.setTrangThai(0);
        }
        return ma;
    }

//    private ChiTietGiamGia getFrom(){
//        ChiTietGiamGia ctgg = new ChiTietGiamGia();
//        ctgg.setId(lblId.getText());
////        ctgg.setIdMaGiamGia(txtIdMaGiamGia.getText());
////        ctgg.setIdChiTietSanPham(txtIdCTSP.getText());
//        return ctgg;
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChiTietSP = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGiamGia = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtPhanTramGiam = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtDieuKien = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        rdoCo = new javax.swing.JRadioButton();
        rdokhong = new javax.swing.JRadioButton();
        dateNgayBatDau = new com.toedter.calendar.JDateChooser();
        dateNgayKetThuc = new com.toedter.calendar.JDateChooser();
        btnThem = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChiTietGiamGia = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setForeground(new java.awt.Color(0, 0, 255));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản Lí Sản Phẩm"));

        jLabel6.setText("Áp Dụng");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Hình Thức");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tblChiTietSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã giày", "Tên Giày", "Màu Sắc", "Size", "Chất Liệu", "Hãng Giày", "Số Lượng", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblChiTietSP);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Giảm Giá"));

        jLabel12.setText("Tìm Kiếm");

        tblGiamGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Giảm Giá", "Phần Trăm Giảm", "Số Lượng", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Điệu Kiện", "Trạng Thái"
            }
        ));
        tblGiamGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGiamGiaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblGiamGia);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 19, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách Khuyến Mại"));

        jLabel15.setText("Mã");

        txtMa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMaMouseClicked(evt);
            }
        });
        txtMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaActionPerformed(evt);
            }
        });

        jLabel16.setText("Phần Trăm");

        jLabel17.setText("Số Lượng");

        jLabel13.setText("Ngày Bắt Đầu");

        jLabel14.setText("Ngày Kết Thúc");

        jLabel18.setText("Điều Kiện");

        jLabel19.setText("Trạng Thái");

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoCo);
        rdoCo.setText("Đang hoạt động");

        buttonGroup1.add(rdokhong);
        rdokhong.setText("Dừng hoạt động");

        dateNgayBatDau.setDateFormatString("dd-MM-yyyy");

        dateNgayKetThuc.setDateFormatString("dd-mm- yyyy");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel14))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDieuKien)
                                    .addComponent(dateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMa)
                                    .addComponent(txtPhanTramGiam)
                                    .addComponent(txtSoLuong)
                                    .addComponent(dateNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(jLabel19)
                                    .addGap(51, 51, 51)
                                    .addComponent(rdoCo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addGap(108, 108, 108)
                                    .addComponent(rdokhong))))
                        .addGap(54, 54, 54))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtPhanTramGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(dateNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(dateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtDieuKien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(rdoCo))
                .addGap(18, 18, 18)
                .addComponent(rdokhong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa))
                .addGap(26, 26, 26))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi Tiết Giảm Giá"));

        tblChiTietGiamGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Giảm Giá", "Mã sản phẩm", "Tên Sản Phẩm", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Số Tiền Còn Lại", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTietGiamGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietGiamGiaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblChiTietGiamGia);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Áp dụng mã", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
//        AddChiTiet ctgg = getFormChiTietGiamGia();
        int row = tblChiTietGiamGia.getSelectedRow();
        int dong = tblGiamGia.getSelectedRow();
//        String ma = ;
        maSer.delete(tblGiamGia.getValueAt(dong, 0).toString());

        String maGG = tblChiTietGiamGia.getValueAt(row, 0).toString();
        String maCTSP = tblChiTietGiamGia.getValueAt(row, 1).toString();
//        String maCTSP1 = ctspSer.
        String idChiTiet = ctggSer.getIdChiTiet(maGG, maCTSP);
        System.out.println(idChiTiet);
        ctggSer.deleteMaGiamGia(idChiTiet);
        listctgg = ctggSer.getAllChiTietGiamGia();
        showDataChiTietGiamGia();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        MaGiamGia ma = getData();
        maSer.updateMa(ma);
        listMa = maSer.getAll();
        showDataTable(listMa);
        int rowCTGG = tblChiTietGiamGia.getSelectedRow();
        int rowgg = tblGiamGia.getSelectedRow();
        int rowsp = tblChiTietSP.getSelectedRow();
        AddChiTiet chiTiet = getFormChiTietGiamGia();
        String maGG = tblGiamGia.getValueAt(rowgg, 0).toString();
        String maSP = tblChiTietSP.getValueAt(rowsp, 0).toString();
        String maCTSP = tblChiTietGiamGia.getValueAt(rowCTGG, 1).toString();
        String maCTGG = tblChiTietGiamGia.getValueAt(rowCTGG, 0).toString();
        String giamGia = maSer.layIdByMa(maGG);
        String sanPham = ctspSer.layIdByMa(maSP);
        String idChiTiet = ctggSer.getIdChiTiet(maCTGG, maCTSP);
        System.out.println(giamGia);
        System.out.println(sanPham);
        System.out.println(idChiTiet);
        ctggSer.updateChiTiet(chiTiet, giamGia, sanPham, idChiTiet);
        showDataChiTietGiamGia();

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        int row[] = tblChiTietSP.getSelectedRows();
        MaGiamGia ma = getData();
        maSer.addMa(ma);
        listMa = maSer.getAll();
        showDataTable(listMa);
        for (int i = 0; i < row.length; i++) {
            AddChiTiet chiTiet = getFormChiTietGiamGia();
            ctggSer.addChiTiet(chiTiet);
            showDataChiTietGiamGia();
        }

        System.out.println(row);
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaActionPerformed

    private void txtMaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaMouseClicked

    private void tblGiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGiamGiaMouseClicked
        int row = tblGiamGia.getSelectedRow();
        txtMa.setText(tblGiamGia.getValueAt(row, 0).toString());
        txtPhanTramGiam.setText(tblGiamGia.getValueAt(row, 1).toString());
        txtSoLuong.setText(tblGiamGia.getValueAt(row, 2).toString());
//        dateNgayBatDau.setDate((tblGiamGia.getModel().getValueAt(row, 3).toString()));
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("dd-MM-yyyy");

        String dateNgayBd = tblGiamGia.getValueAt(row, 3).toString();
        String dateNgayKt = tblGiamGia.getValueAt(row, 4).toString();

        Date date = new Date();
        Date d = new Date();
        try {

            date = sdf.parse(dateNgayBd);
            date = sdf.parse(dateNgayKt);

        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        dateNgayBatDau.setDate(date);
        dateNgayKetThuc.setDate(date);
//        dateNgayBatDau.setDateFormatString(tblGiamGia.getValueAt(row, 3).toString());
//        dateNgayKetThuc.setDateFormatString(tblGiamGia.getValueAt(row, 4).toString());
        txtDieuKien.setText(tblGiamGia.getValueAt(row, 5).toString());
        if (tblGiamGia.getValueAt(row, 6).toString().equals("1")) {
            rdoCo.setSelected(true);
        } else {
            rdokhong.setSelected(true);
        }


    }//GEN-LAST:event_tblGiamGiaMouseClicked

    private void tblChiTietGiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietGiamGiaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblChiTietGiamGiaMouseClicked

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
            java.util.logging.Logger.getLogger(FromGiamGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FromGiamGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FromGiamGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FromGiamGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FromGiamGia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dateNgayBatDau;
    private com.toedter.calendar.JDateChooser dateNgayKetThuc;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JRadioButton rdoCo;
    private javax.swing.JRadioButton rdokhong;
    private javax.swing.JTable tblChiTietGiamGia;
    private javax.swing.JTable tblChiTietSP;
    private javax.swing.JTable tblGiamGia;
    private javax.swing.JTextField txtDieuKien;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtPhanTramGiam;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
