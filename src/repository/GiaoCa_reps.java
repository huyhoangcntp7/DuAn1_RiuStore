/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import DomainModel.giaoCa;
import Unility.JDBC_HELPER;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class GiaoCa_reps {

    public static giaoCa getAllGC() {
        giaoCa gc = new giaoCa();
        String sql = "SELECT [id]\n"
                + "      ,[maCa]\n"
                + "      ,[thoiGianNhanCa]\n"
                + "      ,[thoiGianGiaoCa]\n"
                + "      ,[idNhanVienTrongCa]\n"
                + "      ,[idNhanVienCaTiepTheo]\n"
                + "      ,[ngayLam]\n"
                + "      ,[tienBatDau]\n"
                + "      ,[tienDoanhThu]\n"
                + "      ,[tongTienMat]\n"
                + "      ,[tongtienKhac]\n"
                + "      ,[tongtienconlai]\n"
                + "      ,[tongTienTrongCa]\n"
                + "      ,[tongTienMatRut]\n"
                + "      ,[tienPhatSinh]\n"
                + "      ,[ghiChu]\n"
                + "      ,[idChuCuaHangReset]\n"
                + "      ,[trangThai]\n"
                + "  FROM [DA1_GiayRIU].[dbo].[Ca] where trangthai = 1";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql);
        try {
            while (rs.next()) {

                gc.setMa(rs.getString("maca"));
                gc.setThoigiannhanca(rs.getString("thoigiannhanca"));
                gc.setThoigiangiaoca(rs.getString("Thoigiangiaoca"));;
                gc.setGhichu(rs.getString("ghichu"));
                gc.setNgaylam(rs.getString("ngaylam"));
                gc.setTrangthai(rs.getInt("trangthai"));
                gc.setTienbatdau(rs.getDouble("tienbatdau"));
                gc.setTienconlai(rs.getDouble("tongtienconlai"));
                gc.setTienphatsinh(rs.getDouble("tienphatsinh"));
                gc.setTienmat(rs.getDouble("tongtienmat"));
                gc.setTienchuyenkhoan(rs.getDouble("tongtienkhac"));
                gc.setTienmatrut(rs.getDouble("tongtienmatrut"));
                gc.setTongtientrongca(rs.getDouble("tongtientrongca"));
                gc.setTiendoanhthu(rs.getDouble("tiendoanhthu"));
                gc.setIdnhanvientrongca(rs.getString("idnhanvientrongca"));
                gc.setIdnhanviencatieptheo(rs.getString("idnhanviencatieptheo"));
                gc.setIdchucuahang(rs.getString("idchucuahangReset"));

            }
            return gc;
        } catch (SQLException ex) {
            return null;
        }

    }

    public static String getIdByEmail(String email) {
        String id = null;
        String sql = "select id from nhanvien  where email = ? ";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql, email);
        try {
            while (rs.next()) {
                id = rs.getString("id");

            }
            return id;
        } catch (SQLException ex) {
            return null;
        }

    }

    public static String getTenByEmail(String email) {
        String ten = null;
        String sql = "select hotennhanvien from nhanvien  where email = ? ";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql, email);
        try {
            while (rs.next()) {
                ten = rs.getString("hotennhanvien");

            }
            return ten;
        } catch (SQLException ex) {
            return null;
        }

    }

    public static String getIdByMa(String ma) {
        String id = null;
        String sql = "select id from nhanvien  where manhanvien = ? ";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql, ma);
        try {
            while (rs.next()) {
                id = rs.getString("id");

            }
            return id;
        } catch (SQLException ex) {
            return null;
        }

    }

    public static List<String> getallMaNV() {
        List<String> ma = new ArrayList();
        String sql = "select maNhanVien from NhanVien  ";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql);
        try {
            while (rs.next()) {

                String ma1 = rs.getString("manhanvien");
                ma.add(ma1);

            }
            return ma;
        } catch (SQLException ex) {
            return null;
        }

    }

    public static int getSLCa() {
        int sl = 0;
        String sql = "select COUNT(id) from ca ";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql);
        try {
            while (rs.next()) {
                sl = rs.getInt(1);
            }
            return sl;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return sl;
        }

    }

    public static double getdoanhThubyThoiGian(String tgbd, String tgkt) {
        double dt = 0;
        String sql = "select sum(soLuong*donGia)  from HoaDon join HoaDonChiTiet on HoaDon.id = HoaDonChiTiet.idHoaDon where giotao >= ? \n"
                + "and giotao <= ? ";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql, tgbd, tgkt);
        try {
            while (rs.next()) {
                dt = rs.getDouble(1);
            }
            return dt;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return dt;
        }
    }

    public static double getTienmatbythoigian(String bd, String kt, String ngaytao) {
        double tienmat = 0;
        String sql = " select sum (tienMat)  from HoaDon join HoaDonChiTiet on HoaDon.id = HoaDonChiTiet.idHoaDon where giotaohoadon >= ? \n"
                + "and giotaohoadon <= ? and ngaytao = ?";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql, bd, kt, ngaytao);
        try {
            while (rs.next()) {
                tienmat = rs.getDouble(1);
            }
            return tienmat;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return tienmat;
        }
    }

    public static double getTienkhacbythoigian(String bd, String kt, String ngaytao) {
        double tienkhac = 0;
        String sql = "select sum (tienKhac)  from HoaDon join HoaDonChiTiet on HoaDon.id = HoaDonChiTiet.idHoaDon where giotaoHoadon >= ?\n"
                + "and giotaoHoaDon <= ? and ngaytao = ?";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql, bd, kt, ngaytao);
        try {
            while (rs.next()) {
                tienkhac = rs.getDouble(1);
            }
            return tienkhac;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return tienkhac;
        }
    }

    public static String getThoigianbatdauca() {
        String tgbd = null;
        String sql = "select thoiGianNhanCa from ca where trangThai = 1 ";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql);
        try {
            while (rs.next()) {
                tgbd = rs.getString(1);
            }
            return tgbd;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return tgbd;
        }
    }

    public static String getTTByHDTreo() {
        String tt = null;
        String sql = "select trangthai from hoadon where trangThai= '5' and hinhthucmua ='off'";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql);
        try {
            while (rs.next()) {
                tt = rs.getString("trangthai");

            }
            return tt;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;

        }

    }

    public static List<giaoCa> getGiaoCaTK(int thang, String id) {
        List<giaoCa> list = new ArrayList<>();

        String sql = "select ngayLam,thoiGianNhanCa,thoiGianGiaoCa,tongTienTrongCa,tienPhatSinh,ghiChu,tongTienMatRut,trangThai from Ca where idNhanVienTrongCa=? and YEAR(ngayLam)=YEAR(GETDATE()) and MONTH(ngayLam)=?";
        ResultSet rs = JDBC_HELPER.selectTongQuat(sql, id, thang);
        try {
            while (rs.next()) {
                giaoCa gc = new giaoCa();
                gc.setNgaylam(rs.getString(1));
                gc.setGioBatDau(String.valueOf(rs.getString(2)));

                gc.setGioKetThuc(rs.getString(3));
                gc.setTongtientrongca(rs.getDouble(4));
                gc.setTienphatsinh(rs.getDouble(5));
                gc.setGhichu(rs.getString(6));
                gc.setTienmatrut(rs.getDouble(7));
                gc.setTrangthai(rs.getInt(8));

                list.add(gc);
            }
            return list;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static int add(giaoCa gc) {
        String sql = "insert into ca (maca,thoigiannhanca,idnhanvientrongca,ngaylam,tienbatdau,trangthai) values(?,?,?,?,?,?)";
        return JDBC_HELPER.updateTongQuat(sql, gc.getMa(), gc.getThoigiannhanca(), gc.getIdnhanvientrongca(), gc.getNgaylam(), gc.getTienbatdau(), gc.getTrangthai());
    }

    public static int update(String thoigiaoca, String idnhanviencatieptheo, double tiendoanhthu, double tongtienkhac, double tongtienMat, double tongtientrongca, double tienphatsinh, String ghichu) {
        String sql = "update  ca set thoiGianGiaoCa = ?,idNhanVienCaTiepTheo = ?,tienDoanhThu=?,tongTienMat=?,tongtienKhac=?,tongTienTrongCa=?,tienPhatSinh=?,ghichu=?,trangthai=2 \n"
                + "where trangThai = 1 ";
        return JDBC_HELPER.updateTongQuat(sql, thoigiaoca, idnhanviencatieptheo, tiendoanhthu, tongtienMat, tongtienkhac, tongtientrongca, tienphatsinh, ghichu);
    }

    public static int updateCCH(String idcch, double tienrut) {
        String sql = "update ca set idChuCuaHangReset=?,tongTienMatRut=? where trangThai=1";
        return JDBC_HELPER.updateTongQuat(sql, idcch, tienrut);
    }

    public static boolean checkHoaDonTreo() {
        String gc = getTTByHDTreo();
        System.out.println("GiaoCa" + gc);
        if (gc == null) {
            return false;
        } else {
            return true;
        }
    }
    
    public static boolean checkTonTaiCaLam(){
     String gc = getThoigianbatdauca();
        if (gc == null) {
            return false;
        } else {
            return true;
        }
    
    }
}
