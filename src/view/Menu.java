package view;

import DomainModel.AddChiTiet;
import DomainModel.ChiTietGiamGia;
import DomainModel.ChiTietGiay;
import DomainModel.ChiTietSanPham;
import DomainModel.ChucVuDomain;
import DomainModel.GiayVIP;
import DomainModel.HoaDonBanHang;
import DomainModel.HoaDonCT;
import DomainModel.KhachHang;
import DomainModel.MaGiamGia;
import DomainModel.NhanVien;
import DomainModel.ThuocTinh;
import DomainModel.giaoCa;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.ThongKe_Serv;
import service.service_Impl.ChatLieu_Service;
import service.service_Impl.ChiTietGiay_Service;
import service.service_Impl.Hang_Service;
import service.service_Impl.MauSac_Service;
import service.service_Impl.Size_Service;
import service.service_Impl.ThongKe_ServImpl;
import viewModel.ChiTietGiay_View;
import viewModel.ThongKe_ViewModel;
import viewModel.ThuocTinh_View;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import repository.DangNhap_Resp;
import repository.HoaDonBanHangRepository;
import repository.HoaDonCTRepository;
import repository.KhachHangRepository;
import repository.SanPhanBanHangRepository;
import service.ChiTietGiamGiaService;
import service.ChiTietSanPhamService;
import service.ChucVu_Service;
import service.GiamGiaService;
import service.Giaoca_Serv;
import service.NhanVien_Service;
import service.service_Impl.ChiTietGiamGiaServiceImpl;
import service.service_Impl.ChiTietSanPhamServiceImpl;
import service.service_Impl.ChucVu_ServiceImpl;
import service.service_Impl.GiamGiaServiceImpl;
import service.service_Impl.GiaoCa_sevrIPML;
import service.service_Impl.HoaDonInService;
import service.service_Impl.NhanVien_ServiceImpl;
import service.service_Impl.chuCuaHangService;
import static view.QLGCA.lblTienMat;
import static view.QLGCA.lblTongTien;
import viewModel.HoaDonCTBanHang;
import viewModel.NhanVienViewModel;
import viewModel.SanPhamBanHang;
import viewModel.ThongTinHoaDonBanHang;

public class Menu extends javax.swing.JFrame implements Runnable, ThreadFactory {

    DefaultTableModel dtm = null;
    ThongKe_Serv thongKe_Serv = new ThongKe_ServImpl();
    List<ThongKe_ViewModel> listTKDoanhThu = new ArrayList<>();
    List<ThongKe_ViewModel> listTKSanPham = new ArrayList<>();
    String pattern = "#.###";
    DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance();
    List<NhanVien> list;
    NhanVien_ServiceImpl chService = new NhanVien_ServiceImpl();
    ChucVu_Service cvService = new ChucVu_ServiceImpl();
    List<ChucVuDomain> listCV;
    List<HoaDonCTBanHang> listgh = new ArrayList<>();
    List<ThongTinHoaDonBanHang> listhdbh;
    List<SanPhamBanHang> listspbh;
    private ArrayList<HoaDonCTBanHang> listHdct = new ArrayList<>();
    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor exe = Executors.newSingleThreadExecutor(this);
    chuCuaHangService _CuaHangService = new chuCuaHangService();

    Giaoca_Serv Giaoca = new GiaoCa_sevrIPML();
    List<giaoCa> listgc = new ArrayList<>();

    List<String> st = Giaoca.getallMaNV();

    String anhStr = null;
    List<ChiTietGiay_View> listCTG;
    List<ChiTietGiay> listChiTiet;
    List<ThuocTinh_View> listTT;
    DefaultTableModel model;

    MauSac_Service mauSV = new MauSac_Service();
    Size_Service sizeSV = new Size_Service();
    ChatLieu_Service chatLieuSV = new ChatLieu_Service();
    Hang_Service hangSV = new Hang_Service();
    ChiTietGiay_Service chiTietGiaYSV = new ChiTietGiay_Service();
    HoaDonInService _DonInService = new HoaDonInService();

    private List<MaGiamGia> listMa;
    private List<ChiTietGiamGia> listctgg;
    private List<ChiTietSanPham> listctsp;
    private GiamGiaService maSer;
    private ChiTietGiamGiaService ctggSer;
    private ChiTietSanPhamService ctspSer;

    public Menu() {
        initComponents();
        dcf.applyPattern(pattern);
        this.setLocationRelativeTo(null);

        // Thống kê
        pnlTimkiemTong.setVisible(false);
        NgayBatDau.setDate(new Date());
        NgayKetThuc.setDate(new Date());
        setTable();
        listTKSanPham = thongKe_Serv.getAll();
        loadTableSanPham(listTKSanPham);
        pnlSoLuong.setVisible(false);
        listTKDoanhThu = thongKe_Serv.getAllTheoNgayHomNay();
        loadTableThongKe(listTKDoanhThu);

        setPanelThongKe(thongKe_Serv.getTongSoHD(), thongKe_Serv.getDoanhThu());
        setPanelSanPham();
        // Sản phẩm
        rdoLam.setSelected(true);
        cboChucVu.removeAllItems();
        listCV = cvService.getAllChucVu();
        for (int i = 0; i < listCV.size(); i++) {
            cboChucVu.addItem(String.valueOf(listCV.get(i).getTen()));
        }

        fillCbbSize();
        fillCbbChatLieu();
        fillCbbHangGiay();
        fillCbbMauSac();
        // Sản phẩm

        // giao ca
        loadTableCaLam();
        // giao ca
        // Bán hàng
//        initWebcam();
        fillHDHT();
        fillSPBH();
        fillHDCho();
        fillHDGiao();
        fillHDTReo();
        String nam = String.valueOf(java.time.LocalDate.now().getYear());
        String thang = String.valueOf(java.time.LocalDate.now().getMonthValue());
        String ngay = String.valueOf(java.time.LocalDate.now().getDayOfMonth());
        String day = String.valueOf(nam + "-" + thang + "-" + ngay);
        txtNgayTao.setText(day);
        // bán hàng

        DateNgayBatDau.setDate(new Date());
        DateNgayKetThuc.setDate(new Date());

        maSer = new GiamGiaServiceImpl();
        listMa = maSer.getAll();

        ctggSer = new ChiTietGiamGiaServiceImpl();
        listctgg = ctggSer.getAllChiTietGiamGia();

        ctspSer = new ChiTietSanPhamServiceImpl();
        listctsp = ctspSer.getAll();

        DateNgayBatDau.setDate(new Date());
        DateNgayKetThuc.setDate(new Date());

        showDataTableCTSP();
        showDataChiTietGiamGia();
//        showDataTable(listMa);
        showDataGiamGia();
//        fillToGiamGia();

//        setDate();
        Thread t = new Thread(this);
        t.start();
        txtTenNhanVien.setText("Lê ĐỨc Nam");
    }

    // set ngày giờ
//    void setDate() {
//        Date dNow = new Date();
//        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//        lblDongHo.setText(ft.format(dNow) + "");
//    }
    private void loadTableCaLam() {
        list = chService.getAllNhanVien();
        dtm = (DefaultTableModel) tblNhanvienTK.getModel();
        dtm.setRowCount(0);
        for (NhanVien n : list) {

            if (n.getChucVu().getTen().equalsIgnoreCase("Chủ cửa hàng") == false) {
                Object rowData[] = {
                    n.getMa(),
                    n.getHoTen(),
                    n.getChucVu().getTen()

                };
                dtm.addRow(rowData);
            }

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

    private void showDataGiamGia() {
        listMa = maSer.getAll();
        dtm = (DefaultTableModel) tblGiamGia.getModel();
        dtm.setRowCount(0);
        for (MaGiamGia mgg : listMa) {
            Object[] data = {
                mgg.getMaGiamGia(),
                mgg.getPhanTramGiam(),
                mgg.getSoLuong(),
                mgg.getNgayBatDau(),
                mgg.getNgayketThuc(),
                mgg.getDieuKienGiamGia(),
                mgg.getTrangThai() == 1 ? "Đang hoạt động" : "Không hoạt động"
            };
            dtm.addRow(data);
        }
    }

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

//    public void fillToGiamGia() {
//        listMa = maSer.getAll();
//        dtm = (DefaultTableModel) tblGiamGia.getModel();
//        dtm.setRowCount(0);
//        for (MaGiamGia ma : listMa) {
//            Object[] giamGia = {
//                ma.getMaGiamGia(),
//                ma.getPhanTramGiam(),
//                ma.getSoLuong(),
//                ma.getNgayBatDau(),
//                ma.getNgayketThuc(),
//                ma.getDieuKienGiamGia(),
//                ma.getTrangThai(),};
//            dtm.addRow(giamGia);
//        }
//    }
    private void showDataTable(List<MaGiamGia> lists) {
        dtm = (DefaultTableModel) tblGiamGia.getModel();
        dtm.setRowCount(0);
        for (MaGiamGia ma : lists) {
            dtm.addRow(ma.toRow());
        }
    }

    //nhanvien-----------------------------------------------------------------------
    public void fillTableNV() {
        list = chService.getAllNhanVien();
        dtm = (DefaultTableModel) tblNhanvien.getModel();
        dtm.setRowCount(0);
        String t;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTrangThai() == 1) {
                t = "Đang làm";
            } else {
                t = "Đang nghỉ";
            }

            Object[] data = new Object[]{
                list.get(i).getMa(),
                list.get(i).getHoTen(),
                list.get(i).getChucVu().getTen(),
                t
            };
            dtm.addRow(data);
        }
    }

    //sanpham------------------------------------------------------------------------
    private void fillTableChiTietGiay() {
        listCTG = chiTietGiaYSV.getAllChiTietGiayView();
        model = (DefaultTableModel) tblGiay.getModel();
        model.setRowCount(0);
        for (ChiTietGiay_View ct : listCTG) {
            Object[] data = {
                ct.getTenGiay(),
                ct.getTenMauSac(),
                ct.getSize(),
                ct.getChatLieu(),
                ct.getTenHang(),
                ct.getNamBaoHanh(),
                ct.getSoLuong(),
                ct.getGiaBan(),
                ct.getAnh()
            };
            model.addRow(data);
        }
    }

    public void setPanelSanPham() {

        lblSoGiayKd.setText(String.valueOf(thongKe_Serv.getSoGiayKD()));
        lblSoGiaySapHet.setText(String.valueOf(thongKe_Serv.getSoGiaySapHet()));
        lblSoGiayHet.setText(String.valueOf(thongKe_Serv.getSoGiayHet()));

    }

    public ImageIcon resizeImage(String url) {
        ImageIcon imageIcon = new ImageIcon(url);
        Image img = imageIcon.getImage();
        Image newimg = img.getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon1 = new ImageIcon(newimg);
        return imageIcon1;

    }

//    private void fillCbbGiay() {
//        cbbGiay.removeAllItems();
//        listTT = giaySV.getAllGiayView();
//        for (ThuocTinh_View tt : listTT) {
//            cbbGiay.addItem(tt.getTen());
//        }
//    }
    private void fillCbbMauSac() {
        cbbMauSac.removeAllItems();
        listTT = mauSV.getAllMauView();
        for (ThuocTinh_View tt : listTT) {
            cbbMauSac.addItem(tt.getTen());
        }
    }

    private void fillCbbSize() {
        cbbSize.removeAllItems();
        listTT = sizeSV.getAllSizeView();
        for (ThuocTinh_View tt : listTT) {
            cbbSize.addItem(tt.getTen());
        }
    }

    private void fillCbbChatLieu() {
        cbbChatLieu.removeAllItems();
        listTT = chatLieuSV.getAllChatLieuView();
        for (ThuocTinh_View tt : listTT) {
            cbbChatLieu.addItem(tt.getTen());
        }
    }

    private void fillCbbHangGiay() {
        cbbHang.removeAllItems();
        listTT = hangSV.getAllHangView();
        for (ThuocTinh_View tt : listTT) {
            cbbHang.addItem(tt.getTen());
        }
    }

    private void fillTableThuocTinhMau() {
        listTT = mauSV.getAllMauView();
        model = (DefaultTableModel) tblThuocTinh.getModel();
        model.setRowCount(0);
        for (ThuocTinh_View gv : listTT) {
            Object[] data = {
                gv.getMa(),
                gv.getTen(),
                gv.getTrangThai()
            };
            model.addRow(data);
        }
    }

    private void fillTableThuocTinhSize() {
        listTT = sizeSV.getAllSizeView();
        model = (DefaultTableModel) tblThuocTinh.getModel();
        model.setRowCount(0);
        for (ThuocTinh_View gv : listTT) {
            Object[] data = {
                gv.getMa(),
                gv.getTen(),
                gv.getTrangThai()
            };
            model.addRow(data);
        }
    }

    private void fillTableThuocTinhChatLieu() {
        listTT = chatLieuSV.getAllChatLieuView();
        model = (DefaultTableModel) tblThuocTinh.getModel();
        model.setRowCount(0);
        for (ThuocTinh_View gv : listTT) {
            Object[] data = {
                gv.getMa(),
                gv.getTen(),
                gv.getTrangThai()
            };
            model.addRow(data);
        }
    }

    private void fillTableThuocTinhHang() {
        listTT = hangSV.getAllHangView();
        model = (DefaultTableModel) tblThuocTinh.getModel();
        model.setRowCount(0);
        for (ThuocTinh_View gv : listTT) {
            Object[] data = {
                gv.getMa(),
                gv.getTen(),
                gv.getTrangThai()
            };
            model.addRow(data);
        }
    }
    //SanPham---------------------------------------------------------------------------------

    public void loadTableThongKe(List<ThongKe_ViewModel> list) {
        dtm = (DefaultTableModel) tblThongKe.getModel();
        dtm.setRowCount(0);
        String tt = null;
        for (ThongKe_ViewModel t : list) {
            if (t.getTt() == 1) {
                tt = "Thành công";
            } else if (t.getTt() == 2) {
                tt = "Chưa thanh toán";
            } else if (t.getTt() == 3) {
                tt = "Giao";

            } else if (t.getTt() == 4) {
                tt = "Huy";
            }
            Object rowData[] = {
                t.getMaHD(),
                t.getMaSp(),
                t.getTenSp(),
                t.getDonGia(),
                t.getSlBan(),
                t.getTienmat(),
                t.getTienkhac(),
                t.getTiengiamgia(),
                t.getDoanhThu(),
                t.getNgaytao(),
                tt,
                t.getLidoHuy()
            };
            dtm.addRow(rowData);
        }

    }

    public void loadTableSanPham(List<ThongKe_ViewModel> list) {
        dtm = (DefaultTableModel) tblThongKe1.getModel();
        dtm.setRowCount(0);
        for (ThongKe_ViewModel t : list) {
            Object rowData[] = {
                t.getMaSp(),
                t.getTenSp(),
                t.getMau(),
                t.getSize(),
                t.getChatLieu(),
                t.getSlTon(),
                t.getSlBan(),
                t.getGianhap(),
                t.getDonGia(),
                t.getDoanhThu(),
                t.getNamBH()};
            dtm.addRow(rowData);
        }

    }

    public void fillHDHT() {
        listhdbh = HoaDonBanHangRepository.getAllHDHT();
        dtm = (DefaultTableModel) tblHoaDonHoanThanh.getModel();
        dtm.setRowCount(0);
        for (int i = 0; i < listhdbh.size(); i++) {
            Object[] data = new Object[]{
                listhdbh.get(i).getMa(),
                listhdbh.get(i).getTennv(),
                listhdbh.get(i).getTenkh(),
                listhdbh.get(i).getThanhtien(),};
            dtm.addRow(data);
        }
    }

    public void fillHDCho() {
        listhdbh = HoaDonBanHangRepository.getAllHDCho();
        dtm = (DefaultTableModel) tblHoaDonCho.getModel();
        dtm.setRowCount(0);
        for (int i = 0; i < listhdbh.size(); i++) {
            Object[] data = new Object[]{
                listhdbh.get(i).getMa(),
                listhdbh.get(i).getTennv(),
                listhdbh.get(i).getTenkh(),
                listhdbh.get(i).getThanhtien(),};
            dtm.addRow(data);
        }
    }

    public void fillHDGiao() {
        listhdbh = HoaDonBanHangRepository.getAllHDGiao();
        dtm = (DefaultTableModel) tblHoaDonGiao.getModel();
        dtm.setRowCount(0);
        for (int i = 0; i < listhdbh.size(); i++) {
            Object[] data = new Object[]{
                listhdbh.get(i).getMa(),
                listhdbh.get(i).getTennv(),
                listhdbh.get(i).getTenkh(),
                listhdbh.get(i).getThanhtien(),};
            dtm.addRow(data);
        }
    }

    public void fillSPBH() {
        listspbh = SanPhanBanHangRepository.getAllSpBh();
        dtm = (DefaultTableModel) tblSanPhamBanHang.getModel();
        dtm.setRowCount(0);
        for (int i = 0; i < listspbh.size(); i++) {
            Object[] data = new Object[]{
                listspbh.get(i).getMa(),
                listspbh.get(i).getTen(),
                listspbh.get(i).getHang(),
                listspbh.get(i).getSize(),
                listspbh.get(i).getMau(),
                listspbh.get(i).getDongia(),
                listspbh.get(i).getPhamtramgiam(),
                listspbh.get(i).getSl(),};
            dtm.addRow(data);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    public void clearHDTQ() {
        txtTongTienTQ.setText("");
        txtKhuyenMaiThemTQ.setText("");
        txtTienKhachCanTra.setText("");
        txtTienMatTQ.setText("");
        txtTienCKTQ.setText("");
        txtTienDuTQ.setText("");

    }

    public void clearHDGH() {
        txtTenKHGH.setText("");
        txtSDTGH.setText("");
        txtNgaySinhKH.setText("");
        txtDiaChiGH.setText("");
        txtTongTienGH.setText("");
        txtGiamGiaGH.setText("");
        txtPhiShip.setText("");
        txtCanTraGH.setText("");
        txtTienKhachTra.setText("");
        txtMaVanChuyen.setText("");
    }

    public void setPanelThongKe(int soDOnHang, double doanhThu) {

        lblTongDonHang.setText(String.valueOf(soDOnHang));
        lblDoanhthu.setText(dcf.format(doanhThu));
        lblHoaDonHuy1.setText(String.valueOf(thongKe_Serv.getSoHDHuy()));
        lblHoaDonGiao.setText(String.valueOf(thongKe_Serv.getSoHDGiao()));

    }

    public void setPanelThongKe2(int soDOnHang, double doanhThu, int huy, int giao) {

        lblTongDonHang.setText(String.valueOf(soDOnHang));
        lblDoanhthu.setText(dcf.format(doanhThu));
        lblHoaDonHuy1.setText(String.valueOf(huy));
        lblHoaDonGiao.setText(String.valueOf(giao));

    }

    public HoaDonBanHang getFormHoaDonBanHangGiao() {

        double tienKhac = Double.parseDouble(txtTienKhachTra.getText());
        double phiShip = Double.parseDouble(txtPhiShip.getText());
        String ngaythanhtoan = txtNgayTao.getText();
        String nam = String.valueOf(java.time.LocalDate.now().getYear());
        String thang = String.valueOf(java.time.LocalDate.now().getMonthValue());
        String ngay = String.valueOf(java.time.LocalDate.now().getDayOfMonth() + 1);
        String ngaygiao = String.valueOf(nam + "-" + thang + "-" + ngay);
        String sdt = txtSDTGH.getText();
        String idkh = KhachHangRepository.getIdKHBySDT(sdt);
        double giamGia = Double.parseDouble(txtGiamGiaGH.getText());
        String mavc = txtMaVanChuyen.getText();
        System.out.println(idkh);
        return new HoaDonBanHang(null, null, idkh, null, ngaythanhtoan, ngaythanhtoan, ngaygiao, ngaythanhtoan, null, null, mavc, 3, 0, tienKhac, giamGia, phiShip);
    }

    public HoaDonBanHang getFormHoaDonBanHang() {
        System.out.println(txtKhuyenMaiThemTQ.getText());
        double giamthem = Double.parseDouble(txtKhuyenMaiThemTQ.getText());
        double tienMat = Double.parseDouble(txtTienMatTQ.getText());
        double tienDu = Double.parseDouble(txtTienDuTQ.getText());
        double tienMatDB = tienMat - tienDu;
        double tienKhac = Double.parseDouble(txtTienCKTQ.getText());
        String nam = String.valueOf(java.time.LocalDate.now().getYear());
        String thang = String.valueOf(java.time.LocalDate.now().getMonthValue());
        String ngay = String.valueOf(java.time.LocalDate.now().getDayOfMonth());
        String ngaythanhtoan = String.valueOf(nam + "-" + thang + "-" + ngay);

        return new HoaDonBanHang(null, null, null, null, null, ngaythanhtoan, null, ngaythanhtoan, null, null, null, 1, tienMatDB, tienKhac, giamthem, 0);
    }

    private void setTable() {
        tblGiay.getTableHeader().setBackground(new Color(122, 181, 236));
        tblGiay.getTableHeader().setOpaque(false);
        tblGioHang.getTableHeader().setBackground(new Color(122, 181, 236));
        tblSanPhamBanHang.getTableHeader().setBackground(new Color(122, 181, 236));
        tblHoaDonHoanThanh.getTableHeader().setBackground(new Color(122, 181, 236));
        tblThongKe.getTableHeader().setBackground(new Color(122, 181, 236));
        tblNhanvien.getTableHeader().setBackground(new Color(122, 181, 236));
        tblGiamGia.getTableHeader().setBackground(new Color(122, 181, 236));
        tblHoaDonCho.getTableHeader().setBackground(new Color(122, 181, 236));
        tblHoaDonGiao.getTableHeader().setBackground(new Color(122, 181, 236));
        tblThuocTinh.getTableHeader().setBackground(new Color(122, 181, 236));
        tblNhanvienTK.getTableHeader().setBackground(new Color(122, 181, 236));
        tblChiTietGiaoCa.getTableHeader().setBackground(new Color(122, 181, 236));
        tblHoaDonTreo.getTableHeader().setBackground(new Color(122, 181, 236));
        tblChiTietGiamGia.getTableHeader().setBackground(new Color(122, 181, 236));
        tblChiTietSP.getTableHeader().setBackground(new Color(122, 181, 236));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        popupTreoHD = new javax.swing.JMenuItem();
        popupHoanThanhHD = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        popupHoanThanhHDTreo = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        buttonGroup2 = new javax.swing.ButtonGroup();
        Goc = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlTong = new javax.swing.JPanel();
        pnlBanHang = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnSua1 = new javax.swing.JButton();
        btnXoa1 = new javax.swing.JButton();
        btnXoaAllGH = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPhamBanHang = new javax.swing.JTable();
        txtTimSP = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        tbbTao = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        scpTao = new javax.swing.JScrollPane();
        tblHoaDonCho = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        scpGiao = new javax.swing.JScrollPane();
        tblHoaDonGiao = new javax.swing.JTable();
        jPanel35 = new javax.swing.JPanel();
        scpTreo = new javax.swing.JScrollPane();
        tblHoaDonTreo = new javax.swing.JTable();
        scpHoanThanh = new javax.swing.JScrollPane();
        tblHoaDonHoanThanh = new javax.swing.JTable();
        btnTimTheoNgay1 = new javax.swing.JButton();
        txtDate = new com.toedter.calendar.JDateChooser();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        tbbTTHD = new javax.swing.JTabbedPane();
        pnlTaiQuay = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtTongTienTQ = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTienMatTQ = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTienCKTQ = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        txtTienDuTQ = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        txtKhuyenMaiThemTQ = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        txtTienKhachCanTra = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        lblTienMatTQ = new javax.swing.JLabel();
        lblTienChuyenKhoan = new javax.swing.JLabel();
        lblGiamGiaTQ = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        lblTienDuTQ = new javax.swing.JLabel();
        pnlGiaoHang = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtTongTienGH = new javax.swing.JTextField();
        txtTienKhachTra = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        txtPhiShip = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        txtCanTraGH = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtGiamGiaGH = new javax.swing.JTextField();
        jPanel22 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtTenKHGH = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        txtSDTGH = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNgaySinhKH = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        txtDiaChiGH = new javax.swing.JTextField();
        btnLuuKH = new javax.swing.JButton();
        lblTenKH = new javax.swing.JLabel();
        lblSDTGH = new javax.swing.JLabel();
        lblDiaChiGH = new javax.swing.JLabel();
        lblNgaySinhGH = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        txtMaVanChuyen = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        lblGiamGiaGH = new javax.swing.JLabel();
        lblPhiShip = new javax.swing.JLabel();
        lblTienKhachTra = new javax.swing.JLabel();
        lblMaVanChuyenGH = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        txtNgayTao = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnTaoDon = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        barcode = new javax.swing.JTextField();
        pnlSanPham = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbbMauSac = new javax.swing.JComboBox<>();
        cbbSize = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        cbbChatLieu = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        cbbHang = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jasdf = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtMaGiay = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblAnh = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        pnlWebCam = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        txtTenGiay = new javax.swing.JTextField();
        txtNamBaoHanh = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtGiaNhap = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        txtMaBarCode = new javax.swing.JTextField();
        lblMaGiay = new javax.swing.JLabel();
        lblTenGiay = new javax.swing.JLabel();
        lblNamBaoHanh = new javax.swing.JLabel();
        lblSoLuong = new javax.swing.JLabel();
        lblGiaNhap = new javax.swing.JLabel();
        lblGiaBan = new javax.swing.JLabel();
        btnQuetMa = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblGiay = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnHienThi = new javax.swing.JButton();
        btnThemExcel = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        rdoMauSac = new javax.swing.JRadioButton();
        rdoSize = new javax.swing.JRadioButton();
        rdoChatLieu = new javax.swing.JRadioButton();
        rdoHang = new javax.swing.JRadioButton();
        btnThemThuocTinh = new javax.swing.JButton();
        btnSuaThuocTinh = new javax.swing.JButton();
        btnXoaThuocTinh = new javax.swing.JButton();
        ckbTrangThai = new javax.swing.JCheckBox();
        btnLamMoi = new javax.swing.JButton();
        txtMaThuocTinh = new javax.swing.JTextField();
        txtTenThuocTinh = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblThuocTinh = new javax.swing.JTable();
        pnlThongKe = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        cbbBoloc = new javax.swing.JComboBox<>();
        jPanel27 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        lblTongDonHang = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        btnXuatFile = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblThongKe = new javax.swing.JTable();
        pnlTimkiemTong = new javax.swing.JPanel();
        pnlTheoNgay = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();
        NgayBatDau = new com.toedter.calendar.JDateChooser();
        NgayKetThuc = new com.toedter.calendar.JDateChooser();
        pnlTheoNam = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        btnTimKiemTheoNam = new javax.swing.JButton();
        cbbNam = new javax.swing.JComboBox<>();
        jLabel61 = new javax.swing.JLabel();
        btnMailDoanhThu = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        lblHoaDonGiao = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        lblHoaDonHuy1 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        lblDoanhthu = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblThongKe1 = new javax.swing.JTable();
        cbbThongKeSanPham = new javax.swing.JComboBox<>();
        jLabel52 = new javax.swing.JLabel();
        btnXuatFile1 = new javax.swing.JButton();
        jPanel32 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        lblSoGiayKd = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        lblSoGiayHet = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        lblSoGiaySapHet = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        btnMailSanPham = new javax.swing.JButton();
        pnlSoLuong = new javax.swing.JPanel();
        txtSoluong = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblLoi = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblChiTietGiaoCa = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblNhanvienTK = new javax.swing.JTable();
        cbbThangLamViec = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        pnlGiamGia = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblGiamGia = new javax.swing.JTable();
        jPanel37 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblChiTietSP = new javax.swing.JTable();
        jPanel38 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblChiTietGiamGia = new javax.swing.JTable();
        jPanel39 = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        txtMa1 = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        txtPhanTramGiam = new javax.swing.JTextField();
        jLabel100 = new javax.swing.JLabel();
        txtSoLuong1 = new javax.swing.JTextField();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        txtDieuKien = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        btnSua2 = new javax.swing.JButton();
        btnXoa2 = new javax.swing.JButton();
        rdoCo = new javax.swing.JRadioButton();
        rdokhong = new javax.swing.JRadioButton();
        DateNgayBatDau = new com.toedter.calendar.JDateChooser();
        btnThem1 = new javax.swing.JButton();
        lblMa1 = new javax.swing.JLabel();
        lblPhanTramGiam = new javax.swing.JLabel();
        lblNgayBatDau = new javax.swing.JLabel();
        lblSoLuong1 = new javax.swing.JLabel();
        lblNgayKetThuc = new javax.swing.JLabel();
        lblDieuKien = new javax.swing.JLabel();
        DateNgayKetThuc = new com.toedter.calendar.JDateChooser();
        btnCapNhap = new javax.swing.JButton();
        pnlNhanvien = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        rdoLam = new javax.swing.JRadioButton();
        rdoNghi = new javax.swing.JRadioButton();
        cboChucVu = new javax.swing.JComboBox<>();
        txtEmail = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JTextField();
        lblMa = new javax.swing.JLabel();
        lblTen = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblMatKhau = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        cbbLocCv = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        txtTimKiemNV = new javax.swing.JTextField();
        jPanel42 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblNhanvien = new javax.swing.JTable();
        btnThemNV = new javax.swing.JButton();
        btnSuaNV = new javax.swing.JButton();
        btnResetNV = new javax.swing.JButton();
        btnLoadNV = new javax.swing.JButton();
        pnlNenGiaoCa = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        pnlQuyen = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JLabel();
        lblDongHo = new javax.swing.JLabel();
        lblNgayHienTai = new javax.swing.JLabel();
        lblBanHang = new javax.swing.JLabel();
        lblSanpham = new javax.swing.JLabel();
        lblThoat = new javax.swing.JLabel();
        lblNhanvien = new javax.swing.JLabel();
        lblGiaoCa = new javax.swing.JLabel();
        lblGiamGia = new javax.swing.JLabel();
        lblThongKe = new javax.swing.JLabel();
        lblDangXuat = new javax.swing.JLabel();

        popupTreoHD.setText("Treo HĐ");
        popupTreoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popupTreoHDActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popupTreoHD);

        popupHoanThanhHD.setText("Hoàn Thành");
        popupHoanThanhHD.setToolTipText("");
        popupHoanThanhHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popupHoanThanhHDActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popupHoanThanhHD);

        jMenuItem2.setText("Hủy HĐ");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        popupHoanThanhHDTreo.setText("Hoàn Thành");
        popupHoanThanhHDTreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popupHoanThanhHDTreoActionPerformed(evt);
            }
        });
        jPopupMenu2.add(popupHoanThanhHDTreo);

        jMenuItem3.setText("Hủy HĐ ");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItem3);

        jMenuItem1.setText("Hủy ");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu3.add(jMenuItem1);

        jMenuItem4.setText("Treo HĐ");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jPopupMenu3.add(jMenuItem4);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Goc.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Hover_BanHang.png"))); // NOI18N
        Goc.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlTong.setLayout(new java.awt.CardLayout());

        pnlBanHang.setPreferredSize(new java.awt.Dimension(1234, 834));

        jPanel1.setPreferredSize(new java.awt.Dimension(1234, 834));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Giỏ Hàng"));

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Số Lượng", "Đơn Giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGioHang.setSelectionBackground(new java.awt.Color(255, 204, 204));
        tblGioHang.setShowGrid(false);
        tblGioHang.setShowHorizontalLines(true);
        jScrollPane2.setViewportView(tblGioHang);

        btnSua1.setBackground(new java.awt.Color(0, 204, 204));
        btnSua1.setFont(new java.awt.Font("Segoe UI", 3, 13)); // NOI18N
        btnSua1.setForeground(new java.awt.Color(255, 255, 255));
        btnSua1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Sua123.png"))); // NOI18N
        btnSua1.setText("Sửa SL");
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });

        btnXoa1.setBackground(new java.awt.Color(0, 204, 204));
        btnXoa1.setFont(new java.awt.Font("Segoe UI", 3, 13)); // NOI18N
        btnXoa1.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancel-24.png"))); // NOI18N
        btnXoa1.setText("Xóa SP");
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });

        btnXoaAllGH.setBackground(new java.awt.Color(0, 204, 204));
        btnXoaAllGH.setFont(new java.awt.Font("Segoe UI", 3, 13)); // NOI18N
        btnXoaAllGH.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaAllGH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/trash.png"))); // NOI18N
        btnXoaAllGH.setText("Xóa All");
        btnXoaAllGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaAllGHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnXoaAllGH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(btnXoa1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoaAllGH, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnSua1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Sản Phẩm"));

        tblSanPhamBanHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Hãng", "Size", "Màu", "Đơn giá", "% giảm", "Số lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPhamBanHang.setSelectionBackground(new java.awt.Color(255, 204, 204));
        tblSanPhamBanHang.setShowGrid(false);
        tblSanPhamBanHang.setShowHorizontalLines(true);
        tblSanPhamBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamBanHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblSanPhamBanHangMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPhamBanHang);

        txtTimSP.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimSPCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Hóa Đơn"));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        tbbTao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbbTaoMouseClicked(evt);
            }
        });

        tblHoaDonCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã HĐ", "Tên Nhân Viên", "Tên Khách Hàng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonCho.setSelectionBackground(new java.awt.Color(255, 204, 204));
        tblHoaDonCho.setShowGrid(false);
        tblHoaDonCho.setShowHorizontalLines(true);
        tblHoaDonCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblHoaDonChoMouseReleased(evt);
            }
        });
        scpTao.setViewportView(tblHoaDonCho);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scpTao, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 136, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addComponent(scpTao, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        tbbTao.addTab("HĐ Tạo", jPanel5);

        tblHoaDonGiao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã HĐ", "Tên Nhân Viên", "Tên Khách Hàng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonGiao.setSelectionBackground(new java.awt.Color(255, 204, 204));
        tblHoaDonGiao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonGiaoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblHoaDonGiaoMouseReleased(evt);
            }
        });
        scpGiao.setViewportView(tblHoaDonGiao);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scpGiao, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 131, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addComponent(scpGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        tbbTao.addTab("HĐ Đang Giao", jPanel6);

        tblHoaDonTreo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã HĐ", "Tên Nhân Viên", "Tên Khách Hàng", "Lí do"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonTreo.setSelectionBackground(new java.awt.Color(255, 204, 204));
        tblHoaDonTreo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonTreoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblHoaDonTreoMouseReleased(evt);
            }
        });
        scpTreo.setViewportView(tblHoaDonTreo);

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scpTreo, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 131, Short.MAX_VALUE)
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel35Layout.createSequentialGroup()
                    .addComponent(scpTreo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        tbbTao.addTab("HĐ Treo", jPanel35);

        tblHoaDonHoanThanh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã HĐ", "Tên Nhân Viên", "Tên Khách Hàng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonHoanThanh.setSelectionBackground(new java.awt.Color(255, 204, 204));
        tblHoaDonHoanThanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonHoanThanhMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblHoaDonHoanThanhMouseEntered(evt);
            }
        });
        scpHoanThanh.setViewportView(tblHoaDonHoanThanh);

        tbbTao.addTab("HĐ Đã Hoàn Thành", scpHoanThanh);

        btnTimTheoNgay1.setText("Tìm");
        btnTimTheoNgay1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimTheoNgay1ActionPerformed(evt);
            }
        });

        txtDate.setDateFormatString("dd MMMM,yyyy");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbbTao)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnTimTheoNgay1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimTheoNgay1))
                .addGap(7, 7, 7)
                .addComponent(tbbTao, javax.swing.GroupLayout.PREFERRED_SIZE, 153, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2));

        pnlTaiQuay.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Tổng tiền ");

        txtTongTienTQ.setEditable(false);
        txtTongTienTQ.setBackground(new java.awt.Color(238, 238, 238));
        txtTongTienTQ.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtTongTienTQ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTongTienTQ.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));
        txtTongTienTQ.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("Tiền mặt");

        txtTienMatTQ.setBackground(new java.awt.Color(238, 238, 238));
        txtTienMatTQ.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtTienMatTQ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTienMatTQ.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));
        txtTienMatTQ.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtTienMatTQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienMatTQActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Tiền CK");

        txtTienCKTQ.setBackground(new java.awt.Color(238, 238, 238));
        txtTienCKTQ.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtTienCKTQ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTienCKTQ.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));
        txtTienCKTQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienCKTQActionPerformed(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel63.setText("Tiền dư");

        txtTienDuTQ.setEditable(false);
        txtTienDuTQ.setBackground(new java.awt.Color(238, 238, 238));
        txtTienDuTQ.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtTienDuTQ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTienDuTQ.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));
        txtTienDuTQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienDuTQActionPerformed(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel64.setText("Giảm giá ");

        txtKhuyenMaiThemTQ.setBackground(new java.awt.Color(238, 238, 238));
        txtKhuyenMaiThemTQ.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtKhuyenMaiThemTQ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtKhuyenMaiThemTQ.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));
        txtKhuyenMaiThemTQ.setSelectedTextColor(new java.awt.Color(242, 242, 242));
        txtKhuyenMaiThemTQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKhuyenMaiThemTQActionPerformed(evt);
            }
        });

        jLabel65.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel65.setText("Khách cần trả");

        txtTienKhachCanTra.setEditable(false);
        txtTienKhachCanTra.setBackground(new java.awt.Color(238, 238, 238));
        txtTienKhachCanTra.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtTienKhachCanTra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTienKhachCanTra.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));
        txtTienKhachCanTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachCanTraActionPerformed(evt);
            }
        });

        jLabel67.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel67.setText("VNĐ");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel3.setText("VNĐ");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel5.setText("VNĐ");

        jLabel68.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel68.setText("VNĐ");

        jLabel69.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel69.setText("%");

        jLabel93.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel93.setText("VNĐ");

        lblTienMatTQ.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblTienMatTQ.setForeground(new java.awt.Color(255, 0, 0));

        lblTienChuyenKhoan.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblTienChuyenKhoan.setForeground(new java.awt.Color(255, 0, 0));

        lblGiamGiaTQ.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblGiamGiaTQ.setForeground(new java.awt.Color(255, 0, 0));

        jLabel94.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel94.setText("VNĐ");

        jLabel106.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel106.setText("VNĐ");

        lblTienDuTQ.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblTienDuTQ.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout pnlTaiQuayLayout = new javax.swing.GroupLayout(pnlTaiQuay);
        pnlTaiQuay.setLayout(pnlTaiQuayLayout);
        pnlTaiQuayLayout.setHorizontalGroup(
            pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                        .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTaiQuayLayout.createSequentialGroup()
                                .addGap(339, 339, 339)
                                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                                .addGap(502, 502, 502)
                                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                        .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTienDuTQ, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                                        .addComponent(txtTienDuTQ, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51)
                                        .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblGiamGiaTQ, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                                        .addComponent(txtKhuyenMaiThemTQ, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTienChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTienCKTQ, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                                .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                                        .addGap(105, 105, 105)
                                        .addComponent(txtTongTienTQ, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel9))
                                .addGap(46, 46, 46)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                                .addComponent(jLabel65)
                                .addGap(18, 18, 18)
                                .addComponent(txtTienKhachCanTra, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                                        .addComponent(txtTienMatTQ, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(49, 49, 49)
                                        .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lblTienMatTQ, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlTaiQuayLayout.setVerticalGroup(
            pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel67)
                .addGap(4, 4, 4)
                .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTongTienTQ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(51, 51, 51)
                .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(jLabel69)
                    .addComponent(txtKhuyenMaiThemTQ, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(lblGiamGiaTQ, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(txtTienKhachCanTra, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTaiQuayLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel93)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTaiQuayLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtTienMatTQ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)))
                .addComponent(lblTienMatTQ, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel94)
                    .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTienCKTQ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)))
                .addGap(18, 18, 18)
                .addComponent(lblTienChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel68)
                .addGap(8, 8, 8)
                .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTaiQuayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel63)
                        .addComponent(txtTienDuTQ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel106))
                .addGap(18, 18, 18)
                .addComponent(lblTienDuTQ, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        tbbTTHD.addTab("Tại quầy", pnlTaiQuay);

        pnlGiaoHang.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("Tổng");

        txtTongTienGH.setEditable(false);
        txtTongTienGH.setBackground(new java.awt.Color(238, 238, 238));
        txtTongTienGH.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtTongTienGH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTongTienGH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        txtTienKhachTra.setBackground(new java.awt.Color(238, 238, 238));
        txtTienKhachTra.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtTienKhachTra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTienKhachTra.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));
        txtTienKhachTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachTraActionPerformed(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel71.setText("Tiền Khách trả");

        jLabel72.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel72.setText("Phí ship");

        txtPhiShip.setBackground(new java.awt.Color(238, 238, 238));
        txtPhiShip.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPhiShip.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPhiShip.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));
        txtPhiShip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhiShipActionPerformed(evt);
            }
        });

        jLabel73.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel73.setText("Khách cần trả");

        txtCanTraGH.setEditable(false);
        txtCanTraGH.setBackground(new java.awt.Color(238, 238, 238));
        txtCanTraGH.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtCanTraGH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCanTraGH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Giảm giá thêm");

        txtGiamGiaGH.setBackground(new java.awt.Color(238, 238, 238));
        txtGiamGiaGH.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtGiamGiaGH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGiamGiaGH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));
        txtGiamGiaGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiamGiaGHActionPerformed(evt);
            }
        });

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khách hàng"));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Tên KH");

        txtTenKHGH.setBackground(new java.awt.Color(238, 238, 238));
        txtTenKHGH.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtTenKHGH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTenKHGH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        jLabel75.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel75.setText("SĐT");

        txtSDTGH.setBackground(new java.awt.Color(238, 238, 238));
        txtSDTGH.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtSDTGH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSDTGH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));
        txtSDTGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTGHActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Ngày sinh");

        txtNgaySinhKH.setBackground(new java.awt.Color(238, 238, 238));
        txtNgaySinhKH.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtNgaySinhKH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNgaySinhKH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));
        txtNgaySinhKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgaySinhKHActionPerformed(evt);
            }
        });

        jLabel85.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel85.setText("Địa chỉ");

        txtDiaChiGH.setBackground(new java.awt.Color(238, 238, 238));
        txtDiaChiGH.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtDiaChiGH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiaChiGH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        btnLuuKH.setBackground(new java.awt.Color(255, 0, 51));
        btnLuuKH.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnLuuKH.setForeground(new java.awt.Color(255, 255, 255));
        btnLuuKH.setText("Lưu KH");
        btnLuuKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuKHActionPerformed(evt);
            }
        });

        lblTenKH.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        lblTenKH.setForeground(new java.awt.Color(255, 0, 0));
        lblTenKH.setText(" ");

        lblSDTGH.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        lblSDTGH.setForeground(new java.awt.Color(255, 0, 0));
        lblSDTGH.setText(" ");

        lblDiaChiGH.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        lblDiaChiGH.setForeground(new java.awt.Color(255, 0, 0));
        lblDiaChiGH.setText(" ");

        lblNgaySinhGH.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        lblNgaySinhGH.setForeground(new java.awt.Color(255, 0, 0));
        lblNgaySinhGH.setText(" ");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtSDTGH, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTenKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblSDTGH, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtTenKHGH, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(btnLuuKH, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDiaChiGH, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChiGH, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNgaySinhGH, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNgaySinhKH, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKHGH, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTenKH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSDTGH, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel75))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSDTGH))
                    .addComponent(btnLuuKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNgaySinhKH, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel85))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblNgaySinhGH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiaChiGH, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDiaChiGH)))
                .addGap(8, 8, 8))
        );

        jLabel86.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel86.setText("VNĐ");

        jLabel87.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel87.setText("VNĐ");

        jLabel88.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel88.setText("VNĐ");

        jLabel90.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel90.setText("VNĐ");

        jLabel92.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel92.setText("%");

        jLabel97.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel97.setText("Mã vận chuyển");

        txtMaVanChuyen.setBackground(new java.awt.Color(238, 238, 238));
        txtMaVanChuyen.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtMaVanChuyen.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMaVanChuyen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        jLabel95.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel95.setText("VNĐ");

        lblGiamGiaGH.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblGiamGiaGH.setForeground(new java.awt.Color(255, 0, 0));
        lblGiamGiaGH.setText(" ");

        lblPhiShip.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblPhiShip.setForeground(new java.awt.Color(255, 0, 0));
        lblPhiShip.setText(" ");

        lblTienKhachTra.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblTienKhachTra.setForeground(new java.awt.Color(255, 0, 0));
        lblTienKhachTra.setText(" ");

        lblMaVanChuyenGH.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblMaVanChuyenGH.setForeground(new java.awt.Color(255, 0, 0));
        lblMaVanChuyenGH.setText(" ");

        javax.swing.GroupLayout pnlGiaoHangLayout = new javax.swing.GroupLayout(pnlGiaoHang);
        pnlGiaoHang.setLayout(pnlGiaoHangLayout);
        pnlGiaoHangLayout.setHorizontalGroup(
            pnlGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGiaoHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGiaoHangLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(pnlGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnlGiaoHangLayout.createSequentialGroup()
                                .addGroup(pnlGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel73)
                                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlGiaoHangLayout.createSequentialGroup()
                                .addGroup(pnlGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(pnlGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlGiaoHangLayout.createSequentialGroup()
                                        .addGroup(pnlGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTongTienGH, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtGiamGiaGH, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblGiamGiaGH, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPhiShip, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(27, 27, 27)
                                        .addGroup(pnlGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel86, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel92, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lblPhiShip, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlGiaoHangLayout.createSequentialGroup()
                                .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(pnlGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnlGiaoHangLayout.createSequentialGroup()
                                        .addGroup(pnlGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTienKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMaVanChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(44, 44, 44)
                                        .addGroup(pnlGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lblMaVanChuyenGH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTienKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel71, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlGiaoHangLayout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(txtCanTraGH, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlGiaoHangLayout.setVerticalGroup(
            pnlGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGiaoHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtTongTienGH, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel86))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(pnlGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtGiamGiaGH, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel92))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGiamGiaGH, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhiShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72)
                    .addComponent(jLabel87))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPhiShip, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCanTraGH, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73)
                    .addComponent(jLabel88))
                .addGroup(pnlGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGiaoHangLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(pnlGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTienKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel71))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE))
                    .addGroup(pnlGiaoHangLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel90)
                        .addGap(3, 3, 3)))
                .addComponent(lblTienKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlGiaoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaVanChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel97)
                    .addComponent(jLabel95))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMaVanChuyenGH, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        tbbTTHD.addTab("Giao hàng", pnlGiaoHang);

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel6.setText("Mã HĐ");

        txtMaHD.setEditable(false);
        txtMaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHDActionPerformed(evt);
            }
        });

        txtNgayTao.setEditable(false);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel8.setText("Ngày tạo");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                    .addComponent(txtMaHD))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnTaoDon.setBackground(new java.awt.Color(255, 0, 51));
        btnTaoDon.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnTaoDon.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add (1).png"))); // NOI18N
        btnTaoDon.setText("Tạo đơn");
        btnTaoDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbbTTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTaoDon)))
                .addGap(203, 203, 203))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTaoDon, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbbTTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnThanhToan.setBackground(new java.awt.Color(0, 204, 0));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-dolar-20.png"))); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 204, 0));
        jButton9.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh (1).png"))); // NOI18N
        jButton9.setText("Xóa Form");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 442, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel23.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(0, 204, 204));
        jButton2.setFont(new java.awt.Font("Segoe UI", 3, 13)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Quét mã");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel23.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 100, 40));

        barcode.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                barcodeCaretUpdate(evt);
            }
        });
        jPanel23.add(barcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 160, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(33, 33, 33)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBanHangLayout = new javax.swing.GroupLayout(pnlBanHang);
        pnlBanHang.setLayout(pnlBanHangLayout);
        pnlBanHangLayout.setHorizontalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1343, Short.MAX_VALUE)
        );
        pnlBanHangLayout.setVerticalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBanHangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlTong.add(pnlBanHang, "card2");

        jPanel10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.setPreferredSize(new java.awt.Dimension(1025, 754));

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel2.setText("Năm bảo hành");

        jLabel12.setText("Màu sắc");

        cbbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel17.setText("Size");

        cbbChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel18.setText("Chất liệu");

        cbbHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel19.setText("Hãng");

        jasdf.setText("Số lượng");

        jLabel21.setText("Giá nhập");

        jLabel22.setText("Giá bán");

        txtMaGiay.setBackground(new java.awt.Color(238, 238, 238));
        txtMaGiay.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMaGiay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        jLabel23.setText("Mã giày");

        jLabel24.setText("Tên giày");

        lblAnh.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, null, java.awt.Color.lightGray));
        lblAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhMouseClicked(evt);
            }
        });

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("WedCam"));
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel12MouseClicked(evt);
            }
        });
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlWebCam.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        pnlWebCam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlWebCamMouseClicked(evt);
            }
        });
        pnlWebCam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel12.add(pnlWebCam, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 200, 140));

        jLabel25.setText("Mã barCode");

        txtTenGiay.setBackground(new java.awt.Color(238, 238, 238));
        txtTenGiay.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTenGiay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        txtNamBaoHanh.setBackground(new java.awt.Color(238, 238, 238));
        txtNamBaoHanh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNamBaoHanh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        txtSoLuong.setBackground(new java.awt.Color(238, 238, 238));
        txtSoLuong.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSoLuong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        txtGiaNhap.setBackground(new java.awt.Color(238, 238, 238));
        txtGiaNhap.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGiaNhap.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        txtGiaBan.setBackground(new java.awt.Color(238, 238, 238));
        txtGiaBan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGiaBan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        jLabel51.setText("Ảnh");

        txtMaBarCode.setBackground(new java.awt.Color(238, 238, 238));
        txtMaBarCode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMaBarCode.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        lblMaGiay.setForeground(new java.awt.Color(255, 0, 51));
        lblMaGiay.setText(" ");

        lblTenGiay.setForeground(new java.awt.Color(255, 0, 0));
        lblTenGiay.setText(" ");

        lblNamBaoHanh.setForeground(new java.awt.Color(255, 0, 0));
        lblNamBaoHanh.setText(" ");

        lblSoLuong.setForeground(new java.awt.Color(255, 0, 0));
        lblSoLuong.setText(" ");

        lblGiaNhap.setForeground(new java.awt.Color(255, 0, 0));
        lblGiaNhap.setText(" ");

        lblGiaBan.setForeground(new java.awt.Color(255, 0, 0));
        lblGiaBan.setText(" ");

        btnQuetMa.setText("Quét mã");
        btnQuetMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuetMaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addComponent(jasdf, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblNamBaoHanh, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtTenGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtMaGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtNamBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                                        .addGap(96, 96, 96)
                                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(lblMaGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(lblTenGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addGap(80, 80, 80))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(43, 43, 43))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(lblGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(133, 133, 133)))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(btnQuetMa, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(38, 38, 38))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)
                                .addComponent(lblMaGiay)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtTenGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTenGiay)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNamBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNamBaoHanh)
                                .addGap(2, 2, 2)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jasdf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                        .addComponent(lblSoLuong)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblGiaNhap)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                    .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuetMa)
                        .addContainerGap())))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        tblGiay.setBackground(new java.awt.Color(242, 242, 242));
        tblGiay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Giày", "Màu sắc", "Size", "Chất liệu", "Hãng", "Năm bảo hành", "Số lượng", "Giá Bán", "Ảnh"
            }
        ));
        tblGiay.setSelectionBackground(new java.awt.Color(255, 204, 204));
        tblGiay.setShowGrid(false);
        tblGiay.setShowHorizontalLines(true);
        tblGiay.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                tblGiayComponentRemoved(evt);
            }
        });
        tblGiay.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblGiayAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblGiay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGiayMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblGiayMouseReleased(evt);
            }
        });
        jScrollPane6.setViewportView(tblGiay);

        jLabel26.setText("Tìm kiếm sản phẩm");

        txtTimKiem.setBackground(new java.awt.Color(238, 238, 238));
        txtTimKiem.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel26)
                .addGap(32, 32, 32)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnThem.setBackground(new java.awt.Color(0, 204, 204));
        btnThem.setFont(new java.awt.Font("Segoe UI", 3, 13)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add (1).png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setBorder(null);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(0, 204, 204));
        btnSua.setFont(new java.awt.Font("Segoe UI", 3, 13)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Sua123.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setBorder(null);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(0, 204, 204));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 3, 13)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete_kh.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setBorder(null);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMoi.setBackground(new java.awt.Color(0, 204, 204));
        btnMoi.setFont(new java.awt.Font("Segoe UI", 3, 13)); // NOI18N
        btnMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh (1).png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.setBorder(null);
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnHienThi.setBackground(new java.awt.Color(0, 204, 204));
        btnHienThi.setFont(new java.awt.Font("Segoe UI", 3, 13)); // NOI18N
        btnHienThi.setForeground(new java.awt.Color(255, 255, 255));
        btnHienThi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/HienThi.png"))); // NOI18N
        btnHienThi.setText("Hiển thị");
        btnHienThi.setBorder(null);
        btnHienThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHienThiActionPerformed(evt);
            }
        });

        btnThemExcel.setBackground(new java.awt.Color(0, 204, 204));
        btnThemExcel.setFont(new java.awt.Font("Segoe UI", 3, 13)); // NOI18N
        btnThemExcel.setForeground(new java.awt.Color(255, 255, 255));
        btnThemExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        btnThemExcel.setText("Thêm excel");
        btnThemExcel.setBorder(null);
        btnThemExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(btnThemExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(btnHienThi, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHienThi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Sản phẩm", jPanel10);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel33.setText("Mã thuộc tính");

        jLabel34.setText("Tên thuộc tính");

        rdoMauSac.setText("Màu sắc");
        rdoMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMauSacActionPerformed(evt);
            }
        });

        rdoSize.setText("Size");
        rdoSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoSizeActionPerformed(evt);
            }
        });

        rdoChatLieu.setText("Chất liệu");
        rdoChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChatLieuActionPerformed(evt);
            }
        });

        rdoHang.setText("Hãng");
        rdoHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoHangActionPerformed(evt);
            }
        });

        btnThemThuocTinh.setText("Thêm");
        btnThemThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuocTinhActionPerformed(evt);
            }
        });

        btnSuaThuocTinh.setText("Sửa");
        btnSuaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThuocTinhActionPerformed(evt);
            }
        });

        btnXoaThuocTinh.setText("Xóa");
        btnXoaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaThuocTinhActionPerformed(evt);
            }
        });

        ckbTrangThai.setText("Trạng thái");

        btnLamMoi.setText("Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        txtMaThuocTinh.setBackground(new java.awt.Color(238, 238, 238));
        txtMaThuocTinh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMaThuocTinh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        txtTenThuocTinh.setBackground(new java.awt.Color(238, 238, 238));
        txtTenThuocTinh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTenThuocTinh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(34, 355, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addComponent(rdoMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addComponent(rdoSize, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(rdoChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(57, 57, 57)))
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(rdoHang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(ckbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(195, 195, 195))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(btnThemThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(btnSuaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(btnXoaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(264, 264, 264))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoMauSac)
                    .addComponent(rdoSize)
                    .addComponent(rdoChatLieu)
                    .addComponent(rdoHang))
                .addGap(22, 22, 22)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemThuocTinh)
                    .addComponent(btnSuaThuocTinh)
                    .addComponent(btnXoaThuocTinh)
                    .addComponent(btnLamMoi))
                .addGap(19, 19, 19))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        tblThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã thuộc tính", "Tên thuộc tính", "Tình trạng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThuocTinh.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblThuocTinh.setSelectionBackground(new java.awt.Color(255, 204, 204));
        tblThuocTinh.setShowGrid(false);
        tblThuocTinh.setShowHorizontalLines(true);
        tblThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocTinhMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblThuocTinh);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1215, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Chi tiết giày", jPanel14);

        javax.swing.GroupLayout pnlSanPhamLayout = new javax.swing.GroupLayout(pnlSanPham);
        pnlSanPham.setLayout(pnlSanPhamLayout);
        pnlSanPhamLayout.setHorizontalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );
        pnlSanPhamLayout.setVerticalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        pnlTong.add(pnlSanPham, "card3");

        jPanel25.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel27.setText("Bộ lọc: ");

        cbbBoloc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm nay", "Theo ngày", "Theo năm" }));
        cbbBoloc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbBolocItemStateChanged(evt);
            }
        });

        jPanel27.setBackground(new java.awt.Color(255, 204, 204));
        jPanel27.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel44.setText("Tổng đơn hàng");

        lblTongDonHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTongDonHang.setForeground(new java.awt.Color(0, 51, 51));
        lblTongDonHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bill.png"))); // NOI18N

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel44)
                    .addComponent(lblTongDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTongDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel36)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        btnXuatFile.setBackground(new java.awt.Color(102, 255, 0));
        btnXuatFile.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnXuatFile.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        btnXuatFile.setText("Xuat File Excel");
        btnXuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileActionPerformed(evt);
            }
        });

        jPanel17.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Mã giày", "Tên giày", "Đơn giá", "Số lượng bán", "Tiền mặt", "Tiền chuyển khoản", "Giảm giá thêm(%)", "Doanh thu", "Ngày bán", "Trạng thái", "Lí do hủy"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongKe.setFocusable(false);
        tblThongKe.setRowHeight(25);
        tblThongKe.setSelectionBackground(new java.awt.Color(255, 204, 204));
        tblThongKe.setShowGrid(false);
        tblThongKe.setShowHorizontalLines(true);
        tblThongKe.getTableHeader().setReorderingAllowed(false);
        jScrollPane12.setViewportView(tblThongKe);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlTimkiemTong.setLayout(new java.awt.CardLayout());

        pnlTheoNgay.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel42.setText("Từ:");

        jLabel43.setText("Đến: ");

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        NgayBatDau.setDateFormatString("dd-MM-yyyy");

        NgayKetThuc.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout pnlTheoNgayLayout = new javax.swing.GroupLayout(pnlTheoNgay);
        pnlTheoNgay.setLayout(pnlTheoNgayLayout);
        pnlTheoNgayLayout.setHorizontalGroup(
            pnlTheoNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTheoNgayLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(NgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(btnTimKiem)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        pnlTheoNgayLayout.setVerticalGroup(
            pnlTheoNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTheoNgayLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlTheoNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlTheoNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(NgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTimKiem)
                        .addGroup(pnlTheoNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(jLabel43))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlTimkiemTong.add(pnlTheoNgay, "card2");

        pnlTheoNam.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel59.setText("Năm:");

        btnTimKiemTheoNam.setText("Tìm kiếm");
        btnTimKiemTheoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemTheoNamActionPerformed(evt);
            }
        });

        cbbNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2019", "2020", "2021", "2022" }));

        javax.swing.GroupLayout pnlTheoNamLayout = new javax.swing.GroupLayout(pnlTheoNam);
        pnlTheoNam.setLayout(pnlTheoNamLayout);
        pnlTheoNamLayout.setHorizontalGroup(
            pnlTheoNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTheoNamLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(cbbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addComponent(btnTimKiemTheoNam)
                .addGap(119, 119, 119))
        );
        pnlTheoNamLayout.setVerticalGroup(
            pnlTheoNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTheoNamLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlTheoNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiemTheoNam)
                    .addComponent(jLabel59)
                    .addComponent(cbbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlTimkiemTong.add(pnlTheoNam, "card2");

        jLabel61.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 102, 204));
        jLabel61.setText("Bảng doanh thu");

        btnMailDoanhThu.setBackground(new java.awt.Color(255, 0, 102));
        btnMailDoanhThu.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnMailDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        btnMailDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/email.png"))); // NOI18N
        btnMailDoanhThu.setText("Gửi mail");
        btnMailDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMailDoanhThuActionPerformed(evt);
            }
        });

        jPanel29.setBackground(new java.awt.Color(153, 255, 153));
        jPanel29.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel47.setText("Hóa đơn đang giao");

        lblHoaDonGiao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHoaDonGiao.setForeground(new java.awt.Color(0, 51, 51));
        lblHoaDonGiao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/download__1_-removebg-preview.png"))); // NOI18N

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHoaDonGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addGap(60, 60, 60))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(lblHoaDonGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel41.setBackground(new java.awt.Color(153, 153, 255));
        jPanel41.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel96.setText("Hóa đơn hủy");

        lblHoaDonHuy1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHoaDonHuy1.setForeground(new java.awt.Color(0, 51, 51));
        lblHoaDonHuy1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel105.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/download__2_-removebg-preview.png"))); // NOI18N

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblHoaDonHuy1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel96)))
                .addGap(80, 80, 80))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addComponent(jLabel96)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addComponent(lblHoaDonHuy1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addComponent(jLabel105)
                        .addContainerGap(30, Short.MAX_VALUE))))
        );

        jPanel28.setBackground(new java.awt.Color(255, 255, 153));
        jPanel28.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel45.setText("Doanh thu");

        lblDoanhthu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDoanhthu.setForeground(new java.awt.Color(0, 51, 51));
        lblDoanhthu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel46.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(102, 102, 102));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("VND");

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cart-9-48.png"))); // NOI18N

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(lblDoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addGap(94, 94, 94))))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel37)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel46)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addGap(529, 529, 529)
                                .addComponent(btnMailDoanhThu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXuatFile))
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel61))))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(cbbBoloc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(179, 179, 179)
                        .addComponent(pnlTimkiemTong, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(pnlTimkiemTong, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMailDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbbBoloc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)))
                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane4.addTab("Doanh thu", jPanel25);

        jPanel21.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblThongKe1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã giày", "Tên giày", "Màu sắc", "Size", "Chất liệu", "Số lượng tồn", "Số lượng bán", "Giá nhập", "Giá bán", "Doanh thu", "Năm bảo hành"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongKe1.setFocusable(false);
        tblThongKe1.setRowHeight(25);
        tblThongKe1.setSelectionBackground(new java.awt.Color(255, 204, 204));
        tblThongKe1.setShowGrid(false);
        tblThongKe1.setShowHorizontalLines(true);
        tblThongKe1.getTableHeader().setReorderingAllowed(false);
        jScrollPane13.setViewportView(tblThongKe1);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
        );

        cbbThongKeSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tat ca", "Top 5 san pham ban nhieu nhat", "Top 5 san pham doanh thu cao nhat", "Danh sach san pham co so luong ton thap" }));
        cbbThongKeSanPham.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbThongKeSanPhamItemStateChanged(evt);
            }
        });

        jLabel52.setText("Thống kê theo:");

        btnXuatFile1.setBackground(new java.awt.Color(102, 255, 0));
        btnXuatFile1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnXuatFile1.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatFile1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        btnXuatFile1.setText("Xuat File Excel");
        btnXuatFile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFile1ActionPerformed(evt);
            }
        });

        jPanel32.setBackground(new java.awt.Color(255, 204, 204));
        jPanel32.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel53.setText("Số giày đang kinh doanh");

        lblSoGiayKd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSoGiayKd.setForeground(new java.awt.Color(0, 51, 51));
        lblSoGiayKd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bill.png"))); // NOI18N

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lblSoGiayKd, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel53)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSoGiayKd, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel40)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel33.setBackground(new java.awt.Color(255, 255, 153));
        jPanel33.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel54.setText("Số giày đã hết hàng");

        lblSoGiayHet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSoGiayHet.setForeground(new java.awt.Color(0, 51, 51));
        lblSoGiayHet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cart-9-48.png"))); // NOI18N

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSoGiayHet, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel54)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addGap(18, 18, 18)
                        .addComponent(lblSoGiayHet, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel41)))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jPanel34.setBackground(new java.awt.Color(153, 255, 153));
        jPanel34.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel56.setText("Số giày sắp hết hàng");

        lblSoGiaySapHet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSoGiaySapHet.setForeground(new java.awt.Color(0, 51, 51));
        lblSoGiaySapHet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/download__1_-removebg-preview.png"))); // NOI18N

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel56)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblSoGiaySapHet, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSoGiaySapHet, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jLabel60.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 102, 204));
        jLabel60.setText("Bảng sản phẩm");

        btnMailSanPham.setBackground(new java.awt.Color(255, 0, 102));
        btnMailSanPham.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnMailSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnMailSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/email.png"))); // NOI18N
        btnMailSanPham.setText("Gửi mail");
        btnMailSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMailSanPhamActionPerformed(evt);
            }
        });

        pnlSoLuong.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtSoluong.setBackground(new java.awt.Color(238, 238, 238));
        txtSoluong.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSoluong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        jLabel55.setText("<=");

        jButton1.setText("Tìm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblLoi.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblLoi.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout pnlSoLuongLayout = new javax.swing.GroupLayout(pnlSoLuong);
        pnlSoLuong.setLayout(pnlSoLuongLayout);
        pnlSoLuongLayout.setHorizontalGroup(
            pnlSoLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSoLuongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(pnlSoLuongLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblLoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlSoLuongLayout.setVerticalGroup(
            pnlSoLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSoLuongLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(pnlSoLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLoi, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(cbbThongKeSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(pnlSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(440, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel60)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnMailSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXuatFile1)
                                .addGap(32, 32, 32))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel52)
                                    .addComponent(cbbThongKeSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(67, 67, 67))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(pnlSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXuatFile1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMailSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel60)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(21, 21, 21))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(41, 41, 41)
                        .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1213, Short.MAX_VALUE)
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 731, Short.MAX_VALUE)
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                    .addContainerGap(19, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(21, Short.MAX_VALUE)))
        );

        jTabbedPane4.addTab("Sản phẩm", jPanel18);

        tblChiTietGiaoCa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Ngày làm", "Giờ bắt đầu ", "Giờ kết thúc", "Tổng tiền trong ca", "Tiền phát sinh", "Tiền đã rút", "Trạng thái", "Ghi chú"
            }
        ));
        tblChiTietGiaoCa.setSelectionBackground(new java.awt.Color(255, 204, 204));
        tblChiTietGiaoCa.setShowGrid(false);
        tblChiTietGiaoCa.setShowHorizontalLines(true);
        jScrollPane7.setViewportView(tblChiTietGiaoCa);

        tblNhanvienTK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Chức vụ"
            }
        ));
        tblNhanvienTK.setSelectionBackground(new java.awt.Color(255, 204, 204));
        tblNhanvienTK.setShowGrid(false);
        tblNhanvienTK.setShowVerticalLines(true);
        tblNhanvienTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanvienTKMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblNhanvienTK);

        cbbThangLamViec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel29.setText("Tháng làm việc");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(cbbThangLamViec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1019, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(cbbThangLamViec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1218, Short.MAX_VALUE)
            .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel30Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 728, Short.MAX_VALUE)
            .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel30Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane4.addTab("Ca làm", jPanel30);

        javax.swing.GroupLayout pnlThongKeLayout = new javax.swing.GroupLayout(pnlThongKe);
        pnlThongKe.setLayout(pnlThongKeLayout);
        pnlThongKeLayout.setHorizontalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongKeLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        pnlThongKeLayout.setVerticalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongKeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pnlTong.add(pnlThongKe, "card2");

        jPanel36.setBorder(javax.swing.BorderFactory.createTitledBorder("Giảm Giá"));

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
        tblGiamGia.setSelectionBackground(new java.awt.Color(255, 204, 204));
        tblGiamGia.setShowGrid(false);
        tblGiamGia.setShowHorizontalLines(true);
        tblGiamGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGiamGiaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblGiamGia);

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel37.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản Lí Sản Phẩm"));

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
        tblChiTietSP.setSelectionBackground(new java.awt.Color(255, 204, 204));
        tblChiTietSP.setShowGrid(false);
        tblChiTietSP.setShowHorizontalLines(true);
        jScrollPane4.setViewportView(tblChiTietSP);

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel38.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi Tiết Giảm Giá"));

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
        tblChiTietGiamGia.setSelectionBackground(new java.awt.Color(255, 204, 204));
        tblChiTietGiamGia.setShowGrid(false);
        tblChiTietGiamGia.setShowHorizontalLines(true);
        tblChiTietGiamGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietGiamGiaMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblChiTietGiamGia);

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1186, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        jPanel39.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách Khuyến Mại"));

        jLabel98.setText("Mã");

        txtMa1.setBackground(new java.awt.Color(238, 238, 238));
        txtMa1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMa1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));
        txtMa1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMa1MouseClicked(evt);
            }
        });
        txtMa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMa1ActionPerformed(evt);
            }
        });

        jLabel99.setText("Phần Trăm");

        txtPhanTramGiam.setBackground(new java.awt.Color(238, 238, 238));
        txtPhanTramGiam.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPhanTramGiam.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        jLabel100.setText("Số Lượng");

        txtSoLuong1.setBackground(new java.awt.Color(238, 238, 238));
        txtSoLuong1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        txtSoLuong1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSoLuong1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        jLabel101.setText("Ngày Bắt Đầu");

        jLabel102.setText("Ngày Kết Thúc");

        jLabel103.setText("Điều Kiện");

        txtDieuKien.setBackground(new java.awt.Color(238, 238, 238));
        txtDieuKien.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDieuKien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        jLabel104.setText("Trạng Thái");

        btnSua2.setBackground(new java.awt.Color(0, 204, 204));
        btnSua2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnSua2.setForeground(new java.awt.Color(255, 255, 255));
        btnSua2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh (1).png"))); // NOI18N
        btnSua2.setText("Sửa");
        btnSua2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua2ActionPerformed(evt);
            }
        });

        btnXoa2.setBackground(new java.awt.Color(0, 204, 204));
        btnXoa2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnXoa2.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/trash.png"))); // NOI18N
        btnXoa2.setText("Xóa");
        btnXoa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoCo);
        rdoCo.setText("Đang hoạt động");

        buttonGroup1.add(rdokhong);
        rdokhong.setText("Dừng hoạt động");

        DateNgayBatDau.setDateFormatString("yyyy-MM-dd");

        btnThem1.setBackground(new java.awt.Color(0, 204, 204));
        btnThem1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnThem1.setForeground(new java.awt.Color(255, 255, 255));
        btnThem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add (1).png"))); // NOI18N
        btnThem1.setText("Thêm");
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        lblMa1.setForeground(new java.awt.Color(255, 0, 0));
        lblMa1.setText("             ");

        lblPhanTramGiam.setForeground(new java.awt.Color(255, 0, 0));
        lblPhanTramGiam.setText("             ");

        lblNgayBatDau.setText("             ");

        lblSoLuong1.setForeground(new java.awt.Color(255, 0, 0));
        lblSoLuong1.setText("             ");

        lblNgayKetThuc.setText("             ");

        lblDieuKien.setForeground(new java.awt.Color(255, 0, 0));
        lblDieuKien.setText("             ");

        DateNgayKetThuc.setDateFormatString("yyyy-MM-dd");

        btnCapNhap.setBackground(new java.awt.Color(0, 204, 204));
        btnCapNhap.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnCapNhap.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Sua123.png"))); // NOI18N
        btnCapNhap.setText("Cập Nhập");
        btnCapNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel39Layout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPhanTramGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMa1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSoLuong1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel39Layout.createSequentialGroup()
                                    .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMa1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                                    .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtSoLuong1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPhanTramGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel39Layout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(DateNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                                    .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel103)
                                        .addComponent(jLabel102))
                                    .addGap(29, 29, 29)
                                    .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnSua2)
                                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtDieuKien, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblNgayKetThuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(DateNgayKetThuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel39Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel104)
                                .addGap(78, 78, 78))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnCapNhap)
                                .addGap(33, 33, 33)))
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDieuKien, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel39Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoCo)
                                    .addComponent(rdokhong, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem1)
                .addGap(137, 137, 137)
                .addComponent(btnXoa2)
                .addGap(24, 24, 24))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel98)
                    .addComponent(txtMa1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMa1)
                .addGap(2, 2, 2)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel99)
                    .addComponent(txtPhanTramGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPhanTramGiam)
                .addGap(18, 18, 18)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel100)
                    .addComponent(txtSoLuong1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(lblSoLuong1)
                .addGap(18, 18, 18)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel101)
                    .addComponent(DateNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNgayBatDau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel102))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNgayKetThuc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel103)
                    .addComponent(txtDieuKien, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(lblDieuKien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel104)
                    .addComponent(rdoCo))
                .addGap(18, 18, 18)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(rdokhong)
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                        .addComponent(btnCapNhap)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem1)
                            .addComponent(btnSua2)
                            .addComponent(btnXoa2))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout pnlGiamGiaLayout = new javax.swing.GroupLayout(pnlGiamGia);
        pnlGiamGia.setLayout(pnlGiamGiaLayout);
        pnlGiamGiaLayout.setHorizontalGroup(
            pnlGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGiamGiaLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlGiamGiaLayout.createSequentialGroup()
                        .addGroup(pnlGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(132, Short.MAX_VALUE))
        );
        pnlGiamGiaLayout.setVerticalGroup(
            pnlGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGiamGiaLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(pnlGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGiamGiaLayout.createSequentialGroup()
                        .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(31, 31, 31)
                        .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pnlTong.add(pnlGiamGia, "card2");

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý Nhân Viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        jLabel77.setText("Mã nhân viên");

        txtMa.setBackground(new java.awt.Color(238, 238, 238));
        txtMa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        jLabel78.setText("Tên nhân viên");

        jLabel79.setText("Ngày sinh");

        jLabel80.setText("Số điện thoại");

        jLabel81.setText("Địa chỉ");

        jLabel82.setText("Email");

        jLabel83.setText("Trạng thái");

        jLabel84.setText("Chức vụ");

        txtTen.setBackground(new java.awt.Color(238, 238, 238));
        txtTen.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        txtNgaySinh.setBackground(new java.awt.Color(238, 238, 238));
        txtNgaySinh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNgaySinh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        txtSDT.setBackground(new java.awt.Color(238, 238, 238));
        txtSDT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        txtDiaChi.setBackground(new java.awt.Color(238, 238, 238));
        txtDiaChi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        buttonGroup2.add(rdoLam);
        rdoLam.setText("Đang làm");

        buttonGroup2.add(rdoNghi);
        rdoNghi.setText("Đang nghỉ");

        cboChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtEmail.setBackground(new java.awt.Color(238, 238, 238));
        txtEmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        jLabel62.setText("Mật khẩu");

        txtMatKhau.setBackground(new java.awt.Color(238, 238, 238));
        txtMatKhau.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMatKhau.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));

        lblMa.setForeground(new java.awt.Color(255, 0, 0));

        lblTen.setForeground(new java.awt.Color(255, 0, 0));

        lblNgaySinh.setForeground(new java.awt.Color(255, 0, 0));

        lblSDT.setForeground(new java.awt.Color(255, 0, 0));

        lblDiaChi.setForeground(new java.awt.Color(255, 0, 0));

        lblEmail.setForeground(new java.awt.Color(255, 0, 0));

        lblMatKhau.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel84)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(rdoLam)
                        .addGap(18, 18, 18)
                        .addComponent(rdoNghi))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62)
                    .addComponent(jLabel82)
                    .addComponent(jLabel83)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel80)
                            .addComponent(jLabel79)
                            .addComponent(jLabel78)
                            .addComponent(jLabel77)
                            .addComponent(jLabel81))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTen, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMa, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMa, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTen, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel79))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel80))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel81))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel82))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62))
                .addGap(18, 18, 18)
                .addComponent(lblMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel83)
                    .addComponent(rdoLam)
                    .addComponent(rdoNghi))
                .addGap(32, 32, 32)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84)
                    .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(101, Short.MAX_VALUE))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 204, 204), new java.awt.Color(0, 204, 204)));

        jLabel20.setText("Lọc theo chức vụ");

        cbbLocCv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Vui lòng chọn-", "Chủ cửa hàng", "Thu ngân", "Nhân viên" }));
        cbbLocCv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocCvActionPerformed(evt);
            }
        });

        jLabel28.setText("Tìm kiếm");

        txtTimKiemNV.setBackground(new java.awt.Color(238, 238, 238));
        txtTimKiemNV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 0)));
        txtTimKiemNV.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemNVCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(31, 31, 31)
                .addComponent(cbbLocCv, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154)
                .addComponent(jLabel28)
                .addGap(29, 29, 29)
                .addComponent(txtTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(cbbLocCv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(txtTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        jPanel42.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 204), new java.awt.Color(0, 204, 204), new java.awt.Color(0, 204, 204), new java.awt.Color(0, 204, 204)));

        tblNhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Chức vụ", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanvien.setSelectionBackground(new java.awt.Color(255, 204, 204));
        tblNhanvien.setShowGrid(false);
        tblNhanvien.setShowHorizontalLines(true);
        tblNhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanvientblNhanvienMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tblNhanvien);

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        btnThemNV.setBackground(new java.awt.Color(0, 102, 102));
        btnThemNV.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnThemNV.setForeground(new java.awt.Color(255, 255, 255));
        btnThemNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add (1).png"))); // NOI18N
        btnThemNV.setText("Thêm");
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVbtnThem1ActionPerformed(evt);
            }
        });

        btnSuaNV.setBackground(new java.awt.Color(0, 102, 102));
        btnSuaNV.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnSuaNV.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Sua123.png"))); // NOI18N
        btnSuaNV.setText("Sửa");
        btnSuaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNVbtnSua1ActionPerformed(evt);
            }
        });

        btnResetNV.setBackground(new java.awt.Color(0, 102, 102));
        btnResetNV.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnResetNV.setForeground(new java.awt.Color(255, 255, 255));
        btnResetNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh (1).png"))); // NOI18N
        btnResetNV.setText("Làm mới");
        btnResetNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetNVbtnResetActionPerformed(evt);
            }
        });

        btnLoadNV.setBackground(new java.awt.Color(0, 102, 102));
        btnLoadNV.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnLoadNV.setForeground(new java.awt.Color(255, 255, 255));
        btnLoadNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add_kh.png"))); // NOI18N
        btnLoadNV.setText("Hiển thị");
        btnLoadNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadNVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(btnThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(btnSuaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123)
                        .addComponent(btnResetNV, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLoadNV, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetNV, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoadNV, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout pnlNhanvienLayout = new javax.swing.GroupLayout(pnlNhanvien);
        pnlNhanvien.setLayout(pnlNhanvienLayout);
        pnlNhanvienLayout.setHorizontalGroup(
            pnlNhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNhanvienLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );
        pnlNhanvienLayout.setVerticalGroup(
            pnlNhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNhanvienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        pnlTong.add(pnlNhanvien, "card2");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/NenGiaoCa.png"))); // NOI18N

        javax.swing.GroupLayout pnlNenGiaoCaLayout = new javax.swing.GroupLayout(pnlNenGiaoCa);
        pnlNenGiaoCa.setLayout(pnlNenGiaoCaLayout);
        pnlNenGiaoCaLayout.setHorizontalGroup(
            pnlNenGiaoCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNenGiaoCaLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(0, 8, Short.MAX_VALUE))
        );
        pnlNenGiaoCaLayout.setVerticalGroup(
            pnlNenGiaoCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlTong.add(pnlNenGiaoCa, "card7");

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Quyen.png"))); // NOI18N

        javax.swing.GroupLayout pnlQuyenLayout = new javax.swing.GroupLayout(pnlQuyen);
        pnlQuyen.setLayout(pnlQuyenLayout);
        pnlQuyenLayout.setHorizontalGroup(
            pnlQuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuyenLayout.createSequentialGroup()
                .addComponent(jLabel16)
                .addGap(0, 136, Short.MAX_VALUE))
        );
        pnlQuyenLayout.setVerticalGroup(
            pnlQuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlTong.add(pnlQuyen, "card7");

        Goc.add(pnlTong, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 26, 1370, 790));

        jLabel35.setText("Xin Chào:");
        Goc.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 80, 20));
        Goc.add(txtTenNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 168, 20));

        lblDongHo.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        lblDongHo.setForeground(new java.awt.Color(102, 102, 102));
        lblDongHo.setText("ThoiGianODayNhe");
        Goc.add(lblDongHo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 0, 120, 20));

        lblNgayHienTai.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        lblNgayHienTai.setForeground(new java.awt.Color(102, 102, 102));
        lblNgayHienTai.setText("jLabel28");
        Goc.add(lblNgayHienTai, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 0, 100, 20));

        lblBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBanHangMouseClicked(evt);
            }
        });
        Goc.add(lblBanHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 190, 70));

        lblSanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSanphamMouseClicked(evt);
            }
        });
        Goc.add(lblSanpham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 200, 60));

        lblThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThoatMouseClicked(evt);
            }
        });
        Goc.add(lblThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 730, 190, 60));

        lblNhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNhanvienMouseClicked(evt);
            }
        });
        Goc.add(lblNhanvien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 200, 60));

        lblGiaoCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGiaoCaMouseClicked(evt);
            }
        });
        Goc.add(lblGiaoCa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 200, 60));

        lblGiamGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGiamGiaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblGiamGiaMouseEntered(evt);
            }
        });
        Goc.add(lblGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 200, 50));

        lblThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThongKeMouseClicked(evt);
            }
        });
        Goc.add(lblThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 580, 200, 60));

        lblDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDangXuatMouseClicked(evt);
            }
        });
        Goc.add(lblDangXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 660, 190, 50));

        getContentPane().add(Goc, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 0, 1590, 820));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void getFormHDCT() {
        int check = 0;
        //int slsp = Integer.parseInt(JOptionPane.showInputDialog("Số lượng sản phẩm muốn mua"));
        int rowhttao = tblHoaDonCho.getSelectedRow();
        int rowhdtreo = tblHoaDonTreo.getSelectedRow();
        String slsp = JOptionPane.showInputDialog("Số lượng sản phẩm muốn mua");
        int row = tblSanPhamBanHang.getSelectedRow();
        String masp = tblSanPhamBanHang.getValueAt(row, 0).toString();
        int slgiay = SanPhanBanHangRepository.getSLGiay(masp);
        try {
            Integer.parseInt(slsp);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nhập số lượng chưa chính xác");
            return;
        }
        if (slsp == null) {
            return;
        } else if (slsp.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số lượng tối thiểu là 1");
            return;
        } else if (Integer.parseInt(slsp) < 1) {
            JOptionPane.showMessageDialog(this, "Số lượng tối thiểu là 1");
            return;
        } else if (Integer.parseInt(slsp) > slgiay) {
            JOptionPane.showMessageDialog(this, "Số lượng không đủ");
            return;
        }

        String mahd = txtMaHD.getText();
        String idhd = HoaDonBanHangRepository.getIDHDByMaHD(mahd);

        if (!slsp.trim().isEmpty()) {
            int rowhd = tblHoaDonCho.getSelectedRow();
            int rowsp = tblSanPhamBanHang.getSelectedRow();
            int slcon = SanPhanBanHangRepository.getSLGiay(masp);
            int slthuc = slcon - Integer.parseInt(slsp);
            SanPhanBanHangRepository.updateSLSP(slthuc, masp);
            String idsp = SanPhanBanHangRepository.getIDSpByMaSP(masp);
            double dongia = Double.parseDouble(tblSanPhamBanHang.getValueAt(rowsp, 5).toString()) - (Double.parseDouble(tblSanPhamBanHang.getValueAt(rowsp, 5).toString())
                    * (Double.parseDouble(tblSanPhamBanHang.getValueAt(rowsp, 6).toString()) / 100));

            for (int i = 0; i < listgh.size(); i++) {
                if (masp.equals(listgh.get(i).getMasp())) {
                    HoaDonCTRepository.updateHDCT(listgh.get(i).getSl() + Integer.parseInt(slsp), idhd, idsp);
                    check = 1;
                }
            }
            fillSPBH();
            HoaDonCT hdct = new HoaDonCT(idhd, idsp, Integer.parseInt(slsp), dongia);
            if (check == 0) {
                HoaDonCTRepository.addHDCT(hdct);
            }
            if (pnlGiaoHang.isVisible() == true) {
                txtTongTienGH.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd)));
                txtCanTraGH.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd) - (HoaDonCTRepository.getSumTT(mahd) * Double.parseDouble(txtGiamGiaGH.getText()) / 100) + Double.parseDouble(txtPhiShip.getText())));
                txtTienKhachTra.setText(String.valueOf(Double.parseDouble(txtCanTraGH.getText())));
            } else if (pnlTaiQuay.isVisible() == true) {
                txtTongTienTQ.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd)));
                txtTienKhachCanTra.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd) - (HoaDonCTRepository.getSumTT(mahd) * Double.parseDouble(txtKhuyenMaiThemTQ.getText()) / 100)));
                txtTienDuTQ.setText(String.valueOf(Double.parseDouble(txtTienMatTQ.getText()) + Double.parseDouble(txtTienCKTQ.getText()) - Double.parseDouble(txtTienKhachCanTra.getText())));
            }

        }
    }

    public void fillHDCT() {
        String mahd = txtMaHD.getText();
        listgh = HoaDonBanHangRepository.getHDCTByMaHD(mahd);
        dtm = (DefaultTableModel) tblGioHang.getModel();
        dtm.setRowCount(0);
        for (int i = 0; i < listgh.size(); i++) {
            Object[] data = new Object[]{
                listgh.get(i).getMasp(),
                listgh.get(i).getTen(),
                listgh.get(i).getSl(),
                listgh.get(i).getDonGia(),};
            dtm.addRow(data);
        }
    }

    public KhachHang getFormKhacHang() {
        int slkh = KhachHangRepository.DemKH();
        String ma = "KH" + ++slkh;
        String hoten = txtTenKHGH.getText();
        String ns = "";
        if (txtNgaySinhKH.getText().equals("")) {
            ns = null;
        } else {
            ns = txtNgaySinhKH.getText();
        }
        String sdt = txtSDTGH.getText();
        String diachi = txtDiaChiGH.getText();
        System.out.println(ns);
        return new KhachHang(null, ma, hoten, ns, sdt, diachi);
    }

    // validate kahch hang
    boolean validateKhachHang() {
        SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
        String sdt = txtSDTGH.getText();
        String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        boolean ktr = sdt.matches(reg);

        if (txtTenKHGH.getText().trim().isEmpty()) {
            lblTenKH.setText("Chưa nhập tên khách hàng");

        } else {
            lblTenKH.setText(" ");
        }
        if (txtDiaChiGH.getText().trim().isEmpty()) {
            lblDiaChiGH.setText("Chưa nhập địa chỉ");
        } else {
            lblDiaChiGH.setText(" ");
        }
        if (txtSDTGH.getText().trim().isEmpty()) {
            lblSDTGH.setText("Chưa nhập sdt khách hàng");
        } else if (ktr == false) {
            lblSDTGH.setText("Nhập sai định dạng số điện thoại");
        } else {
            lblSDTGH.setText(" ");
        }
        try {
            fm.parse(txtNgaySinhKH.getText());
            lblNgaySinhGH.setText(" ");
        } catch (ParseException ex) {
            lblNgaySinhGH.setText("Nhập ngày sinh theo định dạng yyyy/MM/dd");
        }

        return false;
    }

    boolean checkKhachHang() {
        if (validateKhachHang()) {
            return false;
        } else if (!txtTenKHGH.getText().trim().isEmpty()
                && !txtDiaChiGH.getText().trim().isEmpty()
                && !txtSDTGH.getText().trim().isEmpty()
                && !txtNgaySinhKH.getText().trim().isEmpty()) {
            return true;
        }
        return false;
    }

    public HoaDonBanHang getFromHDBH() {
        int sl = HoaDonBanHangRepository.getSoHD();
        String tennv = txtTenNhanVien.getText();
        String ma = "HD" + ++sl;
        String ngaytao = txtNgayTao.getText();
        String idnv = HoaDonBanHangRepository.getIDNVByHoTen(tennv);
        String gioht = String.valueOf(java.time.LocalTime.now());
        String time = gioht.substring(0, 8);

        String tt = "";
        if (pnlTaiQuay.isVisible() == true) {
            tt = "Tại quầy";
        } else {
            tt = "Giao hàng";
        }

        return new HoaDonBanHang(ma, idnv, null, tt, ngaytao, null, null, null, time, null, null, 2, 0.0, 0.0, 0.0, 0.0);
    }

    // Hóa Đơn in 
    void fillHoaDonIn() {
        HoaDonBanHangRepository _BanHangRepository = new HoaDonBanHangRepository();
        DefaultTableModel mol = (DefaultTableModel) HoaDonInView.tblHoaDonIn.getModel();
        mol.setRowCount(0);
        for (var x : _DonInService.getAll(_BanHangRepository.getIDHDByMaHD(txtMaHD.getText()))) {
            mol.addRow(new Object[]{
                x.getTenSanPham(),
                x.getSoLuong(),
                x.getDonGia(),
                x.getDonGia() * x.getSoLuong()
            });
        }
    }

    public void hoaDonInTQ() {
        HoaDonBanHangRepository _BanHangRepository = new HoaDonBanHangRepository();
        HoaDonInView HDV = new HoaDonInView();

        HDV.lblTongCong.setText(txtTongTienTQ.getText());
        System.out.println(txtTienMatTQ.getText());
        System.out.println(txtTienCKTQ.getText());
        HDV.lblTienKhachTRa.setText(
                Double.parseDouble(txtTienMatTQ.getText())
                + Double.parseDouble(txtTienCKTQ.getText()) + "");
        double tienKhuyenMai = 0;
        tienKhuyenMai = Double.parseDouble(txtTongTienTQ.getText()) - Double.parseDouble(txtTienKhachCanTra.getText());
        HDV.lblKhuyenMai.setText("" + tienKhuyenMai + "");

        Double tienPhaiTra = Double.parseDouble(HDV.lblTongCong.getText()) - tienKhuyenMai;
        HDV.lblTongTienPhaiTra.setText(tienPhaiTra + "");
        Double tienKhachTra = Double.parseDouble(HDV.lblTienKhachTRa.getText()) - Double.parseDouble(HDV.lblTongTienPhaiTra.getText());
        HDV.lblTienThua.setText(tienKhachTra + "");
        HDV.lblMaHD.setText(txtMaHD.getText());
        HDV.lblTenNhanVien.setText(txtTenNhanVien.getText());
        fillHoaDonIn();
        HDV.setVisible(true);
    }

    public void hoaDonInGH() {
        HoaDonBanHangRepository _BanHangRepository = new HoaDonBanHangRepository();
        HoaDonInView HDV = new HoaDonInView();
        HDV.lblTongCong.setText(txtTongTienGH.getText());
        System.out.println(txtTienKhachTra.getText());
        HDV.lblTienKhachTRa.setText(
                Double.parseDouble(txtTienKhachTra.getText()) + "");
        double tienKhuyenMai = 0;
        tienKhuyenMai = Double.parseDouble(txtTongTienGH.getText()) - Double.parseDouble(txtCanTraGH.getText());
        HDV.lblKhuyenMai.setText(" " + tienKhuyenMai + "");

        Double tienPhaiTra = Double.parseDouble(HDV.lblTongCong.getText()) - tienKhuyenMai;
        HDV.lblTongTienPhaiTra.setText(tienPhaiTra + "");
        Double tienKhachTra = Double.parseDouble(HDV.lblTienKhachTRa.getText()) - Double.parseDouble(HDV.lblTongTienPhaiTra.getText());
        HDV.lblTienThua.setText(tienKhachTra + "");
        HDV.lblMaHD.setText(txtMaHD.getText());
        HDV.lblTenNhanVien.setText(txtTenNhanVien.getText());
        fillHoaDonIn();
        HDV.setVisible(true);
    }

    private void clearlblBHTQ() {
        lblGiamGiaTQ.setText(" ");
        lblTienChuyenKhoan.setText(" ");
        lblTienMatTQ.setText(" ");
        lblTienDuTQ.setText(" ");
    }

    public boolean checkBHTQ() {
        clearlblBHTQ();
        Double kh;
        String reg = "[0-9_-]{1,12}$";
        boolean regexTienKhuyenMai = txtKhuyenMaiThemTQ.getText().matches(reg);
        boolean regexTienCK = txtTienCKTQ.getText().matches(reg);
        boolean regexTietMat = txtTienMatTQ.getText().matches(reg);

        try {
            kh = Double.parseDouble(this.txtKhuyenMaiThemTQ.getText());
        } catch (NumberFormatException e) {
            lblGiamGiaTQ.setText("Giảm giá phải là số");

        }
        Double ck;
        try {
            ck = Double.parseDouble(txtTienCKTQ.getText());
        } catch (NumberFormatException e) {
            lblTienChuyenKhoan.setText("Tiền chuyển khoản chưa chính xác");

        }
        Double tm;
        try {
            tm = Double.parseDouble(txtTienMatTQ.getText());
        } catch (NumberFormatException e) {
            lblTienMatTQ.setText("Tiền mặt chưa chính xác");

        }
        if (Double.parseDouble(txtTienDuTQ.getText()) < 0.0) {
            lblTienDuTQ.setText("Khách hàng chưa trả đủ tiền");

        } else {
            lblTienDuTQ.setText(" ");
        }
        if (txtTienCKTQ.getText().trim().isBlank()) {
            lblTienChuyenKhoan.setText("Bạn chưa nhập tiền chuyển khoản");

        } else if (regexTienCK == true) {
            lblTienChuyenKhoan.setText("Nhập sai tiền chuyển khoản");

        }
        if (txtTienMatTQ.getText().isBlank()) {
            lblTienMatTQ.setText("Bạn chưa nhập tiền mặt");

        } else if (regexTietMat == true) {
            lblTienMatTQ.setText("Nhập sai tiền mặt ");

        }
        if (txtKhuyenMaiThemTQ.getText().isBlank()) {
            lblGiamGiaTQ.setText("Bạn chưa nhập giảm giá");

        }
        if (regexTienKhuyenMai == true) {
            lblGiamGiaTQ.setText("Nhập sai % giảm giá");

        } else if (Double.parseDouble(txtKhuyenMaiThemTQ.getText()) < 0 || Double.parseDouble(txtKhuyenMaiThemTQ.getText()) > 50) {
            lblGiamGiaTQ.setText("Giảm giá nằm ngoài khoảng");

        }

        return false;

    }

    boolean checkBHTQThanhCong() {
        if (checkBHTQ()) {
            return false;
        } else if (Double.parseDouble(txtTienDuTQ.getText()) >= 0
                && Double.parseDouble(txtTienCKTQ.getText()) >= 0
                && Double.parseDouble(txtTienMatTQ.getText()) >= 0
                && Double.parseDouble(txtKhuyenMaiThemTQ.getText()) >= 0) {
            return true;
        }
        return false;
    }

    private boolean validateHoaDonDG() {
        String reg = "[a-zA-Z_-]{1,12}$";
        boolean regexTienKhuyenMai = txtGiamGiaGH.getText().matches(reg);
        boolean regexPhiShip = txtPhiShip.getText().matches(reg);
        boolean regexTietKhachTra = txtTienKhachTra.getText().matches(reg);

        if (txtPhiShip.getText().equals("")) {
            lblPhiShip.setText("Phí ship không được để trống");

        } else if (regexPhiShip == true) {
            lblPhiShip.setText("Nhập sai phí ship");
        } else {
            lblPhiShip.setText(" ");
        }
        try {
            Double.parseDouble(txtPhiShip.getText());
        } catch (NumberFormatException e) {
            lblPhiShip.setText("Phí ship chưa chính xác");
        }

        if (txtGiamGiaGH.getText().equals("")) {
            lblGiamGiaGH.setText("Giảm giá không được để trống");

        } else if (regexTienKhuyenMai == true) {
            lblGiamGiaGH.setText("Nhập sai giảm giá");
        } else {
            lblGiamGiaGH.setText(" ");
        }
        try {
            Double.parseDouble(txtTienKhachTra.getText());
        } catch (NumberFormatException e) {
            lblTienKhachTra.setText("Tiền khách trả chưa chính xác");
        }
        if (txtTienKhachTra.getText().equals("")) {
            lblTienKhachTra.setText("Tiền khách trả không được để trống");

        } else if (regexTietKhachTra == true) {
            lblTienKhachTra.setText("Nhập sai tiền khách trả");
        } else {
            lblTienKhachTra.setText(" ");
        }
        if (txtMaVanChuyen.getText().equals("")) {
            lblMaVanChuyenGH.setText("Mã vận chuyển không được để trống");

        } else {
            lblMaVanChuyenGH.setText(" ");
        }

        Pattern gh6 = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]]", Pattern.CASE_INSENSITIVE);
        Matcher vc = gh6.matcher(txtMaVanChuyen.getText());
        boolean cons6 = vc.find();
        {
            if (cons6 == true) {
                lblMaVanChuyenGH.setText("Không nhập kí tự trong mã vận chuyển");

            }
        }
        return false;
    }

    boolean checkBHDGThanhCong() {
        if (validateHoaDonDG()) {
            return false;
        } else if (!txtGiamGiaGH.getText().trim().isEmpty()
                && !txtPhiShip.getText().trim().isEmpty()
                && !txtTienKhachTra.getText().trim().isEmpty()
                && !txtMaVanChuyen.getText().trim().isEmpty()) {
            return true;
        }
        return false;
    }

    public void fillHDTReo() {
        listhdbh = HoaDonBanHangRepository.getAllHHTreo();
        dtm = (DefaultTableModel) tblHoaDonTreo.getModel();
        dtm.setRowCount(0);
        for (int i = 0; i < listhdbh.size(); i++) {
            Object[] data = new Object[]{
                listhdbh.get(i).getMa(),
                listhdbh.get(i).getTennv(),
                listhdbh.get(i).getTenkh(),
                listhdbh.get(i).getLiDo(),};
            dtm.addRow(data);
        }
    }
    private void popupTreoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupTreoHDActionPerformed
        // TODO add your handling code here:
        int rowhd = tblHoaDonGiao.getSelectedRow();
        String idhd = HoaDonBanHangRepository.getIDHDByMaHD(tblHoaDonGiao.getValueAt(rowhd, 0).toString());
        String text = JOptionPane.showInputDialog("Lí do treo đơn", "Đơn Treo");
        int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn treo hóa đơn ? ");
        if (chon == JOptionPane.YES_OPTION) {
            HoaDonBanHang hdgiao = getFormHoaDonBanHangGiao();
            HoaDonBanHangRepository.updateTTTReo(hdgiao, text, idhd);
            JOptionPane.showMessageDialog(this, "Hóa đơn chuyển sang trạng thái treo");
            fillHDTReo();
            fillHDGiao();
            fillHDCT();
            clearHDGH();
            dtm = (DefaultTableModel) tblGioHang.getModel();
            dtm.setRowCount(0);
        }

    }//GEN-LAST:event_popupTreoHDActionPerformed

    private void popupHoanThanhHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupHoanThanhHDActionPerformed
        // TODO add your handling code here:
        int rowhd = tblHoaDonGiao.getSelectedRow();
        String idhd = HoaDonBanHangRepository.getIDHDByMaHD(tblHoaDonGiao.getValueAt(rowhd, 0).toString());
        int chon = JOptionPane.showConfirmDialog(this, "Xác nhận giao thành công đơn hàng");
        if (chon == JOptionPane.YES_OPTION) {
            HoaDonBanHang hdgiao = getFormHoaDonBanHangGiao();
            HoaDonBanHangRepository.updateTTHD(hdgiao, idhd);
            JOptionPane.showMessageDialog(this, "Giao hàng thành công");
            fillHDHT();
            fillHDTReo();
            fillHDGiao();
            fillHDCT();
            dtm = (DefaultTableModel) tblGioHang.getModel();
            dtm.setRowCount(0);
            clearHDGH();
        }

    }//GEN-LAST:event_popupHoanThanhHDActionPerformed

    private void popupHoanThanhHDTreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupHoanThanhHDTreoActionPerformed

        int rowhd = tblHoaDonTreo.getSelectedRow();
        String mahd = tblHoaDonTreo.getValueAt(rowhd, 0).toString();
        String idhd = HoaDonBanHangRepository.getIDHDByMaHD(mahd);
        if (HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhd).equals("Tại quầy")) {
            int chon = JOptionPane.showConfirmDialog(this, "Xác nhận thành công đơn hàng");
            if (chon == JOptionPane.YES_OPTION) {
                HoaDonBanHang hdbh = getFormHoaDonBanHang();
                HoaDonBanHangRepository.updateTTHD(hdbh, idhd);
                JOptionPane.showMessageDialog(this, "Giao hàng thành công");
                fillHDHT();
                fillHDTReo();
                fillHDGiao();
                fillHDCT();
                dtm = (DefaultTableModel) tblGioHang.getModel();
                dtm.setRowCount(0);
                clearHDTQ();
            }

        }

        if (HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhd).equals("Giao hàng")) {
            int chon = JOptionPane.showConfirmDialog(this, "Xác nhận giao thành công đơn hàng");
            if (chon == JOptionPane.YES_OPTION) {
                HoaDonBanHang hdgiao = getFormHoaDonBanHangGiao();
                HoaDonBanHangRepository.updateTTHD(hdgiao, idhd);
                JOptionPane.showMessageDialog(this, "Giao hàng thành công");
                fillHDHT();
                fillHDTReo();
                fillHDGiao();
                fillHDCT();
                dtm = (DefaultTableModel) tblGioHang.getModel();
                dtm.setRowCount(0);
                clearHDGH();
            }

        }

        dtm = (DefaultTableModel) tblGioHang.getModel();
        dtm.setRowCount(0);
//        HoaDonBanHangRepository.updateTTDG(idhd);

        fillHDCho();
        fillHDHT();
        fillHDGiao();
        fillHDTReo();
        clearHDGH();
        clearHDTQ();
    }//GEN-LAST:event_popupHoanThanhHDTreoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int rowhdt = tblHoaDonCho.getSelectedRow();
        int row = tblGioHang.getRowCount();
        String mahdt = tblHoaDonCho.getValueAt(rowhdt, 0).toString();
        String idhdt = HoaDonBanHangRepository.getIDHDByMaHD(mahdt);
        int tt1 = HoaDonBanHangRepository.getTTByIDHD(idhdt);
        if (row > 0) {
            JOptionPane.showMessageDialog(this, "Không để giỏ hàng còn hàng khi hủy đơn");
            return;
        }
        if (tt1 == 2) {
            int chon = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn hủy hóa đơn không ?");
            if (chon == JOptionPane.YES_OPTION) {
                dtm = (DefaultTableModel) tblHoaDonCho.getModel();
                HoaDonBanHangRepository.deleteHD(idhdt);
                JOptionPane.showMessageDialog(this, "Hủy hóa đơn tạo");
            }
        }
        dtm = (DefaultTableModel) tblGioHang.getModel();
        dtm.setRowCount(0);
        fillHDCho();
        clearHDGH();
        clearHDTQ();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        int rowhd = tblHoaDonGiao.getSelectedRow();
        String idhd = HoaDonBanHangRepository.getIDHDByMaHD(tblHoaDonGiao.getValueAt(rowhd, 0).toString());
        String lydo = JOptionPane.showInputDialog("Lí do hủy", "Hủy đơn");
        if (lydo == null) {
            return;
        }
        if (!lydo.equals("")) {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn hủy đơn hàng không ?");
            if (chon == JOptionPane.YES_OPTION) {
                HoaDonBanHangRepository.updateHuyHdGiao(lydo, idhd);
                JOptionPane.showMessageDialog(this, "Đã hủy hóa đơn đang giao");
                fillHDGiao();
                dtm = (DefaultTableModel) tblGioHang.getModel();
                dtm.setRowCount(0);
                clearHDGH();
            }

        }

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        int rowhd = tblHoaDonTreo.getSelectedRow();
        String idhd = HoaDonBanHangRepository.getIDHDByMaHD(tblHoaDonTreo.getValueAt(rowhd, 0).toString());
        String lydo = JOptionPane.showInputDialog("Lí do hủy", "Hủy đơn");
        if (lydo == null) {
            return;
        }
        if (!lydo.equals("")) {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn Hủy HĐ ? ");
            if (chon == JOptionPane.YES_OPTION) {
                HoaDonBanHangRepository.updateHuyHdGiao(lydo, idhd);
                JOptionPane.showMessageDialog(this, "Đã hủy hóa đơn đang treo");
                fillHDTReo();
                dtm = (DefaultTableModel) tblGioHang.getModel();
                dtm.setRowCount(0);
                clearHDGH();
            }

        }


    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private MaGiamGia getData() {
        MaGiamGia ma = new MaGiamGia();
        ma.setMaGiamGia(txtMa1.getText());
        ma.setPhanTramGiam(Integer.parseInt(txtPhanTramGiam.getText()));
        ma.setSoLuong(Integer.parseInt(txtSoLuong1.getText()));
        ma.setNgayBatDau(DateNgayBatDau.getDate());
        ma.setNgayketThuc(DateNgayKetThuc.getDate());
        ma.setDieuKienGiamGia(txtDieuKien.getText());
        if (rdoCo.isSelected()) {
            ma.setTrangThai(1);
        } else {
            ma.setTrangThai(0);
        }
        return ma;

    }

    private AddChiTiet getFormChiTietGiamGia() {
        AddChiTiet add = new AddChiTiet();
        int row = tblChiTietSP.getSelectedRow();
        String maSP = ctspSer.layIdByMa(tblChiTietSP.getValueAt(row, 0).toString());
        String maGG = maSer.layIdByMa(txtMa1.getText());
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


    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
//        int d = tblGioHang.getSelectedRow();
//        if (d < 1) {
//            JOptionPane.showMessageDialog(this, "Rõ hàng còn trống, không treo!");
//            return;
//        }
        int rowhd = tblHoaDonCho.getSelectedRow();
        String mad = tblHoaDonCho.getValueAt(rowhd, 0).toString();
        String idhdt = HoaDonBanHangRepository.getIDHDByMaHD(mad);
        String htm = HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhdt);
        String idhd = HoaDonBanHangRepository.getIDHDByMaHD(tblHoaDonCho.getValueAt(rowhd, 0).toString());
        if (htm.equals("Giao hàng")) {

            JOptionPane.showMessageDialog(this, "Không thể chuyển trạng thái treo");
            fillHDTReo();
            fillHDCho();
            clearHDTQ();
            dtm = (DefaultTableModel) tblGioHang.getModel();
            dtm.setRowCount(0);
            return;
        }
        String text = JOptionPane.showInputDialog("Lí do treo đơn", "Đơn Treo");
        if (text == null) {
            return;
        }
        if (htm.equals("Tại quầy")) {
            if (!text.equals("")) {
                HoaDonBanHangRepository.updateTTTReoHDTaoTQ(text, idhd);
                JOptionPane.showMessageDialog(this, "Hóa đơn chuyển sang trạng thái treo");
                fillHDTReo();
                fillHDCho();
                clearHDTQ();
                dtm = (DefaultTableModel) tblGioHang.getModel();
                dtm.setRowCount(0);
            }
        }

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        // TODO add your handling code here:

        int rowhdct = tblGioHang.getSelectedRow();
        int rowhd = tblHoaDonCho.getSelectedRow();
        if (rowhdct == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn san phẩm trong giỏ hàng");
            return;
        }
        String masp = tblGioHang.getValueAt(rowhdct, 0).toString();
        int slht = Integer.parseInt(tblGioHang.getValueAt(rowhdct, 2).toString());
        String idctg = SanPhanBanHangRepository.getIDSpByMaSP(masp);
        String mahd = txtMaHD.getText();
        String idhd = HoaDonBanHangRepository.getIDHDByMaHD(mahd);
        int rowhdtreo = tblHoaDonTreo.getSelectedRow();
        if (tbbTao.getSelectedIndex() == 0) {
            if (rowhd == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 Hóa Đơn tạo");
                return;
            }
            if (HoaDonBanHangRepository.getTTByIDHD(idhd) == 2 && HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhd).equals("Tại quầy")) {
                int slsp = Integer.parseInt(JOptionPane.showInputDialog("Số lượng sản phẩm sửa"));
                HoaDonCTRepository.updateSLHDCT(slsp, idctg, idhd);
                if (slsp < slht) {
                    int slt = SanPhanBanHangRepository.getSLGiay(masp);
                    int slcn = slt + (slht - slsp);
                    SanPhanBanHangRepository.updateSLSP(slcn, masp);
                    fillSPBH();
                } else if (slsp > slht) {
                    int slt = SanPhanBanHangRepository.getSLGiay(masp);
                    int slcn = slt - (slsp - slht);
                    SanPhanBanHangRepository.updateSLSP(slcn, masp);
                    fillSPBH();
                }
            }
            if (HoaDonBanHangRepository.getTTByIDHD(idhd) == 2 && HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhd).equals("Giao hàng")) {
                int slsp = Integer.parseInt(JOptionPane.showInputDialog("Số lượng sản phẩm sửa"));
                HoaDonCTRepository.updateSLHDCT(slsp, idctg, idhd);
                if (slsp < slht) {
                    int slt = SanPhanBanHangRepository.getSLGiay(masp);
                    int slcn = slt + (slht - slsp);
                    SanPhanBanHangRepository.updateSLSP(slcn, masp);
                    fillSPBH();
                } else if (slsp > slht) {
                    int slt = SanPhanBanHangRepository.getSLGiay(masp);
                    int slcn = slt - (slsp - slht);
                    SanPhanBanHangRepository.updateSLSP(slcn, masp);
                    fillSPBH();
                }
            }

        }
        if (tbbTao.getSelectedIndex() == 2) {
            if (rowhdtreo == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 Hóa Đơn treo");
                return;
            }
            String idhdtreo = HoaDonBanHangRepository.getIDHDByMaHD(tblHoaDonTreo.getValueAt(rowhdtreo, 0).toString());
            if (HoaDonBanHangRepository.getTTByIDHD(idhdtreo) == 5 && HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhdtreo).equals("Tại quầy")) {
                int slsp = Integer.parseInt(JOptionPane.showInputDialog("Số lượng sản phẩm sửa"));
                HoaDonCTRepository.updateSLHDCT(slsp, idctg, idhd);
                if (slsp < slht) {
                    int slt = SanPhanBanHangRepository.getSLGiay(masp);
                    int slcn = slt + (slht - slsp);
                    SanPhanBanHangRepository.updateSLSP(slcn, masp);
                    fillSPBH();
                } else if (slsp > slht) {
                    int slt = SanPhanBanHangRepository.getSLGiay(masp);
                    int slcn = slt - (slsp - slht);
                    SanPhanBanHangRepository.updateSLSP(slcn, masp);
                    fillSPBH();
                }
            }
        }
        if (tbbTao.getSelectedIndex() == 2) {
            if (HoaDonBanHangRepository.getTTByIDHD(idhd) == 5 && HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhd).equals("Giao hàng")) {
                JOptionPane.showMessageDialog(this, "Hóa đơn đang giao không thể sửa số lượng sản phẩm");
                return;
            }
        }
        int rowhddg = tblHoaDonGiao.getSelectedRow();
        if (tbbTao.getSelectedIndex() == 1) {
            if (rowhddg == 0) {
                JOptionPane.showMessageDialog(this, "Hóa đơn đang giao không thể sửa số lượng sản phẩm");
                return;
            }
        }
        if (tbbTao.getSelectedIndex() == 1) {
            if (HoaDonBanHangRepository.getTTByIDHD(idhd) == 3 && HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhd).equals("Giao hàng")) {
                JOptionPane.showMessageDialog(this, "Hóa đơn đang giao không thể sửa số lượng sản phẩm");
                return;
            }
        }
        if (pnlGiaoHang.isVisible() == true) {
            txtTongTienGH.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd)));
            txtCanTraGH.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd) - (HoaDonCTRepository.getSumTT(mahd) * Double.parseDouble(txtGiamGiaGH.getText()) / 100) + Double.parseDouble(txtPhiShip.getText())));
            txtTienKhachTra.setText(String.valueOf(Double.parseDouble(txtCanTraGH.getText())));
        } else if (pnlTaiQuay.isVisible() == true) {
            txtTongTienTQ.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd)));
            txtTienKhachCanTra.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd) - (HoaDonCTRepository.getSumTT(mahd) * Double.parseDouble(txtKhuyenMaiThemTQ.getText()) / 100)));
            txtTienDuTQ.setText(String.valueOf(Double.parseDouble(txtTienMatTQ.getText()) + Double.parseDouble(txtTienCKTQ.getText()) - Double.parseDouble(txtTienKhachCanTra.getText())));
        }
        fillHDCT();
    }//GEN-LAST:event_btnSua1ActionPerformed

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        // TODO add your handling code here:
        int rowhdct = tblGioHang.getSelectedRow();
        int rowhd = tblHoaDonCho.getSelectedRow();
        if (rowhdct == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 dòng trong giỏ hàng");
            return;
        }
        String masp = tblGioHang.getValueAt(rowhdct, 0).toString();
        String idctg = SanPhanBanHangRepository.getIDSpByMaSP(masp);
        String mahd = txtMaHD.getText();
        String idhd = HoaDonBanHangRepository.getIDHDByMaHD(mahd);
        int slxoa = Integer.parseInt(tblGioHang.getValueAt(rowhdct, 2).toString());
        int rowhdtreo = tblHoaDonTreo.getSelectedRow();
//            if (rowhd == -1) {
//                JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 Hóa Đơn tạo");
//                return;
//            }
        if (HoaDonBanHangRepository.getTTByIDHD(idhd) == 2 && HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhd).equals("Tại quầy")) {
            System.out.println("a");
            int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa sản phẩm trong giỏ hàng không ?");
            if (chon == JOptionPane.YES_OPTION) {
                HoaDonCTRepository.deleteDHCT(idctg, idhd);
            }

        }
        if (tbbTao.getSelectedIndex() == 1) {
            if (HoaDonBanHangRepository.getTTByIDHD(idhd) == 3 && HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhd).equals("Giao hàng")) {
                JOptionPane.showMessageDialog(this, "Hóa đơn đang giao không thể xóa sản sản phẩm");
                return;
            }
        }
        if (HoaDonBanHangRepository.getTTByIDHD(idhd) == 2 && HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhd).equals("Giao hàng")) {
            System.out.println("a");
            int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa sản phẩm trong giỏ hàng không ?");
            if (chon == JOptionPane.YES_OPTION) {
                HoaDonCTRepository.deleteDHCT(idctg, idhd);
            }

        }
        if (tbbTao.getSelectedIndex() == 2) {
            if (rowhdtreo == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 Hóa Đơn treo");
                return;
            }
            String idhdtreo = HoaDonBanHangRepository.getIDHDByMaHD(tblHoaDonTreo.getValueAt(rowhdtreo, 0).toString());
            if (HoaDonBanHangRepository.getTTByIDHD(idhdtreo) == 5 && HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhdtreo).equals("Tại quầy")) {
                int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa toàn bộ sản phẩm trong giỏ hàng không ?");
                if (chon == JOptionPane.YES_OPTION) {
                    HoaDonCTRepository.deleteDHCT(idctg, idhd);
                }

            }
        }
        int rowhddg = tblHoaDonGiao.getSelectedRow();
        if (tbbTao.getSelectedIndex() == 1) {
            if (rowhddg == 0) {
                JOptionPane.showMessageDialog(this, "Hóa đơn đang giao không thể xóa sản phẩm");
                return;
            }
        }
        if (tbbTao.getSelectedIndex() == 2) {
            if (HoaDonBanHangRepository.getTTByIDHD(idhd) == 5 && HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhd).equals("Giao hàng")) {
                JOptionPane.showMessageDialog(this, "Hóa đơn đang giao không thể xóa sản phẩm");
                return;
            }
        }

        int slt = SanPhanBanHangRepository.getSLGiay(masp);
        int slcn = slt + slxoa;

        SanPhanBanHangRepository.updateSLSP(slcn, masp);
        if (pnlGiaoHang.isVisible() == true) {
            txtTongTienGH.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd)));
            txtCanTraGH.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd) - (HoaDonCTRepository.getSumTT(mahd) * Double.parseDouble(txtGiamGiaGH.getText()) / 100) + Double.parseDouble(txtPhiShip.getText())));
            txtTienKhachTra.setText(String.valueOf(Double.parseDouble(txtCanTraGH.getText())));
        } else if (pnlTaiQuay.isVisible() == true) {
            txtTongTienTQ.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd)));
            txtTienKhachCanTra.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd) - (HoaDonCTRepository.getSumTT(mahd) * Double.parseDouble(txtKhuyenMaiThemTQ.getText()) / 100)));
            txtTienDuTQ.setText(String.valueOf(Double.parseDouble(txtTienMatTQ.getText()) + Double.parseDouble(txtTienCKTQ.getText()) - Double.parseDouble(txtTienKhachCanTra.getText())));
        }
        fillHDCT();
        fillSPBH();
    }//GEN-LAST:event_btnXoa1ActionPerformed

    private void btnXoaAllGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaAllGHActionPerformed
        // TODO add your handling code here:
        int rowhdct = tblGioHang.getRowCount();
        int rowsp = tblSanPhamBanHang.getRowCount();
        int rowhd = tblHoaDonCho.getSelectedRow();
        int rowhdtreo = tblHoaDonTreo.getSelectedRow();

        if (tbbTao.getSelectedIndex() == 0) {
            if (rowhd == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 Hóa Đơn tạo");
                return;
            }
            if (rowhdct == 0) {
                JOptionPane.showMessageDialog(this, "Giỏ hàng đang trống");
                return;
            }
            String idhd = HoaDonBanHangRepository.getIDHDByMaHD(tblHoaDonCho.getValueAt(rowhd, 0).toString());
            if (HoaDonBanHangRepository.getTTByIDHD(idhd) == 2 && HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhd).equals("Tại quầy")) {

                int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa toàn bộ sản phẩm trong giỏ hàng không ?");
                if (chon == JOptionPane.YES_OPTION) {
                    HoaDonCTRepository.deleteAllGH(idhd);
                }
            }
            if (HoaDonBanHangRepository.getTTByIDHD(idhd) == 2 && HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhd).equals("Giao hàng")) {

                int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa toàn bộ sản phẩm trong giỏ hàng không ?");
                if (chon == JOptionPane.YES_OPTION) {
                    HoaDonCTRepository.deleteAllGH(idhd);
                }
            }
        }
        if (tbbTao.getSelectedIndex() == 2) {
            if (rowhdtreo == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 Hóa Đơn treo");
                return;
            }

            if (rowhdct == 0) {
                JOptionPane.showMessageDialog(this, "Giỏ hàng đang trống");
                return;
            }
            String idhdtreo = HoaDonBanHangRepository.getIDHDByMaHD(tblHoaDonTreo.getValueAt(rowhdtreo, 0).toString());
            if (HoaDonBanHangRepository.getTTByIDHD(idhdtreo) == 5 && HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhdtreo).equals("Tại quầy")) {
                int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa toàn bộ sản phẩm trong giỏ hàng không ?");
                if (chon == JOptionPane.YES_OPTION) {
                    HoaDonCTRepository.deleteAllGH(idhdtreo);
                }

            }
        }
        String mahd = txtMaHD.getText();
        String idhd = HoaDonBanHangRepository.getIDHDByMaHD(mahd);
        if (tbbTao.getSelectedIndex() == 2) {
            if (HoaDonBanHangRepository.getTTByIDHD(idhd) == 5 && HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhd).equals("Giao hàng")) {
                JOptionPane.showMessageDialog(this, "Hóa đơn đang giao không thể xóa toàn bộ sản phẩm");
                return;
            }
        }
        int rowhddg = tblHoaDonGiao.getSelectedRow();
        if (tbbTao.getSelectedIndex() == 1) {
            if (rowhddg == 0) {
                JOptionPane.showMessageDialog(this, "Hóa đơn đang giao không thể xóa toàn bộ sản phẩm");
                return;
            }
        }
        if (tbbTao.getSelectedIndex() == 1) {
            if (HoaDonBanHangRepository.getTTByIDHD(idhd) == 3 && HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhd).equals("Giao hàng")) {
                JOptionPane.showMessageDialog(this, "Hóa đơn đang giao không thể xóa sản sản phẩm");
                return;
            }
        }
        for (int i = 0; i < rowhdct; i++) {
            for (int j = 0; j < rowsp; j++) {
                String masp = tblGioHang.getValueAt(i, 0).toString();
                int slxoa = Integer.parseInt(tblGioHang.getValueAt(i, 2).toString());
                if (tblGioHang.getValueAt(i, 0).toString().equals(tblSanPhamBanHang.getValueAt(j, 0).toString())) {
                    int slt = SanPhanBanHangRepository.getSLGiay(masp);
                    int slcn = slt + slxoa;
                    SanPhanBanHangRepository.updateSLSP(slcn, masp);
                    fillSPBH();
                }
            }

        }
        if (pnlGiaoHang.isVisible() == true) {
            txtTongTienGH.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd)));
            txtCanTraGH.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd) - (HoaDonCTRepository.getSumTT(mahd) * Double.parseDouble(txtGiamGiaGH.getText()) / 100) + Double.parseDouble(txtPhiShip.getText())));
            txtTienKhachTra.setText(String.valueOf(Double.parseDouble(txtCanTraGH.getText())));
        } else if (pnlTaiQuay.isVisible() == true) {
            txtTongTienTQ.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd)));
            txtTienKhachCanTra.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd) - (HoaDonCTRepository.getSumTT(mahd) * Double.parseDouble(txtKhuyenMaiThemTQ.getText()) / 100)));
            txtTienDuTQ.setText(String.valueOf(Double.parseDouble(txtTienMatTQ.getText()) + Double.parseDouble(txtTienCKTQ.getText()) - Double.parseDouble(txtTienKhachCanTra.getText())));
        }
        fillHDCT();
    }//GEN-LAST:event_btnXoaAllGHActionPerformed

    private void tblSanPhamBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamBanHangMouseClicked
        // TODO add your handling code here:
        int rowhttao = tblHoaDonCho.getSelectedRow();
        int rowhdtreo = tblHoaDonTreo.getSelectedRow();
        if (tbbTao.getSelectedIndex() == 0) {
            if (rowhttao == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn trước khi thêm !");
                return;
            }
        } else if (tbbTao.getSelectedIndex() == 2) {
            if (rowhdtreo == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn trước khi thêm !");
                return;
            }
        } else if (tbbTao.getSelectedIndex() == 1) {
            int rowhddg = tblHoaDonGiao.getSelectedRow();
            if (rowhddg == 0) {
                JOptionPane.showMessageDialog(this, "Hóa đơn đang giao không thể thêm sản phẩm");
                return;
            }
        }
        String mahd = txtMaHD.getText();
        String idhd = HoaDonBanHangRepository.getIDHDByMaHD(mahd);
        if (tbbTao.getSelectedIndex() == 2) {
            if (HoaDonBanHangRepository.getTTByIDHD(idhd) == 5 && HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhd).equals("Giao hàng")) {
                JOptionPane.showMessageDialog(this, "Hóa đơn đang giao không thể thêm sản sản phẩm");
                return;
            }
        }
        if (tbbTao.getSelectedIndex() == 1) {
            if (HoaDonBanHangRepository.getTTByIDHD(idhd) == 3 && HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhd).equals("Giao hàng")) {
                JOptionPane.showMessageDialog(this, "Hóa đơn đang giao không thể thêm sản sản phẩm");
                return;
            }
        }
        getFormHDCT();
        fillHDCT();

        //        txtTongTien.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd)));
    }//GEN-LAST:event_tblSanPhamBanHangMouseClicked

    private void tblSanPhamBanHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamBanHangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSanPhamBanHangMouseEntered

    private void txtTimSPCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimSPCaretUpdate
        // TODO add your handling code here:
        listspbh = _CuaHangService.getAll(txtTimSP.getText());
        dtm = (DefaultTableModel) tblSanPhamBanHang.getModel();
        dtm.setRowCount(0);
        for (int i = 0; i < listspbh.size(); i++) {
            Object[] data = new Object[]{
                listspbh.get(i).getMa(),
                listspbh.get(i).getTen(),
                listspbh.get(i).getHang(),
                listspbh.get(i).getSize(),
                listspbh.get(i).getMau(),
                listspbh.get(i).getDongia(),
                listspbh.get(i).getSl(),};
            dtm.addRow(data);
        }
    }//GEN-LAST:event_txtTimSPCaretUpdate

    private void tblHoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChoMouseClicked

        listhdbh = HoaDonBanHangRepository.getAllHDCho();
        int row = tblHoaDonCho.getSelectedRow();
        ThongTinHoaDonBanHang hd = listhdbh.get(row);
        String mahd = txtMaHD.getText();
        txtMaHD.setText(hd.getMa());
        txtNgayTao.setText(hd.getNgaytao());
        txtKhuyenMaiThemTQ.setText(String.valueOf(hd.getGiamgiathem()));
        txtTienMatTQ.setText(String.valueOf(hd.getTienmat()));
        txtTienCKTQ.setText(String.valueOf(hd.getTienkhac()));
        if (hd.getHinhthuc().equals("Tại quầy")) {
            clearHDGH();
            tbbTTHD.setSelectedIndex(0);
            txtTongTienTQ.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd)));
            txtKhuyenMaiThemTQ.setText(String.valueOf(hd.getGiamgiathem()));
            txtTienKhachCanTra.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd) - (HoaDonCTRepository.getSumTT(mahd) * Double.parseDouble(txtKhuyenMaiThemTQ.getText()) / 100)));
            txtTienDuTQ.setText(String.valueOf(Double.parseDouble(txtTienMatTQ.getText()) + Double.parseDouble(txtTienCKTQ.getText()) - Double.parseDouble(txtTienKhachCanTra.getText())));
        } else if (hd.getHinhthuc().equals("Giao hàng")) {
            clearHDTQ();
            tbbTTHD.setSelectedIndex(1);
            txtTongTienGH.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd)));
            txtPhiShip.setText(String.valueOf(hd.getPhiship()));
            txtGiamGiaGH.setText(String.valueOf(hd.getGiamgiathem()));
            txtTienKhachTra.setText(String.valueOf(hd.getTienkhac()));
            txtCanTraGH.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd) - (HoaDonCTRepository.getSumTT(mahd) * Double.parseDouble(txtGiamGiaGH.getText()) / 100) + Double.parseDouble(txtPhiShip.getText())));
            txtTienKhachTra.setText(String.valueOf(Double.parseDouble(txtCanTraGH.getText())));
        }
        //        txtKhuyenMaiThemTQ.setText(String.valueOf(0.1));
        //        double canTra = Double.valueOf(txtTongTienTQ.getText()) * (giamGia / 100);
        //        txtTenKHGH.setText(hd.getTenkh());
        //        txtTongTien.setText(String.valueOf(hd.getThanhtien()));
        //        txtTienMatTQ.setText(String.valueOf(hd.getTienmat()));
        //        txtTienCKTQ.setText(String.valueOf(hd.getTienkhac()));
        //        int rowhdct = tblGioHang.getRowCount();
        //        for(int i =0 ; i < rowhdct; i++){
        //
        //        }

        fillHDCT();
        fillHDGiao();
        fillHDHT();
        fillHDTReo();
    }//GEN-LAST:event_tblHoaDonChoMouseClicked

    private void tblHoaDonChoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChoMouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            jPopupMenu3.show(this, 370, 130 + (tblHoaDonTreo.getSelectedRow() * 25));
        }
    }//GEN-LAST:event_tblHoaDonChoMouseReleased

    private void tblHoaDonGiaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonGiaoMouseClicked
        // TODO add your handling code here:
        clearHDTQ();
        String mahd = txtMaHD.getText();
        tbbTTHD.setSelectedIndex(1);
        listhdbh = HoaDonBanHangRepository.getAllHDGiao();
        int row = tblHoaDonGiao.getSelectedRow();
        ThongTinHoaDonBanHang hd = listhdbh.get(row);
        txtMaHD.setText(hd.getMa());
        txtDiaChiGH.setText(hd.getDiachi());
        txtTenKHGH.setText(hd.getTenkh());
        txtSDTGH.setText(hd.getSdt());
        txtNgaySinhKH.setText(hd.getNgaysinh());
        txtDiaChiGH.setText(hd.getDiachi());
        txtTongTienGH.setText(String.valueOf(hd.getThanhtien()));
        txtGiamGiaGH.setText(String.valueOf(hd.getGiamgiathem()));
        txtPhiShip.setText(String.valueOf(hd.getPhiship()));

        txtTienKhachTra.setText(String.valueOf(hd.getTienkhac()));
        txtNgayTao.setText(hd.getNgaytao());

        if (pnlGiaoHang.isVisible() == true) {
            txtTongTienGH.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd)));
            txtCanTraGH.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd) - (HoaDonCTRepository.getSumTT(mahd) * Double.parseDouble(txtGiamGiaGH.getText()) / 100) + Double.parseDouble(txtPhiShip.getText())));
            txtTienKhachTra.setText(String.valueOf(Double.parseDouble(txtCanTraGH.getText())));
        }
        txtMaVanChuyen.setText(hd.getMavanchuyen().trim());

        listgh = HoaDonBanHangRepository.getHDCTByMaHD(mahd);
        dtm = (DefaultTableModel) tblGioHang.getModel();
        dtm.setRowCount(0);
        for (int i = 0; i < listgh.size(); i++) {
            Object[] data = new Object[]{
                listgh.get(i).getMasp(),
                listgh.get(i).getTen(),
                listgh.get(i).getSl(),
                listgh.get(i).getDonGia(),};
            dtm.addRow(data);
        }
        fillHDCho();
        fillHDHT();
        fillHDTReo();
    }//GEN-LAST:event_tblHoaDonGiaoMouseClicked

    private void tblHoaDonGiaoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonGiaoMouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            jPopupMenu1.show(this, 370, 130 + (tblHoaDonTreo.getSelectedRow() * 25));
        }
    }//GEN-LAST:event_tblHoaDonGiaoMouseReleased

    private void tblHoaDonTreoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonTreoMouseClicked
        // TODO add your handling code here:
        clearHDTQ();
        tbbTTHD.setSelectedIndex(1);
        listhdbh = HoaDonBanHangRepository.getAllHDTreo();
        int row = tblHoaDonTreo.getSelectedRow();
        String mahd = tblHoaDonTreo.getValueAt(row, 0).toString();
        String idhd = HoaDonBanHangRepository.getIDHDByMaHD(mahd);
        ThongTinHoaDonBanHang hd = HoaDonBanHangRepository.getTTHDByIDHD(idhd);
        txtMaHD.setText(mahd);
        if (hd.getHinhthuc().equals("Tại quầy")) {
            clearHDGH();
            tbbTTHD.setSelectedIndex(0);
            txtTienMatTQ.setText(String.valueOf(hd.getTienmat()));
            txtTienCKTQ.setText(String.valueOf(hd.getTienkhac()));
            txtTongTienTQ.setText(String.valueOf(hd.getThanhtien()));
            txtKhuyenMaiThemTQ.setText(String.valueOf(hd.getGiamgiathem()));
            txtNgayTao.setText(hd.getNgaytao());

            txtTongTienTQ.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd)));
            txtTienKhachCanTra.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd) - (HoaDonCTRepository.getSumTT(mahd) * Double.parseDouble(txtKhuyenMaiThemTQ.getText()) / 100)));
            txtTienDuTQ.setText(String.valueOf(Double.parseDouble(txtTienMatTQ.getText()) + Double.parseDouble(txtTienCKTQ.getText()) - Double.parseDouble(txtTienKhachCanTra.getText())));
        } else if (hd.getHinhthuc().equals("Giao hàng")) {
            clearHDTQ();
            tbbTTHD.setSelectedIndex(1);
            txtTenKHGH.setText(hd.getTenkh());
            txtSDTGH.setText(hd.getSdt());
            txtNgaySinhKH.setText(hd.getNgaysinh());
            txtDiaChiGH.setText(hd.getDiachi());
            txtTongTienGH.setText(String.valueOf(hd.getThanhtien()));
            txtGiamGiaGH.setText(String.valueOf(hd.getGiamgiathem()));
            txtPhiShip.setText(String.valueOf(hd.getPhiship()));
            txtTienKhachTra.setText(String.valueOf(hd.getTienkhac()));
            txtTongTienGH.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd)));
            txtCanTraGH.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd) - (HoaDonCTRepository.getSumTT(mahd) * Double.parseDouble(txtGiamGiaGH.getText()) / 100) + Double.parseDouble(txtPhiShip.getText())));
            txtTienKhachTra.setText(String.valueOf(Double.parseDouble(txtCanTraGH.getText())));
            txtNgayTao.setText(hd.getNgaytao());
            txtMaVanChuyen.setText(hd.getMavanchuyen().trim());
        }
        listgh = HoaDonBanHangRepository.getHDCTByMaHD(mahd);
        dtm = (DefaultTableModel) tblGioHang.getModel();
        dtm.setRowCount(0);
        for (int i = 0; i < listgh.size(); i++) {
            Object[] data = new Object[]{
                listgh.get(i).getMasp(),
                listgh.get(i).getTen(),
                listgh.get(i).getSl(),
                listgh.get(i).getDonGia(),};
            dtm.addRow(data);
        }
        fillHDCho();
        fillHDGiao();
        fillHDHT();
    }//GEN-LAST:event_tblHoaDonTreoMouseClicked

    private void tblHoaDonTreoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonTreoMouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            jPopupMenu2.show(this, 370, 130 + (tblHoaDonTreo.getSelectedRow() * 25));
        }
    }//GEN-LAST:event_tblHoaDonTreoMouseReleased

    private void tblHoaDonHoanThanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonHoanThanhMouseClicked
        // TODO add your handling code here:
        listhdbh = HoaDonBanHangRepository.getAllHDHT();
        int row = tblHoaDonHoanThanh.getSelectedRow();
        String mahd = txtMaHD.getText();
        ThongTinHoaDonBanHang hd = listhdbh.get(row);
        txtMaHD.setText(hd.getMa());
        txtTenKHGH.setText(hd.getTenkh());
        if (hd.getHinhthuc().equals("Tại quầy")) {
            clearHDGH();
            tbbTTHD.setSelectedIndex(0);
            txtTienMatTQ.setText(String.valueOf(hd.getTienmat()));
            txtTienCKTQ.setText(String.valueOf(hd.getTienkhac()));
            txtTongTienTQ.setText(String.valueOf(hd.getThanhtien()));
            txtKhuyenMaiThemTQ.setText(String.valueOf(hd.getGiamgiathem()));
            txtNgayTao.setText(hd.getNgaytao());

            txtTongTienTQ.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd)));
            txtTienKhachCanTra.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd) - (HoaDonCTRepository.getSumTT(mahd) * Double.parseDouble(txtKhuyenMaiThemTQ.getText()) / 100)));
            txtTienDuTQ.setText(String.valueOf(Double.parseDouble(txtTienMatTQ.getText()) + Double.parseDouble(txtTienCKTQ.getText()) - Double.parseDouble(txtTienKhachCanTra.getText())));
        } else if (hd.getHinhthuc().equals("Giao hàng")) {
            clearHDTQ();
            tbbTTHD.setSelectedIndex(1);
            txtTenKHGH.setText(hd.getTenkh());
            txtSDTGH.setText(hd.getSdt());
            txtNgaySinhKH.setText(hd.getNgaysinh());
            txtDiaChiGH.setText(hd.getDiachi());
            txtTongTienGH.setText(String.valueOf(hd.getThanhtien()));
            txtGiamGiaGH.setText(String.valueOf(hd.getGiamgiathem()));
            txtPhiShip.setText(String.valueOf(hd.getPhiship()));

            txtTienKhachTra.setText(String.valueOf(hd.getTienkhac()));
            txtTongTienGH.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd)));
            txtCanTraGH.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd) - (HoaDonCTRepository.getSumTT(mahd) * Double.parseDouble(txtGiamGiaGH.getText()) / 100) + Double.parseDouble(txtPhiShip.getText())));
            txtTienKhachTra.setText(String.valueOf(Double.parseDouble(txtCanTraGH.getText())));
            txtNgayTao.setText(hd.getNgaytao());
            txtMaVanChuyen.setText(hd.getMavanchuyen().trim());
        }
        listgh = HoaDonBanHangRepository.getHDCTByMaHD(mahd);
        dtm = (DefaultTableModel) tblGioHang.getModel();
        dtm.setRowCount(0);
        for (int i = 0; i < listgh.size(); i++) {
            Object[] data = new Object[]{
                listgh.get(i).getMasp(),
                listgh.get(i).getTen(),
                listgh.get(i).getSl(),
                listgh.get(i).getDonGia(),};
            dtm.addRow(data);
        }
        fillHDCho();
        fillHDGiao();
        fillHDTReo();
    }//GEN-LAST:event_tblHoaDonHoanThanhMouseClicked

    private void tblHoaDonHoanThanhMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonHoanThanhMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHoaDonHoanThanhMouseEntered

    private void tbbTaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbbTaoMouseClicked
        // TODO add your handling code here:
        dtm = (DefaultTableModel) tblGioHang.getModel();
        dtm.setRowCount(0);
        clearHDTQ();
        clearHDGH();
    }//GEN-LAST:event_tbbTaoMouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel4MouseClicked

    private void txtTienMatTQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienMatTQActionPerformed
        // TODO add your handling code here:
        if (Double.parseDouble(txtTienCKTQ.getText()) >= 0) {
            lblTienDuTQ.setText(" ");
        }
        if (txtTienCKTQ.getText().trim().length() == 0) {
            txtTienDuTQ.setText(String.valueOf(Double.parseDouble(txtTienMatTQ.getText())
                    - Double.parseDouble(txtTienKhachCanTra.getText())));

        } else if (txtTienMatTQ.getText().trim().length() == 0) {
            txtTienDuTQ.setText(String.valueOf(Double.parseDouble(txtTienCKTQ.getText())
                    - Double.parseDouble(txtTienKhachCanTra.getText())));
        } else {
            txtTienDuTQ.setText(String.valueOf(Double.parseDouble(txtTienMatTQ.getText()) + Double.parseDouble(txtTienCKTQ.getText())
                    - Double.parseDouble(txtTienKhachCanTra.getText())));
        }

    }//GEN-LAST:event_txtTienMatTQActionPerformed

    private void txtTienCKTQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienCKTQActionPerformed
        // TODO add your handling code here:
        if (Double.parseDouble(txtTienCKTQ.getText()) >= 0) {
            lblTienDuTQ.setText(" ");
        }
        if (txtTienMatTQ.getText().trim().length() == 0) {
            txtTienDuTQ.setText(String.valueOf(Double.parseDouble(txtTienCKTQ.getText())
                    - Double.parseDouble(txtTienKhachCanTra.getText())));

        } else if (txtTienCKTQ.getText().trim().length() == 0) {
            txtTienDuTQ.setText(String.valueOf(Double.parseDouble(txtTienMatTQ.getText())
                    - Double.parseDouble(txtTienKhachCanTra.getText())));
        } else {
            txtTienDuTQ.setText(String.valueOf(Double.parseDouble(txtTienMatTQ.getText()) + Double.parseDouble(txtTienCKTQ.getText())
                    - Double.parseDouble(txtTienKhachCanTra.getText())));
        }

    }//GEN-LAST:event_txtTienCKTQActionPerformed

    private void txtTienDuTQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienDuTQActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienDuTQActionPerformed

    private void txtKhuyenMaiThemTQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKhuyenMaiThemTQActionPerformed
        // TODO add your handling code here:
        //        double giamGia = Double.valueOf(txtKhuyenMaiThemTQ.getText());
        txtTienKhachCanTra.setText(String.valueOf(Double.valueOf(txtTongTienTQ.getText())
                - (Double.valueOf(txtTongTienTQ.getText()) * (Double.valueOf(txtKhuyenMaiThemTQ.getText()) / 100))));
        txtTienDuTQ.setText(String.valueOf(Double.parseDouble(txtTienMatTQ.getText()) + Double.parseDouble(txtTienCKTQ.getText()) - Double.parseDouble(txtTienKhachCanTra.getText())));

    }//GEN-LAST:event_txtKhuyenMaiThemTQActionPerformed

    private void txtTienKhachCanTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachCanTraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienKhachCanTraActionPerformed

    private void txtTienKhachTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachTraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienKhachTraActionPerformed

    private void txtPhiShipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhiShipActionPerformed
        // TODO add your handling code here:
        if (validateHoaDonDG()) {
            return;
        }
        if (txtPhiShip.getText().trim().length() == 0) {
            txtCanTraGH.setText(String.valueOf(Double.parseDouble(txtTongTienGH.getText())
                    - (Double.parseDouble(txtTongTienGH.getText()) * Double.parseDouble(txtGiamGiaGH.getText()) / 100)));
            txtTienKhachTra.setText(String.valueOf(Double.parseDouble(txtCanTraGH.getText())));
        } else if (txtGiamGiaGH.getText().trim().length() == 0) {
            txtCanTraGH.setText(String.valueOf(Double.parseDouble(txtTongTienGH.getText())
                    + Double.parseDouble(txtPhiShip.getText())));
            txtTienKhachTra.setText(String.valueOf(Double.parseDouble(txtCanTraGH.getText())));
        } else if (txtPhiShip.getText().trim().length() == 0 && txtGiamGiaGH.getText().trim().length() == 0) {
            txtCanTraGH.setText(String.valueOf(Double.parseDouble(txtTongTienGH.getText())));
            txtTienKhachTra.setText(String.valueOf(Double.parseDouble(txtCanTraGH.getText())));
        } else {
            txtCanTraGH.setText(String.valueOf(Double.parseDouble(txtTongTienGH.getText())
                    - (Double.parseDouble(txtTongTienGH.getText()) * Double.parseDouble(txtGiamGiaGH.getText()) / 100)
                    + Double.parseDouble(txtPhiShip.getText())));
            txtTienKhachTra.setText(String.valueOf(Double.parseDouble(txtCanTraGH.getText())));
        }
    }//GEN-LAST:event_txtPhiShipActionPerformed

    private void txtGiamGiaGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiamGiaGHActionPerformed
        // TODO add your handling code here:
        if (txtPhiShip.getText().trim().length() == 0) {
            txtCanTraGH.setText(String.valueOf(Double.parseDouble(txtTongTienGH.getText())
                    - (Double.parseDouble(txtTongTienGH.getText()) * Double.parseDouble(txtGiamGiaGH.getText()) / 100)));
            txtTienKhachTra.setText(String.valueOf(Double.parseDouble(txtCanTraGH.getText())));
        } else if (txtGiamGiaGH.getText().trim().length() == 0) {
            txtCanTraGH.setText(String.valueOf(Double.parseDouble(txtTongTienGH.getText())
                    + Double.parseDouble(txtPhiShip.getText())));
            txtTienKhachTra.setText(String.valueOf(Double.parseDouble(txtCanTraGH.getText())));
        } else if (txtPhiShip.getText().trim().length() == 0 && txtGiamGiaGH.getText().trim().length() == 0) {
            txtCanTraGH.setText(String.valueOf(Double.parseDouble(txtTongTienGH.getText())));
            txtTienKhachTra.setText(String.valueOf(Double.parseDouble(txtCanTraGH.getText())));
        } else {
            txtCanTraGH.setText(String.valueOf(Double.parseDouble(txtTongTienGH.getText())
                    - (Double.parseDouble(txtTongTienGH.getText()) * Double.parseDouble(txtGiamGiaGH.getText()) / 100)
                    + Double.parseDouble(txtPhiShip.getText())));
            txtTienKhachTra.setText(String.valueOf(Double.parseDouble(txtCanTraGH.getText())));
        }


    }//GEN-LAST:event_txtGiamGiaGHActionPerformed

    private void txtSDTGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTGHActionPerformed
        // TODO add your handling code here:
        String sdt = txtSDTGH.getText();
        KhachHang khm = KhachHangRepository.getKHBySDT(sdt);
        txtTenKHGH.setText(khm.getTenkh());
        txtNgaySinhKH.setText(khm.getNgaysinh());
        txtDiaChiGH.setText(khm.getDiachi());
    }//GEN-LAST:event_txtSDTGHActionPerformed

    private void txtNgaySinhKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgaySinhKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgaySinhKHActionPerformed

    private void btnLuuKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuKHActionPerformed
        // TODO add your handling code here:
        KhachHang kh = getFormKhacHang();
        if (checkKhachHang()) {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn lưu KH không ?");
            if (chon == JOptionPane.YES_OPTION) {
                KhachHangRepository.addKh(kh);
                JOptionPane.showMessageDialog(this, "Lưu thông tin khách hàng thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Không lưu thông tin khách hàng");

            }
        }
    }//GEN-LAST:event_btnLuuKHActionPerformed

    private void txtMaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHDActionPerformed

    private void btnTaoDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoDonActionPerformed
        // TODO add your handling code here:
        clearHDGH();
        clearHDTQ();
        int rowhdt = tblHoaDonCho.getRowCount();
        if (rowhdt >= 1) {
            JOptionPane.showMessageDialog(this, "Không thể tạo mới hóa đơn");
            return;
        }
        HoaDonBanHang dh = getFromHDBH();
        HoaDonBanHangRepository.add(dh);
        fillHDCho();
        tblHoaDonCho.setRowSelectionInterval(0, 0);
    }//GEN-LAST:event_btnTaoDonActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed

        int rowhd = tblHoaDonCho.getSelectedRow();
        String mahd = txtMaHD.getText();
        String idhdc = HoaDonBanHangRepository.getIDHDByMaHD(mahd);
        if (rowhd == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn");
            return;
        }
        if (HoaDonBanHangRepository.getTTByIDHD(idhdc) == 2) {
            if (rowhd == -1) {
                JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn");
                return;
            }
        }
        int rowGioHang = tblGioHang.getRowCount();
        if (rowGioHang < 1) {
            JOptionPane.showMessageDialog(this, "Giỏ hàng đang trống trống!");
            return;
        }
        String idhd = HoaDonBanHangRepository.getIDHDByMaHD(mahd);

        if (HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhd).equalsIgnoreCase("Tại quầy")) {
            if (checkBHTQThanhCong()) {
                HoaDonBanHang hdbh = getFormHoaDonBanHang();
                HoaDonBanHangRepository.updateTTHD(hdbh, idhd);
                lblTienChuyenKhoan.setText(" ");
                lblTienDuTQ.setText(" ");
                lblTienMatTQ.setText(" ");
                lblGiamGiaTQ.setText(" ");
                hoaDonInTQ();
                dtm = (DefaultTableModel) tblGioHang.getModel();
                dtm.setRowCount(0);

                fillHDCho();
                fillHDHT();
                fillHDGiao();
                fillHDTReo();
                clearHDGH();
                clearHDTQ();
            }

        } else if (HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhd).equalsIgnoreCase("Giao hàng")) {
            if (checkBHDGThanhCong()) {
                HoaDonBanHang hdbh = getFormHoaDonBanHangGiao();
                HoaDonBanHangRepository.updateTTDG(hdbh, idhd);
                hoaDonInGH();
                dtm = (DefaultTableModel) tblGioHang.getModel();
                dtm.setRowCount(0);

                fillHDCho();
                fillHDHT();
                fillHDGiao();
                fillHDTReo();
                clearHDGH();
                clearHDTQ();
            }

        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        clearHDGH();
        clearHDTQ();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void lblAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMouseClicked
        // TODO add your handling code here:
        try {
            JFileChooser fc = new JFileChooser("");
            fc.showOpenDialog(null);
            File f = fc.getSelectedFile();
            //            Image img = ImageIO.read(f);
            //            anhStr = f.getName();
            anhStr = f.getAbsolutePath();
            lblAnh.setText("");
            //            int width = lblAnh.getWidth();
            //            int height = lblAnh.getHeight();
            //            lblAnh.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
            lblAnh.setIcon(resizeImage(String.valueOf(anhStr)));
            System.out.println(anhStr);
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }//GEN-LAST:event_lblAnhMouseClicked

    private void tblGiayComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tblGiayComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tblGiayComponentRemoved

    private void tblGiayAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblGiayAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblGiayAncestorAdded

    private void tblGiayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGiayMouseClicked
        // TODO add your handling code here:
        int row = tblGiay.getSelectedRow();
        ChiTietGiay_View ct = listCTG.get(row);
        showDeTaiChiTietGiay(ct);
    }//GEN-LAST:event_tblGiayMouseClicked

    private void tblGiayMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGiayMouseReleased
        // TODO add your handling code here:
        //        if (evt.isPopupTrigger()) {
        //            jPopupMenu1.show(this, 500, 430 + (tblGiay.getSelectedRow() * 25));
        //        }
    }//GEN-LAST:event_tblGiayMouseReleased

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (check() == 0) {
            if (chiTietGiaYSV.checkTrungMa(txtMaGiay.getText())) {
                JOptionPane.showMessageDialog(this, "Mã giày này đã tồn tại!");
                return;
            }
            if (chiTietGiaYSV.checkTrungMaBarCode(txtMaBarCode.getText())) {
                JOptionPane.showMessageDialog(this, "Mã barcode này đã tồn tại!");
                return;
            }
            btnThem.setBackground(new Color(242, 242, 242));
            int choice = JOptionPane.showConfirmDialog(this, "insert", "Confirm", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                ChiTietGiay ct = getFormChiTietGiay();
                chiTietGiaYSV.themChiTietGiay(ct);
                fillTableChiTietGiay();
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        //        if (check() == 0) {
        ChiTietGiay ct = getFormChiTietGiay();
        int row = tblGiay.getSelectedRow();
        //        listChiTiet.get(row).getId();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui long chon 1 dong tren table");
            return;
        }
        String id = chiTietGiaYSV.getIdGiay(txtMaGiay.getText());
        ct.setId(id);

        int row1 = chiTietGiaYSV.suaChiTietGiay(ct);
        if (row1 > 0) {
            JOptionPane.showMessageDialog(this, "Update thanh cong");
            fillTableChiTietGiay();
        } else {
            JOptionPane.showMessageDialog(this, "Update that bai");
        }
        //        }

        //        ChiTietGiay ct = getFormChiTietGiay();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        ChiTietGiay ct = getFormChiTietGiay();
        //        int row = tblGiay.getSelectedRow();
        //        listChiTiet.get(row).getId();chiTietGiaYSV.getIdGiay(txtMaGiay.getText());chiTietGiaYSV.getIdGiay();
        String id = chiTietGiaYSV.getIdGiay(txtMaGiay.getText());
        ct.setId(id);

        int row1 = chiTietGiaYSV.xoaChiTietGiay(ct);
        if (row1 > 0) {
            JOptionPane.showMessageDialog(this, "Update thanh cong");
            fillTableChiTietGiay();
        } else {
            JOptionPane.showMessageDialog(this, "Update that bai");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        lamMoi();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnHienThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHienThiActionPerformed
        // TODO add your handling code here:
        fillTableChiTietGiay();
        fillCbbSize();
        fillCbbChatLieu();
        fillCbbHangGiay();
        fillCbbMauSac();
    }//GEN-LAST:event_btnHienThiActionPerformed

    private void btnThemExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemExcelActionPerformed
        // TODO add your handling code here:
        File excelFile = null;
        FileInputStream fis = null;
        BufferedInputStream bfis = null;
        XSSFWorkbook imports = null;
        JFileChooser f = new JFileChooser("E:\\ki_4\\block2\\Da1\\excel");
        int choose = f.showOpenDialog(null);
        if (choose == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = f.getSelectedFile();
                fis = new FileInputStream(excelFile);
                bfis = new BufferedInputStream(fis);

                imports = new XSSFWorkbook(bfis);
                XSSFSheet sheet = imports.getSheetAt(0);
                for (int row = 0; row <= sheet.getLastRowNum(); row++) {
                    XSSFRow exRow = sheet.getRow(row);
                    //                    ct.setIdMauSac(mauSV.getIdMauSacByTen(cbbMauSac.getSelectedItem().toString()));
                    //        ct.setIdSize(sizeSV.getIdSizeByTen(cbbSize.getSelectedItem().toString()));
                    //        ct.setIdChatLieu();
                    //        ct.setIdHang(hangSV.getIdHangByTen(cbbHang.getSelectedItem().toString()));
                    //                    XSSFCell exCellMaGiay = exRow.getCell(0);
                    //                    XSSFCell exCellTenGiay = exRow.getCell(1);
                    //                    XSSFCell exCellMau = exRow.getCell(2);
                    //                    XSSFCell exCellSize = exRow.getCell(3);
                    //                    XSSFCell exCellChatLieu = exRow.getCell(4);
                    //                    XSSFCell exCellHang = exRow.getCell(5);
                    //                    XSSFCell exCellnamBH = exRow.getCell(6);
                    //                    XSSFCell exCellSoLuong = exRow.getCell(7);
                    //                    XSSFCell exCellgiaNhap = exRow.getCell(8);
                    //                    XSSFCell exCellGiaBan = exRow.getCell(9);
                    //                    XSSFCell exCellAnh = exRow.getCell(10);

                    String exCellMaGiay = String.valueOf(exRow.getCell(0));
                    String exCellTenGiay = String.valueOf(exRow.getCell(1));
                    String exCellMau = mauSV.getIdMauSacByTen(String.valueOf(exRow.getCell(2)));
                    System.out.println(exCellMau);
                    String exCellSize = sizeSV.getIdSizeByTen(String.valueOf(exRow.getCell(3)).substring(0, 2));
//                    String size134 = exCellSize.substring(0, exCellSize.length() - 2);

                    String exCellChatLieu = chatLieuSV.getIdChatLieuByTen(String.valueOf(exRow.getCell(4)));
                    String exCellHang = hangSV.getIdHangByTen(String.valueOf(exRow.getCell(5)));
                    String exCellnamBH = String.valueOf(exRow.getCell(6)).substring(0, 4);
                    String exCellSoLuong = String.valueOf(exRow.getCell(7));
                    String soLuong = exCellSoLuong.substring(0, exCellSoLuong.length() - 2);
                    String exCellgiaNhap = String.valueOf(exRow.getCell(8));
                    String exCellGiaBan = String.valueOf(exRow.getCell(9));
                    String exCellAnh = String.valueOf(exRow.getCell(10));
                    //                    System.out.println(exCellMaGiay);
                    //                    System.out.println(exCellTenGiay);
                    //                    System.out.println(exCellMau);
                    //                    System.out.println(exCellSize);
                    //                    System.out.println(exCellChatLieu);
                    //                    System.out.println(exCellHang);
                    //                    System.out.println(exCellnamBH);
                    //                    System.out.println(exCellSoLuong);
                    //                    System.out.println(exCellgiaNhap);
                    //                    System.out.println(exCellGiaBan);
                    //                    System.out.println(exCellAnh);
                    GiayVIP ct = new GiayVIP(null, exCellMaGiay, null, exCellTenGiay, exCellMau, exCellSize, exCellChatLieu, exCellHang, exCellnamBH, soLuong, exCellgiaNhap, exCellGiaBan, exCellAnh);
                    chiTietGiaYSV.themChiTietGiayVIP(ct);
                    fillTableChiTietGiay();
                    //                    model.addRow(new Object[] {exCellTenGiay,exCellMau,exCellSize,exCellChatLieu,exCellHang,exCellnamBH,exCellSoLuong,
                    //                                                exCellGiaBan,exCellAnh});

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnThemExcelActionPerformed

    private void rdoMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMauSacActionPerformed
        fillTableThuocTinhMau();
    }//GEN-LAST:event_rdoMauSacActionPerformed

    private void rdoSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoSizeActionPerformed
        // TODO add your handling code here:
        fillTableThuocTinhSize();
    }//GEN-LAST:event_rdoSizeActionPerformed

    private void rdoChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoChatLieuActionPerformed
        // TODO add your handling code here:
        fillTableThuocTinhChatLieu();
    }//GEN-LAST:event_rdoChatLieuActionPerformed

    private void rdoHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoHangActionPerformed
        // TODO add your handling code here:
        fillTableThuocTinhHang();
    }//GEN-LAST:event_rdoHangActionPerformed

    private void btnThemThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThuocTinhActionPerformed
        if (rdoMauSac.isSelected()) {
            ThuocTinh tt = getFormThuocTinh();
            mauSV.themMauSac(tt);
            fillTableThuocTinhMau();
        } else if (rdoSize.isSelected()) {
            ThuocTinh tt = getFormThuocTinh();
            sizeSV.themSize(tt);
            fillTableThuocTinhSize();
        } else if (rdoChatLieu.isSelected()) {
            ThuocTinh tt = getFormThuocTinh();
            chatLieuSV.themChatLieu(tt);
            fillTableThuocTinhChatLieu();
        } else if (rdoHang.isSelected()) {
            ThuocTinh tt = getFormThuocTinh();
            hangSV.themHangGiay(tt);
            fillTableThuocTinhHang();
        }
    }//GEN-LAST:event_btnThemThuocTinhActionPerformed

    private void btnSuaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThuocTinhActionPerformed
        // TODO add your handling code here:
        String idMau = mauSV.getIdMauSac(txtMaThuocTinh.getText());
        String idSize = sizeSV.getIdSize(txtMaThuocTinh.getText());
        String idChatLieu = chatLieuSV.getIdChatLieu(txtMaThuocTinh.getText());
        String idHang = hangSV.getIdHangGiay(txtMaThuocTinh.getText());
        if (rdoMauSac.isSelected()) {
            ThuocTinh tt = getFormThuocTinh();
            mauSV.suaMauSac(tt, idMau);
            fillTableThuocTinhMau();
        } else if (rdoSize.isSelected()) {
            ThuocTinh tt = getFormThuocTinh();
            sizeSV.suaSize(tt, idSize);
            fillTableThuocTinhHang();
        } else if (rdoChatLieu.isSelected()) {
            ThuocTinh tt = getFormThuocTinh();
            chatLieuSV.suaChatLieu(tt, idChatLieu);
            fillTableThuocTinhChatLieu();
        } else if (rdoHang.isSelected()) {
            ThuocTinh tt = getFormThuocTinh();
            hangSV.suaHangGiay(tt, idHang);
            fillTableThuocTinhHang();
        }
    }//GEN-LAST:event_btnSuaThuocTinhActionPerformed

    private void btnXoaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaThuocTinhActionPerformed
        // TODO add your handling code here:
        String idMau = mauSV.getIdMauSac(txtMaThuocTinh.getText());
        String idSize = sizeSV.getIdSize(txtMaThuocTinh.getText());
        String idChatLieu = chatLieuSV.getIdChatLieu(txtMaThuocTinh.getText());
        String idHang = hangSV.getIdHangGiay(txtMaThuocTinh.getText());
        if (rdoMauSac.isSelected()) {
            ThuocTinh tt = getFormThuocTinh();
            mauSV.xoaMauSac(tt, idMau);
            fillTableThuocTinhMau();
        } else if (rdoSize.isSelected()) {
            ThuocTinh tt = getFormThuocTinh();
            sizeSV.xoaSize(tt, idSize);
            fillTableThuocTinhSize();
        } else if (rdoChatLieu.isSelected()) {
            ThuocTinh tt = getFormThuocTinh();
            chatLieuSV.xoaChatLieu(tt, idChatLieu);
            fillTableThuocTinhChatLieu();
        } else if (rdoHang.isSelected()) {
            ThuocTinh tt = getFormThuocTinh();
            hangSV.xoaHangGiay(tt, idHang);
            fillTableThuocTinhHang();
        }
    }//GEN-LAST:event_btnXoaThuocTinhActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        txtMaThuocTinh.setText("");
        txtTenThuocTinh.setText("");
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tblThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhMouseClicked
        // TODO add your handling code here:
        int index = tblThuocTinh.getSelectedRow();
        model = (DefaultTableModel) tblThuocTinh.getModel();
        String id = tblThuocTinh.getValueAt(index, 0).toString();
        if (rdoMauSac.isSelected()) {
            ThuocTinh tt = mauSV.getAllMauSacById(id);
            showDeTaiThuocTinh(tt);
        } else if (rdoSize.isSelected()) {
            ThuocTinh tt = sizeSV.getAllSizeById(id);
            showDeTaiThuocTinh(tt);
        } else if (rdoChatLieu.isSelected()) {
            ThuocTinh tt = chatLieuSV.getAllChatLieuById(id);
            showDeTaiThuocTinh(tt);
        } else if (rdoHang.isSelected()) {
            ThuocTinh tt = hangSV.getAllHangGiayById(id);
            showDeTaiThuocTinh(tt);
        }
    }//GEN-LAST:event_tblThuocTinhMouseClicked

    private void cbbThongKeSanPhamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbThongKeSanPhamItemStateChanged
        if (cbbThongKeSanPham.getSelectedIndex() == 0) {
            pnlSoLuong.setVisible(false);
            listTKSanPham = thongKe_Serv.getAll();
            loadTableSanPham(listTKSanPham);
        } else if (cbbThongKeSanPham.getSelectedIndex() == 1) {
            pnlSoLuong.setVisible(false);
            listTKSanPham = thongKe_Serv.getTop5SoLuongBan();
            loadTableSanPham(listTKSanPham);
        } else if (cbbThongKeSanPham.getSelectedIndex() == 2) {
            pnlSoLuong.setVisible(false);
            listTKSanPham = thongKe_Serv.getTop5DoanhThu();
            loadTableSanPham(listTKSanPham);
        } else {
            pnlSoLuong.setVisible(true);

        }
    }//GEN-LAST:event_cbbThongKeSanPhamItemStateChanged

    private void btnXuatFile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFile1ActionPerformed

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("danh sach");
        //
        XSSFRow row = null;
        Cell cell = null;
        row = sheet.createRow(2);

        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Mã giày");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Tên giày");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Màu sắc");

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Size");

        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Chất liệu");

        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Số lượng tồn");

        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Số lượng bán");

        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Giá nhập");

        cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("Giá bán");

        cell = row.createCell(9, CellType.STRING);
        cell.setCellValue("Doanh thu");

        cell = row.createCell(10, CellType.STRING);
        cell.setCellValue("Năm bảo hành");

        row = sheet.createRow(0);
        cell = row.createCell(3, CellType.STRING);

        JFileChooser f = new JFileChooser("");
        f.setDialogTitle("Mở  file");
        f.showSaveDialog(null);

        File file = new File(f.getSelectedFile() + ".xlsx");

        if (f.getSelectedFile() == null) {
            return;
        }
        for (int i = 0; i < listTKSanPham.size(); i++) {
            //Modelbook book =listTK.get(i);
            row = sheet.createRow(3 + i);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(listTKSanPham.get(i).getMaSp());

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(listTKSanPham.get(i).getTenSp());

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(listTKSanPham.get(i).getMau());

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(listTKSanPham.get(i).getSize());

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(listTKSanPham.get(i).getChatLieu());

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(listTKSanPham.get(i).getSlTon());

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue(listTKSanPham.get(i).getSlBan());

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue(listTKSanPham.get(i).getGianhap());

            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue(listTKSanPham.get(i).getDonGia());

            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue(listTKSanPham.get(i).getDoanhThu());

            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue(listTKSanPham.get(i).getNamBH());

        }

        try {
            FileOutputStream fis = new FileOutputStream(file);
            workbook.write(fis);
            JOptionPane.showMessageDialog(this, "in thanh cong D:\\danhsach");
            fis.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_btnXuatFile1ActionPerformed

    private void btnMailSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMailSanPhamActionPerformed
        String input = null;
        input = JOptionPane.showInputDialog(this, "Nhập địa chỉ email cần gửi", "Địa chỉ", JOptionPane.YES_NO_OPTION);
        if (input == null) {
            return;
        }
        if (input != null && input.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Địa chỉ mail không hợp lệ");
            return;
        }

        final String username = "hoangnhph24464@fpt.edu.vn";
        final String password = "Hh30052002";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hoangnhph24464@fpt.edu.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(input)
            );
            message.setSubject("Xin chào bạn");
            message.setText("Số giày đang kinh doanh: " + lblSoGiayKd.getText() + "\n"
                    + "Số giày đã hết hàng: " + lblSoGiayHet.getText() + "\n"
                    + "Số giày sắp hết hàng: :" + lblSoGiaySapHet.getText() + "\n");

            try {
                Transport.send(message);
            } catch (MessagingException messagingException) {
                JOptionPane.showMessageDialog(this, "Địa chỉ email không đúng");
                return;
            }

            JOptionPane.showMessageDialog(this, "Gửi thành công");

        } catch (AddressException ex) {
            JOptionPane.showMessageDialog(this, "Địa chỉ email không đúng");

            ex.printStackTrace();
        } catch (MessagingException e) {
            System.out.println("ahihi3");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnMailSanPhamActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String sl = txtSoluong.getText();
        int slInt = 0;
        if (sl.trim().length() == 0) {
            lblLoi.setText("Vui lòng nhập số lượng mong muốn!");
            return;
        } else {
            lblLoi.setText("");
        }
        try {
            slInt = Integer.parseInt(sl);
            lblLoi.setText("");
        } catch (NumberFormatException numberFormatException) {
            lblLoi.setText("Vui lòng nhập số nguyen!");
            return;
        }

        if (slInt <= 0) {
            lblLoi.setText("Vui lòng nhập số lớn hơn 0!");
            return;
        } else {
            lblLoi.setText("");
        }

        listTKSanPham = thongKe_Serv.getMinSoLuongTon(slInt);
        loadTableSanPham(listTKSanPham);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblGiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGiamGiaMouseClicked
        int row = tblGiamGia.getSelectedRow();
        txtMa1.setText(tblGiamGia.getValueAt(row, 0).toString().trim());
        txtPhanTramGiam.setText(tblGiamGia.getValueAt(row, 1).toString().trim());
        txtSoLuong1.setText(tblGiamGia.getValueAt(row, 2).toString().trim());
        //        dateNgayBatDau.setDate((tblGiamGia.getModel().getValueAt(row, 3).toString()));
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("dd-MM-yyyy");
        Date date = new Date();

        DateNgayBatDau.setDateFormatString(tblGiamGia.getValueAt(row, 3).toString().trim());
        DateNgayKetThuc.setDateFormatString(tblGiamGia.getValueAt(row, 4).toString().trim());
        DateNgayBatDau.setDate(date);
        DateNgayKetThuc.setDate(date);

//        String dateNgayBd = sdf.format(DateNgayBatDau.getDate());
//        String dateNgayKt = sdf.format(DateNgayKetThuc.getDate());
//        try {
//
//            date = sdf.parse(dateNgayBd);
//            date = sdf.parse(dateNgayKt);
//
//            
//
//        } catch (ParseException ex) {
//            ex.printStackTrace();
//        }
        txtDieuKien.setText(tblGiamGia.getValueAt(row, 5).toString().trim());
        if (tblGiamGia.getValueAt(row, 6).toString().equals("1")) {
            rdoCo.setSelected(true);
        } else {
            rdokhong.setSelected(true);
        }
    }//GEN-LAST:event_tblGiamGiaMouseClicked

    private void tblChiTietGiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietGiamGiaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblChiTietGiamGiaMouseClicked

    private void txtMa1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMa1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMa1MouseClicked

    private void txtMa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMa1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMa1ActionPerformed

    private void btnSua2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua2ActionPerformed
        //        MaGiamGia ma = getData();
        //        maSer.updateMa(ma);
        //        listMa = maSer.getAll();
        //        showDataTable(listMa);
        //        int rowCTGG = tblChiTietGiamGia.getSelectedRow();
        //        int rowgg = tblGiamGia.getSelectedRow();
        //        int rowsp = tblChiTietSP.getSelectedRow();
        //        AddChiTiet chiTiet = getFormChiTietGiamGia();
        //        String maGG = tblGiamGia.getValueAt(rowgg, 0).toString();
        //        String maSP = tblChiTietSP.getValueAt(rowsp, 0).toString();
        //        String maCTSP = tblChiTietGiamGia.getValueAt(rowCTGG, 1).toString();
        //        String maCTGG = tblChiTietGiamGia.getValueAt(rowCTGG, 0).toString();
        //        String giamGia = maSer.layIdByMa(maGG);
        //        String sanPham = ctspSer.layIdByMa(maSP);
        //        String idChiTiet = ctggSer.getIdChiTiet(maCTGG, maCTSP);
        //        System.out.println(giamGia);
        //        System.out.println(sanPham);
        //        System.out.println(idChiTiet);
        //        ctggSer.updateChiTiet(chiTiet, giamGia, sanPham, idChiTiet);
        //        showDataChiTietGiamGia();

        int rowCTGG = tblChiTietGiamGia.getSelectedRow();
        if (rowCTGG == -1) {
            JOptionPane.showMessageDialog(this, "Vui long chon chi tiet giam gia");
            return;
        }
        int rowgg = tblGiamGia.getSelectedRow();
        if (rowgg == -1) {
            JOptionPane.showMessageDialog(this, "Vui long chon ma giam gia");
            return;
        }
        int rowsp = tblChiTietSP.getSelectedRow();
        if (rowsp == -1) {
            JOptionPane.showMessageDialog(this, "Vui long chon ma san pham");
            return;
        }
        AddChiTiet chiTiet = getFormChiTietGiamGia();
        if (chiTiet == null) {
            return;
        }
        String maGG = tblGiamGia.getValueAt(rowgg, 0).toString();
        String maSP = tblChiTietSP.getValueAt(rowsp, 0).toString();
        String maCTSP = tblChiTietGiamGia.getValueAt(rowCTGG, 1).toString();
        String maCTGG = tblChiTietGiamGia.getValueAt(rowCTGG, 0).toString();
        String giamGia = maSer.layIdByMa(maGG);
        String sanPham = ctspSer.layIdByMa(maSP);
        String idChiTiet = ctggSer.getIdChiTiet(maCTGG, maCTSP);
        //        System.out.println(giamGia);
        //        System.out.println(sanPham);
        //        System.out.println(idChiTiet);
        ctggSer.updateChiTiet(chiTiet, giamGia, sanPham, idChiTiet);
        JOptionPane.showMessageDialog(this, "Sửa mã chi tiết thành công");
        showDataChiTietGiamGia();

//        MaGiamGia ma = getData();
//        maSer.updateMa(ma);
//        listMa = maSer.getAll();
//        showDataTable(listMa);
    }//GEN-LAST:event_btnSua2ActionPerformed

    private void btnXoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa2ActionPerformed
        int row = tblChiTietGiamGia.getSelectedRow();
        int dong = tblGiamGia.getSelectedRow();

        if (row == -1) {
            maSer.delete(tblGiamGia.getValueAt(dong, 0).toString());
            JOptionPane.showMessageDialog(this, "Xóa mã thành công");
            showDataGiamGia();
            return;
        }

        if (dong == -1) {
            String maGG = tblChiTietGiamGia.getValueAt(row, 0).toString();
            String maCTSP = tblChiTietGiamGia.getValueAt(row, 1).toString();
            String idChiTiet = ctggSer.getIdChiTiet(maGG, maCTSP);
            ctggSer.deleteMaGiamGia(idChiTiet);
            listctgg = ctggSer.getAllChiTietGiamGia();
            JOptionPane.showMessageDialog(this, "Xóa mã chi tiết thành công");
        }
        showDataChiTietGiamGia();
    }//GEN-LAST:event_btnXoa2ActionPerformed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        if (checkForm() == 0) {
            if (maSer.checkTrungMa(txtMa1.getText())) {
                JOptionPane.showMessageDialog(this, "mã giảm giá này đã tồn tại!!");
                return;
            }
            MaGiamGia ma = getData();
            maSer.addMa(ma);
            listMa = maSer.getAll();
            showDataTable(listMa);
//        int index = tblChiTietSP.getRowCount();
            AddChiTiet chiTiet = getFormChiTietGiamGia();
//        for (int i = 0; i < index; i++) {

//            System.out.println(row[i]);
            ctggSer.addChiTiet(chiTiet);
            showDataChiTietGiamGia();
        }
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnThemNVbtnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVbtnThem1ActionPerformed
        if (checkNV()) {
            int chon = JOptionPane.showConfirmDialog(this, "Xac nhan them");
            if (chon == JOptionPane.YES_OPTION) {
                NhanVienViewModel nv = getFormNV();
                if (chService.checkTrungMa(nv.getMa())) {
                    JOptionPane.showMessageDialog(null, "trung ma nhanVien");
                    txtMa.requestFocus();
                } else {
                    chService.them(nv);
                    fillTableNV();
                    JOptionPane.showMessageDialog(this, "Them thanh cong");
                    clear();
                }
            }

        }
    }//GEN-LAST:event_btnThemNVbtnThem1ActionPerformed

    private void btnSuaNVbtnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNVbtnSua1ActionPerformed
        int row = this.tblNhanvien.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Hãy chọn 1 dòng trên Table");
            return;
        }
        if (checkNV()) {
            int chon = JOptionPane.showConfirmDialog(this, "Xac nhan sua");
            if (chon == JOptionPane.YES_OPTION) {
                NhanVienViewModel nv = getFormNV();
                chService.sua(nv);
                fillTableNV();
                JOptionPane.showMessageDialog(this, "Sua thanh cong");
                clear();
            }
        }

    }//GEN-LAST:event_btnSuaNVbtnSua1ActionPerformed

    private void btnResetNVbtnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetNVbtnResetActionPerformed
        txtMa.setText("");
        txtTen.setText("");
        txtNgaySinh.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        rdoLam.setSelected(true);
        cboChucVu.setSelectedIndex(0);
        txtMatKhau.setText("");
    }//GEN-LAST:event_btnResetNVbtnResetActionPerformed

    private void tblNhanvientblNhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanvientblNhanvienMouseClicked
        int row = tblNhanvien.getSelectedRow();
        NhanVien ch = list.get(row);
        txtMa.setText(ch.getMa());
        txtTen.setText(ch.getHoTen());
        txtDiaChi.setText(ch.getDiaChi());
        txtEmail.setText(ch.getEmail());
        txtSDT.setText(ch.getSoDienThoai());
        txtNgaySinh.setText(ch.getNgaySinh());
        cboChucVu.setSelectedItem(ch.getChucVu().getTen());
        if (ch.getTrangThai() == 1) {
            rdoLam.setSelected(true);
        } else {
            rdoNghi.setSelected(true);
        }
        txtMatKhau.setText(ch.getMatKhau());
    }//GEN-LAST:event_tblNhanvientblNhanvienMouseClicked

    private void btnLoadNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadNVActionPerformed
        fillTableNV();
    }//GEN-LAST:event_btnLoadNVActionPerformed

    private void lblBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBanHangMouseClicked
        ImageIcon img = new ImageIcon(getClass().getResource("/img/Hover_BanHang.png/"));
        jLabel1.setIcon(img);
        pnlBanHang.setVisible(true);
        pnlSanPham.setVisible(false);
        pnlNhanvien.setVisible(false);
        pnlThongKe.setVisible(false);
        pnlGiamGia.setVisible(false);
        fillSPBH();
        fillTableChiTietGiay();
    }//GEN-LAST:event_lblBanHangMouseClicked

    private void lblSanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanphamMouseClicked
        ImageIcon img = new ImageIcon(getClass().getResource("/img/Hover_SanPham.png/"));
        jLabel1.setIcon(img);
        if (DangNhap_Resp.checkDNChuChQuyen(txtTenNhanVien.getText()) == false) {
            pnlBanHang.setVisible(false);
            pnlThongKe.setVisible(false);
            pnlGiamGia.setVisible(false);
            pnlNhanvien.setVisible(false);
            pnlSanPham.setVisible(false);
            pnlNenGiaoCa.setVisible(false);
            pnlQuyen.setVisible(true);
            return;
        }
        pnlBanHang.setVisible(false);
        pnlThongKe.setVisible(false);
        pnlGiamGia.setVisible(false);
        pnlNhanvien.setVisible(false);

        pnlSanPham.setVisible(true);
    }//GEN-LAST:event_lblSanphamMouseClicked

    private void lblThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThoatMouseClicked

        if (Giaoca.checkTonTaiCaLam() && DangNhap_Resp.checkDNChuChQuyen(txtTenNhanVien.getText()) == false) {
            JOptionPane.showMessageDialog(this, "Vui lòng kết ca trước khi thoát");
            return;
        }
        System.exit(0);
    }//GEN-LAST:event_lblThoatMouseClicked

    private void lblNhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNhanvienMouseClicked
        ImageIcon img = new ImageIcon(getClass().getResource("/img/Hover_Nhanvien.png/"));
        jLabel1.setIcon(img);

        if (DangNhap_Resp.checkDNChuChQuyen(txtTenNhanVien.getText()) == false) {
            pnlBanHang.setVisible(false);
            pnlThongKe.setVisible(false);
            pnlGiamGia.setVisible(false);
            pnlNhanvien.setVisible(false);
            pnlSanPham.setVisible(false);
            pnlNenGiaoCa.setVisible(false);
            pnlQuyen.setVisible(true);
            return;
        }
        pnlBanHang.setVisible(false);
        pnlSanPham.setVisible(false);
        pnlThongKe.setVisible(false);
        pnlGiamGia.setVisible(false);
        pnlNhanvien.setVisible(true);
    }//GEN-LAST:event_lblNhanvienMouseClicked

    private void lblGiaoCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGiaoCaMouseClicked

        ImageIcon img = new ImageIcon(getClass().getResource("/img/Hover_GiaoCa.png/"));
        jLabel1.setIcon(img);
        new QLGCA().setVisible(true);

        pnlBanHang.setVisible(false);
        pnlSanPham.setVisible(false);
        pnlThongKe.setVisible(false);
        pnlGiamGia.setVisible(false);
        pnlNhanvien.setVisible(false);
        pnlNenGiaoCa.setVisible(true);

    }//GEN-LAST:event_lblGiaoCaMouseClicked

    private void lblGiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGiamGiaMouseClicked
        ImageIcon img = new ImageIcon(getClass().getResource("/img/Hover_GiamGia.png/"));
        jLabel1.setIcon(img);

        if (DangNhap_Resp.checkDNChuChQuyen(txtTenNhanVien.getText()) == false) {
            pnlBanHang.setVisible(false);
            pnlThongKe.setVisible(false);
            pnlGiamGia.setVisible(false);
            pnlNhanvien.setVisible(false);
            pnlSanPham.setVisible(false);
            pnlNenGiaoCa.setVisible(false);
            pnlQuyen.setVisible(true);
            return;
        }
        pnlBanHang.setVisible(false);
        pnlSanPham.setVisible(false);
        pnlThongKe.setVisible(false);
        pnlNhanvien.setVisible(false);

        pnlGiamGia.setVisible(true);
        showDataTableCTSP();
    }//GEN-LAST:event_lblGiamGiaMouseClicked

    private void lblGiamGiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGiamGiaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblGiamGiaMouseEntered

    private void lblThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThongKeMouseClicked
        ImageIcon img = new ImageIcon(getClass().getResource("/img/Hover_ThongKe.png/"));
        jLabel1.setIcon(img);

        if (DangNhap_Resp.checkDNChuChQuyen(txtTenNhanVien.getText()) == false) {
            pnlBanHang.setVisible(false);
            pnlThongKe.setVisible(false);
            pnlGiamGia.setVisible(false);
            pnlNhanvien.setVisible(false);
            pnlSanPham.setVisible(false);
            pnlNenGiaoCa.setVisible(false);
            pnlQuyen.setVisible(true);
            return;
        }
        loadTableCaLam();
        setPanelThongKe(thongKe_Serv.getTongSoHD(), thongKe_Serv.getDoanhThu());
        setPanelSanPham();
        listTKSanPham = thongKe_Serv.getAll();
        loadTableSanPham(listTKSanPham);

        pnlBanHang.setVisible(false);
        pnlSanPham.setVisible(false);

        pnlGiamGia.setVisible(false);
        pnlNhanvien.setVisible(false);
        pnlThongKe.setVisible(true);
    }//GEN-LAST:event_lblThongKeMouseClicked

    private void lblDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangXuatMouseClicked
        if (Giaoca.checkTonTaiCaLam() && DangNhap_Resp.checkDNChuChQuyen(txtTenNhanVien.getText()) == false) {
            JOptionPane.showMessageDialog(this, "Vui lòng kết ca trước khi đăng xuất");
            return;
        }

        this.setVisible(false);
        new DangNhap_Form().setVisible(true);
    }//GEN-LAST:event_lblDangXuatMouseClicked

    private void btnMailDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMailDoanhThuActionPerformed
        String input = null;
        input = JOptionPane.showInputDialog(this, "Nhập địa chỉ email cần gửi", "Địa chỉ", JOptionPane.YES_NO_OPTION);
        if (input == null) {
            return;
        }
        if (input != null && input.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Địa chỉ mail không hợp lệ");
            return;
        }

        final String username = "hoangnhph24464@fpt.edu.vn";
        final String password = "Hh30052002";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hoangnhph24464@fpt.edu.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(input)
            );
            message.setSubject("Xin chào bạn");
            message.setText("Số đơn hàng: " + lblTongDonHang.getText() + "\n"
                    + "Doanh thu: " + lblDoanhthu.getText() + "\n"
                    + "Hóa đơn hủy: :" + lblHoaDonHuy1.getText() + "\n"
                    + "Hóa đơn giao: " + lblHoaDonGiao.getText());

            try {
                Transport.send(message);
            } catch (MessagingException messagingException) {
                JOptionPane.showMessageDialog(this, "Địa chỉ email không đúng");

                return;

            }

            JOptionPane.showMessageDialog(this, "Gửi thành công");

        } catch (AddressException ex) {
            System.out.println("Địa chỉ email không đúng");
            return;

        } catch (MessagingException e) {
            System.out.println("ahihi3");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnMailDoanhThuActionPerformed

    private void btnTimKiemTheoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemTheoNamActionPerformed
        String nam = cbbNam.getSelectedItem().toString();
        listTKDoanhThu = thongKe_Serv.getAllTHeoNam(nam);
        loadTableThongKe(listTKDoanhThu);
        setPanelThongKe2(thongKe_Serv.getTongSoHDTheoNgay(nam + "-1-1", nam + "-12-30"), thongKe_Serv.getDoanhThuTheoNgay(nam + "-1-1", nam + "-12-30"), thongKe_Serv.getSoHDHuyTheoNgay(nam + "-1-1", nam + "-12-30"), thongKe_Serv.getSoHDGiaoTheoNgay(nam + "-1-1", nam + "-12-30"));
    }//GEN-LAST:event_btnTimKiemTheoNamActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        Date batDau = NgayBatDau.getDate();
        Date KetThuc = NgayKetThuc.getDate();
        String ngayBD = null;
        String ngayKT = null;
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd");
        ngayBD = sdf.format(batDau);
        ngayKT = sdf.format(KetThuc);
        if (ngayKT.compareTo(ngayBD) < 0) {
            JOptionPane.showMessageDialog(this, "Ngày tìm kiếm phải lớn hơn ngày bắt đầu");
            return;
        }

        listTKDoanhThu = thongKe_Serv.getAllTheoKhoangNgay(ngayBD, ngayKT);

        loadTableThongKe(listTKDoanhThu);
        setPanelThongKe2(thongKe_Serv.getTongSoHDTheoNgay(ngayBD, ngayKT), thongKe_Serv.getDoanhThuTheoNgay(ngayBD, ngayKT), thongKe_Serv.getSoHDHuyTheoNgay(ngayBD, ngayKT), thongKe_Serv.getSoHDGiaoTheoNgay(ngayBD, ngayKT));
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnXuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileActionPerformed

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("danh sach");
        //
        XSSFRow row = null;
        Cell cell = null;
        row = sheet.createRow(2);

        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Mã hóa đơn");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Mã giày");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Tên giày");

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Đơn giá");

        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Số lượng bán");

        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Tiền mặt");

        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Tiền chuyển khoản");

        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Giảm giá");

        cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("Doanh thu");

        cell = row.createCell(9, CellType.STRING);
        cell.setCellValue("Ngày bán");

        cell = row.createCell(10, CellType.STRING);
        cell.setCellValue("Trạng thái");

        cell = row.createCell(11, CellType.STRING);
        cell.setCellValue("Lí do hủy");

        row = sheet.createRow(0);
        cell = row.createCell(3, CellType.STRING);

        for (int i = 0; i < listTKDoanhThu.size(); i++) {
            //Modelbook book =listTK.get(i);
            row = sheet.createRow(3 + i);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(listTKDoanhThu.get(i).getMaHD());

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(listTKDoanhThu.get(i).getMaSp());

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(listTKDoanhThu.get(i).getTenSp());

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(listTKDoanhThu.get(i).getDonGia());

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(listTKDoanhThu.get(i).getSlBan());

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(listTKDoanhThu.get(i).getTienmat());

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue(listTKDoanhThu.get(i).getTienkhac());

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue(listTKDoanhThu.get(i).getTiengiamgia());

            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue(listTKDoanhThu.get(i).getDoanhThu());

            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue(listTKDoanhThu.get(i).getNgaytao());

            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue(listTKDoanhThu.get(i).getTt());

            cell = row.createCell(11, CellType.STRING);
            cell.setCellValue(listTKDoanhThu.get(i).getLidoHuy());

        }

        JFileChooser f = new JFileChooser("D:\\");
        f.setDialogTitle("Mở  file");
        f.showSaveDialog(null);

        File file = new File(f.getSelectedFile() + ".xlsx");
        if (f.getSelectedFile() == null) {
            return;
        }

        try {
            FileOutputStream fis = new FileOutputStream(file);
            workbook.write(fis);
            JOptionPane.showMessageDialog(this, "in thanh cong");
            fis.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnXuatFileActionPerformed

    private void cbbBolocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbBolocItemStateChanged
        if (cbbBoloc.getSelectedIndex() == 0) {
            pnlTimkiemTong.setVisible(false);
            listTKDoanhThu = thongKe_Serv.getAllTheoNgayHomNay();
            loadTableThongKe(listTKDoanhThu);
            setPanelThongKe(thongKe_Serv.getTongSoHD(), thongKe_Serv.getDoanhThu());
        } else if (cbbBoloc.getSelectedIndex() == 1) {
            pnlTimkiemTong.setVisible(true);
            pnlTheoNam.setVisible(false);
            pnlTheoNgay.setVisible(true);

        } else {
            pnlTimkiemTong.setVisible(true);
            pnlTheoNgay.setVisible(false);
            pnlTheoNam.setVisible(true);
        }
    }//GEN-LAST:event_cbbBolocItemStateChanged

    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked

    }//GEN-LAST:event_jPanel12MouseClicked

    private void pnlWebCamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlWebCamMouseClicked


    }//GEN-LAST:event_pnlWebCamMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int rowhdt = tblHoaDonCho.getSelectedRow();
        int rowhdtreo = tblHoaDonTreo.getSelectedRow();
        int rowhtht = tblHoaDonHoanThanh.getSelectedRow();
        if (rowhtht >= 0) {
            JOptionPane.showMessageDialog(this, "Hóa đơn đã hoàn thành !");
            return;
        }
        if (tbbTao.getSelectedIndex() == 0) {
            if (rowhdt == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn tạo !");
                return;
            }
        }
        if (tbbTao.getSelectedIndex() == 2) {
            if (rowhdtreo == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn treo !");
                return;
            }
        }
        String mahd = txtMaHD.getText();
        String idhd = HoaDonBanHangRepository.getIDHDByMaHD(mahd);
        if (tbbTao.getSelectedIndex() == 2) {
            if (HoaDonBanHangRepository.getTTByIDHD(idhd) == 5 && HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhd).equals("Giao hàng")) {
                JOptionPane.showMessageDialog(this, "Hóa đơn đang giao không thể thêm sản sản phẩm");
                return;
            }
        }
        if (tbbTao.getSelectedIndex() == 1) {
            if (HoaDonBanHangRepository.getTTByIDHD(idhd) == 3 && HoaDonBanHangRepository.getHinhThucMuaByIdhd(idhd).equals("Giao hàng")) {
                JOptionPane.showMessageDialog(this, "Hóa đơn đang giao không thể thêm sản phẩm");
                return;
            }
        }
        new barcode().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnTimTheoNgay1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimTheoNgay1ActionPerformed
        // TODO add your handling code here:
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        listhdbh = HoaDonBanHangRepository.getHoaDonByNgay(df.format(txtDate.getDate()));
        System.out.println(df.format(txtDate.getDate()));
        dtm = (DefaultTableModel) tblHoaDonHoanThanh.getModel();
        dtm.setRowCount(0);
        for (int i = 0; i < listhdbh.size(); i++) {

            if (listhdbh.get(i).getTrangThai() == 1) {
                Object[] data = new Object[]{
                    listhdbh.get(i).getMa(),
                    listhdbh.get(i).getTennv(),
                    listhdbh.get(i).getTenkh(),
                    listhdbh.get(i).getThanhtien(),};
                dtm.addRow(data);
            }

        }

        dtm = (DefaultTableModel) tblHoaDonCho.getModel();
        dtm.setRowCount(0);
        for (int i = 0; i < listhdbh.size(); i++) {
            if (listhdbh.get(i).getTrangThai() == 2) {
                Object[] data = new Object[]{
                    listhdbh.get(i).getMa(),
                    listhdbh.get(i).getTennv(),
                    listhdbh.get(i).getTenkh(),
                    listhdbh.get(i).getThanhtien(),};

                dtm.addRow(data);
            }
        }

        dtm = (DefaultTableModel) tblHoaDonGiao.getModel();
        dtm.setRowCount(0);
        for (int i = 0; i < listhdbh.size(); i++) {
            if (listhdbh.get(i).getTrangThai() == 3) {
                Object[] data = new Object[]{
                    listhdbh.get(i).getMa(),
                    listhdbh.get(i).getTennv(),
                    listhdbh.get(i).getTenkh(),
                    listhdbh.get(i).getLiDo(),};
                dtm.addRow(data);
            }
        }
        dtm = (DefaultTableModel) tblHoaDonTreo.getModel();
        dtm.setRowCount(0);
        for (int i = 0; i < listhdbh.size(); i++) {
            if (listhdbh.get(i).getTrangThai() == 5) {
                Object[] data = new Object[]{
                    listhdbh.get(i).getMa(),
                    listhdbh.get(i).getTennv(),
                    listhdbh.get(i).getTenkh(),
                    listhdbh.get(i).getLiDo(),};
                dtm.addRow(data);
            }
        }
    }//GEN-LAST:event_btnTimTheoNgay1ActionPerformed

    private void btnQuetMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuetMaActionPerformed
        // TODO add your handling code here:
        new barcodeSanPham().setVisible(true);
    }//GEN-LAST:event_btnQuetMaActionPerformed

    private void btnCapNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhapActionPerformed
        int row = tblChiTietGiamGia.getSelectedRow();
//        
        MaGiamGia ma = getData();
        maSer.updateMa(ma);
        listMa = maSer.getAll();
        JOptionPane.showMessageDialog(this, "Cập nhập mã thành công");
//        showDataTable(listMa);
        showDataGiamGia();
        if (tblChiTietGiamGia.getValueAt(row, 4).toString().equalsIgnoreCase(lblNgayHienTai.getText())) {
//            ChiTietGiay sp = getFormChiTietGiay();
            ctspSer.updateGia(ctspSer.layIdByMa(tblChiTietGiamGia.getValueAt(row, 1).toString()),
                    ctggSer.getIdChiTiet(tblChiTietGiamGia.getValueAt(row, 0).toString(),
                            tblChiTietGiamGia.getValueAt(row, 1).toString()));
            JOptionPane.showMessageDialog(this, "Cập nhập giá thành công");
            showDataChiTietGiamGia();
        }
    }//GEN-LAST:event_btnCapNhapActionPerformed

    private void cbbLocCvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocCvActionPerformed
        if (cbbLocCv.getItemCount() > 0) {
            dtm = (DefaultTableModel) this.tblNhanvien.getModel();
            dtm.setRowCount(0);

            String chucvu = cbbLocCv.getSelectedItem().toString();
            list = chService.getAllNhanVien();
            String tt = null;
            for (NhanVien y : list) {
                if (y.getTrangThai() == 1) {
                    tt = "Đang làm";
                } else {
                    tt = "Đang nghỉ";
                }

                if (y.getChucVu().getTen().equalsIgnoreCase(chucvu)) {
                    dtm.addRow(new Object[]{
                        y.getMa(), y.getHoTen(), y.getChucVu().getTen(), tt
                    });
                }

            }
        } else {
            return;
        }
    }//GEN-LAST:event_cbbLocCvActionPerformed

    private void txtTimKiemNVCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemNVCaretUpdate
        String tk = txtTimKiemNV.getText();
        if (tk.equals("")) {
            return;
        }
        list = chService.getAllNV(tk);
        dtm = (DefaultTableModel) tblNhanvien.getModel();
        dtm.setRowCount(0);
        String tt = null;
        for (NhanVien nv : list) {
            if (nv.getTrangThai() == 1) {
                tt = "Đang làm";
            } else {
                tt = "Đang nghỉ";
            }
            Object[] data = {
                nv.getMa(),
                nv.getHoTen(),
                nv.getChucVu().getTen(),
                tt
            };
            dtm.addRow(data);
        }
    }//GEN-LAST:event_txtTimKiemNVCaretUpdate

    private void barcodeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_barcodeCaretUpdate
        // TODO add your handling code here:
        String mabc = barcode.getText();
        String mahd = txtMaHD.getText();
        int check = 0;
        String idsp = SanPhanBanHangRepository.getIDCTGByMaCode(mabc);
        double dongia = SanPhanBanHangRepository.getDonGiaByMaCode(mabc);
        String idhd = HoaDonBanHangRepository.getIDHDByMaHD(mahd);
        for (int i = 0; i < listgh.size(); i++) {
            if (SanPhanBanHangRepository.getMaSp(mabc).equals(listgh.get(i).getMasp())) {
                System.out.println(listgh.get(i).getSl());
                HoaDonCTRepository.updateHDCT(listgh.get(i).getSl() + 1, idhd, idsp);
                check = 1;
                fillHDCT();
                if (pnlGiaoHang.isVisible() == true) {
                    txtTongTienGH.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd)));
                    txtCanTraGH.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd) - (HoaDonCTRepository.getSumTT(mahd) * Double.parseDouble(txtGiamGiaGH.getText()) / 100) + Double.parseDouble(txtPhiShip.getText())));
                    txtTienKhachTra.setText(String.valueOf(Double.parseDouble(txtCanTraGH.getText())));
                } else if (pnlTaiQuay.isVisible() == true) {
                    txtTongTienTQ.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd)));
                    txtTienKhachCanTra.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd) - (HoaDonCTRepository.getSumTT(mahd) * Double.parseDouble(txtKhuyenMaiThemTQ.getText()) / 100)));
                    txtTienDuTQ.setText(String.valueOf(Double.parseDouble(txtTienMatTQ.getText()) + Double.parseDouble(txtTienCKTQ.getText()) - Double.parseDouble(txtTienKhachCanTra.getText())));
                }
                return;
            }
        }
        fillSPBH();
        if (tbbTao.getSelectedIndex() == 0) {
            for (int i = 0; i < SanPhanBanHangRepository.getBarCode().size(); i++) {
                if (mabc.trim().equals(SanPhanBanHangRepository.getBarCode().get(i))) {
                    HoaDonCT hdct = new HoaDonCT(idhd, idsp, 1, dongia);
                    HoaDonCTRepository.addHDCT(hdct);

                    int slcon = SanPhanBanHangRepository.getSLGiay(SanPhanBanHangRepository.getMaSp(mabc));
                    int slthuc = slcon - 1;
                    SanPhanBanHangRepository.updateSLSP(slthuc, SanPhanBanHangRepository.getMaSp(mabc));
                    fillHDCT();
                    fillSPBH();
                }
            }
        }
        if (tbbTao.getSelectedIndex() == 2) {
            for (int i = 0; i < SanPhanBanHangRepository.getBarCode().size(); i++) {
                if (mabc.trim().equals(SanPhanBanHangRepository.getBarCode().get(i))) {
                    HoaDonCT hdct = new HoaDonCT(idhd, idsp, 1, dongia);
                    HoaDonCTRepository.addHDCT(hdct);

                    int slcon = SanPhanBanHangRepository.getSLGiay(SanPhanBanHangRepository.getMaSp(mabc));
                    int slthuc = slcon - 1;
                    SanPhanBanHangRepository.updateSLSP(slthuc, SanPhanBanHangRepository.getMaSp(mabc));
                    fillHDCT();
                    fillSPBH();
                }
            }
        }

        if (pnlGiaoHang.isVisible() == true) {
            txtTongTienGH.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd)));
            txtCanTraGH.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd) - (HoaDonCTRepository.getSumTT(mahd) * Double.parseDouble(txtGiamGiaGH.getText()) / 100) + Double.parseDouble(txtPhiShip.getText())));
            txtTienKhachTra.setText(String.valueOf(Double.parseDouble(txtCanTraGH.getText())));
        } else if (pnlTaiQuay.isVisible() == true) {
            txtTongTienTQ.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd)));
            txtTienKhachCanTra.setText(String.valueOf(HoaDonCTRepository.getSumTT(mahd) - (HoaDonCTRepository.getSumTT(mahd) * Double.parseDouble(txtKhuyenMaiThemTQ.getText()) / 100)));
            txtTienDuTQ.setText(String.valueOf(Double.parseDouble(txtTienMatTQ.getText()) + Double.parseDouble(txtTienCKTQ.getText()) - Double.parseDouble(txtTienKhachCanTra.getText())));
        }
    }//GEN-LAST:event_barcodeCaretUpdate

    private void tblNhanvienTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanvienTKMouseClicked
        int row = tblNhanvienTK.getSelectedRow();
        String id = chService.getNVByMaNV(tblNhanvienTK.getValueAt(row, 0).toString());
        int thang = cbbThangLamViec.getSelectedIndex() + 1;
        listgc = Giaoca.getGiaoCaTK(thang, id);
        dtm = (DefaultTableModel) tblChiTietGiaoCa.getModel();
        dtm.setRowCount(0);
        String tt = "";
        for (giaoCa ca : listgc) {
            if (ca.getTrangthai() == 1) {
                tt = "Ðang làm việc";

            } else if (ca.getTrangthai() == 2) {
                tt = "Ðã kết ca";
            }

            Object rowData[] = {
                ca.getNgaylam(),
                ca.getGioBatDau(),
                ca.getGioKetThuc(),
                ca.getTongtientrongca(),
                ca.getTienphatsinh(),
                ca.getTienmatrut(),
                tt,
                ca.getGhichu(),};
            dtm.addRow(rowData);
        }

    }//GEN-LAST:event_tblNhanvienTKMouseClicked

    //sanPham--------------------------------------------------------------------------------------
    private void lamMoi() {
        txtMaGiay.setText("");
        txtTenGiay.setText("");
        txtNamBaoHanh.setText("");
        txtSoLuong.setText("");
        txtGiaNhap.setText("");
        txtGiaBan.setText("");
        cbbChatLieu.setSelectedIndex(0);
        cbbHang.setSelectedIndex(0);
        cbbSize.setSelectedIndex(0);
        cbbMauSac.setSelectedIndex(0);
        lblAnh.setIcon(null);
        txtMaBarCode.setText("");
    }

    private void showDeTaiChiTietGiay(ChiTietGiay_View ct) {
        txtMaGiay.setText(ct.getMaGiay());
        txtTenGiay.setText(ct.getTenGiay());
        cbbMauSac.setSelectedItem(ct.getTenMauSac());
        cbbSize.setSelectedItem(ct.getSize());
        cbbChatLieu.setSelectedItem(ct.getChatLieu());
        cbbHang.setSelectedItem(ct.getTenHang());
        txtNamBaoHanh.setText(String.valueOf(ct.getNamBaoHanh()));
        txtSoLuong.setText(String.valueOf(ct.getSoLuong()));
        txtGiaNhap.setText(String.valueOf(ct.getGiaNhap()));
        txtGiaBan.setText(String.valueOf(ct.getGiaBan()));
//        lblAnh.setText("");
        int row = tblGiay.getSelectedRow();
        String icon = tblGiay.getValueAt(row, 8).toString();
        lblAnh.setIcon(resizeImage(String.valueOf(icon)));
    }

    private void showDeTaiThuocTinh(ThuocTinh tt) {
//        txtIdThuocTinh.setText(tt.getId());
        txtMaThuocTinh.setText(tt.getMa());
        txtTenThuocTinh.setText(tt.getTen());
        if (tt.getTrangThai() == 1) {
            ckbTrangThai.setSelected(true);
        } else {
            ckbTrangThai.setSelected(false);
        }
    }

    private ThuocTinh getFormThuocTinh() {
        String ma = txtMaThuocTinh.getText();
        String ten = txtTenThuocTinh.getText();
        int trangThai = ckbTrangThai.isSelected() ? 1 : 2;

        ThuocTinh tt = new ThuocTinh(null, ma, ten, trangThai);
        return tt;
    }

//    private void initWebcam() {
//        Dimension size = WebcamResolution.QVGA.getSize();
//        webcam = Webcam.getWebcams().get(0);
//        try {
//            webcam.setViewSize(size);
//        } catch (Exception e) {
//            return;
//        }
//        
//        panel = new WebcamPanel(webcam);
//        panel.setPreferredSize(size);
//        panel.setFPSDisplayed(true);
//        
//        pnlCamBanHang.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 280));
//        exe.execute(this);
//    }
    public int checkForm() {
        int dem = 0;

//        for (MaGiamGia Magg : listMa) {
//            if(txtMa1.getText().equalsIgnoreCase(Magg.getMaGiamGia()))
//            {
//                lblMa1.setText("*Mã đã tồn tại");
//                return dem++;
//            }    
//        }
        if (txtMa1.getText().equals("")) {
            lblMa1.setText("*Vui lòng nhập mã giảm giá");
            dem++;
        } else {
            lblMa1.setText(" ");
        }

        if (txtSoLuong1.getText().equals("")) {
            lblSoLuong1.setText("*Vui lòng nhập số lượng");
            dem++;
        } else {
            lblSoLuong1.setText(" ");
        }

        if (txtDieuKien.getText().equals("")) {
            lblDieuKien.setText("*Vui lòng nhập điệu kiện");
            dem++;
        } else {
            lblDieuKien.setText(" ");
        }

        if (txtPhanTramGiam.getText().equals("")) {
            lblPhanTramGiam.setText("*Vui lòng nhập phần trăm giảm");
            dem++;
        } else if (Integer.parseInt(txtPhanTramGiam.getText()) <= 0 || Integer.parseInt(txtPhanTramGiam.getText()) >= 50) {
            lblPhanTramGiam.setText("Vui lòng nhập phần trăm giảm lớn hơn 0 và nhỏ hơn 50 ");
            dem++;
        } else {
            lblPhanTramGiam.setText(" ");
        }

        if (txtSoLuong1.getText().equals("")) {
            lblSoLuong1.setText("*Vui lòng nhập số lượng");
            dem++;
        } else if (txtSoLuong1.getText().length() <= 0) {
            lblSoLuong1.setText("*Vui lòng nhập số lượng lớn hơn không");
            dem++;
        } else {
            lblSoLuong1.setText(" ");
        }
        System.out.println(dem);
        return dem;
    }

    public int check() {
        int dem = 0;

        if (txtMaGiay.getText().equals("")) {
            lblMaGiay.setText("*Vui lòng nhập mã giày");
            dem++;
        } else {
            lblMaGiay.setText(" ");
        }
        if (txtTenGiay.getText().equals("")) {
            lblTenGiay.setText("*Vui lòng nhập tên giày");
            dem++;
        } else {
            lblTenGiay.setText(" ");
        }
        if (txtNamBaoHanh.getText().equals("")) {
            lblNamBaoHanh.setText("*Vui lòng nhập năm bảo hành");
            dem++;
        } else if (Integer.parseInt(txtNamBaoHanh.getText()) <= 0) {
            lblNamBaoHanh.setText("*Vui lòng nhập năm bảo hành lớn hơn không");
            dem++;
        } else {
            lblNamBaoHanh.setText(" ");
        }
        if (txtSoLuong.getText().equals("")) {
            lblSoLuong.setText("*Vui lòng nhập số lượng");
            dem++;
        } else if (txtSoLuong.getText().length() <= 0) {
            lblSoLuong.setText("*Vui lòng nhập số lượng lớn hơn không");
            dem++;
        } else {
            lblSoLuong.setText(" ");
        }

        if (txtGiaNhap.getText().equals("")) {
            lblGiaNhap.setText("*Vui lòng nhập giá nhập");
            dem++;
        } else if (Integer.parseInt(txtGiaNhap.getText()) <= 0) {
            lblGiaNhap.setText("*Vui lòng nhập giá nhập lớn hơn không");
            dem++;
        } else {
            lblGiaNhap.setText(" ");
        }
        if (txtGiaBan.getText().equals("")) {
            lblGiaBan.setText("*Vui lòng nhập giá bán");
            dem++;
        } else if (txtGiaBan.getText().length() <= 0) {
            lblGiaBan.setText("*Vui lòng nhập giá bán lơn hơn không");
            dem++;
        } else {
            lblGiaBan.setText(" ");
        }
        Pattern sp1 = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~]", Pattern.CASE_INSENSITIVE);
        Matcher mt1 = sp1.matcher(txtMaGiay.getText());
        boolean cons1 = mt1.find();
        {
            if (cons1 == true) {
                lblMaGiay.setText("*Không được nhập kí tự đặc biệt trong mã giày");
                txtMaGiay.requestFocus();
                dem++;
            }
        }
        Pattern sp2 = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~[a-z]]", Pattern.CASE_INSENSITIVE);
        Matcher mt2 = sp2.matcher(txtSoLuong.getText());
        boolean cons2 = mt2.find();
        {
            if (cons2 == true) {
                lblSoLuong.setText("*Không được nhập kí tự đặc biệt trong số lượng");
                txtSoLuong.requestFocus();
                dem++;
            }
        }
        Pattern sp3 = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~[a-z]]", Pattern.CASE_INSENSITIVE);
        Matcher mt3 = sp3.matcher(txtNamBaoHanh.getText());
        boolean cons3 = mt3.find();
        {
            if (cons3 == true) {
                lblNamBaoHanh.setText("*Không được nhập kí tự đặc biệt trong năm bảo hành");
                txtNamBaoHanh.requestFocus();
                dem++;
            }
        }

        Pattern sp4 = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~[a-z]]", Pattern.CASE_INSENSITIVE);
        Matcher mt4 = sp4.matcher(txtGiaNhap.getText());
        boolean cons4 = mt4.find();
        {
            if (cons4 == true) {
                lblGiaNhap.setText("*Không được nhập kí tự đặc biệt trong giá nhập");
                txtGiaNhap.requestFocus();
                dem++;
            }
        }

        Pattern sp5 = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~[a-z]]", Pattern.CASE_INSENSITIVE);
        Matcher mt5 = sp5.matcher(txtGiaBan.getText());
        boolean cons5 = mt5.find();
        {
            if (cons5 == true) {
                lblGiaBan.setText("*Không được nhập kí tự đặc biệt trong giá bán");
                txtGiaBan.requestFocus();
                dem++;
            }
        }

        return dem;
    }

    private ChiTietGiay getFormChiTietGiay() {
//        String maG = txtMaGiay.getText();
//        String tenG = txtTenGiay.getText();
//        String idCL = chatLieuSV.getIdChatLieuByTen(cbbChatLieu.getSelectedItem().toString());
//        String idMS = mauSV.getIdMauSacByTen(cbbMauSac.getSelectedItem().toString());
//        String idS = sizeSV.getIdSizeByTen(cbbSize.getSelectedItem().toString());
//        String idH = hangSV.getIdHangByTen(cbbHang.getSelectedItem().toString());
//        int namBH = Integer.parseInt(txtNamBaoHanh.getText());
//        int soLuong = Integer.parseInt(txtSoLuong.getText());
//        double giaNhap = Double.parseDouble(txtGiaNhap.getText());
//        double giaBan = Double.parseDouble(txtGiaBan.getText());
//        String anh = lblAnh.getText();
//
//        ChiTietGiay ct = new ChiTietGiay(null, maG, tenG, idMS, idS, idCL, idH, namBH, soLuong, giaNhap, giaBan, anh);
//        return ct;

        ChiTietGiay ct = new ChiTietGiay();
        ct.setMaGiay(txtMaGiay.getText());
        ct.setTenGiay(txtTenGiay.getText());
        ct.setIdMauSac(mauSV.getIdMauSacByTen(cbbMauSac.getSelectedItem().toString()));
        ct.setIdSize(sizeSV.getIdSizeByTen(cbbSize.getSelectedItem().toString()));
        ct.setIdChatLieu(chatLieuSV.getIdChatLieuByTen(cbbChatLieu.getSelectedItem().toString()));
        ct.setIdHang(hangSV.getIdHangByTen(cbbHang.getSelectedItem().toString()));
        ct.setNamBaoHanh(Integer.parseInt(txtNamBaoHanh.getText()));
        ct.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        ct.setGiaNhap(Double.parseDouble(txtGiaNhap.getText()));
        ct.setGiaBan(Double.parseDouble(txtGiaBan.getText()));
        ct.setMaBarCode(txtMaBarCode.getText());
        ct.setAnh(anhStr);
        return ct;
    }

    public NhanVienViewModel getFormNV() {
        String ma = txtMa.getText();
        String ten = txtTen.getText();
        String dc = txtDiaChi.getText();
        String em = txtEmail.getText();
        String ns = txtNgaySinh.getText();
        String sdt = txtSDT.getText();
        int tt;
        if (rdoLam.isSelected()) {
            tt = 1;
        } else {
            tt = 0;
        }
        String cv = String.valueOf(cboChucVu.getSelectedItem());
        String mk = txtMatKhau.getText();
        ChucVuDomain idcv = cvService.layIdChucVu(cv);
        return new NhanVienViewModel(ma, ten, ns, sdt, dc, em, tt, mk, idcv);
    }

    @Override
    public void run() {
        do {
            try {
                while (true) {
                    Calendar now = Calendar.getInstance();
                    int hour = now.get(Calendar.HOUR_OF_DAY);
                    int minute = now.get(Calendar.MINUTE);
                    int second = now.get(Calendar.SECOND);
                    int month = now.get(Calendar.MONTH) + 1;
                    int day = now.get(Calendar.DAY_OF_MONTH);
                    int year = now.get(Calendar.YEAR);
                    String txt = hour + ":" + minute + ":" + second;
//                    String lblNTN = day + " - " + month + " - " + year;
                    String lblNTN = year + "-" + month + "-" + day;
                    this.lblDongHo.setText(txt);
                    lblNgayHienTai.setText(lblNTN);
                    Thread.sleep(100);
                }

            } catch (InterruptedException e) {
//                e.printStackTrace();
            }

//            Result rs = null;
//            BufferedImage image = null;
//            
//            if (webcam.isOpen()) {
//                if ((image = webcam.getImage()) == null) {
//                    continue;
//                }
//            }
//            
//            LuminanceSource source = new BufferedImageLuminanceSource(image);
//            
//            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//            
//            try {
//                rs = new MultiFormatReader().decode(bitmap);
//            } catch (NotFoundException notFoundException) {
////                notFoundException.printStackTrace();
//            }
//            
//            if (rs != null) {
//                txtMaBarCode.setText(rs.getText());
//                txtTimKiem.setText(rs.getText());
//            }
//            
        } while (true);
    }

    public void clear() {
        txtMa.setText("");
        txtTen.setText("");
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtMatKhau.setText("");
        txtSDT.setText("");
        txtNgaySinh.setText("");
    }

    public void clearlbl() {
        lblMa.setText("");
        lblTen.setText("");
        lblDiaChi.setText("");
        lblEmail.setText("");
        lblSDT.setText("");
        lblMatKhau.setText("");
        lblNgaySinh.setText("");
    }

    public boolean checkNV() {
        clearlbl();
        String ma = txtMa.getText();
        String ten = txtTen.getText();
        String dc = txtDiaChi.getText();
        String email = txtEmail.getText();
        String sdt = txtSDT.getText();
        String mk = txtMatKhau.getText();
        String ns = txtNgaySinh.getText();

        int dem = 0;
        if (ma.isBlank()) {
            lblMa.setText("Vui lòng nhập mã nhân viên");
            dem++;
        } else {
            lblMa.setText("");
        }
        if (ten.isBlank()) {
            lblTen.setText("Vui lòng nhập tên nhân viên");
            dem++;
        } else {
            lblTen.setText("");
        }
        if (dc.isBlank()) {
            lblDiaChi.setText("Vui lòng nhập địa chỉ");
            dem++;
        } else {
            lblDiaChi.setText("");
        }
        if (email.isBlank()) {
            lblEmail.setText("Vui lòng nhập email");
            dem++;
        } else {
            lblEmail.setText("");
        }
        if (sdt.isBlank()) {
            lblSDT.setText("Vui lòng nhập số điện thoại");
            dem++;
        } else {
            lblSDT.setText("");
        }
        if (mk.isBlank()) {
            lblMatKhau.setText("Vui lòng nhập mật khẩu");
            dem++;
        } else {
            lblMatKhau.setText("");
        }
        if (ns.isBlank()) {
            lblNgaySinh.setText("Vui lòng nhập ngày sinh");
            dem++;
        } else {
            lblNgaySinh.setText("");
        }

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd-mm-yyyy");
        Date d;
        String ngaySinh = txtNgaySinh.getText();
        try {
            d = format.parse(txtNgaySinh.getText());
        } catch (ParseException e) {
            lblNgaySinh.setText("Sai định dạng ngày tháng");
            txtNgaySinh.requestFocus();
            e.printStackTrace();
            dem++;
        }

        Pattern sp1 = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~]", Pattern.CASE_INSENSITIVE);
        Matcher mt1 = sp1.matcher(txtMa.getText());
        boolean cons1 = mt1.find();
        {
            if (cons1 == true) {
                lblMa.setText("*Không được nhập kí tự đặc biệt");
                txtMa.requestFocus();
                dem++;
            }
        }

        Pattern sp2 = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~]", Pattern.CASE_INSENSITIVE);
        Matcher mt2 = sp2.matcher(txtTen.getText());
        boolean cons2 = mt2.find();
        {
            if (cons2 == true) {
                lblTen.setText("*Không được nhập kí tự đặc biệt");
                txtTen.requestFocus();
                dem++;
            }
        }

        Pattern sp3 = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~[a-z]]", Pattern.CASE_INSENSITIVE);
        Matcher mt3 = sp3.matcher(txtSDT.getText());
        boolean cons3 = mt3.find();
        {
            if (cons3 == true) {
                lblSDT.setText("*Không được nhập kí tự đặc biệt");
                txtSDT.requestFocus();
                dem++;
            }
        }

        Pattern sp4 = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~]", Pattern.CASE_INSENSITIVE);
        Matcher mt4 = sp4.matcher(txtDiaChi.getText());
        boolean cons4 = mt4.find();
        {
            if (cons4 == true) {
                lblDiaChi.setText("*Không được nhập kí tự đặc biệt");
                txtDiaChi.requestFocus();
                dem++;
            }
        }

        Pattern sp5 = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~]", Pattern.CASE_INSENSITIVE);
        Matcher mt5 = sp5.matcher(txtMatKhau.getText());
        boolean cons5 = mt5.find();
        {
            if (cons5 == true) {
                lblMatKhau.setText("*Không được nhập kí tự đặc biệt");
                txtMatKhau.requestFocus();
                dem++;
            }
        }

        return dem == 0;

    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Menu().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateNgayBatDau;
    private com.toedter.calendar.JDateChooser DateNgayKetThuc;
    private javax.swing.JPanel Goc;
    private com.toedter.calendar.JDateChooser NgayBatDau;
    private com.toedter.calendar.JDateChooser NgayKetThuc;
    public static javax.swing.JTextField barcode;
    private javax.swing.JButton btnCapNhap;
    private javax.swing.JButton btnHienThi;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLoadNV;
    private javax.swing.JButton btnLuuKH;
    private javax.swing.JButton btnMailDoanhThu;
    private javax.swing.JButton btnMailSanPham;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnQuetMa;
    private javax.swing.JButton btnResetNV;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnSua2;
    private javax.swing.JButton btnSuaNV;
    private javax.swing.JButton btnSuaThuocTinh;
    private javax.swing.JButton btnTaoDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnThemExcel;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JButton btnThemThuocTinh;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiemTheoNam;
    private javax.swing.JButton btnTimTheoNgay1;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoa1;
    private javax.swing.JButton btnXoa2;
    private javax.swing.JButton btnXoaAllGH;
    private javax.swing.JButton btnXoaThuocTinh;
    private javax.swing.JButton btnXuatFile;
    private javax.swing.JButton btnXuatFile1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbBoloc;
    private javax.swing.JComboBox<String> cbbChatLieu;
    private javax.swing.JComboBox<String> cbbHang;
    private javax.swing.JComboBox<String> cbbLocCv;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbNam;
    private javax.swing.JComboBox<String> cbbSize;
    private javax.swing.JComboBox<String> cbbThangLamViec;
    private javax.swing.JComboBox<String> cbbThongKeSanPham;
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JCheckBox ckbTrangThai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JLabel jasdf;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblBanHang;
    private javax.swing.JLabel lblDangXuat;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblDiaChiGH;
    private javax.swing.JLabel lblDieuKien;
    private javax.swing.JLabel lblDoanhthu;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGiaBan;
    private javax.swing.JLabel lblGiaNhap;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblGiamGiaGH;
    private javax.swing.JLabel lblGiamGiaTQ;
    private javax.swing.JLabel lblGiaoCa;
    private javax.swing.JLabel lblHoaDonGiao;
    private javax.swing.JLabel lblHoaDonHuy1;
    private javax.swing.JLabel lblLoi;
    private javax.swing.JLabel lblMa;
    private javax.swing.JLabel lblMa1;
    private javax.swing.JLabel lblMaGiay;
    private javax.swing.JLabel lblMaVanChuyenGH;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblNamBaoHanh;
    private javax.swing.JLabel lblNgayBatDau;
    private javax.swing.JLabel lblNgayHienTai;
    private javax.swing.JLabel lblNgayKetThuc;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblNgaySinhGH;
    private javax.swing.JLabel lblNhanvien;
    private javax.swing.JLabel lblPhanTramGiam;
    private javax.swing.JLabel lblPhiShip;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblSDTGH;
    private javax.swing.JLabel lblSanpham;
    private javax.swing.JLabel lblSoGiayHet;
    private javax.swing.JLabel lblSoGiayKd;
    private javax.swing.JLabel lblSoGiaySapHet;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblSoLuong1;
    private javax.swing.JLabel lblTen;
    private javax.swing.JLabel lblTenGiay;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblThoat;
    private javax.swing.JLabel lblThongKe;
    private javax.swing.JLabel lblTienChuyenKhoan;
    private javax.swing.JLabel lblTienDuTQ;
    private javax.swing.JLabel lblTienKhachTra;
    private javax.swing.JLabel lblTienMatTQ;
    private javax.swing.JLabel lblTongDonHang;
    private javax.swing.JPanel pnlBanHang;
    private javax.swing.JPanel pnlGiamGia;
    private javax.swing.JPanel pnlGiaoHang;
    private javax.swing.JPanel pnlNenGiaoCa;
    private javax.swing.JPanel pnlNhanvien;
    private javax.swing.JPanel pnlQuyen;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JPanel pnlSoLuong;
    private javax.swing.JPanel pnlTaiQuay;
    private javax.swing.JPanel pnlTheoNam;
    private javax.swing.JPanel pnlTheoNgay;
    private javax.swing.JPanel pnlThongKe;
    private javax.swing.JPanel pnlTimkiemTong;
    private javax.swing.JPanel pnlTong;
    private javax.swing.JPanel pnlWebCam;
    private javax.swing.JMenuItem popupHoanThanhHD;
    private javax.swing.JMenuItem popupHoanThanhHDTreo;
    private javax.swing.JMenuItem popupTreoHD;
    private javax.swing.JRadioButton rdoChatLieu;
    private javax.swing.JRadioButton rdoCo;
    private javax.swing.JRadioButton rdoHang;
    private javax.swing.JRadioButton rdoLam;
    private javax.swing.JRadioButton rdoMauSac;
    private javax.swing.JRadioButton rdoNghi;
    private javax.swing.JRadioButton rdoSize;
    private javax.swing.JRadioButton rdokhong;
    private javax.swing.JScrollPane scpGiao;
    private javax.swing.JScrollPane scpHoanThanh;
    private javax.swing.JScrollPane scpTao;
    private javax.swing.JScrollPane scpTreo;
    private javax.swing.JTabbedPane tbbTTHD;
    private javax.swing.JTabbedPane tbbTao;
    private javax.swing.JTable tblChiTietGiamGia;
    private javax.swing.JTable tblChiTietGiaoCa;
    private javax.swing.JTable tblChiTietSP;
    private javax.swing.JTable tblGiamGia;
    private javax.swing.JTable tblGiay;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDonCho;
    private javax.swing.JTable tblHoaDonGiao;
    private javax.swing.JTable tblHoaDonHoanThanh;
    private javax.swing.JTable tblHoaDonTreo;
    private javax.swing.JTable tblNhanvien;
    private javax.swing.JTable tblNhanvienTK;
    private javax.swing.JTable tblSanPhamBanHang;
    private javax.swing.JTable tblThongKe;
    private javax.swing.JTable tblThongKe1;
    private javax.swing.JTable tblThuocTinh;
    private javax.swing.JTextField txtCanTraGH;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDiaChiGH;
    private javax.swing.JTextField txtDieuKien;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtGiamGiaGH;
    private javax.swing.JTextField txtKhuyenMaiThemTQ;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMa1;
    public static javax.swing.JTextField txtMaBarCode;
    private javax.swing.JTextField txtMaGiay;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaThuocTinh;
    private javax.swing.JTextField txtMaVanChuyen;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtNamBaoHanh;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtNgaySinhKH;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtPhanTramGiam;
    private javax.swing.JTextField txtPhiShip;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSDTGH;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtSoLuong1;
    private javax.swing.JTextField txtSoluong;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenGiay;
    private javax.swing.JTextField txtTenKHGH;
    public static javax.swing.JLabel txtTenNhanVien;
    private javax.swing.JTextField txtTenThuocTinh;
    private javax.swing.JTextField txtTienCKTQ;
    private javax.swing.JTextField txtTienDuTQ;
    private javax.swing.JTextField txtTienKhachCanTra;
    private javax.swing.JTextField txtTienKhachTra;
    private javax.swing.JTextField txtTienMatTQ;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiemNV;
    private javax.swing.JTextField txtTimSP;
    private javax.swing.JTextField txtTongTienGH;
    private javax.swing.JTextField txtTongTienTQ;
    // End of variables declaration//GEN-END:variables
}
