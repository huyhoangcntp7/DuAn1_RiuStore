/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import DomainModel.HoaDonBanHang;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import Unility.JDBC_Helper_1;
import viewModel.ThongTinHoaDonBanHang;
import viewModel.HoaDonCTBanHang;
import viewModel.SanPhamBanHang;

/**
 *
 * @author DeLL
 */
public class HoaDonBanHangRepository {

    public static int updateTTTReo(HoaDonBanHang hdbh, String liDo, String idhd) {
        String sql = "update HoaDon set trangThai = 5, idKhachHang = ? , ngayThanhToan = ?, ngayGiaoHang = ?, phiShip = ? ,\n"
                + "giamGiaThem = ?, tienMat = ?, tienKhac = ?,liDoTreo=? where id = ?";
        return JDBC_Helper_1.updateTong(sql, hdbh.getIdkh(), hdbh.getNgaythanhtoan(), hdbh.getNgaygiaohang(),
                hdbh.getPhiShip(), hdbh.getGiamgia(), hdbh.getTienMat(), hdbh.getTienKhac(), liDo, idhd);
    }

    public static int updateTTTReoHDTaoTQ(String liDo, String idhd) {
        String sql = "update HoaDon set trangThai = 5, liDoTreo=? where id = ?";
        return JDBC_Helper_1.updateTong(sql, liDo, idhd);
    }

    public static int updateTTTReoHDTaoGH(String liDo, String idhd, String idkh ) {
        String sql = "update HoaDon set trangThai = 5, liDoTreo=?, idKhachHang = ? where id = ?";
        return JDBC_Helper_1.updateTong(sql, liDo, idkh, idhd);
    }

    public static int updateTTHuy(HoaDonBanHang hdbh, String liDo, String idhd) {
        String sql = "update HoaDon set trangThai = 5, idKhachHang = ? , ngayThanhToan = ?, ngayGiaoHang = ?, phiShip = ?,\n"
                + "giamGiaThem = ?, tienMat = ?, tienKhac = ?,liDoHuy=? where id = ?";
        return JDBC_Helper_1.updateTong(sql, hdbh.getIdkh(), hdbh.getNgaythanhtoan(), hdbh.getNgaygiaohang(),
                hdbh.getPhiShip(), hdbh.getMagd(), hdbh.getGiamgia(), hdbh.getTienMat(), hdbh.getTienKhac(), liDo, idhd);
    }

    public static List<ThongTinHoaDonBanHang> getAllHHTreo() {
        List<ThongTinHoaDonBanHang> list = new ArrayList<>();
        String sql = "select  maHoaDon,hoTenNhanVien, ngayTao, giamGiaThem, phiShip,maVanChuyen ,hoaDon.liDoTreo ,KhachHang.hoTenKhachHang,\n"
                + "                KhachHang.soDienThoai,KhachHang.diaChi,KhachHang.ngaySinh, HoaDon.hinhThucMua, HoaDon.tienMat, HoaDon.tienKhac,\n"
                + "                       SUM( soLuong * donGia) as 'ThanhTien' from HoaDon join NhanVien on HoaDon.idNhanVien = NhanVien.id\n"
                + "                  left join KhachHang on KhachHang.id = HoaDon.idKhachHang\n"
                + "                  left join HoaDonChiTiet on HoaDon.id =  HoaDonChiTiet.idHoaDon \n"
                + "				  where HoaDon.trangThai = 5\n"
                + "				  group by  maHoaDon,hoTenNhanVien, ngayTao,maVanChuyen, giamGiaThem, phiShip, maGiaoDich , KhachHang.hoTenKhachHang,	  \n"
                + "                KhachHang.soDienThoai,KhachHang.diaChi,KhachHang.ngaySinh, HoaDon.hinhThucMua, HoaDon.tienMat, HoaDon.tienKhac,liDotreo";
        ResultSet rs = JDBC_Helper_1.selectTong(sql);
        try {
            while (rs.next()) {
                String ma = rs.getString("maHoaDon");
                String tennv = rs.getString("hoTenNhanVien");
                String tenkh = rs.getString("hoTenKhachHang");
                String ngaytao = rs.getString("ngayTao");
                double giamThem = rs.getDouble("giamGiaThem");
                double phiShip = rs.getDouble("phiShip");
                String maVC = rs.getString("maVanChuyen");
                String sdt = rs.getString("soDienThoai");
                String diaChi = rs.getString("diaChi");
                String ngaySinh = rs.getString("ngaySinh");
                String hinhthucmua = rs.getString("hinhThucMua");
                double tienMat = rs.getDouble("tienMat");
                double tienKhac = rs.getDouble("tienKhac");
                double thanhtien = rs.getDouble("ThanhTien");
                String liDo = rs.getString("liDotreo");
                double khachcantra = thanhtien + phiShip - (thanhtien * (giamThem / 100));
                double tienDu = tienKhac + tienMat - khachcantra;
                ThongTinHoaDonBanHang hd = new ThongTinHoaDonBanHang(ma, tennv, ngaytao,
                        tenkh, sdt, hinhthucmua, diaChi, ngaySinh, maVC, thanhtien, khachcantra, tienDu, tienMat, tienKhac, phiShip, giamThem, liDo);
                list.add(hd);

            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // hoa don tim theo ngay 
    public static List<ThongTinHoaDonBanHang> getHoaDonByNgay(String ngayTao) {
        
        List<ThongTinHoaDonBanHang> list = new ArrayList<>();
        String sql = "select maHoaDon,hoTenNhanVien, ngayTao, giamGiaThem, phiShip,maVanChuyen , KhachHang.hoTenKhachHang,"
                + " KhachHang.soDienThoai,KhachHang.diaChi,KhachHang.ngaySinh, HoaDon.hinhThucMua, HoaDon.tienMat, HoaDon.tienKhac,\n"
                + "                soLuong * donGia as 'ThanhTien',liDoTreo,hoaDon.trangThai from HoaDon join NhanVien on HoaDon.idNhanVien = NhanVien.id\n"
                + "                left join KhachHang on KhachHang.id = HoaDon.idKhachHang\n"
                + "            left join HoaDonChiTiet on HoaDon.id =  HoaDonChiTiet.idHoaDon where ngayTao=?";
        ResultSet rs = JDBC_Helper_1.selectTong(sql,ngayTao);
        try {
            while (rs.next()) {
                String ma = rs.getString("maHoaDon");
                String tennv = rs.getString("hoTenNhanVien");
                String tenkh = rs.getString("hoTenKhachHang");
                String ngaytao = rs.getString("ngayTao");
                double giamThem = rs.getDouble("giamGiaThem");
                double phiShip = rs.getDouble("phiShip");
                String maVC = rs.getString("maVanChuyen");
                String sdt = rs.getString("soDienThoai");
                String diaChi = rs.getString("diaChi");
                String ngaySinh = rs.getString("ngaySinh");
                String hinhthucmua = rs.getString("hinhThucMua");
                double tienMat = rs.getDouble("tienMat");
                double tienKhac = rs.getDouble("tienKhac");
                double thanhtien = rs.getDouble("ThanhTien");
                String liDo = rs.getString("liDoTreo");
                int trangThai = rs.getInt("trangThai");
                double khachcantra = thanhtien + phiShip - (thanhtien * (giamThem / 100));
                double tienDu = tienKhac + tienMat - khachcantra;
                ThongTinHoaDonBanHang hd = new ThongTinHoaDonBanHang(ma, tennv, ngaytao,
                        tenkh, sdt, hinhthucmua, diaChi, ngaySinh, maVC, thanhtien, khachcantra, tienDu, tienMat, tienKhac, phiShip, giamThem, liDo,trangThai);
                list.add(hd);

            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static List<ThongTinHoaDonBanHang> getAllHDHT() {
        List<ThongTinHoaDonBanHang> list = new ArrayList<>();
        String sql = "select  maHoaDon,hoTenNhanVien, ngayTao, giamGiaThem, phiShip, maVanChuyen , KhachHang.hoTenKhachHang,\n"
                + "                KhachHang.soDienThoai,KhachHang.diaChi,KhachHang.ngaySinh, HoaDon.hinhThucMua, HoaDon.tienMat, HoaDon.tienKhac,\n"
                + "                       SUM( soLuong * donGia) as 'ThanhTien' from HoaDon join NhanVien on HoaDon.idNhanVien = NhanVien.id\n"
                + "                  left join KhachHang on KhachHang.id = HoaDon.idKhachHang\n"
                + "                  left join HoaDonChiTiet on HoaDon.id =  HoaDonChiTiet.idHoaDon \n"
                + "				  where HoaDon.trangThai = 1\n"
                + "				  group by  maHoaDon,hoTenNhanVien, ngayTao, giamGiaThem, phiShip, maGiaoDich, maVanChuyen , KhachHang.hoTenKhachHang,	  \n"
                + "                KhachHang.soDienThoai,KhachHang.diaChi,KhachHang.ngaySinh, HoaDon.hinhThucMua, HoaDon.tienMat, HoaDon.tienKhac";
        ResultSet rs = JDBC_Helper_1.selectTong(sql);
        try {
            while (rs.next()) {
                String ma = rs.getString("maHoaDon");
                String tennv = rs.getString("hoTenNhanVien");
                String tenkh = rs.getString("hoTenKhachHang");
                String ngaytao = rs.getString("ngayTao");
                double giamThem = rs.getDouble("giamGiaThem");
                double phiShip = rs.getDouble("phiShip");
                String maVC = rs.getString("maVanChuyen");
                String sdt = rs.getString("soDienThoai");
                String diaChi = rs.getString("diaChi");
                String ngaySinh = rs.getString("ngaySinh");
                String hinhthucmua = rs.getString("hinhThucMua");
                double tienMat = rs.getDouble("tienMat");
                double tienKhac = rs.getDouble("tienKhac");
                double thanhtien = rs.getDouble("ThanhTien");
                double khachcantra = thanhtien + phiShip - (thanhtien * (giamThem / 100));
                double tienDu = tienKhac + tienMat - khachcantra;
                ThongTinHoaDonBanHang hd = new ThongTinHoaDonBanHang(ma, tennv, ngaytao,
                        tenkh, sdt, hinhthucmua, diaChi, ngaySinh, maVC, thanhtien, khachcantra, tienDu, tienMat, tienKhac, phiShip, giamThem, null);
                list.add(hd);

            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<ThongTinHoaDonBanHang> getAllHDCho() {
        List<ThongTinHoaDonBanHang> list = new ArrayList<>();
        String sql = "select  maHoaDon,hoTenNhanVien, ngayTao, giamGiaThem, phiShip, maGiaoDich, maVanChuyen , KhachHang.hoTenKhachHang,\n"
                + "                KhachHang.soDienThoai,KhachHang.diaChi,KhachHang.ngaySinh, HoaDon.hinhThucMua, HoaDon.tienMat, HoaDon.tienKhac,\n"
                + "                       SUM( soLuong * donGia) as 'ThanhTien' from HoaDon join NhanVien on HoaDon.idNhanVien = NhanVien.id\n"
                + "                  left join KhachHang on KhachHang.id = HoaDon.idKhachHang\n"
                + "                  left join HoaDonChiTiet on HoaDon.id =  HoaDonChiTiet.idHoaDon \n"
                + "				  where HoaDon.trangThai = 2\n"
                + "				  group by  maHoaDon,hoTenNhanVien, ngayTao, giamGiaThem,maVanChuyen, phiShip, maGiaoDich , KhachHang.hoTenKhachHang,	  \n"
                + "                KhachHang.soDienThoai,KhachHang.diaChi,KhachHang.ngaySinh, HoaDon.hinhThucMua, HoaDon.tienMat, HoaDon.tienKhac\n"
                + "				  ";
        ResultSet rs = JDBC_Helper_1.selectTong(sql);
        try {
            while (rs.next()) {
                String ma = rs.getString("maHoaDon");
                String tennv = rs.getString("hoTenNhanVien");
                String tenkh = rs.getString("hoTenKhachHang");
                String ngaytao = rs.getString("ngayTao");
                double giamThem = rs.getDouble("giamGiaThem");
                double phiShip = rs.getDouble("phiShip");
                String maGD = rs.getString("maGiaoDich");
                String maVC = rs.getString("maVanChuyen");
                String sdt = rs.getString("soDienThoai");
                String diaChi = rs.getString("diaChi");
                String ngaySinh = rs.getString("ngaySinh");
                String hinhthucmua = rs.getString("hinhThucMua");
                double tienMat = rs.getDouble("tienMat");
                double tienKhac = rs.getDouble("tienKhac");
                double thanhtien = rs.getDouble("ThanhTien");
                double khachcantra = thanhtien + phiShip - (thanhtien * (giamThem / 100));
                double tienDu = tienKhac + tienMat - khachcantra;
                ThongTinHoaDonBanHang hd = new ThongTinHoaDonBanHang(ma, tennv, ngaytao,
                        tenkh, sdt, hinhthucmua, diaChi, ngaySinh, maVC, thanhtien, khachcantra, tienDu, tienMat, tienKhac, phiShip, giamThem, null);
                list.add(hd);

            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<ThongTinHoaDonBanHang> getAllHDGiao() {
        List<ThongTinHoaDonBanHang> list = new ArrayList<>();
        String sql = "select  maHoaDon,hoTenNhanVien, ngayTao, giamGiaThem, phiShip,  maVanChuyen , KhachHang.hoTenKhachHang,\n"
                + "                KhachHang.soDienThoai,KhachHang.diaChi,KhachHang.ngaySinh, HoaDon.hinhThucMua, HoaDon.tienMat, HoaDon.tienKhac,\n"
                + "                       SUM( soLuong * donGia) as 'ThanhTien' from HoaDon join NhanVien on HoaDon.idNhanVien = NhanVien.id\n"
                + "                  left join KhachHang on KhachHang.id = HoaDon.idKhachHang\n"
                + "                  left join HoaDonChiTiet on HoaDon.id =  HoaDonChiTiet.idHoaDon \n"
                + "				  where HoaDon.trangThai = 3\n"
                + "				  group by  maHoaDon,hoTenNhanVien, ngayTao,maVanChuyen, giamGiaThem, phiShip, maGiaoDich , KhachHang.hoTenKhachHang,	  \n"
                + "                KhachHang.soDienThoai,KhachHang.diaChi,KhachHang.ngaySinh, HoaDon.hinhThucMua, HoaDon.tienMat, HoaDon.tienKhac";
        ResultSet rs = JDBC_Helper_1.selectTong(sql);
        try {
            while (rs.next()) {
                String ma = rs.getString("maHoaDon");
                String tennv = rs.getString("hoTenNhanVien");
                String tenkh = rs.getString("hoTenKhachHang");
                String ngaytao = rs.getString("ngayTao");
                double giamThem = rs.getDouble("giamGiaThem");
                double phiShip = rs.getDouble("phiShip");

                String maVC = rs.getString("maVanChuyen");
                String sdt = rs.getString("soDienThoai");
                String diaChi = rs.getString("diaChi");
                String ngaySinh = rs.getString("ngaySinh");
                String hinhthucmua = rs.getString("hinhThucMua");
                double tienMat = rs.getDouble("tienMat");
                double tienKhac = rs.getDouble("tienKhac");
                double thanhtien = rs.getDouble("ThanhTien");
                double khachcantra = thanhtien + phiShip - (thanhtien * (giamThem / 100));
                double tienDu = tienKhac + tienMat - khachcantra;
                ThongTinHoaDonBanHang hd = new ThongTinHoaDonBanHang(ma, tennv, ngaytao,
                        tenkh, sdt, hinhthucmua, diaChi, ngaySinh, maVC, thanhtien, khachcantra, tienDu, tienMat, tienKhac, phiShip, giamThem, null);
                list.add(hd);

            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<ThongTinHoaDonBanHang> getAllHDTreo() {
        List<ThongTinHoaDonBanHang> list = new ArrayList<>();
        String sql = "select maHoaDon,hoTenNhanVien, ngayTao, giamGiaThem, phiShip, maVanChuyen , KhachHang.hoTenKhachHang,"
                + " KhachHang.soDienThoai,KhachHang.diaChi,KhachHang.ngaySinh, HoaDon.hinhThucMua, HoaDon.tienMat, HoaDon.tienKhac,\n"
                + "                soLuong * donGia as 'ThanhTien' from HoaDon join NhanVien on HoaDon.idNhanVien = NhanVien.id\n"
                + "                left join KhachHang on KhachHang.id = HoaDon.idKhachHang\n"
                + "            left join HoaDonChiTiet on HoaDon.id =  HoaDonChiTiet.idHoaDon where HoaDon.trangThai = 5";
        ResultSet rs = JDBC_Helper_1.selectTong(sql);
        try {
            while (rs.next()) {
                String ma = rs.getString("maHoaDon");
                String tennv = rs.getString("hoTenNhanVien");
                String tenkh = rs.getString("hoTenKhachHang");
                String ngaytao = rs.getString("ngayTao");
                double giamThem = rs.getDouble("giamGiaThem");
                double phiShip = rs.getDouble("phiShip");
                String maVC = rs.getString("maVanChuyen");
                String sdt = rs.getString("soDienThoai");
                String diaChi = rs.getString("diaChi");
                String ngaySinh = rs.getString("ngaySinh");
                String hinhthucmua = rs.getString("hinhThucMua");
                double tienMat = rs.getDouble("tienMat");
                double tienKhac = rs.getDouble("tienKhac");
                double thanhtien = rs.getDouble("ThanhTien");
                double khachcantra = thanhtien + phiShip - (thanhtien * (giamThem / 100));
                double tienDu = tienKhac + tienMat - khachcantra;
                ThongTinHoaDonBanHang hd = new ThongTinHoaDonBanHang(ma, tennv, ngaytao,
                        tenkh, sdt, hinhthucmua, diaChi, ngaySinh, maVC, thanhtien, khachcantra, tienDu, tienMat, tienKhac, phiShip, giamThem, null);
                list.add(hd);

            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<HoaDonCTBanHang> getHDCTByMaHD(String mahd) {
        List<HoaDonCTBanHang> listSP = new ArrayList<>();
        String sql = "select maGiay, tenGiay, HoaDonChiTiet.soLuong, donGia from HoaDonChiTiet "
                + "join ChiTietGiay on ChiTietGiay.id = HoaDonChiTiet.idChiTietGiay\n"
                + "				join HoaDon on HoaDon.id = HoaDonChiTiet.idHoaDon where maHoaDon = ?";
        ResultSet rs = JDBC_Helper_1.selectTong(sql, mahd);
        try {
            while (rs.next()) {
                String masp = rs.getString("maGiay");
                String ten = rs.getString("tenGiay");
                int sl = rs.getInt("soLuong");
                double donGia = rs.getDouble("donGia");
                HoaDonCTBanHang sp = new HoaDonCTBanHang(masp, ten, sl, donGia);
                listSP.add(sp);
            }
            return listSP;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getHDCho() {
        String sql = "select COUNT(*) as 'SL' from HoaDon where trangThai = 2";
        ResultSet rs = JDBC_Helper_1.selectTong(sql);
        try {
            while (rs.next()) {
                int sl = rs.getInt("SL");
                return sl;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 0;

    }

    public static String getIDNVByHoTen(String ten) {
        String sql = "select id from NhanVien where hoTenNhanVien = ?";
        ResultSet rs = JDBC_Helper_1.selectTong(sql, ten);
        try {
            while (rs.next()) {
                String id = rs.getString("id");
                return id;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;

    }

    public static int getSoHD() {
        String sql = "select COUNT(*) as 'sl' from HoaDon";
        ResultSet rs = JDBC_Helper_1.selectTong(sql);
        try {
            while (rs.next()) {
                int sl = rs.getInt("sl");
                return sl;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 0;

    }

    public static String getIDHDByMaHD(String maHD) {
        String sql = "select id from HoaDon where maHoaDon = ?";
        ResultSet rs = JDBC_Helper_1.selectTong(sql, maHD);
        try {
            while (rs.next()) {
                String id = rs.getString("id");
                return id;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static int updateTTHD(HoaDonBanHang hdbh, String idhd) {
        String sql = "update HoaDon set trangThai = 1 , ngayThanhToan = ?,ngayNhan = ?,maGiaoDich = ?,"
                + " giamGiaThem = ?, tienMat = ?, tienKhac = ? where id = ?";
        return JDBC_Helper_1.updateTong(sql, hdbh.getNgaythanhtoan(), hdbh.getNgaynhan(), hdbh.getMagd(),
                hdbh.getGiamgia(), hdbh.getTienMat(), hdbh.getTienKhac(), idhd);
    }

    public static int updateHuyHdGiao(String lyDoHuy, String idhdg) {
        String sql = "update HoaDon set trangThai = 4 , liDoHuy = ? where id = ? ";
        return JDBC_Helper_1.updateTong(sql, lyDoHuy, idhdg);
    }

    public static int updateTTHDHuy(HoaDonBanHang hdbh, String idhd) {
        String sql = "update HoaDon set trangThai = 5 , ngayThanhToan = ?,maGiaoDich = ?,"
                + " giamGiaThem = ?, tienMat = ?, tienKhac = ? where id = ?";
        return JDBC_Helper_1.updateTong(sql, hdbh.getNgaythanhtoan(), hdbh.getMagd(),
                hdbh.getGiamgia(), hdbh.getTienMat(), hdbh.getTienKhac(), idhd);
    }

    public static int updateTTDG(HoaDonBanHang hdbh, String idhd) {
        String sql = "update HoaDon set trangThai = 3, idKhachHang = ? , ngayThanhToan = ?, ngayGiaoHang = ?,"
                + " phiShip = ? ,maGiaoDich = ?,maVanChuyen = ?,\n"
                + "giamGiaThem = ?, tienMat = ?, tienKhac = ? where id = ?";
        return JDBC_Helper_1.updateTong(sql, hdbh.getIdkh(), hdbh.getNgaythanhtoan(), hdbh.getNgaygiaohang(),
                hdbh.getPhiShip(), hdbh.getMagd(), hdbh.getMavc(), hdbh.getGiamgia(), hdbh.getTienMat(), hdbh.getTienKhac(), idhd);
    }

    public static int add(HoaDonBanHang hdbh) {
        String sql = "insert into HoaDon (maHoaDon, idNhanVien, idKhachHang, "
                + "ngayTao,\n"
                + "gioTaoHoaDOn, trangThai, hinhThucMua, tienMat, tienKhac, giamGiaThem )"
                + " values (?,?,?,?,?,2,?,0.0,0.0,0.0)";
        return JDBC_Helper_1.updateTong(sql, hdbh.getMahd(), hdbh.getIdnv(),
                hdbh.getIdkh(), hdbh.getNgaytao(),
                hdbh.getGiohientai(), hdbh.getHinhthucmua()
        );
    }

    public static int deleteHD(String idhd) {
        String sql = "delete from HoaDon where id = ?";
        return JDBC_Helper_1.updateTong(sql, idhd);
    }

    public static String getHinhThucMuaByIdhd(String idhd) {
        String sql = "select hinhThucMua from HoaDon where id = ?";
        ResultSet rs = JDBC_Helper_1.selectTong(sql, idhd);
        try {
            while (rs.next()) {
                String htmua = rs.getString("hinhThucMua");
                return htmua;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static int getTTByIDHD(String idhd) {
        String sql = "select trangThai from HoaDon where id = ?";
        ResultSet rs = JDBC_Helper_1.selectTong(sql, idhd);
        try {
            while (rs.next()) {
                int trangthai = rs.getInt("trangThai");
                return trangthai;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public static ThongTinHoaDonBanHang getTTHDByIDHD(String idhd) {
        String sql = "select maHoaDon,hoTenNhanVien, ngayTao, giamGiaThem, phiShip, maVanChuyen , KhachHang.hoTenKhachHang,"
                + " KhachHang.soDienThoai,KhachHang.diaChi,KhachHang.ngaySinh, HoaDon.hinhThucMua, HoaDon.tienMat, HoaDon.tienKhac,\n"
                + "                soLuong * donGia as 'ThanhTien' from HoaDon join NhanVien on HoaDon.idNhanVien = NhanVien.id\n"
                + "                left join KhachHang on KhachHang.id = HoaDon.idKhachHang\n"
                + "            left join HoaDonChiTiet on HoaDon.id =  HoaDonChiTiet.idHoaDon where HoaDon.trangThai = 5 and HoaDon.id = ?";
        ResultSet rs = JDBC_Helper_1.selectTong(sql, idhd);
        try {
            while (rs.next()) {
                String ma = rs.getString("maHoaDon");
                String tennv = rs.getString("hoTenNhanVien");
                String tenkh = rs.getString("hoTenKhachHang");
                String ngaytao = rs.getString("ngayTao");
                double giamThem = rs.getDouble("giamGiaThem");
                double phiShip = rs.getDouble("phiShip");
                String maVC = rs.getString("maVanChuyen");
                String sdt = rs.getString("soDienThoai");
                String diaChi = rs.getString("diaChi");
                String ngaySinh = rs.getString("ngaySinh");
                String hinhthucmua = rs.getString("hinhThucMua");
                double tienMat = rs.getDouble("tienMat");
                double tienKhac = rs.getDouble("tienKhac");
                double thanhtien = rs.getDouble("ThanhTien");
                double khachcantra = thanhtien + phiShip - (thanhtien * (giamThem / 100));
                double tienDu = tienKhac + tienMat - khachcantra;
                ThongTinHoaDonBanHang hd = new ThongTinHoaDonBanHang(ma, tennv, ngaytao,
                        tenkh, sdt, hinhthucmua, diaChi, ngaySinh, maVC, thanhtien, khachcantra, tienDu, tienMat, tienKhac, phiShip, giamThem, null);

                return hd;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
